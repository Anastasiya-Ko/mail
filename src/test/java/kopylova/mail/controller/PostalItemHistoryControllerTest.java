package kopylova.mail.controller;

import kopylova.mail.model.view.PostalItemHistoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
class PostalItemHistoryControllerTest {

    @Autowired
    PostalItemHistoryController controller;

    @Test
    void testRegistrationPostalItem() {

        Long postalItemId = 16L;
        Long officeId = 2L;

        PostalItemHistoryDTO createDto = controller.registrationPostalItem(postalItemId, officeId);

        assertEquals(createDto.getPostalItemOwner().getId(), postalItemId);
    }
    @Test
    void testArrivalPostalItem() {

        Long postalItemId = 16L;
        Long officeId = 2L;

        PostalItemHistoryDTO createDto = controller.arrivalPostalItem(postalItemId, officeId);

        assertEquals(createDto.getPostalItemOwner().getId(), postalItemId);
    }

    @Test
    void testDeparturePostalItem() {

        Long postalItemId = 16L;

        PostalItemHistoryDTO createDto = controller.departurePostalItem(postalItemId);

        assertEquals(createDto.getPostalItemOwner().getId(), postalItemId);
    }

    @Test
    void testReceivedPostalItem() {

        Long postalItemId = 16L;

        PostalItemHistoryDTO createDto = controller.receivedPostalItem(postalItemId);

        assertEquals(createDto.getPostalItemOwner().getId(), postalItemId);
    }

    @Test
    void testReadAllPostalItemHistory() {

        Long postalItemId = 16L;

        var createDto = controller.readAllPostalItemHistory(postalItemId);

        assertFalse(createDto.isEmpty());
    }

    @Test
    void testReadLastStatusPostalItem() {

        String status = "Почтовое отправление зарегистрировано";

        Long postalItemId = 16L;

        var lastStatusPostalItem = controller.readLastStatusPostalItem(postalItemId);

        assertEquals(status, lastStatusPostalItem);

    }
}