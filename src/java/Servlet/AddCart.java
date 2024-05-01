/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.ProductCart;
import Model.ProductCartDA;
import Model.ProductDA;
import Model.Product;
import Model.ShoppingCart;
import Model.ShoppingCartDA;
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
public class AddCart extends HttpServlet {

    private ProductDA productDA;
    private Product product;
    private ProductCart pcart;
    private ProductCartDA pcartDA;
    private ShoppingCartDA shopcartDA;
    private ShoppingCart shopcart;

    @Override
    public void init() throws ServletException {
        pcartDA = new ProductCartDA();
        productDA = new ProductDA();
        shopcartDA = new ShoppingCartDA();
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
        String memberid = request.getParameter("member");
        String id = request.getParameter("cart");
        //Product Cart ID
        ArrayList<ProductCart> prodcartl = null;

            try {
               prodcartl = pcartDA.getAllRecord();
            } catch (Exception ex) {
              out.print(ex);
            }
            int intID = pcartDA.generateID();
            for (int i = 0; i < prodcartl.size(); ++i) {
                ProductCart pc = prodcartl.get(i);
                while (pc.getProductCartID().equals("PC" + intID)) {
                    intID++;
                }
            }
           
            String pcartid = "PC" + intID;
        String pdid = request.getParameter("product");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int size = Integer.parseInt(request.getParameter("size"));
        double price = Double.parseDouble(request.getParameter("price"));

        try {

            Product product = new Product();
            product = productDA.getRecord(pdid);

            pcartDA.addRecord(pcartid, id, pdid, quantity, size);
            HttpSession session = request.getSession();
            session.setAttribute("productCart", product);
            response.sendRedirect("TheBeast.jsp");

        } catch (Exception ex) {

            out.print(ex);

        } finally {

            out.close();
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
