/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasses;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubuntu
 */
public class UserDAO {
    
    
    
    

    public static UserBean login(UserBean user) {
        
       
            Statement stmt = null;
            Connection conn =null;
            ResultSet rs =null;
            String name = user.getName();
            String password = user.getPassword();
            ConnectionManager connmgr = new ConnectionManager();
            
            String query = "Select email,lastname from users where firstname='"+name+"' AND password='"+password+"'";
             try {
            System.out.println("query:"+query);
            System.out.println("getting conn: UserDAO.login()");
            conn = connmgr.getConnection();
            stmt = conn.createStatement();
             rs = stmt.executeQuery(query);
            boolean more = rs.next();
            
            if(!more){
                System.out.println("!!!!!!! NOT A VALID USER!!!!!");
                user.setValid(false);
            }
            else{
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                System.out.println("******Welcome "+ lastname);
                user.setLastname(lastname);
                user.setEmail(email);
                user.setValid(true);
            }
            rs.close();
                stmt.close();
                //connmgr.closeConnection(conn);
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            finally
            {
                System.out.println("finally : UserDAO.login()");
                    if (rs != null)	{
                    try {
                    rs.close();
                    } catch (Exception e) {}
                    rs = null;
                    }
                    
                    if (stmt != null) {
                    try {
                    stmt.close();
                    } catch (Exception e) {}
                    stmt = null;
                    }
                    
                    if (conn != null) {
                    try {
                    connmgr.closeConnection(conn);
                    } catch (Exception e) {
                    }
                    
                    conn = null;
                    }
                    }
            
            return user;
        
    }
    
}
