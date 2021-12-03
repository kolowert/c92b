<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Entry Page</title>
<meta charset="UTF-8">
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
	<p><a href="${pageContext.request.contextPath}/entry">Start >>></a></p>
	
	<p><a href="${pageContext.request.contextPath}/play/play1.jsp">Play1 >>></a></p>
	
	<p><a href="${pageContext.request.contextPath}/play/play2.jsp">Play2 >>></a></p>
	
	<p><a href="${pageContext.request.contextPath}/play/play3.jsp">Play3 >>></a></p>
	
	<p><a href="${pageContext.request.contextPath}/play/play4.jsp">Play4 >>></a>&ensp; editing from dao</p>
	
	<p>--------------------------------------------</p>
	
	<button type="button" class="btn">Basic</button>
	
	<jsp:include page="_footer.jsp"></jsp:include>
</div>
</body>
</html>
