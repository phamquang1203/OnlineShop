<%-- 
    Document   : index
    Created on : May 16, 2023, 11:14:25 AM
    Author     : PhamQuang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%
            String path = request.getContextPath();
        %>
     <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Welcome to Gundarium</title>
        <link rel="stylesheet" href="<%=path%>/css/style.css">
        <link rel="stylesheet" href="<%=path%>/css/feedback.css">
        <!-- font awesome -->
        <script src="https://kit.fontawesome.com/6d68bb11b2.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://kit.fontawesome.com/6d68bb11b2.css" crossorigin="anonymous">
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <header style="background-color: white">
            
            <img src="img/Logo.png" alt="alt" style="width: 10%"/>
           <nav class="navigation">
                
                <a href="#">Home</a>
                <a href="#">About</a>
                <a href="#">Services</a>
                <a href="#">Contact</a>

                
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
            <a href="cart"> <img src="img/Cart.png" alt="alt" style="width: 13%"></a>
            
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

        <div class="shell" >
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div>
                            <h2>FILTER:</h2>
                            <label>Contact Name:</label>
                            <input id="userName" name="userName">
                            <br><br>
                            <label>Product Name:</label>
                            <input id="productName" name="productName">
                            <br><br>
                            <label>Rated Star:</label> <br>
                            <input class="star" name="star" type="radio" value="5">
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <br>
                            <input class="star" name="star" type="radio" value="4">
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                            <br>
                            <input class="star" name="star" type="radio" value="3">
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                            <br>
                            <input class="star" name="star" type="radio" value="2">
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #bcbcb5;"></i>
                            <br>
                            <input class="star" name="star" type="radio" value="1">
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                                <i class="fa-solid fa-star" style="color: #f7e400;"></i>
                            <br><br>
                            <button type="button" id="search" onclick="search(1)" class="btn btn-primary">Search</button>

                            <hr>
                            <h2>SORT:</h2>
                            Contact Name: 
                                <input class="sort" name="sort" type="radio" value="5" onclick="search(1)"><label>Increase</label>
                                <input class="sort" name="sort" type="radio" value="4" onclick="search(1)"><label>Decrease</label>
                                <br>
                            Product Name:
                                <input class="sort" name="sort" type="radio" value="3" onclick="search(1)"><label>Increase</label>
                                <input class="sort" name="sort" type="radio" value="2" onclick="search(1)"><label>Decrease</label>
                                <br>
                            Rated Star:
                                <input class="sort" name="sort" type="radio" value="1" onclick="search(1)"><label>Increase</label>
                                <input class="sort" name="sort" type="radio" value="6" onclick="search(1)"><label>Decrease</label>
                        </div>
                    </div>
                    <div class="col-md-8" id="right">
                        <h1>Feedbacks List</h1>
                        <table id="orders">
                            <thead>
                                <tr>
                                    <th>Contact Name</th>
                                    <th>Product ID</th>
                                    <th>Product Name</th>
                                    <th>Rated Star</th>
                                    <th>Comment</th>
                                </tr>
                            </thead>
                            <tbody id="tbody">
                                <c:forEach items="${feedbackList}" var="feedback">
                                    <tr>
                                        <td>${feedback.getUserName()}</td>
                                        <td>${feedback.getProductID()}</td>
                                        <td>${feedback.getProductName()}</td>
                                        <td>${feedback.getRatedStar()}</td>
                                        <td>${feedback.getComment()}</td>
                                    </tr>
                                </c:forEach>
                                
                            </tbody>
                        </table>
                        <div id="paging">
                            <div class="pagination">
                                <c:forEach begin="1" end="${numberOfPage}" step="1" var="stepValue">
                                    <c:choose>
                                        <c:when test="${stepValue == currentPage}">
                                            <span onclick="search('${stepValue}')" class="active">${stepValue}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span onclick="search('${stepValue}')">${stepValue}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
            </div>
        </div>



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="<%=path%>/js/feedback.js"></script>
        <script type="module" src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
    </body>
</html>


