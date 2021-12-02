<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.SimpleDateFormat"
	import="java.util.Calendar" import="fun.kolowert.c92b.utility.Utils" %>

<%
String timeStamp = new SimpleDateFormat(" dd/MM/yyyy").format(Calendar.getInstance().getTime());
String weekday = Utils.reportDayOfWeek();
%>

<br />
<br />
<div
	style="background: Lavender; text-align: right; padding: 0px; margin-top: 5px;">
	<pre style="color: MediumPurple"><%=weekday%><%=timeStamp%></pre>
</div>