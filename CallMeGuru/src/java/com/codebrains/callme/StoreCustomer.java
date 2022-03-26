/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codebrains.callme;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shiva
 */
@WebServlet(name = "StoreCustomer", urlPatterns = {"/StoreCustomer"})
public class StoreCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = DataBase.getCon();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
 getServletContext().getRequestDispatcher("/Header.html").include(request, response);
            String su = request.getParameter("txtName");
            String sp = request.getParameter("txtPwd");
            String se = request.getParameter("txtEmail");
            String sph = request.getParameter("txtPhone");
            String sg = request.getParameter("rdGender");
            String sad = request.getParameter("txtAddrs");
            String query = "insert into customer(cid,username,password,email,phone,gender,address) values(?,?,?,?,?,?,?)";
            PreparedStatement p1 = con.prepareStatement(query);
            p1.setInt(1, DataBase.getNextId("customer"));
            p1.setString(2, su);
            p1.setString(3, sp);
            p1.setString(4, se);
            p1.setString(5, sph);
            p1.setString(6, sg);
            p1.setString(7, sad);
            int c = p1.executeUpdate();
            con.close();
            out.print("<h1 align=center><i>Successfully Registered. Please <a href=/CallMeGuru/CustomerLogin.html>Login</a></i></h1>");
            getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}




