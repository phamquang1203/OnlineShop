/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Model.DBContext;
import Model.Feedback;
import Model.Product;
import Model.Users;
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
public class FeedbackDAO extends DBContext{

    Connection cnn = null;
    Statement stm;//Thuc thi cac cau lenh sql
    PreparedStatement pstm;
    ResultSet rs;//Luu tru va xu ly du lieu

    ProductDAO productDAO = new ProductDAO();
    UserDAO userDAO = new UserDAO();

    public List<Feedback> getAllFeedbackByProductId(String productId) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        cnn = new DBContext().connection;
        try {
            String sql = "SELECT RatedStar, Comment, ProductID, UsersID, id FROM OnlineShop.feedback;";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("Comment");
                int ratedStart = rs.getInt("RatedStar");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                Feedback feedback = new Feedback(id, comment, ratedStart, product, users);
                feedbacks.add(feedback);
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
        return feedbacks;
    }
    
    public List<Feedback> getAllFeedbackByProductName(String name) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        cnn = new DBContext().connection;
        try {
            String sql = "SELECT f.* FROM shoponline.feedback f "
                    + "inner join shoponline.product p on f.ProductID=p.ID "
                    + "where shoponline.f.Status=1 AND shoponline.p.Name='"+name+"'";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("Comment");
                int ratedStart = rs.getInt("RatedStar");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                Feedback feedback = new Feedback(id, comment, ratedStart, product, users, status ,image, orderID);
                feedbacks.add(feedback);
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
        return feedbacks;
    }
    
    public List<Feedback> getAllFeedbackByOrderID(String OrderID) throws SQLException {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        cnn = new DBContext().connection;
        try {
            String sql = "SELECT f.* FROM shoponline.feedback f "
                    + "where shoponline.f.OrderID='"+OrderID+"'";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("Comment");
                int ratedStart = rs.getInt("RatedStar");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                Feedback feedback = new Feedback(id, comment, ratedStart, product, users, status ,image, orderID);
                feedbacks.add(feedback);
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
        return feedbacks;
    }

    public Feedback getLastFeedBack() throws SQLException {
//        ArrayList<Feedback> feedbacks = new ArrayList<>();
        cnn = new DBContext().connection;
        try {
            String sql = "SELECT RatedStar, Comment, ProductID, UsersID, ID FROM feedback order by ID desc LIMIT 1";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("Comment");
                int ratedStart = rs.getInt("RatedStar");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                Feedback feedback = new Feedback(id, comment, ratedStart, product, users);
//                feedbacks.add(feedback);
                return feedback;
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
    
    public Feedback getLastFeedBack1() throws SQLException {
//        ArrayList<Feedback> feedbacks = new ArrayList<>();
        cnn = new DBContext().connection;
        try {
            String sql = "SELECT * FROM feedback order by ID desc LIMIT 1";
            pstm = cnn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("Comment");
                int ratedStart = rs.getInt("RatedStar");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                Feedback feedback = new Feedback(id, comment, ratedStart, product, users, status ,image, orderID);
//                feedbacks.add(feedback);
                return feedback;
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

//    INSERT INTO OnlineShop.feedback (RatedStar, Comment, ProductID, UsersID) VALUES(0, '', '', '');
    public Feedback saveFeedback(Feedback feedback) throws SQLException {
        cnn = new DBContext().connection;
        try {
            String sql = "INSERT INTO OnlineShop.feedback (RatedStar, Comment, ProductID, UsersID) VALUES(?, ?, ?, ?);";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, feedback.getRatedStart());
            pstm.setString(2, feedback.getComment());
            pstm.setString(3, feedback.getProduct().getId());
            pstm.setString(4, feedback.getUsers().getId());
            if (pstm.executeUpdate() > 0) {
                return getLastFeedBack();
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
    
    public Feedback saveFeedback1(Feedback feedback) throws SQLException {
        cnn = new DBContext().connection;
        try {
            String sql = "INSERT INTO feedback (RatedStar, Comment, ProductID, UsersID, Status, Image, OrderID) VALUES(?, ?, ?, ?, ?, ?, ?);";
            pstm = cnn.prepareStatement(sql);
            pstm.setInt(1, feedback.getRatedStart());
            pstm.setString(2, feedback.getComment());
            pstm.setString(3, feedback.getProduct().getId());
            pstm.setString(4, feedback.getUsers().getId());
            pstm.setInt(5, feedback.getStatus());
            pstm.setString(6, feedback.getImage());
            pstm.setString(7, feedback.getOrderID());
            if (pstm.executeUpdate() > 0) {
                return getLastFeedBack1();
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
    
    
    
    //CTT
    public Feedback getFeedbackByID(int id) {
        Feedback data = null;
        try {
            String strSelect = "select fb.* from feedback fb WHERE fb.ID ="+id+"";
            PreparedStatement ps = connection.prepareStatement(strSelect);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int RatedStar = rs.getInt("RatedStar");
                String Comment = rs.getString("Comment");
//                String ProductID = rs.getString("ProductID");
//                String UsersID = rs.getString("UsersID");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int Status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                return new Feedback(ID, Comment, RatedStar, product, users, Status,image, orderID);
            }
        } catch (Exception e) {
            System.out.println("getListFeedback:" + e.getMessage());
        }
        return data;
    }
    
    public int changeStatus(int ID) {
        int result = 0;
        try {
//            String strSelect = "select * from feedback";
//            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            rs = stm.executeQuery(strSelect); if(rs.next()) System.out.println("123");
            String sql = "UPDATE `shoponline`.`feedback` SET `Status` = '1' WHERE (`ID` = ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ID);
            result = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("getListFeedback:" + e.getMessage());
        }
        return result;
    }
    
    public ArrayList<Feedback> getListFeedback() {
        ArrayList<Feedback> data = new ArrayList<>();
        try {
//            String strSelect = "select * from feedback";
//            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
//            rs = stm.executeQuery(strSelect); if(rs.next()) System.out.println("123");
            String sql = "select fb.*,u.Name as UserName, p.Name as ProductName, p.ID as ProductID from feedback fb "
                    + "inner join product p on fb.ProductID = p.ID "
                    + "inner join users u on u.ID=fb.UsersID ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int RatedStar = rs.getInt("RatedStar");
                String Comment = rs.getString("Comment");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int Status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                data.add(new Feedback(ID, Comment, RatedStar, product, users, Status,image, orderID));
            }
        } catch (Exception e) {
            System.out.println("getListFeedback:" + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<Feedback> getListFeedbackByFilter2(String userName, String productName, int Star, int Page) {
        ArrayList<Feedback> data = new ArrayList<>();
        try {
            String sql = "select distinct fb.* from feedback fb \n" +
                "inner join product p on fb.ProductID = p.ID \n" +
                "inner join users u on u.ID=fb.UsersID \n" +
                "where p.Name LIKE '%"+productName+"%' AND u.Name LIKE '%"+userName+"%'";
            
            if(Star>0) sql +=  "AND fb.RatedStar=" +Star;
            sql +=  " limit " +(Page-1)*5 + ",5";
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int RatedStar = rs.getInt("RatedStar");
                String Comment = rs.getString("Comment");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int Status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                data.add(new Feedback(ID, Comment, RatedStar, product, users, Status,image, orderID));
            }
        } catch (Exception e) {
            System.out.println("getListFeedback:" + e.getMessage());
        }
        return data;
    }
    public ArrayList<Feedback> getListFeedbackByFilter1(String userName, String productName, int Star) {
        ArrayList<Feedback> data = new ArrayList<>();
        try {
            String sql = "select distinct fb.*,u.Name as UserName, p.Name as ProductName from feedback fb \n" +
                "inner join product p on fb.ProductID = p.ID \n" +
                "inner join users u on u.ID=fb.UsersID \n" +
                "where p.Name LIKE '%"+productName+"%' AND u.Name LIKE '%"+userName+"%'";
            
            if(Star>0) sql +=  "AND fb.RatedStar=" +Star;
            
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int ID = rs.getInt("ID");
                int RatedStar = rs.getInt("RatedStar");
                String Comment = rs.getString("Comment");
                Product product = productDAO.getProductById(rs.getString("ProductID"));
                Users users = userDAO.getUsersByID(rs.getString("UsersID"));
                int Status = rs.getInt("Status");
                String image = rs.getString("Image");
                String orderID = rs.getString("OrderID");
                data.add(new Feedback(ID, Comment, RatedStar, product, users, Status,image, orderID));
            }
        } catch (Exception e) {
            System.out.println("getListFeedback:" + e.getMessage());
        }
        return data;
    }
    
    public static void main(String[] args) throws SQLException {
        FeedbackDAO fb = new FeedbackDAO();
        Product pro = new Product(); pro.setId("HG01");
        Users u = new Users(); u.setId("phamquang1203");
        System.out.println(fb.saveFeedback1(new Feedback(0, "comment", 3, pro, u, 0, "fjshvsd", "abc")).getId());
    }

}
