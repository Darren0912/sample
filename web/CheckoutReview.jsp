
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="Model.Product, java.util.*" %>
<%@page import="Model.Order_Product, java.util.*"%>
<jsp:useBean id="payment" scope="session" class="Model.Payment" />
<jsp:useBean id="order" scope="session" class="Model.BeastOrder" />
<% List myList = (List) session.getAttribute("myList");%> 
<% List<Product> productList = (List<Product>) session.getAttribute("productList");%> 
<% List<Order_Product> orderprodList = (List<Order_Product>) session.getAttribute("orderprodList");%> 


<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
                    <form action="BackBeast" method="POST">
                     <button type="submit" class="btn btn-light">   <i style="font-size:40px" class="fa fa-arrow-left">  </i>  
                                <input type="hidden" name="cart" value="${order.getShoppingCartID()}" />
                                &#xf177;
                            </button>
                        
                    </form>
                </td>
                <th style="text-align:right;font-weight:400;"><b>${payment.getPaymentDate()}</b></th>

            </tr>

            <tbody>
                <tr>
                    <td style="height:35px;"> <h3>Thank you for your ordering !!! </h3></td>
                </tr>
                <tr>

                    <td colspan="2" style="border: solid 1px #ddd; padding:10px 20px;">

                        <p style="font-size:14px;margin:0 0 6px 0;"><span style="font-weight:bold;display:inline-block;min-width:150px">Order <b style="color:green;font-weight:normal;margin:0">Success</b></span></p>
                        <p style="font-size:14px;margin:0 0 6px 0;"><span style="font-weight:bold;display:inline-block;min-width:146px">Payment ID: ${payment.getPaymentID()}</span></p>
                    </td>
                </tr>
                <tr>
                    <td style="height:35px;"></td>
                </tr>
                <tr>
                    <td style="width:50%;padding:20px;vertical-align:top">
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;"><span style="display:block;font-weight:bold;font-size:13px">Name</span><%=myList.get(0)%></p>
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;"><span style="display:block;font-weight:bold;font-size:13px;">Email</span> <%=myList.get(1)%></p>
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;font-weight:bold;">Phone<span style="display:block;font-size:13px;"><%=myList.get(2)%></span></p>
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;"><span style="display:block;font-weight:bold;font-size:13px;"><%=myList.get(4)%></span> Home</p>
                    </td>
                    <td style="width:50%;padding:20px;vertical-align:top">
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;"><span style="display:block;font-weight:bold;font-size:13px;">Address</span> <%=myList.get(3)%></p>
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;"><span style="display:block;font-weight:bold;font-size:13px;">Payment Method</span>${payment.getPaymentMethod()}</p>
                        <p style="margin:0 0 10px 0;padding:0;font-size:14px;"><span style="display:block;font-weight:bold;font-size:13px;">Order Status</span>${order.orderStatus}</p>
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

</html>

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




</style>