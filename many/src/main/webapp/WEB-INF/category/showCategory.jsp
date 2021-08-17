<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="/">Home</a>
	<h1><c:out value="${category.name}"/></h1>
	<h3>Products:</h3>
	<c:forEach items="${category.products}" var="prod">
		<ul><c:out value="${prod.name}"/></ul>
	</c:forEach>
	
	<form action="/categories/${category.id}" method="post">
		Add a category: <br/>
		<select name="prodId">
			<c:forEach items="${products}" var="p">
				<option value="${p.id}"><c:out value="${p.name}"/></option>
			</c:forEach>
		</select>
		<button>Add</button>
	
	</form>
</body>
</html>