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
public enum Status {

    REGISTRATION("Почтовое отправление зарегистрировано"),
    ARRIVAL("Прибытие Почтового отправления в промежуточное Почтовое отделение"),
    DEPARTURE("Убытие Почтового отправления из Почтового отделения"),
    RECEIVED("Почтовое отправление получено адресатом");

    String descriptions;

}

