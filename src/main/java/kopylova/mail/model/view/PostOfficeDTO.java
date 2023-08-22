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
@Schema(description = "Представление Почтового отделения")
public class PostOfficeDTO {

    @Schema(description = "Идентификатор почтового отделения", accessMode = Schema.AccessMode.READ_ONLY)
    Long id;

    @NotNull(message = "У Почтового отделения должен быть индекс")
    @Positive(message = "Индекс должен содержать только положительные числа")
    @Schema(description = "Индекс почтового отделения", example = "625012")
    Integer officeIndex;

    @NotNull(message = "У Почтового отделения должно быть название")
    @Schema(description = "Название почтового отделения", example = "Почтовое отделение")
    String officeName;

    @NotBlank(message = "У Почтового отделения должен быть адрес")
    @Schema(description = "Адрес почтового отделения", example = "г.Город, ул. Улица, д.1")
    String officeAddress;
}
