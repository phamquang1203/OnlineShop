/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Orders;
import Model.Product;
import Model.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author PhamQuang
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class ChangeProductInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //get information
        String newProductId = req.getParameter("productId");
        String newProductName = req.getParameter("productName");
        String currentImage = req.getParameter("currentImage");
        String newImage = null;
        //get image info
        try {
            Part part = req.getPart("productImage");
            //The following directory should be changed for every machine
            String realPath = "E:\\School_projects\\Semester_5\\SWP391\\onlinegundamshop-master\\web\\img";
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            newImage = filename;
            if(!Files.exists(Paths.get(realPath))){
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath+"/"+filename);
        } catch (Exception e) {

        }

        //get more information
        String newProductPriceTemp = req.getParameter("productPrice");
        Double newProductPrice = null;
        String newProductDetails = req.getParameter("productDetails");
        String newProductQuantityTemp = req.getParameter("productQuantity");
        int newProductQuantity = 0;

        try {
            if(newImage.equals("")){
                newImage = currentImage;
            }
            newProductPrice = Double.parseDouble(newProductPriceTemp);
            newProductQuantity = Integer.parseInt(newProductQuantityTemp);
            Product p = new Product(newProductId, newProductName, newImage, newProductPrice, newProductDetails, newProductQuantity);
            p.updateData(p, newProductId);
        } catch (Exception e) {
            req.setAttribute("valid", false);
        }

        //Refresh details page
        HttpSession session = req.getSession();
        String acc = req.getParameter("currentAccountId");
        Users u = new Users(acc, "");
        ArrayList<Users> data = u.getListUsers();
        for (int i = 0; i < data.size(); i++) {
            if (acc.equals(data.get(i).getId().trim())) {
                Users temp = data.get(i);
                //Marketer handling(must finish post features to be done)
                if (temp.getTypeId() == 2) {
                    Product p = new Product();
                    ArrayList<Product> productList = p.getListProduct();
                    session.setAttribute("productList", productList);
                }

                req.setAttribute("temp", temp);
                req.getRequestDispatcher("details.jsp").forward(req, resp);
            }
        }
    }

}
