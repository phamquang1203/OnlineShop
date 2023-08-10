/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cart;
import Model.Feedback;
import Model.Product;
import Model.Users;
import dao.FeedbackDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PhamQuang
 */
@MultipartConfig
@WebServlet(name = "OrderDetailsController", urlPatterns = {"/orderDetails"})
public class OrderDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        String orderId = req.getParameter("id");
        if(session.getAttribute("orderID") != null && req.getParameter("id")==null){
            orderId = (String)session.getAttribute("orderID");
            session.removeAttribute("orderID");
            req.setAttribute("msg", "Feedback Successfully!");
        }
        
        Cart c = new Cart();
        ArrayList<Cart> cartList = c.getListCartByOrderID(orderId);
        ArrayList<Product> productList = new ArrayList<Product>();
        double totalPrice = 0;
        //Get productList by cartList
        for (int i =0; i< cartList.size();i++){
            Product p = new Product();
            p = p.getProductByID(cartList.get(i).getProductId());
            productList.add(p);
            totalPrice = totalPrice + cartList.get(i).getPrice();
        }
        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("productListing", productList);
        session.setAttribute("cartListing", cartList); 
        
        try {
            //bct
            List<Feedback> feedbacks = new FeedbackDAO().getAllFeedbackByOrderID(orderId);
            if(!feedbacks.isEmpty()) session.setAttribute("feedbacks",feedbacks);
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("orderId", orderId);
        req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);

    }
    
    private static final long serialVersionUID = 1L;
    
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {resp.setContentType("text/html;charset=UTF-8");
        try {
            
            int rating =1;
            if(req.getParameter("rate")!=null)  rating = Integer.parseInt(req.getParameter("rate"));
            
            String comment ="";
            if(req.getParameter("txtComment")!=null) comment= req.getParameter("txtComment");
            
            String orderID = (String)req.getSession().getAttribute("orderId");
//            int productID = Integer.parseInt(req.getParameter("productID"));
            Users user = (Users)req.getSession().getAttribute("account");
            
            String fileNameToStore = "";
            String random = randomString(5);
            
            Part filePart  = req.getPart("chooseFile");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString().replace(".",random+".");
            if(fileName.length()!=0){
                //Part filePart  = req.getPart("chooseFile");
                fileNameToStore = fileName;
                // Set the destination directory for the uploaded file
                String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                // Copy the file to the destination directory
                InputStream inputStream = filePart.getInputStream();
                Files.copy(inputStream, Paths.get(uploadPath + File.separator + fileName), StandardCopyOption.REPLACE_EXISTING);
            
            }
            
            ArrayList<Product> products = new ArrayList<>();
            if(req.getParameter("productID")!=null){
                products.add(new ProductDAO().getProductById(req.getParameter("productID")));
            }else{
                products = (ArrayList<Product>)(req.getSession().getAttribute("productListing"));
            }
            
            ArrayList<Feedback> feedbacks = new ArrayList<>();
            for (Product product : products) {
                feedbacks.add(new Feedback(0, comment, rating, product, user, 0, fileNameToStore , orderID));
            }
            
            for (Feedback feedback : feedbacks) {
                new FeedbackDAO().saveFeedback1(feedback);
            }
//            boolean isreviewed = 
//            if(!isreviewed){
//                req.setAttribute("msg", "some thing went wrong!");
//            }
            req.getSession().setAttribute("orderID", orderID);
            resp.getWriter().print("abc");
            doGet(req, resp);
            //req.getRequestDispatcher("/AccountProfile2_review?id="+orderID).forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().print("loi");
//            resp.sendRedirect(req.getContextPath()+"/404error.jsp");
        }
    }
    
    
    public String randomString(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

}
