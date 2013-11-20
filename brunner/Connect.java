package brunner;

import java.sql.*;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Stelle eine verbinung zu einem Mysql-Server her
 * @author helmuthbrunner
 */

public class Connect {

	private Connection c;
	private DataSource source;
	private MysqlDataSource mds;
	private Statement s;
	private ResultSet rs;
	
	/**
	 * Default-Connection
	 */
	public Connect() {      
        this.newConnection(new DataSource());
	}
	
	/**
	 * Connection
	 * @param source
	 */
	public Connect(DataSource source) {
		this.newConnection(source);
	}
	
	/**
	 * Stelle eine neue Verbindung zum Server her. Mit den jeweiligen Parametern.
	 * @param source die jeweiligen Parameter
	 */
	public void newConnection(DataSource source) {
		this.source=source;
		this.connect();
	}
	
	/**
	 * Eine Methode die die Verbinug zum Server herstellt.
	 */
	public void connect() {
		mds= new MysqlDataSource();
		mds.setServerName(source.getServername());
		mds.setUser(source.getUser());
		mds.setPassword(source.getPassword());
		mds.setDatabaseName(source.getDatabase());
		
		try {
			c= mds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	
	}
	
	/**
	 * Die aktulle Verbingung zum Server.
	 * @return
	 */
	public Connection getCurrentConnection() {
		return c;
	}
	
	/**
	 * Gibt das Erebnis eines Selectes aus.
	 * @param query der Select-Befehl
	 * @return ein ResultSet Objekt
	 */
	public ResultSet query(String query) {		
		try {
			s= c.createStatement();
			rs= s.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		
		return rs;
		
	}
	
	/**
	 * Schließt die Verbingung
	 */
	public void exit() {
		try {
			c.close();
			c= null;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Gibt die aktuellen Parameter fuer die aktuelle Verbindung zurueck.
	 * @return das Objekt die aktuellen Parameter enthaelt.
	 */
	public DataSource getSource() {
		return this.source;
	}
}
