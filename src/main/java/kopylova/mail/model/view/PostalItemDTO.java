package kopylova.mail.model.view;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Schema(description = "Представление Почтового отправления")
public class PostalItemDTO {

    @Schema(description = "Идентификатор почтового отправления", accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotNull(message = "У Почтового отправления должен быть тип")
    @Schema(description = "Тип почтового отправления", example = "LETTER/PARCEL/PACKAGE/POSTCARD")
    String type;

    @NotNull(message = "У Почтового отправления должен быть индекс получателя!")
    @Positive(message = "Индекс должен содержать только положительные числа")
    @Schema(description = "Индекс получателя почтового отправления", example = "620123")
    Integer recipientIndex;

    @NotBlank(message = "У Почтового отправления должен быть адрес получателя!")
    @Schema(description = "Адрес получателя почтового отправления", example = "г.Город, ул. Улица, д.1")
    String recipientAddress;

    @NotBlank(message = "У Почтового отправления должно быть имя получателя!")
    @Schema(description = "Имя получателя почтового отправления", example = "Лев Толстой")
    String receiverName;

}
