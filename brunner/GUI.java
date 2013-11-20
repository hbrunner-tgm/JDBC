package brunner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Ein Klasse die ein Panel mit Komponeten erstellt.
 * @author helmuthbrunner
 *
 */

public class GUI extends JPanel {
	
	public JTextField query;
	private JTextField server, db, user, pw;
	private Connect c;
	private JTextArea output;
	private JButton button, reconnect;
	private JLabel lserver, ldb, luser, lpw, lquery;
	private ResultSet set;
	private JPanel panel;
	
	/**
	 * Ein Konsturkor fuer die GUI
	 * @param c
	 */
	public GUI(Connect c) {
		super();		
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(7,1));
		this.c=c;
		output = new JTextArea();
		
		//----Button----
		button= new JButton("Abschicken");
		button.addActionListener(new ActionHandler());
		
		reconnect= new JButton("Connect");
		reconnect.addActionListener(new ActionHandler());
		
		
		super.setLayout(new BorderLayout());
		
		//----- Eingabefelder -----
		server= new JTextField();
		db= new JTextField();
		user= new JTextField();
		pw= new JTextField();
		query= new JTextField();
		
		server.setText(c.getSource().getServername());
		db.setText(c.getSource().getDatabase());
		user.setText(c.getSource().getUser());
		pw.setText(c.getSource().getPassword());
		
		
		//--- Lables ---
		lserver= new JLabel("Servername");
		ldb= new JLabel("Database:");
		luser= new JLabel("User:");
		lpw= new JLabel("Password:");
		lquery= new JLabel("Select:");
		
		//--- Text-Area ---
		output= new JTextArea();
		
		//--- Hinzufuegen der Komponenten ---
		panel.add(lserver);
		panel.add(server);
		panel.add(ldb);
		panel.add(db);
		panel.add(luser);
		panel.add(user);
		panel.add(lpw);
		panel.add(pw);
		panel.add(lquery);
		panel.add(query);
		panel.add(output);
		
		panel.add(button);
		panel.add(reconnect);
		
		super.add(panel);
		super.add(output, BorderLayout.SOUTH);
		//------
		
	}
	
	/**
	 * Fuegt den Output des SQL-Statmenst in ein JTextArea
	 */
	public void anzeigen() {
		output.setText("");
		try {
			while(set.next()) {
				//TODO Du weißt was.
				output.append(set.getString(set.findColumn("name") )+"\n");//Keine Dynamische Auswahl der Felder.
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Fehler beim TextArea");
		}
	}
	
	/**
	 * Baut eine Verbindung mit den Parametern auf die in den den Text-Feldern stehen.
	 */
	public void reconnect() {
		DataSource ds= new DataSource();
		// private JTextField server, db, user, pw;
	
		ds.setServername(server.getText());
		ds.setDatabase(db.getText());
		ds.setUser(db.getText());
		ds.setPassword(pw.getText());
		
		this.c= new Connect();
	}
	
	/**
	 * Eine Klasse die sich um die Listerner kuemmert.
	 */
	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b= (JButton) e.getSource();
			
			if(b.getText().equals("Abschicken")) {
				set= c.query(query.getText());
				anzeigen();
			}
			
			if(b.getText().equals("Connect")) {
				reconnect();
			}
			
		}
		
	}
	
}
