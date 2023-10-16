package br.vjps.tsi.crms.enumeration;

/**
 * Enumeração que representa os tipos de exame dispoíveis no sistema.
 * 
 * @author Vinicius J P Silva
 */
public enum ExamType {
	ECHOCARDIOGRAM("Ecocardiograma"),
	ELECTROCARDIOGRAM("Eletrocardiograma");
	
    private final String description;

    ExamType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
	public static ExamType getByDescription(String description) {
		for(ExamType type : ExamType.values())
			if(type.description.equalsIgnoreCase(description))
				return type;
		return null;
	}
}
