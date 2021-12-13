<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.dao.DaoOperator" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Report Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<jsp:include page="_header.jsp"></jsp:include>
		<br />
		
		<b style="color: DodgerBlue; font-size: 16px;">Report Master</b>
		&ensp; &ensp;
		<input type="button"
		onclick="location.href='${pageContext.request.contextPath}/report?task=xReport'"
		value="x-Report" />
		&ensp; &ensp;
		<input type="button"
		onclick="location.href='${pageContext.request.contextPath}/report?task=zReport'"
		value="z-Report" />
		
		<br /> <br /> <br />
		<h4 style="color: DodgerBlue; font-size: 20px;">Custom Period Report</h4>
		
			<form action="${pageContext.request.contextPath}/report" method="POST">
				<input type="hidden" name="task" value="customReport"> 
				<b>From</b> &ensp; &ensp; y
				<select name="fromYear">
					<option>2021</option>
					<option>2020</option>
					<option>2019</option>
				</select>
				&ensp; m
				<select name="fromMonth">
					<%
					for (int i = 1; i <= 12; i++) {
						out.println("<option>" + i + "</option>");
					}
					%>
				</select>
				&ensp; d
				<select name="fromDay">
					<%
					for (int i = 1; i <= 31; i++) {
						out.println("<option>" + i + "</option>");
					}
					%>
				</select>
				&ensp; &ensp; &ensp;
				<b>To</b> &ensp; &ensp; y
				<select name="toYear">
					<option>2021</option>
					<option>2020</option>
					<option>2019</option>
				</select>
				&ensp; m
				<select name="toMonth">
					<%
					for (int i = 1; i <= 12; i++) {
						out.println("<option>" + i + "</option>");
					}
					%>
				</select>
				&ensp; d
				<select name="toDay">
					<%
					for (int i = 1; i <= 31; i++) {
						out.println("<option>" + i + "</option>");
					}
					%>
				</select>
				
				&ensp; &ensp;
				<input type="submit" value="Make Report" /> 
				<br /> <br />
				
			</form>
		
		<!-- -------------------------------------------------------------------------------------- -->
		
		<div class="container p-3 my-3 bg-light border">
			
			${reportBody}
			
		</div>
		
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>