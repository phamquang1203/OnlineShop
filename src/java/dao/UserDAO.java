/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.DBContext;
import Model.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DW
 */
public class UserDAO {
    //Khai bao cac thanh phan xu ly DB

    Connection cnn = null;
    Statement stm;//Thuc thi cac cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu

    public Users getUsersByID(String userId) throws SQLException {
        cnn = new DBContext().connection;
        try {
            String sql = "select * from Users u "
                    + " WHERE u.ID = ? ";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, userId);
            rs = pstm.executeQuery();
            if (rs.next()) {
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
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return null;
    }

    public Users getUsersByEmail(String email) throws SQLException {
        cnn = new DBContext().connection;
        try {
            String sql = "select * from Users u "
                    + " WHERE u.Email = ? ";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String password = rs.getString(3);
                String avatar = rs.getString(4);
                String gender = rs.getString(5);
                String e = rs.getString(6);
                String mobile = rs.getString(7);
                String address = rs.getString(8);
                int typeId = rs.getInt(9);
                Users a = new Users(id, name, password, avatar, gender, e, mobile, address, typeId);
                return a;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return null;
    }

//    UPDATE OnlineShop.Users SET Name='', Password='', Avatar='', Gender='', Email='', Mobile='', Address='', TypeID=0 WHERE ID='';
    public boolean changePassword(String newPass, String userId) throws SQLException {
        cnn = new DBContext().connection;
        try {
            String sql = "UPDATE Users SET Password=? WHERE ID= ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, newPass);
            pstm.setString(2, userId);
            System.out.println(sql);
            if (pstm.executeUpdate() > 0) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return false;
    }
}
