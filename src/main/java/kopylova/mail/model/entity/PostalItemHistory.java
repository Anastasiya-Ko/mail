package kopylova.mail.model.entity;


import jakarta.persistence.*;
import kopylova.mail.model.dictionary.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

/**
 * Сущность<p>
 * История почтового отправления
 */

@Data
@Entity(name = "postal_item_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostalItemHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Связь с таблицей Почтовое отправление<p>
     * Много смен статуса в истории - у одного Почтового отправления
     */
    @ManyToOne
    @JoinColumn(name = "postal_item_id")
    PostalItem postalItemOwner;

    /**
     * Статус Почтового отправления
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;



    /**
     * Связь с таблицей Почтовое отделение<p>
     * У многих почтовых отделений - много смен статуса
     */
    @ManyToMany
    @JoinTable(
            name = "postal_item_history_post_office",
            joinColumns = @JoinColumn(name = "postal_item_history_id"),
            inverseJoinColumns = @JoinColumn(name = "post_office_id")
    )
    List<PostOffice> offices;

}
