<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<div class="table">
			<h2>Exames do paciente ${patient.name}</h2>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Tipo</th>
						<th>Hipótese</th>
						<th>Médico Solicitante</th>
						<th>Data Prevista</th>
						<th>Realizar Exame</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="exam" items="${waitingExams}" varStatus="id">
						<tr bgcolor="${id.count % 2 != 0  ? 'ffffff' : 'cccccc'}">
							<td>${exam.id}</td>					
							<td>${exam.type.description}</td>					
							<td>${exam.hypothesis.description}</td>					
							<td>${exam.physician.name}</td>					
							<td><fmt:formatDate value="${exam.expectedDate.time}" pattern="dd/MM/yyyy" /></td>					
							<td class="btn-td">
								<form action="controller" method="post" class="login-form" id="form_${exam.id}">
									<input type="text" name="logic" hidden="true" value="FinishExam">
									<input type="number" name="examId" hidden="true" value="${exam.id}" id="examId_${exam.id}">
									<button form="form_${exam.id}" class="button"> 
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