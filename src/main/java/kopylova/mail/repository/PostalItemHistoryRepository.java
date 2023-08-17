package kopylova.mail.repository;

import kopylova.mail.model.entity.PostalItemHistory;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с таблицей Истории движения почтового отправления
 */
public interface PostalItemHistoryRepository extends JpaRepository<PostalItemHistory, Long> {
}
