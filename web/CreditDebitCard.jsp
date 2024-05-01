<%-- 
    Document   : assignment
    Created on : May 4, 2023, 1:39:20 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <title>Credit Card Payment</title>
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
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
    </head>
    <body>
        <%@include file="include/newheader2.jsp" %>

            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.payment/3.0.0/jquery.payment.min.js"></script>
            
            <div class="padding">
               
                <div class="row">
                    <div class="container-fluid d-flex justify-content-center">
                        <div class="col-sm-8 col-md-6">
                            <div class="card">
                                <div class="card-header" >

                                    <div class="row">
                                        <div class="col-md-6">
                                            <span>CREDIT/DEBIT CARD PAYMENT</span>

                                        </div>
 
                                        <div class="col-md-6 text-right" style="margin-top: -5px;">

                                        </div>      

                                    </div>    

                                </div>

                                <div class="card-body" style="height: 400px">
                                    <c:if test="${not empty errorMessage}">
                                        <div class="error">${errorMessage}</div>
                                    </c:if>
                                    
                                    <form action="ValidateCard" method="POST">
                                        <label for="shoes">TYPE:</label>
                                        <select name="payment" id="payment">
                                            <option value="visa">VISA</option>
                                            <option value="MasterCard">MasterCard</option>
                                        </select>


                                        <br>
                                        <label for="cc-number" class="control-label">CARD NUMBER</label>
                                        <input id="cc-number" name="cardNumber" type="tel" class="input-lg form-control cc-number" autocomplete="cc-number" placeholder="&bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull; &bull;&bull;&bull;&bull;" required />

                                        <label for="cc-exp" class="control-label">CARD EXPIRY</label>
                                        <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" autocomplete="cc-exp" placeholder="&bull;&bull; / &bull;&bull;" required>

                                        <label for="cc-cvc" class="control-label">CARD CVC</label>
                                        <input id="cc-cvc" name="ccv" type="tel" class="input-lg form-control cc-cvc" autocomplete="off" placeholder="&bull;&bull;&bull;" required>
                                        <span class="error" id="ccvError"></span>

                                        <label for="numeric" class="control-label">CARD HOLDER NAME</label>
                                        <input  type="text" class="input-lg form-control">

                                        <br>
                                        <input value="MAKE PAYMENT" type="submit" class="btn btn-success btn-lg form-control" style="font-size: .8rem;">

                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer id="footer"> 


            <%@include file="include/footer.jsp" %>
        </footer>


    </body>
    <%-- This is the myform.jsp page --%>

    <script>
        // Define regular expressions for the card number and CCV formats
        var cardNumberRegex = /^[0-9]{16}$/;
        var ccvRegex = /^[0-9]{3}$/;

        // Get form elements
        var form = document.querySelector('form');
        var cardNumberInput = form.querySelector('#cardNumber');
        var ccvInput = form.querySelector('#ccv');
        var cardNumberError = form.querySelector('#cardNumberError');
        var ccvError = form.querySelector('#ccvError');

        // Add event listener to the form's submit button
        form.addEventListener('submit', function (event) {
            // Reset error messages
            cardNumberError.innerText = '';
            ccvError.innerText = '';

            // Validate card number
            if (!cardNumberInput.value.match(cardNumberRegex)) {
                cardNumberError.innerText = 'Invalid card number format. Please enter a 16-digit number.';
                event.preventDefault();
            }

            // Validate CCV
            if (!ccvInput.value.match(ccvRegex)) {
                ccvError.innerText = 'Invalid CCV format. Please enter a 3-digit number.';
                event.preventDefault();
            }
        });
    </script>
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
            width: 750px;
            padding: 15px;
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


    <style>
        .padding{

            padding: 5rem !important;
        }



        .form-control:focus {
            box-shadow: 10px 0px 0px 0px #ffffff !important;
            border-color: #4ca746;
        }
    </style>

</html>

