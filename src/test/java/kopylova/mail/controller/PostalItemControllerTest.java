package kopylova.mail.controller;

import kopylova.mail.model.view.PostalItemDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
class PostalItemControllerTest {

    @Autowired
    PostalItemController controller;

    @Test
    void createPostalItem() {

        PostalItemDTO view = new PostalItemDTO();

        view.setReceiverName("test");
        view.setType("LETTER");
        view.setRecipientAddress("test");
        view.setRecipientIndex(107);

        PostalItemDTO createDto = controller.createPostalItem(view);

        assertEquals("LETTER", createDto.getType());
    }
}