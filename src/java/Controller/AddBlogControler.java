/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import static Controller.BlogControler.getAlphaNumericString;
import Model.Blog;
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
import java.sql.Date;

/**
 *
 * @author PC
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "AddBlogControler", urlPatterns = {"/addblog"})
public class AddBlogControler extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ID = getAlphaNumericString(5);
        String title = request.getParameter("title");
        String content = request.getParameter("Content");
        String img = "";
        try {
            Part part = request.getPart("BlogImage");
            //The following directory should be changed for every machine
            String realPath = "E:\\School_projects\\Semester_5\\SWP391\\onlinegundamshop-master\\web\\img";
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            img = filename;
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath + "/" + filename);
        } catch (Exception e) {

        }
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());
        String uploadDate = sqlDate.toString();
        String userID = request.getParameter("id");
        String cateID = request.getParameter("CateID");
        Blog c = new Blog(ID, title, content, uploadDate, uploadDate, userID, cateID, img);
        c.add();
        request.getRequestDispatcher("completeAddBlog.jsp").forward(request, response);
    }

}
