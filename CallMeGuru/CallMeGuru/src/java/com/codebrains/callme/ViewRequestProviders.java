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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author K SOMANADH
 */
@WebServlet(name = "ViewRequestProviders", urlPatterns = {"/ViewRequestProviders"})
public class ViewRequestProviders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
      Connection con = DataBase.getCon();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
      getServletContext().getRequestDispatcher("/Header.html").include(request, response);
       HttpSession session=request.getSession();
        String serv=(String)session.getAttribute("serv");
            String query = "select * from problems where typeofservice=?";
            
            PreparedStatement p1 = con.prepareStatement(query);
            p1.setString(1,serv);

            ResultSet rs = p1.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
             out.print("<table align=center border=0 cellpadding=10");
            out.print("<tr style='color:white;background-color:gray'>");
            for (int i = 1; i <= cols; i++) {
                out.print("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.print("</tr>");

            while (rs.next()) 
               
                {
                String rid=rs.getString(1);
               
                 out.print("<tr><td><a href=/CallMeGuru/ChangeRequestStatus?request="+rid+" style='color:turquoise'>"+rs.getString(1)+"</a></td>");
                  
                for (int i = 2; i <= cols; i++)
                {
                    out.print("<td>" + rs.getString(i) + "</td>");
                }
                 out.print("<td><a href=/CallMeGuru/ChangeRequestEndStatus?request="+rid+" style='color:turquoise'>Click to Complete</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");
            con.close();
            
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
