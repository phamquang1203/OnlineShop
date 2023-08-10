/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Feedback;
import Model.Product;
import dao.FeedbackDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PhamQuang
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/ProductDetailController"})
public class ProductDetailController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            String name = req.getParameter("name");
            Product p = new Product();
            p = p.getListProductByName(name);
            FeedbackDAO feedbackDAO = new FeedbackDAO();
            List<Feedback> feedbacks = feedbackDAO.getAllFeedbackByProductName(name);
            session.setAttribute("product", p);
            session.setAttribute("feedbacks", feedbacks);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            req.getRequestDispatcher("productDetail.jsp").forward(req, resp);
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

}
