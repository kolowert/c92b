<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Receipt"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.bean.Item"
	import="fun.kolowert.c92b.bean.SoldRecord"
	import="fun.kolowert.c92b.dao.DaoStore"
	import="fun.kolowert.c92b.dao.DaoSold"
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
											List<Item> items = daoStore.getAll();
											out.println("<option>" + "> > > add item to the order > > >" + "</option>");
											for (Item item : items) {
												out.println("<option>" + item.brief() + "</option>");
											}
					%>
				</select> 
				&ensp; : &ensp; 
				<input name="quantity" />
				&ensp; &ensp; 
				<input type="submit" value="next" /> 
				&ensp; &ensp; 
				<input
					type="button" onclick="location.href='order?task=finish'"
					value="finish" />
				&ensp; &ensp;
				<input
					type="button" onclick="location.href='order?task=cancel'"
					value="cancel" />
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
		
		<!-- receipt --------------------------------------------------------- -->
		<div class="container p-3 my-1 bg-light border">
			<%
			String receiptLabel = "not created yet";
				String total = "--.--";
				int currentReceiptId = -1;
				long currentReceiptOpenTime = -1L;
				String opentime = "--.--.-- --:--:--";
				Object preCurrentReceipt = session.getAttribute("currentReceipt");
				if (preCurrentReceipt instanceof Receipt) {
					Receipt currentReceipt = (Receipt) preCurrentReceipt;
					currentReceiptId = currentReceipt.getId();
					receiptLabel = "#" + currentReceiptId; 
					currentReceiptOpenTime = currentReceipt.getOpentime();
					total = Utils.norm(currentReceipt.getSum());
				}
				if (currentReceiptOpenTime > 0) {
					opentime = Utils.unixTimeToTimeStamp(currentReceiptOpenTime);
				}
			%>
			<h5 class="text-muted">Receipt <small><%=receiptLabel%>&emsp;<%=opentime%></small></h5>
			<%
			DaoSold daoSold = DaoSold.getInstance();
							List<SoldRecord> soldRecords = daoSold.getByReceipt(currentReceiptId);
							out.println("<h4 class='text-primary'>TOTAL: " + total + "</h4>");
							// make table
							out.println("<table class='table'>");
							out.println("<thead><tr><th>Item</th><th>price</th><th>quantity</th><th>cost</th></tr></thead>");
							out.println("<tbody>");
							for (SoldRecord soldRecord : soldRecords) {
								Item item = daoStore.get(soldRecord.getItemId());
								String receiptLine = item.getName() + " >>> " + soldRecord.brief();
								out.println("<tr><td>" + item.getName() + "</td><td>" + item.getPrice() + "</td><td>" 
										+ Utils.norm(soldRecord.getSoldQuantity()) + " " + item.getUnit() + "</td><td>" 
										+ Utils.norm(soldRecord.getSoldCost()) + "</td></tr>");
							}
							out.println("</tbody>");
							out.println("</table>");
			%>
		</div>

		<!-- Containers*** -->

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>