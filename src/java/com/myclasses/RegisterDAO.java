/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasses;

import java.sql.Connection;
import java.sql.Date;
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
    
    Connection conn;
    ResultSet rs;

    public boolean createUser(RegisterBean user) {
        
         PreparedStatement stmt = null;
         ConnectionManager connmgr=null;
            
            String name = user.getFirstname();
            String password = user.getPassword();
            boolean valid = validUser(name);
            if(valid){
            String query = "INSERT INTO users (firstname, lastname, email, password,bdate) VALUES (?,?,?,?,?)";
             try {
            System.out.println("query:"+query);
            System.out.println("getting conn: RegisterDAO.createUser()");
            connmgr = new ConnectionManager();
            
            conn = connmgr.getConnection();
            stmt= conn.prepareStatement(query);
            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            System.out.println("dates : >>>>> "+user.getBdate().toString());
            stmt.setDate(5, (Date.valueOf(user.getBdate())));
           
            stmt.executeUpdate();
            stmt.close();
            //connmgr.closeConnection(conn);
            
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            finally
            {
                System.out.println("finally: RegisterDAO.createUser()");
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
             return true;
            }
            else
                return false;
    }

    private boolean validUser(String name) {
        Statement stmt = null;
        ConnectionManager connmgr=null;    
            
            String query = "Select firstname from Mayu.users where firstname='"+name+"'";
             try {
            System.out.println("query:"+query);
            System.out.println("getting conn: RegisterDAO.validUser()");
            connmgr = new ConnectionManager();
            conn = connmgr.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            boolean more = rs.next();
            
            if(more){
                System.out.println("!!!!!!! USER ALREADY EXISTS!!!!!");
                rs.close();
                stmt.close();
                //connmgr.closeConnection(conn);
                return false;
            }
            else{
                rs.close();
                stmt.close();
                //connmgr.closeConnection(conn);
                return true;
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
             
            finally
            {
                System.out.println("finally: RegisterDAO.validateUser()");
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
            
            return false;
    }
    
}
