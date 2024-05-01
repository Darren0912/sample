<%-- 
    Document   : Successfull
    Created on : May 6, 2023, 4:39:41 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Successfully</title>
    </head>
    <body>
        <div class="container">
            <button type="submit" class="btn" onclick="openPopup()" >Click It !</button>
            <div class="popup" id="popup">
                <img src="IMG/tick.jpeg">
                <h2>Thank You!</h2>
                <p>Registers Successfully</p>
                <form action="Signup.jsp">
                <input type="submit" value="Back to Login">
                </form>
            </div>
        </div>
        
        <script>
            let popup = document.getElementById("popup");
            
            function openPopup()
            {
                popup.classList.add("open-popup");
            }
            
           
        </script>
        
    </body>
</html>


<style>
    *{
       
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }
    
    .container{
        widht: 100%;
        height: 100vh;
        backgorund: #3c5077;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .btn,input{
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
    body{
       background-color:blue;
    }
</style>
