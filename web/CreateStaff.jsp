<%-- 
    Document   : CreateStaff
    Created on : May 6, 2023, 11:15:24 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="staff" scope="session" class="Model.Staff" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Staff</title>
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
            <form action="ManageStaff.jsp">
                <input type="submit" value="Back"/>
            </form>
            <form action="CreateStaff" method="POST">
                <h1>Create New Staff</h1>
                <div class="formcontainer">
                    <hr/>
                    <div class="container">
                        <label for="uname"><strong>Position :</strong></label>
                        <input type="text" name="position" value="staff" readonly>
                        <label for="uname"><strong>Phone No.:</strong></label>
                        <input type="text" name="phone" required>
                    </div>   

                    <p><input type="submit" name="submit" value="Create"><input type="reset" value="Reset"></p>
            </form>
        </body>

    </html>
