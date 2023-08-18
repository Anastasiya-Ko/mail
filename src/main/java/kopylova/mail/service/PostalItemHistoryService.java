package kopylova.mail.service;

import kopylova.mail.mapper.PostalItemHistoryMapper;
import kopylova.mail.model.dictionary.Status;
import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.entity.PostalItem;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.model.view.PostalItemHistoryDTO;
import kopylova.mail.repository.PostalItemHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

        PostalItemHistory history = new PostalItemHistory();

        List<PostOffice> officeList = new ArrayList<>();
        officeList.add(postOfficeService.getById(officeId));

        history.setPostalItemOwner(postalItemService.getById(postalItemId));
        history.setStatus(Status.REGISTRATION);
        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }

    /**
     * ??? переделать на получение id Обновление Истории движения посылки, ПО ПРИБЫТИЮ в промежуточный почтовый пункт
     */
    public PostalItemHistoryDTO arrivalPostalItem(PostalItem postalItem, PostOffice office) {


        PostalItemHistory history = new PostalItemHistory();
        List<PostOffice> officeList = new ArrayList<>();

        officeList.add(office);

        history.setPostalItemOwner(postalItem);
        history.setStatus(Status.ARRIVAL);
        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);

    }

    /**
     * переделать на получение id Обновление Истории движения посылки, ПО УБЫТИЮ из почтового пункта
     */
    public PostalItemHistoryDTO departurePostalItem(PostalItem postalItem) {

        PostalItemHistory history = new PostalItemHistory();

        history.setPostalItemOwner(postalItem);
        history.setStatus(Status.DEPARTURE);
        history.setOffices(null);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }

    /**
     * переделать на получение id Обновление Истории движения посылки, ПРИ ПОЛУЧЕНИИ почтового отправления адресатом
     */
    public PostalItemHistoryDTO receivedPostalItem(PostalItem postalItem) {

        PostalItemHistory history = new PostalItemHistory();

        history.setPostalItemOwner(postalItem);
        history.setStatus(Status.RECEIVED);
        history.setOffices(null);

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
