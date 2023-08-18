package kopylova.mail.controller;

import jakarta.websocket.server.PathParam;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.service.PostalItemHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы и историей передвижения Почтового отправления
 */

@RestController
@RequestMapping("/history-status")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemHistoryController {

    PostalItemHistoryService service;

    /**
     * Получение всех статусов одного почтового отправления
     */
    @GetMapping
    public List<PostalItemHistory> read(@RequestParam Long postalItemId){
        return service.read(postalItemId);
    }

    /**
     * Получение актуального статуса одного почтового отправления
     */
    @GetMapping("/last-status")
    public String readLastStatus(@RequestParam Long postalItemId){
        return service.readLastStatus(postalItemId);
    }
}
