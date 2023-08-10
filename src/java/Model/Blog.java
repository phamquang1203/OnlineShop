package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class Blog {

    String ID;
    String title;
    String Content;
    String UploadDate;
    String UploadTime;
    String UserID;
    String CateID;
    String BlogImage;

    public Blog() {
    }

    public Blog(String ID, String title, String Content, String UploadDate, String UploadTime, String UserID, String CateID, String BlogImage) {
        this.ID = ID;
        this.title = title;
        this.Content = Content;
        this.UploadDate = UploadDate;
        this.UploadTime = UploadTime;
        this.UserID = UserID;
        this.CateID = CateID;
        this.BlogImage = BlogImage;
    }
    
    

    public Blog(String title, String Content, String UploadDate, String UploadTime, String UserID, String CateID, String BlogImage) {
        this.title = title;
        this.Content = Content;
        this.UploadDate = UploadDate;
        this.UploadTime = UploadTime;
        this.UserID = UserID;
        this.CateID = CateID;
        this.BlogImage = BlogImage;
    }

    public String getCateID() {
        return CateID;
    }

    public void setCateID(String CateID) {
        this.CateID = CateID;
    }

    public String getBlogImage() {
        return BlogImage;
    }

    public void setBlogImage(String BlogImage) {
        this.BlogImage = BlogImage;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public String getUploadDate() {
        return UploadDate;
    }

    public void setUploadDate(String UploadDate) {
        this.UploadDate = UploadDate;
    }

    public String getUploadTime() {
        return UploadTime;
    }

    public void setUploadTime(String UploadTime) {
        this.UploadTime = UploadTime;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
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
                String CateID = rs.getString(7);
                String BlogImage = rs.getString(8);
                data.add(new Blog(id, title, Content, UploadDate, UploadTime, UserID, CateID, BlogImage));
            }
        } catch (Exception e) {
            System.out.println("getListBlog:" + e.getMessage());
        }
        return data;
    }

    public Blog getListBlogByTitle(String titles) {
        Blog data = new Blog();
        try {
            String strSelect = "SELECT * FROM shoponline.blog where title = '" + titles + "' ";
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                String id = rs.getString(1);
                titles = rs.getString(2);
                String Content = rs.getString(3);
                String UploadDate = rs.getString(4);
                String UploadTime = rs.getString(5);
                String UserID = rs.getString(6);
                String CateID = rs.getString(7);
                String BlogImage = rs.getString(8);
                data = new Blog(id, titles, Content, UploadDate, UploadTime, UserID, CateID, BlogImage);
            }
        } catch (Exception e) {
            System.out.println("getListBlogByTitle:" + e.getMessage());
        }
        return data;
    }
    
    public ArrayList<Blog> getListBlogByID(String id) {
        ArrayList<Blog> data = new ArrayList<Blog>();
        try {
            String strSelect = "SELECT * FROM shoponline.blog where id = '" + id + "' ";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                id = rs.getString(1);
                String title = rs.getString(2);
                String Content = rs.getString(3);
                String UploadDate = rs.getString(4);
                String UploadTime = rs.getString(5);
                String UserID = rs.getString(6);
                String CateID = rs.getString(7);
                String BlogImage = rs.getString(8);
                data.add(new Blog(id, title, Content, UploadDate, UploadTime, UserID, CateID, BlogImage));
            }
        } catch (Exception e) {
            System.out.println("getListBlog:" + e.getMessage());
        }
        return data;
    }

    public Blog getListBlogByCateID(String id) {
        Blog data = new Blog();
        try {
            String strSelect = "SELECT * FROM shoponline.blog where id = '" + id + "' ";
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                id = rs.getString(1);
                String title = rs.getString(2);
                String Content = rs.getString(3);
                String UploadDate = rs.getString(4);
                String UploadTime = rs.getString(5);
                String UserID = rs.getString(6);
                String CateID = rs.getString(7);
                String BlogImage = rs.getString(8);
                data = new Blog(id, title, Content, UploadDate, UploadTime, UserID, CateID, BlogImage);
            }
        } catch (Exception e) {
            System.out.println("getListBlogByID:" + e.getMessage());
        }
        return data;
    }

    public int getTotalBlog() {
        String strSelect = "SELECT count(*) FROM shoponline.blog";
        try {
            System.out.println(strSelect);
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("getTotalBlog:" + e.getMessage());
        }
        return 0;
    }

    public ArrayList<Blog> pagingBlog(int index) {
        ArrayList<Blog> lb = new ArrayList<>();
        String strSelect = "SELECT * FROM shoponline.blog order by UploadDate DESC, UploadTime desc limit ?,3;";
        try {
            System.out.println(strSelect);
            pstm = cnn.prepareStatement(strSelect);
            pstm.setInt(1, (index - 1) * 3);
            rs = pstm.executeQuery();
            while (rs.next()) {
                lb.add(new Blog(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8)));
                return lb;
            }
        } catch (Exception e) {
            System.out.println("pagingBlog:" + e.getMessage());
        }
        return null;
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

    public void add() {
        try {
            String strUpdate = "INSERT INTO Blog(id, title, Content, UploadDate, UploadTime, UserID,CateID, BlogImage)\n"
                    + "VALUES (?,?,?,?,?,?,?,?);";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, ID.toString().trim());
            pstm.setString(2, title.toString().trim());
            pstm.setString(3, Content.toString().trim());
            pstm.setString(4, UploadDate.toString().trim());
            pstm.setString(5, UploadTime.toString().trim());
            pstm.setString(6, UserID.toString().trim());
            pstm.setString(7, CateID.toString().trim());
            pstm.setString(8, BlogImage.toString().trim());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("AddBlogs:" + e.getMessage());
        }
    }
}
