<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="fun.kolowert.c92b.bean.Operator"
	import="fun.kolowert.c92b.bean.Item"
	import="fun.kolowert.c92b.bean.MeasureUnit"
	import="fun.kolowert.c92b.dao.DaoStore"
	import="fun.kolowert.c92b.utility.Utils"
	import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Item</title>
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
			<h5 style="text-align: left">Edit Item in Store</h5>
			<br />
		</div>

		<%
		String itemName = "undefined";
		String itemUnit = "undefined";
		double remains = -1.0;
		double price = -1.0;
		int itemId = Utils.parseIntIdFromObject(request.getParameter("id"));

		DaoStore daoStore = DaoStore.getInstance();
		Item item = daoStore.get(itemId);

		if (item != null) {
			itemName = item.getName();
			itemUnit = item.getUnit().toString();
			remains = item.getQuantity();
			price = item.getPrice();
		}
		%>
		
		<h4><%=itemName %> &nbsp; <small style="color: DarkGray">Item id: <%=itemId%></small> </h4>
		
		<form action="${pageContext.request.contextPath}/item" method="POST">
			<input type="hidden" name="task" value="editItem"> 
			<input type="hidden" name="id" value=<%=itemId%>>
			<br /> 
			Measure Unit: <select name="unit">
				<%
				MeasureUnit[] measureUnits = MeasureUnit.values();
				out.println("<option>" + itemUnit + "</option>");
				for (MeasureUnit unit : measureUnits) {
					if (unit.toString().equals(itemUnit)) {
						continue;
					}
					out.println("<option>" + unit.toString() + "</option>");
				}
				%>
			</select> 
			&nbsp; &nbsp;
			Quantity: <input name="quantity" value=<%=remains %> />
			<br /> <br /> 
			Price: <input name="price" value=<%=price %> />
			
			&nbsp; &nbsp;
			<input type="submit" value="edit" /> 
			&nbsp; &nbsp; 
			<input type="button"
				onclick="location.href='${pageContext.request.contextPath}/play/store.jsp'"
				value="cancel" />
		</form>

		<br />
		<br />
		
		<form action="${pageContext.request.contextPath}/item" method="POST">
			<input type="hidden" name="task" value="deleteItem"> 
			<input type="hidden" name="id" value=<%=itemId%>>
			<input type="submit" value="Delete Item from Store" />
		</form>

		<jsp:include page="_footer.jsp"></jsp:include>
	</div>
</body>
</html>
