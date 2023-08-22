package kopylova.mail.model.view;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Представление Почтового отправления
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostalItemDTO {

    Long id;

    @NotNull(message = "У Почтового отправления должен быть тип")
    String type;

    @NotNull(message = "У Почтового отправления должен быть индекс получателя!")
    @Positive(message = "Индекс должен содержать только положительные числа")
    Integer recipientIndex;

    @NotBlank(message = "У Почтового отправления должен быть адрес получателя!")
    String recipientAddress;

    @NotBlank(message = "У Почтового отправления должно быть имя получателя!")
    String receiverName;

}
