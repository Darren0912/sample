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
import Model.Product;
import Model.ProductCart;
import Model.ProductCartDA;
import Model.ProductDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class PaymentMethod extends HttpServlet {

    private ProductDA productDA;
    private BeastOrder order;
    private BeastOrderDA orderDA;
    private ProductCartDA prodcartDA;
    private Order_ProductDA orderproductDA;

    @Override
    public void init() throws ServletException {
        orderDA = new BeastOrderDA();
        prodcartDA = new ProductCartDA();
        orderproductDA = new Order_ProductDA();
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
        String choice = request.getParameter("paymentmethod");
        Double total = Double.parseDouble(request.getParameter("total"));
        String cartID = request.getParameter("cart");
        String orderID = orderDA.generateID();

        double deliveryfee = 0;
        if (total >= 200) {
            deliveryfee = 0;
        } else {
            deliveryfee = 25;
        }
        Order_Product orderproduct=new Order_Product();
        order = new BeastOrder(orderID, cartID, "packaging", deliveryfee);
        orderDA.addRecord(order);
        List<ProductCart> prodcartList = prodcartDA.getProductCartRecord(cartID);

        List<Order_Product> orderProductList = new ArrayList<Order_Product>();
        String productID = null;
        int quantity = 0;
       
            for (int i = 0; i < prodcartList.size(); i++) {
                productID = prodcartList.get(i).getProductID();
                quantity = prodcartList.get(i).getQuantity();
                //Order_Product ID
                ArrayList<Order_Product> orderproductl = null;
               
               orderproductl = orderproductDA.getAllRecord();
               
                int intID = orderproductDA.generateID();
                if (orderproductl != null) {
                    for (int j = 0; j < orderproductl.size(); ++j) {
                        Order_Product op = orderproductl.get(j);
                        while (op.getOrderProductID().equals("OP" + intID)) {
                            intID++;
                        }
                    }
                }
                String orderprodid = "OP" + intID;
                orderproduct = new Order_Product(orderprodid, orderID, productID, quantity);
                orderproductDA.addRecord(orderproduct);
            }
        

        prodcartDA.deleteRecord(cartID);
        HttpSession session = request.getSession();
        session.setAttribute("order",order);
        if (choice.equals("cash") == true) {
            response.sendRedirect("PaymentDetails.jsp");
        } else {
            response.sendRedirect("CreditDebitCard.jsp");
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
