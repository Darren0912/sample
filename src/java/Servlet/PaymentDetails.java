/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.BeastOrder;
import Model.BeastOrderDA;
import java.util.Date;
import java.text.SimpleDateFormat;
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
public class PaymentDetails extends HttpServlet {

    private PaymentDA paymentDA;
    private Order_ProductDA orderprodDA;
    private ProductDA productDA;
    private BeastOrderDA orderDA;

    @Override
    public void init() throws ServletException {
        paymentDA = new PaymentDA();
        orderprodDA = new Order_ProductDA();
        productDA = new ProductDA();
        orderDA = new BeastOrderDA();;
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
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String paymentMethod = request.getParameter("typePayment");
        String orderID = request.getParameter("order");
        String memberName = request.getParameter("memberName");
        String email = request.getParameter("email");
        String phonenum = request.getParameter("phonenum");
        String address = request.getParameter("address");
        String place = request.getParameter("place");
        String paymentID = paymentDA.generateID();
        BeastOrder order=new BeastOrder();
        order = orderDA.getCartRecord(orderID);
        List<Order_Product> orderprodList = orderprodDA.getOrderProductRecord1(orderID);
        Product product = null;
        List<Product> productList = new ArrayList<Product>();
        String productID = null;
        double discount = 0;
        double total = 0;
        double deliveryFee=0;
        String paymentdate = dateFormat.format(today);
        for (int i = 0; i < orderprodList.size(); i++) {
            productID = orderprodList.get(i).getProductID();
            product = productDA.getProductRecord(productID);
            if (productID.equals("P9") == true) {
                discount = 0.5;
                total+=(product.getPrice()*0.5)*orderprodList.get(i).getOrderedQuantity();
                productList.add(product);
            }
            else{
            total += product.getPrice()*orderprodList.get(i).getOrderedQuantity();
            productList.add(product);
            }
        }
        if (paymentMethod.equals("visa") || paymentMethod.equals("MasterCard")) {
            paymentMethod = "Debit Card";
        } else {
            paymentMethod = "cash";
        }
        if (total >=200){
            deliveryFee=0;
            total+=deliveryFee;
        }
        else{
            deliveryFee=25;
            total+=deliveryFee;
        }
        Payment payment = null;
        payment = new Payment(paymentID, discount, total, paymentMethod, paymentdate, orderID);
        paymentDA.addRecord(payment);

        ArrayList<String> myList = new ArrayList<String>();
        myList.add(memberName);
        myList.add(email);
        myList.add(phonenum);
        myList.add(address);
        myList.add(place);
        HttpSession session = request.getSession();
        session.setAttribute("order", order);
        session.setAttribute("payment", payment);
        session.setAttribute("productList", productList);
        session.setAttribute("orderprodList", orderprodList);
        session.setAttribute("myList", myList);
        response.sendRedirect("CheckoutReview.jsp");
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
