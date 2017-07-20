<%@ include file="header.jsp"%>
<div class="container">
	<div class="row">
		<div class="white-text center-align card-panel teal lighten-2">
			<h5>Inscription</h5>
		</div>
	</div>
	<div class="row">
		<form class="col s12" action="Subscribe" method="post">
			<div class="row">
				<div class="input-field col s12">
					<input id="login" type="text" name="login"> <label
						for="login">Login</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input type="password" name="password"> <label
						for="password">Password</label>
				</div>
			</div>
			<div class="row">
				<div class="input-field col s12">
					<input type="password" name="confirmation"> <label
						for="confirmation">Confirmation</label>
				</div>
			</div>
			<button class="btn waves-effect waves-light" type="submit"
				name="action">
				Submit <i class="material-icons right">send</i>
			</button>
		</form>
	</div>
</div>
<%@ include file="footer.jsp"%>