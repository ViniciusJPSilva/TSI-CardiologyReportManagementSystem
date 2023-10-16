<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="br.vjps.tsi.crms.enumeration.PhysicianCategory"%>
<%@ include file="_authentication.jsp" %>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet">
</head>

<body>
	<c:import url="_header.jsp" />
	<main>
		<section class="hero-section">
			<div class="card-grid">
				<c:if test="${sessionScope.user.category == PhysicianCategory.PHYSICIAN}">
					<button class="card" onclick="location.href='examination-request.jsp'">
						<span class="card__background"
							style="background-image: url(images/request_exam.png)"></span>
						<div class="card__content">
							<p class="card__category">Exames</p>
							<h3 class="card__heading">Solicitar Exame</h3>
						</div>
					</button>
				</c:if>
				<c:if test="${sessionScope.user.category == PhysicianCategory.RESIDENT}">					
					<button class="card" onclick="location.href='search-patient.jsp?destiny=PerformsExam'">
						<span class="card__background"
							style="background-image: url(images/exam.jpg)"></span>
						<div class="card__content">
							<p class="card__category">Exames</p>
							<h3 class="card__heading">Realizar Exame</h3>
						</div>
					</button>
				</c:if>
				<c:if test="${sessionScope.user.category == PhysicianCategory.RESIDENT}">
					<button class="card" onclick="location.href='search-patient.jsp?destiny=PerformsReport'">
						<span class="card__background"
							style="background-image: url(images/generate_report.jpg)"></span>
						<div class="card__content">
							<p class="card__category">Laudos</p>
							<h3 class="card__heading">Emitir Laudo</h3>
						</div>
					</button>
				</c:if>
				<c:if test="${sessionScope.user.category == PhysicianCategory.TEACHING}">
					<form class="card" action="controller" method="post">
						<input type="hidden" name="logic" value="ListPendingReports">
						<button>
							<span class="card__background"
								style="background-image: url(images/evaluate.jpg)"></span>
							<div class="card__content">
								<p class="card__category">Laudos</p>
								<h3 class="card__heading">Avaliar Laudo</h3>
							</div>
						</button>
					</form>
				</c:if>
				<button class="card" onclick="location.href='search-patient.jsp?destiny=ListDefinitiveReports'">
					<span class="card__background"
						style="background-image: url(images/consult.jpg)"></span>
					<div class="card__content">
						<p class="card__category">Laudos</p>
						<h3 class="card__heading">Consultar Laudo</h3>
					</div>
				</button>
			</div>
		</section>
	</main>
</body>

</html>