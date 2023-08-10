<%-- 
    Document   : login
    Created on : Mar 12, 2023, 3:47:09 PM
    Author     : PhamQuang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Login</title>
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
    </head>
    <body>
        <form action="login" method="post" style="border:1px solid #ccc">
            <div class="container">

                <h1>Login</h1>
                <p>Please fill in this form to login.</p>
                <hr>

                <label for="id"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="id" required>

                <label for="psw"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <div class="clearfix">
                    <button type="submit" class="signupbtn">Login</button>
                </div>
                <c:if test="${check == false}">
                    <h4 style="color: red">Invalid login information</h4>
                </c:if>
                <a href="forgetPassword">Forget Password ?</a>
            </div>
        </form>
    </body>
</html>
