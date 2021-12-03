<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.dao.DaoOperator" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Staff Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<br />

		<jsp:include page="_headerx.jsp"></jsp:include>
		<br />
		<div class="container border">
			<div class="row">
				<div class="col-sm-7">
					<h5 class="text-muted">List of operators:</h5>
				</div>
				<div class="col-sm-5">
					<button type="button" class="btn btn-light">Register new</button>
				</div>
			</div>
		</div>

		<div class="container">
			<br /><b><a href="${pageContext.request.contextPath}/index.jsp">go home >>></a></b>
		</div>

	</div>
</body>
</html>