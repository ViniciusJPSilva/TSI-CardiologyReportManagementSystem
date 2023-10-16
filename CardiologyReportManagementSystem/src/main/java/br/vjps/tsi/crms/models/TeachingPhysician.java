package br.vjps.tsi.crms.models;

import br.vjps.tsi.crms.enumeration.Title;

/**
 * Classe que representa um Médico Docente, que é uma extensão de Physician (Médico).
 * 
 * @author Vinícius J P Silva
 * 
 * @see br.vjps.tsi.crms.models.Physician
 * @see br.vjps.tsi.crms.enumeration.Title
 */
public class TeachingPhysician extends Physician {
	
	/** Titulação */
	private Title title;

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

} // class TeachingPhysician
