/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Member;
import Model.MemberDA;
import Model.ShoppingCart;
import Model.ShoppingCartDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Ding L
 */
public class Register extends HttpServlet {

    private ShoppingCart cart;
    private ShoppingCartDA cartDA;
    private Member member;
    private MemberDA memberDA;

    @Override
    public void init() throws ServletException {
        memberDA = new MemberDA();
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
        //MemberID
        
        ArrayList<Member> ml = null;

            try {
               ml = memberDA.getAllRecord();
            } catch (Exception ex) {
              out.println(ex);
            }
            int intID = memberDA.generateID();
            for (int i = 0; i < ml.size(); ++i) {
                Member m = ml.get(i);
                while (m.getMemberID().equals("M" + intID)) {
                    intID++;
                }
            }
            String id = "M" + intID;
        //CartID
        String Cartid = cartDA.generateID();
        //Member
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String conpassword = request.getParameter("conpassword");
        //Cart
        double subtotal = 0;
        if (password.equals(conpassword) == true) {
            member = new Member(id, username, email, password, address);
            memberDA.addRecord(member);
            cart = new ShoppingCart(Cartid, id, subtotal);
            cartDA.addRecord(cart);
            response.sendRedirect("Successful.jsp");
        } else {
            response.sendRedirect("Invalidlogin.jsp");

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
