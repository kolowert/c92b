<%@page import="fun.kolowert.c92b.bean.Receipt"%>
<%@page import="fun.kolowert.c92b.dao.DaoReceipt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Item"
	import="fun.kolowert.c92b.bean.SoldRecord"
	import="fun.kolowert.c92b.dao.DaoSold"
	import="fun.kolowert.c92b.dao.DaoStore"
	import="fun.kolowert.c92b.utility.Utils" 
	import="java.util.List"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>unfold receipt</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js"></script>
</head>

<body>
	<div class="container-lg p-1">
		<jsp:include page="_header.jsp"></jsp:include>
		
		<%
		int receiptId = Utils.parseStringToInt(request.getParameter("id"));
		DaoReceipt daoReceipt = DaoReceipt.getInstance();
		Receipt receipt = daoReceipt.getReceiptById(receiptId);
		double receiptSum = 0.0;
		if (receipt != null) {
			receiptSum = receipt.getSum();
		}
		%>
		
		<div class="container my-3">
			<div class="row">
				<div class="col-sm-4">
					<h5 class="text-muted">Receipt #<%=receiptId%></h5>
				</div>
				<div class="col-sm-4">
					<input type="button" 
						onclick="location.href='${pageContext.request.contextPath}/main?dir=receipts'"
						value="All Receipts" />						
				</div>
				<div class="col-sm-4">
				<div class="text-end">
					<form action="${pageContext.request.contextPath}/receipt" method="GET">
						<input type="hidden" name="task" value="deleteReceipt"> 
						<input type="hidden" name="receiptId" value=<%=receiptId%>> 
						<input type="submit" value="Cancel and delete the receipt" />
					</form>
				</div>
				</div>
			</div>
		</div>
		
		<div class="container-lg">
			<table class="table">
				<thead>
					<tr>
						<th>Item</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Cost</th>
						<th class="text-primary">Edit</th>
					</tr>
				</thead>
				<tbody>
					<%
					DaoStore daoStore = DaoStore.getInstance();
					DaoSold daoSold = DaoSold.getInstance();
					List<SoldRecord> soldRecords = daoSold.getSoldRecords();
					for (SoldRecord soldRecord : soldRecords) {
						if (soldRecord.getReceiptId() != receiptId) { continue; }
						int itemId = soldRecord.getItemId();
						Item item = daoStore.getItem(itemId);
						if (item == null) {
							item = Item.getNullItem();
						}
						String itemName = item.getName();
						double soldPrice = soldRecord.getSoldPrice();
						double soldQuantity = soldRecord.getSoldQuantity();
						double soldCost = soldRecord.getSoldCost();
						
						String path = request.getContextPath();
						String redirectLink = "<a href=\"" + path + "/receipt?task=cancelRecord"
								+ "&receiptId=" + receiptId
								+ "&recordId=" + soldRecord.getId()
								+ "\">remove</a>";
						out.println("<tr><td>" 
								+ itemName + "</td><td>" 
								+ Utils.norm(soldPrice) + "</td><td>" 
								+ Utils.norm(soldQuantity) + "</td><td>" 
								+ Utils.norm(soldCost) + "</td><td>" 
								+ redirectLink + "</td></tr>");
					}
					out.println("<tr><td>" 
							+ "<b>TOTAL</b> " + "</td><td>" 
							+ "" + "</td><td>"
							+ "" + "</td><td>" 
							+ "<b>" + Utils.norm(receiptSum) + "</b></td><td>" 
							+ "" + "</td></tr>");
					%>
				</tbody>
			</table>
		</div>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>