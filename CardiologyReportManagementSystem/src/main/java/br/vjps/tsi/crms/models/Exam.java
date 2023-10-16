package br.vjps.tsi.crms.models;

import java.util.Calendar;

import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.enumeration.ExamType;
import br.vjps.tsi.crms.enumeration.ICD;

/**
 * Classe que representa um Exame.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.models.Patient;
 * @see br.vjps.tsi.crms.models.Physician;
 * 
 * @see br.vjps.tsi.crms.enumeration.ExamStatus;
 * @see br.vjps.tsi.crms.enumeration.ExamType;
 * @see br.vjps.tsi.crms.enumeration.ICD;
 * 
 */
public class Exam {
	
	/** Dias corridos para a data do exame */
	public static final int DAYS_UNTIL_EXAM_DATE = 3;
	
	private Long id;
	private Patient patient;
	private ExamType type;
	private ExamStatus status;
	private ICD hypothesis;
	private String recomendation;
	private Physician physician;
	private Calendar expectedDate;
	private Calendar examDate;
	private String resultPath;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public ExamType getType() {
		return type;
	}
	
	public void setType(ExamType type) {
		this.type = type;
	}
	
	public ExamStatus getStatus() {
		return status;
	}
	
	public void setStatus(ExamStatus status) {
		this.status = status;
	}
	
	public ICD getHypothesis() {
		return hypothesis;
	}
	
	public void setHypothesis(ICD hypothesis) {
		this.hypothesis = hypothesis;
	}
	
	public String getRecomendation() {
		return recomendation;
	}
	
	public void setRecomendation(String recomendation) {
		this.recomendation = recomendation;
	}
	
	public Physician getPhysician() {
		return physician;
	}
	
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public Calendar getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Calendar expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Calendar getExamDate() {
		return examDate;
	}

	public void setExamDate(Calendar examDate) {
		this.examDate = examDate;
	}

	public String getResultPath() {
		return resultPath;
	}

	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
	
}
