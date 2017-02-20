package voice;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import voice.VarList;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Editor {

	private Display display;
	private static ArrayList<String> words;
	private static TextPanel area;
	private static JLabel jLabel;
	private static JList<String> jList;
	JPanel paneM;
	private static int checkCount=0;
	private static int readLineCount=0;
	private static int lastIndex=0;
	private int listStatus=0;
	
	
	public Editor() {
		
		start();
		VarList.intVarList.add("    int Variables");
		VarList.charVarList.add("   char Variables");
		VarList.floatVarList.add("    float Variables");
		VarList.doubleVarList.add("    double Variables");
		VarList.stringVarList.add("    String Variables");
	}

	public void start(){
		
		display=new Display();
		display.initializeDisplay();
		getSwingObjects();
	}

	private void getSwingObjects() {
		area=display.getArea();
		jLabel=display.getLabel();
		jList=display.getList();
		jList.requestFocus();
		paneM=display.getPaneM();
		area.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getModifiers() == InputEvent.CTRL_MASK){
						words=V2w.startLiveRecognition();
					
					if(words==null){
						try {
							throw new WordNotFoundException();
						} catch (WordNotFoundException e1) {
							System.out.println(e1.toString());
						}
					}
					checkTask();
					
				 }
				 if(e.getModifiers()==InputEvent.ALT_MASK)
					 
					 if(listStatus==0)
					 {
						display.setList();
						 listStatus++;
					 } 
					 else if(listStatus==1)
					 {
						 
						display.setListI();
						 
						 listStatus++;
					 }
					 else if(listStatus==2)
					 {
						 display.setListF();
						 listStatus++;
					 }
					 else if(listStatus==3)
					 {
						 display.setListD();
						 listStatus++;
					 }
					 else if(listStatus==4)
					 {
						 display.setListC();
						 listStatus++;
					 }
					 else if(listStatus==5)
					 {
						 display.setListS();
						 listStatus++;
					 }
				 listStatus=listStatus%6;
			}
		});
		
		
	}
	static void checkList() {
		int mStatus=jList.getSelectedIndex();
		//area.append(""+mStatus);
		switch(mStatus){
		case 16:words=V2w.startRecognition("dcr_for_loop");
		        break;
		case 15:words=V2w.startRecognition("inc_for_loop");
        break;        
		case 17:words=V2w.startRecognition("insert_break");
        break;  
		case 30:words=V2w.startRecognition("insert_case");
        break;
		case 48:words=V2w.startRecognition("insert_curly");
        break;
		case 27:words=V2w.startRecognition("insert_dot");
        break;
		case 28:words=V2w.startRecognition("insert_semicolon");
        break;
		case 52:words=V2w.startRecognition("insert_space");
        break;
		case 29:words=V2w.startRecognition("insert_switch");
        break;
		case 55:words=V2w.startRecognition("save_java");
        break;
		case 56:words=V2w.startRecognition("save_c");
        break;
        default:words.clear();words.add(" Sorry ");
        	area.appendSimple(" No Voice Available in System");
        
		}
		checkTask();
	}

    private static void checkTask(){
    	
    	//new file
    	 if((words.contains("new")&&words.contains("file"))){
			jLabel.setText("new file");
			words=V2w.startRecognition("save_as_text");
			
			if(words==null){
				try {
					throw new WordNotFoundException();
				} catch (WordNotFoundException e1) {
					System.out.println(e1.toString());
				}
			}
			checkTask();
			area.setCaretPosition(0);
			area.select(0,area.getDocument().getLength());
			area.replaceSelection("");
			
		}
    	//for loops
    	 else if((words.contains("insert")&&words.contains("increasing"))&&words.contains("for")&&words.contains("loop")){
			jLabel.setText("increasing for loop");
			area.append("for ");
			area.appendSimple("(");
			area.append("int ");
			area.appendSimple("i;i<var;i++)\n{\n\n}\n");
			int len = area.getDocument().getLength();
			len=len-2;
	        area.setCaretPosition(len);
			/*if(!VarList.intVarList.isEmpty())
			     jList.setListData((String[])VarList.intVarList.toArray());*/
			
		}
    	else if((words.contains("insert")&&words.contains("decreasing"))&&words.contains("for")&&words.contains("loop")){
			jLabel.setText("decreasing for loop");
			area.append("for ");
			area.appendSimple("(");
			area.append("int ");
			area.appendSimple("i;i>var;i--)\n{\n\n}\n");
			int len = area.getDocument().getLength();
			len=len-2;
			/*if(!VarList.intVarList.isEmpty())
				jList.setListData((String[])VarList.intVarList.toArray());*/
			
		}
    	
    	//break,continue,static,public,protected,private
    	else if((words.contains("insert")&&words.contains("break"))){
			jLabel.setText("break");
			area.append("break;\t");
			
		}
    	else if((words.contains("insert")&&words.contains("continue"))){
			jLabel.setText("continue");
			area.append("continue;\t");
			
		}
    	else if((words.contains("insert")&&words.contains("static"))){
			jLabel.setText("static");
			area.append("static\t");
			
		}
    	else if((words.contains("insert")&&words.contains("public"))){
			jLabel.setText("public");
			area.append("public\t");
			
		}
    	else if((words.contains("insert")&&words.contains("private"))){
			jLabel.setText("private");
			area.append("private\t");
			
		}
    	else if((words.contains("insert")&&words.contains("protected"))){
			jLabel.setText("protected");
			area.append("protected\t");
			
		}
    	//import final this super
    	else if((words.contains("insert")&&words.contains("import"))){
			jLabel.setText("import");
			area.append("import\t");
			
		} 
    	else if((words.contains("insert")&&words.contains("final"))){
			jLabel.setText("final");
			area.append("final\t");
			
		}
    	else if((words.contains("insert")&&words.contains("super"))){
			jLabel.setText("super");
			area.append("super\t");
			
		}
    	else if((words.contains("insert")&&words.contains("this"))){
			jLabel.setText("this");
			area.append("this\t");
			
		}
    	//dot semi-colon comma
    	else if((words.contains("insert")&&(words.contains("dot")||words.contains("god")))){
			jLabel.setText("dot");
			area.appendSimple(".");
			
		}
    	else if((words.contains("insert")&&words.contains("semi")&&words.contains("colon"))){
			jLabel.setText("semi-colon");
			area.appendSimple(";\t");
			
		}
    	else if((words.contains("insert")&&words.contains("comma"))){
			jLabel.setText("comma");
			area.appendSimple(",");
			
		}
    	//switch
    	else if((words.contains("insert")&&words.contains("switch"))){
			jLabel.setText("switch");
			area.append("switch");
			area.appendSimple("{\n}");
			
		}
    	
    	// add cases
    	else if((words.contains("insert")&&words.contains("case"))){
			jLabel.setText("Adding a case");
			area.append("case ");
			area.appendSimple(checkCount+": ");
			checkCount++;
		}
    	//try and catch
    	else if((words.contains("insert")&&words.contains("try"))){
			jLabel.setText("try catch");
			area.append("try");
			area.appendSimple("{\n\n}\n ");
			area.append("catch");
			area.appendSimple("(Exception e)\n{\n\n}");
			int len=area.getDocument().getLength();
			len=len-2;
			area.setCaretPosition(len);
			
		}
    	//do while
    	else if((words.contains("insert")&&words.contains("do")&&words.contains("while"))){
			jLabel.setText("do While");
			area.append("do");
			area.appendSimple("{\n\n}\n");
			area.append("while");
			area.appendSimple("{\n\n}");
			int len=area.getDocument().getLength();
			len=len-2;
			area.setCaretPosition(len);
			
		}
    	//class interface implements
    	else if((words.contains("insert")&&words.contains("class"))){
			jLabel.setText("class");
			area.append("class\t");
			
		}
    	else if((words.contains("insert")&&words.contains("interface"))){
			jLabel.setText("interface");
			area.append("interface\t");
			
		}
    	else if((words.contains("insert")&&words.contains("implements"))){
			jLabel.setText("implements");
			area.append("implements\t");
			
		}
    	else if((words.contains("insert")&&words.contains("extends"))){
			jLabel.setText("extends");
			area.append("extends\t");
			
		}
    	//equal operators:add or not-equal sub mul div modulo greater-than less-than greater-than-equal less-than-equal
    	else if(words.contains("insert")&&(words.contains("equals"))){
			jLabel.setText("equals sign");
			area.appendSimple("==\t");
    	}
    	else if(words.contains("insert")&&(words.contains("equal"))){
			jLabel.setText("equal sign");
			area.appendSimple("=\t");
    	}
    	else if(words.contains("insert")&&(words.contains("add")||words.contains("plus"))){
			jLabel.setText("Addition sign");
			area.appendSimple("+\t");
    	}
    	else if(words.contains("insert")&&(words.contains("substract")||words.contains("minus"))){
			jLabel.setText("Substraction sign");
			area.appendSimple("-\t");
    	}
    	else if(words.contains("insert")&&(words.contains("multiply")||words.contains("into"))){
			jLabel.setText("Multiplication sign");
			area.appendSimple("*\t");
    	}
    	else if(words.contains("insert")&&(words.contains("divide")||words.contains("division"))){
			jLabel.setText("Divide sign");
			area.appendSimple("/\t");
    	}
    	else if(words.contains("insert")&&(words.contains("not equals")||words.contains("not equal"))){
			jLabel.setText("Not Equal sign");
			area.appendSimple("!=\t");
    	}
    	else if(words.contains("insert")&&(words.contains("greater than"))){
			jLabel.setText("Greater than sign");
			area.appendSimple(">\t");
    	}
    	else if(words.contains("insert")&&(words.contains("greater than")&&words.contains("equal"))&&(words.contains("to")||words.contains("too")||words.contains("two"))){
			jLabel.setText("Greater than or equals sign");
			area.appendSimple(">=\t");
    	}
    	else if(words.contains("insert")&&(words.contains("less than")&&words.contains("equal"))&&(words.contains("to")||words.contains("too")||words.contains("two"))){
			jLabel.setText("Less than or equals sign");
			area.appendSimple("<=\t");
    	}
    	else if(words.contains("insert")&&(words.contains("Less than"))){
			jLabel.setText("Less than sign");
			area.appendSimple("<\t");
    	}
    	//variables
    	  // int 
    	else if(words.contains("create")&&(words.contains("integer")&&words.contains("variable"))){
			jLabel.setText("int variable");
			try
			{
				if(words.get(3)!=null){
					area.append("int ");
				    area.appendSimple(words.get(3)+";\n");
				    VarList.intVarList.add(words.get(3));
				}    
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	//double
    	else if(words.contains("create")&&(words.contains("double")&&words.contains("variable"))){
			jLabel.setText("double variable");
			try
			{
				if(words.get(3)!=null){
					area.append("double ");
			        area.appendSimple(words.get(3)+";\n");
			        VarList.doubleVarList.add(words.get(3));
				}    
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	//float
    	else if(words.contains("create")&&(words.contains("float")&&words.contains("variable"))){
			jLabel.setText("float variable");
			try
			{
				if(words.get(3)!=null){
					area.append("float ");
			        area.appendSimple(words.get(3)+";\n");
			        VarList.floatVarList.add(words.get(3));
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	//String
    	else if(words.contains("create")&&(words.contains("string")&&words.contains("variable"))){
			jLabel.setText("String variable");
			try
			{
				if(words.get(3)!=null){
					area.append("String ");
			        area.appendSimple(words.get(3)+";\n");
			        VarList.stringVarList.add(words.get(3));
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	//char
    	else if(words.contains("create")&&(words.contains("char")&&words.contains("variable"))){
			jLabel.setText("char variable");
			try
			{
				if(words.get(3)!=null){
					area.append("char ");
			        area.appendSimple(words.get(3)+";\n");
			        VarList.charVarList.add(words.get(3));
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	//char array
    	else if(words.contains("create")&&(words.contains("char")&&words.contains("array"))){
			jLabel.setText("char array");
			try
			{
				if(words.get(3)!=null){
					area.append("char[] ");
			        area.appendSimple(words.get(3)+"= ");
			        area.append("new char[]");
			        area.appendSimple(";\n");
			        VarList.charVarList.add(words.get(3)+"[]");
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	// int array
    	else if(words.contains("create")&&(words.contains("int")&&words.contains("array"))){
			jLabel.setText("int array");
			try
			{
				if(words.get(3)!=null){
					area.append("int[] ");
			        area.appendSimple(words.get(3)+"= ");
			        area.append("new int[]");
			        area.appendSimple(";\n");
			        VarList.intVarList.add(words.get(3)+"[]");
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	// float array
    	else if(words.contains("create")&&(words.contains("float")&&words.contains("array"))){
			jLabel.setText("float array");
			try
			{
				if(words.get(3)!=null){
					area.append("float[] ");
			        area.appendSimple(words.get(3)+"= ");
			        area.append("new float[]");
			        area.appendSimple(";\n");
			        VarList.floatVarList.add(words.get(3)+"[]");
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	// double array
    	else if(words.contains("create")&&(words.contains("double")&&words.contains("array"))){
			jLabel.setText("double array");
			try
			{
				if(words.get(3)!=null){
					area.append("double[] ");
			        area.appendSimple(words.get(3)+"= ");
			        area.append("new double[]");
			        area.appendSimple(";\n");
			        VarList.doubleVarList.add(words.get(3)+"[]");
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	// String array
    	else if(words.contains("create")&&(words.contains("string")&&words.contains("array"))){
			jLabel.setText("String array");
			try
			{
				if(words.get(3)!=null){
					area.append("String[] ");
			        area.appendSimple(words.get(3)+"= ");
			        area.append("new String[]");
			        area.appendSimple(";\n");
			        VarList.stringVarList.add(words.get(3)+"[]");
				}
			}
			catch(Exception e)
			{
				jLabel.setText("invalid voice input");
			}
    	}
    	//brackets
    	else if(words.contains("insert")&&(words.contains("square")&&words.contains("bracket"))){
			jLabel.setText("Square Bracket");
			area.appendSimple("[\t]\t");
    	}
    	else if(words.contains("insert")&&(words.contains("curly")&&words.contains("bracket"))){
			jLabel.setText("Curly Bracket");
			area.appendSimple("{\t}\t");
    	}
    	//method
    	 //public static void main
    	else if(words.contains("insert")&&(words.contains("main")&&words.contains("method"))){
			jLabel.setText("Main Method");
			area.append("public static void ");
			area.appendSimple("main(");
			area.append("String");
			area.appendSimple(" args[])\n{\n\n}");
			int len = area.getDocument().getLength();
			len=len-2;
	        area.setCaretPosition(len);
    	}
    	//save
    	else if(words.contains("save")&&(words.contains("text")||words.contains("test"))){
			jLabel.setText("Saving...");
			String text=area.getText();
			try {
				PrintWriter out = new PrintWriter(words.get(2)+".txt");
				out.write(text);
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	else if(words.contains("save")&&(words.contains("php")||words.contains("p"))){
			jLabel.setText("Saving...");
			String text=area.getText();
			try {
				PrintWriter out = new PrintWriter(words.get(2)+".php");
				out.write(text);
				out.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    	}
    	else if(words.contains("save")&&(words.contains("java")||words.contains("test"))){
			jLabel.setText("Saving...");
			String text=area.getText();
			try {
				PrintWriter out = new PrintWriter(words.get(2)+".java");
				out.write(text);
				out.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    	}
    	else if(words.contains("save")&&(words.contains("c")||words.contains("see"))){
			jLabel.setText("Saving...");
			String text=area.getText();
			try {
				PrintWriter out = new PrintWriter(words.get(2)+".c");
				out.write(text);
				out.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    	}
    	else if(words.contains("save")&&(words.contains("html")||words.contains("h"))){
			jLabel.setText("Saving...");
			String text=area.getText();
			try {
				PrintWriter out = new PrintWriter(words.get(2)+".html");
				out.write(text);
				out.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    	}
    	else if(words.contains("save")&&(words.contains("xml")||words.contains("x"))){
			jLabel.setText("Saving...");
			String text=area.getText();
			try {
				PrintWriter out = new PrintWriter(words.get(2)+".xml");
				out.write(text);
				out.close();
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
    	}
    	//Input
    	else if(words.contains("insert")&&(words.contains("input")&&words.contains("method"))){
			jLabel.setText("User Input");
			area.appendSimple("BufferedReader br=");
			area.append("new");
			area.appendSimple(" BufferedReader(");
			area.append("new");
			area.appendSimple(" InputStreamReader(System.in));");
    	}
    	//read a new line
    	else if(words.contains("read")&&(words.contains("new")&&words.contains("line"))){
			jLabel.setText("Reading new Line...");
			area.append("String");area.
			appendSimple(" var"+readLineCount+"=br.readLine();");
			readLineCount++;
    	}
    	//ctrl+f feature
    	else if(words.contains("find")){
    		try
    		{
			jLabel.setText("Find "+words.get(1));
			String text=area.getText();
			int index=text.indexOf(words.get(1),0);
			area.setCaretPosition(index);
			lastIndex=index+words.get(1).length();
			//TODO: find feature
    		}
    		catch(Exception e)
    		{
    			jLabel.setText("Error with voice input");
    		}
    	}
    	else if(words.contains("find")&&(words.contains("next"))){
    		try
    		{
			jLabel.setText("Find "+words.get(2));
			String text=area.getText();
			int index=text.indexOf(words.get(2),lastIndex);
			area.setCaretPosition(index);
			
    		}
    		catch(Exception e)
    		{
    			jLabel.setText("Error with voice input");
    		}
			
    	}
    	//new line & tab space
    	else if(words.contains("insert")&&(words.contains("line"))){
			jLabel.setText("Inserting new Line...");
			area.appendSimple("\n");
    	}
    	else if(words.contains("insert")&&(words.contains("space"))){
			jLabel.setText("Inserting tab space...");
			area.appendSimple("\t");
    	}
    	//
    	else{
    		try
    		{
    		jLabel.setText("Inserting User Text");
    		//String array[]=(String[])words.toArray();
			for(int text=0;text<words.size();text++)
    		area.appendSimple(words.get(text)+"\t");
    		}
    		catch(Exception e){
    			jLabel.setText("Voice Error!!!"+e.getMessage());
    		}
    	}
    	//end of checkTask() method
    	 
    }

	
}
