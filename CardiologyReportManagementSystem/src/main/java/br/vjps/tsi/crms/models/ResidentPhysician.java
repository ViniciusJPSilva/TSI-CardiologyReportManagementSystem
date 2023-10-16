package br.vjps.tsi.crms.models;

/**
 * Classe que representa um Médico Residente, que é uma extensão de Physician (Médico).
 * 
 * @author Vinícius J P Silva
 * 
 * @see br.vjps.tsi.crms.models.Physician
 */
public class ResidentPhysician extends Physician {
	
	/** Ano de inicio da residência */
	private Short residencyStartYear;
	
    /**
     * Registro de residência: ano de início da residência + CRM.
     * Formato padrão: "2023/12345"
     */
	private String residencyRegistration;

	public Short getResidencyStartYear() {
		return residencyStartYear;
	}

	public void setResidencyStartYear(Short residencyStartYear) {
		this.residencyStartYear = residencyStartYear;
	}
	
    /**
     * Obtém o registro de residência, que é uma combinação do ano de início da residência e CRM.
     * O formato padrão é "2023/12345".
     *
     * @return O registro de residência.
     */
	public String getResidencyRegistration() {
		updateResidencyRegistration();
		return residencyRegistration;
	}
	
    /**
     * Atualiza o registro de residência com o formato "yyyy/crm".
     */
	private void updateResidencyRegistration() {
		this.residencyRegistration = String.format("%04d/%d", residencyStartYear, getCrm());
	}
	
} // class ResidentPhysician
