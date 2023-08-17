package kopylova.mail.service;

import kopylova.mail.mapper.PostalItemMapper;
import kopylova.mail.model.entity.PostalItem;
import kopylova.mail.model.view.PostalItemDTO;
import kopylova.mail.repository.PostalItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с Почтовым отправлением
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemService {

    PostalItemRepository postalItemRepository;
    PostalItemMapper postalItemMapper;


    /**
     * Занесение нового почтового отправления в бд
     * @param view представление почтового отправления для внесения в бд
     * @return представление, сохранённой в бд, сущности почтового отправления
     */
    public PostalItemDTO createPostalItem(PostalItemDTO view) {
        PostalItem entity = postalItemMapper.mapperToEntity(view);
        postalItemRepository.save(entity);
        return postalItemMapper.mapperToDTO(entity);

    }

}
