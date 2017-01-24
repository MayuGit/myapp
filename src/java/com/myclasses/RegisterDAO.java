/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubuntu
 */
public class RegisterDAO {
    
    static Connection conn;
    static ResultSet rs;

    public static boolean createUser(RegisterBean user) {
        
         PreparedStatement stmt = null;
            
            String name = user.getFirstname();
            String password = user.getPassword();
            boolean valid = validUser(name);
            if(valid){
            String query = "INSERT INTO users (firstname, lastname, email, password) VALUES (?,?,?,?)";
             try {
            System.out.println("query:"+query);
            conn = ConnectionManager.getConnection();
            stmt= conn.prepareStatement(query);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
                    
           
            stmt.executeUpdate();
            
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            finally
            {
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
                    conn.close();
                    } catch (Exception e) {
                    }
                    
                    conn = null;
                    }
                    }
             return true;
            }
            else
                return false;
    }

    private static boolean validUser(String name) {
        Statement stmt = null;
            
            
            String query = "Select firstname from users where firstname='"+name+"'";
             try {
            System.out.println("query:"+query);
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            boolean more = rs.next();
            
            if(more){
                System.out.println("!!!!!!! USER ALREADY EXISTS!!!!!");
                return false;
            }
            else
                return true;
            
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            finally
            {
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
                    conn.close();
                    } catch (Exception e) {
                    }
                    
                    conn = null;
                    }
                    }
            
            return false;
    }
    
}
