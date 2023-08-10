/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamQuang
 */
public class Orders {

    String id;
    String status;
    String date;
    String usersId;
    String orderId;

    public Orders() {
        connect();
    }

    public Orders(String id, String status, String date, String usersId, String orderId) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.usersId = usersId;
        this.orderId = orderId;
        connect();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsersId() {
        return usersId;
    }

    public void setUsersId(String usersId) {
        this.usersId = usersId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    //Khai bao cac thanh phan xu ly DB
    Connection cnn; //ket noi
    Statement stm;//Thuc thi cac cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu

    private void connect() {
        try {
            cnn = new DBContext().connection;
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect failed");
            }
        } catch (Exception e) {
        }
    }

    public void add() {
        try {
            String strUpdate = "INSERT INTO Orders(ID,Status,Date,UsersID,OrderID)\n"
                    + "VALUES (?,?,?,?,?);";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, id.toString().trim());
            pstm.setString(2, status.toString().trim());
            pstm.setString(3, date.toString().trim());
            pstm.setString(4, usersId.toString().trim());
            pstm.setString(5, orderId.toString().trim());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("AddOrder:" + e.getMessage());
        }
    }

    public ArrayList<Orders> getListOrder() {
        ArrayList<Orders> data = new ArrayList<Orders>();
        try {
            String strSelect = "SELECT * FROM shoponline.Orders";
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String status = rs.getString(2);
                String date = rs.getString(3);
                String usersid = rs.getString(4);
                String orderid = rs.getString(5);
                data.add(new Orders(id, status, date, usersid, orderid));
            }
        } catch (Exception e) {
            System.out.println("getListOrder: " + e.getMessage());
        }
        return data;
    }

    public ArrayList<Orders> getListOrderByStatus(String orderStatus) {
        ArrayList<Orders> data = new ArrayList<Orders>();
        try {
            String strSelect = "SELECT * FROM shoponline.Orders where Status = '" + orderStatus + "';";
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String status = rs.getString(2);
                String date = rs.getString(3);
                String usersid = rs.getString(4);
                String orderid = rs.getString(5);
                data.add(new Orders(id, status, date, usersid, orderid));
            }
        } catch (Exception e) {
            System.out.println("getListOrder: " + e.getMessage());
        }
        return data;
    }

    public ArrayList<Orders> getListOrderByUserID(String userID) {
        ArrayList<Orders> data = new ArrayList<Orders>();
        try {
            String strSelect = "SELECT * FROM shoponline.Orders where UsersID like '" + userID + "%' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String status = rs.getString(2);
                String date = rs.getString(3);
                String usersid = rs.getString(4);
                String orderid = rs.getString(5);
                data.add(new Orders(id, status, date, usersid, orderid));
            }
        } catch (Exception e) {
            System.out.println("getListOrderByUserId:" + e.getMessage());
        }
        return data;
    }

    public Orders getOrderByOrderID(String OrderID) {
        try {
            String strSelect = "SELECT * FROM shoponline.orders where OrderID = '" + OrderID + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String status = rs.getString(2);
                String date = rs.getString(3);
                String usersid = rs.getString(4);
                String orderid = rs.getString(5);
                Orders o = new Orders(id, status, date, usersid, orderid);
                return o;
            }
        } catch (Exception e) {
            System.out.println("getOrderByOrderID:" + e.getMessage());
        }
        return null;
    }

    public Orders getOrderByID(String ID) {
        try {
            String strSelect = "SELECT * FROM shoponline.orders where ID = '" + ID + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String status = rs.getString(2);
                String date = rs.getString(3);
                String usersid = rs.getString(4);
                String orderid = rs.getString(5);
                Orders o = new Orders(id, status, date, usersid, orderid);
                return o;
            }
        } catch (Exception e) {
            System.out.println("getOrderByID:" + e.getMessage());
        }
        return null;
    }

    public void updateStatus(String newStatus) {
        try {
            String strUpdate = "UPDATE `shoponline`.`orders` SET `Status` = '" + newStatus + "' WHERE (`ID` = '" + id + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateStatus: " + e.getMessage());
        }
    }

}
