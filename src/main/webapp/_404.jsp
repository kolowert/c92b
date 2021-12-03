<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Not Found</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container-lg p-1">
		<jsp:include page="_header-light.jsp"></jsp:include>
		<h2 style="color: Brown">Resource not found!</h2>
		<b><a href="${pageContext.request.contextPath}/index.jsp">go home >>></a></b>
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>