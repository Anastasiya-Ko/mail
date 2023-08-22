package kopylova.mail.model.view;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Представление Истории Почтового отправления")
public class PostalItemHistoryDTO {

    @Schema(description = "Идентификатор истории почтового отправления", accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotNull(message = "У Почтового отправления должен быть статус")
    @Schema(description = "Статус почтового отправления", example = "REGISTRATION/ARRIVAL/DEPARTURE/RECEIVED")
    String status;

    @Schema(description = "Почтовое отправление, принадлежащее данной истории")
    PostalItemDTO postalItemOwner;

    @Schema(description = "Почтовый офис, принадлежащий данной истории")
    List<PostOfficeDTO> offices;
}
