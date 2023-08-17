package kopylova.mail.mapper;

import kopylova.mail.model.dictionary.Status;
import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.model.view.PostOfficeDTO;
import kopylova.mail.model.view.PostalItemHistoryDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Маппинг Истории почтового отправления<p>
 * Сопоставление полей энтити, полям в дто
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemHistoryMapper {

    PostalItemMapper postalItemMapper;

    /**
     * Сопоставление данных дто, данным сущности истории почтового отделения
     * @param view представление истории почтового отделения
     * @return сущность истории почтового отделения
     */
    public PostalItemHistory mapperToEntity(PostalItemHistoryDTO view) {
        PostalItemHistory entity = new PostalItemHistory();

        entity.setStatus(Status.valueOf(view.getStatus()));
        entity.setCreateStatus(view.getCreateStatus());
        entity.setPostalItemOwner(postalItemMapper.mapperToEntity(view.getPostalItemOwner()));

        return entity;

    }

    /**
     * Сопоставление данных сущности, данным дто
     * @param entity сущность истории почтового отделения
     * @return представление истории почтового отделения
     */
    public PostalItemHistoryDTO mapperToDTO(PostalItemHistory entity) {

        PostalItemHistoryDTO view = new PostalItemHistoryDTO();

        return view;
    }
}
