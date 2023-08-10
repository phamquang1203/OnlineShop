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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author PhamQuang
 */
public class ChangeStatusController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //get new status 
        String newOrderStatus = req.getParameter("orderStatus");

        //Get order by orderID
        String currentOrderId = req.getParameter("currentOrderId");
        Orders pendingOrder = new Orders();
        pendingOrder = pendingOrder.getOrderByOrderID(currentOrderId);

        //Change order status
        pendingOrder.updateStatus(newOrderStatus);
        
        //Refresh details page
        HttpSession session = req.getSession();
        String acc = req.getParameter("currentAccountId");
        Users u = new Users(acc, "");
        ArrayList<Users> data = u.getListUsers();
        for (int i = 0; i < data.size(); i++) {
            if (acc.equals(data.get(i).getId().trim())) {
                Users temp = data.get(i);
                //Sales handling
                if (temp.getTypeId() == 3) {
                    //Order details

                    //  + get all order from table
                    Orders o = new Orders();
                    ArrayList<Orders> orderList = o.getListOrder();
                    session.setAttribute("orderList", orderList);

                    //  + get customer data from orderList
                    ArrayList<Users> buyerList = new ArrayList<Users>();
                    for (Orders orderList1 : orderList) {
                        String newUserId = orderList1.getUsersId();
                        Users newUser = u.getUsersByID(newUserId);
                        buyerList.add(newUser);
                    }
                    session.setAttribute("buyerList", buyerList);
                }

                req.setAttribute("temp", temp);
                req.getRequestDispatcher("details.jsp").forward(req, resp);
            }
        }
    }

}
