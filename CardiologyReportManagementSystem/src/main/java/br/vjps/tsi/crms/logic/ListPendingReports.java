package br.vjps.tsi.crms.logic;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.vjps.tsi.crms.dao.ReportDAO;
import br.vjps.tsi.crms.models.Report;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListPendingReports implements Logic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistemaa.");
		
		try(ReportDAO reportDAO = new ReportDAO()) {
			List<Report> pendingReports = reportDAO.selectAll().stream()
				    .filter(exam -> !exam.isDefinitive())
				    .collect(Collectors.toList());
			
			// Ordena pelo nome do paciente.
			pendingReports.sort((r1, r2) -> {
				return r1.getExam().getPatient().getName().compareToIgnoreCase(r2.getExam().getPatient().getName());
			});
			
			if(pendingReports.isEmpty()) {
				request.setAttribute("message", "Não há laudos com revisão pendente!");
			} else {
				request.setAttribute("reports", pendingReports);
				url = "pending-reports.jsp";
			}
		}
		
		return url;
	}
	
}
