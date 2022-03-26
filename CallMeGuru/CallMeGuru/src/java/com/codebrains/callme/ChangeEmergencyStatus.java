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
@WebServlet(name = "ChangeEmergencyStatus", urlPatterns = {"/ChangeEmergencyStatus"})
public class ChangeEmergencyStatus extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Calendar now = Calendar.getInstance();
        String sid = request.getParameter("rid");
        String time = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));

        try {
            Connection con = DataBase.getCon();
            String query = "update emergency set status='solved', acceptime=? where req_id=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1,time);
            st.setInt(2, Integer.parseInt(sid));
            int c = st.executeUpdate();
            
           if (c > 0)
                  {
                   response.sendRedirect("/CallMeGuru/ViewEmergency");
                   }
            
        } 
        catch (Exception ee) {
            ee.printStackTrace();
        }
    }


  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

}
}
