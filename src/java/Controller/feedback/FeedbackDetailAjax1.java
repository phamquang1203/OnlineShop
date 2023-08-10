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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author user
 */
@WebServlet(name = "FeedbackDetailAjax1", urlPatterns = {"/changeStatusmkt"})
public class FeedbackDetailAjax1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        int ID = Integer.parseInt(request.getParameter("ID"));
        FeedbackDAO fbdao = new FeedbackDAO();
        Feedback fb = new Feedback();
        if(fbdao.changeStatus(ID) !=0){
            fb = fbdao.getFeedbackByID(ID);
        }
        
        String statusHTML = "<span id=\"status\" style=\"color: green;\">Done</span>";
        if(fb.getStatus()==0){
            statusHTML="<span id=\"status\" style=\"color: red;\">In Progress</span>";
        }
        PrintWriter out = response.getWriter();
        String result = "<div>Contact Name: "+fb.getUsers().getName()+"</div>\n" +
"        <div>Product ID: "+fb.getProduct().getId()+"</div>\n" +
"        <div>Product Name: "+fb.getProduct().getName()+"</div>\n" +
"        <div>Rated Star: "+fb.getRatedStart()+"</div>\n" +
"        <div>Comment: "+fb.getComment()+"</div>\n" +
"        <div id=\"status\">Status: "+statusHTML+"</div>\n" +
"        <div><button onclick=\"setDone('"+fb.getId()+"')\">set Done</button></div>";
        out.println(result);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
