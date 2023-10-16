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
			<h2>Exames do paciente ${patient.name} - Aguardando Laudo</h2>
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Tipo</th>
						<th>Hipótese</th>
						<th>Médico Solicitante</th>
						<th>Data do Exame</th>
						<th>Resultado</th>
						<th>Emitir Laudo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="exam" items="${examsWaitingReport}" varStatus="id">
						<tr bgcolor="${id.count % 2 != 0  ? 'ffffff' : 'cccccc'}">
							<td>${exam.id}</td>					
							<td>${exam.type.description}</td>					
							<td>${exam.hypothesis.description}</td>					
							<td>${exam.physician.name}</td>					
							<td><fmt:formatDate value="${exam.examDate.time}" pattern="dd/MM/yyyy" /></td>					
							<td class="btn-td">
								<a href="downloadServlet?file=${exam.resultPath}" class="table_link">
									<span class="material-symbols-outlined">download</span>
								</a>
							</td>					
							<td class="btn-td">
								<a href="fill-out-report.jsp?id=${exam.id}&patient=${patient.name}">
									<button form="form" class="button"> 
										<span class="material-symbols-outlined">edit_document</span>
									</button>
								</a>
							</td>					
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>
</body>
</html>