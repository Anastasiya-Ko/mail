package kopylova.mail.controller;

import jakarta.validation.Valid;
import kopylova.mail.model.entity.PostOffice;
import kopylova.mail.model.entity.PostalItem;
import kopylova.mail.model.entity.PostalItemHistory;
import kopylova.mail.model.view.PostalItemHistoryDTO;
import kopylova.mail.service.PostalItemHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы с историей передвижения Почтового отправления
 */

@RestController
@RequestMapping("/history-status")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemHistoryController {

    PostalItemHistoryService postalItemHistoryService;

    /**
     * Обновление Истории движения посылки, ПО ПРИБЫТИЮ в промежуточный почтовый пункт
     */
    @PostMapping("/arrival-postal-item")
    public PostalItemHistoryDTO arrivalPostalItem(@Valid @RequestBody PostalItem postalItem, PostOffice office){
        return postalItemHistoryService.arrivalPostalItem(postalItem, office);
    }

    /**
     * Обновление Истории движения посылки, ПО УБЫТИЮ из почтового пункта
     */
    @PostMapping("/departure-postal-item")
    public PostalItemHistoryDTO departurePostalItem(@Valid @RequestBody PostalItem postalItem){
        return postalItemHistoryService.departurePostalItem(postalItem);
    }

    /**
     * Обновление Истории движения посылки, ПРИ ПОЛУЧЕНИИ почтового отправления адресатом
     */
    @PostMapping("/received-postal-item")
    public PostalItemHistoryDTO receivedPostalItem(@Valid @RequestBody PostalItem postalItem){
        return postalItemHistoryService.receivedPostalItem(postalItem);
    }

    /**
     * Получение ВСЕЙ ИСТОРИИ движения одного почтового отправления
     */
    @GetMapping("/all-history")
    public List<PostalItemHistoryDTO> readAllPostalItemHistory(@RequestParam Long postalItemId){
        return postalItemHistoryService.readAllHistory(postalItemId);
    }

    /**
     * Получение КРАЙНЕГО СТАТУСА одного почтового отправления
     */
    @GetMapping("/last-status")
    public String readLastStatusPostalItem(@RequestParam Long postalItemId){
        return postalItemHistoryService.readLastStatus(postalItemId);
    }
}
