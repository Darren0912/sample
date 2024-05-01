/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Product;
import Model.ProductCart;
import Model.ProductCartDA;
import Model.ProductDA;
import Model.ShoppingCartDA;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
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
public class DisplayCart extends HttpServlet {

    private ShoppingCartDA shopCartDA;
    private ProductCartDA prodCartDA;
    private ProductDA productDA;
    private Product product;

    @Override
    public void init() throws ServletException {
        prodCartDA = new ProductCartDA();
        shopCartDA = new ShoppingCartDA();
        productDA = new ProductDA();
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
        String memberID = request.getParameter("member");
        String CartID = request.getParameter("cart");

        HttpSession session = request.getSession();
        List<ProductCart> prodcartList = prodCartDA.getProductCartRecord(CartID);
        Product product = null;
        List<Product> productInformationList = new ArrayList<Product>();
        String productID = null;
        if (prodcartList.size() != 0) {
            for (int i = 0; i < prodcartList.size(); i++) {
                productID = prodcartList.get(i).getProductID();
                product = productDA.getProductRecord(productID);
                productInformationList.add(product);

            }
            session.setAttribute("productInformationList", productInformationList);
            session.setAttribute("prodcartList", prodcartList);

            response.sendRedirect("Cart.jsp");
        } else {
            response.sendRedirect("EmptyCart.jsp");
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
