/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasses;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author ubuntu
 */
public class ConnectionManager {
    
    Connection conn;
    String URL;
    static int openCount;
    static int closeCount;
    
    public Connection getConnection(){
        try{
            /*Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mayu", "root", "mint");*/
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/mydb");
            conn = ds.getConnection();
            openCount+=1;
            System.out.println("*****connection granted*******>"+openCount);
        }catch(Exception e){
            System.out.println("!!!!!CONNECTION FAILED!!!!!");
            System.out.println(e);
            return null;
        }
        return conn;
    }
    
    public void closeConnection(Connection conn){
        try {
            conn.close();
            closeCount+=1;
            System.out.println("========connection closed======>"+closeCount);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
