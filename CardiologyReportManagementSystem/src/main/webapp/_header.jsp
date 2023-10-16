<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="shortcut icon" href="favicon.ico?" type="image/x-icon">

<link rel="stylesheet" href="style/style.css">
<title>Sistema de Laudos</title>
</head>

<body>
	
	<header class="header">
		<a href="menu.jsp" class="header__logo">Sistema de Laudos</a>
		<c:if test="${sessionScope.status}">
			<h4 class="message">Olá, ${sessionScope.user.name}!</h4>
			<ul class="header__nav">
				<li class="header__list"><a class="header__link"
					href="menu.jsp">Menu</a></li>
				<li class="header__list"><a class="header__link"
					href="controller?logic=Logout">Sair</a></li>
			</ul>
		</c:if>
	</header>
	<script type="text/javascript" src="script/script.js"></script>

</body>

</html>