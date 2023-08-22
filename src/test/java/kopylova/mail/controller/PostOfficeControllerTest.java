package kopylova.mail.controller;

import kopylova.mail.model.view.PostOfficeDTO;
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
class PostOfficeControllerTest {

    @Autowired
    PostOfficeController controller;
    @Test
    void createOffice() {

        PostOfficeDTO view = new PostOfficeDTO();

        view.setOfficeIndex(123456);
        view.setOfficeName("test");
        view.setOfficeAddress("test");

        PostOfficeDTO createDto = controller.createOffice(view);

        assertEquals(123456, createDto.getOfficeIndex());
    }
}