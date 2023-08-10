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
public class Product {

    String id;
    String name;
    String image;
    Double price;
    String details;
    int quantity;

    public Product() {
        connect();
    }

    public Product(String id, String name, String image, Double price, String details, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.details = details;
        this.quantity = quantity;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public ArrayList<Product> getListProductByType(String type) {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "SELECT * FROM shoponline.product where id like '" + type + "%' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                String id = rs.getString(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String details = rs.getString(5);
                String quantityTemp = rs.getString(6);
                int quantity = Integer.parseInt(quantityTemp);
                data.add(new Product(id, name, image, price, details, quantity));
            }
        } catch (Exception e) {
            System.out.println("getListProductByType:" + e.getMessage());
        }
        return data;
    }

    public Product getListProductByName(String name) {
        Product data = new Product();
        try {
            String strSelect = "SELECT * FROM shoponline.product where name = '" + name + "' ";
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                String id = rs.getString(1);
                String nametemp = rs.getString(2);
                String image = rs.getString(3);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String details = rs.getString(5);
                String quantityTemp = rs.getString(6);
                int quantity = Integer.parseInt(quantityTemp);
                data = new Product(id, nametemp, image, price, details, quantity);
            }
        } catch (Exception e) {
            System.out.println("getListProductByName:" + e.getMessage());
        }
        return data;
    }

    public Product getProductByID(String id) {
        Product data = new Product();
        try {
            String strSelect = "SELECT * FROM shoponline.product where ID = '" + id + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String idTemp = rs.getString(1);
                String nametemp = rs.getString(2);
                String image = rs.getString(3);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String details = rs.getString(5);
                String quantityTemp = rs.getString(6);
                int quantity = Integer.parseInt(quantityTemp);
                data = new Product(idTemp, nametemp, image, price, details, quantity);
            }
        } catch (Exception e) {
            System.out.println("getListProductByName:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "SELECT * FROM shoponline.product";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String details = rs.getString(5);
                String quantityTemp = rs.getString(6);
                int quantity = Integer.parseInt(quantityTemp);
                data.add(new Product(id, name, image, price, details, quantity));
            }
        } catch (Exception e) {
            System.out.println("getListProduct:" + e.getMessage());
        }
        return data;
    }
    
        public ArrayList<Product> getListProductBySearch(String productSearch) {
        ArrayList<Product> data = new ArrayList<Product>();
        try {
            String strSelect = "SELECT * FROM shoponline.product where name like '%"+ productSearch+"%' or id like '%"+ productSearch+"%';";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String image = rs.getString(3);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String details = rs.getString(5);
                String quantityTemp = rs.getString(6);
                int quantity = Integer.parseInt(quantityTemp);
                data.add(new Product(id, name, image, price, details, quantity));
            }
        } catch (Exception e) {
            System.out.println("getListProductBySearch:" + e.getMessage());
        }
        return data;
    }
    
    public void add() {
        try {
            String strUpdate = "INSERT INTO Product(ID,Name,Image,Price,Details,Quantity)\n"
                    + "VALUES (?,?,?,?,?,?);";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, id.toString().trim());
            pstm.setString(2, name.toString().trim());
            pstm.setString(3, image.toString().trim());
            pstm.setString(4, price.toString().trim());
            pstm.setString(5, details.toString());
            pstm.setString(6, Integer.toString(quantity));
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("AddUsers:" + e.getMessage());
        }
    }

    public void updateQuantity(int newQuantity) {
        try {
            String strUpdate = "UPDATE `shoponline`.`product` SET `Quantity` = '" + newQuantity + "' WHERE (`ID` = '" + id + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

    public void updateData(Product updatedProduct, String updateId) {
        try {
            String strUpdate = "UPDATE `shoponline`.`product` SET `Name` = '"+updatedProduct.name+"', `Image` = '"+updatedProduct.image+"', `Price` = '"+updatedProduct.price+"', `Details` = '"+updatedProduct.details+"', `Quantity` = '"+updatedProduct.quantity+"' WHERE (`ID` = '"+updateId+"');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateData: " + e.getMessage());
        }
    }

}
