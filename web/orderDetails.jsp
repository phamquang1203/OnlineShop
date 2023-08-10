<%-- 
    Document   : orderDetails
    Created on : Jun 9, 2023, 10:12:50 PM
    Author     : PhamQuang
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order details</title>

        <link href="css/orderDetails.css" rel="stylesheet" type="text/css"/>
        <!--font awesome-->
        <script src="https://kit.fontawesome.com/6d68bb11b2.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://kit.fontawesome.com/6d68bb11b2.css" crossorigin="anonymous">
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <%
        String path = request.getContextPath();
    %>
    <body>
        <div class="container">
        <h1>Your Order</h1>
        <table>
            <tr>
                <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Image</td>
                <td style="border: 4mm ridge rgba(211, 220, 50, .6);">ID</td>
                <td style="border: 4mm ridge rgba(211, 220, 50, .6);">ProductName</td>
                <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Quantity</td>
                <td style="border: 4mm ridge rgba(211, 220, 50, .6);">Price</td>
            </tr>
            <c:forEach items="${cartListing}" var="item" varStatus="status">
                <tr>
                    <td style="border: 4mm ridge rgba(211, 220, 50, .6);"><img style="max-width: 10%" src="img/${sessionScope.productListing[status.index].getImage()}" alt="alt"/></td>
                    <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getId()}</td>
                    <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${sessionScope.productListing[status.index].getName()}</td>
                    <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getQuantity()}</td>
                    <td style="border: 4mm ridge rgba(211, 220, 50, .6);">${item.getPrice()}</td>
                </tr>
            </c:forEach>
        </table>
        <h4>Total Cost: ${sessionScope.totalPrice}</h4>
        <br>
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal"  onclick="">Feedback</button>
        <c:if test="${msg != null}"><div style="color: green;">${msg}</div></c:if>
        </div>
        
        
        <c:if test="${feedbacks != null}">
            
            <h2>Your Feedback</h2>
            <div class="feedback-container">
                <c:forEach items="${feedbacks}" var="feedback">
                    <div class="feedback-left">
                        <div>ID: ${feedback.getProduct().getId()}</div>
                        <div>Name: ${feedback.getProduct().getName()}</div>
                        <div>
                            <img src="<%=path%>/img/${feedback.getProduct().getImage()}">
                        </div>
                    </div>
                    <div class="feedback">
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
                        <c:if test="${feedback.getImage().length() != 0}"><img src="<%=path%>/img/${feedback.getImage()}" alt="feedback img"></c:if>
                            <hr>
                        </div>
                </c:forEach>
            </div>
        </c:if>


        <!-- The Modal -->
        <div class="modal" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <h1 style="margin: 20px;">Feedback Detail</h1>

                    <!-- Modal body -->
                    <div id="modal-body" class="modal-body">
                        <form method="POST" action="orderDetails" enctype="multipart/form-data">

                            <div class="" style="display: flex; flex-wrap: wrap"> 
                                <strong style="    margin:10px; color: #555555;">Rating:</strong>

                                <div class="rating"> 
                                    <input type="radio" id="star1" name="rate" value="5" />
                                    <label for="star1"></label> 
                                    <input type="radio" id="star2" name="rate" value="4" />
                                    <label for="star2"></label> 
                                    <input type="radio" id="star3" name="rate" value="3" />
                                    <label for="star3"></label>
                                    <input type="radio" id="star4" name="rate" value="2" />
                                    <label for="star4"></label> 
                                    <input type="radio" id="star5" name="rate" value="1" />
                                    <label for="star5"></label> 
                                </div> 

                                <div class="file-upload">
                                    <div class="file-select">
                                        <!--                                        <div class="file-select-button" id="fileName">Add image Review</div>
                                                                                <div class="file-select-name" id="noFile" style="width: 403px;">No image chosen...</div> -->
                                        <input type="file" name="chooseFile" accept="image/*,.jpg,.jepg,.png" id="chooseFile">
                                    </div>
                                </div>

<!--                                <input style="display: none;" name="orderID" value="${odDetailList.getOrderID()}">
                                <input style="display: none;" name="productID" value="${odDetailList.getProductID()}">-->

                                <textarea rows="4" cols="50" style="    background: #eeeeee;
                                          width: 408px;
                                          height: 73px;" name="txtComment" type="text" placeholder="Enter your review"/></textarea>
                                <input style="
                                       padding: 3px;
                                       float: right;
                                       width: 70px;
                                       border-radius: inherit;
                                       background: #ffa000;
                                       color: #393939;
                                       border-radius: 5px;" 
                                       type="submit" value="Review" />
                            </div>
                        </form>
                        <c:forEach items="${cartListing}" var="item" varStatus="status">
                            <hr>
                            <form method="POST" action="orderDetails" enctype="multipart/form-data">
                                <div>Name: ${sessionScope.productListing[status.index].getName()}</div>
                                <div> <img style="max-width: 10%" src="img/${sessionScope.productListing[status.index].getImage()}" alt="alt"/></div>
                                <div class="" style="display: flex; flex-wrap: wrap"> 
                                    <strong style="    margin:10px; color: #555555;">Rating:</strong>
                                    <div class="rating"> 
                                        <input type="radio" id="star1${item.getId()}" name="rate" value="5" />
                                        <label for="star1${item.getId()}"></label> 
                                        <input type="radio" id="star2${item.getId()}" name="rate" value="4" />
                                        <label for="star2${item.getId()}"></label> 
                                        <input type="radio" id="star3${item.getId()}" name="rate" value="3" />
                                        <label for="star3${item.getId()}"></label>
                                        <input type="radio" id="star4${item.getId()}" name="rate" value="2" />
                                        <label for="star4${item.getId()}"></label> 
                                        <input type="radio" id="star5${item.getId()}" name="rate" value="1" />
                                        <label for="star5${item.getId()}"></label> 
                                    </div> 

                                    <div class="file-upload">
                                        <div class="file-select">
                                            <input type="file" name="chooseFile" accept="image/*,.jpg,.jepg,.png" id="chooseFile">
                                        </div>
                                    </div>

                                    <input style="display: none;" name="productID" value="${sessionScope.productListing[status.index].getId()}">

                                    <textarea rows="4" cols="50" style="    background: #eeeeee;
                                              width: 408px;
                                              height: 73px;" name="txtComment" type="text" placeholder="Enter your review"/></textarea>
                                    <input style="
                                           padding: 3px;
                                           float: right;
                                           width: 70px;
                                           border-radius: inherit;
                                           background: #ffa000;
                                           color: #393939;
                                           border-radius: 5px;" 
                                           type="submit" value="Review" />
                                </div>
                            </form>
                        </c:forEach>       
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
    
    </body>
    <script>
        $('#chooseFile').bind('change', function () {
            var filename = $("#chooseFile").val();
            if (/^\s*$/.test(filename)) {
                $(".file-upload").removeClass('active');
                $("#noFile").text("No file chosen...");
            } else {
                $(".file-upload").addClass('active');
                $("#noFile").text(filename.replace("C:\\fakepath\\", ""));
            }
        });
    </script>
</html>
