/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.feedback;

import Model.Feedback;
import dao.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author user
 */
@WebServlet(name="FeedbackControllermkt", urlPatterns={"/feedbackmkt"})
public class FeedbackController_mkt extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //Caotrung
        FeedbackDAO dbd = new FeedbackDAO();
        ArrayList<Feedback> feedbackList = dbd.getListFeedback();
        int currentPage = 1;
        int numberOfPage = (int)Math.ceil(feedbackList.size()/5);
        if(feedbackList.size()<=5) {req.setAttribute("feedbackList", feedbackList);}
        else {
            req.setAttribute("feedbackList", feedbackList.subList(0, 5));
        }
        
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("numberOfPage", numberOfPage);
        
        req.getRequestDispatcher("feedback-mkt.jsp").forward(req, resp);
    }
    
}
