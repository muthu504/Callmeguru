
package com.codebrains.callme;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author siva
 */

public class DataBase
{
   public static Connection getCon()
    {
        Connection con = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:XE", "guru", "guru");
             }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return con;
    }
    public boolean isequals(String au,String ap)
    {
        try 
        {
            
            Connection con = DataBase.getCon();
            String query = "select * from admin where username=? and password=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, au);
            st.setString(2, ap);
           ResultSet rs=st.executeQuery();
           
           if(rs.next()) {
               
             return true;
              
           }
           else {
               return false;
             
           }
    }
        catch (Exception ee) {
            ee.printStackTrace();
        }
         return false;
    }
    public static int getNextId(String table) {
        Connection con;
        int max=1;
        try {
            String query = "";
            con = getCon();
            if (table.equals("customer")) {
                query = "select max(cid) from customer";
            } else if (table.equals("providers")) {
                query = "select max(pid) from providers";
            }else if (table.equals("emergency")) {
                query = "select max(req_id) from emergency";
            }  
            else
                query = "select max(probid) from problems";
            
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                max = rs.getInt(1);
                max=max+1;
            } else {
                max = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return max;
    }


    
    
}
