package kopylova.mail.controller;

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
public class PostOfficeController {

    PostOfficeService postOfficeService;

    @PostMapping
    public PostOfficeDTO create(@Valid @RequestBody PostOfficeDTO view){
        return postOfficeService.create(view);
    }

    @GetMapping("/one")
    public PostOfficeDTO readOnePostOffice(@RequestParam Long postOfficeId){
        return postOfficeService.readOnePostOffice(postOfficeId);
    }

    @GetMapping("/all")
    public List<PostOfficeDTO> readAllPostOffice(){
        return postOfficeService.readAllPostOffice();
    }


}
