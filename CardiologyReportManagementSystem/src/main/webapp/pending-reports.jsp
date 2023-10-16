<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="_teaching-auth.jsp"%>
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
		<div class="table">
			<h2>Laudos Provisórios</h2>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Paciente</th>
						<th>CPF</th>
						<th>Exame</th>
						<th>Examinador</th>
						<th>Data de Emissão</th>
						<th>Resultado</th>
						<th>Revisar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${reports}" varStatus="id">
						<tr bgcolor="${id.count % 2 != 0  ? 'ffffff' : 'cccccc'}">
							<td>${report.id}</td>					
							<td>${report.exam.patient.name}</td>					
							<td>${report.exam.patient.cpf}</td>					
							<td>${report.exam.type.description}</td>					
							<td>${report.resident.name}</td>				
							<td><fmt:formatDate value="${report.emissionDate.time}" pattern="dd/MM/yyyy" /></td>		
							<td class="btn-td">
								<a href="downloadServlet?file=${report.exam.resultPath}" class="table_link">
									<span class="material-symbols-outlined">download</span>
								</a>
							</td>				
							<td class="btn-td">
								<form action="controller" method="post" class="login-form" id="form_${report.id}">
									<input type="text" name="logic" hidden="true" value="GetReport">
									<input type="text" name="destiny" hidden="true" value="review-report.jsp">
									<input type="number" name="reportId" hidden="true" value="${report.id}" id="reportId_${report.id}">
									<button form="form_${report.id}" class="button"> 
										<span class="material-symbols-outlined">edit_document</span>
									</button>
								</form>
							</td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>