package kopylova.mail.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kopylova.mail.model.view.PostalItemHistoryDTO;
import kopylova.mail.service.PostalItemHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history-status")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Контроллер История движения почтового отправления",
     description = "Взаимодействие со справочником История движения почтового отправления")
public class PostalItemHistoryController {

    PostalItemHistoryService postalItemHistoryService;

    @PostMapping("/registration-postal-item")
    @Operation(summary = "Назначение почтовому отправлению статуса - зарегистрировано")
    public PostalItemHistoryDTO registrationPostalItem(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления") Long postalItemId,
            @RequestParam @Parameter(description = "Идентификатор почтового офиса") Long postOfficeId){
        return postalItemHistoryService.registrationPostalItem(postalItemId, postOfficeId);
    }

    @PostMapping("/arrival-postal-item")
    @Operation(summary = "Назначение почтовому отправлению статуса - прибыло в промежуточное почтовое отделение")
    public PostalItemHistoryDTO arrivalPostalItem(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления") Long postalItemId,
            @RequestParam @Parameter(description = "Идентификатор почтового офиса") Long postOfficeId){
        return postalItemHistoryService.arrivalPostalItem(postalItemId, postOfficeId);
    }

    @PostMapping("/departure-postal-item")
    @Operation(summary = "Назначение почтовому отправлению статуса - убыло из почтового отделения")
    public PostalItemHistoryDTO departurePostalItem(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления") Long postalItemId){
        return postalItemHistoryService.departurePostalItem(postalItemId);
    }


    @PostMapping("/received-postal-item")
    @Operation(summary = "Назначение почтовому отправлению статуса - получено адресатом")
    public PostalItemHistoryDTO receivedPostalItem(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления") Long postalItemId){
        return postalItemHistoryService.receivedPostalItem(postalItemId);
    }

    @GetMapping("/all-history")
    @Operation(summary = "Полная история движения почтового отправления")
    public List<PostalItemHistoryDTO> readAllPostalItemHistory(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления") Long postalItemId){
        return postalItemHistoryService.readAllHistory(postalItemId);
    }

    @GetMapping("/last-status")
    @Operation(summary = "Получение текущего статуса почтового отправления")
    public String readLastStatusPostalItem(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления") Long postalItemId){
        return postalItemHistoryService.readLastStatus(postalItemId);
    }
}
