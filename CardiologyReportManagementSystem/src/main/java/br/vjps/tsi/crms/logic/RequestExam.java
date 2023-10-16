package br.vjps.tsi.crms.logic;

import java.io.IOException;

import br.vjps.tsi.crms.dao.ExamDAO;
import br.vjps.tsi.crms.dao.PatientDAO;
import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.enumeration.ExamType;
import br.vjps.tsi.crms.enumeration.ICD;
import br.vjps.tsi.crms.models.Exam;
import br.vjps.tsi.crms.models.Patient;
import br.vjps.tsi.crms.models.Physician;
import br.vjps.tsi.crms.system.SystemSettings;
import br.vjps.tsi.crms.utility.EmailSender;
import br.vjps.tsi.crms.utility.Utility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestExam implements Logic {
	
	private static final String EMAIL_TITLE = "Confirmação de Agendamento de Exame";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistema.");
		
		Exam exam = new Exam();
		String cpf = request.getParameter("cpf").replaceAll("\\D+","");
		exam.setType(ExamType.getByDescription(request.getParameter("type")));
		exam.setHypothesis(ICD.getByCode(request.getParameter("icd")));
		exam.setRecomendation(request.getParameter("recomendation"));
		
		Patient patient;
		try (PatientDAO patientDAO = new PatientDAO()) {
			patient = patientDAO.select(cpf);
			if(patient == null) {
				request.setAttribute("message", "Paciente não encontrado!");
				return url;
			}
		} 
		
		exam.setPatient(patient);
		
		try (ExamDAO examDAO = new ExamDAO()) {
			for(Exam e : examDAO.selectByPatient(patient))
				if(e.getStatus() == ExamStatus.WAITING_EXAM && e.getType() == exam.getType()) {
					request.setAttribute("message", String.format("Paciente já possui um exame de %s agendado!", exam.getType().getDescription()));
					return url;
				}
			
			exam.setExpectedDate(Utility.getFutureDate(Exam.DAYS_UNTIL_EXAM_DATE));
			exam.setStatus(ExamStatus.WAITING_EXAM);
			exam.setPhysician(((Physician) request.getSession().getAttribute("user")));
			
			if(examDAO.insert(exam)) {
				request.setAttribute("message", String.format("Exame agendado com sucesso!", exam.getType().getDescription()));
				
				if(SystemSettings.EMAIL_MODE != SystemSettings.EMAIL_SENDING_MODE.DO_NOT_SEND) {
					String emailMessage = String.format(
			                "Prezado(a) %s,\n\nEstamos entrando em contato para confirmar o agendamento do seu exame.\nAqui estão os detalhes:\n\nPaciente: %s\nData prevista de realização: %s\nExame solicitado: %s\nRecomendações para realização do exame: %s\n\nMédico(a) solicitante:\n%s\nCRM: %d\n\nPor favor, verifique as informações acima e esteja no local do exame no horário agendado.\nSe você tiver alguma dúvida ou precisar reagendar, entre em contato conosco.\n\nAtenciosamente,\nEquipe do Médica.",
			                patient.getName(), patient.getName(), Utility.calendarToReadableString(exam.getExpectedDate()), 
			                exam.getType().getDescription(), ((!exam.getRecomendation().isBlank()) ? exam.getRecomendation() : "Nenhuma") , 
			                exam.getPhysician().getName(), exam.getPhysician().getCrm());
					EmailSender.sendAsync(emailMessage, EMAIL_TITLE, SystemSettings.TEST_EMAIL);
				}
			}
			
		} catch (Exception e) {
		}
		
		return url;
		
	}

}
