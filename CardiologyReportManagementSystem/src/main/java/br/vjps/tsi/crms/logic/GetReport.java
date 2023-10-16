package br.vjps.tsi.crms.logic;

import java.io.IOException;

import br.vjps.tsi.crms.dao.ReportDAO;
import br.vjps.tsi.crms.models.Report;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetReport implements Logic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistema.");
		
		Long id = Long.parseLong(request.getParameter("reportId"));
		String destiny = request.getParameter("destiny");
		
		Report report;
		try(ReportDAO reportDAO = new ReportDAO()) {
			report = reportDAO.select(id);
			
			if(report == null) {
				request.setAttribute("message", "Laudo inexistente!");
			} else {
				request.setAttribute("report", report);
				url = destiny;
			}
		}
		
		return url;
	}

	
}
