/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.DBContext;
import Model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DW
 */
public class ProductDAO {
    //Khai bao cac thanh phan xu ly DB

    Connection cnn = null;
    Statement stm;//Thuc thi cac cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu

    public Product getProductById(String productId) throws SQLException {
        cnn = new DBContext().connection;
        try {
            String sql = "select * from product p where p.id = ?";
            pstm = cnn.prepareStatement(sql);
            pstm.setString(1, productId);
            rs = pstm.executeQuery();
            if (rs.next()) {
                Product product = new Product(rs.getString("id"),
                        rs.getString("name"), rs.getString("image"),
                        rs.getDouble("price"), rs.getString("details"),
                        rs.getInt("quantity"));
                return product;
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

    public List<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        cnn = new DBContext().connection;
        try {
            String sql = "select * from product p";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getString("id"),
                        rs.getString("name"), rs.getString("image"),
                        rs.getDouble("price"), rs.getString("details"),
                        rs.getInt("quantity"));
                products.add(product);
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
        return products;
    }

}
