<%-- 
    Document   : assignment
    Created on : May 4, 2023, 1:39:20 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="member" scope="session" class="Model.Member" />
<jsp:useBean id="product" scope="session" class="Model.Product" />
<jsp:useBean id="cart" scope="session" class="Model.ShoppingCart" />

<!DOCTYPE html>
<html>
    <head>

        <title>Product Details</title>
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

    

<%@include file="include/newheader.jsp" %>
            <div class="container mt-5 mb-5 d-flex justify-content-center align-items-center">



                <div class="card">
                    <div class="inner-card"> <img src="data:image/jpg;base64,${product.byte64Image}" class="card-img img-fluid" width="96" height="350" alt="">
                        <div class="d-flex justify-content-between align-items-center mt-3 px-2">
                            <h4>${product.productName}</h4> 
                        </div>
                        <div class="mt-2 px-2"> <small>${product.description}</small> </div>
                        <br>
                        <div class="px-2">
                            <h4>RM ${product.price}</h4>
                             
                            <form action="AddCart" method="POST">
                                <label for="shoes">Choose a size: </label>
                                <select name="size" id="cars">
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                           

                          
                                <label for="shoes">Quantity: </label>
                                <select name="quantity" id="cars">
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                </select>
                                <input type="hidden" name="member" value="${member.memberID}" />
                                <input type="hidden" name="product" value="${product.productID}" />
                                <input type="hidden" name="price" value="${product.price}" />
                                <input type="hidden" name="cart" value="${cart.cartID}" />
                                <input type="submit" value="Add to Cart" class="btn btn-outline-primary px-3" />
                                  
                            </form>
                        </div>
                            <br>
                        <a href="Basketball.jsp" <div class="px-2 mt-3"> <button class="btn btn-primary px-3">back</button>  </a>
                       
</div>
                </div>
            </div>

                             




    <style>
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










    <footer id="footer"> 
        <div class="footer-newsletter"> 
            <div class="container">
                <div class="row"> 
                    <div class="col-lg-6"> 


                        </form> 
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
</header>	
</div>
</body>
</html>
