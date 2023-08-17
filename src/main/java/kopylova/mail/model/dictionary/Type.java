package kopylova.mail.model.dictionary;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Перечисление типов отправления
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Type {

    LETTER("Письмо"),
    PARCEL("Посылка"),
    PACKAGE("Бандероль"),
    POSTCARD("Открытка");

    String descriptions;
}
