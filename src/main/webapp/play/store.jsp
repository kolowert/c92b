<%@page import="fun.kolowert.c92b.bean.MeasureUnit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"
	import="fun.kolowert.c92b.bean.Item"
	import="fun.kolowert.c92b.bean.MeasureUnit"
	import="fun.kolowert.c92b.dao.DaoStore"
	import="fun.kolowert.c92b.utility.Utils"
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Store Page</title>
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

		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<h5 class="text-muted">Items Store:</h5>
				</div>
				<div class="col-sm-6">
					<p style="text-align: right">
						Add Item <input type="button"
							onclick="location.href='${pageContext.request.contextPath}/play/input-item.jsp'"
							value="add" />
					</p>
				</div>
			</div>
		</div>

		<div class="container-lg">

			<table class="table">
				<thead>
					<tr>
						<th>id</th>
						<th>Name</th>
						<th>units</th>
						<th>quantity</th>
						<th style='color: ForestGreen'>price</th>
						<th class="text-primary">items</th>
					</tr>
				</thead>
				<tbody>
					<%
					DaoStore daoStore = DaoStore.getInstance();
					List<Item> items = daoStore.getAll();
					for (Item item : items) {
						int id = item.getId();
						String name = item.getName();
						MeasureUnit unit = item.getUnit();
						double quantity = item.getQuantity();
						double price = item.getPrice();
						String path = request.getContextPath();
						String editLink = "<a href=\"" + path + "/play/edit-item.jsp?id=" + id + "\">edit</a>";
						out.println("<tr><td>" + Utils.norm(id) + "</td><td>" + name + "</td><td>" + unit + "</td><td>" 
								+ quantity + "</td><td><b style='color: ForestGreen'>" + Utils.norm(price) + "</b></td><td>" 
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