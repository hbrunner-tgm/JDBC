package brunner;

import javax.swing.JFrame;

/**
 * Eine Klasse die ein neues Frame erstellt.
 * @author helmuthbrunner
 *
 */
public class Frame extends JFrame {
	
	private GUI gui;
	
	/**
	 * Ein Konstruktor der die Einstellung fuer ein JFrame setzt.
	 * @param c ein Connect- Objekt der aufgebauten Verbindung
	 */
	public Frame(Connect c) {
		super("Datenbank");
		gui= new GUI(c);
		super.add(gui);
		super.setSize(380, 250);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		super.setLocationRelativeTo(null);
	}

}
