/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Model.Product;
import Model.ProductDA;
import java.io.IOException;
import java.io.InputStream;
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
import javax.servlet.http.Part;
import javax.swing.JOptionPane;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author User
 */
@MultipartConfig
public class ProcessProduct extends HttpServlet {

    private Product product;
    private ProductDA productDA;

    public void init() throws ServletException {
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

        String submit = request.getParameter("submit");

        if (submit.equals("CreateProduct")) {

            response.sendRedirect("CreateProduct.jsp");

        } else if (submit.equals("Edit")) {
            String id = request.getParameter("productID");

            Product product = productDA.getRecord(id);

            HttpSession session = request.getSession();
            session.setAttribute("product", product);

            response.sendRedirect("UpdateProduct.jsp");

        } else if (submit.equals("Update")) {
            String id = request.getParameter("id");
            String sid = request.getParameter("sid");
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            String strPrice = request.getParameter("price");
            Double price = Double.parseDouble(strPrice);
            String category = request.getParameter("category");
            
            Part p = request.getPart("image");

            try {
            InputStream is = p.getInputStream();
            Product product = new Product(id,sid, name, desc, price, category,null);
            productDA.updateRecord(product,is);

            ArrayList<Product> productList = null;
            try {
                productList = productDA.getAll();
            } catch (SQLException ex) {
               out.print(ex);
            }

            HttpSession session = request.getSession();
            session.setAttribute("productList", productList);

            //JOptionPane.showMessageDialog(null, "Product " + id + " has been updated!", "Message", JOptionPane.INFORMATION_MESSAGE);
            response.sendRedirect("ManageProduct.jsp");
            }catch (Exception ex) {
                out.println(ex);
            } finally {

                out.close();

            }

        } else {

            //int option = JOptionPane.showConfirmDialog(null, "Are you sure want to delete Product:" + id);
            //if (option == JOptionPane.YES_OPTION) {
            String id = request.getParameter("productID");

            productDA.deleteRecord(id);

            List<Product> productList = null;
            try {
                productList = productDA.getAll();
            } catch (SQLException ex) {
                out.print(ex);
            }
            HttpSession session = request.getSession();
            session.setAttribute("productList", productList);

            //JOptionPane.showMessageDialog(null, "Product " + id + " has been deleted!", "Message", JOptionPane.INFORMATION_MESSAGE);
            //}
            response.sendRedirect("ManageProduct.jsp");
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