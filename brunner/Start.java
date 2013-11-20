package brunner;

/**
 * Die Main-Methode die das Programm starten soll.
 * @author helmuthbrunner
 *
 */

public class Start {

	public static void main(String[] args) {
		
		CLIPraser clip= new CLIPraser(args);
		
		clip.parse();//Starte auch die GUI

	}

}
