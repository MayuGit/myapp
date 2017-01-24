/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author ubuntu
 */
public class ConnectionManager {
    
    static Connection conn;
    static String URL;
    
    public static Connection getConnection(){
        try{
            /*Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mayu", "root", "mint");*/
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/mydb");
            conn = ds.getConnection();
            System.out.println("*****connection granted*******");
        }catch(Exception e){
            System.out.println("!!!!!CONNECTION FAILED!!!!!");
            System.out.println(e);
            return null;
        }
        return conn;
    }
    
}
