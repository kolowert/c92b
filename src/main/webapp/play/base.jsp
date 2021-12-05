<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.bean.Item"
	import="fun.kolowert.c92b.dao.DaoStore"
	import="fun.kolowert.c92b.utility.Utils" 
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Base Page</title>
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

		<!-- Containers___ -->

		<div class="container p-3 my-3 bg-light border">
			<form action="${pageContext.request.contextPath}/order" method="POST">
				<input type="hidden" name="task" value="addToReceipt"> 
				<select name="itemBrief">
					<%
					DaoStore daoStore = DaoStore.getInstance();
					List<Item> items = daoStore.getItems();
					out.println("<option>" + "> > > add item to order > > >" + "</option>");
					for (Item item : items) {
						out.println("<option>" + item.brief() + "</option>");
					}
					%>
				</select> 
				&nbsp; : &nbsp; <input name="quantity" />
				&nbsp; &nbsp; 
				<input type="submit" value="next" /> 
				&nbsp; &nbsp; 
				<input
					type="button" onclick="location.href='order?task=finish'"
					value="finish" />
			</form>
			
			<br />
			<%
			String messageType = Utils.atributeToStringOrStub(request.getAttribute("messageType"), "fail");
			String orderMessage = Utils.atributeToStringOrStub(request.getAttribute("orderMessage"), "");
			if (messageType.equals("good")) {
				out.println("<p class='text-primary'>" + orderMessage + "</p>");
			} else {
				out.println("<p class='text-danger'>" + orderMessage + "</p>");
			}
			%>
		</div>

		<div class="container p-3 my-1 bg-light border">
			<h1>Second container</h1>
			<p>This container has a border and some extra padding and
				margins.</p>
		</div>

		<!-- Containers*** -->

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>