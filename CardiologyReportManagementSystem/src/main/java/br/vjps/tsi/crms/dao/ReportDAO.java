package br.vjps.tsi.crms.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.vjps.tsi.crms.db.ConnectionFactory;
import br.vjps.tsi.crms.enumeration.ICD;
import br.vjps.tsi.crms.models.Patient;
import br.vjps.tsi.crms.models.Report;
import br.vjps.tsi.crms.models.ResidentPhysician;
import br.vjps.tsi.crms.models.TeachingPhysician;
import br.vjps.tsi.crms.utility.Utility;

/**
 * Esta classe implementa um Data Access Object (DAO) para a entidade "Report".
 * Ela fornece métodos para acessar e manipular dados relacionados aos laudos médicos.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.dao.DataAccessObject
 * @see java.io.Closeable
 */
public class ReportDAO implements DataAccessObject<Report>, Closeable {
	public static final String ID = "id", EXAM = "exam_id", DEFINITIVE = "is_definitive", DESCRIPTION = "description", CONCLUSION = "conclusion",
			RESIDENT = "resident_crm", TEACHING = "teaching_crm", EMISSION_DATE = "emission_date";

	private Connection connection;

	public ReportDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	@Override
	public boolean insert(Report data) {
		final String sql = "INSERT INTO report (exam_id, description, conclusion, resident_crm, emission_date) VALUES (?, ?, ?, ?, ?)";
		
		boolean result = false;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, data.getExam().getId());
			statement.setString(2, data.getDescription());
			statement.setString(3, data.getConclusion().getCode());
			statement.setLong(4, data.getResident().getCrm());
			statement.setTimestamp(5, new Timestamp(data.getEmissionDate().getTimeInMillis()));
			
			statement.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public boolean delete(Report data) {
		return false;
	}

	@Override
	public boolean update(Report data) {
		final String sql = "UPDATE report SET is_definitive = ?, description = ?, conclusion = ?, teaching_crm = ? WHERE id = ?";
		
		boolean result = false;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setBoolean(1, data.isDefinitive());
			statement.setString(2, data.getDescription());
			statement.setString(3, data.getConclusion().getCode());
			statement.setLong(4, data.getTeaching().getCrm());
			statement.setLong(5, data.getId());
			
			statement.execute();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	@Override
	public Report select(Long code) {
		final String sql = "SELECT * FROM report WHERE id = ?";
		
		ExamDAO examDAO = new ExamDAO();
		PhysicianDAO physicianDAO = new PhysicianDAO();
		
		Report report = null;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, code);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				report = createByResultSet(resultSet);

				if(report != null) {
					report.setExam(examDAO.select(resultSet.getLong(EXAM)));
					report.setResident((ResidentPhysician) physicianDAO.select(resultSet.getLong(RESIDENT)));
					report.setTeaching((TeachingPhysician) physicianDAO.select(resultSet.getLong(TEACHING)));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			examDAO.close();
			physicianDAO.close();
		}
		
		return report;
	}

	@Override
	public List<Report> selectAll() {
		final String sql = "SELECT * FROM report";
		
		ExamDAO examDAO = new ExamDAO();
		PhysicianDAO physicianDAO = new PhysicianDAO();
		
		List<Report> reportList = new ArrayList<>();
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				Report report = createByResultSet(resultSet);

				if(report != null) {
					report.setExam(examDAO.select(resultSet.getLong(EXAM)));
					report.setResident((ResidentPhysician) physicianDAO.select(resultSet.getLong(RESIDENT)));
					report.setTeaching((TeachingPhysician) physicianDAO.select(resultSet.getLong(TEACHING)));
				
					reportList.add(report);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			examDAO.close();
			physicianDAO.close();
		}
		
		return reportList;
	}
	
	public List<Report> selectByPatient(Patient patient) {
		final String cpf = patient.getCpf();
		return selectAll().stream().filter((report) -> report.getExam().getPatient().getCpf().equalsIgnoreCase(cpf)).collect(Collectors.toList());
	}
	
	
	public static Report createByResultSet(ResultSet resultSet) {
		Report report = new Report();

		try {
			Timestamp emissionDate = resultSet.getTimestamp(EMISSION_DATE);
			
			report.setId(resultSet.getLong(ID));
			report.setDefinitive(resultSet.getBoolean(DEFINITIVE));
			report.setDescription(resultSet.getString(DESCRIPTION));
			report.setConclusion(ICD.getByCode(resultSet.getString(CONCLUSION)));
			if(emissionDate != null)
				report.setEmissionDate(Utility.timestampToCalendar(emissionDate));
		} catch (SQLException e) {
			report = null;
		}
		
		return report;
	}
	

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}






