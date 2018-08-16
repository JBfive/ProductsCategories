<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>New Products Page</title>
</head>
<body>
	<form:form action="/create_product" method="post" modelAttribute="products">
	    <p>
	        <form:label path="name">Name:</form:label>
	        <form:errors path="name"/>
	        <form:input path="name"/>
	        
	    </p>
	    <p>
	        <form:label path="description">Description:</form:label>
	        <form:errors path="description"/>
	        <form:textarea path="description"/>
	    </p>
	    <p>
	    	<form:label path="price">Price:</form:label>
	    	<form:errors path="price"/>
	    	<form:input path="price"/>
    	</p>
	    
	    <input type="submit" value="Create"/>
	</form:form>  
</body>
</html>