package voice;

import java.util.ArrayList;

public class VarList {

	public static ArrayList<String> intVarList=new ArrayList<String>();
	public static ArrayList<String> history=new ArrayList<String>();
	public static ArrayList<String> bookmarks=new ArrayList<String>();
	public static ArrayList<String> floatVarList=new ArrayList<String>();
	public static ArrayList<String> doubleVarList=new ArrayList<String>();
	public static ArrayList<String> charVarList=new ArrayList<String>();
	public static ArrayList<String> stringVarList=new ArrayList<String>();
	public static String[] voiceListWeb={
		" <<COMMAND LIST>> ",
		"Search",
		"Show Bookmarks",
		"Show History",
		"Add to Side Pane",
		"Add to Bookmark",
		"Move to Main Pane",
		"Close"
	};
	public static String[] voiceList={
			//"create a ",
			"   CREATE COMMAND LIST  ",
			"Create a int <variableName>",
			"Create a String <variableName>",
			"Create a char <variableName>",
			"Create a float <variableName>",
			"Create a double <variableName>",
			"Create an int array <arrayName>",
			"Create an char array <arrayName>",
			"Create an float array <arrayName>",
			"Create an double array <arrayName>",
			"Create an char array <arrayName>",
			"Create an String array <arrayName>",
			//Find,Find next
			"find <name or word>",
			"find next <next occurence of previously searched word>",
			//"Insert a ",
			"   INSERT COMMAND LIST  ",
			"Insert an increasing for loop",
			"Insert a dcreasing for loop",
			"Insert a break",
			"Insert a continue",
			"Insert a static",
			"Insert a public",
			"Insert a private",
			"Insert a protected",
			"Insert a import",
			"Insert a final",
			"Insert a super",
			"Insert a this",
			"Insert a dot(.)",
			"Insert a Semi-colon(;)",
			"Insert a switch",
			"Insert a case",
			"Insert a do While Loop",
			"Insert a class Keyword",
			"Insert a interface Keyword",
			"Insert a implements",
			"Insert a extends",
			"Insert a equal Sign(=)",
			"Insert a equals Sign(==)",
			"Insert a plus Sign(+)",
			"Insert a minus Sign(-)",
			"Insert a multiplication Sign(*)",
			"Insert a division Sign(/)",
			"Insert a not Equals Sign(!=)",
			"Insert a greater than Sign(>)",
			"Insert a less than Sign(<)",
			"Insert a greater than and equal to Sign(>=)",
			"Insert a less than and equals to Sign(<)",
			"Insert a Square Bracket",
			"Insert a Curly Bracket",
			"Insert a Input Method",
			"Insert a read Line",
			"Insert a new Line(\n)",
			"Insert a Tab Space(\t)",
			//"Save as ",
			"   SAVE COMMAND LIST  ",
			"Save as Text File",
			"Save as Java File",
			"Save as C File",
			"Save as PHP File"};

	
}
