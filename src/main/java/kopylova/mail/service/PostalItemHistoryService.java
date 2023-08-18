package kopylova.mail.service;

import kopylova.mail.mapper.PostalItemHistoryMapper;
import kopylova.mail.model.dictionary.Status;
import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.entity.PostalItem;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.model.view.PostOfficeDTO;
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

    /**
     * Назначение статуса движения посылки, при регистрации
     */
    public void registrationPostalItem(PostalItem postalItem, PostOffice office) {

        PostalItemHistory history = new PostalItemHistory();
        List<PostOffice> officeList = new ArrayList<>();
        officeList.add(office);

        history.setStatus(Status.REGISTRATION);
        history.setPostalItemOwner(postalItem);
        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);
    }

    /**
     * Обновление Истории движения посылки, ПО ПРИБЫТИЮ в промежуточный почтовый пункт
     */
    public PostalItemHistoryDTO arrivalPostalItem(PostalItem postalItem, PostOffice office) {


        PostalItemHistory history = new PostalItemHistory();
        List<PostOffice> officeList = new ArrayList<>();

        officeList.add(office);

        history.setStatus(Status.ARRIVAL);
        history.setPostalItemOwner(postalItem);
        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);

    }

    /**
     * Обновление Истории движения посылки, ПО УБЫТИЮ из почтового пункта
     */
    public PostalItemHistoryDTO departurePostalItem(PostalItem postalItem, PostOffice office) {

        PostalItemHistory history = new PostalItemHistory();
        List<PostOffice> officeList = new ArrayList<>();

        officeList.add(office);

        history.setStatus(Status.DEPARTURE);
        history.setPostalItemOwner(postalItem);
        history.setOffices(officeList);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }

    /**
     * Обновление Истории движения посылки, ПРИ ПОЛУЧЕНИИ почтового отправления адресатом
     */
    public PostalItemHistoryDTO receivedPostalItem(Long historyId) {

        PostalItemHistory history = new PostalItemHistory();

        history.setStatus(Status.RECEIVED);

        postalItemHistoryRepository.save(history);

        return postalItemHistoryMapper.mapperToDTO(history);
    }

    /**
     * Получение статуса почтового отправления
     */
    public String readStatusById(Long postalItemId) {
        return getById(postalItemId).getStatus().getDescriptions();

    }

    /**
     * Получение ПОЛНОЙ ИСТОРИИ одного почтового отправления
     */
    public List<PostalItemHistory> read(Long postalItemId) {
        return postalItemHistoryRepository.findAllByPostalItemOwnerId(postalItemId);
    }

    /**
     * Получение АКТУАЛЬНОГО статуса одного почтового отправления
     */
    public String readLastStatus(Long postalItemId) {

        var status = postalItemHistoryRepository.findAllByPostalItemOwnerId(postalItemId);

        String result = null;
        if (!status.isEmpty()){
            status.sort(Comparator.comparing(PostalItemHistory::getId));
            result = status.get(status.size() - 1).getStatus().getDescriptions();
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
