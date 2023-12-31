/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.user;

import Model.Users;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import validator.ChangePasswordValidator;

/**
 *
 * @author DW
 */
@WebServlet(name = "ChangePassword", urlPatterns = {"/change-password"})
public class ChangePassword extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

        String url = "changePassword.jsp";
        try {
            HttpSession session = request.getSession();
            Users account = (Users) session.getAttribute("account");
            if (account != null) {
                request.setAttribute("account", account);
            } else {
                url = "login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
        String url = "changePassword.jsp";
        try {
            HttpSession session = request.getSession();
            Users users = (Users) session.getAttribute("account");
            String currentPassword = request.getParameter("currentPassword");
            String newPassword = request.getParameter("newPassword");
            String reNewPassword = request.getParameter("reNewPassword");
            ChangePasswordValidator changePasswordValidator = new ChangePasswordValidator();
            boolean check = true;
            if (!users.getPassowrd().equals(currentPassword)) {
                changePasswordValidator.setPassword("Your Password Not Match!");
                check = false;
            }
            if (!newPassword.equals(reNewPassword)) {
                changePasswordValidator.setNewPassword("Your New Password and RePassword much match!");
                check = false;
            }
            String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
            if (!newPassword.matches(passwordRegex)) {
                changePasswordValidator.setRePassword("Your Password Need At least 1 Uppercase char, 1 Lowercase char, 1 number, 1 Special Char, Length must be > 8");
                check = false;
            }
            if (check) {
                UserDAO userDAO = new UserDAO();
                userDAO.changePassword(newPassword, users.getId());
                url = "details?id=" + users.getId();
            } else {
                //create message here
                request.setAttribute("PasswordValidator", changePasswordValidator);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             request.getRequestDispatcher(url).forward(request, response);
        }
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