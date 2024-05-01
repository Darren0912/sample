/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Member;
import Model.MemberDA;
import Model.ShoppingCartDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ProcessMember extends HttpServlet {

    private ShoppingCartDA shopCartDA;
    private Member member;
    private MemberDA memberDA;
    
    public void init() throws ServletException {
        memberDA = new MemberDA();
        shopCartDA = new ShoppingCartDA();
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
        String submit = request.getParameter("submit");
        
        if (submit.equals("Edit")) {
            String id = request.getParameter("memberID");
            
            Member member = memberDA.getRecord(id);
            
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
            
            response.sendRedirect("UpdateMember.jsp");
            
        } else if (submit.equals("Update")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            
            Member mem = new Member(id, name, email, password, address);
            memberDA.updateRecord(mem);
            
            ArrayList<Member> memberList = null;
            try {
                memberList = memberDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("memberList", memberList);

            //JOptionPane.showMessageDialog(null, "Member " + id + " has been updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
            response.sendRedirect("ManageMember.jsp");
            
        } else {

            //int option = JOptionPane.showConfirmDialog(null, "Are you sure want to delete Member:" + id);
            //if (option == JOptionPane.YES_OPTION) {
            String id = request.getParameter("memberID");
            shopCartDA.deleteRecord(id);
            memberDA.deleteRecord(id);
            
            List<Member> memberList = null;
            try {
                memberList = memberDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }
            HttpSession session = request.getSession();
            session.setAttribute("memberList", memberList);

            //JOptionPane.showMessageDialog(null, "Member " + id + " has been deleted!", "Message", JOptionPane.INFORMATION_MESSAGE);
            //}
            response.sendRedirect("ManageMember.jsp");
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
