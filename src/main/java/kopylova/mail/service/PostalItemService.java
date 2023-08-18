package kopylova.mail.service;

import kopylova.mail.mapper.PostalItemHistoryMapper;
import kopylova.mail.mapper.PostalItemMapper;
import kopylova.mail.model.entity.PostalItem;
import kopylova.mail.model.view.PostalItemDTO;
import kopylova.mail.repository.PostalItemRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с Почтовым отправлением
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemService {

    PostalItemRepository postalItemRepository;

    PostalItemHistoryService postalItemHistoryService;
    PostOfficeService postOfficeService;

    PostalItemMapper postalItemMapper;


    /**
     * Занесение нового почтового отправления в бд
     */
    public PostalItemDTO createPostalItem(PostalItemDTO view, Long officeId) {

        PostalItem entity = postalItemMapper.mapperToEntity(view);
        var office = postOfficeService.getById(officeId);

        postalItemRepository.save(entity);
        postalItemHistoryService.registrationPostalItem(entity, office);

        return postalItemMapper.mapperToDTO(entity);

    }

    /**
     * Чтение одного почтового отправления
     */
    public PostalItemDTO readOnePostalItem(Long id){

        return postalItemMapper.mapperToDTO(getById(id));
    }

    /**
     * Список всех почтовых отправлений
     */
    public List<PostalItemDTO> readAllPostalItems() {

        var entityList = postalItemRepository.findAll();
        return entityList.stream().map(entity -> postalItemMapper.mapperToDTO(entity)).collect(Collectors.toList());
    }

    /**
     * Метод внутреннего пользования, для получения Почтового отправления(сущности) по идентификатору
     */
    private PostalItem getById(Long postalItemId) {
        String ex = String.format(("Почтовое отправление с номером = %d не найдено"), postalItemId);
        return postalItemRepository.findById(postalItemId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, ex));
    }

}
