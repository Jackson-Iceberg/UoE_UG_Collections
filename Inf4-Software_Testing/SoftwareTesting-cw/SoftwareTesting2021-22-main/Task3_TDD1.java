package st;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


public class Task3_TDD1 {
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



	
}

