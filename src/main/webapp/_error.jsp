<!DOCTYPE html>
<%
String message = pageContext.getException().getMessage();
String exception = pageContext.getException().getClass().toString();
%>

<html>
<head>
<meta charset="UTF-8">
<title>Exception</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<div class="container-lg p-1">
	<jsp:include page="_header-light.jsp"></jsp:include>
	<br />
	<h1 style="color: red">Error</h1>
	<h2>Exception occurred while processing the request</h2>
	<p>
		Type:
		<%=exception%></p>
	<p>
		Message:
		<%=message%></p>

	<b><a href="${pageContext.request.contextPath}/index.jsp">go home >>></a></b>
	<jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>