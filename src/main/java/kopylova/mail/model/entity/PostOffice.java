package kopylova.mail.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.awt.print.Book;
import java.util.List;

/**
 * Сущность<p>
 * Почтовое отделение
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
     * Индекс Почтового отделения
     */
    @Column(name = "office_index")
    Integer officeIndex;

    /**
     * Наименование Почтового отделения
     */
    @Column(name = "office_name")
    String officeName;

    /**
     * Адрес Почтового отделения
     */
    @Column(name = "office_address")
    String officeAddress;

    @ManyToMany(mappedBy = "offices")
    List<PostalItemHistory> histories;

}
