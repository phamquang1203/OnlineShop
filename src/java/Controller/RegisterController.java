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
import java.util.*;

/**
 *
 * @author PhamQuang
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String repass = req.getParameter("repass");
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
        if (password.trim().equals(repass.trim()) && !u.checkUsersByID(id) && !u.checkUsersByEmail(email) && !u.checkUsersByMobile(mobile)) {
            Users a = new Users(id, name, password, avatar, gender, email, mobile, address, typeId);
            a.add();
            HttpSession session = req.getSession();
            session.setAttribute("data", a);
            req.getRequestDispatcher("completeRegister.jsp").forward(req, resp);
        } else {
            req.setAttribute("pass", password);
            req.setAttribute("repass", repass);
            req.setAttribute("exist", u.checkUsersByID(id));
            req.setAttribute("exist2", u.checkUsersByEmail(email));
            req.setAttribute("exist3", u.checkUsersByMobile(mobile));
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("register.jsp");
    }

}
