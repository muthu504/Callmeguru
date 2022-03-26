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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author muthu
 */
@WebServlet(name = "ViewProviders", urlPatterns = {"/ViewProviders"})
public class ViewProviders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Connection con = DataBase.getCon();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
      getServletContext().getRequestDispatcher("/Header.html").include(request, response);
            String query = "select * from providers";
            PreparedStatement p1 = con.prepareStatement(query);

            ResultSet rs = p1.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int cols = rsmd.getColumnCount();
            out.print("<table align=center border=0 cellpadding=10");
            out.print("<tr style='color:white;background-color:gray'>");
            for (int i = 1; i <= cols; i++) {
                out.print("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.print("</tr>");

            while (rs.next()) {
                String pid=rs.getString(1);
               
                 out.print("<tr><td><a href=/CallMeGuru/ChangeProviderStatus?provider="+pid+" style='color:turquoise'>"+rs.getString(1)+"</a></td>");
                  
                for (int i = 2; i <= cols; i++)
                {
                    out.print("<td>" + rs.getString(i) + "</td>");
                }
                 out.print("<td><a href=/CallMeGuru/ChangeBlockStatus?provider="+pid+" style='color:turquoise'>Block</a></td>");
                out.print("</tr>");
            }
            out.print("</table>");
            con.close();
             out.print("<a href=javascript:window.history.back()>BACK</a>");
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
