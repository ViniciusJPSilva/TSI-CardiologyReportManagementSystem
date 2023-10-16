package br.vjps.tsi.crms.enumeration;

/**
 * Enumeração que representa as situações de um exame.
 * 
 * @author Vinicius J P Silva
 */
public enum ExamStatus {
	WAITING_EXAM("Aguardando exame"),
	WAITING_REPORT("Aguardando laudo"),
	REPORT_CARRIED_OUT("Laudo realizado"),
	EXAM_CANCELED("Exame cancelado");
	
    private final String description;

    ExamStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
	public static ExamStatus getByDescription(String description) {
		for(ExamStatus status : ExamStatus.values())
			if(status.description.equalsIgnoreCase(description))
				return status;
		return null;
	}
}
