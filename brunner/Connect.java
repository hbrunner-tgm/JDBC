package brunner;

import java.sql.*;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Stelle eine Verbinung zu einem Server her.
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
	 * Neue Verbinung mit den angegeben Parametern aufbauen.
	 * @param source die neuen Verbindungparameter
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
	 * Eine Methode die die Verbindnug zum Server herstellt.
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
			String fm="";
			
			if(e.getMessage().indexOf("Host")!=-1)
				fm+= "\n" + "Hostname("+source.getServername()+") konnte nicht gefunden werden.";
			if(e.getMessage().substring(0, 16).equals("Unknown database"))
				fm+= "\n" + "Datenbank("+source.getDatabase()+") ist auf diesem Server nicht vorhanden";
			if(e.getMessage().substring(0, 13).equals("Access denied"))
				fm+= "\n" + "Benutzer("+source.getUser()+") oder Passwort("+source.getPassword()+") sind Falsch";
		
			
			if(!fm.equals("")) {
				
				fm+= "\n\n"+ "Bitte die richtigen Parameter in die Textfelder eintragen und auf \"Connect\" klicken.";
				
				JOptionPane.showMessageDialog(null, fm);
			}
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
	 * Gibt das Erebniss eines Selectes aus.
	 * @param query der Select-Befehl
	 * @return ein ResultSet Objekt
	 */
	public ResultSet query(String query) {		
		try {
			s= c.createStatement();
			rs= s.executeQuery(query);
		}catch (SQLException e) {
			//System.err.println("SQL-Exception -:- Connect:query");
			String str= e.getMessage();
			int i= (int) str.length()/2;
			JOptionPane.showMessageDialog(null, str.subSequence(0, i)+"\n"+str.subSequence(i, e.getMessage().length()));
		}catch (NullPointerException e) {
			
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
		}catch (SQLException e) {
			System.err.println("Fehler in exit -:- Connect:exit");
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
