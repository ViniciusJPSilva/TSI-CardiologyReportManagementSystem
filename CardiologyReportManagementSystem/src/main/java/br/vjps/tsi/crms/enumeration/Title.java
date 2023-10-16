package br.vjps.tsi.crms.enumeration;

/**
 * Enumeração que representa títulos acadêmicos.
 * 
 * @author Vinicius J P Silva
 */
public enum Title {
	DOCTOR("Doutor"),
    ASSISTANT("Assistente"),
    LIBRE_DOCENT("Livre-docente"),
    FULL_PROFESSOR("Titular");
	
    private final String description;

    Title(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    
    public static Title getByDescription(String description) {
		for(Title title : Title.values())
			if(title.description.equalsIgnoreCase(description))
				return title;
		return null;
	}
}
