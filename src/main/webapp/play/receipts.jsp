<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.bean.Receipt"
	import="fun.kolowert.c92b.dao.DaoReceipt"
	import="fun.kolowert.c92b.dao.DaoOperator"
	import="fun.kolowert.c92b.utility.Utils" 
	import="java.util.List"
%>
<!DOCTYPE html>
<html>
<head>
<title>Receipts Page</title>
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

		<h5 class="text-muted">Receipts:</h5>

		<div class="container-lg">
			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>sum</th>
						<th>operator</th>
						<th>close time</th>
						<th class="text-primary">Edit</th>
					</tr>
				</thead>
				<tbody>
					<%
					DaoOperator daoOperator = DaoOperator.getInstance();
					DaoReceipt daoReceipt = DaoReceipt.getInstance();
					List<Receipt> receipts = daoReceipt.getAll();
					for (Receipt receipt : receipts) {
						int id = receipt.getId();
						double sum = receipt.getSum();
						int operatorId = receipt.getOperatorId();
						Operator operator = daoOperator.get(operatorId);
						if (operator == null) {
							operator = Operator.getNullOperator();
						}
						String operatorLogin = operator.getLogin();
						String operatorRole = operator.getRole();
						long closetime  = receipt.getClosetime();
						String path = request.getContextPath();
						String editLink = "<a href=\"" + path + "/play/receipt.jsp?id=" + id + "\">unfold</a>";
												
						out.println("<tr><td>" 
								+ id + "</td><td class='text-success'><b>" 
								+ Utils.norm(sum) + "</b></td><td>" 
								+ operatorLogin + " <small>(" + operatorRole + ")</small></td><td>"
								+ Utils.unixTimeToTimeStamp(closetime) + "</td><td>"
								+ editLink + "</td></tr>");
					}
					%>
				</tbody>
			</table>
		</div>
		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>