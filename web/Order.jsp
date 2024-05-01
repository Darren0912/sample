<%-- 
    Document   : assignment
    Created on : May 4, 2023, 1:39:20 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Product, java.util.*" %>
<%@page import="Model.ProductCart, java.util.*" %> 
<% List<Product> productInformationList = (List<Product>) session.getAttribute("productInformationList");%> 
<% List<ProductCart> prodcartList = (List<ProductCart>) session.getAttribute("prodcartList");%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Order</title>

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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
    </head>	

    <%@include file="include/newheader2.jsp" %>	<!-- ./ end of navbar -->


            <div class="container-fluid my-5 d-sm-flex justify-content-center">
                <div class="card px-2">
                    <div class="card-header bg-white">
                        <div class="row justify-content-between">
                            <div class="col">
                                <p class="text-muted"><span class="font-weight-bold text-dark"> Order </span></p> 

                                <div class="flex-col my-auto">

                                </div>
                            </div>
                        </div>
                        <hr>
                        <%! double total = 0;%>

                        <%for (int i = 0; i < productInformationList.size(); i++) {%>
                        <div class="card-body">
                            <div class="media flex-column flex-sm-row">
                                <div class="media-body ">
                                    <h5 class="bold"><%=productInformationList.get(i).getProductName()%></h5>
                                    <p class="text-muted"> Quantity: <%=prodcartList.get(i).getQuantity()%></p>
                                    <h4 class="mt-3 mb-4 bold"> RM <%=productInformationList.get(i).getPrice()%> </h4>
                                    <span class="small text-muted"> <%=productInformationList.get(i).getCategory()%> </span>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <br>
                                    <br>

                                </div><img src="data:image/jpg;base64,<%= productInformationList.get(i).getByte64Image()%>" class="card-img img-fluid" width="96" height="350" alt="">
                                <%total += productInformationList.get(i).getPrice() * prodcartList.get(i).getQuantity();%>
                            </div>
                        </div>
                        <hr>
                        <% }%>    

                        <h4 class="mt-3 mb-4 bold"> Total: RM <%=total%> </h4>
                        <form method="POST" action="PaymentMethod">
                             <input type="hidden" value="<%=total%>" name="total" />
                            <input type="hidden" value="<%=prodcartList.get(0).getCartID()%>" name="cart" />
                            <label for="shoes">Payment Method:</label>
                            <select name="paymentmethod">
                                <option value="cash">Cash</option>
                                <option value="creditCard">Debit/Credit Card</option>
                            </select>
                            <br>
                            <br>
                            <div class="card-footer  bg-white ">
                                <div class="row text-center  ">

                                    <br>
                                    <br>
                                    <div class="col my-auto   border-line ">  <button type="submit" class="custom-btn btn-4"><span>Confirm Order</span></button></div>
                                    </form>

                                </div>

                            </div>
                            <div class="col  my-auto  border-line" style="text-align:center;"><button class="custom-btn btn-4"><span><a href="Cart.jsp"><h5>Cancel</h5></a></button></div></span></button>
                    </div>
                </div>


            </div>
    </div>
    <footer id="footer"> 

<%@include file="include/footer.jsp" %>
        
    </footer>
</body>
<style>
    button {
        margin: 20px;
    }
    .custom-btn {
        width: 130px;
        height: 40px;
        color: #fff;
        border-radius: 5px;
        padding: 10px 25px;
        font-family: 'Lato', sans-serif;
        font-weight: 500;
        background: transparent;
        cursor: pointer;
        transition: all 0.3s ease;
        position: relative;
        display: inline-block;
        box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
            7px 7px 20px 0px rgba(0,0,0,.1),
            4px 4px 5px 0px rgba(0,0,0,.1);
        outline: none;
    }
    .btn-4 {
        background-color: #4dccc6;
        background-image: linear-gradient(315deg, #4dccc6 0%, #96e4df 74%);
        line-height: 42px;
        padding: 0;
        border: none;
    }
    .btn-4:hover{
        background-color: #89d8d3;
        background-image: linear-gradient(315deg, #89d8d3 0%, #03c8a8 74%);
    }
    .btn-4 span {
        position: relative;
        display: block;
        width: 100%;
        height: 100%;
    }
    .btn-4:before,
    .btn-4:after {
        position: absolute;
        content: "";
        right: 0;
        top: 0;
        box-shadow:  4px 4px 6px 0 rgba(255,255,255,.9),
            -4px -4px 6px 0 rgba(116, 125, 136, .2), 
            inset -4px -4px 6px 0 rgba(255,255,255,.9),
            inset 4px 4px 6px 0 rgba(116, 125, 136, .3);
        transition: all 0.3s ease;
    }
    .btn-4:before {
        height: 0%;
        width: .1px;
    }
    .btn-4:after {
        width: 0%;
        height: .1px;
    }
    .btn-4:hover:before {
        height: 100%;
    }
    .btn-4:hover:after {
        width: 100%;
    }
    .btn-4 span:before,
    .btn-4 span:after {
        position: absolute;
        content: "";
        left: 0;
        bottom: 0;
        box-shadow:  4px 4px 6px 0 rgba(255,255,255,.9),
            -4px -4px 6px 0 rgba(116, 125, 136, .2), 
            inset -4px -4px 6px 0 rgba(255,255,255,.9),
            inset 4px 4px 6px 0 rgba(116, 125, 136, .3);
        transition: all 0.3s ease;
    }
    .btn-4 span:before {
        width: .1px;
        height: 0%;
    }
    .btn-4 span:after {
        width: 0%;
        height: .1px;
    }
    .btn-4 span:hover:before {
        height: 100%;
    }
    .btn-4 span:hover:after {
        width: 100%;
    }

    body{
        background-image:url(IMG/background1.0.jpg);

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

    .btn-theme {
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
        padding:7px;
    }

    .signup-button{
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


    .signup-button:hover{

        background-color:#e9ecf0d1;
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

<style>



    .container{


    }

    .card {
        background-color: #c1e8ed;
        width: 400px;
        padding: 10px;
        border: 1px solid #eee
    }

    .inner-card {
        background: #fff;
        padding: 10px;
        border-radius: 5px
    }

    .heart {
        cursor: pointer;
        height: 35px;
        width: 35px;
        font-size: 13px;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #beb4aa;
        border-radius: 50%;
        background-color: #eee
    }

    .btn:focus {
        color: #fff;
        background-color: #025ce2;
        border-color: #0257d5;
        box-shadow: none
    }

</style>
<style>


    .container-fluid {
        margin-top: 80px !important;
        margin-bottom: 80px !important;
    }

    p {
        font-size: 14px;
        margin-bottom: 7px;
    }

    .cursor-pointer {
        cursor: pointer;
    }


    .bold{
        font-weight: 600;
    }

    .small{
        font-size: 12px !important;
        letter-spacing: 0.5px !important;
    }






    .card {
        background-color: #fff ;
        box-shadow: 2px 4px 15px 0px rgb(0, 108, 170);
        z-index: 0;
    }

    small{
        font-size: 12px !important;
    }

    .a {
        justify-content: space-between !important;
    }

    .border-line {
        border-right: rgb(226, 206, 226)
    }

    .card-footer img{
        opacity: 0.3;
    }

    .card-footer h5{
        font-size: 1.1em;
        cursor: pointer;
    }
</style>
</html>

