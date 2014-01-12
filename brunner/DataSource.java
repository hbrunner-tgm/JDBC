package brunner;
/**
 * Eine Klasse die die Eingabeparameter verwaltet.
 * @author helmuthbrunner
 */
public class DataSource {

	//Attirbute
	private String servername, user, password, database;

	/**
	 * Ein Default-Konstruktor der die Paramter wie folgt setzt:
	 * 	server: localhost
	 * 	benutzer: root
	 * 	passwort: root
	 * 	datenbank: flugschule
	 */
	public DataSource() {
		this.setServername("localhost");
		this.setUser("root");
		this.setPassword("root");
		this.setDatabase("flugschule");
	}
	
	/**
	 * Ein Konstruktor der die Parameter, die für die Verbindung zu der Datenbank die von nöten sind, übernimmt.
	 * @param servername der Servername
	 * @param user ein Benutzer der auf die Datenbank zugreifen will
	 * @param password das Passwort
	 * @param database ein Datenbank die dann ausgwehält wird
	 */
	public DataSource(String servername, String user, String password, String database) {
		this.setServername(servername);
		this.setUser(user);
		this.setPassword(password);
		this.setDatabase(database);
	}
	
	/**
	 * Gibt der Servername zurück.
	 * @return der Servname
	 */
	public String getServername() {
		return servername;
	}

	/**
	 * Zum setzen des Servernamen.
	 * @param servername der neue Servername
	 */
	public void setServername(String servername) {
		this.servername = servername;
	}

	/**
	 * Gibt den Benutzer zurück der 
	 * @return den Benuter
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Zum setzen des Benutzers
	 * @param user der neue Benuter
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Gibt das Passwort zurück
	 * @return das Password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Zum setzen des Passwortes
	 * @param password das neue Passwort
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gibt die Datenbank zurück
	 * @return die Datenbank
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * Zum setzen einer Datenbank
	 * @param database die neue Datenbank
	 */
	public void setDatabase(String database) {
		this.database = database;
	} 
}
