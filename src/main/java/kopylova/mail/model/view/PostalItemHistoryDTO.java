package kopylova.mail.model.view;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostalItemHistoryDTO {

    Long id;

    @NotNull(message = "У Почтового отправления должен быть статус")
    String status;

    PostalItemDTO postalItemOwner;

    List<PostOfficeDTO> offices;
}
