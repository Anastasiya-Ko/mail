package kopylova.mail.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import kopylova.mail.model.view.PostalItemDTO;
import kopylova.mail.service.PostalItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Validated
@RequestMapping("/postal-item")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Контроллер Почтовое отправление", description = "Взаимодействие со справочником Почтовое отправление")
public class PostalItemController {

    PostalItemService postalItemService;

    @PostMapping
    @Operation(summary = "Внесение в базу нового почтового отправления")
    @ApiResponse(description = "Представление почтового отправления")
    public PostalItemDTO createPostalItem(
            @Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Представление почтового отправления") PostalItemDTO view) {
        return postalItemService.createPostalItem(view);
    }

    @GetMapping("/one")
    @Operation(summary = "Поиск почтового отправления по его идентификатору")
    @ApiResponse(description = "Представление почтового отправления")
    public PostalItemDTO readOnePostalItem(
            @RequestParam @Parameter(description = "Идентификатор почтового отправления")
            @Min(0) Long postalItemId){
        return postalItemService.readOnePostalItem(postalItemId);
    }

    @GetMapping("/all")
    @Operation(summary = "Чтение всех почтовых отправлений в бд")
    @ApiResponse(description = "Лист представлений почтового отправления")
    public List<PostalItemDTO> readAllPostalItems(){
        return postalItemService.readAllPostalItems();
    }

}
