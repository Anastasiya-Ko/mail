package kopylova.mail.service;

import kopylova.mail.mapper.PostalItemHistoryMapper;
import kopylova.mail.model.dictionary.Status;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.model.view.PostalItemHistoryDTO;
import kopylova.mail.repository.PostalItemHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Сервис для работы с Историей движения посылки
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemHistoryService {

    PostalItemHistoryRepository postalItemHistoryRepository;
    PostalItemHistoryMapper postalItemHistoryMapper;

    /**
     * Обновление Истории движения посылки, по прибытию в промежуточный почтовый пункт
     * @param historyId id Истории движения посылки для изменения в бд
     * @return представление, изменённой в бд, сущности Истории движения посылки
     */
    public PostalItemHistoryDTO arrivalPostalItem(Long historyId) {
        var updateEntity = getById(historyId);
        updateEntity.setStatus(Status.ARRIVAL);
        postalItemHistoryRepository.save(updateEntity);
        return postalItemHistoryMapper.mapperToDTO(updateEntity);
    }

    /**
     * Обновление Истории движения посылки, по убытию из почтового пункта
     * @param historyId id Истории движения посылки для изменения в бд
     * @return представление, изменённой в бд, сущности Истории движения посылки
     */
    public PostalItemHistoryDTO departurePostalItem(Long historyId) {
        var updateEntity = getById(historyId);
        updateEntity.setStatus(Status.DEPARTURE);
        postalItemHistoryRepository.save(updateEntity);
        return postalItemHistoryMapper.mapperToDTO(updateEntity);
    }

    /**
     * Обновление Истории движения посылки, при получении почтового отправления адресатом
     * @param historyId id Истории движения посылки для изменения в бд
     * @return представление, изменённой в бд, сущности Истории движения посылки
     */
    public PostalItemHistoryDTO receivedPostalItem(Long historyId) {
        var updateEntity = getById(historyId);
        updateEntity.setStatus(Status.RECEIVED);
        postalItemHistoryRepository.save(updateEntity);
        return postalItemHistoryMapper.mapperToDTO(updateEntity);
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
