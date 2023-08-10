/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Orders;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import utils.MailSender;

/**
 *
 * @author PhamQuang
 */
@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

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
        req.getRequestDispatcher("order.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //Create order here

        //Get data
        //GenerateID
        String id = getAlphaNumericString(5);
        // + get Order id
        String orderId = req.getParameter("orderID");
        // + get userid
        String accountId = req.getParameter("accountID");
        // + status = preparing
        String status = "Submitted";
        //Get current date
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());
        String currentDate = sqlDate.toString();
        //create order
        Orders o = new Orders(id, status, currentDate, accountId, orderId);

        //get email
        Users buyer = new Users();
        buyer = buyer.getUsersByID(accountId);
        String email = buyer.getEmail();

        //create message
        String htmlContent = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <title>Password Notification</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            font-size: 14px;\n"
                + "            line-height: 1.6;\n"
                + "        }\n"
                + "\n"
                + "        .container {\n"
                + "            width: 500px;\n"
                + "            margin: 0 auto;\n"
                + "        }\n"
                + "\n"
                + "        h2 {\n"
                + "            color: #555;\n"
                + "        }\n"
                + "\n"
                + "        p {\n"
                + "            margin-bottom: 20px;\n"
                + "        }\n"
                + "\n"
                + "        .password {\n"
                + "            font-weight: bold;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div class=\"container\">\n"
                + "        <h2>Order Notification</h2>\n"
                + "        <p>Dear Buyer,</p>\n"
                + "        <p>Your order has been submitted. Please confirm it by typing this orderID into your account information:</p>\n"
                + "        <p>Order ID: <span class=\"password\">" + id + "</span></p>\n"
                + "        <p>Please ensure to keep this orderID confidential and do not share it with anyone.</p>\n"
                + "        <p>Thank you!</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>";
        
        MailSender.send("Order confirmation", htmlContent, email);
        //add order to database
        o.add();

        //recreate orderId
        String newOrderID = getAlphaNumericString(5);
        HttpSession session = req.getSession();
        session.setAttribute("orderID", newOrderID);

        //back to home page
        req.getRequestDispatcher("createOrder.jsp").forward(req, resp);
    }

}
