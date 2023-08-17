package kopylova.mail.model.entity;


import jakarta.persistence.*;
import kopylova.mail.model.dictionary.Status;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

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
     * Дата создания статуса
     */
    @Column(name = "create_status")
    LocalDate createStatus;


}
