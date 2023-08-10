/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cart;
import Model.Orders;
import Model.Product;
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
public class ChangeAccountTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //get user id
        String changeAccountId = req.getParameter("changeAccountId");
        String newTypeIdTemp = req.getParameter("typeId");
        int newTypeId = Integer.parseInt(newTypeIdTemp);
        //change type id
        Users ur = new Users();
        ur.updateAccountType(newTypeId, changeAccountId);

        //Refresh account info
        HttpSession session = req.getSession();
        String result = "", acc = req.getParameter("currentAccountId");
        Users u = new Users(acc, "");
        ArrayList<Users> data = u.getListUsers();
        for (int i = 0; i < data.size(); i++) {
            if (acc.equals(data.get(i).getId().trim())) {
                Users temp = data.get(i);

                //Customer handling
                if (temp.getTypeId() == 1) {
                    //Get orderlist by userID
                    Orders o = new Orders();
                    ArrayList<Orders> orderList = o.getListOrderByUserID(temp.getId());
                    session.setAttribute("orderList", orderList);
                }
                //Marketer handling(must finish post features to be done)
                if (temp.getTypeId() == 2) {
                    Product p = new Product();
                    ArrayList productList = p.getListProduct();
                    session.setAttribute("productList", productList);
                    //Get start date and end date
                    //Get number of posts in a week
                    //Get number of products being bought
                    // + analyse data from shopping cart
                }

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
                //Admin handling
                if (temp.getTypeId() == 4) {
                    //Admin dashboard
                    //  + Show number of success orders
                    Orders o = new Orders();
                    ArrayList<Orders> successOrders = o.getListOrderByStatus("Success");
                    session.setAttribute("successOrdersAmount", successOrders.size());
                    //  + Show number of cancelled orders
                    ArrayList<Orders> cancelledOrders = o.getListOrderByStatus("Cancelled");
                    session.setAttribute("cancelledOrdersAmount", cancelledOrders.size());
                    //  + Show number of submitted orders
                    ArrayList<Orders> submittedOrders = o.getListOrderByStatus("Submitted");
                    session.setAttribute("submittedOrdersAmount", submittedOrders.size());

                    //  + Show current profits
                    Cart c = new Cart();

                    // + Revenue from sd product
                    ArrayList<Cart> sdCart = c.getCartListByProductID("SD");
                    Double sdRevenue = 0.0d;
                    for (int j = 0; j < sdCart.size(); j++) {
                        sdRevenue = sdRevenue + sdCart.get(j).getPrice();
                    };
                    session.setAttribute("sdRevenue", sdRevenue);

                    // + Revenue from hg product
                    ArrayList<Cart> hgCart = c.getCartListByProductID("HG");
                    Double hgRevenue = 0.0d;
                    for (int j = 0; j < hgCart.size(); j++) {
                        hgRevenue = hgRevenue + hgCart.get(j).getPrice();
                    }
                    session.setAttribute("hgRevenue", hgRevenue);

                    // + Revenue from mg product
                    ArrayList<Cart> mgCart = c.getCartListByProductID("MG");
                    Double mgRevenue = 0.0d;
                    for (int j = 0; j < mgCart.size(); j++) {
                        mgRevenue = mgRevenue + mgCart.get(j).getPrice();
                    }
                    session.setAttribute("mgRevenue", mgRevenue);

                    // + Revenue from pg product
                    ArrayList<Cart> pgCart = c.getCartListByProductID("PG");
                    Double pgRevenue = 0.0d;
                    for (int j = 0; j < pgCart.size(); j++) {
                        pgRevenue = pgRevenue + pgCart.get(j).getPrice();
                    }
                    session.setAttribute("pgRevenue", pgRevenue);

                    //  + Show list of orders sorted by date
                    //  + Show list of feedbacks
                    //User Details
                    //Get user List
                    ArrayList<Users> userList = u.getListUsers();
                    session.setAttribute("userList", userList);
                }
                req.setAttribute("temp", temp);
                req.getRequestDispatcher("details.jsp").forward(req, resp);
            }
        }
    }

}
