package TBEStructure.DatabaseAccess;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import TBEStructure.model.User;
import TBEStructure.helper.DBconnection;

import TBEStructure.model.OrderDetail;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartDA {

    private Connection con;
    private ObservableList<CartTable> allCartList;
    public class CartTable {
        public SimpleStringProperty Id = new SimpleStringProperty();
        public SimpleStringProperty Title = new SimpleStringProperty();
        public SimpleStringProperty ModuleCode = new SimpleStringProperty();
        public SimpleStringProperty Price = new SimpleStringProperty();

        public String getPrice() {
            return Price.get();
        }

        public String getId() {
            return Id.get();
        }

        public String getModuleCode() {
            return ModuleCode.get();
        }

        public String getTitle() {
            return Title.get();
        }

    }
    // ========================================================================================================

    public void addCartItem(OrderDetail orderDetail) {

        boolean exists = false;
        con = new DBconnection().getNewConnection();

        String selectQuery = "Select * from OrderDetail where OrderID=? and BookID=?;";
        try {
            PreparedStatement selectStmt = con.prepareStatement(selectQuery);
            selectStmt.setInt(1, orderDetail.getOrderID());
            selectStmt.setInt(2, orderDetail.getBook().getBookId());
            ResultSet selectRS = selectStmt.executeQuery();

            if (selectRS.next()) {
                if (selectRS.getInt("OrderID") == orderDetail.getOrderID()
                        && selectRS.getInt("BookID") == orderDetail.getBook().getBookId()) {
                    exists = true;
                }
            }
            selectRS.close();

        } catch (Exception e) {
            System.out.println("Error " + this.toString() + e);
        }

        if (!exists) {
            String query = "INSERT INTO OrderDetail (OrderID,BookID) VALUES ( ?, ?) ;";

            try (PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, orderDetail.getOrderID());
                statement.setInt(2, orderDetail.getBook().getBookId());
                statement.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Error " + this.toString() + e);
            }
        }
        try {
            con.close();
            con = null;
        } catch (SQLException e) {
            System.out.println("Error " + this.toString() + e);
        }
    }

    // ========================================================================================================

    public boolean removeFromCart(OrderDetail orderDetail) {

        try {
            con = new DBconnection().getNewConnection();
            String query = "delete from OrderDetail where BookId=? and OrderId=?;";

            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orderDetail.getBook().getBookId());
            statement.setInt(2, orderDetail.getOrderID());
            statement.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error " + this.toString() + e);
            return false;
        }
        try {
            con.close();
            con = null;
        } catch (SQLException e) {
            System.out.println("Error " + this.toString() + e);
            return false;
        }
        return true;
    }

    // ========================================================================================================

   

    public ObservableList<CartTable> getAllCartItems(User user) {

        allCartList = FXCollections.observableArrayList();
        try {
            con = new DBconnection().getNewConnection();

            // query to select the cart items
            String query = "Select * from OrderDetail where OrderID=?;";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, user.getUserId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                String query2 = "Select * from Book where BookId=?;";

                PreparedStatement statement2 = con.prepareStatement(query2);

                statement2.setInt(1, resultSet.getInt("BookId"));
                ResultSet resultSet2 = statement2.executeQuery();

                resultSet2.next();

                CartTable cartTable = new CartTable();
                cartTable.Id.set(String.valueOf(resultSet2.getInt("BookId")));
                cartTable.Title.set(String.valueOf(resultSet2.getString("Title")));
                cartTable.Price.set(String.valueOf(resultSet2.getDouble("SalePrice")));
                cartTable.ModuleCode.set(String.valueOf(resultSet2.getString("ModuleCode")));
              

                allCartList.add(cartTable);
            }

            resultSet.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error " + this.toString() + e);
        }
        return allCartList;
    }

}
