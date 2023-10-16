package br.vjps.tsi.crms.logic;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import br.vjps.tsi.crms.dao.ExamDAO;
import br.vjps.tsi.crms.dao.PatientDAO;
import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.models.Exam;
import br.vjps.tsi.crms.models.Patient;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PerformsExam implements Logic {

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
		
		try (ExamDAO examDAO = new ExamDAO()) {
			List<Exam> waitingExams = examDAO.selectByPatient(patient).stream()
				    .filter(exam -> exam.getStatus() == ExamStatus.WAITING_EXAM)
				    .collect(Collectors.toList());
			
			waitingExams.sort((e1, e2) -> {
				return Long.compare(e1.getId(), e2.getId());
			});
			
			if(waitingExams.isEmpty()) {
				request.setAttribute("message", String.format("O(a) paciente %s não possui exames à serem realizados!", patient.getName().split(" ")[0]));
			} else {
				request.setAttribute("waitingExams", waitingExams);
				request.setAttribute("patient", patient);
				url = "waiting-exams.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return url;
	}

}
