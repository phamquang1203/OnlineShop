/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author PhamQuang
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class AddProductController extends HttpServlet {

    static String getAlphaNumericString(int n) {

        // choose a Character random from this String
        String AlphaNumericString ="0123456789";

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
        resp.sendRedirect("addProduct.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //Get Product Image
        String productImage = "";
        try {
            Part part = req.getPart("productImage");
            //The following directory should be changed for every machine
            String realPath = "E:\\School_projects\\Semester_5\\SWP391\\onlinegundamshop-master\\web\\img";
            String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            productImage = filename;
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath + "/" + filename);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //Create productId
        String productType = req.getParameter("productType");
        String productId = productType + getAlphaNumericString(5);
        
        //Get productName
        String productName = req.getParameter("productName");
        
        //Get productPrice
        String productPriceTemp = req.getParameter("productPrice");
        double productPrice = 0;
        boolean isNumber = true;
        try{
            productPrice = Double.parseDouble(productPriceTemp);
        }catch(Exception e){
            isNumber = false;
        }
        
        //Get product Detail
        String productDetail= req.getParameter("productDetail");
        
        //Get product quantity
        String productQuantityTemp = req.getParameter("productQuantity");
        int productQuantity = Integer.parseInt(productQuantityTemp);
        
        if (isNumber && !productImage.isEmpty()){
            Product p = new Product(productId,productName,productImage,productPrice,productDetail,productQuantity);
            p.add();
            req.getRequestDispatcher("addProductComplete.jsp").forward(req, resp);
        }else{
            req.setAttribute("isNumber", isNumber);
            req.setAttribute("noImage", productImage.isEmpty());
            req.getRequestDispatcher("addProduct.jsp").forward(req, resp);

        }
    }

}
