import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    UserRepository repository;

    UserService service;
    UserController controller;

    @BeforeEach
    void setup() {
        service = new UserService();
        controller = new UserController(repository, service);
    }

    @Test
    void callRepositorySave() {
        controller.registerUser("Adam", "haslo1234");

        verify(repository).saveUser("Adam", "haslo1234");
    }

    @Test
    void handleDatabaseError() {
        doThrow(new RuntimeException("Brak DB"))
                .when(repository)
                .saveUser(anyString(), anyString());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                controller.registerUser("Adam", "haslo1234")
        );

        assertTrue(exception.getMessage().contains("Brak DB"));
    }
}
