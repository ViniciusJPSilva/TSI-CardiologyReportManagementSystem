package br.vjps.tsi.crms.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.vjps.tsi.crms.system.SystemSettings;

/**
 * Classe responsável por criar conexões com o banco de dados PostgreSQL.
 * 
 * @author Vinícius J P Silva
 */
public class ConnectionFactory {
	
	private static final String DB_URL = "jdbc:postgresql://localhost/%s";
	
    /**
     * Obtém uma conexão com o banco de dados.
     *
     * @return Uma conexão com o banco de dados PostgreSQL.
     */
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(String.format(DB_URL, SystemSettings.DB_NAME), SystemSettings.DB_USER, SystemSettings.DB_PASSWD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
