/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codebrains.callme;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yeswanth Reddy
 */
@WebServlet(name = "StoreCustomerMessages", urlPatterns = {"/StoreCustomerMessages"})
public class StoreCustomerMessages extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = DataBase.getCon();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            getServletContext().getRequestDispatcher("/Header.html").include(request, response);
            
            String se = request.getParameter("txtEmail");
            String sp = request.getParameter("txtPhone");
            String ss = request.getParameter("txtSubject");
             String sd = request.getParameter("txtDesc");
           
            String query = "insert into customermessages(email,phone,subject,description) values(?,?,?,?)";
            PreparedStatement p1 = con.prepareStatement(query);
             p1.setString(1, se);
              p1.setString(2, sp);
            p1.setString(3, ss);
            p1.setString(4, sd);
           
            int c = p1.executeUpdate();
           
             out.print("<h1 align=center><i>Successfully stored.  <a href=/CallMeGuru/CustomerMessages>View Messages</a></i></h1>");
            out.print("<a href=javascript:window.history.back()>BACK</a>");
             con.close();
            getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}