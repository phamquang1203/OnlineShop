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
public class ChangeItemAmountController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        //Get item id, order id and current amount
        String itemId = req.getParameter("itemId");
        String orderId = req.getParameter("orderId");
        int newAmount = Integer.parseInt(req.getParameter("amount"));

        //Get cart by itemID and productID
        Cart c = new Cart();
        c = c.getCartByProductID(itemId, orderId);

        //get current cart quantity
        int currentCartQuantity = c.getQuantity();

        //get current product quantity
        Product p = new Product();
        p = p.getProductByID(itemId);
        int productQuantity = p.getQuantity();

        //Update item in cart
        if (newAmount <= currentCartQuantity + productQuantity) {
            p.updateQuantity(productQuantity + currentCartQuantity - newAmount);
            c.updateCartQuantity(newAmount);
            Double newPrice = p.getPrice()*newAmount;
            c.updateCartPrice(newPrice);
        } else {
            newAmount = currentCartQuantity + productQuantity;
            p.updateQuantity(productQuantity + currentCartQuantity - newAmount);
            c.updateCartQuantity(newAmount);
            Double newPrice = p.getPrice()*newAmount;
            c.updateCartPrice(newPrice);
        }

        //get all items by orderID
        ArrayList<Cart> listc = new ArrayList<Cart>();
        listc = c.getListCartByOrderID(orderId);

        //initialize total price        
        double totalPrice = 0;

        //initialize items to generate productList
        Product pu = new Product();
        ArrayList<Product> listp = new ArrayList<Product>();

        //generate productList with cartList and calculate total price
        for (int i = 0; i < listc.size(); i++) {
            totalPrice = totalPrice + listc.get(i).getPrice();
            pu = p.getProductByID(listc.get(i).getProductId());
            listp.add(pu);
        }
        session.setAttribute("cart", listp);
        session.setAttribute("cart2", listc);

        //Calculate tax
        double tax = totalPrice / 100;
        double totalWithTax = totalPrice + tax;

        //set total price
        session.setAttribute("totalPrice", totalPrice);

        //Set tax
        session.setAttribute("tax", tax);

        //set total with tax
        session.setAttribute("totalWithTax", totalWithTax);

        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }

}
