<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty sessionScope.currentSessionUser}">
		<form method="POST" action="addProduct">
			<table>
				<tr><td>Product name:</td><td><input type="text" name="productName"/></td></tr>
				<tr><td>Stock: </td><td><input type="number" name="stock" min="0" max="10"/></td></tr>
				<tr><td>Image: </td><td> <input type="file" name="imgName" accept="image/*"></td></tr>
				<tr><td></td><td><input type="submit" value="Add"/></td></tr>
			</table>
		</form>
	</c:if>	
</body>
</html>