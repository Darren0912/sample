
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="order" scope="session" class="Model.BeastOrder" />
<jsp:useBean id="payment" scope="session" class="Model.Payment" />
<%@page import="Model.Product, java.util.*" %>
<%@page import="Model.Order_Product, java.util.*"%>
<% List<Product> productList = (List<Product>) session.getAttribute("productList");%> 
<% List<Order_Product> orderprodList = (List<Order_Product>) session.getAttribute("orderprodList");%> 

<!DOCTYPE html>
<html>
    <title>Refer to Order</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
    <link href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" rel="stylesheet" type="text/css"/>

    <body style="background-color:#e2e1e0;font-family: Open Sans, sans-serif;font-size:100%;font-weight:400;line-height:1.4;color:#000;">
        <table style="max-width:670px;margin:50px auto 10px;background-color:#fff;padding:50px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;-webkit-box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24);-moz-box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24);box-shadow:0 1px 3px rgba(0,0,0,.12),0 1px 2px rgba(0,0,0,.24); border-top: solid 10px green;">

            <tr>
                <td>

                <th style=" position: relative;right:150px;font-weight:400;font-size: 23px;"><b>Review all product items</b></th>

                </td>

            </tr>

            <br>
            <br>
            <tr>

                <td>
                    <form action="OrderList.jsp" >
                        
                        <input type="hidden" name="cart" value="" />
                        <button type="submit" class="btn btn-light">  <i style="font-size:40px" class="fa fa-arrow-left">    </i>
                        </button>
                    </form>
                </td>
                <th style="text-align:right;font-weight:400;"><b>${payment.paymentDate}</b></th>

            </tr>

            <tbody>

                <tr>

                    <td colspan="2" style="border: solid 1px #ddd; padding:10px 20px;">

                        <p style="font-size:14px;margin:0 0 6px 0;"><span style="font-weight:bold;display:inline-block;min-width:150px">Order <b style="color:green;font-weight:normal;margin:0">Success</b></span></p>
                        <p style="font-size:14px;margin:0 0 6px 0;"><span style="font-weight:bold;display:inline-block;min-width:146px">Order ID: ${order.orderID}</span></p>
                        <p style="font-size:14px;margin:0 0 6px 0;"><span style="font-weight:bold;display:inline-block;min-width:146px">Payment ID: ${payment.paymentID}</span></p>
                        <p style="font-size:14px;margin:0 0 6px 0;"><span style="font-weight:bold;display:inline-block;min-width:146px">Payment Method: ${payment.paymentMethod}</span></p>
                       
                    </td>
                </tr>
                <tr>
                    <td style="height:50px;">

                    </td>
                </tr>
                <tr>
                    <td style="height:30px;">
                        <h4>Tracking Order</h4>
                    </td>
                </tr>
                <tr>

                    <td style="height:35px; ">
                        <div class="card card-timeline px-2 border-none">
                            <%String status=order.getOrderStatus(); %>
                            <%if (status.equals("packaging")){ %>
                             <ul class="bs4-order-tracking"> 
                                <li class="step active"> 
                                    <div><i class="fas fa-user"></i></div>Packaging </li> 
                                <li class="step"> <div><i class="fas fa-truck"></i></div> Shipping </li> 
                                <li class="step "> <div><i class="fas fa-birthday-cake"></i></div> Delivered </li> 
                            </ul>
                            <% } %>
                            <%if (status.equals("shipping")){ %>
                             <ul class="bs4-order-tracking"> 
                                <li class="step active"> 
                                    <div><i class="fas fa-user"></i></div>Packaging </li> 
                                <li class="step active"> <div><i class="fas fa-truck"></i></div> Shipping </li> 
                                <li class="step "> <div><i class="fas fa-birthday-cake"></i></div> Delivered </li> 
                            </ul>
                            <% } %>
                            <%if (status.equals("delivery")){ %>
                             <ul class="bs4-order-tracking"> 
                                <li class="step active"> 
                                    <div><i class="fas fa-user"></i></div>Packaging </li> 
                                <li class="step active"> <div><i class="fas fa-truck"></i></div> Shipping </li> 
                                <li class="step active "> <div><i class="fas fa-birthday-cake"></i></div> Delivered </li> 
                            </ul>
                            <% } %>
                           
                        </div>
                    </td>
                </tr>


                <tr>
                    <td colspan="2" style="font-size:20px;padding:30px 15px 0 15px;">Items</td>
                </tr>


              
                <%for (int i = 0; i < productList.size(); i++) {%>
                <tr>
                    <td colspan="2" style="padding:15px;">

                        <p style="font-size:14px;margin:0;padding:10px;border:solid 1px #ddd;font-weight:bold;">

                            <img src="data:image/jpg;base64,<%= productList.get(i).getByte64Image()%>" class="card-img img-fluid" width="96" height="350" alt="">

                            <span style="display:block;font-size:13px;font-weight:normal;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=productList.get(i).getProductName()%></span>      
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;x<%=orderprodList.get(i).getOrderedQuantity()%></a>
                            <br><br>
                            
                            
<% } %>
           
            
                            

                            <br>
                            <br>
                            <a class="price" style="float:right">${payment.getTotalPrice()}</a><a style="float:right" >Total Price:&nbsp;&nbsp; </a>
                            
                            <br>
                        </p>

                    </td>

                </tr>

            </tbody>



        </table>
    </body>



<style>


    .price:before {
        content: 'RM';

    }

    .size:before{
        content: 'US';
    }

    .product-image {
        width: 15%;
        float:left;
    }

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

    .btn-1 {
        background: rgb(6,14,131);
        background: linear-gradient(0deg, rgba(6,14,131,1) 0%, rgba(12,25,180,1) 100%);
        border: none;
    }
    .btn-1:hover {
        background: rgb(0,3,255);
        background: linear-gradient(0deg, rgba(0,3,255,1) 0%, rgba(2,126,251,1) 100%);
    }




    bs4-order-tracking{
        margin-bottom: 30px;
        overflow: hidden;
        color: #878788;
        padding-left: 0px;
        margin-top: 30px
    }
    .bs4-order-tracking li{
        list-style-type: none;
        font-size: 13px;width: 25%;
        float: left;position: relative;
        font-weight: 400;color: #878788;
        text-align: center
    }
    .bs4-order-tracking li:first-child:before{
        margin-left: 15px !important;
        padding-left: 11px !important;
        text-align : left !important
    }
    .bs4-order-tracking li:last-child:before{
        margin-right: 5px !important;
        padding-right: 11px !important;
        text-align : right !important
    }
    .bs4-order-tracking li>div{
        color: #fff;width: 29px;
        text-align: center;
        line-height: 29px;
        display: block;
        font-size: 12px;
        background: #878788;
        border-radius: 50%;
        margin: auto
    }
    .bs4-order-tracking li:after{
        content: '';
        width: 150%;
        height: 2px;
        background: #878788;
        position: absolute;
        left: 0%;
        right: 0%;
        top: 15px;
        z-index: -1
    }
    .bs4-order-tracking li:first-child:after{
        left: 50%
    }
    .bs4-order-tracking li:last-child:after{
        left: 0%!important;
        width: 0% !important
    }
    .bs4-order-tracking li.active{
        font-weight: bold;
        color: #dc3545
    }
    .bs4-order-tracking li.active>div{
        background: #dc3545
    }
    .bs4-order-tracking li.active:after{
        background: #dc3545
    }
    .card-timeline{
        background-color: #fff;z-index: 0
    }
</style>
</html>