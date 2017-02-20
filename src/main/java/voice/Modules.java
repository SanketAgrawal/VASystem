package voice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Modules{
	
	
	public static double height1,width1;
	public static double lab_1x,lab_1y,lab_2x,lab_2y,lab_3x,lab_3y,lab_4x,lab_4y,lab_5x,lab_5y;
	public static double dim_1x,dim_1y,dim_2x,dim_2y,dim_3x,dim_3y,dim_4x,dim_4y,dim_5x,dim_5y;
	static JFrame frame;
	public static JButton label1,label2,label3,label4;
	public static JLabel label5,label6;
	

	public static void main(String[] args) {
		V2w.initialize();
		
	/*	while (SpeechInterface.getRecognizerQueueSize() > 0)
		{
		      String s = SpeechInterface.popRecognizedString();
		      System.out.println("You said: " + s);
		}
		/*
		ArrayList<String> a=V2w.startLiveRecognition();
		for(int i=0;i<a.size();i++){
			System.out.print(a.get(i)+" ");
		}*/
       Toolkit t=Toolkit.getDefaultToolkit();
		
		Dimension dim=t.getScreenSize();
          height1=dim.height;
          width1=dim.width;
          coordinates(height1,width1);
          dimension(height1,width1);
          window_View();
		}
	public static void coordinates(double height,double width)
	{
	         	//Label1
		        lab_1x=width*0.20;
		        lab_1y=height*0.10;
		
		        //Label2
				lab_2x=width*0.52;
				lab_2y=height*0.10;
				
				
				//Label3
				lab_3x=width*0.20;
				lab_3y=height*0.42;
				
				//Label4
				lab_4x=width*0.52;
				lab_4y=height*0.42;
				//label5
			     lab_5x=width*0.15;
			     lab_5y=height*0.75;
		
	}
	
	public static void dimension(double height,double width)
	{
		
		//label1
		dim_1x=width*0.30;
		dim_1y=height*0.30;
		
		      //label2
				dim_2x=width*0.30;
				dim_2y=height*0.30;
				
				//label3
				dim_3x=width*0.30;
				dim_3y=height*0.30;		
				
				//label4
				dim_4x=width*0.30;
				dim_4y=height*0.30;	
		    //label5
				dim_5x=width*0.80;
				dim_5y=height*0.10;
		
		
		
	}
	
	
	public static void window_View()
	{
		frame=new JFrame("VASYSTEM");
		frame.setBounds(0, 0, (int) width1, (int) height1);
		
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		 label1 = new JButton(); 
		 label1.setBounds((int) lab_4x, (int) lab_4y, (int) dim_4x, (int) dim_4y);
		 Border border = BorderFactory.createLineBorder(Color.blue);
			label1.setBorder(border);
			label1.setVisible(true);
			int a1=(int) dim_4x;
			int b1=(int) dim_4y;
			ImageIcon imageIcon1 = new ImageIcon(new ImageIcon(V2w.class.getResource("/data/"+"calculator"+".jpg").getPath()).getImage().getScaledInstance(a1,b1, Image.SCALE_DEFAULT));
			label1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Calculator calculator=new Calculator();
					try {
						calculator.start();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			label1.setIcon(imageIcon1);
			
			//label2
			 label2 = new JButton(" third Label "); 
			 label2.setBounds((int) lab_3x, (int) lab_3y, (int) dim_3x, (int) dim_3y);
			 Border border2 = BorderFactory.createLineBorder(Color.green);
				label2.setBorder(border2);
				label2.setVisible(true);
				int a2=(int) dim_3x;
				int b2=(int) dim_3y;
				ImageIcon imageIcon2 = new ImageIcon(new ImageIcon(V2w.class.getResource("/data/"+"Browser"+".png").getPath()).getImage().getScaledInstance(a2,b2, Image.SCALE_DEFAULT));
				label2.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Browser browser=new Browser();
						browser.start();
						
					}
				});
				label2.setIcon(imageIcon2);
		 
		 //label3
		 label3 = new JButton(" first Label "); 
		 label3.setBounds((int) lab_1x, (int) lab_1y, (int) dim_1x, (int) dim_1y);
		 Border border3 = BorderFactory.createLineBorder(Color.black);
			label3.setBorder(border3);
			label3.setVisible(true);
			int a3=(int) dim_1x;
			int b3=(int) dim_1y;
			ImageIcon imageIcon3 = new ImageIcon(new ImageIcon(V2w.class.getResource("/data/"+"Notepad2"+".jpg").getPath()).getImage().getScaledInstance(a3,b3, Image.SCALE_DEFAULT));
			
			label3.setIcon(imageIcon3);
			label3.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					new Editor();
				}
			});
	 
			//label4
			
			 label4 = new JButton(); 
			 label4.setBounds((int) lab_2x, (int) lab_2y, (int) dim_2x, (int) dim_2y);
			 Border border4 = BorderFactory.createLineBorder(Color.gray);
				label4.setBorder(border4);
				label4.setVisible(true);
				
				int a4=(int) dim_2x;
				int b4=(int) dim_2y;
				ImageIcon imageIcon4 = new ImageIcon(new ImageIcon(V2w.class.getResource("/data/"+"fse"+".png").getPath()).getImage().getScaledInstance(a4,b4, Image.SCALE_DEFAULT));
				label4.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						FileSearchEngine engine=new FileSearchEngine();
						engine.start();
					}
				});
				label4.setIcon(imageIcon4);
		 
				//label5
				
				 label5 = new JLabel(); 
				 label5.setBounds((int) lab_5x, (int) lab_5y, (int) dim_5x, (int) dim_5y);
				 BorderFactory.createLineBorder(Color.gray);
				 label5.setText("VOICE AUTOMATED SOFTWARE");
				
				 
					//label5.setBorder(border5);
					label5.setFont(new Font("Forte", Font.PLAIN, 70));
					label5.setVisible(true);
					label5.setForeground(Color.black);
					
					//label6
					label6=new JLabel();
					 label6.setBounds((int) lab_5x, (int) lab_5y, (int) dim_5x, (int) dim_5y);
					 
					 Border border6 = BorderFactory.createLineBorder(Color.green);
						label6.setBorder(border6);
						label6.setVisible(true);

		 frame.add(label1); 
	 frame.add(label2); 
		 frame.add(label3); 
		 frame.add(label4); 
	 frame.add(label5);
		 //frame.add(label6);
		 frame.setLayout(null);
		frame.setVisible(true);
		
		
	}

}
class WordNotFoundException extends Exception{
	public String toString(){
		return "Unable to find Recog. Words";
	}
}