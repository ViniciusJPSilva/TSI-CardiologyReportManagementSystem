package br.vjps.tsi.crms.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.vjps.tsi.crms.db.ConnectionFactory;
import br.vjps.tsi.crms.enumeration.ExamStatus;
import br.vjps.tsi.crms.enumeration.ExamType;
import br.vjps.tsi.crms.enumeration.ICD;
import br.vjps.tsi.crms.models.Exam;
import br.vjps.tsi.crms.models.Patient;
import br.vjps.tsi.crms.utility.Utility;

/**
 * Esta classe implementa um Data Access Object (DAO) para a entidade "Exam".
 * Ela fornece métodos para acessar e manipular dados relacionados aos exames.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.dao.DataAccessObject
 * @see java.io.Closeable
 */
public class ExamDAO implements DataAccessObject<Exam>, Closeable {
	public static final String ID = "id", PATIENT = "patient_cpf", TYPE = "type", STATUS = "status", HYPOTHESIS = "hypothesis", RECOMENDATION = "recomendation",
			PHYSICIAN = "physician_crm", EXPEC_DATE = "expected_date", EXAM_DATE = "exam_date", RES_PATH = "result_path";

	private Connection connection;

	public ExamDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	@Override
	public boolean insert(Exam data) {
		final String sql = "INSERT INTO exam (patient_cpf, \"type\", status, hypothesis, recomendation, physician_crm, expected_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		boolean result = false;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, data.getPatient().getCpf());
			statement.setString(2, data.getType().getDescription());
			statement.setString(3, data.getStatus().getDescription());
			statement.setString(4, data.getHypothesis().getCode());
			statement.setString(5, data.getRecomendation());
			statement.setLong(6, data.getPhysician().getCrm());
			statement.setDate(7, new Date(data.getExpectedDate().getTimeInMillis()));
			
			statement.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean delete(Exam data) {
		return false;
	}

	@Override
	public boolean update(Exam data) {
		final String sql = "UPDATE exam SET status = ?, exam_date = ?, result_path = ? WHERE id = ?";
		
		boolean result = false;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, data.getStatus().getDescription());
			
			statement.setTimestamp(2, (data.getExamDate() != null) ? new Timestamp(data.getExamDate().getTimeInMillis()) : null);
			
			statement.setString(3, data.getResultPath());
			statement.setLong(4, data.getId());
			
			statement.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public Exam select(Long code) {
		final String sql = "SELECT * FROM exam WHERE id = ?";
		
		PatientDAO patientDAO = new PatientDAO();
		PhysicianDAO physicianDAO = new PhysicianDAO();
		
		Exam exam = null;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, code);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				exam = createByResultSet(resultSet);

				if(exam != null) {
					exam.setPatient(patientDAO.select(resultSet.getString(PATIENT)));
					exam.setPhysician(physicianDAO.select(resultSet.getLong(PHYSICIAN)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			patientDAO.close();
			physicianDAO.close();
		}
		
		return exam;
	}

	@Override
	public List<Exam> selectAll() {
		final String sql = "SELECT * FROM exam";
		
		PatientDAO patientDAO = new PatientDAO();
		PhysicianDAO physicianDAO = new PhysicianDAO();
		
		List<Exam> examList = new ArrayList<>();
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Exam exam = createByResultSet(resultSet);

				if(exam != null) {
					exam.setPatient(patientDAO.select(resultSet.getString(PATIENT)));
					exam.setPhysician(physicianDAO.select(resultSet.getLong(PHYSICIAN)));
				
					examList.add(exam);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			patientDAO.close();
			physicianDAO.close();
		}
		
		return examList;
	}
	
	public List<Exam> selectByPatient(Patient patient) {
		final String sql = "SELECT * FROM exam WHERE patient_cpf = ?";
		
		PatientDAO patientDAO = new PatientDAO();
		PhysicianDAO physicianDAO = new PhysicianDAO();
		
		List<Exam> examList = new ArrayList<>();
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setString(1, patient.getCpf());
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Exam exam = createByResultSet(resultSet);

				if(exam != null) {
					exam.setPatient(patientDAO.select(resultSet.getString(PATIENT)));
					exam.setPhysician(physicianDAO.select(resultSet.getLong(PHYSICIAN)));
				
					examList.add(exam);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			patientDAO.close();
			physicianDAO.close();
		}
		
		return examList;
	}
	
	public static Exam createByResultSet(ResultSet resultSet) {
		Exam exam = new Exam();

		try {
			Calendar expectedDate = Calendar.getInstance();
			expectedDate.setTime(resultSet.getDate(EXPEC_DATE));

			Timestamp examDate = resultSet.getTimestamp(EXAM_DATE);
			
			exam.setId(resultSet.getLong(ID));
			exam.setType(ExamType.getByDescription(resultSet.getString(TYPE)));
			exam.setStatus(ExamStatus.getByDescription(resultSet.getString(STATUS)));
			exam.setHypothesis(ICD.getByCode(resultSet.getString(HYPOTHESIS)));
			exam.setExpectedDate(expectedDate);
			if(examDate != null)
				exam.setExamDate(Utility.timestampToCalendar(examDate));
			exam.setResultPath(resultSet.getString(RES_PATH));
		} catch (SQLException e) {
			exam = null;
		}
		
		return exam;
	}
	

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}






