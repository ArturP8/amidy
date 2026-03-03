import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    void passwordTooShort_shouldReturnFalse() {
        UserService service = new UserService();
        assertFalse(service.isPasswordValid("123"));
    }

    @Test
    void passwordLongEnough_shouldReturnTrue() {
        UserService service = new UserService();
        assertTrue(service.isPasswordValid("12345678"));
    }
}
