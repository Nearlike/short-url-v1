<%@ include file="header.jsp"%>
<div class="container">
	<div class="row">
		<div class="card-panel teal lighten-2">
			<p class="white-text">
				Url demandée :
				<%=request.getAttribute("path")%></p>
		</div>
	</div>
	<%
		Boolean redirectValid = (Boolean) request.getAttribute("redirectValid");
		Boolean passwordValid = (Boolean) request.getAttribute("passwordValid");
		if (redirectValid != null && redirectValid == false) {
	%>
	<div class="row">
		<h5 class="red-text">Désolé, cette URL n'est plus disponible !</h5>
	</div>
	<%
		} else {
			if (passwordValid != null && passwordValid == true) {
	%>
	<div class="row">
		<form class="col s12" action="" method="post">
			<div class="row">
				<div class="input-field col s12">
					<input id="password" type="password" name="password"> <label
						for="password">Password</label>
				</div>
			</div>
			<input type="hidden" name="id"
				value="<%=request.getAttribute("idPasswordValid")%>">

			<button class="btn waves-effect waves-light" type="submit"
				name="action">
				Submit <i class="material-icons right">send</i>
			</button>
		</form>
		<%
			}
			}
		%>
	</div>
</div>
<%@ include file="footer.jsp"%>