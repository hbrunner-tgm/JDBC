package brunner;

import java.util.*;

/**
 * Prueft ein SQL- Statment und schneidet die Felder heraus.
 * @author helmuthbrunner
 *
 */
public class Check {
	
	private String[] ja= {"select", "show", "describe", "desc"}, 
					 nein= {"insert", "update", "delete", "alter"};

	private String errom;
	
	/**
	 * Ein Default - Konstruktor
	 */
	public Check() {
	}
	
	/**
	 * Checkt den SQL- Befehl das mit keine inserts oder andere Befehle eingegeben werden.
	 * @param query der SQL- Befehl
	 * @return ob es ein gueltiger SQL - Befehl ist.
	 */
	public boolean check(String query) {
		
		if(query.contains(";")==true) {
			this.errom="Bitte keinen Strichpunkt angeben!";
			return false;
		}
		
		for(String s: nein) {
			if(query.contains(s)) {
				this.errom= "Kein \""+s+"\" angeben!";
				return false;
			}
		}
		
		for(String s: ja) {
			if(query.contains(s)) {
				return true;
			}
		}
		return true;
		
	}
	
	/**
	 * Gibt die Felder die in dem SQL-Satement verwendet werden zurueck.
	 * @param query der SQL-Befehl
	 * @return die Felder als Liste
	 */
	public List<String> felder(String query) {//TODO Funktioniert nicht richtig
		return null;
	}
	
	/**
	 * Gibt den Fehler Text aus zu dem
	 * @return den Fehler Text des zugehörigen fehlers.
	 */
	public String errom() {
		return errom;
	}
}
