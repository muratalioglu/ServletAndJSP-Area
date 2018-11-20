<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>products.jsp</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/productsTableStyle.css">
</head>
<body>
	<c:if test="${not empty sessionScope.currentSessionUser}">
		<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost/tododb"
			user="root" password="1D952tnZ" />
					   
		<sql:query dataSource="${db}" var="resultSet">
			SELECT * FROM Products;
		</sql:query>
		<table>
			<tr><th>Name</th><th>Stock</th><th>Image</th><th>...</th></tr>
			<c:forEach var="product" items="${resultSet.rows}">
				<tr>
					<td><c:out value="${product.name}"/></td>
					<td><c:out value="${product.stock}"/></td>
					<td><img class="thumbnail" src="img/${product.img_name}"/></td>
					<td>
						<form method="POST" action="find">
							<button type="submit" name="id" value="${product.id}">Edit</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>