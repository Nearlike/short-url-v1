<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Short Url</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.99.0/css/materialize.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<nav>
		<div class="nav-wrapper">
			<ul>
				<li><a href="index">Accueil</a></li>
				<%
					Boolean isLogged = (Boolean) session.getAttribute("isLogged");
					if (isLogged != null && isLogged == true) {
				%>
				<li><a href="shortUrlUser">Raccourcir</a></li>
				<li><a href="urls">Mon Compte</a></li>
				<li><a href="deconnexion">Déconnexion</a></li>
				<%
					}
					if (isLogged == null || isLogged == false) {
				%>
				<li><a href="subscribe">Création d'un compte</a></li>
				<li><a href="connexion">Connexion</a></li>
				<%
					}
				%>
			</ul>
		</div>
	</nav>