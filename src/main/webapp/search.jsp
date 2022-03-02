<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="/resources/style.css">
</head>

<body>

<%@page import="java.util.List"%>      <%--Importing all the dependent classes--%>
<%@page import="entities.Deal"%>
<%@page import="entities.Flight"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.*"%>
<%@page import="java.io.*"%>
<%@page import="java.text.SimpleDateFormat"%>

<div class="row1">
<a href="/" > Home </a>
<h1 class="center2">
    Welcome to Air Fare</h1>
    <a href="/deals.jsp" > Deals </a>
</div>

<h2 style="text-align:center">Search for flights</h2>

<form action="/flights" method="get" class="form2">
    <div class="column2">
            <label for="source">Departure airport : </label>
            <input type="text" id="source" name = "source" required/>
        </div>
    <div class="column2">
        <label for="destination">Destination Airport : </label>
        <input type="text" id="destination" name="destination" required/>
    </div>
    <div class="column2">
        <label for="time">Journey begins : </label>
        <input type="time" id="time" name="search_time" required/>
    </div>
    <input class="searchbutton" type="submit" value="Search">
</form>

<% List<Flight> flightList = (List) request.getAttribute("flight-array"); %>

<%
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");
%>


<%
if(flightList != null)  // Null check for the object
{
pageContext.setAttribute("list", flightList);
%>

    <c:forEach var="flight" items="${pageScope.list}">
        <div class="card_flights">
            <div class="font1"><c:out value="${flight.owner} ${flight.flightNumber}"/></div>
            <div class="font2"><c:out value="Journey commence at ${flight.departureTime}, "/></div>
            <div class="font2"> <c:out value="upto ${flight.arrivalTime}"/> </div>
            <div class="font7"> <c:out value="${flight.source} to ${flight.destination}"/> </div>
            <div class="font5"><c:out value="Rs. ${flight.price} "/></div>
            <div class="font6"><c:out value="${flight.leg} legs on the way"/></div>
        </div>
    </c:forEach>


	<%
	}
%>


</body>
</html>

