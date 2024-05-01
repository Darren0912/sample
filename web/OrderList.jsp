<%-- 
    Document   : OrderList
    Created on : May 13, 2023, 11:09:15 PM
    Author     : Ding L
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.BeastOrder, java.util.*"%>
<% List<BeastOrder> orderList = (List<BeastOrder>) session.getAttribute("orderList");%> 
<%@page import="Model.Payment, java.util.*"%>
<% List<Payment> paymentList = (List<Payment>) session.getAttribute("paymentList");%> 
<!DOCTYPE html>
<html>
    <head>
        <title>Order List</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
         <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" ></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        
        <div class="container mt-5">
            <form action="BackBeast" method="POST">
                <input type="hidden" name="cart" value="<%=orderList.get(0).getShoppingCartID() %>" />
          <button class="btn btn-light"><i class="fa fa-chevron-left" style="font-size:36px"></i></button>
           </form>
        <div class="d-flex justify-content-center row">
            <div class="col-md-10">
                <div class="rounded">
                    
                    <div class="table-responsive table-borderless">
                        
                        <table class="table">
                            <thead>
                                <tr>
                                    
                                    <th>Order #</th>
                                    <th>status</th>
                                    <th>Total</th>
                                    <th>Created</th>
                                    <th></th>
                                </tr>
                            </thead>
                            
                             <%  int[] orderArr = new int[orderList.size()];
                for (int i = 0; i < orderList.size(); i++) {%>
                            <tbody class="table-body">
                                <tr class="cell-1">
                                    
                                    <td><%=orderList.get(i).getOrderID()%></td>
                                    <td><span class="badge badge-success"><%=orderList.get(i).getOrderStatus()%></span></td>
                                    <td>RM <%=paymentList.get(i).getTotalPrice()%></td>
                                    <td><%=paymentList.get(i).getPaymentDate()%></td>
                                   <td>  <form action="ReferOrderDetails" method="POST" >
                                <input type="hidden" value="<%=orderList.get(i).getOrderID()%>" name="order" />
                                <input type="hidden" value="<%=paymentList.get(i).getPaymentID()%>" name="payment" />
                                
                                <button type="submit" class="btn btn-light"><i class="fa fa-ellipsis-h text-black-50"></i></button>
                            
                                    </form></td>
                             </tr>
                           
                            
                               
                                
                            </tbody>
                            <% } %>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
   
<style>
     
body {
  background: #eee;
  font-family: Assistant, sans-serif;
}

.cell-1 {
  border-collapse: separate;
  border-spacing: 0 4em;
  background: #fff;
  border-bottom: 5px solid transparent;
  /*background-color: gold;*/
  background-clip: padding-box;
}

thead {
  background: #dddcdc;
}

.toggle-btn {
  width: 40px;
  height: 21px;
  background: grey;
  border-radius: 50px;
  padding: 3px;
  cursor: pointer;
  -webkit-transition: all 0.3s 0.1s ease-in-out;
  -moz-transition: all 0.3s 0.1s ease-in-out;
  -o-transition: all 0.3s 0.1s ease-in-out;
  transition: all 0.3s 0.1s ease-in-out;
}

.toggle-btn > .inner-circle {
  width: 15px;
  height: 15px;
  background: #fff;
  border-radius: 50%;
  -webkit-transition: all 0.3s 0.1s ease-in-out;
  -moz-transition: all 0.3s 0.1s ease-in-out;
  -o-transition: all 0.3s 0.1s ease-in-out;
  transition: all 0.3s 0.1s ease-in-out;
}

.toggle-btn.active {
  background: blue !important;
}

.toggle-btn.active > .inner-circle {
  margin-left: 19px;
}
    </style>

 </html>