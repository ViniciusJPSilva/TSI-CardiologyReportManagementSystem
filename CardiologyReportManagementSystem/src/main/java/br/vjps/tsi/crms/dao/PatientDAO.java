package br.vjps.tsi.crms.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.vjps.tsi.crms.db.ConnectionFactory;
import br.vjps.tsi.crms.enumeration.Gender;
import br.vjps.tsi.crms.models.Patient;

/**
 * Esta classe implementa um Data Access Object (DAO) para a entidade "Patient".
 * Ela fornece métodos para acessar e manipular dados relacionados aos pacientes.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.dao.DataAccessObject
 * @see java.io.Closeable
 */
public class PatientDAO implements DataAccessObject<Patient>, Closeable {

	public static final String CPF = "cpf", NAME = "name", EMAIL = "email", GENDER = "gender", BIRTH_DATE = "birth_date";
	
	private Connection connection;

	public PatientDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public boolean insert(Patient data) {
		return false;
	}

	@Override
	public boolean delete(Patient data) {
		return false;
	}

	@Override
	public boolean update(Patient data) {
		return false;
	}

	@Override
	public Patient select(Long cpf) {
		return select(Long.toString(cpf));
	}
	
	public Patient select(String cpf) {
		final String sql = "SELECT * FROM patient WHERE cpf = ?";
		
		Patient patient = null;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, cpf);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				Calendar date = Calendar.getInstance();
				date.setTime(resultSet.getDate(BIRTH_DATE));
				
				patient = new Patient();
				patient.setCpf(cpf);
				patient.setName(resultSet.getString(NAME));
				patient.setEmail(resultSet.getString(EMAIL));
				patient.setGender(Gender.getByDescription(resultSet.getString(GENDER)));
				patient.setBirthDate(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patient;
	}

	@Override
	public List<Patient> selectAll() {
		final String sql = "SELECT * FROM patient";
		
		List<Patient> patientList = new ArrayList<>();
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Calendar date = Calendar.getInstance();
				date.setTime(resultSet.getDate(BIRTH_DATE));
				
				Patient patient = new Patient();
				patient.setCpf(resultSet.getString(CPF));
				patient.setName(resultSet.getString(NAME));
				patient.setEmail(resultSet.getString(EMAIL));
				patient.setGender(Gender.getByDescription(resultSet.getString(GENDER)));
				patient.setBirthDate(date);
				
				patientList.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patientList;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
