<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<c:if test="${not empty sessionScope.currentSessionUser}">
		<p><a href="logout">Logout</a>
	    <a href="addProduct.jsp">Add Product</a></p>
	    <hr>
	    <c:import url="products.jsp"/>
	</c:if>
</body>
</html>