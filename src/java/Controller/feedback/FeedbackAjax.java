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
@WebServlet(name = "FeedbackAjax", urlPatterns = {"/searchFeedbackmkt"})
public class FeedbackAjax extends HttpServlet {

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
        
        
        String UserName = request.getParameter("UserName");
        String ProductName = request.getParameter("ProductName");
        int Star = Integer.parseInt(request.getParameter("Star"));
        int Sort = Integer.parseInt(request.getParameter("Sort"));
        int Page = Integer.parseInt(request.getParameter("Page"));
        FeedbackDAO fb = new FeedbackDAO();
        List<Feedback> feedbackList = fb.getListFeedbackByFilter1(UserName, ProductName, Star);
        
        
        
        Comparator sortByUserNameIncrease = new Comparator() {
        @Override
            public int compare(Object o1, Object o2) {
                Feedback pro1 = (Feedback) o1;
                Feedback pro2 = (Feedback) o2;
                
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) >0) return 1;
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) ==0) {
                    return 0;
                }
                return -1;
                
            }
        };
        Comparator sortByUserNameDecrease = new Comparator() {
        @Override
            public int compare(Object o1, Object o2) {
                Feedback pro1 = (Feedback) o1;
                Feedback pro2 = (Feedback) o2;
                
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) >0) return -1;
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) ==0) {
                    return 0;
                }
                return 1;
            }
        };
        //tang >0 1
        Comparator sortByProductNameIncrease = new Comparator() {
        @Override
            public int compare(Object o1, Object o2) {
                Feedback pro1 = (Feedback) o1;
                Feedback pro2 = (Feedback) o2;
                
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) >0) return 1;
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) ==0) {
                    return 0;
                }
                return -1;
                
            }
        };
        Comparator sortByProductNameDecrease = new Comparator() {
        @Override
            public int compare(Object o1, Object o2) {
                Feedback pro1 = (Feedback) o1;
                Feedback pro2 = (Feedback) o2;
                
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) >0) return -1;
                if(pro1.getUsers().getName().compareTo(pro2.getUsers().getName()) ==0) {
                    return 0;
                }
                return 1;
                
            }
        };
        
        
        //tang >0 1
        Comparator sortByRatedStarIncrease = new Comparator() {
        @Override
            public int compare(Object o1, Object o2) {
                Feedback pro1 = (Feedback) o1;
                Feedback pro2 = (Feedback) o2;
                
                if(pro1.getRatedStart()- pro2.getRatedStart() >0) return 1;
                if(pro1.getRatedStart() - pro2.getRatedStart() ==0) {
                    return 0;
                }
                return -1;
                
            }
        };
        Comparator sortByRatedStarDecrease = new Comparator() {
        @Override
            public int compare(Object o1, Object o2) {
                Feedback pro1 = (Feedback) o1;
                Feedback pro2 = (Feedback) o2;
                
                if(pro1.getRatedStart()- pro2.getRatedStart() >0) return -1;
                if(pro1.getRatedStart() - pro2.getRatedStart() ==0) {
                    return 0;
                }
                return 1;
                
            }
        };
        
        List<Feedback> displayList= fb.getListFeedbackByFilter2(UserName, ProductName, Star, Page);
        int numberOfPage = (int)Math.ceil(feedbackList.size()/5);
        switch (Sort) {
            case 1: displayList.sort(sortByUserNameIncrease);break;
            case 2:displayList.sort(sortByUserNameDecrease);break;
            case 3:displayList.sort(sortByProductNameIncrease);break;
            case 4:displayList.sort(sortByProductNameDecrease);break;
            case 5:displayList.sort(sortByRatedStarIncrease);break;
            case 6:displayList.sort(sortByRatedStarDecrease);break;
            default:
        }
        
//        List<Feedback> displayList=null;
//        if(numberOfPage == Page)  displayList = feedbackList.subList((Page-1)*5, feedbackList.size() - (Page-1)*5); 
//        else displayList = feedbackList.subList((Page-1)*5, 5); 
        
        PrintWriter out = response.getWriter();
        String result = "<table id=\"orders\">\n" +
"                            <thead>\n" +
"                                <tr>\n" +
"                                    <th>Contact Name</th>\n" +
"                                    <th>Product ID</th>\n" +
"                                    <th>Product Name</th>\n" +
"                                    <th>Rated Star</th>\n" +
"                                    <th>Comment</th>\n" +
"                                    <th>Status</th>\n" +
"                                    <th></th>\n" +
"                                </tr>\n" +
"                            </thead>\n" +
"                            <tbody id=\"tbody\">";
        for (Feedback feedback : displayList) {
            String statusHTML = "<div style=\"color: green;\">Done</div>";
            if(feedback.getStatus()==0){
                statusHTML="<div style=\"color: red;\">In Progress</div>";
            }
            result +="<tr>\n" +
"                         <td>"+feedback.getUsers().getName()+"</td>\n" +
"                         <td>"+feedback.getProduct().getId()+"</td>\n" +
"                         <td>"+feedback.getProduct().getName()+"</td>\n" +
"                         <td>"+feedback.getRatedStart()+"</td>\n" +
"                         <td>"+feedback.getComment()+"</td>\n" +
"                         <td id=\"statuss-"+feedback.getId()+"\">"+statusHTML+"</td>\n" +
"                         <td><button onclick=\"getDetail('"+feedback.getId()+"')\" type=\"button\" class=\"btn btn-primary\" data-bs-toggle=\"modal\" data-bs-target=\"#myModal\" >View</button></td>\n" +
"                     </tr>";
            
        }
        result += "</tbody>\n" +
"                        </table>\n" +
"                        <div id=\"paging\">\n" +
"                            <div class=\"pagination\">";
        for (int i = 1; i <= numberOfPage; i++) {
            if(i == Page) result += "<span onclick=\"search("+i+")\" class=\"active\">"+i+"</span>";
            else result += "<span onclick=\"search("+i+")\">"+i+"</span>";
        }
        result += "</div>\n" +
"                        </div> </div>";
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
