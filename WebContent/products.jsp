<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="main.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
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
	</table>
</body>
</html>