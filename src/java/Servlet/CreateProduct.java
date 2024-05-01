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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author User
 */
@MultipartConfig
public class CreateProduct extends HttpServlet {

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

        if (submit.equals("Create")) {

            ArrayList<Product> pl = null;

            try {
                pl = productDA.getAll();
            } catch (SQLException ex) {
               out.print(ex);
            }
            int intID = productDA.generateID();
            for (int i = 0; i < pl.size(); ++i) {
                Product p = pl.get(i);
                while (p.getProductID().equals("PT" + intID)) {
                    intID++;
                }
            }
            String id = "P" + intID;

            String sID = request.getParameter("staff");
            String name = request.getParameter("name");
            String desc = request.getParameter("desc");
            String strPrice = request.getParameter("price");
            Double price = Double.parseDouble(strPrice);
            String category = request.getParameter("category");
            // get image
            Part p = request.getPart("image");

            try {
                product = new Product(id, sID, name, desc, price, category, null);
                InputStream is = p.getInputStream();
                productDA.addRecord(product, is);
                ArrayList<Product> productList = null;
                try {
                    productList = productDA.getAll();
                } catch (SQLException ex) {
                     out.print(ex);
                }

                HttpSession session = request.getSession();
                session.setAttribute("productList", productList);

                //JOptionPane.showMessageDialog(null, "New Product Added!", "Message", JOptionPane.INFORMATION_MESSAGE);
                response.sendRedirect("ManageProduct.jsp");
            } catch (Exception ex) {
                out.println(ex);
            } finally {

                out.close();

            }

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