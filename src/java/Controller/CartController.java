/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cart;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author PhamQuang
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    static String getAlphaNumericString(int n) {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //get session
        HttpSession session = req.getSession();

        //get OrderID
        String orderId = (String) session.getAttribute("orderID");

        //generate cart to access function
        Cart c = new Cart();
        ArrayList<Cart> listc = c.getListCartByOrderID(orderId);

        //initialize total price        
        double totalPrice = 0;

        //initialize items to generate productList
        Product p = new Product();
        Product pu = new Product();
        ArrayList<Product> listp = new ArrayList<Product>();

        //generate productList with cartList
        for (int i = 0; i < listc.size(); i++) {
            totalPrice = totalPrice + listc.get(i).getPrice();
            pu = p.getProductByID(listc.get(i).getProductId());
            listp.add(pu);
        }

        //Calculate tax
        double tax = totalPrice / 100;
        double totalWithTax = totalPrice + tax;

        //Set list product
        session.setAttribute("cart", listp);

        //Set list cart
        session.setAttribute("cart2", listc);

        //set total price
        session.setAttribute("totalPrice", totalPrice);

        //Set tax
        session.setAttribute("tax", tax);

        //set total with tax
        session.setAttribute("totalWithTax", totalWithTax);

        //Dispatch to main cart site
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //add items to cart
        HttpSession session = req.getSession();

        //Get productID
        String productId = req.getParameter("productId");

        //Get OrderID
        String orderId = (String) session.getAttribute("orderID");

        //get current Product quantity
        Product p = new Product();
        p = p.getProductByID(productId);
        int productQuantity = p.getQuantity();
        
        //Get buying quantity
        String quantityTemp = req.getParameter("quantity");
        int quantity = Integer.parseInt(quantityTemp);

        //Get product price
        double price = Double.parseDouble(req.getParameter("productPrice"))*quantity;

        //get item in cart
        Cart c = new Cart();
        c = c.getCartByProductID(productId, orderId);
        if (c == null && quantity <= productQuantity) {//if item doesn't exist in cart
            //update product Quantity
            int newProductQuantity = productQuantity - quantity;
            p.updateQuantity(newProductQuantity);
            //add new item to cart
            c = new Cart(getAlphaNumericString(5), productId, quantity, price, orderId);
            c.add();
        } else if (quantity <= productQuantity) {//if item already exist in cart
            //Update product pantity
            int newProductQuantity = productQuantity - quantity;
            p.updateQuantity(newProductQuantity);

            //add item quantity
            System.out.println("add");
            int currentQuantity = c.getQuantity();
            int newQuantity = currentQuantity + quantity;
            c.updateQuantity(newQuantity, price);
        }
        p = p.getProductByID(productId);        
        req.setAttribute("product", p);
        //going back to productDetail
        req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
    }

}
