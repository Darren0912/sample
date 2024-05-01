/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Member;
import Model.MemberDA;
import Model.BeastOrder;
import Model.BeastOrderDA;
import Model.Product;
import Model.ProductDA;
import Model.Staff;
import Model.StaffDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class StaffLogin extends HttpServlet {

    private StaffDA staffDA;
    private Staff staff;
    private Member member;
    private MemberDA memberDA;
    private Product product;
    private ProductDA productDA;
    private BeastOrder order;
    private BeastOrderDA orderDA;

    public void init() throws ServletException {
        staffDA = new StaffDA();
        memberDA = new MemberDA();
        productDA = new ProductDA();
        orderDA = new BeastOrderDA();
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        staff = staffDA.getLogin(username, password);

        if (staff != null) {
            String id = staff.getStaffID();
            String name = staff.getStaffName();
            String position = staff.getPosition();
            String phone = staff.getPhoneNo();
            String pass = staff.getPassword();

            Staff staff = new Staff(id, name, position, phone, pass);
            HttpSession session = request.getSession();
            session.setAttribute("staff", staff);
//get staff
            ArrayList<Staff> staffList = null;
            try {
                staffList = staffDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }

            session.setAttribute("staffList", staffList);
//get member
            ArrayList<Member> memberList = null;
            try {
                memberList = memberDA.getAll();
            } catch (SQLException ex) {
                 out.print(ex);
            }

            session.setAttribute("memberList", memberList);
//get product           
            ArrayList<Product> productList = new ArrayList<Product>();
            try {
                productList = productDA.getAll();
            } catch (SQLException ex) {
                 out.print(ex);
            }

            session.setAttribute("productList", productList);
//get Order            
            ArrayList<BeastOrder> orderList = new ArrayList<BeastOrder>();
            try {
                orderList = orderDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }

            session.setAttribute("orderList1", orderList);
            

            if ((staff.getPosition()).equals("manager")) {

                response.sendRedirect("Manager.jsp");
            } else {

                response.sendRedirect("Staff.jsp");
            }
        } else {
                String errorMessage = "Wrong Username or Password , Please try again...";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/SLogin.jsp");
                dispatcher.forward(request, response);
                response.sendRedirect("SLogin.jsp");

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