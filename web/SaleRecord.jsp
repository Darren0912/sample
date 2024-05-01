<%-- 
    Document   : SaleRecord
    Created on : May 9, 2023, 1:39:54 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Order_Product, java.util.*" %>
<jsp:useBean id="staff" scope="session" class="Model.Staff" />
<%ArrayList<Order_Product> orderprodList = (ArrayList<Order_Product>) session.getAttribute("orderprodList"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.1/css/all.css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js" rel="stylesheet">
        <link href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" rel="stylesheet">
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sale Record</title>
    </head>
    <div class="wrapper d-flex">
        <div class="sidebar">

            <ul>
                <% if (staff.getPosition().equals("manager")) { %>
                <li><a href="ManagerProfile.jsp"><i class="fas fa-home"></i>Profile</a></li>
                <li><a href="ManageStaff.jsp"><i class="fas fa-users"></i>Staff Information</a></li>
                    <% } else {%>
                <li><a href="StaffProfile.jsp"><i class="fas fa-home"></i>Profile</a></li>
                    <% }%>
                <li><a href="ManageMember.jsp"><i class="fas fa-users"></i>Member Information</a></li>
                <li class="notification1"><a href="ManageProduct.jsp"><i class="fas fa-calendar-week"></i>Manage Product</a></li>
                <li><a href="ManageOrder.jsp"><i class="fas fa-calendar-week"></i>Manage Order</a></li>
                    <% if (staff.getPosition().equals("manager")) { %>
                <li>
                    <form action="ProcessSales" method="POST">
                        <i class="fas fa-signal" style="color:white"></i>
                        <input type="submit" name="submit" value="Sale Record" style="background-color:black;color: white"/>
                    </form>
                </li>
                <li>
                    <form action="ProcessReport" method="POST">
                        <i class="fas fa-signal" style="color:white"></i>
                        <input type="submit" name="submit" value="report" style="background-color:black;color: white"/>
                    </form>
                </li>
                <% }%>


            </ul>



            <div class="userProfile">
                <a href="ManagerProfile.jsp"><i class="far fa-user-circle  xyz" style="font-size:22px;position:relative;bottom:10px"></i></a>

                <p class="username" style="position:relative;bottom:-4px"><%=staff.getPosition()%> : <%=staff.getStaffName()%><br></p>

                <body>

                    <a href="SLogin.jsp"><i class="fas fa-sign-out-alt" style="font-size:22px;position:relative;bottom:-6px"></i></a>
                </body>
            </div>


        </div>
    </div>
    <div class="content read" style="position:relative;left:140px;top:70px;">

        <form action="ProcessSales" method="POST">
            <label for="salesDate">Select Date:</label>
            <input type="date" id="salesDate" name="salesDate" required><br><br>
            <label for="reportType">Select Report Type:</label>
            <select id="reportType" name="reportType" required>
                <option value="day">Daily Report</option>
                <option value="month">Monthly Report</option>
                <option value="year">Yearly Report</option>
            </select><br>

            <input type="submit" name="submit" value="check"/>
        </form>

        <h2 style="background-color: grey;color: white;text-align: center">Sale Record</h2>


        <table>
            <thead>
                <tr>
                    <th style="text-align: center">Product ID</th>
                    <th style="text-align: center">Total Sale</th>                   
                </tr>
            </thead>
            <tbody>
                <% 
                if(orderprodList!=null){
                    int[] orderArr = new int[orderprodList.size()];
                        for (int i = 0; i < orderprodList.size(); ++i) {
                            Order_Product order = orderprodList.get(i);%>
                <tr>
                    <td align="center"><%=order.getProductID()%></td>
                    <td align="center"><%=order.getOrderedQuantity()%></td>

                </tr>
                <% }
                    }%>  
            </tbody>
        </table>

    </body>
</html>
<script>
    let popup = document.getElementById("popup");

    function openPopup()
    {
        popup.classList.add("open-popup");
    }


</script>

<style>
    * {
        box-sizing: border-box;
        font-family: -apple-system, BlinkMacSystemFont, "segoe ui", roboto, oxygen, ubuntu, cantarell, "fira sans", "droid sans", "helvetica neue", Arial, sans-serif;
        font-size: 16px;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
    }
    body {
        background-color: #FFFFFF;
        margin: 0;
    }

    .content {
        max-width: 1000px;
        margin: 0 auto;
    }
    .content h2 {
        margin: 0;
        padding: 25px 0;
        font-size: 22px;
        border-bottom: 1px solid #ebebeb;
        color: #666666;
    }
    .read .create-contact {
        display: inline-block;
        text-decoration: none;
        background-color: #38b673;
        font-weight: bold;
        font-size: 14px;
        color: #FFFFFF;
        padding: 10px 15px;
        margin: 15px 0;
    }
    .read .create-contact:hover {
        background-color: #32a367;
    }
    .read .pagination {
        display: flex;
        justify-content: flex-end;
    }
    .read .pagination a {
        display: inline-block;
        text-decoration: none;
        background-color: #a5a7a9;
        font-weight: bold;
        color: #FFFFFF;
        padding: 5px 10px;
        margin: 15px 0 15px 5px;
    }
    .read .pagination a:hover {
        background-color: #999b9d;
    }
    .read table {
        width: 100%;
        padding-top: 30px;
        border-collapse: collapse;
    }
    .read table thead {
        background-color: #ebeef1;
        border-bottom: 1px solid #d3dae0;
    }
    .read table thead td {
        padding: 10px;
        font-weight: bold;
        color: #767779;
        font-size: 14px;
    }
    .read table tbody tr {
        border-bottom: 1px solid #d3dae0;
    }
    .read table tbody tr:nth-child(even) {
        background-color: #fbfcfc;
    }
    .read table tbody tr:hover {
        background-color: #376ab7;
    }
    .read table tbody tr:hover td {
        color: #FFFFFF;
    }
    .read table tbody tr:hover td:nth-child(1) {
        color: #FFFFFF;
    }
    .read table tbody tr td {
        padding: 10px;
    }
    .read table tbody tr td:nth-child(1) {
        color: #a5a7a9;
    }
    .read table tbody tr td.actions {
        padding: 8px;
        text-align: right;
    }
    .read table tbody tr td.actions .edit, .read table tbody tr td.actions .trash {
        display: inline-flex;
        text-align: right;
        text-decoration: none;
        color: #FFFFFF;
        padding: 10px 12px;
    }
    .read table tbody tr td.actions .trash {
        background-color: #b73737;
    }
    .read table tbody tr td.actions .trash:hover {
        background-color: #a33131;
    }
    .read table tbody tr td.actions .edit {
        background-color: #37afb7;
    }
    .read table tbody tr td.actions .edit:hover {
        background-color: #319ca3;
    }

    .delete .yesno {
        display: flex;
    }
    .delete .yesno a {
        display: inline-block;
        text-decoration: none;
        background-color: #38b673;
        font-weight: bold;
        color: #FFFFFF;
        padding: 10px 15px;
        margin: 15px 10px 15px 0;
    }
    .delete .yesno a:hover {
        background-color: #32a367;
    }

    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        list-style: none;
        text-decoration: none;
        font-family: arial;
    }
    body{
        background: #fff;
    }
    .wrapper{
        position: relative;
    }
    .sidebar{
        position: fixed;
        width: 250px;
        height: 100%;
        background: #000;
        padding: 10px 0;

    }
    .wrapper .sidebar ul li{
        padding: 15px;	
    }
    .wrapper .sidebar ul li a{
        color: #bdb8d7;
        display: block;
    }
    .wrapper .sidebar ul li a .fas{
        width: 25px!important;
    }
    .wrapper .sidebar ul li a .far{
        width: 25px!important;
    }
    .wrapper .sidebar ul li:hover{
        background: #311B92;
    }
    .wrapper .sidebar ul li a:hover{
        color: #fff;
        text-decoration: none;
    }
    .myproject{
        margin-top: 25px;
        color: #ffffffa8;
        font-size: 14px;
        margin-bottom: 0;
    }
    .userProfile{
        position: absolute;
        bottom: 0;
        left: 5%;
        display: flex;

    }
    .userProfile a{
        width: 20px;
        background: #000;
        color: #bdb8d7;
        text-decoration: none;
        font-size: 15px;
    }
    .userProfile a:hover{
        color: #fff;
        background: #000;
    }
    .userProfile p{
        color: #fff;
        padding: 0 15px 0 15px;
    }
    .userProfile 
    .xyz,
    .mnp{
        padding-top: 60%;
        line-height: 30px;
        font-size: 25px!important;
    }
    .notification1{
        display: flex;
    }
    .notification1 .number1{
        font-size: 15px;
        display: block;
        padding-left: 100px;
        color: #fff;
    } 

    .notification2{
        display: flex;
    }
    .notification2 .number2{
        font-size: 15px;
        display: block;
        padding-left: 85px;
        color: #fff;
    }

    .container{
        widht: 100%;
        height: 100vh;
        backgorund: #3c5077;
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .btn{
        padding: 10px 60px;
        background: aqua;
        border: 0;
        outline: none;
        cursor: pointer;
        font-size: 22px;
        font-weight: 500;
        border-radius: 30px;
    }

    .popup{
        width: 400px;
        background: #fff;
        border-radius: 6px;
        position: absolute;
        top: 35%;
        left: 55%;
        transfrom: translate(-50%,-50%) scale(0.1);
        text-align: center;
        paddding: 0 30px 30px;
        color: #333;
        visibility: hidden;
        transition: transform 0.7s, top 0.7s;
    }

    .open-popup{
        visibility: visible;
        top: 50%;
        transform: translate(-50%,-50%) scale(1);
    }
    .popup img{
        width: 100px;
        margin-top: -50px;
        border-radius: 50%;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    }

    .popup h2{
        font-size: 38px;
        font-weight: 500;
        margin: 30px 0 10px;
    }

    .popup button{
        width: 100%;
        margin-top: 50px;
        padding: 10px 0;
        background: #6fd649;
        color: #fff;
        border: 0;
        outline: none;
        font-size: 18px;
        border-radius: 4px;
        cursor: pointer;
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
    }
</style>

