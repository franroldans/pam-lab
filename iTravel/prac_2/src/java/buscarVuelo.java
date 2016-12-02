/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

public class buscarVuelo extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String previousPage = request.getParameter("previous_page");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2", "pr2", "pr2");       
            
            String company = request.getParameter("company");
            String origin = request.getParameter("origin");
            String destination = request.getParameter("destination");
            ArrayList companiesList = this.getCompanies(connection);
            request.setAttribute("companies_list", companiesList);
            request.setAttribute("origins_list", this.getOrigins(connection));
            request.setAttribute("dest_list", this.getDestinations(connection));
            if(previousPage == null){
               request.setAttribute("found", this.searchFlight(connection, company, origin, destination));
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("buscarVuelo.jsp");
            dispatcher.forward(request, response );
            response.sendRedirect("prac_2/buscarVuelo.jsp");
        }
        
    }
    protected ArrayList getCompanies(Connection conn) throws SQLException{
        ArrayList companies = new ArrayList();
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
        String query = "SELECT companyia FROM vuelos ";
        PreparedStatement preStatement = conn.prepareStatement(query);
        ResultSet rs = preStatement.executeQuery();
        while(rs.next()){
            String comp = rs.getString("companyia");
            if(!companies.contains(comp))
            {
                companies.add(comp);
            }
        }
        return companies;
    }
    
    protected ArrayList getOrigins(Connection conn) throws SQLException{
        ArrayList origen = new ArrayList();
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
        String query = "SELECT origen FROM vuelos ";
        PreparedStatement preStatement = conn.prepareStatement(query);
        ResultSet rs = preStatement.executeQuery();
        while(rs.next()){
            String orig = rs.getString("origen");
            if(!origen.contains(orig))
            {
                origen.add(orig);
            }
        }
        return origen;
    }
    
    protected ArrayList getDestinations(Connection conn) throws SQLException{
        ArrayList destination = new ArrayList();
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
        String query = "SELECT destino FROM vuelos ";
        PreparedStatement preStatement = conn.prepareStatement(query);
        ResultSet rs = preStatement.executeQuery();
        while(rs.next()){
            String dest = rs.getString("destino");
            if(!destination.contains(dest))
            {
                destination.add(dest);
            }
        }
        return destination;
    }
    
    protected List searchFlight(Connection conn, String company, String origin,
          String destination) throws SQLException{
        List flights = new ArrayList();
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
        String query = "select * from vuelos where companyia = '" + company +"' and origen = '" + origin + "' and destino = '"+destination +"'";
        PreparedStatement preStatement = conn.prepareStatement(query);

        ResultSet rs = preStatement.executeQuery();
        while(rs.next()){
            flights.add(rs.getInt("id_vuelo")); 
            flights.add(rs.getString("num_vuelo"));
            flights.add(rs.getString("companyia"));
            flights.add(rs.getString("origen"));
            flights.add(rs.getString("hora_salida"));
            flights.add(rs.getString("destino"));
            flights.add(rs.getString("hora_llegada"));
        }
        return flights;
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buscarVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(buscarVuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(buscarVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(buscarVuelo.class.getName()).log(Level.SEVERE, null, ex);
        }
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