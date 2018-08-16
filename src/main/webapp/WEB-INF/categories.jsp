<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Category Page</title>
</head>
<body>
	<h1><c:out value="${category.name}"/></h1>
	
	<h3>Products:</h3>
	<ul>
	
		<c:forEach items="${catPro}" var="list">
			<li><c:out value="${list.name}"/></li>
		</c:forEach>
		
	</ul>
	
	<form action="/add_product" method="post" >
	    <p>
	        Add Product:
	        
	        <select name="products">
	        <c:forEach items="${product}" var="list">
	        	<option value="${list.id}" label="${list.name}"/>
	        </c:forEach>
	        </select>
	    </p>
	    <input type="hidden" name="category" value="${category.id}"/>
	    <input type="submit" value="Add"/>
    </form>
</body>
</html>