/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Member;
import Model.Product;
import Model.ProductDA;
import Model.MemberDA;
import Model.ShoppingCart;
import Model.ShoppingCartDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.transaction.UserTransaction;

/**
 *
 * @author Ding L
 */
public class Login extends HttpServlet {

    private MemberDA memberDA;
    private ProductDA productDA;
    private ShoppingCart cart;
    private ShoppingCartDA cartDA;

    @Override
    public void init() throws ServletException {
        memberDA = new MemberDA();
        productDA = new ProductDA();
        cartDA = new ShoppingCartDA();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param re private MemberDA memberDA; private Member member;
     *
     * @Overridequest servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Boolean exist = memberDA.existMember(username);
        Boolean correct = memberDA.passwordCorrect(username, password);
        HttpSession session = request.getSession();
        try {
            if (exist != true) {
                String errorMessage = "Member did not exist, Please try again...";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Signup.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("Signup.jsp");
            } else if (correct != true) {
                String errorMessage = "Password Incorrect, Please try again...";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Signup.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("Signup.jsp");

            } else {
                ShoppingCart cart = new ShoppingCart();
                Member member = new Member();
                member = memberDA.getMemberInformation(username);
                String id = member.getMemberID();
                cart = cartDA.getShopCartInformation(id);

                session.setAttribute("member", member);
                session.setAttribute("cart", cart);

                List<Product> productList = productDA.getAllRecord();
                session.setAttribute("productList", productList);

                response.sendRedirect("TheBeast.jsp");

            }
        } catch (Exception ex) {

            out.print(ex);

        } finally {

            out.close();

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
