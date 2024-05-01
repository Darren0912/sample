<%-- 
    Document   : emptyCart
    Created on : May 13, 2023, 1:39:20 PM
    Author     : Ding L
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        
        <title>Cart is Empty</title>
    </head>
    <body>
        <div class="container-fluid  mt-100">
            <div class="row">

                <div class="col-md-12">

                    <div class="card">
                        <div class="card-header">
                            <h5>Cart</h5>
                        </div>
                        <div class="card-body cart">
                            <div class="col-sm-12 empty-cart-cls text-center">
                                <img src="https://i.imgur.com/dCdflKN.png" width="130" height="130" class="img-fluid mb-4 mr-3">
                                <h3><strong>Your Cart is Empty</strong></h3>
                                <h4>Add something to make me happy :)</h4>
                                <a href="TheBeast.jsp" class="btn btn-primary cart-btn-transform m-3" data-abc="true">continue shopping</a>


                            </div>
                        </div>
                    </div>


                </div>

            </div>

        </div>
    </body>

    <style>
      

        body {
            background-color: #eee;
            font-family: 'Calibri', sans-serif !important;
        }

        .mt-100{
            margin-top:100px;

        }


        .card {
            margin-bottom: 30px;
            border: 0;
            -webkit-transition: all .3s ease;
            transition: all .3s ease;
            letter-spacing: .5px;
            border-radius: 8px;
            -webkit-box-shadow: 1px 5px 24px 0 rgba(68,102,242,.05);
            box-shadow: 1px 5px 24px 0 rgba(68,102,242,.05);
        }

        .card .card-header {
            background-color: #fff;
            border-bottom: none;
            padding: 24px;
            border-bottom: 1px solid #f6f7fb;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .card-header:first-child {
            border-radius: calc(.25rem - 1px) calc(.25rem - 1px) 0 0;
        }



        .card .card-body {
            padding: 30px;
            background-color: transparent;
        }

        .btn-primary, .btn-primary.disabled, .btn-primary:disabled {
            background-color: #4466f2!important;
            border-color: #4466f2!important;
        }
    </style>
</html>

