package brunner;

import org.apache.commons.cli.*;

/**
 * Eine Klasse in der die Argument verarbeitet werden.
 * 
 * Die Klasse verwendet die http://commons.apache.org/proper/commons-cli/ commandline tool
 * 
 * @author helmuthbrunner
 *
 */
public class CLIPraser {
	
	//Attribute
	private String[] args;
	private Options options;

	public CLIPraser(String[] args) {
		
		this.args=args;
		this.options= new Options();
		
		Option host = OptionBuilder.withArgName("Hostname")
                .hasArg()
                .withDescription("Hostname des Servers")
                .create("h");
		
		Option user = OptionBuilder.withArgName("User")
                .hasArg()
                .withDescription("Benutzer mit dem man sich auf der Datenbank anmelden möchte")
                .create("u");
		
		Option pw = OptionBuilder.withArgName("Passwort")
                .hasArg()
                .withDescription("Passwort")
                .create("p");
		
		Option db = OptionBuilder.withArgName("Datenbank")
                .hasArg()
                .withDescription("zur auswahl einer Datenbank")
                .create("d");
		
		this.options.addOption(host);
		this.options.addOption(user);
		this.options.addOption(pw);
		this.options.addOption(db);
	}
	
	
	
	/**
	 * Eine Methode die die Parameter Parst.
	 */
	public void parse() {
		
		GnuParser parser = new GnuParser();
		
        try
        {
        	CommandLine line= parser.parse(this.options, this.args);
        	if(line.hasOption("h")
        			&& line.hasOption("u")
                    && line.hasOption("p")
                    && line.hasOption("d") ) {
        		
        		String h= line.getOptionValue("h");
        		String u= line.getOptionValue("u");
        		String p= line.getOptionValue("p");
        		String d= line.getOptionValue("d");
        		
        		//Übergeben der Paramter an die DataSource-Klasse
        		DataSource source = new DataSource(h, u, p, d);
        		Connect c= new Connect(source); //Aufbauen einer neuen Verbindung
        		Frame f= new Frame(c); //Das Fenster erzeugen
        		
        	}else {
        		this.printHelp(); //Ausgabe der Hilfe
        	}
        	
        }catch(ParseException exc) {
        	this.printHelp(); //Ausgabe der Hilfe
        }
		
	}
	
	/**
	 * Die Hilfe-Methode.
	 * Ausgabe eine kurze Beschreibung wie man die Parameter korrekt übergibt.
	 */
	public void printHelp() {
		HelpFormatter hf = new HelpFormatter();
        hf.printHelp("JDBC", this.options);
	}
}
