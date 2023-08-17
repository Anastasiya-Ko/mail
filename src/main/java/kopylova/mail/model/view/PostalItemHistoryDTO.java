package kopylova.mail.model.view;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostalItemHistoryDTO {

    @NotNull(message = "У Почтового отправления должен быть статус")
    String status;

    @NotNull(message = "У Почтового отправления должна быть дата создания статуса")
    @PastOrPresent(message = "Дата создания статуса должна содержать прошедшую дату или сегодняшнее число")
    LocalDate createStatus;

    PostalItemDTO postalItemOwner;

    List<PostOfficeDTO> offices;
}
