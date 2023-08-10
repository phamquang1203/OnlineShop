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
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author PhamQuang
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ChangeUserDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String currentAccountId = req.getParameter("accountId");
        String newAccountName = req.getParameter("accountName");
        String newAccountGender = req.getParameter("accountGender");
        String newAccountEmail = req.getParameter("accountEmail");
        String newAccountMobile = req.getParameter("accountMobile");
        String newAccountAddress = req.getParameter("accountAddress");
        String newImage = "";
        //get image info
        try {
            Part part = req.getPart("accountImage");
            //The following directory should be changed for every machine
            String realPath = "E:\\School_projects\\Semester_5\\SWP391\\onlinegundamshop-master\\web\\img";
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            newImage = filename;
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath + "/" + filename);
        } catch (Exception e) {

        }
        if (newImage.equals("")) {
            newImage = "default.png";
        }

        Users u = new Users();
        u.updateAccountName(newAccountName, currentAccountId);
        u.updateAccountGender(newAccountGender, currentAccountId);
        u.updateAccountEmail(newAccountEmail, currentAccountId);
        u.updateAccountMobile(newAccountMobile, currentAccountId);
        u.updateAccountAddress(newAccountAddress, currentAccountId);
        u.updateAccountImage(newImage, currentAccountId);
        //Refresh Cart
        HttpSession session = req.getSession();
        String result = "", acc = req.getParameter("accountId");
        Users ur = new Users(acc, "");
        ArrayList<Users> data = ur.getListUsers();
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
                        for (int j = 0; j < sdCart.size();j++) {
                            sdRevenue = sdRevenue + sdCart.get(j).getPrice();
                        };
                        session.setAttribute("sdRevenue", sdRevenue);
                        
                        // + Revenue from hg product
                        ArrayList<Cart> hgCart = c.getCartListByProductID("HG");
                        Double hgRevenue = 0.0d;
                        for (int j = 0; j < hgCart.size();j++) {
                            hgRevenue = hgRevenue + hgCart.get(j).getPrice();
                        }
                        session.setAttribute("hgRevenue", hgRevenue);
                        
                        // + Revenue from mg product
                        ArrayList<Cart> mgCart = c.getCartListByProductID("MG");
                        Double mgRevenue = 0.0d;
                        for (int j = 0; j < mgCart.size();j++) {
                            mgRevenue = mgRevenue + mgCart.get(j).getPrice();
                        }
                        session.setAttribute("mgRevenue", mgRevenue);
                        
                        // + Revenue from pg product
                        ArrayList<Cart> pgCart = c.getCartListByProductID("PG");
                        Double pgRevenue = 0.0d;
                        for (int j = 0; j < pgCart.size();j++) {
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
