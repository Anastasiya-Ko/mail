package kopylova.mail.mapper;

import kopylova.mail.model.dictionary.Type;
import kopylova.mail.model.entity.PostalItem;
import kopylova.mail.model.view.PostalItemDTO;
import org.springframework.stereotype.Service;

/**
 *Маппинг Почтового отправления<p>
 * Сопоставление полей энтити, полям в дто
 */
@Service
public class PostalItemMapper {

    /**
     * Сопоставление данных дто, данным сущности почтового отправления
     * @param view представление почтового отправления
     * @return сущность почтовое отправление
     */
    public PostalItem mapperToEntity(PostalItemDTO view) {
        PostalItem entity = new PostalItem();

        entity.setId(view.getId());
        entity.setType(Type.valueOf(view.getType()));
        entity.setRecipientIndex(view.getRecipientIndex());
        entity.setRecipientAddress(view.getRecipientAddress());
        entity.setReceiverName(view.getReceiverName());

        return entity;

    }

    /**
     * Сопоставление данных сущности, данным дто
     * @param entity сущность почтового отправления
     * @return представление почтового отправления
     */
    public PostalItemDTO mapperToDTO(PostalItem entity) {

        PostalItemDTO view = new PostalItemDTO();

        view.setId(entity.getId());
        view.setType(String.valueOf(entity.getType()));
        view.setRecipientIndex(entity.getRecipientIndex());
        view.setRecipientAddress(entity.getRecipientAddress());
        view.setReceiverName(entity.getReceiverName());

        return view;
    }
}
