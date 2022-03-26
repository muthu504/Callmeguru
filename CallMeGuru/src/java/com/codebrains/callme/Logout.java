/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codebrains.callme;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shiva
 */
@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session =request.getSession(true);
        session.invalidate();
         getServletContext().getRequestDispatcher("/Header.html").include(request, response);
        out.print("<h1 align=center><i>Logout Successfully.</i></h1>");
        out.print("<h1 align=center><i>Thank You for Visit Again.</i></h1>");
        out.println("<h1><center> <a href=HomePage.html>Home Page</a></h1>");
 getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}