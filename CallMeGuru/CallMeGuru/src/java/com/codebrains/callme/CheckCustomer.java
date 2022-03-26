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


@WebServlet(name = "CheckCustomer", urlPatterns = {"/CheckCustomer"})
public class CheckCustomer extends HttpServlet {
   

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
                Connection con =DataBase.getCon();
        
        String su = request.getParameter("txtUser");
        String sp = request.getParameter("txtPwd");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
         getServletContext().getRequestDispatcher("/Header.html").include(request, response);
         
       
        String query = "select * from customer where username=? and password=?";
        PreparedStatement st = con.prepareStatement(query);
            st.setString(1, su);
            st.setString(2, sp);
              ResultSet rs=st.executeQuery();
        

        if (rs.next()) {
             HttpSession session = request.getSession();
                session.setAttribute("cid", rs.getString("cid"));
            response.sendRedirect("/CallMeGuru/CustomerHome.html");
        } 
        else {
            out.print("<h1 align=center><i>incorrect username and password</i></h1>");
              out.println("<h1><center> <a href=CustomerLogin.html>Login Here</a></h1>");

        }
        
         getServletContext().getRequestDispatcher("/Footer.html").include(request, response);
        }catch (Exception ee) {
            ee.printStackTrace();
        }
        
    }
    

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

}


