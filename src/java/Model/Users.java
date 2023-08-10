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
public class Users {

    String id, name, password, avatar, gender, email, mobile, address;
    int typeId;

    public Users() {
        connect();
    }

    public Users(String id, String password) {
        this.id = id;
        this.password = password;
        connect();
    }

    public Users(String id, String name, String password, String avatar, String gender, String email, String mobile, String address, int typeId) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.typeId = typeId;
        connect();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassowrd() {
        return password;
    }

    public void setPassowrd(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    public String getNameByID() {
        try {
            String strSelect = "select * from Users "
                    + "where ID= '" + id + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                return id;
            }
        } catch (Exception e) {
            System.out.println("getNameByID:" + e.getMessage());
        }finally{
            if (cnn != null){
                try {
                    cnn.close();
                    System.out.println("Connection closed !");
                } catch (SQLException ex) {
                    Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "";
    }

    public Users getUsersByID() {
        try {
            String strSelect = "select * from Users "
                    + "where ID= '" + id + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String email = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(id, name, password, avatar, gender, email, mobile, address, typeId);
                return a;
            }
        } catch (Exception e) {
            System.out.println("getUsersByID:" + e.getMessage());
        }finally{
            if (cnn != null){
                try {
                    cnn.close();
                    System.out.println("Connection closed !");
                } catch (SQLException ex) {
                    Logger.getLogger(Orders.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public boolean checkUsersByID(String id) {
        try {
            String strSelect = "select * from Users "
                    + "where ID= '" + id + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String ids = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String email = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(ids, name, password, avatar, gender, email, mobile, address, typeId);
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUsersByID:" + e.getMessage());
        }
        return false;
    }

    public Users getUsersByID(String id) {
        try {
            String strSelect = "select * from Users "
                    + "where ID= '" + id + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String ids = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String email = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(ids, name, password, avatar, gender, email, mobile, address, typeId);
                return a;
            }
        } catch (Exception e) {
            System.out.println("checkUsersByID:" + e.getMessage());
        }
        return null;
    }

    public boolean checkUsersByEmail(String email) {
        try {
            String strSelect = "select * from Users "
                    + "where Email= '" + email + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String ids = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String emails = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(ids, name, password, avatar, gender, emails, mobile, address, typeId);
                return true;
            }
        } catch (Exception e) {
            System.out.println("getUsersByID:" + e.getMessage());
        }
        return false;
    }

    public Users getUsersByEmail(String email) {
        try {
            String strSelect = "select * from Users "
                    + "where Email= '" + email + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String ids = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String emails = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(ids, name, password, avatar, gender, emails, mobile, address, typeId);
                return a;
            }
        } catch (Exception e) {
            System.out.println("getUsersByID:" + e.getMessage());
        }
        return null;
    }

    public boolean checkUsersByMobile(String mobile) {
        try {
            String strSelect = "select * from Users "
                    + "where Mobile= '" + mobile + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String ids = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String emails = rs.getString(6);
                String mobiles = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(ids, name, password, avatar, gender, emails, mobiles, address, typeId);
                return true;
            }
        } catch (Exception e) {
            System.out.println("getUsersByID:" + e.getMessage());
        }
        return false;
    }

    public ArrayList<Users> getListUsers() {
        ArrayList<Users> data = new ArrayList<Users>();
        try {
            String strSelect = "select * from Users ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String email = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                data.add(new Users(id, name, password, avatar, gender, email, mobile, address, typeId));
            }
        } catch (Exception e) {
            System.out.println("getListUsers:" + e.getMessage());
        }
        return data;
    }

    public void add() {
        try {
            String strUpdate = "INSERT INTO Users(ID,Name,Password,Avatar,Gender,Email,Mobile,Address,TypeID)\n"
                    + "VALUES (?,?,?,?,?,?,?,?,?);";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, id.toString().trim());
            pstm.setString(2, name.toString().trim());
            pstm.setString(3, password.toString().trim());
            pstm.setString(4, avatar.toString().trim());
            pstm.setString(5, gender.toString().trim());
            pstm.setString(6, email.toString().trim());
            pstm.setString(7, mobile.toString().trim());
            pstm.setString(8, address.toString().trim());
            pstm.setInt(9, typeId);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("AddUsers:" + e.getMessage());
        }
    }

    public boolean checkUser() {
        try {
            String strSelect = "select * from Users "
                    + "where ID=? "
                    + "and Password=? ";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkAccount:" + e.getMessage());
        }
        return false;
    }

    public void updateAccountType(int typeId, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `TypeID` = " + typeId + " WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountType: " + e.getMessage());
        }
    }

    public void updateAccountName(String name, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `Name` = '" + name + "' WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountName: " + e.getMessage());
        }
    }
    public void updateAccountGender(String gender, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `Gender` = '" + gender + "' WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountGender: " + e.getMessage());
        }
    }
    public void updateAccountEmail(String email, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `Email` = '" + email + "' WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountEmail: " + e.getMessage());
        }
    }
    public void updateAccountMobile(String mobile, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `Mobile` = '" + mobile + "' WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountMobile: " + e.getMessage());
        }
    }
    public void updateAccountAddress(String address, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `Address` = '" + address + "' WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountAddress: " + e.getMessage());
        }
    }
    public void updateAccountImage(String image, String accountId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`users` SET `Avatar` = '" + image + "' WHERE (`ID` = '" + accountId + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccountAddress: " + e.getMessage());
        }
    }
}
