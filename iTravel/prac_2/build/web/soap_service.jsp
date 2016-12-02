<%-- 
    Document   : index
    Created on : 22-nov-2016, 10:54:47
    Author     : nacho and fran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FlightSeats</title>
                <!-- Main style css -->
        <link rel="stylesheet" href="style.css">
    </head>
    <body>
        <div align="center">
            <h1>Check free seats</h1>
            <form action="FlightsServletClient" method="post">
                Flight ID: <input name="id_vuelo" type="number"></input>
                Date: <input name="date" type="number"></input>
                <input type="submit" name="checkFree" value="Check"/>
            </form>
            <h1>Book a seat</h1>
            <form action="bookSeat" method="post">
                Flight ID: <input name="id_vuelo" type="number"></input>
                Date: <input name="date" type="number"></input>
                <input type="submit" name="book" value="Book"/>
            </form>
            <br><br>
            <button onclick="location.href='/prac_2/menu.html'">Return</button>
        </div>
    </body>
</html>
