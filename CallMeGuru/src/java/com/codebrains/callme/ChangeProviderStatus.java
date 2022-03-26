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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author K SOMANADH
 */
@WebServlet(name = "ChangeProviderStatus", urlPatterns = {"/ChangeProviderStatus"})
public class ChangeProviderStatus extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sid = request.getParameter("provider");
        try {
            Connection con = DataBase.getCon();
            String query = "update providers set status='Active',reason='Need' where pid=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, Integer.parseInt(sid));
            int c = st.executeUpdate();
            
            if (c > 0) {
                 String query1 = "select * from providers where pid=?";
                 PreparedStatement st1 = con.prepareStatement(query1);
                 st1.setInt(1, Integer.parseInt(sid));
                   ResultSet rs = st1.executeQuery();
                   while(rs.next()){
                   Mailing.mail(rs.getString("email"),rs.getInt("pid"),rs.getString("username"),rs.getString("password"));
                   response.sendRedirect("/CallMeGuru/ViewProviders");
                   }
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }


  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

}
}
