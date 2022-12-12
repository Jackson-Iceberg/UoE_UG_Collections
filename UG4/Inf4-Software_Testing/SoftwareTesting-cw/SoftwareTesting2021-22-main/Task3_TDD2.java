package st;

import static org.junit.Assert.*;

import org.junit.Test;

public class Task3_TDD2 {
	Parser parser = new Parser();
	// specification 1 and 2
	@Test
	public void Test001() {
		parser.addAll("option1 option2 option3 option4","o1 o2 o3 o4","String Integer Boolean Character");
		assertTrue(parser.shortcutExists("o1"));
		assertTrue(parser.shortcutExists("o2"));
		assertTrue(parser.shortcutExists("o3"));
		assertTrue(parser.shortcutExists("o4"));
		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		assertTrue(parser.optionExists("option3"));
		assertTrue(parser.optionExists("option4"));
		assertEquals(parser.getOptionMap().getType("option1"),Type.STRING);
		assertEquals(parser.getOptionMap().getType("option2"),Type.INTEGER);
		assertEquals(parser.getOptionMap().getType("option3"),Type.BOOLEAN);
		assertEquals(parser.getOptionMap().getType("option4"),Type.CHARACTER);
	}
	//specification 3
	@Test
	public void Test002() {
		parser.addAll("option1   option2","o1 o2","String Integer");
		assertTrue(parser.shortcutExists("o1"));
		assertTrue(parser.shortcutExists("o2"));

		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));

		assertTrue(parser.getOptionMap().optionExists("option1"));
		assertTrue(parser.getOptionMap().optionExists("option2"));

	}
	
	// specification 4
	@Test
	public void Test003() {
		parser.addAll("option1 option2","String Integer");
		assertFalse(parser.shortcutExists(""));
		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		assertTrue(parser.getOptionMap().optionExists("option1"));
		assertTrue(parser.getOptionMap().optionExists("option2"));

	}
	//specification 5 more options than shortcut
	@Test
	public void Test004() {
		parser.addAll("option1 option2 option3","o1 o2","String Integer String");
		assertTrue(parser.shortcutExists("o1"));
		assertTrue(parser.shortcutExists("o2"));
		assertFalse(parser.shortcutExists(""));

		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		assertTrue(parser.optionExists("option3"));

	}
	//specification 6 less option,more shortcut
	@Test 
	public void Test005() {
		parser.addAll("option1","o1 o2","String");
		assertTrue(parser.shortcutExists("o1"));
		assertFalse(parser.shortcutExists("o2"));
		assertTrue(parser.optionExists("option1"));
		assertEquals(parser.getOptionMap().getType("option1"),Type.STRING);
		assertTrue(parser.getOptionMap().optionExists("option1"));
	}

	//specification 6 less option,more type
	@Test
	public void Test006() {
		parser.addAll("option1","shortcut1","String Boolean");
		assertTrue(parser.shortcutExists("shortcut1"));
		assertEquals(parser.getOptionMap().getType("option1"),Type.STRING);
		assertNotEquals(parser.getOptionMap().getType("option1"),Type.BOOLEAN);
	}
	// specification 7 
	@Test
	public void Test007() {
		parser.addAll("option1 option2","o1 o2 o3","String");
		assertTrue(parser.shortcutExists("o1"));
		assertTrue(parser.shortcutExists("o2"));
		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		
		assertEquals(parser.getOptionMap().getType("option1"),Type.STRING);
		assertEquals(parser.getOptionMap().getType("option2"),Type.STRING);
	}
	// specification 1 Group initialisation
	@Test
	public void Test008() {
		try {			
			parser.addAll("option7-9 optiona-c optionA-B","o7-12","");
		}catch (Exception e){
		}
	}
	// specification 2 Group initialisation
	@Test
	public void Test009() {
		parser.addAll("option1-9","o1-10","String");
		assertTrue(parser.shortcutExists("o1"));
		assertTrue(parser.shortcutExists("o2"));
		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		
		assertEquals(parser.getOptionMap().getType("option1"),Type.STRING);
		assertEquals(parser.getOptionMap().getType("option2"),Type.STRING);
	}
	@Test
	public void Test010() { // 7.1
		Parser parser1 = new Parser();
		Parser parser2 = new Parser();
		parser1.addAll("option1 option2 option3","sc1 sc2 sc3","String Integer");
		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		assertTrue(parser.optionExists("option3"));
		parser2.addOption(new Option("option1",Type.STRING),"o1");
		parser2.addOption(new Option("option2",Type.INTEGER),"o2");
		parser2.addOption(new Option("option3",Type.INTEGER),"o3");
		assertEquals(parser1.toString(),parser2.toString());
	}
	@Test
	public void Test011() {
		parser.addAll("option1-3","sc1-o3","Integer String");
		assertTrue(parser.optionExists("option1"));
		assertTrue(parser.optionExists("option2"));
		assertTrue(parser.optionExists("option3"));
		assertTrue(parser.shortcutExists("sc1"));
		assertTrue(parser.shortcutExists("sc2"));
		assertTrue(parser.shortcutExists("sc3"));
		
	}
	@Test
	public void Test012() {
		parser.addAll("optionA-C","cA-M","Integer String");
		assertTrue(parser.optionExists("optionA"));
		assertTrue(parser.optionExists("optionB"));
		assertTrue(parser.optionExists("optionC"));
		assertTrue(parser.shortcutExists("cA"));
		assertTrue(parser.shortcutExists("cB"));
		assertTrue(parser.shortcutExists("cC"));
	}
	@Test
	public void Test013() {
		parser.addAll("option5-7","o1-3","String");
		parser.addAll("OPTION1-3","M1-3","Integer");
		parser.addAll("option_M-Z","mb11-19","String");
		parser.addAll("0a-c","Boolean");
		assertTrue(parser.optionExists("option6"));
		assertTrue(parser.optionExists("OPTION1"));
		assertTrue(parser.optionExists("option_M"));
		assertTrue(parser.shortcutExists("o2"));
		assertTrue(parser.shortcutExists("M2"));
		assertTrue(parser.shortcutExists("mb13"));
		
	}

	@Test
	public void Test014() {
		parser.addAll("opt8-2","o3-1","Boolean");
		assertFalse(parser.optionExists("opt8"));
		assertFalse(parser.optionExists("opt1"));
		assertFalse(parser.shortcutExists("o2"));
	}
	@Test
	public void Test015() {
		parser.addAll("optionA-Z option1-100","String");
		assertTrue(parser.optionExists("optionB"));
		assertTrue(parser.optionExists("optionM"));
		assertTrue(parser.optionExists("optionN"));
		assertTrue(parser.optionExists("option3"));
		assertTrue(parser.optionExists("option2"));
		assertTrue(parser.optionExists("option66"));
	}
	@Test // invalid option name or option shortcut
	public void Test016() {
		parser.addAll("aA-m OPT1-1b gM-2m","sc1-29@","String Integer Character");
		assertFalse(parser.optionExists("aA"));
		assertFalse(parser.optionExists("OPT1"));
		assertFalse(parser.optionExists("gM"));
		assertFalse(parser.shortcutExists("sc1"));
	}
	@Test // decreasing range
	public void Test017() {
		parser.addAll("m125-3","String");
		assertTrue(parser.optionExists("m124"));
		assertTrue(parser.optionExists("m123"));
		assertTrue(parser.optionExists("m125"));
		
	}
	
}
