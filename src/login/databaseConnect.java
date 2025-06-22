package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnect {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/atm_machine"; // Your DB
        String user = "root"; 
        String password = "Mogaljan@1"; // Your password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}
