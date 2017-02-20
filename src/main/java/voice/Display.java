package voice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class Display {

	private double height,width;
	private double voiceAreaH,voiceAreaW,statusH,statusW,inputH,inputW,listH,listW,resultH,resultW;
	private double voiceAreax,voiceAreay,statusx,statusy,inputx,inputy,listx,listy,resultx,resulty;
	public TextPanel area;
	public JLabel l1;
	public JList<String>  list,listI,listD,listC,listF,listS;
	public JScrollPane spI,spD,spC,spF,spS,spM;
	public JPanel jpanel1,paneI,paneD,paneC,paneF,paneS,paneM;
	public JFrame frame;
	
    public void initializeDisplay() {
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension dimension=toolkit.getScreenSize();
		height=dimension.height*0.95;
		width=dimension.width*0.95;
		
		initializeDimens(height,width);
		initializeCoodinates(height,width);
		initializeWindowView();

	}
	
    public void initializeWindowView() {
		
		frame=new JFrame("VAS-Editor");
		frame.setBounds(0, 0, (int)width,(int)height);
		
		//voice
		ImageIcon icon=new ImageIcon(Display.class.getResource("/data/sound-wave-motion-animation2.gif"));
		JLabel frame2=new JLabel(icon);
		frame2.setBounds((int)voiceAreax, (int)voiceAreay, (int)voiceAreaW, (int)voiceAreaH);
		/*frame2.setBackground(Color.CYAN);
		frame2.setVisible(true);*/
		
	   //status
		jpanel1=new JPanel();
		jpanel1.setBounds((int)statusx, (int)statusy, (int)statusW, (int)statusH);
		jpanel1.setBackground(Color.GREEN);
		jpanel1.setVisible(true);
		
		//input words display
		l1 = new JLabel("Test");
		l1.setBounds((int)inputx, (int)inputy, (int)inputW, (int)inputH);
		l1.setText("This is the textview where the sound words of a person are heard");
		Border border = BorderFactory.createLineBorder(Color.black);
		l1.setBorder(border);
		l1.setFocusable(true);
		
		//list view
		 /* list = new JList<String>();
		  list.setBackground(Color.green);
		  list.setLayoutOrientation(JList.VERTICAL);
		  list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  list.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		  JScrollPane jScrollPaneList=new JScrollPane(list);
		  jScrollPaneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  jScrollPaneList.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		  jScrollPaneList.setViewportView(list);*/
		  createList();
		  createListF();
		  createListD();
		  createListI();
		  createListC();
		  createListS();
		
		  //
		  area=new TextPanel();
		  JScrollPane jScrollPane=new JScrollPane(area);
		  jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  jScrollPane.setBounds((int)resultx, (int)resulty, (int)resultW, (int)resultH);
		
	    frame.add(jScrollPane);
		//frame.add(jScrollPaneList);
		//frame.add(list);
		frame.add(l1);
		frame.add(jpanel1);	
		frame.add(frame2);
		//frame.add(area);
		frame.setLayout(null);
		frame.setVisible(true);
		
		
		
	}


	public void initializeCoodinates(double height2, double width2) {
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
		resultx=width2*0.3;
		resulty=height2*0.2;
		
	}


    public void initializeDimens(double height2, double width2) {
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
		listH=height2*0.77;
		listW=width2*0.3;
		//for result
		resultH=height2*0.8;
		resultW=width2*0.7;
	}

	public TextPanel getArea() {
		// TODO Auto-generated method stub
		return area;
	}
	
    public JLabel getLabel() {
		// TODO Auto-generated method stub
		return l1;
	}
	
    public JPanel getPanel() {
		// TODO Auto-generated method stub
		return jpanel1;
	}
	
    public JList<String> getList() {
		// TODO Auto-generated method stub
		return list;
	}
   public void setListI(){
    	createListI();
    	paneM.setVisible(false);
    	paneC.setVisible(false);
    	paneD.setVisible(false);
    	paneS.setVisible(false);
    	paneF.setVisible(false);
    	paneI.setVisible(true);
    }
   public void setListC(){
    	createListC();
    	paneM.setVisible(false);
    	paneI.setVisible(false);
    	paneD.setVisible(false);
    	paneS.setVisible(false);
    	paneF.setVisible(false);
    	paneC.setVisible(true);
    }
   public void setListS(){
    	createListS();
    	paneM.setVisible(false);
    	paneC.setVisible(false);
    	paneD.setVisible(false);
    	paneI.setVisible(false);
    	paneF.setVisible(false);
    	paneS.setVisible(true);
    }
   public void setListD(){
    	createListD();
    	paneM.setVisible(false);
    	paneC.setVisible(false);
    	paneI.setVisible(false);
    	paneS.setVisible(false);
    	paneF.setVisible(false);
    	paneD.setVisible(true);
    }
   public void setListF(){
    	createListF();
    	paneM.setVisible(false);
    	paneC.setVisible(false);
    	paneD.setVisible(false);
    	paneS.setVisible(false);
    	paneI.setVisible(false);
    	paneF.setVisible(true);
    }
   public void setList(){
    	createList();
    	paneI.setVisible(false);
    	paneC.setVisible(false);
    	paneD.setVisible(false);
    	paneS.setVisible(false);
    	paneF.setVisible(false);
    	paneM.setVisible(true);
    }
    private void createList() {
    	if(paneM!=null)
			frame.remove(paneM);
    	paneM=new JPanel();
		paneM.setBackground(Color.BLUE);
		paneM.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		
		list=new JList<String>(VarList.voiceList);
		list.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		spM=new JScrollPane(list);	
		spM.setBounds(0, 0, (int)listW, (int)listH);
		paneM.setVisible(true);
		paneM.add(spM);
		paneM.setLayout(null);
        list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				Editor.checkList();
				
			}

			
		});

		frame.add(paneM);
		
	}

	private void createListI() {
    	if(paneI!=null)
			frame.remove(paneI);
    	paneI=new JPanel();
		paneI.setBackground(Color.BLUE);
		paneI.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		String l[]=new String[VarList.intVarList.size()];
		System.out.println(VarList.intVarList.size());
		for(int i=0;i<VarList.intVarList.size();i++){
			l[i]=VarList.intVarList.get(i);
		}
		listI=new JList<String>(l);
		listI.setBackground(Color.green);
		listI.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		spI=new JScrollPane(listI);	
		spI.setBounds(0,0, (int)listW, (int)listH);
		paneI.setVisible(false);
		paneI.add(spI);
		paneI.setLayout(null);
		frame.add(paneI);
		
	}
    private void createListC() {
    	if(paneC!=null)
			frame.remove(paneC);
    	paneC=new JPanel();
		paneC.setBackground(Color.BLUE);
		paneC.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		String l[]=new String[VarList.charVarList.size()];
		System.out.println(VarList.charVarList.size());
		for(int i=0;i<VarList.charVarList.size();i++){
			l[i]=VarList.charVarList.get(i);
		}
		listC=new JList<String>(l);
		listC.setBackground(Color.green);
		listC.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		spC=new JScrollPane(listC);	
		spC.setBounds(0, 0, (int)listW, (int)listH);
		paneC.setVisible(false);
		paneC.add(spC);
		paneC.setLayout(null);
		frame.add(paneC);
		
	}
    private void createListS() {
    	if(paneS!=null)
			frame.remove(paneS);
    	paneS=new JPanel();
		paneS.setBackground(Color.BLUE);
		paneS.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		String l[]=new String[VarList.stringVarList.size()];
		System.out.println(VarList.stringVarList.size());
		for(int i=0;i<VarList.stringVarList.size();i++){
			l[i]=VarList.stringVarList.get(i);
		}
		listS=new JList<String>(l);
		listS.setBackground(Color.green);
		listS.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		spS=new JScrollPane(listS);	
		spS.setBounds(0, 0, (int)listW, (int)listH);
		paneS.setVisible(false);
		paneS.add(spS);
		paneS.setLayout(null);
		frame.add(paneS);
	
    }
    private void createListF() {
    	if(paneF!=null)
			frame.remove(paneF);
    	paneF=new JPanel();
		paneF.setBackground(Color.BLUE);
		paneF.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		String l[]=new String[VarList.floatVarList.size()];
		System.out.println(VarList.floatVarList.size());
		for(int i=0;i<VarList.floatVarList.size();i++){
			l[i]=VarList.floatVarList.get(i);
		}
		listF=new JList<String>(l);
		listF.setBackground(Color.green);
		listF.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		spF=new JScrollPane(listF);	
		spF.setBounds(0, 0, (int)listW, (int)listH);
		paneF.setVisible(false);
		paneF.add(spF);
		paneF.setLayout(null);
		frame.add(paneF);
	
    }
    private void createListD() {
    	if(paneD!=null)
			frame.remove(paneD);
    	paneD=new JPanel();
		paneD.setBackground(Color.BLUE);
		paneD.setBounds((int)listx, (int)listy, (int)listW, (int)listH);
		String l[]=new String[VarList.doubleVarList.size()];
		System.out.println(VarList.doubleVarList.size());
		for(int i=0;i<VarList.doubleVarList.size();i++){
			l[i]=VarList.doubleVarList.get(i);
		}
		listD=new JList<String>(l);
		listD.setBackground(Color.green);
		listD.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		spD=new JScrollPane(listD);	
		spD.setBounds(0,0, (int)listW, (int)listH);
		paneD.setVisible(false);
		paneD.add(spD);
		paneD.setLayout(null);
		frame.add(paneD);
	
    }

	public JPanel getPaneM() {
		// TODO Auto-generated method stub
		return paneM;
	}



}
