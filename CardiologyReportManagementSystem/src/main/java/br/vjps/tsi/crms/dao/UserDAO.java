package br.vjps.tsi.crms.dao;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.vjps.tsi.crms.db.ConnectionFactory;
import br.vjps.tsi.crms.models.Physician;
import br.vjps.tsi.crms.models.User;

/**
 * Esta classe implementa um Data Access Object (DAO) para a entidade "User".
 * Ela fornece métodos para acessar e manipular dados relacionados aos usuários.
 * 
 * @author Vinicius J P Silva
 * 
 * @see br.vjps.tsi.crms.dao.DataAccessObject
 * @see java.io.Closeable
 */
public class UserDAO implements DataAccessObject<User>, Closeable {
	
	public static final String LOGIN = "login", PASSWD = "password";
	
	private Connection connection;

	public UserDAO() {
		this.connection = ConnectionFactory.getConnection();
	}
	
	@Override
	public boolean insert(User data) {
		return false;
	}

	@Override
	public boolean delete(User data) {
		return false;
	}

	@Override
	public boolean update(User data) {
		return false;
	}

	@Override
	public User select(Long login) {
		final String sql = "SELECT * FROM \"user\" WHERE login = ?";
		
		User user = null;
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.setLong(1, login);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				user = new User();
				user.setLogin(login);
				user.setPassword(resultSet.getString(PASSWD));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public List<User> selectAll() {
		final String sql = "SELECT * FROM \"user\"";
		
		List<User> userList = new ArrayList<>();
		try(PreparedStatement statement = connection.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				User user = new User();
				user.setLogin(resultSet.getLong(LOGIN));
				user.setPassword(resultSet.getString(PASSWD));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public Physician validate(Long login, String password) {
		String sql = "SELECT p.* FROM physician p INNER JOIN \"user\" u ON p.crm = u.login WHERE u.login = ? AND u.password = ?";
		
		Physician physician = null;
		try (PreparedStatement statement = connection.prepareStatement(sql)){
			statement.setLong(1, login);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				physician = PhysicianDAO.createByResultSet(resultSet);
			}
		} catch (SQLException e) {
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
