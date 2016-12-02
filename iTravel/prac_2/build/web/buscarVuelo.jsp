<%-- 
    Document   : buscarVuelo
    Created on : 21-oct-2016, 10:30:30
    Author     : ignacio.reimat
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>iTravel-Search Flight</title>
    </head>
    <body>
        <div align="center">
            <h1>Search a flight      
            </h1><br>
        </div>
        <div align="center">
            <form action="buscarVuelo" method="post" >
               Company: <select name="company">
                    <c:forEach items="${companies_list}" var="company">
                    <option value="<c:out value="${company}"/>">
                       <c:out value="${company}"/>
                    </option>
                    </c:forEach>
                </select>
                Origin: <select name="origin">
                    <c:forEach items="${origins_list}" var="origin">
                    <option value="<c:out value="${origin}"/>">
                       <c:out value="${origin}"/>
                    </option>
                    </c:forEach>
                </select>
               Destination: <select name="destination">
                    <c:forEach items="${dest_list}" var="dest">
                    <option value="<c:out value="${dest}"/>">
                       <c:out value="${dest}"/>
                    </option>
                    </c:forEach>
                </select>
                <input type="submit" value="Buscar Vuelo"/>
            </form>
        </div><br>
         <div style="align-self: center;">
            <table class="tg" align="center">
            <tr>
              <th >Flights Found:<br></th>
            </tr>
            <tr>
              <td >Flight ID<br></td>
              <td >Flight num<br></td>
              <td >Company</td>
              <td >Origin</td>
              <td >Departure</td>
              <td >Destination</td>
              <td >Arrival</td>
              <%int i = 0;%>
           <c:forEach items="${found}" var="found">
                   <% if(i%7==0){%><tr><%}%>
            <td><c:out value="${found.toString()}"/></td>
             <%i++;%>
               </c:forEach>
            
        </table>
             
        </div>
           <div align="center">
               <br><br>
               <image src="https://lh5.ggpht.com/z08ruXs68ssY5oVmbPmtbdVMplPRnmma-ec3ZG6IIjWao7vmWXMwooLrp-R77jiP51rB=w300"/>
           </div>
           <div align="right">
               <button onclick="location.href='/prac_2/menu.html'">Back to menu</button>
           </div>
    </body>
    <style>
	table, td, tr {
	    border: 1px solid black;
            background-color: #13D7D1;
	}
    </style>
</html>