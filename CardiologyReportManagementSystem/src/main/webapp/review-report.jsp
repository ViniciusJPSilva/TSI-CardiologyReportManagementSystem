<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="br.vjps.tsi.crms.enumeration.ICD"%>
<%@ include file="_teaching-auth.jsp"%>
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
			<h2>Revisar Laudo  - ID ${report.id}</h2>
			
			<c:set var="exam" value="${report.exam}" />
			<c:set var="patient" value="${exam.patient}" />
			
			<form action="controller" method="post" class="login-form" id="form">
				<h3>Dados do Paciente</h3>
				<label class="label">Nome:</label>
				<input type="text" name="name" value="${patient.name}" readonly>
				
				<label class="label">CPF:</label>
				<input type="text" name="cpf" value="${patient.cpf}" readonly>
				
				<label class="label">Data de Nascimento:</label>
				<input type="text" name="birthDate" value='<fmt:formatDate value="${patient.birthDate.time}" pattern="dd/MM/yyyy"/>' readonly>
				
				<label class="label">E-mail:</label>
				<input type="text" name="email" value="${patient.email}" readonly>
				
				<label class="label">Sexo:</label>
				<input type="text" name="cpf" value="${patient.gender.description}" readonly>
				
				<hr/>
				<h3>Dados do Exame - ID ${exam.id}</h3>
				
				<label class="label">Tipo:</label>
				<input type="text" name="type" value="${exam.type.description}" readonly>
				
				<label class="label">Hipótese:</label>
				<input type="text" name="hypothesis" value="${exam.hypothesis.code} - ${exam.hypothesis.description}" readonly>
				
				<label class="label">Recomendações:</label>
				<textarea name="recomendation" readonly>${exam.recomendation == null ? 'Nenhuma.':exam.recomendation}</textarea>
				
				<label class="label">Médico Solicitante:</label>
				<input type="text" name="physician" value="${exam.physician.name}" readonly>
				
				<label class="label">Data e hora:</label>
				<input type="text" name="examDate" value='<fmt:formatDate value="${exam.examDate.time}" pattern="dd/MM/yyyy - HH:mm"/>' readonly>
				
				<label class="label">Baixar Resultado:</label>
				<a href="downloadServlet?file=${exam.resultPath}" class="form_link">Clique aqui!</a>

				<hr/>
				<h3>Dados do Laudo</h3>
				
				<label class="label">Residente Responsável:</label>
				<input type="text" name="physician" value="${report.resident.name}" readonly>
				
				<label class="label">Data e hora da emissão:</label>
				<input type="text" name="examDate" value='<fmt:formatDate value="${report.emissionDate.time}" pattern="dd/MM/yyyy - HH:mm"/>' readonly>
				
				<label class="label">Conclusão (alterável):</label>
				<input list="idc-list" id="icd" type="text" name="conclusion" placeholder="Conclusão - CID" value="${report.conclusion.code}" required>
				<datalist id="idc-list">
				    <c:forEach var="icdCode" items="${ICD.values()}">
				        <option value="${icdCode.code}">${icdCode.description}</option>
				    </c:forEach>
				</datalist>
				
				<label class="label">Diagnóstico (alterável):</label>
				<textarea id="textArea" name="description" placeholder="Descrição do diagnóstico" required>${report.description}</textarea>
				<p id="charCount">250 caracteres restantes</p>
				
				<input type="text" name="logic" hidden="true" value="ReviewReport">
				<input type="text" name="reportId" hidden="true" value="${report.id}">
				<button form="form" class="button"> 
					<span class="button__text">Finalizar Revisão</span>
				</button>
			</form>
		</div>
	</main>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script type="text/javascript" src="script/validationScript.js"></script>
</body>
</html>