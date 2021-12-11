<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" 
	import="fun.kolowert.c92b.bean.MeasureUnit"
	import="fun.kolowert.c92b.utility.Utils" 
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Add new Item Page</title>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<jsp:include page="_header.jsp"></jsp:include>

		<div style="color: DimGray">
			<br />
			<h5 style="text-align: left">Add new Item</h5>
			<br />
		</div>

		<form action="${pageContext.request.contextPath}/item" method="POST">
			<input type="hidden" name="task" value="inputNew" /> 
			Name: <input name="name" /> <br /> <br /> 
			Measure Unit: <select name="unit">
				<%
				MeasureUnit[] measureUnits = MeasureUnit.values();
				for (MeasureUnit unit : measureUnits) {
					out.println("<option>" + unit + "</option>");
				}
				%>
			</select> 
			&nbsp; &nbsp; 
			Quantity: <input name="quantity" />
			<br /> <br /> 
			Price: <input name="price" />
			
			&nbsp; &nbsp; 
			<input type="submit" value="input" /> 
			<br /> <br /> 
			<input
				type="button" onclick="location.href='${pageContext.request.contextPath}/play/store.jsp'"
				value="cancel" />
		</form>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>