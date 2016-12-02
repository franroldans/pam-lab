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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/Practica_2/altaVuelo"})
public class altaVuelo extends HttpServlet {
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
        String queryNum = request.getParameter("num");
        String queryCompany= request.getParameter("company");
        String queryOrigin = request.getParameter("origin");
        String queryDeparture = request.getParameter("departure");
        String queryDestination = request.getParameter("destination");
        String queryArrival = request.getParameter("arrival");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;create=true", "pr2", "pr2");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");  
            out.println("</head>");
            out.println("<body>");
            if(this.addFlightIfPossible(connection, queryNum, queryCompany, queryOrigin, queryDeparture, queryDestination, queryArrival)>0){
                out.println("Successful alta Vuelo"); 
                response.sendRedirect("menu.html");
            }else{
                out.println("[ERROR]: This number of flight already added");
                response.sendRedirect("error?previous_page=alta_vuelo");
            }
            out.println("</body>");
            out.println("</html>");
            connection.close();
        }
    }

    protected int addFlightIfPossible(Connection conn, String num, String company, String origin,
          String departure, String destination, String arrival) throws SQLException{
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
         try ( 
            ResultSet rs = statement.executeQuery("SELECT num_vuelo FROM vuelos where "+
                    "num_vuelo = '"+ num+"'")) {
                    rs.next();
                    int num_vuelo = Integer.parseInt(rs.getString("num_vuelo"));
                    if(num_vuelo>0){
                        rs.close();
                        return -1;
                    }else{
                        return 0;
                    }
                }catch(Exception ex){
                        this.addFlight(conn, num, company, origin, departure, destination, arrival);
                        return 1;
                }
    }
    
    protected void addFlight(Connection conn, String num_vuelo, String company, String origin,
          String departure, String destination, String arrival) throws SQLException{
        Statement statement = conn.createStatement();
        statement.setQueryTimeout(30);
        String query = "select max(id_vuelo) from vuelos";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int id_vuelos = rs.getInt(1) + 1;
        PreparedStatement preStatement = conn.prepareStatement("insert into vuelos (id_vuelo,num_vuelo,companyia,origen,hora_salida,destino,hora_llegada) "
                    + " values(?,?,?,?,?,?,?)");
        preStatement.setInt(1,id_vuelos);
        preStatement.setString(2,num_vuelo);
        preStatement.setString(3,company);
        preStatement.setString(4,origin);
        preStatement.setString(5,departure);
        preStatement.setString(6,destination);
        preStatement.setString(7,arrival);
        preStatement.executeUpdate();
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
            Logger.getLogger(altaVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(altaVuelo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(altaVuelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(altaVuelo.class.getName()).log(Level.SEVERE, null, ex);
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