
package dao;

import Model.Blog;
import Model.Blog;
import Model.DBContext;
import Model.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BlogDAO {
    Connection cnn = new DBContext().connection; //ket noi
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
    
    public ArrayList<Blog> getListBlog() {
        ArrayList<Blog> data = new ArrayList<Blog>();
        try {
            String strSelect = "SELECT * FROM shoponline.blog";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String Content = rs.getString(3);
                String UploadDate = rs.getString(4);
                String UploadTime = rs.getString(5);
                String UserID = rs.getString(6);
                String BlogImage = rs.getString(7);
                data.add(new Blog(id, title, Content, UploadDate, UploadTime, UserID, BlogImage));
                return data;
            }
        } catch (Exception e) {
            System.out.println("getListBlog:" + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<Blog> getListBlogByCategoryID(String CID) {
        ArrayList<Blog> data = new ArrayList<Blog>();
        try {
            String strSelect = "SELECT * FROM shoponline.blog where CateID = '" + CID + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                String id = rs.getString(1);
                String titles = rs.getString(2);
                String Content = rs.getString(3);
                String UploadDate = rs.getString(4);
                String UploadTime = rs.getString(5);
                String UserID = rs.getString(6);
                CID = rs.getString(7);
                String BlogImage = rs.getString(8);
                data.add(new Blog(id, titles, Content, UploadDate, UploadTime, UserID,CID, BlogImage));
                return data;
            }
        } catch (Exception e) {
            System.out.println("getListBlog:" + e.getMessage());
        }
        return data;
    }
    
    public void deleteData() {
        try {
            String strUpdate = "delete from blog where blog.ID in ";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("Refresh:" + e.getMessage());
        }
    }
}
