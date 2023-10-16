package br.vjps.tsi.crms.logic;

import java.io.IOException;

import br.vjps.tsi.crms.dao.ReportDAO;
import br.vjps.tsi.crms.enumeration.ICD;
import br.vjps.tsi.crms.models.Report;
import br.vjps.tsi.crms.models.TeachingPhysician;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReviewReport implements Logic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistema.");
		
		Long id = Long.parseLong(request.getParameter("reportId"));
		
		Report report;
		try (ReportDAO reportDAO = new ReportDAO()) {
			report = reportDAO.select(id);
		 
			if(report == null) return url;
			
			report.setConclusion(ICD.getByCode(request.getParameter("conclusion")));
			report.setDescription(request.getParameter("description"));
			report.setDefinitive(true);
			report.setTeaching(((TeachingPhysician) request.getSession().getAttribute("user")));
			
			if(!reportDAO.update(report)) {
				request.setAttribute("message", "Não foi possível revisar o laudo! Operação cancelada.");
				return url;
			}
			
			request.setAttribute("message", "Laudo revisado com sucesso! Tornou-se definitivo.");
		}
		
		return url;
	}

}
