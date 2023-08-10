
package dao;

import Model.Blog;
import Model.Category;
import Model.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    //Khai bao cac thanh phan xu ly DB
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
    
    public ArrayList<Category> getListCategory() {
        ArrayList<Category> data = new ArrayList<Category>();
        try {
            String strSelect = "SELECT * FROM shoponline.category";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String descreption = rs.getString(3);               
                data.add(new Category(id,name, descreption));
            }
        } catch (Exception e) {
            System.out.println("getListCategory:" + e.getMessage());
        }
        return data;
    }
    
    
}
