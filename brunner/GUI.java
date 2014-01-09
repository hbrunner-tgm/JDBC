package brunner;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Ein Klasse die ein Panel mit Komponenten erstellt.
 * @author helmuthbrunner
 */

public class GUI extends JPanel {

	private JTextField server, db, user, pw, query;
	private Connect c;
	private JButton button, reconnect;
	private JLabel lserver, ldb, luser, lpw, lquery;
	private ResultSet set;
	private JPanel panel;
	private Check check;
	private JTable table;
	private ResultSetMetaData data;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	/**
	 * Ein Konsturkor fuer die GUI
	 * @param c
	 */
	public GUI(Connect c) {
		super();		

		panel= new JPanel();
		panel.setLayout(new GridLayout(7,1));
		this.c=c;
		check= new Check();
		table= new JTable();

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

		//---- Befuellen der Textfelder ----
		server.setText(c.getSource().getServername());
		db.setText(c.getSource().getDatabase());
		user.setText(c.getSource().getUser());
		pw.setText(c.getSource().getPassword());


		//--- Lables ---
		lserver= new JLabel("Servername");
		ldb= new JLabel("Datenbank:");
		luser= new JLabel("Benutzer:");
		lpw= new JLabel("Passwort:");
		lquery= new JLabel("SQL - Befehl:");

		//--- JTable ---
		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane = new JScrollPane(table);
		
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
		panel.add(button);
		panel.add(reconnect);
				
		super.add(panel, BorderLayout.CENTER);
		super.add(scrollPane, BorderLayout.SOUTH);
		//------
	}

	/**
	 * Fuegt den Output des SQL-Statmenst in eine JTable.
	 */
	public void anzeigen() {

		try {

			data= set.getMetaData();	

			int columnCount = data.getColumnCount(); // Gibt die Felderanzahl zurück
			String[] column = new String[columnCount]; // Neues Array
			int rows = 0;

			while(set.next()) {
				rows++;//Anzahl der Reihen/Zeilen die vom dem select zurück gegeben werden.
			}

			for(int i=1;i<=columnCount;i++) {

				column[i-1] = data.getColumnName(i);
			}

			Object[][] erg = new Object[rows][columnCount];
			rows = 0;
			set.beforeFirst();

			while(set.next()) {

				for(int i = 1; i <= columnCount; i++) {

					erg[rows][i - 1] = set.getString(i);
				}
				rows++;
			}

			model = new DefaultTableModel(erg, column);
			table.setModel(model);

		}catch (SQLException e) {
			System.err.println("Fehler beim TextArea");
		}catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Fehler ArrayIndexOutOfBounds");
		}catch (NullPointerException e) {
			System.err.println("Fehler NullPointerException");
		}
	}

	/**
	 * Baut eine Verbindung mit den Parametern auf die in den den Text-Feldern stehen.
	 */
	public void reconnect() {
		DataSource ds= new DataSource();
		
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
				
				if(check.check(query.getText() )) 
					set= c.query(query.getText());
				else
					JOptionPane.showMessageDialog(null, ""+check.errom());

				anzeigen();
			}

			if(b.getText().equals("Connect")) {
				reconnect();
			}
		}
	}
}
