package br.vjps.tsi.crms.models;

/**
 * Classe que representa um usuário.
 */
public class User {
	
    /** O login do usuário. */
    private Long login;

    /** A senha do usuário. */
    private String password;

	public Long getLogin() {
		return login;
	}

	public void setLogin(Long login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
} // class User
