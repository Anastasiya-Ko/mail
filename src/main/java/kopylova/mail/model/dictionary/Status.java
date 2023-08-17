package kopylova.mail.model.dictionary;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * Перечисление типов отправления
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Status {

    REGISTRATION("Почтовое отправление зарегистрировано"),
    ARRIVAL("Прибытие Почтового отправления в промежуточное Почтовое отделение"),
    DEPARTURE("Убытие Почтового отправления из Почтового отделения"),
    RECEIVED("Почтовое отправление получено адресатом");

    String descriptions;

}

