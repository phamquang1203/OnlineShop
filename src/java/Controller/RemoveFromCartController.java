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
@WebServlet(name = "RemoveFromCartController", urlPatterns = {"/removeFromCart"})
public class RemoveFromCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String itemId = req.getParameter("itemId");
        String orderId = req.getParameter("orderId");
        Cart c = new Cart();
        c = c.getCartByProductID(itemId, orderId);
        c.removeFromCart();

        //Reinitialize item in cart
        ArrayList<Cart> listc = new ArrayList<Cart>();
        listc = c.getListCartByOrderID(orderId);

        //initialize total price        
        double totalPrice = 0;

        //initialize items to generate productList
        Product p = new Product();
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
