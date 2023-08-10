/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PhamQuang
 */
public class Cart {

    String id;
    String productId;
    int quantity;
    double price;
    String orderId;

    public Cart() {
        connect();
    }

    public Cart(String id, String productId, int quantity, double price, String orderId) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        connect();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public ArrayList<Cart> getListCart() {
        ArrayList<Cart> data = new ArrayList<Cart>();
        try {
            String strSelect = "select * from Cart ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String productId = rs.getString(2);
                String quantityTemp = rs.getString(3);
                int quantity = Integer.parseInt(quantityTemp);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String orderId = rs.getString(5);
                data.add(new Cart(id, productId, quantity, price, orderId));
            }
        } catch (Exception e) {
            System.out.println("getListCart:" + e.getMessage());
        }
        return data;
    }

    public ArrayList<Cart> getListCartByOrderID(String OrderID) {
        ArrayList<Cart> data = new ArrayList<Cart>();
        try {
            String strSelect = "SELECT * FROM shoponline.cart where OrderID = '" + OrderID + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String productId = rs.getString(2);
                String quantityTemp = rs.getString(3);
                int quantity = Integer.parseInt(quantityTemp);
                String priceTemp = rs.getString(4);
                double price = Double.parseDouble(priceTemp);
                String orderId = rs.getString(5);
                data.add(new Cart(id, productId, quantity, price, orderId));
            }
        } catch (Exception e) {
            System.out.println("getListCart:" + e.getMessage());
        }
        return data;
    }

    public Cart getCartByProductID(String productID, String OrderID) {
        try {
            String strSelect = "SELECT * FROM shoponline.cart where OrderID = '" + OrderID + "' and ProductID = '" + productID + "';";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String productIDtemp = rs.getString(2);
                int quantity = Integer.parseInt(rs.getString(3));
                double Price = Double.parseDouble(rs.getString(4));
                String OrderIDtemp = rs.getString(5);
                Cart c = new Cart(id, productIDtemp, quantity, Price, OrderIDtemp);
                return c;
            }
        } catch (Exception e) {
            System.out.println("getCartByID:" + e.getMessage());
        }
        return null;
    }

    public ArrayList<Cart> getCartListByProductID(String productID) {
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        try {
            
            String strSelect = "SELECT * FROM shoponline.cart where ProductID like '%" + productID + "%';";
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String productIDtemp = rs.getString(2);
                int quantity = Integer.parseInt(rs.getString(3));
                double Price = Double.parseDouble(rs.getString(4));
                String OrderIDtemp = rs.getString(5);
                Cart c = new Cart(id, productIDtemp, quantity, Price, OrderIDtemp);
                cartList.add(c);
            }
        } catch (Exception e) {
            System.out.println("getCartListByProductID:" + e.getMessage());
        }
        return cartList;
    }

    public void add() {
        try {
            String strUpdate = "INSERT INTO cart(ID,ProductID,Quantity,Price,OrderID)\n"
                    + "VALUES (?,?,?,?,?);";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, id.toString().trim());
            pstm.setString(2, productId.toString().trim());
            pstm.setString(3, Integer.toString(quantity));
            pstm.setString(4, Double.toString(price));
            pstm.setString(5, orderId.toString().trim());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("AddCart:" + e.getMessage());
        }
    }

    public void remove(String id) {
        try {
            String strUpdate = "DELETE FROM Cart where ID = '" + id + "'";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RemoveCart:" + e.getMessage());
        }

    }

    public void removeFromCart() {
        //get quantity from cart
        int cartQuantity = this.quantity;
        // + get current product quantity
        Product p = new Product();
        p = p.getProductByID(this.productId);
        int currentQuantity = p.getQuantity();
        // + re add item into product
        int reAddQuantity = cartQuantity + currentQuantity;
        p.updateQuantity(reAddQuantity);
        //remove item from cart
        remove(this.id);
    }

    public void deleteData() {
        try {
            String strUpdate = "delete from cart where cart.ID in (select ID from (select cart.ID from cart where cart.ID not in (select cart.ID from cart inner join orders where cart.OrderID = orders.OrderID))tblTmp)";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

    public ArrayList<Cart> selectSpareData() {
        ArrayList<Cart> cartList = new ArrayList<Cart>();
        try {
            String strUpdate = "select * from cart \n"
                    + "where cart.ID not in \n"
                    + "(select cart.ID from cart\n"
                    + " inner join orders where cart.OrderID = orders.OrderID)";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strUpdate);
            while (rs.next()) {
                String id = rs.getString(1);
                String productIDtemp = rs.getString(2);
                int quantity = Integer.parseInt(rs.getString(3));
                double Price = Double.parseDouble(rs.getString(4));
                String OrderIDtemp = rs.getString(5);
                Cart c = new Cart(id, productIDtemp, quantity, Price, OrderIDtemp);
                cartList.add(c);
            }
        } catch (Exception e) {
            System.out.println("SelectSpareData:" + e.getMessage());
        }
        return cartList;
    }

    public void sqlUnsafe() {
        try {
            String strUpdate = "SET SQL_SAFE_UPDATES = 0";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

    public void sqlSafe() {
        try {
            String strUpdate = "SET SQL_SAFE_UPDATES = 1";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

    public void updateQuantity(int newQuantity, double currentPrice) {
        double newPrice = newQuantity * currentPrice;
        this.updatePrice(newPrice);
        try {
            String strUpdate = "UPDATE `shoponline`.`cart` SET `Quantity` = '" + newQuantity + "' WHERE (`ID` = '" + id + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

    public void updatePrice(double newPrice) {
        try {
            String strUpdate = "UPDATE `shoponline`.`cart` SET `Price` = '" + newPrice + "' WHERE (`ID` = '" + id + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Update price: " + e.getMessage());
        }
    }

    public void refresh() {
        //Disable safe storage
        this.sqlUnsafe();

        //get spare quantity from unbought cart
        Cart c = new Cart();
        ArrayList<Cart> spareCart = new ArrayList<Cart>();
        spareCart = c.selectSpareData();

        for (int i = 0; i < spareCart.size(); i++) {//for each unbought item in the cart

            //get spare quantity
            int spareQuantity = 0;
            Cart tempC = spareCart.get(i);
            spareQuantity = tempC.getQuantity();

            //get current quantity
            Product p = new Product();
            p = p.getProductByID(tempC.getProductId());
            int currentQuantity = p.getQuantity();

            //put the unbought items back into the store
            int trueQuantity = spareQuantity + currentQuantity;

            //update item on the database
            p.updateQuantity(trueQuantity);

        }

        //Delete spare data
        this.deleteData();

        //Re-enable safe storage
        this.sqlSafe();
    }

    public void updateCartQuantity(int newQuantity) {
        try {
            String strUpdate = "UPDATE `shoponline`.`cart` SET `Quantity` = '" + newQuantity + "' WHERE (`ID` = '" + id + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

    public void updateCartPrice(Double newPrice) {
        try {
            String strUpdate = "UPDATE `shoponline`.`cart` SET `Price` = '" + newPrice + "' WHERE (`ID` = '" + id + "');";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("RefreshCart:" + e.getMessage());
        }
    }

}
