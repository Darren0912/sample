/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Member;
import Model.MemberDA;
import Model.Product;
import Model.ProductDA;
import Model.ShoppingCart;
import Model.ShoppingCartDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ding L
 */
public class BackBeast extends HttpServlet {
    
    private MemberDA memberDA;
    private ProductDA productDA;
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
     * @param request servlet request
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
        String cartID = request.getParameter("cart");
        ShoppingCart shopcart = new ShoppingCart();
        shopcart = cartDA.getRecord(cartID);
        String memberID=shopcart.getMemberID();
        Member member=new Member();
        member=memberDA.getRecord(memberID);
        HttpSession session = request.getSession();
        session.setAttribute("cart", shopcart);
        
        List<Product> productList = productDA.getAllRecord();
        session.setAttribute("productList", productList);
        response.sendRedirect("TheBeast.jsp");
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
