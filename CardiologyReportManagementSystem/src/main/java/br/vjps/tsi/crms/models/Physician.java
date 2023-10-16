package br.vjps.tsi.crms.models;

import br.vjps.tsi.crms.enumeration.PhysicianCategory;

/**
 * Classe que representa um Médico.
 * 
 * @author Vinícius J P Silva
 */
public class Physician {
	
	/** O número CRM (Conselho Regional de Medicina) do médico. */
	private Long crm;
	private String name;
	private PhysicianCategory category;
	
	public Long getCrm() {
		return crm;
	}
	
	public void setCrm(Long crm) {
		this.crm = crm;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public PhysicianCategory getCategory() {
		return category;
	}

	public void setCategory(PhysicianCategory category) {
		this.category = category;
	}
	
} // class Physician
