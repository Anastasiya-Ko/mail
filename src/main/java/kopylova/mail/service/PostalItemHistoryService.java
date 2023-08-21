package kopylova.mail.service;

import kopylova.mail.mapper.PostalItemHistoryMapper;
import kopylova.mail.model.dictionary.Status;
import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.model.view.PostalItemHistoryDTO;
import kopylova.mail.repository.PostalItemHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Сервис для работы с Историей движения посылки
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemHistoryService {

    PostalItemHistoryRepository postalItemHistoryRepository;

    PostalItemHistoryMapper postalItemHistoryMapper;

    PostOfficeService postOfficeService;
    PostalItemService postalItemService;

    /**
     * РЕГИСТРАЦИЯ почтового отправления
     */
    public PostalItemHistoryDTO registrationPostalItem(Long postalItemId, Long officeId) {

        //Проверка на наличие отправления в бд
        postalItemService.getPostalItemById(postalItemId);

        //Проверка на наличие офиса в бд
        postOfficeService.getPostOfficeById(officeId);

        PostalItemHistory history = new PostalItemHistory();

        List<PostOffice> officeList = new ArrayList<>();
        officeList.add(postOfficeService.getPostOfficeById(officeId));

        history.setPostalItemOwner(postalItemService.getPostalItemById(postalItemId));

        if (!history.getStatus().getDescriptions().equals(readLastStatus(postalItemId))) {
            history.setStatus(Status.REGISTRATION);
        } else throw new RuntimeException("Статус почтового отправления должен отличаться от предыдущего");

        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }

    /**
     * ПРИБЫТИЕ почтового отправления в промежуточный почтовый пункт
     */
    public PostalItemHistoryDTO arrivalPostalItem(Long postalItemId, Long officeId) {

        //Проверка на наличие отправления в бд
        postalItemService.getPostalItemById(postalItemId);

        //Проверка на наличие офиса в бд
        postOfficeService.getPostOfficeById(officeId);

        PostalItemHistory history = new PostalItemHistory();

        List<PostOffice> officeList = new ArrayList<>();
        officeList.add(postOfficeService.getPostOfficeById(officeId));

        history.setPostalItemOwner(postalItemService.getPostalItemById(postalItemId));

        if (!history.getStatus().getDescriptions().equals(readLastStatus(postalItemId))) {
            history.setStatus(Status.ARRIVAL);
        } else throw new RuntimeException("Статус почтового отправления должен отличаться от предыдущего");

        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);

    }

    /**
     * УБЫТИЕ из почтового пункта
     */
    public PostalItemHistoryDTO departurePostalItem(Long postalItemId) {

        //Проверка на наличие отправления в бд
        postalItemService.getPostalItemById(postalItemId);

        PostalItemHistory history = new PostalItemHistory();

        history.setPostalItemOwner(postalItemService.getPostalItemById(postalItemId));

        if (!history.getStatus().getDescriptions().equals(readLastStatus(postalItemId))) {
            history.setStatus(Status.DEPARTURE);
        } else throw new RuntimeException("Статус почтового отправления должен отличаться от предыдущего");

        history.setOffices(Collections.emptyList());

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }

    /**
     * ПОЛУЧЕНИЕ почтового отправления адресатом
     */
    public PostalItemHistoryDTO receivedPostalItem(Long postalItemId) {

        //Проверка на наличие отправления в бд
        postalItemService.getPostalItemById(postalItemId);

        PostalItemHistory history = new PostalItemHistory();

        history.setPostalItemOwner(postalItemService.getPostalItemById(postalItemId));

        if (!history.getStatus().getDescriptions().equals(readLastStatus(postalItemId))) {
            history.setStatus(Status.RECEIVED);
        } else throw new RuntimeException("Статус почтового отправления должен отличаться от предыдущего");

        history.setOffices(Collections.emptyList());

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }


    /**
     * Получение ПОЛНОЙ ИСТОРИИ одного почтового отправления
     */
    public List<PostalItemHistoryDTO> readAllHistory(Long postalItemId) {

        List<PostalItemHistory> listEntity = postalItemHistoryRepository.findAllByPostalItemOwnerId(postalItemId);

        return listEntity.stream().map(list -> postalItemHistoryMapper.mapperToDTO(list)).collect(Collectors.toList());
    }

    /**
     * Получение КРАЙНЕГО статуса одного почтового отправления
     */
    public String readLastStatus(Long postalItemId) {

        var history = postalItemHistoryRepository.findAllByPostalItemOwnerId(postalItemId);

        String result = null;
        if (!history.isEmpty()){
            history.sort(Comparator.comparing(PostalItemHistory::getId));
            result = history.get(history.size() - 1).getStatus().getDescriptions();
        }

        return result;
    }

    /**
     * Метод внутреннего пользования, для получения Истории движения посылки(сущности) по идентификатору
     */
    private PostalItemHistory getById(Long postalItemHistoryId) {
        String ex = String.format(("Истории движения посылки с номером = %d не найдена"), postalItemHistoryId);
        return postalItemHistoryRepository.findById(postalItemHistoryId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ex));
    }


}
