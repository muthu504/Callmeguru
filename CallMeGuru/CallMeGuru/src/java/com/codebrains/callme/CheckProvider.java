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
import javax.servlet.http.HttpSession;


@WebServlet(name = "CheckProvider", urlPatterns = {"/CheckProvider"})
public class CheckProvider extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out=response.getWriter();
         String su = request.getParameter("txtUser");
        String sp = request.getParameter("txtPwd");
         response.setContentType("text/html");
          getServletContext().getRequestDispatcher("/Header.html").include(request, response);
        try{
                Connection con =DataBase.getCon();
               String query = "select * from providers where username=? and password=?";
        PreparedStatement st = con.prepareStatement(query);
            st.setString(1, su);
            st.setString(2, sp);
              ResultSet rs=st.executeQuery();
        
        if (rs.next()) 
        {
            HttpSession session = request.getSession();
                session.setAttribute("pid", rs.getString("pid"));
                session.setAttribute("serv", rs.getString("typeofservice"));
                 if (rs.getString("status").equals("Active"))
                {
            response.sendRedirect("/CallMeGuru/ProviderHome.html");
                }
                 else if(rs.getString("status").equals("inactive"))
                 {
                     out.print("<font color=red size=30><center>Your details under processing<br></font>");
                 }
                 else if(rs.getString("status").equals("Block"))
                 {
                     out.print("<font color=red size=30><center>Your Blocked please contact Admin<br></font>");
                 }
        
        else {
            out.print("<h1 align=center><i>Wrong Credentials</i></h1>");
              out.println("<h1><center> <a href=ProviderRegister.html>New user then Register Here</a></h1>");

           }
        }
         getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
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


