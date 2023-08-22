package kopylova.mail.model.dictionary;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;


@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Schema(description = "Перечисление типов отправления")
public enum Type {

    LETTER("Письмо"),
    PARCEL("Посылка"),
    PACKAGE("Бандероль"),
    POSTCARD("Открытка");

    String descriptions;
}
