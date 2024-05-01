<%-- 
    Document   : newheader
    Created on : May 14, 2023, 10:57:26 PM
    Author     : Ding L
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <div class="container-fluid px-0">	<header>	<div class="shape"></div>	<!-- Navbar -->	
                <nav class="navbar fixed-top navbar-expand-lg navbar-transparent"> 
                    <a class="navbar-brand" href="TheBeast.jsp">The Beast</a> 
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> 
                        <span class="navbar-toggler-icon"></span> 
                    </button> 
                    <div class="collapse navbar-collapse" id="navbarSupportedContent"> 
                        <ul class="navbar-nav mr-auto"> <li class="nav-item active">
                                <a class="nav-link" href="TheBeast.jsp">HOME 
                                    <span class="sr-only">(current)</span></a> </li>
                            <li class="nav-item"> <a class="nav-link" href="Basketball.jsp">Basketball</a> </li> 
                            <li class="nav-item"> <a class="nav-link" href="Casual.jsp">Casual</a> </li> 
                            <li class="nav-item"> <a class="nav-link" href="Running.jsp">Running</a> </li> 
                            <li class="nav-item"> <a class="nav-link" href="Training.jsp">Training</a> </li> 


                        </ul> 
                        <%-- Search bar --%>
                        <div class="row d-flex justify-content-center mt-100">
                            <div class="col-md-4">
                                <form  action="SearchResult" method="POST" class="flex-nowrap col ml-auto footer-subscribe p-0">
                                    <input type="text" class="form-control" placeholder="Search &hellip;" name="productName">
                                    <button type="submit" class="btn btn-theme bg-orange"><i class="fa fa-search p-0"></i></button>
                                </form>

                            </div>
                        </div>

                    </div>	
                    <div class="button-div">  
                        <form action="DisplayCart" method="POST">
                            <input type="hidden" value="${member.memberID}" name="member"/>
                            <input type="hidden" value="${cart.cartID}" name="cart"/>
                            <button class="cart-button"><i class="fa fa-shopping-cart"></i></button>
                        </form>
                        <form action="DisplayOrder" method="POST">
                            <input type="hidden" value="${cart.cartID}" name="cart"/>
                            <button style="margin:10px;" class="cart-button"><i class='fas fa-clipboard-check'></i></button>
                        </form>
                         <form method="POST" action="Logout">
                        <button style="margin:15px;" class="login-button" type="submit">Logout</button>
                        </form>
                    </div>
                </nav>
         </div>
    </body>
</html>
