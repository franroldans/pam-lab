/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

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
        String queryUser= request.getParameter("user");
        String queryPassword = request.getParameter("pswd");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Redirect page</title>");            
            try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2", "pr2", "pr2");
            if(this.isUserSigned(connection, queryUser, queryPassword)){              
             out.println("<meta http-equiv='refresh' content='0; url=menu.html'>");
            }
            else {
                out.println("<meta http-equiv='refresh'" +
                     "content='0; url=/prac_2/error?previous_page=login'>");            
            }
            connection.close();
            }catch(SQLException | ClassNotFoundException se){
                out.println("<meta http-equiv='refresh'" +
                     "content='0; url=/prac_2/error?previous_page=login'>"); 
            }
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
     /**
     * Checks if a query of a user and a password is in the users Database.
     * 
     * @param conn Driver Manager connection.
     * @param queryUser query user.
     * @param queryPswd query password.
     * @throws SQLException if any error related to the database.
     */
    
    protected boolean isUserSigned(Connection conn, String queryUser, String queryPswd) throws SQLException{
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
        try ( 
            ResultSet rs = statement.executeQuery("SELECT id_usuario FROM usuarios "+
                    "where id_usuario= '"+queryUser+"' and password = '"+
                    queryPswd+"'")) {
                rs.next();
                String user = rs.getString("id_usuario");
                if(user != null){
                    rs.close();
                    return true;
                }else{
                    rs.close();
                    return false;
                }
                    
        }catch(Exception ex){
            return false;
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