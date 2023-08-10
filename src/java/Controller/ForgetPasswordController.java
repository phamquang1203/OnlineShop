/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MailSender;
import utils.PasswordGenerator;

/**
 *
 * @author PhamQuang
 */

@WebServlet(name = "ForgetPasswordController", urlPatterns = {"/forgetPassword"})
public class ForgetPasswordController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("forgetPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        //get Email
        String email = req.getParameter("email");
        //get account info by email
        Users u = new Users();
        boolean exist = u.checkUsersByEmail(email);
        if (exist) {//if account exist:
            //Sent an email to reset password
            String genPassword = PasswordGenerator.generatePassword(10);

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
                    + "        <h2>Password Notification</h2>\n"
                    + "        <p>Dear User,</p>\n"
                    + "        <p>Your password has been generated. Please find the details below:</p>\n"
                    + "        <p>Password: <span class=\"password\">" + genPassword + "</span></p>\n"
                    + "        <p>Please ensure to keep this password confidential and do not share it with anyone.</p>\n"
                    + "        <p>Thank you!</p>\n"
                    + "    </div>\n"
                    + "</body>\n"
                    + "</html>";

            MailSender.send("OnlineShopping GUNDAM", htmlContent, email);
            UserDAO userDAO = new UserDAO();
            try {
                userDAO.changePassword(genPassword, userDAO.getUsersByEmail(email).getId());
            } catch (SQLException ex) {
                Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
            resp.sendRedirect("login.jsp");
        } else {//else:
            //email invalid
            session.setAttribute("exist", false);
            req.getRequestDispatcher("login.jsp").forward(req, resp);

        }

    }

}
