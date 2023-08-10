/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Users;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import utils.MailSender;

/**
 *
 * @author PhamQuang
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "AddUsersController", urlPatterns = {"/addUsers"})
public class AddUsersController extends HttpServlet {

    static String getAlphaNumericString(int n) {

        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("addUsers.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //Getting account information
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = getAlphaNumericString(8);
        String avatar = "";
        try {
            Part part = req.getPart("avatar");
            //The following directory should be changed for every machine
            String realPath = "E:\\School_projects\\Semester_5\\SWP391\\onlinegundamshop-master\\web\\img";
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            avatar = filename;
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath + "/" + filename);
        } catch (Exception e) {

        }
        if (avatar.equals("")) {
            avatar = "default.png";
        }
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String address = req.getParameter("address");
        String typeIdtemp = req.getParameter("typeId");
        int typeId = 1;
        try {
            typeId = Integer.parseInt(typeIdtemp);
        } catch (NumberFormatException e) {
            System.out.println("AddUsers:" + e.getMessage());
        }
        Users u = new Users();
        if (!u.checkUsersByID(id) && !u.checkUsersByEmail(email) && !u.checkUsersByMobile(mobile)) {
            Users a = new Users(id, name, password, avatar, gender, email, mobile, address, typeId);
            //Sent email here
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
                    + "        <h2>Order Notification</h2>\n"
                    + "        <p>Dear new user,</p>\n"
                    + "        <p>Your account has been created. Here's your account information:</p>\n"
                    + "        <p>ID: <span class=\"password\">" + id + "</span></p>\n"
                    + "        <p>Password: <span class=\"password\">" + password + "</span></p>\n"
                    + "        <p>Please ensure to keep this information confidential and do not share it with anyone.</p>\n"
                    + "        <p>Thank you!</p>\n"
                    + "    </div>\n"
                    + "</body>\n"
                    + "</html>";

            MailSender.send("New account added by admin", htmlContent, email);

            a.add();
            req.setAttribute("data", a);
            req.getRequestDispatcher("completeAddAccount.jsp").forward(req, resp);
        } else {
            req.setAttribute("exist", u.checkUsersByID(id));
            req.setAttribute("exist2", u.checkUsersByEmail(email));
            req.setAttribute("exist3", u.checkUsersByMobile(mobile));
            req.getRequestDispatcher("addUsers.jsp").forward(req, resp);
        }
    }

}
