package br.vjps.tsi.crms.models;

import java.util.Calendar;

import br.vjps.tsi.crms.enumeration.ICD;

/**
 * Classe que representa um Laudo.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.models.Physician;
 * @see br.vjps.tsi.crms.models.Exam;
 * 
 * @see br.vjps.tsi.crms.enumeration.ICD;
 */
public class Report {
	
	private Long id;
	private Exam exam;
	private boolean definitive;
	private String description;
	private ICD conclusion;
	private ResidentPhysician resident;
	private TeachingPhysician teaching;
	private Calendar emissionDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public boolean isDefinitive() {
		return definitive;
	}
	
	public void setDefinitive(boolean definitive) {
		this.definitive = definitive;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ICD getConclusion() {
		return conclusion;
	}
	
	public void setConclusion(ICD conclusion) {
		this.conclusion = conclusion;
	}
	
	public ResidentPhysician getResident() {
		return resident;
	}

	public void setResident(ResidentPhysician resident) {
		this.resident = resident;
	}

	public TeachingPhysician getTeaching() {
		return teaching;
	}

	public void setTeaching(TeachingPhysician teaching) {
		this.teaching = teaching;
	}

	public Calendar getEmissionDate() {
		return emissionDate;
	}
	
	public void setEmissionDate(Calendar emissionDate) {
		this.emissionDate = emissionDate;
	}
	
}
