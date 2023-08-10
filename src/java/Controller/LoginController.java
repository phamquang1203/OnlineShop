/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.*;

public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //Nhan thong tin
        String id = req.getParameter("id");
        String password = req.getParameter("password");

        //Xu li thong tin
        Users u = new Users(id, password);
        boolean check = u.checkUser();
        HttpSession session = req.getSession();
        //Ket qua tra ve
        if (check) {
            //Luu thong tin login vao session
            Users a = u.getUsersByID();
            session.setAttribute("account", a);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.setAttribute("check", check);
            req.setAttribute("id", id);
            req.setAttribute("password", password);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

}
