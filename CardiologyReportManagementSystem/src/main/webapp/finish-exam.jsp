<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="_resident-auth.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>
	<c:import url="_header.jsp" />
	<main class="login-page">
		<div class="form">
			<h2>Exame finalizado com sucesso!</h2>
			<br>
			<h4>
				<a href="downloadServlet?file=${exam.resultPath}">Baixar PDF</a>
			</h4>
			<h4>
				<a href="fill-out-report.jsp?id=${exam.id}&patient=${exam.patient.name}">Preencher e Emitir Laudo</a>
			</h4>
			<h4>
				<a href="menu.jsp">Menu</a>
			</h4>
		</div>
	</main>
</body>
</html>