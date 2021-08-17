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
	<h1><c:out value="${product.name}"/></h1>
	<h3>Categories:</h3>
	<c:forEach items="${product.categories}" var="ca">
		<ul><c:out value="${ca.name}"/></ul>
	</c:forEach>
	
	<form action="/products/${product.id}" method="post">
		Add a category: <br/>
		<select name="catId">
			<c:forEach items="${categories}" var="c">
				<option value="${c.id}"><c:out value="${c.name}"/></option>
			</c:forEach>
		</select>
		<button>Add</button>
	
	</form>
</body>
</html>