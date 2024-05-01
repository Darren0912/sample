/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.BeastOrder;
import Model.BeastOrderDA;
import Model.Payment;
import Model.PaymentDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class DisplayOrder extends HttpServlet {

    private BeastOrderDA orderDA;
    private PaymentDA paymentDA;

    @Override
    public void init() throws ServletException {

        orderDA = new BeastOrderDA();
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
        processRequest(request, response);
        String cartID = request.getParameter("cart");
        List<Payment> paymentList = new ArrayList<Payment>();
       
        List<BeastOrder> orderList = orderDA.getOrderListRecord(cartID);
        if (orderList.size() != 0) {
            for (int i = 0; i < orderList.size(); i++) {
                String orderID = orderList.get(i).getOrderID();
                List<Payment> payments = paymentDA.getPaymentListRecord(orderID);
                for (int j = 0; j < payments.size(); j++) {
                    paymentList.add(payments.get(j));
                }
            }

        }
        HttpSession session = request.getSession();
        session.setAttribute("orderList", orderList);
        session.setAttribute("paymentList", paymentList);
        response.sendRedirect("OrderList.jsp");
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
