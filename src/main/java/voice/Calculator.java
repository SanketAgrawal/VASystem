package voice;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class Calculator implements KeyListener {

	private static double height,width;
	private static double resH,resW,voiceAreaH,voiceAreaW,statusH,statusW,inputH,inputW,listH,listW,resultH,resultW,cli_dimx,cli_dimy,button_x,button_y,button_coox,button_cooy;
	private static double resx,resy,voiceAreax,voiceAreay,statusx,statusy,inputx,inputy,listx,listy,resultx,resulty,cli_coorx,cli_coory;
	static JPanel panel;
	static JLabel l1;
	static JLabel l2;
	static JLabel l3;
	static int result;
    static String hypothesis;
	 static String selected;
	static int choice;	
	static JFrame frame;
	 static MouseListener mouseListener,mouseListener_1;
	 static JList<String>  list;
	 static String join="",path_add;
	 static int choose=0;
	static int a,b,c,f;
	static JButton button;
	static LiveSpeechRecognizer LiveRecogniser;
	static Configuration configuration;
	public void start()  throws Exception
	{

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		height = dimension.height;
		  width = dimension.width;

		initializeDimens(height, width);
		initializeCoodinates(height, width);
					
		initializeWindowView();
		 clicked_Mode();
		
				 
	}


	public static void initializeWindowView()
	{
		
		 frame = new JFrame("VASystem");
		frame.setBounds(0, 0, (int) width, (int) height);

		// voice
		JPanel frame2 = new JPanel();
		frame2.setBounds((int) voiceAreax, (int) voiceAreay, (int) voiceAreaW, (int) voiceAreaH);
		frame2.setBackground(Color.CYAN);
		frame2.setVisible(true);

		// status
		JPanel jpanel1 = new JPanel();
		jpanel1.setBounds((int) statusx, (int) statusy, (int) statusW, (int) statusH);
		jpanel1.setBackground(Color.red);
		jpanel1.setVisible(true);

		// input words display
		l1 = new JLabel("Test");
		l1.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		l1.setBounds((int) inputx, (int) inputy, (int) inputW, (int) inputH);

		l1.setText("Speak the Voice Commands where a and b are the values of your choice.........");

		Border border = BorderFactory.createLineBorder(Color.black);
		l1.setBorder(border);
		l1.setVisible(true);

		// input operation box

		l2 = new JLabel("Test");
		l2.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		l2.setBounds((int) resultx, (int) resulty, (int) resultW, (int) resultH);

		l2.setText("Enter digit");

		Border border1 = BorderFactory.createLineBorder(Color.black);
		l2.setBorder(border1);
		l2.setVisible(true);

		// result of operation
		l3 = new JLabel("Test");
		l3.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		l3.setBounds((int) resx, (int) resy, (int) resW, (int) resH);

		l3.setText("Your Result is Here.........");

		Border border3 = BorderFactory.createLineBorder(Color.black);
		l3.setBorder(border3);
		l3.setVisible(true);
		//button
		JPanel panel=new JPanel();
		panel.setBounds((int) button_coox, (int) button_cooy, (int) button_x, (int) button_y);
		panel.setBackground(Color.CYAN);
		panel.setVisible(true);
		button = new JButton("Button");
	      panel.setLayout(null);
	      button.setBounds(40,100,100,60);
	      panel.add(button);

	    
	      panel.setSize(400,400);
	      button.addActionListener(new ActionListener(){
	    	  

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Button")) {
				   l2.setText("clicked");
				  
				   l2.setText("Method called");
				}
			}
	    	});
	      panel.setVisible(true);

		// list
				DefaultListModel<String> listModel = new DefaultListModel<>();
		
		listModel.addElement("Live Recognisition");

		listModel.addElement("a add b");
		listModel.addElement("a subtract b");
		listModel.addElement("a multiply b");
		listModel.addElement("a divide b");
		listModel.addElement("a modulo b");
		listModel.addElement("sin a");
		listModel.addElement("cos a");
		listModel.addElement("tan a");
		listModel.addElement("cot a");
		listModel.addElement("sec a");
		listModel.addElement("cosec a");
		listModel.addElement("a power b");
		listModel.addElement("a percent b");

		listModel.addElement("factorial a");
		listModel.addElement("log a");
		listModel.addElement("expo a");
		

		list = new JList<>(listModel);
		
		mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println("Double clicked on Item ");
				selected = list.getSelectedValue();
				
				
				l3.setText(selected);
			
					try

					{
						
						
						
						if (selected.equals("Live Recognisition")) {
				         l1.setText("Live Recognisition is going on........");
							voice();
							}
						if (selected.equals("a add b")) {
							 path_add = "add";
							l1.setText("Addition is going in............");
							voiceSpeak(path_add);
							}
							 if (selected.equals("a subtract b")) {
								 path_add = "subtract";
								l1.setText("Subtraction is going in............");
								voiceSpeak(path_add);
								}
							 if (selected.equals("a multiply b")) {
								 path_add = "multiply_2";
								l1.setText("Multiplication is going in............");
								voiceSpeak(path_add);
								}
							 if (selected.equals("a divide b")) {
								 path_add = "divide_2";
								l1.setText("Division is going in............");
								voiceSpeak(path_add);
								}
							 if (selected.equals("a modulo b")) {
								 path_add = "modulo_3";
								l1.setText("Modulo is going in............");
								voiceSpeak(path_add);
								}

						
						
						
								

					} catch (Exception e1) {
						System.out.println("Exception som");
						e1.printStackTrace();
					

				}

			
				
		};
		
		
		
		
		};
		
		
		
		JScrollPane jsc=new JScrollPane(list);
		jsc.setBounds((int) listx, (int) listy, (int) listW, (int) listH);
		frame.add(jsc);
		list.addMouseListener(mouseListener);
		list.setFont(new Font("Helvetica Neue", Font.PLAIN, 35));
		//list.setBounds((int) listx, (int) listy, (int) listW, (int) listH);
		list.setBackground(Color.orange);
		list.setVisible(true);    
		
		
		//frame.add(list);
		frame.add(l1);
		frame.add(l3);
		frame.add(jpanel1);
		frame.add(frame2);
		//frame.add(panel);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.add(l2);
		
		
		
		 
		
			
			

		
	}
	
	
	
	private static void clicked_Mode()
	{
	
		DefaultListModel<String> model = new DefaultListModel<>();
		  JList<String> sList = new JList<>(model);
				  
		  model.addElement("C");
		  model.addElement("=");
		
		  model.addElement("+");
		  model.addElement("-");
		  model.addElement("/");
		  model.addElement("*");
		  model.addElement("%");
		  model.addElement("e");
		  model.addElement("7");
		  model.addElement("8");
		  model.addElement("9");
		  model.addElement("4");
		  model.addElement("5");
		  model.addElement("6");
		  model.addElement("1");
		  model.addElement("2");
		  model.addElement("3");
		  model.addElement("0");
		  model.addElement("sin");
		  model.addElement("cos");
		  model.addElement("tan");
		  model.addElement("cot");
		  model.addElement("sec");
		
		  model.addElement("log");
		 
		  model.addElement("10^x");
		  model.addElement("sqrt");
		  model.addElement("cosec");
		  
		  
			mouseListener_1 = new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					System.out.println("Double clicked on Item ");
					selected = sList.getSelectedValue();
					
					  join +=selected;
				
					
						 
						  if(selected=="=")  
						  {   
							  String delimeter=("\\+|\\-|\\*|\\/");
							  String[] parts = join.split(delimeter);
							  String part1=parts[0];
							  String part2=parts[1];
							  
							  String[] part3=part2.split("\\=");
							  String part4=part3[0];
							   a=Integer.parseInt(part1);
							   b=Integer.parseInt(part4);
							  
							 
							 for(int k=0;k<join.length(); k++)
							 {
				           
								 
								 if(join.charAt(k)=='+')
								 {
									 c=a+b;
									  l3.setText(c+""); 
									  break;
								 }
								 if(join.charAt(k)=='-')
								 {
									 c=a-b;
									  l3.setText(c+""); 
									  break;
 
								 }
								 if(join.charAt(k)=='*')
								 {
									 c=a*b;
									  l3.setText(c+""); 
									  break;
 
								 }
								 if(join.charAt(k)=='/') 
								 {
									double d=a/b;
									  l3.setText(d+""); 
									  break;
 
								 }
								 
								 
							 }
							  
							 
							
							  
						  }
						  else if(selected=="C")
						  {
						  
						  l2.setText("");
						  l3.setText("");
						  join="";
						  }
						  else
						  {
							  l2.setText(join);  
						  }
			};
			
			
			
			
			};
			


		  sList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		  sList.setVisibleRowCount(-1);
		  sList.setLayoutOrientation(JList.HORIZONTAL_WRAP);

		  sList.addMouseListener(mouseListener_1);
		sList.setFont(new Font("Helvetica Neue", Font.BOLD, 35));
		sList.setBounds((int) cli_coorx, (int) cli_coory, (int) cli_dimx, (int) cli_dimy);
		sList.setBackground(Color.GRAY);
		sList.setVisible(true);
			
		frame.add(sList);
		
		
		
		
		
		
		
		
	}
  
	private static void initializeCoodinates(double height2, double width2) {
		//for voice animation
		voiceAreax=0;
		voiceAreay=0;
		//for status
		statusx=width2/2;
		statusy=0;
		//input words
		inputx=0;
		inputy=height2/10;
		//list view
		listx=0;
		listy=height*0.2;
		//for result
		resultx=width2*0.25;
		resulty=height2*0.3;
		//show result
		resx=width2*0.25;
		resy=height2*0.5;
		//Clicked mode
		cli_coorx=width*0.68;
		cli_coory=height*0.30;
		//button
		button_coox=width*0.20;
		button_cooy=height*0.70;
	}


	private static void initializeDimens(double height2, double width2) {
		//for voice animation
		voiceAreaH=height2/10;
		voiceAreaW=width2/2;
		//for status
		statusH=height2/10;
		statusW=width2/2;
		//input words
		inputH=height2/10;
		inputW=width2;
		//list view
		listH=height2*0.8;
		listW=width2*0.2;
		//for result
		resultH=height2*0.10;
		resultW=width2*0.40;
		//show output
		resH=height2*0.10;
		resW=width2*0.40;
		
		//clicked mode
		  cli_dimx=width*0.30;
		  cli_dimy=height*0.60;
		  //buttton
		  button_x=width*0.15;
		  button_y=width*0.15;

	}
	
	
		 
			public static void voice()
			{
				try { configuration = new Configuration();
               System.out.println("Running live recognisition mode.......");
				configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
				configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
				configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
					
					LiveRecogniser= new LiveSpeechRecognizer(configuration);
					System.out.println("Start Speaking...........");
					LiveRecogniser.startRecognition(true);
					SpeechResult results = LiveRecogniser.getResult();
					String result1= results.getHypothesis();
					System.out.println(result1);
					hypothesis=result1;
					LiveRecogniser.stopRecognition();
					operation();
				} catch (IOException e) {
					
					e.printStackTrace();
					System.out.println(e);
				}
				
			}
	
	public static  void voiceSpeak(String url) throws Exception
	{  
		 configuration = new Configuration();

			configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
			configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
			configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
				
		//AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(url));
		Clip clip = AudioSystem.getClip();
		 clip.open(AudioSystem.getAudioInputStream(new File(V2w.class.getResource("/data/"+url+".wav").getPath())));
		clip.start();
		StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
		InputStream stream =  V2w.class
                .getResourceAsStream("/data/"+url+".wav");

		recognizer.startRecognition(stream);
		SpeechResult result;
		while ((result = recognizer.getResult()) != null) {
			hypothesis = result.getHypothesis();

			System.out.format("Hypothesis: %s\n", result.getHypothesis());
           
		}
		
		recognizer.stopRecognition();
		operation();
	}
		
	public static void operation()
	{
		f=0;
		String[] partition = hypothesis.split(" ");
		int size = partition.length;
		for (int i = 0; i < size; i++)
		
		{

			if (partition[i].equals("plus")) 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("plus");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "+" + second_num);
				double res = first_num + second_num;
				l3.setText("" + res);
				break;
			} 
			
			else if (partition[i].equals("subtract")) 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("sub");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "-" + second_num);
				double res = first_num - second_num;
				l3.setText("" + res);
				break;

			} 
			
			else if (partition[i].equals("multiply")) 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("multiply");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "*" + second_num);
				double res = first_num * second_num;
				l3.setText("" + res);
				break;
			}

			else if (partition[i].equals("divide")) 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("divide");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "/" + second_num);
				double res = first_num / second_num;
				l3.setText("" + res);
				break;
			}

			else if (partition[i].equals("modulo")) 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("mod");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "mod" + second_num);
				double res = first_num % second_num;
				l3.setText("" + res);
				break;
			}

			else if (partition[i] == "power") 
			
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("power");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "" + second_num);
				double res = first_num + second_num;
				l3.setText("" + res);
				break;

			} 
			
			else if (partition[i] == "percent") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split("power");

				String part1 = parts[0];
				String part2 = parts[1];

				double first_num = convert(part1);
				double second_num = convert(part2);
				l2.setText("" + first_num + "%" + second_num);
				double res = (first_num/ second_num)*100;
				l3.setText("" + res);
				// System.out.println("Value of sin(90) is: "+Math.sin(90));
				break;
				
				
			}
			else if (partition[i] == "sin") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				double angle = convert(part2);
				System.out.println(Math.sin(angle));
				break;
				
				
			}
			else if (partition[i] == "cos") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				double angle = convert(part2);
				System.out.println(Math.cos(angle));
				break;
								
			}
			
			
			else if (partition[i] == "tan") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				double angle = convert(part2);
				System.out.println(Math.tan(angle));
				break;
				
				
			}
			else if (partition[i] == "cot") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				double angle = convert(part2);
				System.out.println(1/Math.tan(angle));
				break;
				
				
			}
			
			else if (partition[i] == "cosec") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				double angle = convert(part2);
				System.out.println(1/Math.sin(angle));
				break;
				
				
			}
			
			else if (partition[i] == "sec") 
			{
				l1.setText("Your Voice:-" + hypothesis);

				String[] parts = hypothesis.split(" ");
				String part1 = parts[0];
				String part2 = parts[1];
				double angle = convert(part2);
				System.out.println(1/Math.cos(angle));
				break;
				
				
			}
			else
			{
			  if(i==(size-1)  )
			  {
				  f=5;
			  }
			   
				
			}
			
		}
		if(f==5)
		{
			 AudioInputStream audioInputStream = null;
				try {
					//audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/lenovo/Desktop/Voices/sorry.wav"));
					Clip clip = null;
					try {
						clip = AudioSystem.getClip();
					} catch (LineUnavailableException e1) {
						
						e1.printStackTrace();
					}
					try {
					
						 clip.open(AudioSystem.getAudioInputStream(new File(V2w.class.getResource("/data/"+"sorry"+".wav").getPath())));

					} catch (LineUnavailableException e) {
		
						e.printStackTrace();
					}
					clip.start();
				} catch (UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
		
					e.printStackTrace();
				}
		}
	
	}
					 				  
											   
			       	
	

	
   

	public static int convert(String input)
	{
		String testcase1 = input;
		// Calculator testInstance = new Calculator();
		      return inNumerals(testcase1);	
	}
		public static int inNumerals(String inwords)
		{
		    int wordnum = 0;
		    String[] arrinwords = inwords.split(" ");
		    int arrinwordsLength = arrinwords.length;
		    if(inwords.equals("zero"))
		    {
		        return 0;
		    }
		    if(inwords.contains("thousand"))
		    {
		        int indexofthousand = inwords.indexOf("thousand");
		        //System.out.println(indexofthousand);
		        String beforethousand = inwords.substring(0,indexofthousand);
		        //System.out.println(beforethousand);
		        String[] arrbeforethousand = beforethousand.split(" ");
		        int arrbeforethousandLength = arrbeforethousand.length;
		        //System.out.println(arrbeforethousandLength);
		        if(arrbeforethousandLength==2)
		        {
		            wordnum = wordnum + 1000*(wordtonum(arrbeforethousand[0]) + wordtonum(arrbeforethousand[1]));
		            //System.out.println(wordnum);
		        }
		        if(arrbeforethousandLength==1)
		        {
		            wordnum = wordnum + 1000*(wordtonum(arrbeforethousand[0]));
		            //System.out.println(wordnum);
		        }

		    }
		    if(inwords.contains("hundred"))
		    {
		        int indexofhundred = inwords.indexOf("hundred");
		        //System.out.println(indexofhundred);
		        String beforehundred = inwords.substring(0,indexofhundred);

		        //System.out.println(beforehundred);
		        String[] arrbeforehundred = beforehundred.split(" ");
		        int arrbeforehundredLength = arrbeforehundred.length;
		        wordnum = wordnum + 100*(wordtonum(arrbeforehundred[arrbeforehundredLength-1]));
		        String afterhundred = inwords.substring(indexofhundred+7);//7 for 7 char of hundred
		        //System.out.println(afterhundred);
		        String[] arrafterhundred = afterhundred.split(" ");
		        int arrafterhundredLength = arrafterhundred.length;
		        if(arrafterhundredLength==1)
		        {
		            wordnum = wordnum + (wordtonum(arrafterhundred[0]));
		        }
		        if(arrafterhundredLength==2)
		        {
		            wordnum = wordnum + (wordtonum(arrafterhundred[1]) + wordtonum(arrafterhundred[0]));
		        }
		        //System.out.println(wordnum);

		    }
		    if(!inwords.contains("thousand") && !inwords.contains("hundred"))
		    {
		        if(arrinwordsLength==1)
		        {
		            wordnum = wordnum + (wordtonum(arrinwords[0]));
		        }
		        if(arrinwordsLength==2)
		        {
		            wordnum = wordnum + (wordtonum(arrinwords[1]) + wordtonum(arrinwords[0]));
		        }
		        //System.out.println(wordnum);
		    }


		    return wordnum;
		}   


		public static int wordtonum(String word)
		{
		        int num = 0;
		        switch (word) {
		            case "one":  num = 1;
		                     break;
		            case "two":  num = 2;
		                     break;
		            case "three":  num = 3;
		                     break;
		            case "four":  num = 4;
		                     break;
		            case "five":  num = 5;
		                     break;
		            case "six":  num = 6;
		                     break;
		            case "seven":  num = 7;
		                     break;
		            case "eight":  num = 8;
		                     break;
		            case "nine":  num = 9;
		                     break;
		            case "ten": num = 10;
		                     break;
		            case "eleven": num = 11;
		                     break;
		            case "twelve": num = 12;
		                     break;
		            case "thirteen": num = 13;
		                     break;
		            case "fourteen": num = 14;
		                     break;             
		            case "fifteen": num = 15;
		                     break;
		            case "sixteen": num = 16;
		                     break;
		            case "seventeen": num = 17;
		                     break;
		            case "eighteen": num = 18;
		                     break;
		            case "nineteen": num = 19;
		                     break;
		            case "twenty":  num = 20;
		                     break;
		            case "thirty":  num = 30;
		                     break;
		            case "forty":  num = 40;
		                     break;
		            case "fifty":  num = 50;
		                     break;
		            case "sixty":  num = 60;
		                     break;
		            case "seventy":  num = 70;
		                     break;
		            case"eighty":  num = 80;
		                     break;
		            case "ninety":  num = 90;
		                     break; 
		            case "hundred": num = 100;
		                        break;
		            case "thousand": num = 1000;
		                        break;     
		           /*default: num = "Invalid month";
		                             break;*/
		        }
		        return num;
		
		
	}


		@Override
		public void keyPressed(KeyEvent e) {
			int keycode=e.getKeyCode();		
			if(keycode==KeyEvent.VK_C)
			{
				System.out.println("key pressed");
				voice();
			}
		}


		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
}
	
	



