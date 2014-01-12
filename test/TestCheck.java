package test;

import static org.junit.Assert.*;

import org.junit.Test;

import brunner.Check;

public class TestCheck {

	private Check c= new Check();
	
	@Test
	public void testCheckGueltig() {
		assertEquals(true, c.check("select * from person"));
	}
	
	@Test
	public void testCheckUngueltig() {
		assertEquals(false, c.check("insert into person values (43,'Helmuth Brunner', '1995-10-27')"));
	}
	

}
