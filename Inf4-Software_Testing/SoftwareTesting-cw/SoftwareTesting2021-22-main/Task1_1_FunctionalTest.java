package st;

import static org.junit.Assert.*;

import org.junit.Test;

public class Task1_1_FunctionalTest {
    @Test
    // Specifications 1.2
    public void test001() {
        Parser parser = new Parser();
        assertFalse(parser.toString().isBlank());
        assertFalse(parser.toString().isEmpty());
    }
    @Test
    // Specification 1.3
    public void test002() {
        Parser parser = new Parser();
        Option option = new Option("option",Type.STRING);
        parser.addOption(option,"shortcut");
    }
    @Test
    // Specification 1.3
    public void test003() {
        Parser parser = new Parser();
        parser.addOption(new Option("option",Type.STRING),"shortcut");
    }
    @Test
    // Specification 1.3
    public void test004() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.BOOLEAN);
        Option option3 = new Option("option3",Type.INTEGER);
        Option option4 = new Option("option4",Type.CHARACTER);
        parser.addOption(option1,"shortcut");
        parser.addOption(option2,"shortcut");
        parser.addOption(option3,"shortcut");
        parser.addOption(option4,"shortcut");
        parser.getBoolean("shortcut");
        parser.getString("shortcut");
        parser.getInteger("shortcut");
        parser.getCharacter("shortcut");
    }
    @Test
    // Specification 1.3.1
    public void test005() {
        Parser parser = new Parser();
        Option option1 = new Option("option",Type.STRING);
        Option option2 = new Option("option",Type.BOOLEAN);
        parser.addOption(option1,"shortcut");
        parser.addOption(option2,"shortcut");
        assertTrue(parser.optionExists("option"));
    }
    @Test
    // Specification 1.3.2
    public void test006(){
    	Parser parser = new Parser();
    	try {
    		Option option1 = new Option("Option!",Type.STRING);
    		parser.addOption(option1,"hello");
    	}catch (Exception e) {
    		
    	}
        assertFalse(parser.shortcutExists("hello"));
    }
    @Test
    // Specification 1.3.3
    public void test007(){
        Option option1 = new Option("Option",Type.STRING);
        Parser parser = new Parser();
        parser.addOption(option1,"hello");
        assertFalse(parser.shortcutExists("hellO"));
    }
    @Test
    // Specification 1.3.4
    public void test008() {
        Parser parser = new Parser();
        Option option1 = new Option("option",Type.STRING);
        Option option2 = new Option("o",Type.STRING);
        option1.setValue("valueOfOptionOne");
        option2.setValue("valueOfOptionTwo");
        parser.addOption(option1, "o");
        parser.addOption(option2, "option");
        assertEquals(parser.getString("o"),"valueOfOptionTwo");
        assertEquals(parser.getString("option"),"valueOfOptionOne");
    }
    @Test
    // Specification 1.3.5
    public void test009() {
        Parser parser = new Parser();
        Option option1 = new Option("option",Type.BOOLEAN);
        Option option2 = new Option("o",Type.BOOLEAN);
        Option option3 = new Option("AMG63",Type.BOOLEAN);
        Option option4 = new Option("AP0",Type.BOOLEAN);
        Option option5 = new Option("GP100",Type.BOOLEAN);
        option1.setValue("valueOfOptionOne");
        option2.setValue("valueOfTwo!");
        option3.setValue("100");
        option4.setValue("-100");
        option5.setValue("AMG");
        parser.addOption(option1, "shortcut1");
        parser.addOption(option2, "shortcut2");
        parser.addOption(option3, "shortcut3");
        parser.addOption(option4, "shortcut4");
        parser.addOption(option5, "shortcut5");
        assertTrue(parser.getBoolean("option"));
        assertTrue(parser.getBoolean("o"));
        assertTrue(parser.getBoolean("AMG63"));
        assertTrue(parser.getBoolean("AP0"));
        assertTrue(parser.getBoolean("GP100"));
    }
    @Test
    // Specification 1.4
    public void test010() {
        Parser parser = new Parser();
        Option option1 = new Option("option",Type.STRING);
        Option option2 = new Option("o",Type.STRING);
        parser.addOption(option1);
        parser.addOption(option2, "");
        assertEquals(parser.getString("option"),"");
        assertEquals(parser.getString("o"),"");
    }
    @Test
    // Specification 1.5
    public void test011() {
        Parser parser = new Parser();
        Option option1 = new Option("option",Type.STRING);
        Option option2 = new Option("o",Type.STRING);
        parser.addOption(option1);
        parser.addOption(option2, "");
        assertEquals(parser.getString("option"),"");
        assertEquals(parser.getString("o"),"");
    }
    @Test
    // Specification 1.5.1, 1.5.2, 1.5.3
    public void test012() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.STRING);
        Option option3 = new Option("option3",Type.STRING);
        parser.addOption(option1,"shortcut1");
        parser.addOption(option2,"shortcut2");
        parser.addOption(option3,"shortcut3");
        parser.parse("--option1=100");
        parser.parse("-shortcut2=200");
        parser.parse("--option3 300");
        assertEquals(parser.getString("option1"),"100");
        assertEquals(parser.getString("option2"),"200");
        assertEquals(parser.getString("option3"),"300");
    }
    @Test
    // Specification 1.5.4
    public void test013() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.STRING);
        Option option3 = new Option("option3",Type.STRING);
        parser.addOption(option1,"shortcut1");
        parser.addOption(option2,"shortcut2");
        parser.addOption(option3,"shortcut3");
        parser.parse("--option1='student'");
        parser.parse("--option2=\"200student\"");
        parser.parse("--option3=300student");
        assertEquals(parser.getString("option1"),"student");
        assertEquals(parser.getString("option2"),"200student");
        assertEquals(parser.getString("option3"),"300student");
    }
    @Test
    // Specification 1.5.5
    public void test014() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        parser.addOption(option1,"shortcut1");
        parser.parse("--option1=\'value=\"student\"'");
//        System.out.println(parser.getString("option1"));
        assertEquals(parser.getString("option1"),"value=\"student\"");
    }
    @Test
    // Specification 1.5.6
    public void test015() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        parser.addOption(option1,"shortcut1");
        parser.parse("--option1=100student");
        option1.setValue("200student");
        parser.addOption(option1);
        parser.parse("-shortcut1=300student");
        assertEquals(parser.getString("option1"),"300student");
    }
    @Test
    // Specification 1.5.7
    public void test016() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        parser.addOption(option1,"shortcut1");
        assertEquals(parser.getString("option1"),"");
    }
    @Test
    // Specification 1.5.8
    public void test017() {
        Parser parser = new Parser();
        Option option1 = new Option("input",Type.STRING);
        Option option2 = new Option("output",Type.STRING);
        Option option3 = new Option("option",Type.STRING);
        parser.addOption(option1,"shortcut1");
        parser.addOption(option2,"shortcut2");
        parser.addOption(option3,"shortcut3");
        parser.parse("--input 1.txt --output=2.txt");
        parser.parse("-shortcut3");
        assertEquals(parser.getString("input"),"1.txt");
        assertEquals(parser.getString("output"),"2.txt");
        assertEquals(parser.getString("option"),"");
    }
    @Test
    // Specification 1.5.9
    public void test018() {
        Parser parser = new Parser();
        Option option1 = new Option("option",Type.CHARACTER);
        option1.setValue("150");
        parser.addOption(option1,"shortcut1");
        assertNotEquals(parser.getInteger("option"),"150");
    }
    @Test
    // Specification 1.5.10
    public void test019() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.STRING);
        Option option3 = new Option("option3",Type.STRING);
        Option option4 = new Option("option4",Type.STRING);
        Option option5 = new Option("option5",Type.STRING);
        option1.setValue("{D_QUOTE}");
        option2.setValue("{S_QUOTE}");
        option3.setValue("{SPACE}");
        option4.setValue("{DASH}");
        option5.setValue("{EQUALS}");
        parser.addOption(option1,"shortcut1");
        parser.addOption(option2,"shortcut2");
        parser.addOption(option3,"shortcut3");
        parser.addOption(option4,"shortcut4");
        parser.addOption(option5,"shortcut5");
        assertNotEquals(parser.getInteger("option1"),"{D_QUOTE}");
        assertNotEquals(parser.getInteger("option2"),"{S_QUOTE}");
        assertNotEquals(parser.getInteger("option3"),"{SPACE}");
        assertNotEquals(parser.getInteger("option4"),"{DASH}");
        assertNotEquals(parser.getInteger("option5"),"{EQUALS}");
    }
    @Test
    // Specification 1.5.11
    public void test020() {
        Option option1 = new Option("option1",Type.NOTYPE);
        assertEquals(option1.getType(),Type.NOTYPE);
    }    
    @Test
    // Specification 1.6.1
    public void test021() {
        Parser parser = new Parser();
        Option option1 = new Option("o",Type.STRING);
        Option option2 = new Option("option2",Type.STRING);
        option1.setValue("valueForOne");
        option2.setValue("valueForTwo");
        parser.addOption(option1, "option1");
        parser.addOption(option2,"o");
        assertEquals(parser.getString("o"),"valueForOne");
    }    
    @Test
    // Specification 1.6.2 for task 1 version
    public void test022() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.BOOLEAN);
        Option option3 = new Option("option3",Type.CHARACTER);
        Option option4 = new Option("option4",Type.INTEGER);
        parser.addOption(option1, "option1");
        parser.addOption(option2, "option2");
        parser.addOption(option3, "option3");
        parser.addOption(option4, "option4");
        char c = '\0';
        assertEquals(parser.getString("option1"),"");
        assertEquals(parser.getBoolean("option2"),false);
        assertEquals(parser.getCharacter("option3"),c);
        assertEquals(parser.getInteger("option4"),0);
    }      
    @Test
    // Specification 1.6.3
    public void test023() {
        Parser parser = new Parser();
        try {
            parser.addOption(new Option(null,null),"shortcut1");
            parser.addOption(new Option(null,null),"shortcut2");
            parser.addOption(new Option(null,null),"shortcut3");
        }catch(Exception e){
            assertFalse(parser.optionExists(""));
            assertFalse(parser.shortcutExists("shortcut2"));
            assertFalse(parser.optionOrShortcutExists("shortcut3"));
        }
    }    
    @Test
    // Specification 1.6.4
    public void test024() {
        Parser parser = new Parser();
        parser.addOption(new Option("optimise",Type.STRING), "shortcut");
        parser.parse("--optimise=good");
        assertEquals(parser.getString("optimise"),"good");
    }
    @Test
    // Specification 1.6.5
    public void test025() {
        Parser parser = new Parser();
        parser.addOption(new Option("optimise",Type.STRING), "O");
        parser.parse("-O=good");
        assertEquals(parser.getString("optimise"),"good");
    }
    @Test
    // Specification 1.6.6
    public void test026() {
        Parser parser = new Parser();
        parser.addOption(new Option("char",Type.CHARACTER), "c");
        parser.addOption(new Option("string",Type.STRING), "s");
        parser.parse("--string==999");
        parser.parse("-c=999");
        assertNotEquals(parser.getCharacter("char"),parser.getString("string"));
    }
    @Test
    // Specification 1.7
    public void test027() {
        Parser parser = new Parser();
        parser.addOption(new Option("option1",Type.STRING), "shortcut1");
        parser.addOption(new Option("option2",Type.STRING), "shortcut2");
        parser.addOption(new Option("option3",Type.STRING), "shortcut3");
        
        assertTrue(parser.optionExists("option1"));
        assertTrue(parser.shortcutExists("shortcut2"));
        assertTrue(parser.optionOrShortcutExists("option3"));
        assertTrue(parser.optionOrShortcutExists("shortcut3"));
    }
    @Test
    // Specification 1.8.1
    public void test028() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.STRING);
        parser.addOption(option1);
        parser.addOption(option2);
        parser.parse("--option1=old --option2=old");
        parser.setShortcut("option1", "shorcut1");
        parser.setShortcut("option2", "shorcut2");
        parser.replace("option1 option2", "old", "new");
        assertEquals(option1.getValue(),"new");
        assertEquals(option2.getValue(),"new");
    }
    @Test
    // Specification 1.8.2
    public void test029() {
        Parser parser = new Parser();
        Option option1 = new Option("option1",Type.STRING);
        Option option2 = new Option("option2",Type.STRING);
        parser.addOption(option1,"oldShortcut1");
        parser.addOption(option2,"oldShortcut2");
        parser.parse("--option1=old --option2=old");
        parser.setShortcut("option1", "newShortcut1");
        parser.setShortcut("option2", "newShortcut2");
        parser.replace("option1 option2", "old", "new");
        assertEquals(parser.getString("newShortcut1"),"new");
        assertEquals(parser.getString("newShortcut2"),"new");
    }
    
	@Test
	// [Bug #8 - Medium, 2PTS]
	public void Bug8() {
	    Option opt1 = new Option("option",Type.STRING);
	    Option opt2 = new Option("option",Type.CHARACTER);
	    Parser parser = new Parser();
	    parser.addOption(opt1, "one");
	    parser.addOption(opt2,"two");
	    assertTrue(parser.optionExists("option"));
	}
	@Test
	// [Bug #4 - Medium, 2PTS]
	public void Bug4() {
		Parser parser = new Parser();
	    try {
	    	Option opt1 = new Option("option 1 and check the border of the parser in key",Type.STRING);
	    	parser.addOption(opt1,"shortcut of the option one");
	    }catch (Exception e) {
	    	
	    }
	    assertFalse(parser.shortcutExists("shortcut of the option one"));
	}
	@Test
	// [Bug #17 - Hard, 3PTS]
	public void Bug17() {
		Parser parser = new Parser();
	    try {	    	
	    	Option option1 = new Option("Option bourdary check:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",Type.STRING);	    
	    	parser.addOption(option1);
	    }catch (Exception e) {
	    	
	    }
	    assertFalse(parser.optionExists("Option bourdary check:aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
	}
	@Test
	// [Bug #6 - Easy, 1PT]
	public void Bug6() {
	    Option option1 = new Option("Option 1",Type.STRING);
	    Option option2 = new Option("Option 2",Type.BOOLEAN);
	    assertNotEquals(option1,option2);
	}
	@Test
    // Specification 1.3
    public void Bug1() {
        Parser parser = new Parser();
        Option option = new Option("option",Type.STRING);
        parser.addOption(option);
        assertFalse(parser.shortcutExists(""));
    }
	
	@Test
	public void Bug16() {
	    OptionMap map = new OptionMap();
	    try {	    	
	    	map.getValue(null);
	    }catch (Exception e) {
	    	
	    }
	}
	@Test
	public void Bug11() {
	    Parser parser = new Parser();
	    Option option = new Option("abc@!#$%",Type.STRING);
	    try {
	    	parser.addOption(option,"shortcut");
		    parser.getString("abc@!#$%");
	    }catch (Exception e) {
	    	
	    }
	    assertFalse(parser.shortcutExists("shortcut"));
	}
	@Test
	public void Bug7() {
	    Parser parser = new Parser();
	    Option option = new Option("AMG",Type.STRING);
	    option.setValue("G65");
	    parser.addOption(option,"shortcut");
	    assertEquals(parser.getInteger("AMG"),0);
	}
	@Test
    public void Bug15() {
        Parser parser = new Parser();
        Option option = new Option("AMG",Type.STRING);
        try {
        	option.setValue("9999999999999999999999999999999");
            parser.addOption(option,"shortcut");
        }catch (Exception e) {
        	
        }
        assertNotEquals("9999999999999999999999999999999",parser.getInteger("AMG"));
    }
	@Test
    public void Bug3() {
        Parser parser = new Parser();
        Option option = new Option("Bimmer",Type.BOOLEAN);
        option.setValue("false111");
        parser.addOption(option,"shortcut");
        parser.getInteger("Bimmer");
//        System.out.println(parser.getInteger("Bimmer"));
    }
	@Test
    public void Bug5() {
        Parser parser = new Parser();
        Option option = new Option("Bimmer",Type.INTEGER);
        try {
            option.setValue("-1");
            parser.addOption(option,"shortcut");
            parser.getInteger("Bimmer");
        }catch(Exception e){
        }
	}
	@Test
    public void Bug2() {
        Parser parser = new Parser();
        Option option = new Option("Bimmer",Type.BOOLEAN);
        option.setValue("");
        parser.addOption(option,"shortcut");
        parser.getBoolean(option.getName());
	}
	@Test
    public void Bug20() {
        Parser parser = new Parser();
        Option option = new Option("student",Type.STRING);
        parser.addOption(option,"shortcut");
        parser.parse("--student=I am a good student");
//        System.out.println(parser.getString("student"));
	}
	@Test
    public void Bug14() {
	    Parser parser = new Parser();
	    Option option = new Option("student",Type.STRING);
	    parser.addOption(option,"shortcut");
	    parser.parse("--student=GoodStudent\\n");
	    parser.getString("student");
	}
	@Test
    public void Bug10() {
        Parser parser = new Parser();
        Option option = new Option("student",Type.STRING);
        option.setValue("");
        parser.addOption(option,"shortcut");
        parser.getCharacter("student");
	}
	@Test
    public void Bug18() {
        Parser parser = new Parser();
        Option option1 = new Option("Student",Type.STRING);
        Option option2 = new Option("Teacher",Type.STRING);
        parser.addOption(option1,"shortcutOne");
        parser.addOption(option2,"shortcutTwo");
        parser.parse("--Student=good  --Teacher=awesome");
        try {        	
        	parser.replace("Student   Teacher", "good", "bad");
        }catch (Exception e) {
        	
        }
	}
	@Test
	public void Bug12() {
	    Parser parser = new Parser();
        Option option = new Option("student",Type.STRING);
        option.setValue("good");
        parser.addOption(option,"shortcut");
        parser.replace("-shortcut", "good", "new");
	}
	@Test
    public void Bug9() {
        Parser parser = new Parser();
        parser.parse("   ");
	}
	@Test
    public void Bug13() {
        Parser parser = new Parser();
        Option option = new Option("number",Type.INTEGER);
        option.setValue("555");
        parser.addOption(option,"shortcut");
        parser.parse("--number='5=555'");
//        parser.getInteger("number");
    }
	@Test
    public void Bug19() {
        Parser parser = new Parser();
        Option option = new Option("number",Type.INTEGER);
        option.setValue("555");
        parser.addOption(option,"shortcut");
        parser.parse("--number=\"5-5\"");

    }
	
}
