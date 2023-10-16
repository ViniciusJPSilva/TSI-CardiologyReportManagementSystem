package br.vjps.tsi.crms.models;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import br.vjps.tsi.crms.enumeration.Gender;

/**
 * Classe que representa um Paciente.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.enumeration.Gender
 */
public class Patient {

	private String cpf;
	private String name;
	private String email;
	private Gender gender;
	private Calendar birthDate;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Calendar getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	
    /**
     * Calcula a idade do paciente com base na data de nascimento.
     *
     * @return A idade do paciente.
     */
	public int getAge() {
        LocalDate parsedBirthDate = LocalDate.of(
        			birthDate.get(Calendar.DAY_OF_MONTH), 
        			birthDate.get(Calendar.MONTH), 
        			birthDate.get(Calendar.YEAR)
        		);

        // Calcula a idade.
        return Period.between(parsedBirthDate, LocalDate.now()).getYears();
	}
	
} // class Patient
