<%@ include file="header.jsp"%>
<div class="container">
	<blockquote>Entrez l'url que vous voulez réduire</blockquote>
	<div class="row">
		<form class="col s12" action="ShortUrlIndex" method="post">
			<div class="row">
				<div class="input-field col s12">
					<input id="url" type="url" name="url" class="validate"> <label
						for="url">Url</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s1">
					<input type="checkbox" id="checkboxPassword" name="activePassword">
					<label for="checkboxPassword"></label>
				</div>
				<div class="input-field col s11">
					<input id="password" type="password" name="password"> <label
						for="password">Sécurisée avec mot de passe</label>
				</div>
			</div>
			<button class="btn waves-effect waves-light" type="submit"
				name="action">
				Submit <i class="material-icons right">send</i>
			</button>
		</form>
	</div>
	<div class="row">
		<div class="card-panel teal lighten-2">
			<%
				String urlShort = (String) request.getAttribute("urlShort");
				if (urlShort != null) {
			%>
			<h5 class="white-text"><%=urlShort%></h5>
			<%
				}
			%>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>
