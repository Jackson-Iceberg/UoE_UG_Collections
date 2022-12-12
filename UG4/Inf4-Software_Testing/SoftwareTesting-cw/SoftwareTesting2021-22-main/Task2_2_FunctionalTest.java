package st;

import static org.junit.Assert.*;

import org.junit.Test;

public class Task2_2_FunctionalTest {
	
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
      Option option1 = new Option("Option123",Type.STRING);
      Parser parser = new Parser();
      parser.addOption(option1,"hello");
      assertTrue(parser.shortcutExists("hello"));
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
//      System.out.println(parser.getString("option1"));
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
  // Specification 1.6.2 for task 2 version
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
//	check the OptionMap class (store method and isOptionValid method)
	public void test030() {
		// Initialise
		  OptionMap map = new OptionMap();
	      Option option_string = new Option("option1",Type.STRING);
	      Option option_integer = new Option("option2",Type.INTEGER);
	      Option option_boolean = new Option("option3",Type.BOOLEAN);
	      Option option_char = new Option("option4",Type.CHARACTER);
	      Option option_notype = new Option("option5",Type.NOTYPE);
	      Option option_null = new Option(null,null);
	      Option option_nullshortcut = new Option("option6",Type.STRING);
	      Option option_emptyName = new Option("",Type.STRING);
	      Option option_wrongName = new Option("wrong!",Type.STRING);
	      Option option_wrongshortCut = new Option("option7",Type.STRING);
	      
	      option_string.setValue("100");
	      option_integer.setValue("200");
	      option_boolean.setValue("300");
	      option_char.setValue("400");
	      
	      // Check store method
	      map.store(option_string,"shortcut1");
	      map.store(option_integer,"shortcut2");
	      map.store(option_boolean,"shortcut3");
	      map.store(option_char,"shortcut4");
	      try {
		      map.store(option_notype,"shortcut5");
	      }catch (Exception e) {
	      }
	      try {
		      map.store(option_null,"null");
	      }catch (Exception e) {
	      }
	      
	      map.store(option_string,"shortcut1");
	      map.store(option_string,"");
	      map.store(option_nullshortcut,"");
	      
	      
	   // Check isOptionValid method
	      try {
	    	  map.store(option_emptyName,"shortcut_emptyName");
	      }catch (Exception e) {
	      }
	      try {
	    	  map.store(option_wrongName,"shortcut_wrongName");
	      }catch (Exception e) {
	      }
	      try {
	    	  map.store(option_nullshortcut,null);
	      }catch (Exception e) {
	      }
	      try {
	    	  map.store(option_wrongshortCut,"shortcut!");
	      }catch (Exception e) {
	      }
	}
	@Test
	// Check the OptionMap class (getOption method, getShortcut method and getOptionByNameOrShortcut method) 
	public void test032() {
		// Initialise
		  OptionMap map = new OptionMap();
		  Option option_string = new Option("option1",Type.STRING);
		  option_string.setValue("100");
		  map.store(option_string,"shortcut1");
		  
		  // Check getOption method
		  map.getOption("option1");
		  try {
			  map.getOption("option_notExist");
	      }catch (Exception e) {
	      }
		  
		  // Check getShortcut method
		  map.getShortcut("shortcut1");
		  try {
			  map.getShortcut("shortcut_notExist");
	      }catch (Exception e) {
	      }
		  
		  // Check getOptionByNameOrShortcut method
		  map.getOptionByNameOrShortcut("option1");
		  map.getOptionByNameOrShortcut("shortcut1");
		  map.getOptionByNameOrShortcut("--option1");
		  map.getOptionByNameOrShortcut("-shortcut1");
		  try {
			  map.getOptionByNameOrShortcut("key_notExist");
	      }catch (Exception e) {
	      }
	}
	@Test
	// Check the OptionMap class (getValue method and getType method)
	public void test033() {
		// Initialise
		  OptionMap map = new OptionMap();
		  Option option_string = new Option("option1",Type.STRING);
		  option_string.setValue("100");
		  map.store(option_string,"shortcut1");
		// Check getValue method and getType method
		  map.getValue("option1"); // Check getValue method 		  
		  map.getType("option1");// Check getType method 
	}
	@Test
	// Check the OptionMap class (optionOrShortcutExists method because optionOrShortcutExists invoke optionExists method and shortcutExists method)
	public void test034() {
		// Initialise
		  OptionMap map = new OptionMap();
		  Option option_string = new Option("option1",Type.STRING);
		  option_string.setValue("100");
		  map.store(option_string,"shortcut1");
		  
		  // Check optionOrShortcutExists method and optionExists method	  
		  map.optionOrShortcutExists("option1"); 
		  // Check optionOrShortcutExists method and shortcutExists method	 
		  map.optionOrShortcutExists("shortcut1"); 
		// Check optionOrShortcutExists method when not exist
		  map.optionOrShortcutExists("notExist"); 
		  
	}
	@Test
	// Check the OptionMap class (setShortcut method, setValueWithOptionName and setValueWithOptionShortcut methods)
	public void test035() {
		// Initialise
		  OptionMap map = new OptionMap();
		  Option option_string = new Option("option1",Type.STRING);
		  map.store(option_string,"");
		  
		  // check setShortcut method
		  map.setShortcut("option1","new");
		  try {
			  map.setShortcut(null,"new");
	      }catch (Exception e) {
	      }
		  
		  // check setValueWithOptionName method
		  map.setValueWithOptionName("option1","new2");
		  try {
			  map.setValueWithOptionName(null,"new");
	      }catch (Exception e) {
	      }	
		  
		  // check setValueWithOptionShortcut method
		  map.setValueWithOptionShortcut("new","new3");
		  
		  Option option = new Option("option", Type.STRING); // Specification 1.5.2 // import null option success.
		  Parser parser = new Parser();
		  parser.addOption(option, "o");
		  assertFalse(parser.parse("-option=1.txt") != 0);
		  
	}
	@Test
	// Check the OptionMap class (toString method)
	public void test036() {
		// Initialise
		  OptionMap map = new OptionMap();
		  Option option_string = new Option("option1",Type.STRING);
		  map.store(option_string,"shortcut");
		  // check toString method
		  map.toString();
	}
	
	@Test
	// Check the Parser class (Check addOption method,optionExists method,shortcutExists method,optionOrShortcutExists method) + toString method
	public void test037() {
		// Initialise
		 Parser parser = new Parser();
		 Option option = new Option("option",Type.STRING);
		 // Check addOption method
		 parser.addOption(option,"shortcut");
		 parser.addOption(option);
		 // Check optionExists method
		 assertTrue(parser.optionExists("option"));
		 // Check shortcutExists method
		 assertTrue(parser.shortcutExists("shortcut"));
		// Check optionOrShortcutExists method
		 assertTrue(parser.optionOrShortcutExists("option"));
		 parser.toString();
	}
	@Test
	// Check the Parser class (Check getInteger method, getBoolean method,getString method,getCharacter)
	public void test038() {
		// Initialise
		 Parser parser = new Parser();
		 Option option1 = new Option("option1",Type.STRING);
		 Option option_max = new Option("option_max",Type.STRING);
		 Option option_negative = new Option("option_neg",Type.STRING);
		 Option option_str = new Option("option_str",Type.STRING);
		 Option option2 = new Option("option2",Type.BOOLEAN);
		 Option option2_pos = new Option("option2p",Type.BOOLEAN);
		 Option option2_neg = new Option("option2n",Type.BOOLEAN);
		 Option option3 = new Option("option3",Type.CHARACTER);
		 Option option_notype = new Option("notype",Type.NOTYPE);
		 
//		 Check getInteger method
		 option1.setValue("100");
		 option_max.setValue("99999999999999999999999999999999999999999");
		 option_negative.setValue("-1");
		 option_str.setValue("G63");
		 option2_pos.setValue("200");
		 option2_pos.setValue("-200");
		 option3.setValue("c");

//		 Check getInteger method
		 parser.addOption(option1,"shortcut1");
		 parser.addOption(option_max,"shortcutMax");
		 parser.addOption(option_negative,"shortcutNeg");
		 parser.addOption(option_str,"shortcutStr");
		 parser.addOption(option2,"shortcut2");
		 parser.addOption(option2_pos,"shortcut2");
		 parser.addOption(option2_neg,"shortcut2");
		 parser.addOption(option3,"shortcut3");

//		 Check getInteger method		 
		 assertEquals(100,parser.getInteger("option1"));
		 assertEquals(0,parser.getInteger("option_max"));
		 assertEquals(-1,parser.getInteger("option_neg"));
		 assertEquals(0,parser.getInteger("option_str")); 
		 assertEquals(1,parser.getInteger("option2p"));
		 assertEquals(0,parser.getInteger("option2n"));
		 assertEquals(99,parser.getInteger("option3"));
		 

		 //Check getBoolean method
		 option2_pos.setValue(null);
		 assertEquals(parser.getBoolean("option2"),false);
		 Option neg_1 = new Option("neg1",Type.BOOLEAN);
		 Option neg_2 = new Option("neg2",Type.BOOLEAN);
		 Option neg_3 = new Option("neg3",Type.BOOLEAN);
		 neg_1.setValue("FALSE");
		 neg_2.setValue("0");
		 neg_3.setValue("");
		 parser.addOption(neg_1,"shortcut_neg1");
		 parser.addOption(neg_2,"shortcut_neg2");
		 parser.addOption(neg_3,"shortcut_neg3");
		 assertEquals(false,parser.getBoolean("neg1")); 
		 assertEquals(false,parser.getBoolean("neg2"));
		 assertEquals(false,parser.getBoolean("neg3"));
		 
		 
		//Check getCharacter method
		 Option char1 = new Option("c1",Type.CHARACTER);
		 Option char2 = new Option("c2",Type.CHARACTER);
		 char1.setValue("");
		 char2.setValue("3");
		 parser.addOption(char1,"shortcut_char1");
		 parser.addOption(char2,"shortcut_char2");
		 assertEquals(0,parser.getCharacter("c1")); 
		 assertEquals(51,parser.getCharacter("c2"));
	}


	@Test
	// Check the Parser class (Check setShortcut method and replace method)
	public void test039() {
		// Initialise
		Parser parser = new Parser();
		Option option1 = new Option("option1",Type.STRING);
		Option option2 = new Option("option2",Type.STRING);
		Option option3 = new Option("option3",Type.STRING);
		parser.addOption(option1,"shortcut1");
		parser.addOption(option2,"shortcut2");
		parser.addOption(option3,"shortcut3");
		
		// Check setShortcut method 
		parser.setShortcut("option1","old");
		parser.setShortcut("option2","old");
		
		// Check replace method
		parser.replace("option1", "old", "new");
//		
		parser.replace("option1 option2","old","new");
		parser.replace("--option1","new","new1");
		parser.replace("-shortcut1","new1","new2");	
		
		// Check replace method
		parser.parse("--option1=3");
		parser.replace("--option1","sdjolfjrogi","nriujlfnlfew");
		
	}
	
	@Test
	// Check the Parser class (Check parse method)
	public void test040() {
		// Initialise
		 Parser parser = new Parser();
		 Option option = new Option("option",Type.STRING);
		 Option option2 = new Option("option2",Type.BOOLEAN);
		 parser.addOption(option,"shortcut1");
		 parser.addOption(option2,"shortcut2");
		 // Check parse method
		 parser.parse("");
		 parser.parse(null);
		 parser.parse("--option=100");
		 parser.parse("--option=");
		 parser.parse("--option");
		 parser.parse("--option=\"5-5\"");
		 parser.parse("--option=\"\5\"");
		 parser.parse("--option='\5'");
		 parser.parse("--option=\'5");
		 parser.parse("--option=5\'");
		 parser.parse("--option=\"5");
		 parser.parse("--option=5\"");
		 
		 
		 parser.parse("--option='value=\"student\"'");
		 parser.parse("--option='value=\"student\"'");
		 parser.parse("--option value=\100");
		 parser.parse("--option value='\100\'");
		 
		 parser.parse("--option2=false");
		 parser.parse("--option2=true");
		 parser.parse("--option2=0");
		 assertTrue(parser.getString("option")!="00");
		 
	}
	@Test
	// Check the Option class 
	public void test041() {
		// Initialise
		 Parser parser = new Parser();
		 Object ob = new Object();
		 Option op = null;
		 Option option1 = new Option("option1",Type.STRING);
		 Option option2 = new Option("option2",Type.CHARACTER);
		 Option option3 = new Option(null,Type.NOTYPE);
		 Option option4 = new Option("",Type.STRING);
		 Option option1_backup = option1;
		 Option option5 = new Option(null,Type.STRING);
		 Option option6 = new Option("option1",Type.STRING);
		 
		 
		 // Check equals method
		 assertFalse(option1.equals(option2));
		 assertTrue(option1.equals(option1));
		 assertTrue(option1.equals(option6));
		 assertFalse(option1.equals(op));
		 assertFalse(option3.equals(option1));
		 assertFalse(option2.equals(option3));
		 assertFalse(option3.equals(option2));
		 assertFalse(option1.equals(option3));
		 assertFalse(option1.equals(option4));
		 assertFalse(option5.equals(option3));
		 assertTrue(option1_backup.equals(option1));
		 
		 // fill some gap
		 Option option_null_1 = new Option(null,Type.STRING);
		 Option option_null_2 = new Option(null,Type.STRING);
		 Option option_str_1 = new Option("str",Type.STRING);
		 Option option_str_2 = new Option("str",Type.STRING);
		 Option option_str_3 = new Option("str3",Type.STRING);
		 Option option_str_4 = new Option("str4",Type.STRING);
		 try{
			 assertTrue(option_null_1.equals(option_null_2));	 
		 }catch (Exception e) {	 
		 }
		 
		 option1.setName("option1");
	}
	
	@Test
	// Check the Option class 
	public void test042() {
		Option option = new Option("option",Type.STRING);
		Parser parser = new Parser();
		parser.addOption(option,"shortcut");
		parser.parse("--option=text");
		parser.replace("option","text","new");
		parser.replace("shortcut","text","new2");	
		assertEquals(parser.getString("option"),"new");

}
	
}
