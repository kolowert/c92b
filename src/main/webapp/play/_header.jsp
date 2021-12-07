<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String briefInfo = "undefined operator";
	Object preBriefInfo = request.getSession().getAttribute("briefInfo");
	if (preBriefInfo instanceof String) {
		briefInfo = (String) preBriefInfo;
	}
%>

<div class="container-lg p-1 bg-light border">
	<div class="row">

		<div class="col-sm-4">
			<pre style="color: MediumPurple; text-align: left"><%=briefInfo%></pre>
		</div>

		<div class="col-sm-6">
			<p style="text-align: center">
				  <a href="${pageContext.request.contextPath}/main?dir=base">Order</a> 
				| <a href="${pageContext.request.contextPath}/main?dir=receipts">Receipts</a>
				| <a href="${pageContext.request.contextPath}/main?dir=report">Reports</a>
				| <a href="${pageContext.request.contextPath}/main?dir=store">Store</a>
				| <a href="${pageContext.request.contextPath}/main?dir=staff">Staff</a>
				| <a href="${pageContext.request.contextPath}/entry">Log Out</a>
			</p>
		</div>

		<div class="col-sm-2">
			<h5 style="color: Thistle; text-align: right">Cash Register</h5>
		</div>

	</div>
</div>