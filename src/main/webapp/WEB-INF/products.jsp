<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Product Page</title>
</head>
<body>
	<h1><c:out value="${product.name}"/></h1>
	
	<h3>Categories:</h3>
	<ul>
	
		<c:forEach items="${proCat}" var="list">
			<li><c:out value="${list.name}"/></li>
		</c:forEach>
		
	</ul>
	
	<form action="/add_category" method="post" >
	    <p>
	        Add Category:
	        
	        <select name="categories">
	        <c:forEach items="${category}" var="list">
	        	<option value="${list.id}" label="${list.name}"/>
	        </c:forEach>
	        </select>
	    </p>
	    <input type="hidden" name="product" value="${product.id}"/>
	    <input type="submit" value="Add"/>
    </form>
     
</body>
</html>