/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.myclasses.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ubuntu
 */
@WebServlet(urlPatterns = {"/UserExists"})
public class UserExists extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            System.out.println("I am in ajaxtest servlet");
            ConnectionManager connmgr = new ConnectionManager();
            System.out.println("getting conn: UserExists.processRequest()");
            Connection conn = connmgr.getConnection();
            
            Statement stmt = null;
            ResultSet rs = null;
            try {
                String query = "Select firstname from users where firstname='" + request.getParameter("q") + "'";
                System.out.println("Ajax query:" + query);
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    System.out.println("user id exists!");
                    out.print("true");
                }
                rs.close();
                stmt.close();
                //connmgr.closeConnection(conn);
                //ResultSet rs = 
            } catch (Exception e) {
                System.out.println(e);
            } 
            finally {
                System.out.println("finally : UserExists()");
                if (rs != null) {
                    try {
                        rs.close();
                    } catch (Exception e) {
                    }
                    rs = null;
                }

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Exception e) {
                    }
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

            //out.println("AjaxTesting");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
