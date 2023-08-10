<%-- 
    Document   : details
    Created on : Mar 12, 2023, 5:11:12 PM
    Author     : PhamQuang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.*" %>
<%@page import="Model.Users" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>User Details</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
            crossorigin="anonymous"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"
        ></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <a href="welcome">Back to home</a>
        <div class="container-fluid" style="background-color: black; border-radius: 20px; border: 20px; width: 80%; margin-top: 20px; padding: 20px; color: whitesmoke">
            <form action="changeUserDetail" method="POST" enctype="multipart/form-data">
                <div id="avatar">
                    <img src="img/${temp.getAvatar()}" style="width: 20%; display: block; margin: auto;"/>
                    <input type="file" name="accountImage" accept="image/png, image/jpeg">

                </div>
                <input type="hidden" value="${temp.getId()}" name="accountId">
                <h4>Name: <input type="text" value="${temp.getName()}" name="accountName"></h4>
                <h4>Gender:
                    <c:choose>
                        <c:when test = "${temp.getGender()=='male'}">
                            <select name="accountGender" id="type">
                                <option value="male" selected>Male</option>
                                <option value="female">Female</option>
                                <option value="nb">NB</option>
                            </select>
                        </c:when>
                        <c:when test = "${temp.getGender()=='female'}">
                            <select name="accountGender" id="type">
                                <option value="male">Male</option>
                                <option value="female" selected>Female</option>
                                <option value="nb">NB</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select name="accountGender" id="type">
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="nb" selected>NB</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </h4>
                <h4>Email: ${temp.getEmail()}<input type="hidden" value="${temp.getEmail()}" name="accountEmail"></h4>
                <h4>Mobile: <input type="text" value="${temp.getMobile()}" name="accountMobile"></h4>
                <h4>Address: <input type="text" value="${temp.getAddress()}" name="accountAddress"></h4>
                <button type="submit">Change account info</button>
            </form>

            <c:if test="${temp.getTypeId() == 1}">
                <h4>Welcome ${temp.getName()} Customer !</h4>
                <h4>Your orders: </h4>
                <table>
                    <tr>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">ID</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Date</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Status</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">OrderID</td>
                    </tr>
                    <c:forEach items="${orderList}" var="item">
                        <tr>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">
                                <c:choose>
                                    <c:when test="${item.getStatus() == 'Submitted'}">
                                        <form action="confirmOrder" method="POST">
                                            <input type="text" name="confirmationId">
                                            <input type="hidden" name="currentAccountId" value="${temp.getId()}">
                                            <button type="submit">Confirm Order</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        ${item.getId()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getDate()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getStatus()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><a href="orderDetails?id=${item.getOrderId()}">${item.getOrderId()}</a></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${sessionScope.falseId != null}">
                    <h4 style="color: red">${sessionScope.falseId}</h4>
                </c:if>
            </c:if>
            <c:if test="${temp.getTypeId() == 2}">
                <h4>You are a marketer</h4>
                <a href="feedbackmkt">Show feedback list</a>
                <h4>List of Products </h4>
                <table>
                    <tr>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">ID</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Name</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Image</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Price</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Details</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Quantity</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);"></td>
                    </tr>
                    <c:forEach items="${productList}" var="item" varStatus="status">
                        <tr>
                        <form action="changeProductInfo" method="POST" enctype="multipart/form-data">
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getId()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><input type="text" value="${item.getName()}" name="productName"></td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">
                                <img src="img/${item.getImage()}" alt="alt"/>
                                <input type="file" name="productImage" accept="image/png, image/jpeg">
                                <input type="hidden" value="${item.getImage()}" name="currentImage">
                                <input type="hidden" value="${item.getId()}" name="productId">
                                <input type="hidden" value="${sessionScope.account.getId()}" name="currentAccountId">
                            </td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><input type="text" value="${item.getPrice()}" name="productPrice"></td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><input type="text" value="${item.getDetails()}" name="productDetails"></td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><input type="text" value="${item.getQuantity()}" name="productQuantity"></td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><button type="submit">Change Product Info</button></td>
                        </form>
                        </tr>
                    </c:forEach>
                </table>
                <a href="addProduct">Add new Product</a><br><br>
                <c:if test="${valid == false}">
                    <h4>Change product fail because a variable is of wrong type</h4>
                </c:if>
            </c:if>
            <c:if test="${temp.getTypeId() == 3}">
                <h4>You are a sales person</h4>
                <h4>List of orders</h4>
                <table>
                    <tr>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Order ID</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Ordered Date</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Customer name</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Customer email</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Customer mobile</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Status</td>
                    </tr>
                    <c:forEach items="${orderList}" var="item" varStatus="status">
                        <tr>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><a href="orderDetails?id=${item.getOrderId()}">${item.getOrderId()}</a></td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getDate()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${sessionScope.buyerList[status.index].getName()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${sessionScope.buyerList[status.index].getEmail()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${sessionScope.buyerList[status.index].getMobile()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">
                                <form action="changeStatus" method="POST">
                                    <input type="hidden" value="${item.getOrderId()}" name="currentOrderId">
                                    <input type="hidden" value="${sessionScope.account.getId()}" name="currentAccountId">
                                    <c:choose>
                                        <c:when test = "${item.getStatus() == 'Submitted'}">
                                            <select name="orderStatus" id="type">
                                                <option value="Submitted" selected>Submitted</option>
                                                <option value="Confirmed">Confirmed</option>
                                                <option value="Delivering">Delivering</option>
                                                <option value="Success">Success</option>
                                                <option value="Cancelled">Cancelled</option>
                                            </select>
                                        </c:when>
                                        <c:when test = "${item.getStatus() == 'Delivering'}">
                                            <select name="orderStatus" id="type">
                                                <option value="Submitted">Submitted</option>
                                                <option value="Confirmed">Confirmed</option>
                                                <option value="Delivering" selected>Delivering</option>
                                                <option value="Success">Success</option>
                                                <option value="Cancelled">Cancelled</option>
                                            </select>
                                        </c:when>  
                                        <c:when test = "${item.getStatus() == 'Success'}">
                                            <select name="orderStatus" id="type">
                                                <option value="Submitted">Submitted</option>
                                                <option value="Confirmed">Confirmed</option>
                                                <option value="Delivering">Delivering</option>
                                                <option value="Success" selected>Success</option>
                                                <option value="Cancelled">Cancelled</option>
                                            </select>
                                        </c:when>
                                        <c:when test = "${item.getStatus() == 'Cancelled'}">
                                            <select name="orderStatus" id="type">
                                                <option value="Submitted">Submitted</option>
                                                <option value="Confirmed">Confirmed</option>
                                                <option value="Delivering">Delivering</option>
                                                <option value="Success">Success</option>
                                                <option value="Cancelled" selected>Cancelled</option>
                                            </select>
                                        </c:when>
                                        <c:when test = "${item.getStatus() == 'Confirmed'}">
                                            <select name="orderStatus" id="type">
                                                <option value="Submitted">Submitted</option>
                                                <option value="Confirmed" selected>Confirmed</option>
                                                <option value="Delivering">Delivering</option>
                                                <option value="Success">Success</option>
                                                <option value="Cancelled">Cancelled</option>
                                            </select>
                                        </c:when>
                                    </c:choose>
                                    <button type="submit">Change status</button>

                                </form></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${temp.getTypeId() == 4}">
                <h4>Welcome admin ${temp.getName()}</h4>
                <a href="addUsers">Add new user</a>
                <h4>List of users</h4>
                <table>
                    <tr>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">ID</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Name</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Avatar</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Gender</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Email</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Mobile</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Address</td>
                        <td style="border: 4mm ridge rgba(211, 220, 50, .6);">TypeID</td>
                    </tr>
                    <c:forEach items="${userList}" var="item">
                        <tr>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getId()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getName()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><img src="img/${item.getAvatar()}" alt="alt"/></td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getGender()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getEmail()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getMobile()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getAddress()}</td>
                            <td style="border: 4mm ridge rgba(211, 220, 50, .6);">
                                <form action="changeAccountType" method="POST">
                                    <input type="hidden" value="${sessionScope.account.getId()}" name="currentAccountId">
                                    <input type="hidden" value="${item.getId()}" name="changeAccountId">
                                    <c:choose>
                                        <c:when test = "${item.getTypeId()==1}">
                                            <select name="typeId" id="type">
                                                <option value="1" selected>Customer</option>
                                                <option value="2">Marketer</option>
                                                <option value="3">Sales</option>
                                                <option value="4">Admin</option>
                                            </select>
                                        </c:when>
                                        <c:when test = "${item.getTypeId()==2}">
                                            <select name="typeId" id="type">
                                                <option value="1">Customer</option>
                                                <option value="2" selected>Marketer</option>
                                                <option value="3">Sales</option>
                                                <option value="4">Admin</option>
                                            </select>
                                        </c:when>   
                                        <c:when test = "${item.getTypeId()==3}">
                                            <select name="typeId" id="type">
                                                <option value="1">Customer</option>
                                                <option value="2">Marketer</option>
                                                <option value="3" selected>Sales</option>
                                                <option value="4">Admin</option>
                                            </select>
                                        </c:when>  
                                        <c:otherwise>
                                            <select name="typeId" id="type">
                                                <option value="1">Customer</option>
                                                <option value="2">Marketer</option>
                                                <option value="3">Sales</option>
                                                <option value="4" selected>Admin</option>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>

                                    <button type="submit">Change account type</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>

            <a href="change-password">Change password</a>
        </div>
        <c:if test="${temp.getTypeId() == 4}">
            <div class="container-fluid" style="background-color: black; border-radius: 20px; border: 20px; width: 80%; margin-top: 20px; padding: 20px; color: whitesmoke">
                <h1>Admin dashboard</h1>
                <script type="text/javascript">
                    google.charts.load("current", {packages: ["corechart"]});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Types of order', 'Number of orders'],
                            ['Success', ${sessionScope.successOrdersAmount}],
                            ['Confirmed', ${sessionScope.confirmedOrdersAmount}],
                            ['Cancelled', ${sessionScope.cancelledOrdersAmount}],
                        ]);

                        var options = {
                            title: 'Number of orders',
                            pieHole: 0.4,
                        };

                        var chart = new google.visualization.PieChart(document.getElementById('orderChart'));
                        chart.draw(data, options);
                    }
                </script>

                <div id="orderChart" style="width: 450px; height: 250px; background: black"></div>
                <script type="text/javascript">
                    google.charts.load("current", {packages: ["corechart"]});
                    google.charts.setOnLoadCallback(drawChart);
                    function drawChart() {
                        var data = google.visualization.arrayToDataTable([
                            ['Types of product', 'Revenue'],
                            ['SD', ${sessionScope.sdRevenue}],
                            ['HG', ${sessionScope.hgRevenue}],
                            ['MG', ${sessionScope.mgRevenue}],
                            ['PG', ${sessionScope.pgRevenue}],
                        ]);

                        var options = {
                            title: 'Total revenue',
                            pieHole: 0.4,
                        };

                        var chart = new google.visualization.PieChart(document.getElementById('revenueChart'));
                        chart.draw(data, options);
                    }
                </script>
                <div id="revenueChart" style="width: 450px; height: 250px; background: black"></div>
            </div>
        </c:if>

    </body>
</html>
