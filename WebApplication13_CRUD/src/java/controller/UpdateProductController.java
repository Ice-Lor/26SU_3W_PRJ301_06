/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Le Nhat Tung
 */
public class UpdateProductController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // xu ly du lieu tu search.jsp
        // lay san pham va hien thi ra man hinh product_form.jsp
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xu ly du lieu tu product-form.jsp
        // Lay du lieu tu product_form.jsp => luu xuong database => quay ve trang search
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
