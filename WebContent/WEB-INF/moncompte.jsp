<%@ include file="header.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="shorturl.beans.Url"%>
<div class="container">
	<div class="row">
		<div class="white-text center-align card-panel teal lighten-2">
			<h5>Mes urls</h5>
		</div>
	</div>
	<div class="row">
		<table class="striped">
			<thead>
				<tr>
					<th>Short Url</th>
					<th>Long Url</th>
					<th>Date Création</th>
				</tr>
			</thead>
			<tbody>
			<%
				List<Url> urls = (List<Url>) request.getAttribute("listUrl");
				if (urls != null) {
					for (Url url : urls) {
			%>
			<tr>
			<td><a href="<%=url.getUrlShort()%>"><%=url.getUrlShort()%></a></td>
			<td><a href="<%=url.getUrlLong()%>"><%=url.getUrlLong()%></a></td>
			<td><%=url.getDate()%></td>
			</tr>
			<%
				}
				}
			%>
			</tbody>
		</table>
	</div>
</div>
<%@ include file="footer.jsp"%>