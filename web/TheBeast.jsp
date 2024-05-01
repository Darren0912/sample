<%-- 
    Document   : assignment
    Created on : May 4, 2023, 1:39:20 PM
    Author     : User
--%>

<%@page import="javax.xml.bind.DatatypeConverter"%>
<%@ page import="java.io.ByteArrayInputStream" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="session" class="Model.Member" />
<jsp:useBean id="product" scope="session" class="Model.Product" />
<jsp:useBean id="cart" scope="session" class="Model.ShoppingCart" />
<jsp:useBean id="productDA" scope="session" class="Model.ProductDA" />
<%@page import="Model.Product, java.util.*" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% List<Product> productList = (List<Product>) session.getAttribute("productList");%> 
<!DOCTYPE html>
<html>
    <head>

        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Beast</title>
    </head>
    <body>



        <header>

            <div>
                <div id="indicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#indicators" data-slide-to="0" class="active"></li>
                        <li data-target="#indicators" data-slide-to="1"></li>
                        <li data-target="#indicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
                        <!-- Slide One - Set the background image for this slide in the line below -->


                        <a href="Casual.jsp"<div class="carousel-item active" style="background-image:url('IMG/slideshow1(casual).jpg')">
                                <div class="carousel-caption d-none d-md-block">

                                </div></a>

                        <!-- Slide Two - Set the background image for this slide in the line below -->
                        <a href="Basketball.jsp"  <div class="carousel-item" style="background-image: url('IMG/slideshow2(basketball).jpg')">
                                <div class="carousel-caption d-none d-md-block">
                                </div>

                        </a>


                        <!-- Slide Three - Set the background image for this slide in the line below -->
                        <a href="Training.jsp"<div class="carousel-item" style="background-image: url('IMG/slideshow3(training).jpg')">
                                <div class="carousel-caption d-none d-md-block">

                                </div></a>
                    </div>

                    <a class="carousel-control-prev" href="#indicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#indicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>

                </div>  

        </header>


        <!-- Page Content -->
        <section class="py-5">
            <div class="container">
                <h1 class="font-weight-light">The Beast</h1>
                <p class="lead">Welcome ${member.username} to The Beast Shoes Online Shopping !!</p>

                <script>
                    $(function () {
                        $(document).scroll(function () {
                            var $nav = $("#mainNavbar");
                            $nav.toggleClass("scrolled", $(this).scrollTop() > $nav.height());
                        });
                    });
                </script>
            </div>
        </section>



        <%@include file="include/header.jsp" %>

        <!-- ./ end of navbar -->


        <div class="container">	
            <div class="row">	
                <div class="col-md-6 left-side">	
                    <img src="https://i.imgur.com/jrRBTai.png" class="w-100">	
                </div>	
                <div class="col-md-6 right-side">	
                    <h1>SPORTS SHOES</h1>	
                    <p>Limited Promotion 50%</p>	
                    <div class="text-center">	
                        <a href="Running.jsp" ><button class="btn order-button">ORDER NOW</button>	</a>
                    </div>	
                </div>	               
            </div>	
        </div>	







        <div class="container d-flex justify-content-center mt-50 mb-50">
            <div class="row">

                <%  int[] productArr = new int[productList.size()];
                for (int i = 0; i < 6; i++) {%>

                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions">


                                <a href="">  <img src="data:image/jpg;base64,<%= productList.get(i).getByte64Image()%>" class="card-img img-fluid" width="96" height="350" alt=""></a>
                            </div>
                        </div>
                        <div class="card-body bg-light text-center">
                            <div class="mb-2">

                                <h6 class="font-weight-semibold mb-2">

                                    <a href="" class="text-default mb-2" data-abc="true"><%=productList.get(i).getProductName()%></a>
                                </h6>
                                <a href="" class="text-muted" data-abc="true"><%=productList.get(i).getCategory()%></a>
                            </div>
                            <h3 class="mb-0 font-weight-semibold">RM<%= productList.get(i).getPrice()%></h3>
                            <form action="ViewProduct" method="POST">
                                <input type="hidden" value="<%=productList.get(i).getProductID()%>" name="product" />

                                <input type="submit" class="btn bg-cart" value="View Product Details">
                            </form>
                            <br/>
                        </div>
                    </div>
                </div>


                <% }%>    
                <br/>
            </div> 
        </div>
    </header>	

</div>

<footer id="footer"> 
    <div class="footer-newsletter"> 
        <div class="container">
            <div class="row"> 
                <div class="col-lg-6"> 



                </div>
            </div> 
        </div>
    </div>
    <div class="footer-top"> 
        <div class="container"> 
            <div class="row"> 
                <div class="col-lg-3 col-md-6 footer-links"> 
                    <h4>Useful Links</h4> 
                    <ul>
                        <li>
                            <i class="bx bx-chevron-right"></i> 
                            <a href="TheBeast.jsp">Home</a>
                        </li> 
                        <li>
                            <i class="bx bx-chevron-right">

                            </i> 
                            <a href="Basketball.jsp">Basketball</a>
                        </li> 
                        <li>
                            <i class="bx bx-chevron-right">

                            </i> 
                            <a href="Casual.jsp">Casual</a>
                        </li> 
                        <li>
                            <i class="bx bx-chevron-right">

                            </i> 
                            <a href="Running.jsp">Running</a>
                        </li> 
                        <li>
                            <i class="bx bx-chevron-right">

                            </i> 
                            <a href="Training.jsp">Training</a>
                        </li> 
                    </ul> 
                </div> 

                <div class="col-lg-3 col-md-6 footer-contact"> 
                    <h4>Contact Us</h4> 
                    <p> University 
                        <br> Tunku Abdul Rahman University Malaysia Technology
                        <br> TARUMT
                        <br>
                        <br> 
                        <strong>Phone:</strong> +60187920304<br> 
                        <strong>Email:</strong> infoTheBeast@gmail.com<br> 
                    </p> 
                </div> 
                <div class="col-lg-3 col-md-6 footer-info">
                    <h3>About The Beast</h3> 
                    <p>The Beast Online shoes shopping hope you enjoy the Website with us!</p> 

                </div>
            </div>
        </div>
    </div>
   <%@include file="include/footer.jsp" %>
</footer>

</body>
<style>
    body{
        background-image:url(background1.0.jpg);

        background-repeat: no-repeat;
        background-attachment: fixed;
        height: 100%;
        background-position: center;
        background-size: cover;


    }
</style>
<style>

    .footer-subscribe input[type=text] {
        border: 1px solid #ff3514;
        border-radius: 55px;
        font-size: 12px;
        padding: 12px 40% 12px 20px;
        background: transparent;
        width: 98%;
        box-shadow: none !important;
    }



    a, button[type="submit"], input[type=text] {
        color: #333;
        text-decoration: none;
        -webkit-transition: all 400ms ease-in-out;
        -moz-transition: all 400ms ease-in-out;
        -o-transition: all 400ms ease-in-out;
        -ms-transition: all 400ms ease-in-out;
        transition: all 400ms ease-in-out;
    }

    .footer-subscribe .btn-theme {
        position: absolute;
        top: 0;
        height: 100%;
        right: 0;
    }

    .btn-theme:hover {
        box-shadow: none;
    }

    .btn btn-theme bg-orange {
        color: #FFF !important;
        padding: 0.5rem 1.9rem;
        font-weight: 400;
        font-size: 0.875rem;
        display: inline-block;
        display: inline-flex;
        outline: none;
        border: none;
        cursor: pointer;
        overflow: hidden;
        z-index: 2;
        align-items: center;
        position: relative;
        cursor: pointer;
        -webkit-box-shadow: 0px 14px 47px 0px rgba(28, 28, 28, 0.24);
        -moz-box-shadow: 0px 14px 47px 0px rgba(28, 28, 28, 0.24);
        box-shadow: 0px 14px 47px 0px rgba(28, 28, 28, 0.24);
        -webkit-border-radius: 70px;
        -moz-border-radius: 70px;
        border-radius: 70px; 
    }

    .bg-orange {
        background: #ff3514;
    }
</style>                 
<style>

    .button-div{

        width:350px;
        height:60px;


        display:flex;
        justify-content:center;
        align-items:center;
        border-radius:7px;
        padding:20px;
    }

    .cart-button{
        width:100%;
        height:55px;
        margin-right:7px;
        border:none;
        border-radius:5px;
        background-color:#e9ecf0;
        font-size:17px;
        font-weight:600;
        cursor:pointer;
    }

    .cart-button:hover{

        background-color:#e9ecf0d1;
    }
    .login-button{
        width:100%;
        height:55px;
        border:none;
        border-radius:5px;
        font-size:17px;
        font-weight:600;
        color:#fff;
        background-color:#1859c9;
        cursor:pointer;
    }


    .login-button:hover{

        background-color:#1859c9db;
    }



</style>
<style>



    .left-side,
    .right-side {
        height: 75vh
    }

    .navbar-transparent {
        background: transparent
    }

    .navbar-nav>li>a {
        color: #fff
    }

    .navbar-nav>li:hover {
        border-bottom: 1px solid #fff
    }

    .navbar-nav>.active {
        border-bottom: 1px solid #fff
    }

    .navbar-brand {
        font-family: 'Allerta Stencil', sans-serif;
        color: #83ff00
    }

    .navbar-brand:hover {
        color: #fff
    }

    .left-side img {
        padding-top: 100px
    }



    .right-side {
        padding-top: 150px
    }

    .right-side h1 {
        font-family: 'Allerta Stencil', sans-serif;
        font-size: 2.4em;
        text-align: center
    }

    .right-side p {
        font-size: 1.5em;
        text-align: center;
        font-weight: bold
    }

    .btn.order-button {
        background: transparent;
        border: 2px solid #83ff00;
        color: #83ff00;
        border-radius: 30%;
        transition: 0.7s
    }

    .btn.order-button:hover {
        border: 2px solid #ce0000;
        color: #ce0000
    }

</style>

<style>
    .carousel-item {
        height: 65vh;
        min-height: 350px;
        background: no-repeat center center scroll;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
    }

    #mainNavbar{
        padding-left:  50px;
        padding-top: 20px;
    }

    .navbar-dark .navbar-brand{
        font-family: 'Source Serif Pro', serif;

    }

    .navbar-nav .nav-link{
        font-family: 'Source Serif Pro', serif;

    }

    .display-4{
        font-family: 'Source Serif Pro', serif;
    }

    .lead{
        font-family: 'Source Serif Pro', serif;
    }

    .navbar.scrolled {
        background: rgb(34, 31, 31);
        transition: background 500ms;
    }

    .font-weight-light{
        font-family: 'Source Serif Pro', serif;
    }



</style>

<style>


    body {
        margin: 0;
        font-family: Roboto,-apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
        font-size: .8125rem;
        font-weight: 400;
        line-height: 1.5385;
        color: #333;
        text-align: left;
        background-color: white;
    }

    .mt-50{

        margin-top: 50px;
    }

    .mb-50{

        margin-bottom: 50px;
    }



    .card {
        position: relative;
        display: -ms-flexbox;
        display: flex;
        -ms-flex-direction: column;
        flex-direction: column;
        min-width: 0;
        word-wrap: break-word;
        background-color: #fff;
        background-clip: border-box;
        border: 1px solid rgba(0,0,0,.125);
        border-radius: .1875rem;
    }

    .card-img-actions {
        position: relative;
    }
    .card-body {
        -ms-flex: 1 1 auto;
        flex: 1 1 auto;
        padding: 1.25rem;
        text-align: center;

    }

    .card-img{

        width: 350px;
    }

    .star{
        color: red;
    }

    .bg-cart {
        background-color:orange;
        color:#fff;
    }

    .bg-cart:hover {

        color:#fff;
    }

    .bg-buy {
        background-color:green;
        color:#fff;
        padding-right: 29px;
    }
    .bg-buy:hover {

        color:#fff;
    }

    a{

        text-decoration: none !important;
    }

</style>

<style>
    #footer{background: #404040;
            padding: 0 0 30px 0;
            color: #fff;font-size: 14px}
    #footer 
    .footer-newsletter{padding: 50px 0;
                       background: #404040}
    #footer 
    .footer-newsletter h4{font-size: 24px;
                          margin: 0 0 20px 0;
                          padding: 0;
                          line-height: 1;
                          font-weight: 600}
    #footer 
    .footer-newsletter form{margin-top: 30px;
                            background: #fff;
                            padding: 6px 10px;
                            position: relative;
                            border-radius: 50px}
    #footer 
    .footer-newsletter form input[type="email"]
    {border: 0;
     padding: 8px;
     width: calc(100% - 140px)}

    #footer 
    .footer-newsletter form input[type="submit"]
    {position: absolute;
     top: 0;right: 0;
     bottom: 0;border: 0;
     background: none;
     font-size: 16px;
     padding: 0 30px;
     margin: 3px;
     background: #e96b56;
     color: #fff;
     transition: 0.3s;
     border-radius: 50px}

    #footer 
    .footer-newsletter form input[type="submit"]
    :hover{background: #e6573f}

    #footer 
    .footer-top{background: #3b3b3b;
                border-top: 1px solid #474747;
                border-bottom: 1px solid #474747;
                padding: 60px 0 30px 0}

    #footer 
    .footer-top 
    .footer-info{margin-bottom: 30px}

    #footer 
    .footer-top 
    .footer-info h3
    {font-size: 18px;
     margin: 0 0 20px 0;
     padding: 2px 0 2px 0;
     line-height: 1;
     font-weight: 700}

    #footer 
    .footer-top 
    .footer-info 
    p{font-size: 14px;
      line-height: 24px;
      margin-bottom: 0;
      font-family: "Raleway", sans-serif;
      color: #fff}

    #footer 
    .footer-top 
    .social-links a
    {font-size: 18px;
     display: inline-block;
     background: #545454;
     color: #fff;
     line-height: 1;
     padding: 8px 0;
     margin-right: 4px;
     border-radius: 50%;
     text-align: center;
     width: 36px;
     height: 36px;
     transition: 0.3s}

    #footer 
    .footer-top 
    .social-links a:hover
    {background: #e96b56;
     color: #fff;
     text-decoration: none}

    #footer 
    .footer-top h4
    {font-size: 16px;
     font-weight: bold;
     color: #fff;
     text-transform: uppercase;
     position: relative;
     padding-bottom: 12px}

    #footer 
    .footer-top 
    .footer-links{margin-bottom: 30px}

    #footer 
    .footer-top 
    .footer-links 
    ul{list-style: none;
       padding: 0;
       margin: 0}

    #footer 
    .footer-top 
    .footer-links 
    ul i{padding-right: 2px;
         color: #ec7f6d;
         font-size: 18px;
         line-height: 1}

    #footer 
    .footer-top 
    .footer-links 
    ul li{padding: 10px 0;
          display: flex;
          align-items: center}

    #footer 
    .footer-top 
    .footer-links ul 
    li:first-child{padding-top: 0}

    #footer 
    .footer-top 
    .footer-links 
    ul a{color: #fff;
         transition: 0.3s;
         display: inline-block;
         line-height: 1}

    #footer 
    .footer-top 
    .footer-links 
    ul a:hover{color: #e96b56}

    #footer 
    .footer-top 
    .footer-contact{margin-bottom: 30px}

    #footer 
    .footer-top 
    .footer-contact p
    {line-height: 26px}

    #footer 
    .copyright{text-align: center;
               padding-top: 30px}

    #footer 
    .credits{padding-top: 10px;
             text-align: center;
             font-size: 13px;
             color: #fff
    }

</style>

</html>
