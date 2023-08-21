package kopylova.mail.controller;

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
     * Регистрация почтового отправления
     */
    @PostMapping("/registration-postal-item")
    public PostalItemHistoryDTO registrationPostalItem(@RequestParam Long postalItemId, @RequestParam Long postOfficeId){
        return postalItemHistoryService.registrationPostalItem(postalItemId, postOfficeId);
    }

    /**
     * ПРИБЫТИЕ почтового отправления в промежуточный почтовый пункт
     */
    @PostMapping("/arrival-postal-item")
    public PostalItemHistoryDTO arrivalPostalItem(@RequestParam Long postalItemId, @RequestParam Long officeId){
        return postalItemHistoryService.arrivalPostalItem(postalItemId, officeId);
    }

    /**
     * УБЫТИЕ почтового отправления
     */
    @PostMapping("/departure-postal-item")
    public PostalItemHistoryDTO departurePostalItem(@RequestParam Long postalItemId){
        return postalItemHistoryService.departurePostalItem(postalItemId);
    }

    /**
     * ПОЛУЧЕНИЕ почтового отправления адресатом
     */
    @PostMapping("/received-postal-item")
    public PostalItemHistoryDTO receivedPostalItem(@RequestParam Long postalItemId){
        return postalItemHistoryService.receivedPostalItem(postalItemId);
    }

    /**
     * ВСЯ ИСТОРИЯ движения почтового отправления
     */
    @GetMapping("/all-history")
    public List<PostalItemHistoryDTO> readAllPostalItemHistory(@RequestParam Long postalItemId){
        return postalItemHistoryService.readAllHistory(postalItemId);
    }

    /**
     * КРАЙНИЙ СТАТУС почтового отправления
     */
    @GetMapping("/last-status")
    public String readLastStatusPostalItem(@RequestParam Long postalItemId){
        return postalItemHistoryService.readLastStatus(postalItemId);
    }
}
