/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Staff;
import Model.StaffDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class CreateStaff extends HttpServlet {

    private Staff staff;
    private StaffDA staffDA;

    public void init() throws ServletException {
        staffDA = new StaffDA();
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

        if (submit.equals("Create")) {
            

            ArrayList<Staff> sl = null;

            try {
                sl = staffDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }
            int intID = staffDA.generateID();
            for (int i = 0; i < sl.size(); ++i) {
                Staff s = sl.get(i);
                while (s.getStaffID().equals("ST" + intID)) {
                    intID++;
                }
            }
            
            String id = "ST" + intID;
            
            
            
            String name = "ST" + intID;
            String position = request.getParameter("postion");
            String phone = request.getParameter("phone");
            String password = "ST" + intID;

            staff = new Staff(id, name, position, phone, password);
            staffDA.addRecord(staff);

            ArrayList<Staff> staffList = null;
            try {
                staffList = staffDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }

            HttpSession session = request.getSession();
            session.setAttribute("staffList", staffList);

            //JOptionPane.showMessageDialog(null, "New Staff Added!", "Message", JOptionPane.INFORMATION_MESSAGE);
            response.sendRedirect("ManageStaff.jsp");
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