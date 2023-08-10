/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controller;

import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author PhamQuang
 */
@WebServlet(name="ProductController", urlPatterns={"/ProductController"})
public class ProductController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        
        //Getting productList based on type
        HttpSession session = req.getSession();
        Product p = new Product();
        String type = req.getParameter("id");
        ArrayList<Product> data = p.getListProductByType(type);
        session.setAttribute("data", data);
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    } 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        //Get filter data
        String type1 = req.getParameter("productType1");
        String type2 = req.getParameter("productType2");
        String type3 = req.getParameter("productType3");
        String type4 = req.getParameter("productType4");
        String search = req.getParameter("productSearch");
        Product p = new Product();
        ArrayList<Product> output = new ArrayList<>();
        ArrayList<Product> afterSearch = new ArrayList<>();
        
        //Filter productList by types
        if(type1 != null){
            ArrayList<Product> type1List = p.getListProductByType(type1);
            output.addAll(type1List);
        }
        if(type2 != null){
            ArrayList<Product> type2List = p.getListProductByType(type2);
            output.addAll(type2List);
        }
        if(type3 != null){
            ArrayList<Product> type3List = p.getListProductByType(type3);
            output.addAll(type3List);
        }
        if(type4 != null){
            ArrayList<Product> type4List = p.getListProductByType(type4);
            output.addAll(type4List);
        }
        if (!search.isEmpty()){
            //search item within current output list
            ArrayList<Product> searchList = p.getListProductBySearch(search);
            for(int i = 0; i< searchList.size(); i++){
                for(int j = 0; j< output.size(); j++){
                    if (searchList.get(i).getId().equals(output.get(j).getId())){
                        afterSearch.add(searchList.get(i));
                    }
                }
            }
        }
        
        //remove all duplicates
        if (afterSearch.isEmpty()){
            session.setAttribute("data", output);
        }else{
            session.setAttribute("data", afterSearch);
        }
       
        req.getRequestDispatcher("product.jsp").forward(req, resp);
    }


}
