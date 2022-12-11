package st;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3_Parser {
	
	private OptionMap optionMap;
	
	public Task3_Parser() {
		optionMap = new OptionMap();
	}
	
	public void addOption(Option option, String shortcut) {
		optionMap.store(option, shortcut);
	}
	
	public void addOption(Option option) {
		optionMap.store(option, "");
	}
	
	public boolean optionExists(String key) {
		return optionMap.optionExists(key);
	}
	
	public boolean shortcutExists(String key) {
		return optionMap.shortcutExists(key);
	}
	
	public boolean optionOrShortcutExists(String key) {
		return optionMap.optionOrShortcutExists(key);
	}
	
	public int getInteger(String optionName) {
		String value = getString(optionName);
		Type type = getType(optionName);
		int result;
		switch (type) {
			case STRING:
			case INTEGER:
				try {
					result = Integer.parseInt(value);
				} catch (Exception e) {
			        try {
			            new BigInteger(value);
			        } catch (Exception e1) {
			        }
			        result = 0;
			    }
				break;
			case BOOLEAN:
				result = getBoolean(optionName) ? 1 : 0;
				break;
			case CHARACTER:
				result = (int) getCharacter(optionName);
				break;
			default:
				result = 0;
		}
		return result;
	}
	
	public boolean getBoolean(String optionName) {
		String value = getString(optionName);
		return !(value.toLowerCase().equals("false") || value.equals("0") || value.equals(""));
	}
	
	public String getString(String optionName) {
		return optionMap.getValue(optionName);
	}
	
	public char getCharacter(String optionName) {
		String value = getString(optionName);
		return value.equals("") ? '\0' :  value.charAt(0);
	}
	
	public void setShortcut(String optionName, String shortcutName) {
		optionMap.setShortcut(optionName, shortcutName);
	}
	
	public void replace(String variables, String pattern, String value) {
			
		String[] varsArray = variables.split(" ");
		
		for (int i = 0; i < varsArray.length; ++i) {
			String varName = varsArray[i];
			String var = (getString(varName));
			var = var.replace(pattern, value);
			if(varName.startsWith("--")) {
				String varNameNoDash = varName.substring(2);
				if (optionMap.optionExists(varNameNoDash)) {
					optionMap.setValueWithOptionName(varNameNoDash, var);
				}
			} else if (varName.startsWith("-")) {
				String varNameNoDash = varName.substring(1);
				if (optionMap.shortcutExists(varNameNoDash)) {
					optionMap.setValueWithOptionShortcut(varNameNoDash, var);
				} 
			} else {
				if (optionMap.optionExists(varName)) {
					optionMap.setValueWithOptionName(varName, var);
				}
				if (optionMap.shortcutExists(varName)) {
					optionMap.setValueWithOptionShortcut(varName, var);
				} 
			}

		}
	}
	
	private List<CustomPair> findMatches(String text, String regex) {
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(text);
	    // Check all occurrences
	    List<CustomPair> pairs = new ArrayList<CustomPair>();
	    while (matcher.find()) {
	    	CustomPair pair = new CustomPair(matcher.start(), matcher.end());
	    	pairs.add(pair);
	    }
	    return pairs;
	}
	
	
	public int parse(String commandLineOptions) {
		if (commandLineOptions == null) {
			return -1;
		}
		int length = commandLineOptions.length();
		if (length == 0) {
			return -2;
		}	
		
		List<CustomPair> singleQuotePairs = findMatches(commandLineOptions, "(?<=\')(.*?)(?=\')");
		List<CustomPair> doubleQuote = findMatches(commandLineOptions, "(?<=\")(.*?)(?=\")");
		List<CustomPair> assignPairs = findMatches(commandLineOptions, "(?<=\\=)(.*?)(?=[\\s]|$)");
		
		
		for (CustomPair pair : singleQuotePairs) {
			String cmd = commandLineOptions.substring(pair.getX(), pair.getY());
			cmd = cmd.replaceAll("\"", "{D_QUOTE}").
					  replaceAll(" ", "{SPACE}").
					  replaceAll("-", "{DASH}").
					  replaceAll("=", "{EQUALS}");
	    	
	    	commandLineOptions = commandLineOptions.replace(commandLineOptions.substring(pair.getX(),pair.getY()), cmd);
		}
		
		for (CustomPair pair : doubleQuote) {
			String cmd = commandLineOptions.substring(pair.getX(), pair.getY());
			cmd = cmd.replaceAll("\'", "{S_QUOTE}").
					  replaceAll(" ", "{SPACE}").
					  replaceAll("-", "{DASH}").
					  replaceAll("=", "{EQUALS}");
			
	    	commandLineOptions = commandLineOptions.replace(commandLineOptions.substring(pair.getX(),pair.getY()), cmd);	
		}
		
		for (CustomPair pair : assignPairs) {
			String cmd = commandLineOptions.substring(pair.getX(), pair.getY());
			cmd = cmd.replaceAll("\"", "{D_QUOTE}").
					  replaceAll("\'", "{S_QUOTE}").
					  replaceAll("-", "{DASH}");
	    	commandLineOptions = commandLineOptions.replace(commandLineOptions.substring(pair.getX(),pair.getY()), cmd);	
		}

		commandLineOptions = commandLineOptions.replaceAll("--", "-+").replaceAll("\\s+", " ");


		String[] elements = commandLineOptions.split("-");
		
		
		for (int i = 0; i < elements.length; ++i) {
			String entry = elements[i];
			
			if(entry.isBlank()) {
				continue;
			}

			String[] entrySplit = entry.split("[\\s=]", 2);
			
			boolean isKeyOption = entry.startsWith("+");
			String key = entrySplit[0];
			key = isKeyOption ? key.substring(1) : key;
			String value = "";
			
			if(entrySplit.length > 1 && !entrySplit[1].isBlank()) {
				String valueWithNoise = entrySplit[1].trim();
				value = valueWithNoise.split(" ")[0];
			}
			
			// Explicitly convert boolean.
			if (getType(key) == Type.BOOLEAN && (value.toLowerCase().equals("false") || value.equals("0"))) {
				value = "";
			}
			
			value = value.replace("{S_QUOTE}", "\'").
						  replace("{D_QUOTE}", "\"").
						  replace("{SPACE}", " ").
						  replace("{DASH}", "-").
						  replace("{EQUALS}", "=");
			
			
			boolean isUnescapedValueInQuotes = (value.startsWith("\'") && value.endsWith("\'")) ||
					(value.startsWith("\"") && value.endsWith("\""));
			
			value = value.length() > 1 && isUnescapedValueInQuotes ? value.substring(1, value.length() - 1) : value;
			
			if(isKeyOption) {
				optionMap.setValueWithOptionName(key, value);
			} else {
				optionMap.setValueWithOptionShortcut(key, value);
				
			}			
		}

		return 0;
		
	}

	
	private Type getType(String option) {
		Type type = optionMap.getType(option);
		return type;
	}
	
	@Override
	public String toString() {
		return optionMap.toString();
	}

	
	private class CustomPair {
		
		CustomPair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	    private int x;
	    private int y;
	    
	    public int getX() {
	    	return this.x;
	    }
	    
	    public int getY() {
	    	return this.y;
	    }
	}
	
	// for 3 TDD 1
//	public void addAll(String options,String types) {
//		
//	}
//	public void addAll(String options,String shortcuts,String types) {
//		
//	}
	
	public void testSpace() {
		System.out.println(new String(" hello     there   ").trim().replaceAll("\\s{2,}", " "));
	}
	
	// special for task3
	public OptionMap getOptionMap() {
		return optionMap;
	}
	public int indexOfOptionG(String name) {
	    for(int i = 0;i<name.length();i++) {
            if(name.charAt(i) == '-') {
                return i;
            }
        }
	    return -1;
	}
	public int indexOfShortcutG(String shortcut) {
        for(int i = 0;i<shortcut.length();i++) {
            if(shortcut.charAt(i) == '-') {
                return i;
            }
        }
        return -1;
    }
	public void newAddOption(String name,String shortcut,Type type) {
	    Option option = new Option(name,type);
	    optionMap.store(option,shortcut);
	}
	
	public Type getOptionType(String type) {
	    if(type.equals("String")) return Type.STRING;
	    if(type.equals("Boolean")) return Type.BOOLEAN;
	    if(type.equals("Character")) return Type.CHARACTER;
	    if(type.equals("Integer")) return Type.INTEGER;
	    else {
	        throw new Error("Wrong type of option");
	    }
	}
	public boolean checkRangeValid(String str,int start,int end) {
	    if (Character.isDigit(str.charAt(start)) && (Character.isDigit(str.charAt(end)))) {
	        return true;
	    }
	    if(Character.isLetter(str.charAt(start)) && (Character.isLetter(str.charAt(end)))) {
	        return true;
	    }
	    return false;
	}
	
	public boolean checkUpperLower(String str,int start,int end) {
	    if(Character.isUpperCase(str.charAt(start)) && (Character.isUpperCase(str.charAt(end)))) {
	        return true;
	    }
	    if(Character.isLowerCase(str.charAt(start)) && (Character.isLowerCase(str.charAt(end)))) {
            return true;
        }
	    return false;
	}
	public void addAll(String options,String types) {
		options = options.trim().replaceAll("\\s{2,}", " ");
		types = types.trim().replaceAll("\\s{2,}", " ");
		String[] option_list = options.split(" ");
		String[] type_list = types.split(" ");
		String name;
		String type;
		
		for(int i = 0;i<option_list.length;i++) {
			name = option_list[i];
			if(i>=type_list.length-1) {
				type = type_list[type_list.length-1];
			}else {
				type = type_list[i];
			}
					
			if(type.equals("String")) {
				Option option = new Option(name,Type.STRING);
				optionMap.store(option,"");
			}
			if(type.equals("Boolean")) {
				Option option = new Option(name,Type.BOOLEAN);
				optionMap.store(option,"");
			}
			if(type.equals("Character")) {
				Option option = new Option(name,Type.CHARACTER);
				optionMap.store(option,"");
			}
			if(type.equals("Integer")) {
				Option option = new Option(name,Type.INTEGER);
				optionMap.store(option,"");
			}
		}
	}

	public void addAll(String optionss,String shortcutss,String typess){
		// remove the extra space
	    String options = optionss.replaceAll("\\s{2,}", " ").trim();
	    String types = typess.replaceAll("\\s{2,}", " ").trim();
	    // check the shortcut exist or not
	    if(shortcutss.length() == 0) {	        
	        shortcutss = "";
	    }
	    String shortcuts = shortcutss.replaceAll("\\s{2,}", " ").trim();
	    
	    
	    String [] optionList = options.split(" ");
	    String [] shortcutList = shortcuts.split(" ");
	    String [] typeList = types.split(" ");
	    String name;
	    String shortcut;
	    String type;
	    ArrayList<String> buffer_optionList = new ArrayList<String>();
	    ArrayList<String> buffer_shortcutList = new ArrayList<String>();
	    
	    // normal option
	    for(int i = 0;i<optionList.length;i++) {
	        name = optionList[i];
	        // more option than shortcut
            if(i>=shortcutList.length) {
                shortcut = "";
            }
            else {
                shortcut = shortcutList[i];
            }
            // more option than type, then using the last one
            if(i>=typeList.length) {
                type = typeList[typeList.length-1];
            }
            else {
                type = typeList[i];
            }
            Type ty = getOptionType(type);
            
	        // A1 & B1 path
	        if(indexOfOptionG(name) == -1 && indexOfShortcutG(shortcut)==-1) { 
	            newAddOption(name,shortcut,ty);	            
	        }
	        // A1 & B2 shortcut range
	        if(indexOfOptionG(name) == -1 && indexOfShortcutG(shortcut)!=-1) {
	            int index = indexOfShortcutG(shortcut);
	            int start = index -1;
	            int end = index+1;
	            if(checkRangeValid(shortcut,start,end) != true) {
	                throw new Error("Wrong range of shortcut");	            
	            }
	            if(checkUpperLower(shortcut,start,end)) {
	                throw new Error("Wrong range of shortcut");
	            }
	            else {
	                // range begin with char
	                if(Character.isLetter(shortcut.charAt(start))) {
	                    char c_start = shortcut.charAt(start);
	                    char c_end = shortcut.charAt(end);
	                    int number = (int)(c_end - c_start);
	                    for(int ii = 0;ii<number;ii++) {
	                        char temp_c = (char) (c_start + ii);
	                        String str = name+ Character.toString(temp_c);
	                        buffer_shortcutList.add(str);
	                    }
	                }
	                if(Character.isDigit(shortcut.charAt(start))) {
	                    char c_start = shortcut.charAt(start);
//	                    int c_start=Integer.parseInt(String.valueOf(shortcut.charAt(start)));
                        char c_end = shortcut.charAt(end);
                        int number = (int)(c_end - c_start);
                        name = name.substring(0,name.length()-1);
                        for(int ii = 0;ii<number;ii++) {
                            char temp_c = (char)(c_start + ii);
                            String str = name+ Character.toString(temp_c);
                            buffer_shortcutList.add(str);
                        }
	                }
	                if(buffer_shortcutList.size()>0) {
	                    if(i<buffer_shortcutList.size()) {
	                        String sc = buffer_shortcutList.get(i);
	                        
	                        newAddOption(name,sc,ty);         
	                    }   
	                }
//	                else {
//	                    throw new Error("Wrong range of shortcut");
//	                } 
	            }
	        }
	        // A2 & B1 PATH OPTION range
	        if(indexOfOptionG(name) != -1 && indexOfShortcutG(shortcut)==-1) {
                
            }
	        // A2 & B2 Path option range && shortcut range
	        if(indexOfOptionG(name) != -1 && indexOfShortcutG(shortcut)!=-1) {
                
            }
	        
	    }
	    
	}
	


	
}
