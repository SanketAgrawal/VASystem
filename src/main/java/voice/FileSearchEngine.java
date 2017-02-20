package voice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class FileSearchEngine {
	private static double height,width;
	private static double resH,resW,voiceAreaH,voiceAreaW,statusH,statusW,inputH,inputW,listH,listW,resultH,resultW,cli_dimx,cli_dimy,button_x,button_y,button_coox,button_cooy;
	private static double resx,resy,voiceAreax,voiceAreay,statusx,statusy,inputx,inputy,listx,listy,resultx,resulty,cli_coorx,cli_coory;
	static JPanel panel;
	static String path;
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
	 static String join="";
	 static int choose=0;
	static int a,b,c,run_obj=0;
	static LiveSpeechRecognizer liveRecognizer;
	static JButton button;
	static Configuration configuration ;
	public void start() {
		

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		height = dimension.height;
		  width = dimension.width;

		initializeDimens(height, width);
		initializeCoodinates(height, width);
					
		initializeWindowView();
		
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

		l1.setText("Input the name of the Applications..........");

		Border border = BorderFactory.createLineBorder(Color.black);
		l1.setBorder(border);
		l1.setVisible(true);

		// input operation box

		l2 = new JLabel("Test");
		l2.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		l2.setBounds((int) resultx, (int) resulty, (int) resultW, (int) resultH);

		l2.setText("Enter name of application......");

		Border border1 = BorderFactory.createLineBorder(Color.black);
		l2.setBorder(border1);
		l2.setVisible(true);

		// result of operation
		l3 = new JLabel("Test");
		l3.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		l3.setBounds((int) resx, (int) resy, (int) resW, (int) resH);

		l3.setText("");

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

		listModel.addElement("Internet Explorer");
		listModel.addElement("Window Media Player");
		listModel.addElement("Opera");
		listModel.addElement("Mozilla Firefox");
		listModel.addElement("Chrome");
		listModel.addElement("Notepad++");
		listModel.addElement("Notepad");
		listModel.addElement("IDM");
		listModel.addElement("Bluestacks");
		
//listModel.addElement("");
//		listModel.addElement("cot a");
//		listModel.addElement("sec a");
//		listModel.addElement("cosec a");
//		listModel.addElement("a power b");
//		listModel.addElement("a percent b");
//
//		listModel.addElement("factorial a");
//		listModel.addElement("log a");
//		listModel.addElement("expo a");
//		

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
							live_Recognisition();
	                         }

                    	else
                    	{
                        String  url="som";
					voiceSpeak(url);
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
		//list.add(new JScrollPane());
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
		resultx=width2*0.22;
		resulty=height2*0.4;
		//show result
		resx=width2*0.22;
		resy=height2*0.5;
		
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
		listH=height2*0.75;
		listW=width2*0.20;
		//for result
		resultH=height2*0.07;
		resultW=width2*0.70;
		//show output
		resH=height2*0.07;
		resW=width2*0.70;
		
		
	}
	public static void live_Recognisition()
	{
		
		configuration = new Configuration();

		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");	 

		
		try {
			liveRecognizer= new LiveSpeechRecognizer(configuration);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Start Speaking...........");
		liveRecognizer.startRecognition(true);
		SpeechResult results = liveRecognizer.getResult();
		String result1= results.getHypothesis();
		System.out.println(result1);
		hypothesis=result1;
		liveRecognizer.stopRecognition();
		 operation();
		
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
  		InputStream stream = V2w.class
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
		run_obj=0;
		l3.setText(hypothesis);
		if(hypothesis.equals("internet explorer"))
		{
			path="C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe";
			l3.setText(" REQUESED FILE  FOUND..........");
		}
		else if(hypothesis.equals("Chrome"))
		{
			path="C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome";

		}
		
		else if(hypothesis.equals("Mozilla Firefox"))
		{
			path="C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
		}
		else if(hypothesis.equals("Window Media Player"))
		{
			path="C:\\Program Files\\Windows Media Player\\wmplayer";

		}
		else if(hypothesis.equals("Notepad++"))
		{
			path="C:\\Program Files (x86)\\Notepad++\\notepad++.exe";

		}
		else if(hypothesis.equals("IDM"))
		{
			path="C:\\Program Files (x86)\\Internet Download Manager\\IDMan.exe";
		}
		else if(hypothesis.equals("Opera"))
		{
			path="C:\\Program Files (x86)\\Opera\\launcher.exe";
		}
		else if(hypothesis.equals("Notepad"))
		{
			path="Notepad.exe";
		}
		else if(hypothesis.equals("Bluestacks"))
		{
			path="C:\\Program Files (x86)\\Bluestacks\\HD-RunApp";
		}
		else
		{
			
			System.out.println("SORRRY NO MATCH FOUND.....");
			System.out.println("TRY TO SPEAK IN A BETTER WAY.....");
			 AudioInputStream audioInputStream = null;
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new File("C:/Users/Somshekhar Dixit/Desktop/Voices/sorry.wav"));
					Clip clip = null;
					try {
						clip = AudioSystem.getClip();
					} catch (LineUnavailableException e1) {
						
						e1.printStackTrace();
					}
					try {
						clip.open(audioInputStream);
					} catch (LineUnavailableException e) {
		
						e.printStackTrace();
					}
					clip.start();
				} catch (UnsupportedAudioFileException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
		
					e.printStackTrace();
				}
				
				run_obj=1;


		}
		
		if(run_obj==0)
		 {

		 try {
			 		        String ss = null;
		        Runtime obj = null;
		    	  
		       	
		        Process p = Runtime.getRuntime().exec(path);
		       	
		        BufferedWriter writeer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		        writeer.write("dir");
		        writeer.flush();
		        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		        System.out.println("Here is the standard output of the command:\n");
		        while ((ss = stdInput.readLine()) != null) {
		            System.out.println(ss);
		        }
		        System.out.println("Here is the standard error of the command (if any):\n");
		        while ((ss = stdError.readLine()) != null) {
		            System.out.println(ss);
		        }

		    } catch (IOException e) {
		        System.out.println("FROM CATCH" + e.toString());
		    }
		 }
		
		
}
}
