<%@ include file="header.jsp"%>
<div class="container">
	<div class="row">
		<div class="white-text center-align card-panel teal lighten-2">
			<h5>Reducteur d'url</h5>
		</div>
	</div>
	<div class="row">
		<form class="col s12" action="ShortUrlUser" method="post">
			<div class="row">
				<div class="input-field col s12">
					<input id="id" type="url" name="url"> <label for="url">Url</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s2">
					<input type="checkbox" id="checkboxPassword" name="activePassword">
					<label for="checkboxPassword"></label>
				</div>
				<div class="input-field col s10">
					<input id="password" type="password" name="password"> <label
						for="password">Sécurisée avec mot de passe</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s2">
					<input id="radioDateBetween" type="radio" name="date"
						value="betweenDate"> <label for="radioDateBetween">Valable
						du</label>
				</div>
				<div class="input-field col s5">
					<input type="date" name="dateFrom">
				</div>
				<div class="input-field col s5">
					<input type="date" name="dateTo">
				</div>
			</div>
			<div class="row">
				<div class="input-field col s2">
					<input id="maxClics" type="radio" name="date" value="maxClic">
					<label for="maxClics">Max Clics</label>
				</div>
				<div class="input-field col s10">
					<input type="number" name="nbrMaxClic"> <label>Max
						Clics</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s2">
					<input id="radioDateMax" type="radio" name="date" value="maxDate">
					<label for="radioDateMax">Valable jusqu'au</label>
				</div>
				<div class="input-field col s10">
					<input type="date" name="valueMaxDate">
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