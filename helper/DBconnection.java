package TBEStructure.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBconnection {

    String Query;
    String Url;
    String Username;
    String Password;

    public DBconnection() {
        Url = "jdbc:mysql://localhost:3306/TextBookExchange?useSSL=false";
        Username = "root";
        Password = "hgod2905";
    }

    public Connection getNewConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(Url, Username, Password);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e);
            return null;
        }
        return connection;
    }

}
