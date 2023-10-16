package br.vjps.tsi.crms.logic;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.vjps.tsi.crms.dao.PatientDAO;
import br.vjps.tsi.crms.dao.ReportDAO;
import br.vjps.tsi.crms.models.Patient;
import br.vjps.tsi.crms.models.Report;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListDefinitiveReports implements Logic {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistema.");
		
		String cpf = request.getParameter("cpf").replaceAll("\\D+","");
		
		Patient patient;
		try (PatientDAO patientDAO = new PatientDAO()) {
			patient = patientDAO.select(cpf);
			if(patient == null) {
				request.setAttribute("message", "Paciente não encontrado!");
				return url;
			}
		} 
		
		try (ReportDAO reportDAO = new ReportDAO()) {
			List<Report> definitiveReports = reportDAO.selectByPatient(patient).stream()
				    .filter(exam -> exam.isDefinitive())
				    .collect(Collectors.toList());
			
			if(definitiveReports.isEmpty()) {
				request.setAttribute("message", String.format("O(a) paciente %s não possui nenhum laudo definitivo!", patient.getName().split(" ")[0]));
			} else {
				request.setAttribute("reports", definitiveReports);
				request.setAttribute("patient", patient);
				url = "definitive-reports.jsp";
			}
		} catch (Exception e) {
		}
		
		return url;
	}

}