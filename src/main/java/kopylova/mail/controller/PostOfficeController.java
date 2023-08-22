package kopylova.mail.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kopylova.mail.model.view.PostOfficeDTO;
import kopylova.mail.service.PostOfficeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-office")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Контроллер Почтовый офис", description = "Взаимодействие со справочником Почтовый офис")
public class PostOfficeController {

    PostOfficeService postOfficeService;

    @PostMapping
    @Operation(summary = "Внесение в базу нового почтового офиса")
    public PostOfficeDTO createOffice(
            @Valid @RequestBody @Parameter(description = "Представление почтового офиса") PostOfficeDTO view){
        return postOfficeService.createOffice(view);
    }

    @GetMapping("/one")
    @Operation(summary = "Поиск почтового офиса по его идентификатору")
    public PostOfficeDTO readOnePostOffice(
            @RequestParam @Parameter(description = "Идентификатор почтового офиса") Long postOfficeId){
        return postOfficeService.readOnePostOffice(postOfficeId);
    }

    @GetMapping("/all")
    @Operation(summary = "Чтение всех почтовых офисов в бд")
    public List<PostOfficeDTO> readAllPostOffice(){
        return postOfficeService.readAllPostOffice();
    }


}
