package kopylova.mail.model.entity;

import jakarta.persistence.*;
import kopylova.mail.model.dictionary.Type;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * Сущность<p>
 * Почтовое отправление
 */

@Data
@Entity(name = "postal_item")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostalItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    /**
     * Тип отправления
     */
    @Column(name = "type_item")
    @Enumerated(EnumType.STRING)
    Type type;

    /**
     * Индекс получателя
     */
    @Column(name = "recipient_index")
    Integer recipientIndex;

    /**
     * Адрес получателя
     */
    @Column(name = "recipient_address")
    String recipientAddress;

    /**
     * Имя получателя
     */
    @Column(name = "receiver_name")
    String receiverName;

    /**
     * Связь с таблицей История Почтового отправления<p>
     * У одного Почтового отправления - много смен статуса
     */
    @OneToMany(mappedBy = "postalItemOwner")
    List<PostalItemHistory> postalItemHistories;


}
