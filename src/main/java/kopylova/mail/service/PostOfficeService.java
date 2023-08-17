package kopylova.mail.service;

import kopylova.mail.mapper.PostOfficeMapper;
import kopylova.mail.repository.PostOfficeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

/**
 * Сервис для работы с Почтовым отделением
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostOfficeService {

    PostOfficeRepository postOfficeRepository;
    PostOfficeMapper postOfficeMapper;



}
