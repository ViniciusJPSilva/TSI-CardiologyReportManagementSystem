package br.vjps.tsi.crms.routines;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import br.vjps.tsi.crms.dao.ExamDAO;
import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.models.Exam;
import br.vjps.tsi.crms.system.SystemSettings;
import br.vjps.tsi.crms.utility.EmailSender;
import br.vjps.tsi.crms.utility.Utility;

/**
 * Classe responsável por cancelar exames agendados que não foram realizados dentro do prazo previsto.
 * 
 * @author Vinícius J P Silva
 */
public class CancelExams {
	
	private static final String EMAIL_TITLE = "Cancelamento do Exame";
	
	private CancelExams() {}

    /**
     * Executa o cancelamento de exames que não foram realizados dentro do prazo previsto.
     * Os exames cancelados são atualizados para o status "EXAM_CANCELED" e notificações por
     * e-mail são enviadas aos pacientes.
     */
	public static void execute() {
		try(ExamDAO examDAO = new ExamDAO()) {
			Calendar today = Utility.getTodayCalendar();
			List<Exam> canceledExams = examDAO.selectAll().stream()
					.filter(exam -> ((exam.getStatus() == ExamStatus.WAITING_EXAM) && exam.getExpectedDate().before(today)))
					.collect(Collectors.toList());
			
			
			canceledExams.forEach(exam -> {
				exam.setStatus(ExamStatus.EXAM_CANCELED);
				if(examDAO.update(exam))
					if(SystemSettings.EMAIL_MODE != SystemSettings.EMAIL_SENDING_MODE.DO_NOT_SEND) {
						String emailMessage = String.format(
				                "Prezado(a) %s,\n\nEstamos entrando em contato para informar que o exame previsto para ser realizado no dia %s foi CANCELADO!\nEntre em contato com nosso setor responsável para maiores detalhes e a possibilidade de reagendar o exame.\n\nDetalhes do Exame:\nTipo: %s\nMédico(a) solicitante:\n%s\nCRM: %d\n\nSe você tiver alguma dúvida, entre em contato conosco.\n\nAtenciosamente,\nEquipe do Médica.",
				                exam.getPatient().getName(), Utility.calendarToReadableString(exam.getExpectedDate()), 
				                exam.getType().getDescription(), exam.getPhysician().getName(), exam.getPhysician().getCrm());
						EmailSender.sendAsync(emailMessage, EMAIL_TITLE, SystemSettings.TEST_EMAIL);
					}
			});
		}
	}

}
