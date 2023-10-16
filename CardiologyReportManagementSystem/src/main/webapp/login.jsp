<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="style/style.css">
</head>

<body>
	<c:import url="_header.jsp"/>
	<main class="login-page">
		<div class="form">
			<h2>Fazer Login</h2>
			<form action="controller" method="post" class="login-form" id="form">
				<input type="number" name="crm" placeholder="CRM" pattern="\d*"
					required /> <input type="password" name="password"
					placeholder="Senha" required /> <input type="text" name="logic"
					hidden="true" value="Login">
				<button form="form">Entrar</button>
			</form>
		</div>
	</main>
</body>

</html>