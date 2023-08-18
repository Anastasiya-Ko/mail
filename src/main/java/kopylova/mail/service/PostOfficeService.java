package kopylova.mail.service;

import kopylova.mail.mapper.PostOfficeMapper;
import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.view.PostOfficeDTO;
import kopylova.mail.repository.PostOfficeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для работы с Почтовым отделением
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostOfficeService {

    PostOfficeRepository postOfficeRepository;
    PostOfficeMapper postOfficeMapper;


    /**
     * Создание нового Почтового отделения
     */
    public PostOfficeDTO create(PostOfficeDTO view) {

        var newOffice = postOfficeMapper.mapperToEntity(view);
        postOfficeRepository.save(newOffice);

        return postOfficeMapper.mapperToDTO(newOffice);
    }

    /**
     * Получение одного Почтового отделения, по его id
     */
    public PostOfficeDTO readOnePostOffice(Long postOfficeId){
        return postOfficeMapper.mapperToDTO(getById(postOfficeId));
    }

    public List<PostOfficeDTO> readAllPostOffice(){
        var listEntity = postOfficeRepository.findAll();
        return listEntity.stream().map(entity -> postOfficeMapper.mapperToDTO(entity)).collect(Collectors.toList());
    }

    public PostOffice getById(Long id) {
        return postOfficeRepository.findById(id).orElseThrow(() -> new RuntimeException("Почтовое отделение не найдено!"));
    }
}