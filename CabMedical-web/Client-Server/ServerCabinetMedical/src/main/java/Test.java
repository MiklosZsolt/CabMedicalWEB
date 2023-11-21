import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/cabinetmedical";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexiunea la baza de date a fost realizată cu succes.");
        } catch (SQLException e) {
            System.out.println("Nu s-a putut realiza conexiunea la baza de date: " + e.getMessage());
        }
    }
}
