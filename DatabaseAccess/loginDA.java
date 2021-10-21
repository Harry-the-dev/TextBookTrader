package TBEStructure.DatabaseAccess;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import TBEStructure.helper.DBconnection;
import TBEStructure.model.User;

public class loginDA {

    public User AuthenticateUser(String email, String password) {
        try {

            Connection con = new DBconnection().getNewConnection();
            String query = "SELECT * from Users where Email = ? and Password = ?;";
            PreparedStatement prep = con.prepareStatement(query);
            prep.setString(1, email);
            prep.setString(2, password);
            ResultSet resultSet = prep.executeQuery();
            resultSet.next();
            System.out.println("email: " + resultSet.getString(2) + "password : " + resultSet.getString(3));

            if (resultSet.getString("Email").equalsIgnoreCase(email)
                    && resultSet.getString("Password").equalsIgnoreCase(password)) {
                User user = new User();

                user.setUserId(resultSet.getInt("UserId"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("Password"));
                user.setName(resultSet.getString("Name"));
                user.setSurname(resultSet.getString("Surname"));
                user.setPhoneNumber(resultSet.getString("PhoneNumber"));
                con.close();
                return user;
            }

        } catch (Exception e) {
            System.err.println("AuthenticateUser error :" + e.getMessage());
        }
        return null;
    }

    public void AddCustomer(String name, String surname, String phonenumber, String password, String email) {

        Connection con = new DBconnection().getNewConnection();
        String query = "INSERT INTO Users (Email, Password, Name, Surname, PhoneNumber) VALUES (?,?,?,?,?);";
        
        try {
            PreparedStatement prep = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            prep.setString(1, email);
            prep.setString(2, password);
            prep.setString(3, name);
            prep.setString(4, surname);
            prep.setString(5, phonenumber);
            prep.executeUpdate();
          

        } catch (SQLException a) {
            System.out.println("Error Creating: " + a.getMessage());
        }

        try {
            con.close();
            con = null;
        } catch (SQLException b) {
            System.out.println("Error closing connection: " + b);
        }

        
    }

}
