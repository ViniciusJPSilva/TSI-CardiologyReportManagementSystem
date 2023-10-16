package br.vjps.tsi.crms.logic;

import java.io.IOException;
import java.util.Calendar;

import br.vjps.tsi.crms.dao.ExamDAO;
import br.vjps.tsi.crms.dao.ReportDAO;
import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.enumeration.ICD;
import br.vjps.tsi.crms.models.Exam;
import br.vjps.tsi.crms.models.Report;
import br.vjps.tsi.crms.models.ResidentPhysician;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CreateReport implements Logic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistema.");
		
		Long id = Long.parseLong(request.getParameter("examId"));
		
		Exam exam;
		try (ExamDAO examDAO = new ExamDAO()) {
			exam = examDAO.select(id);
		 
			if(exam == null) return url;
			
			Report report = new Report();
			report.setExam(exam);
			report.setConclusion(ICD.getByCode(request.getParameter("conclusion")));
			report.setDescription(request.getParameter("description"));
			report.setEmissionDate(Calendar.getInstance());
			report.setResident(((ResidentPhysician) request.getSession().getAttribute("user")));
			
			try(ReportDAO reportDAO = new ReportDAO()) {
				if(!reportDAO.insert(report)) {
					request.setAttribute("message", "Não foi possível emitir o laudo! Operação cancelada.");
					return url;
				}
			}
			
			exam.setStatus(ExamStatus.REPORT_CARRIED_OUT);
			examDAO.update(exam);
			
			request.setAttribute("message", "Laudo emitido com sucesso!");
		}
		
		return url;
	}

}
