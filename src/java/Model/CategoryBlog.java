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
 * @author PC
 */
public class CategoryBlog {

    String ID;
    String Name;
    String Descreption;

    public CategoryBlog() {
    }

    public CategoryBlog(String ID, String Name, String Descreption) {
        this.ID = ID;
        this.Name = Name;
        this.Descreption = Descreption;
    }

    
    

    public CategoryBlog(String Name, String Descreption) {
        this.Name = Name;
        this.Descreption = Descreption;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescreption() {
        return Descreption;
    }

    public void setDescreption(String Descreption) {
        this.Descreption = Descreption;
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

    public ArrayList<CategoryBlog> getListCategory() {
        ArrayList<CategoryBlog> data = new ArrayList<CategoryBlog>();
        try {
            String strSelect = "SELECT * FROM shoponline.category";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String descreption = rs.getString(3);
                data.add(new CategoryBlog(id, name, descreption));
            }
        } catch (Exception e) {
            System.out.println("getListCategory:" + e.getMessage());
        }
        return data;

    }
}
