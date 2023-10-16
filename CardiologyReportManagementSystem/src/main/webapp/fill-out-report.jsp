<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="br.vjps.tsi.crms.enumeration.ICD"%>
<%@ include file="_resident-auth.jsp"%>
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
			<h2>Emitir Laudo</h2>
			<form action="controller" method="post" class="login-form" id="form">
				<input type="text" name="patient" value="${param.patient} - Exame ${param.id}" readonly>
				
				<input list="idc-list" id="icd" type="text" name="conclusion" placeholder="Conclusão - CID" required>
				<datalist id="idc-list">
				    <c:forEach var="icdCode" items="${ICD.values()}">
				        <option value="${icdCode.code}">${icdCode.description}</option>
				    </c:forEach>
				</datalist>
				
				<textarea id="textArea" name="description" placeholder="Descrição do diagnóstico" required></textarea>
				<p id="charCount">250 caracteres restantes</p>
				
				<input type="text" name="logic" hidden="true" value="CreateReport">
				<input type="text" name="examId" hidden="true" value="${param.id}">
				<button form="form" class="button"> 
					<span class="button__text">Emitir</span>
				</button>
			</form>
		</div>
	</main>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script type="text/javascript" src="script/validationScript.js"></script>
</body>
</html>