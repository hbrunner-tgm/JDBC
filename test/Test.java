
package test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import brunner.Check;
import brunner.Connect;

/**
 * Eine Klasse die es nur gibt um schnell einpaar Funktion zu testen.
 * @author helmuthbrunner
 *
 */
public class Test {
	public static void main(String[] args) {
		
		Connect c= new Connect();
		
		ResultSet s= c.query("select * from person");
		
		try {
			for(int i=0;s.next();i++) {
				System.out.println(s.getString("name"));
				System.out.println(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		c.exit();
		
		Check check= new Check();
		check.check("select name from person");
		List<String> l= check.felder("select name from person");
		
		for(String str : l) {
			System.out.println(str);
		}
		
	}
}

