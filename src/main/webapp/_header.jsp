<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String briefInfo = "undefined operator";
Object preBriefInfo = request.getSession().getAttribute("briefInfo");
if (preBriefInfo instanceof String) {
	briefInfo = (String) preBriefInfo;
}
%>

<div style="background: Lavender; height: 35px; padding: 0px;">
	<div style="float: left; padding: 0px;">
		<p style="color: MediumPurple">
			<b>Cash Register c92b</b>
		</p>
	</div>
	<div style="float: right; padding: 5px; text-align: right;">
		<pre><%=briefInfo%></pre>
	</div>
</div>

