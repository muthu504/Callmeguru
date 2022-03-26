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
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author K SOMANADH
 */
@WebServlet(name = "StoreEmergency", urlPatterns = {"/StoreEmergency"})
public class StoreEmergency extends HttpServlet {
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Connection con = DataBase.getCon();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            getServletContext().getRequestDispatcher("/Header.html").include(request, response);
            Calendar now = Calendar.getInstance();
            String su = request.getParameter("txtUsr");
            String sp = request.getParameter("txtPhone");
            String sd = request.getParameter("txtDesc");
             String ss = request.getParameter("txtloc");
           String time = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
            String query = "insert into emergency(req_id,pname,phone,description,location,request_time) values(?,?,?,?,?,?)";
            PreparedStatement p1 = con.prepareStatement(query);
            p1.setInt(1, DataBase.getNextId("emergency"));
            p1.setString(2, su);
            p1.setString(3, sp);
            p1.setString(4, sd);
            p1.setString(5, ss);
            p1.setString(6, time);
           
            int c = p1.executeUpdate();
           
             out.print("<h1 align=center><i>We will Intract soon</i></h1>");
            out.print("<a href=javascript:window.history.back()>BACK</a>");
             con.close();
            getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}