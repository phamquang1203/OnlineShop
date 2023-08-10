
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <a href="product.jsp"></a>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/product.css">
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <header style="background-color: white">

            <img src="img/Logo.png" alt="alt" style="width: 10%"/>
            <nav class="navigation">

                <a href="welcome">Home</a>
                <a href="blog">Blog</a>
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



        <div class="shell" style="display: flex">
            <div class="container col-lg-2 col-md-12">
                <form action="product" method="POST">
                      <input type="checkbox" id="SD" name="productType1" value="SD">
                      <label for="SD">SD</label><br>
                      <input type="checkbox" id="HG" name="productType2" value="HG">
                      <label for="HG">HG</label><br>
                      <input type="checkbox" id="MG" name="productType3" value="MG">
                      <label for="HG">MG</label><br>
                      <input type="checkbox" id="PG" name="productType4" value="PG">
                      <label for="HG">PG</label><br>
                      <br>  
                      <input type="text" id="search" name="productSearch" placeholder="Search by name, ID">
                      <button type="submit">Filter</button>
                </form>
            </div>
            <div class="container col-lg-10 col-md-12">
                <div class="row">
                    <c:forEach items="${sessionScope.data}" var="item">
                        <div class="col-md-3">
                            <div class="wsk-cp-product">
                                <div class="wsk-cp-img"><img src="img/${item.getImage()}" alt="Product" class="img-responsive" /></div>
                                <div class="wsk-cp-text">
                                    <div class="price">
                                        <span>${item.getPrice()}VND</span>
                                    </div>

                                    <div class="description-prod">
                                        <p>${item.getName()}</p>
                                    </div>
                                    <div class="card-footer">
                                        <a href="productDetail?name=${item.getName()}" class="inf"><span class="box-inf" style="text-align: center">More</span><i class="zmdi zmdi-shopping-basket"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <footer>
            <div class="col-lg-4 col-md-12">
                <h2>ĐỊA CHỈ</h2>
                <p>Cơ sở 1: Số 3 đường Cầu Giấy, Hà Nội</p>
                <p>Cơ sở 2: 671 đường Hoàng Hoa Thám, Ba Đình , Hà Nội</p>

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