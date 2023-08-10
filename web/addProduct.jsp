<%-- 
    Document   : register
    Created on : Mar 12, 2023, 3:47:28 PM
    Author     : PhamQuang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
    </head>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        * {
            box-sizing: border-box
        }

        /* Full-width input fields */
        input[type=text], input[type=password] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: #f1f1f1;
        }

        input[type=text]:focus, input[type=password]:focus {
            background-color: #ddd;
            outline: none;
        }

        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }

        /* Set a style for all buttons */
        button {
            background-color: #04AA6D;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
        }

        button:hover {
            opacity:1;
        }

        /* Extra styles for the cancel button */
        .cancelbtn {
            padding: 14px 20px;
            background-color: #f44336;
        }

        /* Float cancel and signup buttons and add an equal width */
        .cancelbtn, .signupbtn {
            float: left;
            width: 50%;
        }

        /* Add padding to container elements */
        .container {
            padding: 16px;
        }

        /* Clear floats */
        .clearfix::after {
            content: "";
            clear: both;
            display: table;
        }

        /* Change styles for cancel button and signup button on extra small screens */
        @media screen and (max-width: 300px) {
            .cancelbtn, .signupbtn {
                width: 100%;
            }
        }
    </style>
    <body>

        <form action="addProduct" method="post" enctype="multipart/form-data" style="border:1px solid #ccc">
            <div class="container">
                <h1>Add new Product</h1>
                <p>Please fill in this form to add new product.</p>
                <hr>
                <label for="productImage">Select image for product image:</label>
                <input type="file" name="productImage" accept="image/png, image/jpeg"><br>
                <c:if test="${noImage == true}">
                    <p style="color: red;">You must add a product image</p>
                </c:if>
                <label for="productType"><b>Product type</b></label>
                <select name="productType" id="type">
                    <option value="SD">SD</option>
                    <option value="HG">HG</option>
                    <option value="MG">MG</option>
                    <option value="PG">PG</option>
                </select>                
                <label for="productName"><b>Product Name</b></label>
                <input type="text" placeholder="Enter Product Name" name="productName" required>

                <label for="productPrice"><b>Price(In VND)</b></label>
                <input type="text" placeholder="Enter Price" name="productPrice" required>
                <c:if test="${isNumber == false}">
                    <p style="color: red;">Invalid Price</p>
                </c:if>
                    
                <label for="productDetail"><b>Detail</b></label>
                <input type="text" placeholder="Enter Product Detail" name="productDetail" required>
                
                <label for="productQuantity"><b>Quantity</b></label>
                <input type="number" name="productQuantity">
         
                <div class="clearfix">
                    <button type="submit" class="signupbtn">Add Product</button>
                </div>
            </div>
        </form>
    </body>
</html>
