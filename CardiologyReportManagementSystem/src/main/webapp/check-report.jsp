<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="_authentication.jsp"%>
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
			<h2>Consultando Laudo - ID ${report.id}</h2>
			
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
				
				<label class="label">Conclusão:</label>
				<input type="text" name="conclusion" value="${report.conclusion.code} - ${report.conclusion.description} " readonly>	
				
				<label class="label">Diagnóstico:</label>
				<textarea id="textArea" name="description" readonly>${report.description}</textarea>
				
				<label class="label">Residente Responsável:</label>
				<input type="text" name="residente" value="${report.resident.name}" readonly>
				
				<label class="label">Matrícula do Residente:</label>
				<input type="text" name="residenteRegistration" value="${report.resident.residencyRegistration}" readonly>
				
				<label class="label">Revisado por:</label>
				<input type="text" name="teacher" value="${report.teaching.name} (${report.teaching.title.description})" readonly>
				
				<label class="label">Data e hora da emissão:</label>
				<input type="text" name="examDate" value='<fmt:formatDate value="${report.emissionDate.time}" pattern="dd/MM/yyyy - HH:mm"/>' readonly>
				
				<input type="text" id="cpf" name="cpf" value="${patient.cpf}" hidden="true">
				<input type="text" name="logic" hidden="true" value="ListDefinitiveReports">
				<button form="form" class="button"> 
					<span class="button__text">Voltar</span>
				</button>
				<br>
			</form>
			<button onclick="location.href='menu.jsp'" class="button"> 
				<span class="button__text">Menu</span>
			</button>
		</div>
	</main>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script type="text/javascript" src="script/validationScript.js"></script>
</body>
</html>