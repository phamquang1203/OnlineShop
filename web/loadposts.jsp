<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dao.*" %>
<%@page import="Model.*" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>

<c:forEach  items="${bloglist}" var="b">

    <div class="col-md-6 mt-2">
        <div class="card">
            <img src="img/brake.jpg" class="card-img-top" alt="...">
            <div class="card-body">
                <b>${b.title}</b>
                <p>${b.Content}</p>
                <a href="blogdetail.jsp?ID=${b.ID}" class="btn btn-outline-primary btn-sm">Read more</a>
            </div>
        </div>
    </div>

</c:forEach>



<div class="row">
    <c:forEach  begin="1" end="4">

        <div class="col-md-6 mt-2 " >
            <div class="card">
                <img src="img/brake.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <b>${b.title}</b>
                    <p>${b.Content}</p>
                </div>
                <div class="card-footer text-center">
                    <a href="blogdetail.jsp" class="btn btn-outline-primary btn-sm">Read more</a>
                    <button class="btn btn-outline-primary btn-sm" onclick="likeFunction(this)"><b><i class="fa fa-thumbs-up"></i> Like </b></button>
                    <a href="#" class="btn btn-outline-primary btn-sm"><i class="fa fa-commenting-o"></i><span>Comment</span></a>
                </div>
            </div>
        </div>

    </c:forEach>
</div>




