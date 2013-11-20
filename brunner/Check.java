package brunner;

import java.util.*;

/**
 * Prueft ein SQL- Statment und schneidet die Felder heraus.
 * @author helmuthbrunner
 *
 */
public class Check {
	
	private List<String> l;

	/**
	 * Ein Default - Konstruktor
	 */
	public Check() {		
	}
	
	/**
	 * Checkt den SQL- Befehl das mit keine inserts oder anderen Befehle nur als select.
	 * @param query der SQL- Befehl
	 * @return ob es ein gueltiger SQL - Befehl ist.
	 */
	public boolean check(String query) {//TODO Macht noch nichts
		return true;
	}
	
	/**
	 * Gibt die Felder die in dem SQL-Satement verwendet werden zurueck.
	 * @param query der SQL-Befehl
	 * @return die Felder als Liste
	 */
	public List<String> felder(String query) {//TODO Funktioniert nicht richtig
		l= new ArrayList<String>();
		StringBuilder builder= new StringBuilder(query);
		
		for(int i=0; i<builder.length();i++) {
			l.add( builder.substring(builder.indexOf(" "), builder.indexOf(" ", i)) );
			System.out.println(builder.substring(builder.indexOf(" "), builder.indexOf(" ", i)));
		}
		
		return l;
	}
}
