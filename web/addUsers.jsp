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
        <title>Adding users</title>
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

        <form action="addUsers" method="post" enctype="multipart/form-data" style="border:1px solid #ccc">
            <div class="container">
                <h1>Sign Up</h1>
                <p>Please fill in this form to create an account.</p>
                <hr>
                <label for="img">Select image for avatar:</label>
                <input type="file" name="avatar" accept="image/png, image/jpeg"><br>
                <label for="id"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="id" required>
                <c:if test="${exist == true}">
                    <p style="color: red;">There is already an account associated with this username! </p>
                </c:if>
                <label for="name"><b>Full Name</b></label>
                <input type="text" placeholder="Enter Full Name" name="name" required>
                <label for="email"><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>
                <c:if test="${exist2 == true}">
                    <p style="color: red;">There is already an account associated with this email! </p>
                </c:if>
                <label for="mobile"><b>Mobile</b></label>
                <input type="text" placeholder="Enter Mobile" name="mobile" required>
                <c:if test="${exist3 == true}">
                    <p style="color: red;">There is already an account associated with this phone number! </p>
                </c:if>
                <label for="gender"><b>Gender:</b></label>
                <select name="gender" id="type">
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                    <option value="nb">NB</option>
                </select><br>
                <label for="address"><b>Address</b></label>
                <input type="text" placeholder="Enter Address" name="address" required>
                <label for="typeId"><b>Account type</b></label>
                <select name="typeId" id="type">
                    <option value="1">Customer</option>
                    <option value="2">Marketer</option>
                    <option value="3">Sales</option>
                    <option value="4">Admin</option>
                </select>
                <div class="clearfix">
                    <button type="submit" class="signupbtn">Create new account</button>
                </div>
            </div>
        </form>
    </body>
</html>
