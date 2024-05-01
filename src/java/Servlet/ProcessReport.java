/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Order_Product;
import Model.Order_ProductDA;
import Model.Payment;
import Model.PaymentDA;
import Model.Product;
import Model.ProductDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class ProcessReport
        extends HttpServlet {

    private Order_Product order;
    private Order_ProductDA orderDA;
    private Product product;
    private ProductDA productDA;
    private Payment payment;
    private PaymentDA paymentDA;

    public void init() throws ServletException {
        orderDA = new Order_ProductDA();
        productDA = new ProductDA();
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
        String submit = request.getParameter("submit");
        String Date1 = request.getParameter("Date1");
        String Date2 = request.getParameter("Date2");

        ArrayList<Order_Product> orderList = new ArrayList<Order_Product>();
        ArrayList<Product> productList = new ArrayList<Product>();
        ArrayList<Product> saleList = new ArrayList<Product>();
        ArrayList<Product> newList = new ArrayList<Product>();
        ArrayList<Payment> payList = new ArrayList<Payment>();
        ArrayList<Product> searchList = new ArrayList<Product>();

        int count = 0;
        if (submit.equals("report") || submit.equals("all")) {
            try {
                orderList = orderDA.getAll();
                productList = productDA.getAll();

            } catch (SQLException ex) {
                out.print(ex);
            }

            for (int i = 0; i < productList.size(); i++) {

                Product p = productList.get(i);

                String id = p.getProductID();
                String name = p.getProductName();

                int total = 0;

                try {
                    for (int j = 0; j < orderList.size(); j++) {
                        Order_Product o = orderList.get(j);
                        int num = o.getOrderedQuantity();
                        String pID = p.getProductID();
                        String oID = o.getProductID();
                        if (pID.equals(oID) == true) {
                            total = total + num;
                        } else {
                            total += 0;
                        }
                    }
                    Product s = new Product(id, name, total);
                    saleList.add(s);
                } catch (IllegalStateException e) {
                    System.err.println("IllegalStateException occurred: " + e.getMessage());
                }
            }

            HttpSession session = request.getSession();
            session.setAttribute("saleList", saleList);

            response.sendRedirect("Report.jsp");
            return;

        } else if (submit.equals("leastFive")) {

            HttpSession session = request.getSession();
            saleList = (ArrayList<Product>) session.getAttribute("saleList");

            Collections.sort(saleList, Comparator.comparingInt(Product::getTotal));
            Product product = new Product();
            for (int i = 0; i < 5; i++) {
                product = saleList.get(i);
                newList.add(product);
            }

            session.setAttribute("saleList", newList);
            response.sendRedirect("Report.jsp");
            return;
        } else if (submit.equals("topFive")) {

            HttpSession session = request.getSession();
            saleList = (ArrayList<Product>) session.getAttribute("saleList");

            Collections.sort(saleList, Comparator.comparingInt(Product::getTotal).reversed());

           Product product = new Product();
            for (int i = 0; i < 5; i++) {
                product = saleList.get(i);
                newList.add(product);
            }

            session.setAttribute("saleList", newList);
            response.sendRedirect("Report.jsp");
            return;
        }
        response.sendRedirect("Report.jsp");
        return;

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