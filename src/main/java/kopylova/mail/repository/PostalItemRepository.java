package kopylova.mail.repository;

import kopylova.mail.model.entity.PostalItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий <p>
 * Почтовое отправление
 */
public interface PostalItemRepository extends JpaRepository<PostalItem, Long>{
}
