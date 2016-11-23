/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restfulpam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * GET method to create the database
     */
    @Path("create")
    @GET
    @Produces("text/html")
    public String create() {        
          
        Connection connection = null;
        try{
            Class.forName("org.sqlite.JDBC");                     
          
            // create a database connection
            
            // CHANGE THE PATH TO YOUR DATABASE
            connection = DriverManager.getConnection("jdbc:sqlite:/home/fran/dbrest.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
          
            statement.executeUpdate("drop table if exists vuelo_fecha");
            statement.executeUpdate("create table vuelo_fecha (id_vuelo integer not null, fecha integer not null, num_plazas_ocupadas integer not null, num_plazas_max integer not null,primary key (id_vuelo, fecha))");

            statement.executeUpdate("insert into vuelo_fecha values (1,20141130, 10,100)");
            statement.executeUpdate("insert into vuelo_fecha values (2,20141130, 20,200)");
            statement.executeUpdate("insert into vuelo_fecha values (3,20141130, 30,300)");
            
        }catch (Exception e)
        {         System.err.println(e.getMessage());
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }   
        return "<html><head></head> <body> Database Created </body></html>";
    }

    /**
     * GET method for checking free seats given an id and a date
     * @param id
     * @param fecha
     * @return 
     */
    @Path("free")
    @GET    
    @Produces("text/html")
    public String freeSeats(@QueryParam("id") String id, 
                            @QueryParam("fecha") String fecha) {
        Connection connection = null;
         try{
            Class.forName("org.sqlite.JDBC");                     

            connection = DriverManager.getConnection("jdbc:sqlite:/home/fran/dbrest.db");
            int freeSeats = this.getFreeSeats(connection, id, fecha);
            
            return "<html><head></head> <body> Free seats: " +freeSeats+ "</body></html>";
            
        }catch (ClassNotFoundException | SQLException e)
        {         System.err.println(e.getMessage());
            return "<html><head></head> <body> Error processing request</body></html>";
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }        
    }
    
    /**
     * POST method for checking free seats given an id, a date and a number
     * of seats
     * 
     * @param id
     * @param fecha
     * @param seats
     * @return 
     */
    @Path("reserve")   
    @POST    
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/html")
    public String reserveSeats(  @FormParam("id") String id, 
                                @FormParam("fecha") String fecha, 
                                @FormParam("seats") String seats) 
    {
        Connection connection = null;
         try{
            Class.forName("org.sqlite.JDBC");                     

            connection = DriverManager.getConnection("jdbc:sqlite:/home/fran/dbrest.db");
            int freeSeats = this.getFreeSeats(connection, id, fecha);
            int querySeats = Integer.parseInt(seats);
            if(freeSeats - querySeats < 0){
                return "<html><head></head> <body> Sorry, there are just "+freeSeats
                        +" available seats. You asked for "+
                        querySeats+" seats</body></html>";
            }
            int total = this.getTotalSeats(connection, id, fecha);
            int queryOccupied = total - freeSeats + querySeats;
            String query = "update vuelo_fecha set num_plazas_ocupadas = " +
                    queryOccupied +" where id_vuelo = " + id +
                    " and fecha = "+ fecha + ""; 
            PreparedStatement prepStatement = connection.prepareStatement(query);
            prepStatement.executeUpdate();
            return "<html><head></head> <body> Seat reserved!</body></html>";
        }catch (ClassNotFoundException | SQLException e)
        {         System.err.println(e.getMessage());
            return "<html><head></head> <body> Error processing request</body></html>";
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
    }
    
    public int getFreeSeats(Connection conn, String id, String fecha) throws SQLException{
            String query = "select num_plazas_ocupadas,num_plazas_max from vuelo_fecha "+
                    "where id_vuelo = " + id + " and fecha = "+ fecha+"";
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery();
            rs.next();
            return rs.getInt("num_plazas_max") - rs.getInt("num_plazas_ocupadas");
    }
    public int getTotalSeats(Connection conn, String id, String fecha) throws SQLException{
           String query = "select num_plazas_max from vuelo_fecha "+
                    "where id_vuelo = " + id + " and fecha = "+ fecha+"";
            PreparedStatement prepStatement = conn.prepareStatement(query);
            ResultSet rs = prepStatement.executeQuery();
            rs.next();
            return rs.getInt("num_plazas_max");
    }
    
}