/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightsappclient;

import flight.ws.SQLException_Exception;

/**
 *
 * @author fran
 */
public class FlightsAppClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException_Exception {
        // TODO code application logic here
        int idVuelo = 1;
        int date = 12356789;
        int free = queryFree(idVuelo, date);
        System.out.println("Free Seats: " + free);
        int occupied = bookSeat(idVuelo, date);
        System.out.println("Including your reserve, there are " + occupied +
                " seats occupied");
        
    }

    private static int bookSeat(int idVuelo, int date) throws SQLException_Exception {
        flight.ws.FlightsWS_Service service = new flight.ws.FlightsWS_Service();
        flight.ws.FlightsWS port = service.getFlightsWSPort();
        return port.bookSeat(idVuelo, date);
    }

    private static int queryFree(int idVuelo, int date) throws SQLException_Exception {
        flight.ws.FlightsWS_Service service = new flight.ws.FlightsWS_Service();
        flight.ws.FlightsWS port = service.getFlightsWSPort();
        return port.queryFree(idVuelo, date);
    }
    
}
