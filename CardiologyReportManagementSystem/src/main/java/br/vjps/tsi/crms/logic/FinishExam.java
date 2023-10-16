package br.vjps.tsi.crms.logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import br.vjps.tsi.crms.dao.ExamDAO;
import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.enumeration.ExamType;
import br.vjps.tsi.crms.models.Exam;
import br.vjps.tsi.crms.system.SystemSettings;
import br.vjps.tsi.crms.utility.PDFGenerator;
import br.vjps.tsi.crms.utility.Utility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FinishExam implements Logic {
	
	private static final String[] IMGS_ECHOCARDIOGRAM = {"eco1.jpeg", "eco2.jpeg", "eco3.jpeg", "eco4.jpeg", "eco5.jpeg", "eco6.jpeg", "eco7.jpeg", "eco8.jpeg", "eco9.jpeg", "eco10.jpeg"},
			IMGS_ELECTROCARDIOGRAM = {"eletro1.jpeg", "eletro2.jpg", "eletro3.jpg", "eletro4.jpg", "eletro5.jpg", "eletro6.jpg", "eletro7.png", "eletro8.png", "eletro10.jpg"};

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "message.jsp";
		request.setAttribute("message", "Ocorreu um erro! Contate o admin do sistema.");
		
		Long id = Long.parseLong(request.getParameter("examId"));
		
		Exam exam;
		try (ExamDAO examDAO = new ExamDAO()) {
			exam = examDAO.select(id);
		 
			if(exam == null) return url;
			
			exam.setExamDate(Calendar.getInstance());
			exam.setStatus(ExamStatus.WAITING_REPORT);
			
			// Obtendo 2 imagens aleatórias, baseado no tipo do exame.
			List<String> imgPaths = getRandomPicsByType(2, exam.getType(), SystemSettings.WEB_APP_ROOT_PATH);
			String pdfText = String.format(
	                "ID: %04d\nPaciente: %s\nData e hora: %s\nExame solicitado: %s\nHipótese: %s - %s\n\nMédico solicitante: %s\nCRM: %d\n\n",
	                exam.getId(), exam.getPatient().getName(), Utility.calendarToReadableStringDateAndHour(exam.getExamDate()), 
	                exam.getType().getDescription(), exam.getHypothesis().getCode(), exam.getHypothesis().getDescription(),
	                exam.getPhysician().getName(), exam.getPhysician().getCrm());
			String resultPath = PDFGenerator.generatePDF(imgPaths, pdfText, SystemSettings.WEB_APP_ROOT_PATH + PDFGenerator.PDF_DIRECTORY, String.format("exam-%03d-result.pdf", id));
			
			if(resultPath == null) {
				request.setAttribute("message", "Ocorreu um erro durante a execução do exame!");
				return url;
			}
			
			exam.setResultPath(resultPath);
			if(!examDAO.update(exam)) {
				request.setAttribute("message", "Erro ao atualizar os dados do exame!");
				return url;
			}
			
			request.setAttribute("exam", exam);
			url = "finish-exam.jsp";
		}
		
		return url;
	}

	/**
	 * Gera uma lista de caminhos de imagens aleatórias com base no tipo de exame especificado.
	 *
	 * @param quantity A quantidade de imagens a serem geradas.
	 * @param type     O tipo de exame (Exame de Ecocardiograma ou Eletrocardiograma).
	 * @return Uma lista de caminhos de imagens aleatórias com base no tipo de exame.
	 */
	private List<String> getRandomPicsByType(int quantity, ExamType type, String rootPath) {
		Random generator = new Random();		
		List<String> imgPaths = new ArrayList<>();
		
		if(type == ExamType.ECHOCARDIOGRAM) {
			imgPaths.add(rootPath + PDFGenerator.IMG_DIRECTORY_ECHOCARDIOGRAM + IMGS_ECHOCARDIOGRAM[generator.nextInt(IMGS_ECHOCARDIOGRAM.length)]);
			imgPaths.add(rootPath + PDFGenerator.IMG_DIRECTORY_ECHOCARDIOGRAM +  IMGS_ECHOCARDIOGRAM[generator.nextInt(IMGS_ECHOCARDIOGRAM.length)]);
		} else {
			imgPaths.add(rootPath + PDFGenerator.IMG_DIRECTORY_ELECTROCARDIOGRAM + IMGS_ELECTROCARDIOGRAM[generator.nextInt(IMGS_ELECTROCARDIOGRAM.length)]);
			imgPaths.add(rootPath + PDFGenerator.IMG_DIRECTORY_ELECTROCARDIOGRAM + IMGS_ELECTROCARDIOGRAM[generator.nextInt(IMGS_ELECTROCARDIOGRAM.length)]);
		}
		
		return imgPaths;
	}

}
