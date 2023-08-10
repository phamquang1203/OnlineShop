<%-- 
    Document   : blog
    Created on : Jun 23, 2023, 11:44:59 AM
    Author     : PC
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.*" %>
<%@page import="Model.*" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Welcome to Gundarium</title>
        <link rel="stylesheet" href="css/style.css">
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <style>
        .btn btn-success{
            margin-right: 0px;
        }
    </style>
    <body>

        <header style="background-color: white">

            <img src="img/Logo.png" alt="alt" style="width: 10%"/>
            <nav class="navigation">

                <a href="welcome">Home</a>
                <a href="blog.jsp">Blog</a>


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

        <!--add post modal-->

        <!-- Button trigger modal -->
        <!--search -->
        <!--<input class="form-control" type="text" style="width: 200px" placeholder="Search gundam" aria-label="default input example">-->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" >
            Add Blog
        </button>

        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Add Blog</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="add-post-form" action="addblog" enctype="multipart/form-data" method="post">
                            <div class="form-group">
                                <select class="form-control">
                                    <option selected disabled>----Select your category----</option>                                                                   
                                    <option value="1" name="cateID">Customer support</option> 
                                    <option value="2" name="cateID">Product problem</option> 
                                    <option value="3" name="cateID">New product</option> 

                                </select>                
                            </div>
                            <div class="form-group">
                                <label for="title">Title</label>
                                <input type="text" name="title" placeholder="Enter Title" class="form-control"/>                
                            </div>
                            <div class="form-group">
                                <label for="content">Content</label>
                                <textarea type="text" name="Content" style="height: 250px" placeholder="Enter Content" class="form-control"></textarea>           
                            </div>
                            <div class="form-group">
                                <label>Upload your picture: </label>
                                <br/>
                                <input type="file" name="BlogImage">                
                            </div>                           
                        </form>       
                    </div>
                    <div class="modal-footer">
                        <a type="button" href="#" class="btn btn-default" data-dismiss="modal" style="border:1px solid green" >Add</a>
                        <input type="hidden" value="${sessionScope.account.getId()}" name="accountID">
                        <!--                        <input type="submit" class="btn btn-success" value="Add" >-->
                    </div>
                </div>
            </div>
        </div>

        <!--end post modal-->

        <!--start post modal-->
        <main>
            <div class="container">
                <div class="row mt-3">
                    <div class="col-md-3">
                        <div class="list-group">
                            <a href="#" class="list-group-item list-group-item-action active" aria-current="true">
                                All Blog
                            </a>
                            <a href="#" class="list-group-item list-group-item-action" >Customer support</a>
                            <a href="#" class="list-group-item list-group-item-action">Product problem</a>
                            <a href="#" class="list-group-item list-group-item-action">New product</a>
                        </div>
                    </div>
                    <div class="col-md-9" id="post-container">
                        <div class="container text-center" id="loader">
                            <i class = "fa fa-refresh fa-4x fa-spin"></i>
                            <h3 class="mt-2">Loading...</h3>
                        </div>

                        <div class="container-fluid" id="post-container">

                        </div>
                    </div>
                </div>
            </div>
        </main>

        <!--end post modal-->

        <div class="clearfix">
        <nav aria-label="Page navigation">
            <ul class="pagination ">
                <c:forEach var="i" begin="1" end="${requestScope.endP}">
                    <li class="page-item"><a class="page-link" href="paging?index=${i}">${i}</a></li>
                    
                    </c:forEach>
                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                    <li class="page-item"><a class="page-link" href="#">3</a></li>
            </ul>
        </nav>
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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


        <script>
            $(document).ready(function (e) {
                $.ajax({
                    url: "loadposts.jsp",
                    success: function (data, textStatus, jqXHR) {
                        console.log(data);
                        $("#loader").hide();
                        $("#post-container").html(data);
                    }
                });
            });
        </script>
    </body>
</html>
