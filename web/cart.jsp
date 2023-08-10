<%-- 
    Document   : index
    Created on : May 16, 2023, 11:14:25 AM
    Author     : PhamQuang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <header style="background-color: white">

            <img src="img/Logo.png" alt="alt" style="width: 10%"/>
            <nav class="navigation">

                <a href="welcome">Home</a>
                <c:choose>
                    <c:when test = "${sessionScope.account.getId()!=null}">
                        <a style="color: black">Welcome ${sessionScope.account.getName()}</a>
                        <a href="details?id=${sessionScope.account.getId()}">Check account details</a>
                        <a href="logout.jsp">Sign out</a>
                    </c:when>    
                    <c:otherwise>
                        <a href="login" >Login</a>
                        <a href="register" >Register</a
                    </c:otherwise>
                </c:choose>

            </nav>
            <a href="#"> <img src="img/Cart.png" alt="alt" style="width: 13%"></a>

        </header>
        <div id="carouselExampleIndicators" class="carousel slide pointer-event" data-bs-ride="true">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2" class=""></button>
                <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3" class="active" aria-current="true"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item">
                    <img src="img/hinu.jpg" alt="alt" style="width: 100%"/>
                </div>
                <div class="carousel-item">
                    <img src="img/judge.jpg" alt="alt" style="width: 100%"/>
                </div>
                <div class="carousel-item active">
                    <img src="img/unicorn.jpg" alt="alt" style="width: 100%"/>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="shell cart-page" >
            <div class="container">


                <table>
                    <tr>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th style="text-align: right;">Subtotal</th>
                    </tr>
                    <c:forEach items="${sessionScope.cart}" var="item" varStatus="status">
                        <tr>
                            <td>
                                <div class="cart-info">
                                    <img src="img/${item.getImage()}" alt="alt"/>
                                    <div>
                                        <p>${item.getName()}</p>
                                        <small>Price: ${item.getPrice()}VND</small>
                                        <br>
                                        <form action="removeFromCart" method="POST">
                                            <input type="hidden" name="itemId" value="${item.getId()}">
                                            <input type="hidden" name="orderId" value="${sessionScope.orderID}">
                                            <button type="submit">Remove</button>
                                        </form>
                                    </div>
                                </div>

                            </td>
                            <td>
                                <form action="changeItemAmount" method="POST">
                                    <input type="hidden" name="itemId" value="${item.getId()}">
                                    <input type="hidden" name="orderId" value="${sessionScope.orderID}">
                                    <input type="number" name="amount"value = "${sessionScope.cart2[status.index].getQuantity()}">
                                    <button type="submit">Change Amount</button>

                                </form></td>
                            <td style="text-align: right;">${sessionScope.cart2[status.index].getPrice()}</td>
                        </tr>
                    </c:forEach>
                </table>


                <div class="total-price" style="display: flex;
                     justify-content: flex-end;">
                    <table style="  border-top: 3px solid #ff523b;
                           width:100%;
                           max-width: 400px;">
                        <tr>
                            <td>Subtotal</td>
                            <td style="text-align: right;">${sessionScope.totalPrice}VND</td>
                        </tr>
                        <tr>
                            <td>Tax</td>
                            <td style="text-align: right;">${sessionScope.tax}VND</td>
                        </tr>
                        <tr>
                            <td>Total</td>
                            <td style="text-align: right;">${sessionScope.totalWithTax}VND</td>
                        </tr>
                    </table>
                </div>
                <c:choose>
                    <c:when test = "${sessionScope.account.getId()!=null}">
                        <a href="order" style="background-color: #04AA6D;
                           color: white;
                           padding: 12px;
                           margin: 10px 0;
                           border: none;
                           width: 100%;
                           border-radius: 3px;
                           cursor: pointer;
                           font-size: 17px;">BUY NOW !</a>
                    </c:when>    
                    <c:otherwise>
                        <h1 style="color: red">You have to login to buy items !</h1>
                    </c:otherwise>
                </c:choose>
                <a href="welcome" style="background-color: #04AA6D;
                   color: white;
                   padding: 12px;
                   margin: 10px 0;
                   border: none;
                   width: 100%;
                   border-radius: 3px;
                   cursor: pointer;
                   font-size: 17px;">SELECT MORE !</a>
            </div>
        </div>



        <footer>
            <div class="col-lg-4 col-md-12">
                <h2>ĐỊA CHỈ</h2>
                <p>Cơ sở 1: Số 3 đường Cầu Giấy, Hà Nội</p>
                <p>Cơ sở 2: 671 đường Hoàng Hoa Thám, Ba Đình , Hà Nội</p>
                <p>Cơ sở 3: Số 5 dốc Đốc Ngữ, Ba Đình, Hà Nội</p>
                <p>Cơ sở 4: 50 P Liễu Giai, Ba Đình, Hà Nội, Hanoi, Vietnam</p>
            </div>

            <div class="col-lg-4 col-md-12">
                <h2>LIÊN HỆ</h2>
                <p>SĐT: 095 555 88 88</p>
                <p>Email: phamquang1203@gmail.com</p>
            </div>

            <div class="col-lg-4 col-md-12">
                <h2>THỜI GIAN HOẠT ĐỘNG</h2>
                <p>Thứ 7: từ 14:30 - 18:30</p>
                <p>Chủ Nhật: từ 14:30 - 18:30</p>
            </div>
        </footer>
        <script src="style.css"></script>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    </body>
</html>
