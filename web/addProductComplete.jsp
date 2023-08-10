<%-- 
    Document   : completeRegister
    Created on : May 16, 2023, 1:06:55 PM
    Author     : PhamQuang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Add product complete !</title>
    </head>
    <body>
        <h1>Product created !</h1>
        <h3>You will be redirect to details page in 5 seconds</h3>
        <meta http-equiv="refresh" content="5; url = details?id=${sessionScope.account.getId()}" />
    </body>
</html>
