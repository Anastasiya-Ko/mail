package kopylova.mail.repository;

import kopylova.mail.model.entity.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий <p>
 * Почтовый офис
 */
public interface PostOfficeRepository extends JpaRepository<PostOffice, Long> {
}
