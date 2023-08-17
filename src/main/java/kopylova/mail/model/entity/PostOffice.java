package kopylova.mail.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Сущность<p>
 * Почтовый офис
 */

@Data
@Entity(name = "post_office")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Индекс почтового отделения
     */
    @Column(name = "office_index")
    Integer officeIndex;

    /**
     * Наименование почтового отделения
     */
    @Column(name = "office_name")
    String officeName;

    /**
     * Адрес почтового отделения
     */
    @Column(name = "office_address")
    String officeAddress;

}
