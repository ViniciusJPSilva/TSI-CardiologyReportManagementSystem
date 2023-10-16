<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="br.vjps.tsi.crms.enumeration.ExamType"%>
<%@ page import="br.vjps.tsi.crms.enumeration.ICD"%>
<%@ include file="_physician-auth.jsp"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
	<c:import url="_header.jsp" />
	<main class="login-page">
		<div class="form">
			<h2>Solicitar Exame</h2>
			<form action="controller" method="post" class="login-form" id="form">
				<input type="text" id="cpf" name="cpf" placeholder="CPF do Paciente" required>
				
				<input list="types" id="type" type="text" name="type" placeholder="Exame" required>
				<datalist id="types">
				    <c:forEach var="examType" items="${ExamType.values()}">
				        <option value="${examType.description}">${examType.description}</option>
				    </c:forEach>
				</datalist>
				
				<input list="idc-list" id="icd" type="text" name="icd" placeholder="CID" required>
				<datalist id="idc-list">
				    <c:forEach var="icdCode" items="${ICD.values()}">
				        <option value="${icdCode.code}">${icdCode.description}</option>
				    </c:forEach>
				</datalist>
				
				<textarea id="textArea" name="recomendation" placeholder="Recomendações..."></textarea>
				<p id="charCount">250 caracteres restantes</p>
				
				<input type="text" name="logic" hidden="true" value="RequestExam">
				<button form="form" class="button"> 
					<span class="button__text">Solicitar</span>
				</button>
			</form>
		</div>
	</main>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script type="text/javascript" src="script/validationScript.js"></script>
</body>
</html>