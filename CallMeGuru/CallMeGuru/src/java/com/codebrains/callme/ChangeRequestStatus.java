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
import javax.servlet.http.HttpSession;

/**
 *
 * @author K SOMANADH
 */
@WebServlet(name = "ChangeRequestStatus", urlPatterns = {"/ChangeRequestStatus"})
public class ChangeRequestStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Calendar now = Calendar.getInstance();
        String sid = request.getParameter("request");
        HttpSession session=request.getSession();
        String pid=(String)session.getAttribute("pid");
        String time = (now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
        try {
            Connection con = DataBase.getCon();
            String query = "update problems set provid=?,acceptime=?,status='pickup' where probid=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, Integer.parseInt(pid));
             st.setString(2, time);
              st.setInt(3, Integer.parseInt(sid));
            int c = st.executeUpdate();
            
            if (c > 0) {
                  response.sendRedirect("/CallMeGuru/ViewRequestProviders");
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
