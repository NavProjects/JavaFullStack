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
	<h1>
		<c:out value="${question.question}" />
	</h1>
	<h3>Tags:</h3>
	<c:forEach items="${question.tags}" var="t">
		<c:out value="${t.subject}" />
	</c:forEach>
	<table style="width: 200px" class="table table-striped table-dark">
		<tr>
			<th>Answers</th>
		</tr>
		<c:forEach items="${question.answers}" var="a">
			<tr>
				<td><c:out value="${a.answer}" /></td>
			</tr>
		</c:forEach>
	</table>
	<h3>Add your Answer</h3>
	<div>
		<form:form action="/questions/${question.id }" method="POST"
			modelAttribute="ans">
			<form:input type="hidden" value="${question.id }" path="question" />
			<p>
				<form:label path="answer">Create Answer: </form:label>
				<form:errors path="answer" />
				<form:input path="answer" />
			</p>
			<button>Submit Answer</button>
		</form:form>
	</div>
	<a href="/">Home</a>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/app.js"></script>
</body>
</html>