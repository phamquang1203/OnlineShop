/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Blog;
import Model.Category;
import Model.Users;
import dao.BlogDAO;
import dao.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
@WebServlet(name = "BlogControler", urlPatterns = {"/blog"})
public class BlogControler extends HttpServlet {

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // get no of page
        String indexPage = request.getParameter("index");
        // set index page = 1
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        //get total blog
        Blog b = new Blog();
        int count = b.getTotalBlog();
        int endPages = count / 4;
        if (count % 4 != 0) {
            endPages++;
        }
        //get list blog for one page
        List<Blog> lb = b.pagingBlog(index);
        //get all blog
        //ArrayList<Blog> bloglist = b.getListBlog();
        request.setAttribute("endP", endPages);
        request.setAttribute("bloglist", lb);
        
        // get category of blog
        CategoryDAO cate = new CategoryDAO();
        List<Category> list = cate.getListCategory();
        request.setAttribute("listbb", list);
        request.getRequestDispatcher("blog.jsp").forward(request, response);
    }

}
