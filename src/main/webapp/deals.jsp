<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
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

<% List<Deal> dealList = (List) request.getAttribute("deal-array"); %>

<div class="row1">
<a href="/" > Home </a>
<h1 class="center2">
    Welcome to Air Fare</h1>
    <a href="/search.jsp" >   Find   </a>
</div>
<h2 style="text-align:center">Deals for the Day !!!</h2>

<%
SimpleDateFormat sdf = new SimpleDateFormat("HH:mm aa");
%>


<%
if(dealList != null)  // Null check for the object
{
pageContext.setAttribute("list", dealList);

%>

    <c:forEach var="deal" items="${pageScope.list}">
        <div class="card">
             <div class="font1"><c:out value="${deal.flight.owner} ${deal.flight.flightNumber}"/></div>
            <div class="font2"><c:out value="Journey commence at ${deal.flight.departureTime}, "/></div>
            <div class="font2"> <c:out value="upto ${deal.flight.arrivalTime}"/> </div>
            <div class="font3"> <c:out value="${deal.flight.source} to ${deal.flight.destination}"/> </div>
            <s class="font4">
            <c:out value="Rs. ${deal.flight.price} "/>
            </s>
            <div class="font5"><c:out value="Rs. ${deal.price} "/></div>
            <div class="font2"><c:out value="Book before ${deal.endingTime} !!"/></div>
        </div>
    </c:forEach>

<%
}
%>

</body>
</html>
