import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class UserService {
    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 8;
    }
}

interface UserRepository {
    void saveUser(String username, String password);
}

class UserRepositoryImpl implements UserRepository {

    private static final String URL =
            "jdbc:mysql://localhost:3306/Testowanie?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    @Override
    public void saveUser(String username, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "INSERT INTO users(username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Błąd połączenia z bazą danych", e);
        }
    }
}

class UserController {
    private final UserRepository repository;
    private final UserService service;

    public UserController(UserRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }

    public void registerUser(String username, String password) {
        if (!service.isPasswordValid(password)) {
            throw new IllegalArgumentException("Hasło za krótkie");
        }
        repository.saveUser(username, password);
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepository repo = new UserRepositoryImpl();
        UserService service = new UserService();
        UserController controller = new UserController(repo, service);

        controller.registerUser("Adam", "Haslo123");
        System.out.println("Użytkownik zapisany");
    }
}
