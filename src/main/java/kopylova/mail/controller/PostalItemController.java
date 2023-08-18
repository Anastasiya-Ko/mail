package kopylova.mail.controller;


import jakarta.validation.Valid;
import kopylova.mail.model.view.PostalItemDTO;
import kopylova.mail.service.PostalItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postal-item")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostalItemController {

    PostalItemService postalItemService;

    /**
     * Регистрация нового почтового отправления, с указанием id Почтового офиса
     */
    @PostMapping
    public PostalItemDTO createPostalItem(
            @Valid @RequestBody PostalItemDTO view,
            @RequestParam Long officeId) {
        return postalItemService.createPostalItem(view, officeId);
    }

    /**
     * Чтение одного Почтового отправления по id
     */
    @GetMapping("/one")
    public PostalItemDTO readOnePostalItem(@RequestParam Long postalItemId){
        return postalItemService.readOnePostalItem(postalItemId);
    }

    /**
     * Чтение всех Почтовых отправлений
     */
    @GetMapping("/all")
    public List<PostalItemDTO> readAllPostalItems(){
        return postalItemService.readAllPostalItems();
    }

}
