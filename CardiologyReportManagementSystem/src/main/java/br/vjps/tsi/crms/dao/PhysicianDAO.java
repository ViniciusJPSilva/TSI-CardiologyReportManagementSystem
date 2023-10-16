package br.vjps.tsi.crms.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vjps.tsi.crms.db.ConnectionFactory;
import br.vjps.tsi.crms.enumeration.PhysicianCategory;
import br.vjps.tsi.crms.enumeration.Title;
import br.vjps.tsi.crms.models.Physician;
import br.vjps.tsi.crms.models.ResidentPhysician;
import br.vjps.tsi.crms.models.TeachingPhysician;

/**
 * Esta classe implementa um Data Access Object (DAO) para a entidade "Physician".
 * Ela fornece métodos para acessar e manipular dados relacionados aos médicos.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.dao.DataAccessObject
 * @see java.io.Closeable
 */
public class PhysicianDAO implements DataAccessObject<Physician>, Closeable {

	public static final String CRM = "crm", NAME = "name", TYPE = "type", START_YEAR = "residency_start_year", TITLE = "title";
	
	private Connection connection;

	public PhysicianDAO() {
		this.connection = ConnectionFactory.getConnection();
	}

	@Override
	public boolean insert(Physician data) {
		return false;
	}

	@Override
	public boolean delete(Physician data) {
		return false;
	}

	@Override
	public boolean update(Physician data) {
		return false;
	}

	@Override	
	public Physician select(Long crm) {
		final String sql = "SELECT * FROM physician WHERE crm = ?";
		
		Physician physician = null;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, crm);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {			
				physician = createByResultSet(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return physician;
	}

	@Override
	public List<Physician> selectAll() {
		final String sql = "SELECT * FROM physician";
		
		List<Physician> physicianList = new ArrayList<>();
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {			
				physicianList.add(createByResultSet(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return physicianList;
	}
	
	
	public static Physician createByResultSet(ResultSet resultSet) {
		Physician physician = null;

		try {
			PhysicianCategory category = PhysicianCategory.getByCategory(resultSet.getString(TYPE));
			
			switch (category) {
				case PHYSICIAN:
					physician = new Physician();
					break;
				case RESIDENT:
					physician = new ResidentPhysician();
					((ResidentPhysician) physician).setResidencyStartYear(resultSet.getShort(START_YEAR));
					break;
				case TEACHING:
					physician = new TeachingPhysician();
					((TeachingPhysician) physician).setTitle(Title.getByDescription(resultSet.getString(TITLE)));
					break;
			}
			
			physician.setCategory(category);
			physician.setCrm(resultSet.getLong(CRM));
			physician.setName(resultSet.getString(NAME));
		} catch (SQLException e) {
			physician = null;
		}
		
		return physician;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
