<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Product</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script>
	function validate() {
		if (document.getElementById("productName").value.length == 0 ||
			document.getElementById("stock").value.length == 0 ||
			document.getElementById("imgName").value.length == 0) {
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<p>
		<a href="./logout">Logout</a>
		<a href="./">Home Page</a>
	</p>
	<form method="POST" action="addProduct" onsubmit="return validate();">
		<table>
			<tr><td>Product name:</td><td><input type="text" name="productName" id="productName"/></td></tr>
			<tr><td>Stock: </td><td><input type="number" name="stock" id="stock"  min="0" max="10"/></td></tr>
			<tr><td>Image: </td><td> <input type="file" name="imgName" id="imgName" accept="image/*"></td></tr>
			<tr><td></td><td><input type="submit" value="Add"/></td></tr>
		</table>
	</form>	
</body>
</html>