package br.vjps.tsi.crms.enumeration;

/**
 * Enumeração que representa o Código Internacional de Doenças (CID) ou International Classification of Diseases (ICD).
 * 
 * @author Vinicius J P Silva
 */
public enum ICD {
	I42("I42", "Cardiomiopatias"),
	I46("I46", "Parada cardíaca"),
	I47("I47", "Taquicardia paroxística"),
	I48("I48", "Flutter e fibrilacao atrial"),
	I49("I49", "Outras arritmias cardíacas");
	
	private final String code, description;

	ICD(String code, String description) {
		this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
	public String getCode() {
		return code;
	}

	public static ICD getByCode(String code) {
		for(ICD status : ICD.values())
			if(status.code.equalsIgnoreCase(code))
				return status;
		return null;
	}
	
	public static ICD getByDescription(String description) {
		for(ICD icd : ICD.values())
			if(icd.description.equalsIgnoreCase(description))
				return icd;
		return null;
	}
}
