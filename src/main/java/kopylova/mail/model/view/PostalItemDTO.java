package kopylova.mail.model.view;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import kopylova.mail.model.dictionary.Type;
import kopylova.mail.model.entity.PostalItemHistory;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Представление Почтового отправления
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostalItemDTO {

    @NotBlank(message = "У Почтового отправления должен быть идентификатор!")
    String identifier;

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
