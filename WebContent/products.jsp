<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<<<<<<< HEAD
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
=======
<%@ page import="java.sql.*" %>
<%@ page import="main.Product" %>
>>>>>>> 88224f9b4753e1caaa5509befdb282b8fcde116a
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<<<<<<< HEAD
<title>products.jsp</title>
<link rel="stylesheet" type="text/css" href="css/productsTableStyle.css">
</head>
<body>
	<sql:setDataSource var="db"
					   driver="com.mysql.cj.jdbc.Driver"
					   url="jdbc:mysql://localhost/tododb"
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
				<td><img src="img/${product.img_name}"/></td>
				<td><form method="POST" action="updateProduct"><button type="submit" name="id" value="${product.id}">Edit</button></form></td>
			</tr>
		</c:forEach>
=======
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/productsTableStyle.css">
</head>
<body>
	<table>
	<tr><th>Name</th><th>Stock</th><th>Image</th><th>...</th></tr>
	
	<%
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		    final String connectionString = "jdbc:mysql://localhost/tododb?user=root&password=1D952tnZ";
		    Connection conn = DriverManager.getConnection(connectionString);
		    Statement statement = conn.createStatement();
		    ResultSet resultSet = statement.executeQuery("SELECT * FROM Products;");	    
		    
		    while (resultSet.next()) {
		        Product product = new Product(	resultSet.getInt("id"),
		                						resultSet.getString("name"),
		                						resultSet.getInt("stock"),
		                						resultSet.getString("img_name"));
		        out.write("<tr>");
		        out.write("<td>" + product.getName() + "</td>");
		        out.write("<td>" + product.getStock() + "</td>");
		        out.write("<td><img src=\"./img/" + product.getImgName() + "\"></td>");
		        out.write("<td><form method=\"POST\" action=\"updateProduct\"><button type=\"submit\" name=\"id\" value=\"" + product.getId() +"\">Edit</button></form></td>");
		        out.write("</tr>");
		    }		        
		} catch (SQLException e) {		    
		    System.out.println(e);
		}
	%>
>>>>>>> 88224f9b4753e1caaa5509befdb282b8fcde116a
	</table>
</body>
</html>