<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Insert title here</title>
</head>
<body>

	<h1>Questions Dashboard</h1>
	<table style="width:700px" class="table table-striped table-dark">
		<tr>
			<th>Questions</th>
			<th>Tags</th>
		</tr>
		<c:forEach items="${quest}" var="q">
		<tr>
			<td><a href="/questions/${q.id}"><c:out value="${q.question}"/></a></td>
			<c:forEach items="${q.tags}" var="t">
				<td><c:out value="${t.subject}"/></td>
			</c:forEach>
		</tr>
		</c:forEach>
	</table>
	<a href="/questions/new">New Question</a>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/app.js"></script>
</body>
</html>