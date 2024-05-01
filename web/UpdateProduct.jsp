<%-- 
    Document   : ManagerProfile
    Created on : May 6, 2023, 9:44:12 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="product" scope="session" class="Model.Product" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Information</title>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
        <style>
            html, body {
                display: flex;
                justify-content: center;
                font-family: Roboto, Arial, sans-serif;
                font-size: 15px;
            }
            form {
                border: 5px solid #f1f1f1;
            }
            input[type=text], input[type=password] {
                width: 100%;
                padding: 16px 8px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            button {
                background-color: #8ebf42;
                color: white;
                padding: 14px 0;
                margin: 10px 0;
                border: none;
                cursor: grabbing;
                width: 100%;
            }
            h1 {
                text-align:center;
                fone-size:18;
            }
            button:hover {
                opacity: 0.8;
            }
            .formcontainer {
                text-align: left;
                margin: 24px 50px 12px;
            }
            .container {
                padding: 16px 0;
                text-align:left;
            }
            span.psw {
                float: right;
                padding-top: 0;
                padding-right: 15px;
            }
            /* Change styles for span on extra small screens */
            @media screen and (max-width: 300px) {
                span.psw {
                    display: block;
                    float: none;
                }
            </style>
        </head>
        <body>
            <form action="ManageProduct.jsp">
                <input type="submit" value="Back"/>
            </form>
            <form action="ProcessProduct" method="POST" enctype="multipart/form-data">
                <h1>Product Information</h1>
                <div class="formcontainer">
                    <hr/>
                    <div class="container">
                        <img src="data:image/jpg;base64,<%= product.getByte64Image() %>" class="card-img" width="300" height="300" >
                        <br/>
                        <label for="uname"><strong> Product ID    :</strong></label>
                        <input type="text" name="id" value="${product.productID}" readonly >

                        <label for="uname"><strong> Staff ID :</strong></label>
                        <input type="text" name="sid" value="${product.staffID}" readonly>

                        <label for="uname"><strong>Product Name :</strong></label>
                        <input type="text" name="name" value="${product.productName}">

                        <label for="uname"><strong> Description  :</strong></label>
                        <input type="text" name="desc" value="${product.description}">

                        <label for="uname"><strong>  Price :</strong></label>
                        <input type="text" name="price" value="${product.price}" >

                        <label for="uname"><strong> Category :</strong></label>
                        <input type="text" name="category" value="${product.category}">
                        
                        <label for="uname"><strong>New Image :</strong></label>
                        <input type="file" name="image"  value="data:image/jpg;base64,<%= product.getByte64Image() %>">

                    </div>  


                    <input type="submit" name="submit" value="Update" style="text-align: center" />
            </form>

        </body>
    </html>
