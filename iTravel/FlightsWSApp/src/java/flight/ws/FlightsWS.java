/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flight.ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author nacho and 
 */
@WebService(serviceName = "FlightsWS")
@Stateless()
public class FlightsWS {


    /**
     * Web service operation
     * @param id_vuelo
     * @param date
     * @return 
     */
    @WebMethod(operationName = "query_free")
    public int query_free(@WebParam(name = "id_vuelo") int id_vuelo, @WebParam(name = "date") int date) {
        //TODO write your implementation code here:
        try {
            //TODO write your implementation code here:
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2",
                    "pr2", "pr2");
            String query = "select num_free_seats from INFOVUELOS where id_vuelo = " +
                    id_vuelo + " and date = " + date+"" ;
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery();
            rs.next();
            int seats = rs.getInt("num_free_seats");
            return seats;
        } catch (Exception ex) {
            Logger.getLogger(FlightsWS.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

      /**
     * Web service operation
     * @param id_vuelo
     * @param date
     * @return

     */
    @WebMethod(operationName = "book_seat")
    public int book_seat(@WebParam(name = "id_vuelo") int id_vuelo,
            @WebParam(name = "date") int date) {
        //TODO write your implementation code here:
        try {
            int numFreeSeats = this.query_free(id_vuelo, date);
            if(numFreeSeats>0){
                int seats = numFreeSeats-1;
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                Connection conn;
                conn = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2",
                        "pr2", "pr2");
                String query = "update INFOVUELOS set num_free_seats = " +
                        seats +" where id_vuelo = " + id_vuelo +
                        " and date = "+ date + "";                      
                PreparedStatement prepStatement = conn.prepareStatement(query);
                prepStatement.executeUpdate();
                String query2 = "select num_total_seats from INFOVUELOS " +
                        "where id_vuelo = " + id_vuelo +" and date = "+ date + "";
                PreparedStatement prepStatement2 = conn.prepareStatement(query2);
                ResultSet rs = prepStatement2.executeQuery();
                rs.next();
                int totalSeats = rs.getInt("num_total_seats");
                return totalSeats - seats;
            }else{
                System.out.println("No seats available!");
                return -1;
            }
        } catch (Exception ex) {
            Logger.getLogger(FlightsWS.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
     
    }
}