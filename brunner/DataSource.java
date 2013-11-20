package brunner;
/**
 * Eine Klasse die die Eingabe Parameter verwaltet.
 * @author helmuthbrunner
 *
 */
public class DataSource {

	private String servername, user, password, database;

	public DataSource() {
		this.setServername("localhost");
		this.setUser("root");
		this.setPassword("root");
		this.setDatabase("flugschule");
	}
	
	public DataSource(String servername, String user, String password, String database) {
		this.setServername(servername);
		this.setUser(user);
		this.setPassword(password);
		this.setDatabase(database);
	}
	
	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	} 
}
