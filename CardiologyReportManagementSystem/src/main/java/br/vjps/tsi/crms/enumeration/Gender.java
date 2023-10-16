package br.vjps.tsi.crms.enumeration;

/**
 * Enumeração que representa os gêneros (sexo) de uma pessoa.
 * 
 * @author Vinicius J P Silva
 */
public enum Gender {
	MALE("Masculino"),
    FEMALE("Feminino");
	
    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
	public static Gender getByDescription(String description) {
		for(Gender gender : Gender.values())
			if(gender.description.equalsIgnoreCase(description))
				return gender;
		return null;
	}
}
