package br.vjps.tsi.crms.enumeration;

/**
 * Enumeração que representa os gêneros (sexo) de uma pessoa.
 * 
 * @author Vinicius J P Silva
 */
public enum PhysicianCategory {
	PHYSICIAN("Medico"),
    RESIDENT("Residente"),
    TEACHING("Docente");
	
    private final String category;

    PhysicianCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return category;
    }
    
	public static PhysicianCategory getByCategory(String category) {
		for(PhysicianCategory physicianCategory : PhysicianCategory.values())
			if(physicianCategory.category.equalsIgnoreCase(category))
				return physicianCategory;
		return null;
	}
}
