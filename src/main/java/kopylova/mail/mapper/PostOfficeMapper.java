package kopylova.mail.mapper;

import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.view.PostOfficeDTO;
import org.springframework.stereotype.Service;

/**
 * Маппинг Почтового отделения<p>
 * Сопоставление полей энтити, полям в дто
 */
@Service
public class PostOfficeMapper {

    /**
     * Сопоставление данных дто, данным сущности почтового отделения
     * @param view представление почтового отделения
     * @return сущность почтового отделения
     */
    public PostOffice mapperToEntity(PostOfficeDTO view) {
        PostOffice entity = new PostOffice();

        entity.setId(view.getId());
        entity.setOfficeIndex(view.getOfficeIndex());
        entity.setOfficeName(view.getOfficeName());
        entity.setOfficeAddress(view.getOfficeAddress());

        return entity;

    }

    /**
     * Сопоставление данных сущности, данным дто
     * @param entity сущность почтового отделения
     * @return представление почтового отделения
     */
    public PostOfficeDTO mapperToDTO(PostOffice entity) {

        PostOfficeDTO view = new PostOfficeDTO();

        view.setId(entity.getId());
        view.setOfficeIndex(entity.getOfficeIndex());
        view.setOfficeName(entity.getOfficeName());
        view.setOfficeAddress(entity.getOfficeAddress());


        return view;
    }
}
