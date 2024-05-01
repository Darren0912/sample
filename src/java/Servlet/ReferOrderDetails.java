/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.BeastOrder;
import Model.BeastOrderDA;
import Model.Order_Product;
import Model.Order_ProductDA;
import Model.Payment;
import Model.PaymentDA;
import Model.Product;
import Model.ProductDA;
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
public class ReferOrderDetails extends HttpServlet {

    private ProductDA productDA;
    private BeastOrderDA orderDA;
    private Order_ProductDA orderprodDA;
    private PaymentDA paymentDA;

    @Override
    public void init() throws ServletException {
        productDA=new ProductDA();
        orderprodDA = new Order_ProductDA();
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
        PrintWriter out = response.getWriter();
        String orderID = request.getParameter("order");
        String paymentID = request.getParameter("payment");
        BeastOrder order = new BeastOrder();
        order = orderDA.getCartRecord(orderID);
        Payment payment = new Payment();
        payment = paymentDA.getRecord(paymentID);

        List<Order_Product> orderprodList = orderprodDA.getOrderProductRecord1(orderID);
        Product product = null;
        List<Product> productList = new ArrayList<Product>();
        String productID = null;

        for (int i = 0; i < orderprodList.size(); i++) {
            productID = orderprodList.get(i).getProductID();
            product = productDA.getRecord(productID);
            productList.add(product);
        }
        HttpSession session = request.getSession();

        session.setAttribute("order", order);
        session.setAttribute("payment", payment);
        session.setAttribute("orderprodList", orderprodList);
        session.setAttribute("productList", productList);
        response.sendRedirect("ReferOrder.jsp");
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
