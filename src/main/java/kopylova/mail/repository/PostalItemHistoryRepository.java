package kopylova.mail.repository;

import kopylova.mail.model.dictionary.Status;
import kopylova.mail.model.entity.PostalItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Репозиторий для работы с таблицей Истории движения почтового отправления
 */
public interface PostalItemHistoryRepository extends JpaRepository<PostalItemHistory, Long> {

    List<PostalItemHistory> findAllByPostalItemOwnerId(Long postalItemId);
}
