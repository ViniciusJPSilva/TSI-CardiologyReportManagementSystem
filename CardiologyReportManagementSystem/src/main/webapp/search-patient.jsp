<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="_authentication.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<c:if test="${empty param.destiny}">
		<jsp:forward page="menu.jsp"></jsp:forward>
	</c:if>
	<c:import url="_header.jsp" />
	<main class="login-page">
		<div class="form">
			<h2>Buscar Paciente</h2>
			<form action="controller" method="post" class="login-form" id="form">
				<input type="text" id="cpf" name="cpf" placeholder="CPF do Paciente" required>
				
				<input type="text" name="logic" hidden="true" value="${param.destiny}">
				<button form="form" class="button"> 
					<span class="button__text">Buscar</span>
				</button>
			</form>
		</div>
	</main>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script type="text/javascript" src="script/validationScript.js"></script>
</body>
</html>