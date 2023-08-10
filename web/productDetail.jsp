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
        <link rel="stylesheet" href="css/productdetail.css">
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <%
        String path = request.getContextPath();
    %>
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
        <div class="container-fluid w-100">
            <div class="products">
                <div class="row container-row p-3 p-lg-5">

                    <div class="col-12 col-md-7 img-container px-0">
                        <div class="row">
                            <div class="tab-content col-10 col-md-9 p-0" id="v-pills-tabContent">
                                <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab" tabindex="0"><img src="img/${product.getImage()}" alt="" class="img-fluid"></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-md-5 information-container p-0 p-md-2 ps-md-3">
                        <h1>${product.getName()}</h1>
                        <p class="description col-md-10 mb-0 py-2">
                            ${product.getDetails()}
                        </p>
                        <div class="rating-container py-2">
                            <i class="fa-solid fa-star fa-xl check"></i>
                            <i class="fa-solid fa-star fa-xl check"></i>
                            <i class="fa-solid fa-star fa-xl check"></i>
                            <i class="fa-solid fa-star fa-xl check"></i>
                            <i class="fa-solid fa-star fa-xl"></i>
                        </div>
                        <div class="categories-container col-md-10">
                            <c:choose>
                                <c:when test = "${product.getQuantity() > 0}">
                                    <p class="mb-0">Availability: In Stock</p>
                                    <h2 class="py-2 mb-0">${product.getPrice()}VND</h2>
                                    <form action="cart" method="post">
                                        <p class="quantity col-md-10 py-2 mb-0 ">Quantity:<input class="form-control col-3 my-4" value="1" type="number" name="quantity"></p>
                                        <input type="hidden" name ="productPrice" value="${product.getPrice()}">
                                        <input type="hidden" name="productId" value="${product.getId()}">
                                        <button type="submit" class="addToCard my-4">ADD TO CART</button>
                                    </form>
                                </c:when>    
                                <c:otherwise>
                                    <p class="mb-0">Availability: Out of Stock</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                        
                </div>
            </div>
        </div>
        <section class="vw-100">


        </section>
        
        <div class="feedback-container">
            <c:forEach items="${feedbacks}" var="feedback">
                <div class="feedback-left">
                    <img src="img/Unknown_person.jpg" alt="avt"/>
                </div>
                <div class="feedback">
                    <h2 class="customer-name">${feedback.getUsers().getName()}</h2>
                    <c:choose>
                        <c:when test="${feedback.getRatedStart()== 1}">
                            <p class="rating">&#9733;&#9734;&#9734;&#9734;&#9734;</p>
                        </c:when>
                        <c:when test="${feedback.getRatedStart()== 2}">
                            <p class="rating">&#9733;&#9733;&#9734;&#9734;&#9734;</p>
                        </c:when>
                        <c:when test="${feedback.getRatedStart()== 3}">
                            <p class="rating">&#9733;&#9733;&#9733;&#9734;&#9734;</p>
                        </c:when>
                        <c:when test="${feedback.getRatedStart()== 4}">
                            <p class="rating">&#9733;&#9733;&#9733;&#9733;&#9734;</p>
                        </c:when>
                        <c:otherwise>
                            <p class="rating">&#9733;&#9733;&#9733;&#9733;&#9733;</p>
                        </c:otherwise>
                    </c:choose>
                    <p class="comment">${feedback.getComment()}</p>
                    <c:if test="${feedback.getImage() != null}"><img src="<%=path%>/uploads/${feedback.getImage()}" alt="Product Image"></c:if>
                    <hr>
                </div>
            </c:forEach>
            
            
<!--            <div class="feedback-left">
                <img src="img/Unknown_person.jpg" alt="avt"/>
            </div>
            <div class="feedback">
                <h2 class="customer-name">John Doe</h2>
                <p class="rating">Rating: &#9733;&#9733;&#9733;&#9733;&#9734;</p>
                <p class="product-name">Product Name: Product ABC</p>
                <p class="product-id">Product ID: 12345</p>
                <p class="comment">This product is amazing! I highly recommend it.</p>
                <img src="product_image.jpg" alt="Product Image">
                <hr>
            </div>-->
        </div>
                        
        


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

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
