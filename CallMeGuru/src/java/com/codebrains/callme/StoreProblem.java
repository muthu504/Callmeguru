/*
 * To change this template, choose Tools | Templates
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author shiva
 */
@WebServlet(name = "StoreProblem", urlPatterns = {"/StoreProblem"})
public class StoreProblem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   try {
            Connection con = DataBase.getCon();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
 getServletContext().getRequestDispatcher("/Header.html").include(request, response);
 HttpSession session = request.getSession();
        String cid = (String) session.getAttribute("cid");
        
            Calendar now = Calendar.getInstance();
            String ss = request.getParameter("drpService");
            String dis = request.getParameter("txtDesc");
            String loc = request.getParameter("txtLoc");
            String sph = request.getParameter("txtPhone");
            String time = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
            String query = "insert into problems(probid,cid,typeofservice,description,location,request_time,phone) values(?,?,?,?,?,?,?)";
            PreparedStatement p1 = con.prepareStatement(query);
            p1.setInt(1, DataBase.getNextId("problems"));
            p1.setString(2, cid);
            p1.setString(3, ss);
            p1.setString(4, dis);
            p1.setString(5, loc);
            p1.setString(6, time);
            p1.setString(7, sph);
           
            int c = p1.executeUpdate();
             if(c>0)
            {
            
            con.close();
            out.print("<h1 align=center><i>Successfully Requested</i></h1>");
            getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
            }
            } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}