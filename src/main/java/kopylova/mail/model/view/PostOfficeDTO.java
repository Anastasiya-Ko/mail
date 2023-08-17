package kopylova.mail.model.view;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Представление Почтового отделения
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostOfficeDTO {

    @NotNull(message = "У Почтового отделения должен быть индекс")
    @Positive(message = "Индекс должен содержать только положительные числа")
    Integer officeIndex;

    @NotBlank(message = "У Почтового отделения должно быть название")
    String officeName;

    @NotBlank(message = "У Почтового отделения должен быть адрес")
    String officeAddress;
}
