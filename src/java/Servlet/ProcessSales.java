/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Order_Product;
import Model.Order_ProductDA;
import Model.PaymentDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author junqiang
 */
public class ProcessSales extends HttpServlet {

    private Order_ProductDA orderprodDA;
    private PaymentDA paymentDA = new PaymentDA();

    public void init() throws ServletException {
        orderprodDA = new Order_ProductDA();
        paymentDA = new PaymentDA();
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

        List<Order_Product> orderprodList = new ArrayList<Order_Product>();
       HttpSession session = request.getSession();

        String submit = request.getParameter("submit");
        if (submit.equals("check")) {
            String salesDate = request.getParameter("salesDate");
            String reportType = request.getParameter("reportType");
            PrintWriter out = response.getWriter();

            // Parse the selected date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            try {
                date = dateFormat.parse(salesDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            out.println(date);

            // Create a calendar instance and set it to the selected date
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);

            // Get the year, month, and day of the selected date
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);

            List<String> orderIDList = null;

            if (reportType.equals("day")) {
                try {
                    // Get the sales records for the selected day
                    orderIDList = paymentDA.getSalesByDay(year, month, day);
                } catch (SQLException ex) {
                }
            } else if (reportType.equals("month")) {
                try {
                    // Get the sales records for the selected month
                    orderIDList = paymentDA.getSalesByMonth(year, month);
                } catch (SQLException ex) {
                }
            } else if (reportType.equals("year")) {
                try {
                    // Get the sales records for the selected year
                    orderIDList = paymentDA.getSalesByYear(year);
                } catch (SQLException ex) {
                }
            }

           
            for (int i = 0; i < orderIDList.size(); i++) {
                String orderID = orderIDList.get(i);
                orderprodList = orderprodDA.getOrderProductRecord(orderID);
            }
      
           
            session.setAttribute("orderprodList", orderprodList);
            response.sendRedirect("SaleRecord.jsp");
        } else {
           
           

            session.setAttribute("orderprodList", orderprodList);
            response.sendRedirect("SaleRecord.jsp");
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