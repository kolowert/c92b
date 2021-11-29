<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Entry Page</title>
</head>

<body>
	<jsp:include page="_header.jsp"></jsp:include>
	
	<div style="padding: 10px;">
		<a href="${pageContext.request.contextPath}/entry">Start >>></a>
	</div>
	
	<jsp:include page="_footer.jsp"></jsp:include>
	
</body>
</html>
