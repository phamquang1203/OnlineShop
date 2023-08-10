/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DW
 */
public class Feedback {

    private int id;
    private String comment;
    private int ratedStart;
    private Product product;
    private Users users;
    int status;
    String Image;
    String OrderID;

    public Feedback() {
    }

    public Feedback(int id, String comment, int ratedStart, Product product, Users users) {
        this.id = id;
        this.comment = comment;
        this.ratedStart = ratedStart;
        this.product = product;
        this.users = users;
    }

    
    public Feedback(int id, String comment, int ratedStart, Product product, Users users, int status, String image, String orderID) {
        this.id = id;
        this.comment = comment;
        this.ratedStart = ratedStart;
        this.product = product;
        this.users = users;
        this.status = status;
        this.Image = image;
        this.OrderID = orderID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRatedStart() {
        return ratedStart;
    }

    public void setRatedStart(int ratedStart) {
        this.ratedStart = ratedStart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }
    
    

}
