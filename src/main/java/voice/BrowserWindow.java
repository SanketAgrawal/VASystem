package voice;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javafx.application.*;
import javafx.embed.swing.*;
import javafx.scene.*;
import javafx.scene.web.*;

public class BrowserWindow extends JPanel implements MouseListener,KeyListener{

	private int height,width,listStatus=0;
	private ArrayList<String> words;
	private Browsers browser;
	private JList<String> list,list1,list2;
	private JScrollPane jScrollPane,jScrollPane1,jScrollPane2;
	private JPanel jPanel,lpanel1,lpanel2,lpanel3;
	private Browsers browser2;
	
	
	public BrowserWindow(double height, double width) {
		this.height=(int)height;
		this.width=(int)width;
		initializeView();
		VarList.bookmarks.add("\tBookmarks");
		VarList.history.add("\tHistory");
	}

	private void initializeView() {
		
		this.setBounds(0,0, width, height);
		setBackground(Color.MAGENTA);
		setLayout(null);
		
		try {
			UIManager.setLookAndFeel(
				    UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (UnsupportedLookAndFeelException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		lpanel1=new JPanel();
		lpanel1.setBackground(Color.BLUE);
		list=new JList<String>(VarList.voiceListWeb);
		list.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		jScrollPane=new JScrollPane(list);
		jScrollPane.setBounds(0,0,(int)(width*0.21),(int)(height*0.62));
		lpanel1.setBounds((int)(width*0.01),(int)(width*0.01)+(int)(height*0.26),(int)(width*0.21),(int)(height*0.62));
		list.addMouseListener(this);
		list.addKeyListener(this);
		lpanel1.setVisible(true);
		lpanel1.add(jScrollPane);
		lpanel1.setLayout(null);
		add(lpanel1);
		
		//for bookmark
		lpanel2=new JPanel();
		lpanel2.setBackground(Color.BLUE);
		lpanel2.setBounds((int)(width*0.01),(int)(width*0.01)+(int)(height*0.26),(int)(width*0.21),(int)(height*0.62));
		String ll[]=new String[VarList.bookmarks.size()];
		System.out.println(VarList.bookmarks.size());
		for(int i=0;i<VarList.bookmarks.size();i++){
			ll[i]=VarList.bookmarks.get(i);
		}
		list1=new JList<String>(ll);
		list1.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		jScrollPane1=new JScrollPane(list1);
		jScrollPane1.setBounds(0,0,(int)(width*0.21),(int)(height*0.62));
		lpanel2.setVisible(false);
		lpanel2.add(jScrollPane1);
		lpanel2.setLayout(null);
		add(lpanel2);
		
		//for history
		lpanel3=new JPanel();
		lpanel3.setBackground(Color.BLUE);
		lpanel3.setBounds((int)(width*0.01),(int)(width*0.01)+(int)(height*0.26),(int)(width*0.21),(int)(height*0.62));
		String l[]=new String[VarList.history.size()];
		System.out.println(VarList.history.size());
		for(int i=0;i<VarList.history.size();i++){
			l[i]=VarList.history.get(i);
		}
		list2=new JList<String>(l);
		list2.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		jScrollPane2=new JScrollPane(list2);	
		jScrollPane2.setBounds(0,0,(int)(width*0.21),(int)(height*0.62));
		lpanel3.setVisible(false);
		lpanel3.add(jScrollPane2);
		lpanel3.setLayout(null);
		add(lpanel3);
		
		//for main pane
		browser=new Browsers(this,(int)(width*0.25),(int)(width*0.03),(int)(width*0.69),(int)(height*0.8));
		browser.uRL("https://www.google.co.in");
	
		//for side pane
		jPanel=new JPanel();
		jPanel.setLayout(null);
		jPanel.setBackground(Color.MAGENTA);
		add(jPanel);
		jPanel.setBounds((int)(width*0.01),(int)(width*0.01),(int)(width*0.22),(int)(height*0.24));
		jPanel.addKeyListener(this);
		
		
		SwingUtilities.invokeLater(browser);
		
		JButton button2=new JButton("Bookmark");
		button2.setBounds((int)(width*0.01)+(int)(width*0.11),(int)(width*0.01),(int)(width*0.1),(int)(height*0.1));
		jPanel.add(button2);
		button2.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				words=V2w.startRecognition("show_bookmark");
				 checkTask();
			}
		});
		
		JButton button3=new JButton("History");
		button3.setBounds((int)(width*0.01),(int)(width*0.01)+(int)(height*0.12),(int)(width*0.1),(int)(height*0.1));
		jPanel.add(button3);
		button3.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				words=V2w.startRecognition("show_history");
				 checkTask();
			}
		});
		
		JButton button4=new JButton("Exit");
		button4.setBounds((int)(width*0.01)+(int)(width*0.11),(int)(width*0.01)+(int)(height*0.12),(int)(width*0.1),(int)(height*0.1));
		jPanel.add(button4);
		button4.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				words=V2w.startRecognition("close");
				 checkTask();
			}
		});
		
		JButton button=new JButton("SEARCH");
		button.setBounds((int)(width*0.01),(int)(width*0.01),(int)(width*0.1),(int)(height*0.1));
		jPanel.add(button);
		button.addMouseListener(new MouseListener() {
			
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
				// TODO Auto-generated method stub
				/*ArrayList<String> a=V2w.startLiveRecognition();
				for(int i=0;i<a.size();i++){
					System.out.print(a.get(i)+" ");
				}*/
				
				
				words=V2w.startRecognition("search");
			 checkTask();
				
			}
		});
		
		
		}
	
	private void checkTask(){
		
		if(words.contains("show")&&words.contains("history")){
			createL3();
    		lpanel1.setVisible(false);
    		lpanel2.setVisible(false);
    		lpanel3.setVisible(true);
    		list2.addMouseListener(this);
    		list2.addKeyListener(this);
    		listStatus=2;
		}
		else if(words.contains("show")&&words.contains("book")){
        	
			createL2();
    		lpanel1.setVisible(false);
    		lpanel2.setVisible(true);
    		lpanel3.setVisible(false);
    		list1.addMouseListener(this);
    		list1.addKeyListener(this);
    		listStatus=1;
    		
		}
		else if(words.contains("add")&&words.contains("book")){
        	VarList.bookmarks.add(browser.retURL());
		}
		else if(words.contains("move")&&words.contains("side")){
        	jPanel.setVisible(false);
        	if(browser2==null){
			browser2=new Browsers(BrowserWindow.this,(int)(width*0.01),(int)(width*0.01),(int)(width*0.22),(int)(height*0.24));
			browser2.uRL("http://"+"www.youtube.com");
			SwingUtilities.invokeLater(browser2);
        	}
        	else{browser2.add();}
		}
		else if(words.contains("search")){
        	browser.uRL("http://www.google.co.in");
    		SwingUtilities.invokeLater(browser);
		}
		else if(words.contains("exit")||words.contains("close")){
        	Browser.closeBrowser();
		}
		else if((words.contains("move")||words.contains("moved"))&&(words.contains("maine")||words.contains("maintain")||words.contains("main"))){
        	jPanel.setVisible(true);
        	browser2.remove();
			
		}
        else{
    		try
    		{
    		String voice="";
			for(int text=0;text<words.size();text++)
    		voice=voice.concat(words.get(text)+" ");
			
			browser.uRL("https://www.google.com/search?q="+voice);
			System.out.println("http://www.google.com/search?q="+voice);
    		SwingUtilities.invokeLater(browser);
    		VarList.history.add("http://www.google.com/search?q="+voice);
    		}
    		catch(Exception e){
    			System.err.println("Voice Error!!!"+e.getMessage());
    		}
    	} 
	}

	private void createL2() {
		
		if(lpanel2!=null)
			remove(lpanel2);
		lpanel2=new JPanel();
		lpanel2.setLayout(null);
		lpanel2.setBackground(Color.BLUE);
		lpanel2.setBounds((int)(width*0.01),(int)(width*0.01)+(int)(height*0.26),(int)(width*0.21),(int)(height*0.62));
		String ll[]=new String[VarList.bookmarks.size()];
		System.out.println(VarList.bookmarks.size());
		for(int i=0;i<VarList.bookmarks.size();i++){
			ll[i]=VarList.bookmarks.get(i);
		}
		list1=new JList<String>(ll);
		list1.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		jScrollPane1=new JScrollPane(list1);
		jScrollPane1.setBounds(0,0,(int)(width*0.21),(int)(height*0.62));
		lpanel2.setVisible(false);
		lpanel2.add(jScrollPane1);
		add(lpanel2);
	}

	private void createL3() {
		if(lpanel3!=null)
			remove(lpanel3);
		lpanel3=new JPanel();
		lpanel3.setLayout(null);
		lpanel3.setBackground(Color.BLUE);
		lpanel3.setBounds((int)(width*0.01),(int)(width*0.01)+(int)(height*0.26),(int)(width*0.21),(int)(height*0.62));
		String l[]=new String[VarList.history.size()];
		System.out.println(VarList.history.size());
		for(int i=0;i<VarList.history.size();i++){
			l[i]=VarList.history.get(i);
		}
		list2=new JList<String>(l);
		list2.setFont(new Font("Serif", Font.BOLD|Font.ROMAN_BASELINE, 18));
		jScrollPane2=new JScrollPane(list2);	
		jScrollPane2.setBounds(0,0,(int)(width*0.21),(int)(height*0.62));
		lpanel3.setVisible(false);
		lpanel3.add(jScrollPane2);
		add(lpanel3);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getModifiers()==InputEvent.CTRL_MASK)
		{
			 words=V2w.startLiveRecognition();
			for(int i=0;i<words.size();i++){
				System.out.print(words.get(i)+" ");
			}
            
			
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
		{
			lpanel1.setVisible(true);
    		lpanel2.setVisible(false);
    		lpanel3.setVisible(false);
    		list.addMouseListener(this);
    		list.addKeyListener(this);
    		listStatus=0;
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mStatus=list.getSelectedIndex();
		if(listStatus==0&&mStatus==1){
		     //search	
			 words=V2w.startRecognition("search");
			 checkTask();
    	
		}
		else if(listStatus==0&&mStatus==2){
			//bookmark
			 words=V2w.startRecognition("show_bookmark");
			 checkTask();
        	
		}
        else if(listStatus==0&&mStatus==3){
			//history
        	words=V2w.startRecognition("show_history");
        	checkTask();
		}
        
        else if(listStatus==0&&mStatus==4){
			//move_to_sidepane
        	words=V2w.startRecognition("move_to_side_pane");
        	checkTask();
		}
        else if(listStatus==0&&mStatus==5){
			//add bookmark
        	words=V2w.startRecognition("add_bookmark");
        	checkTask();
		}
        else if(listStatus==0&&mStatus==6){
        	words=V2w.startRecognition("move_to_main_pane");
        	checkTask();
		}
        else if(listStatus==0&&mStatus==7){
        	words=V2w.startRecognition("close");
        	checkTask();
		}
		
        if(listStatus==1){
        	/*String book="";
        	for(int i=0;i<VarList.bookmarks.size();i++){
        		book=book.concat(VarList.bookmarks.get(i));
        	}*/
        	if((VarList.bookmarks.size()-1)>=mStatus&&!VarList.bookmarks.isEmpty()){
        	browser.uRL(VarList.bookmarks.get(mStatus));
    		SwingUtilities.invokeLater(browser);
        	}
        }
        else if(listStatus==2){
        	/*String book="";
        	for(int i=0;i<VarList.history.size();i++){
        		book=book.concat(VarList.history.get(i));
        	}*/
        	if((VarList.history.size()-1)>=mStatus&&!VarList.history.isEmpty()){
        	browser.uRL(VarList.history.get(mStatus));
    		SwingUtilities.invokeLater(browser);
        	}
        }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}


 class Browsers implements Runnable {
    private WebEngine webEngine;

    JPanel frame;int height;int width,x,y;
        
    String string;JFXPanel jfxPanel;

    public Browsers(BrowserWindow browserWindow,int x,int y,int width,int height) {
		frame=browserWindow;
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
	}

	public void uRL(String string) {
		
		this.string=string;
	}

	public void add(){
		frame.add(jfxPanel);
	}
	public void remove(){
		frame.remove(jfxPanel);
	}
	public void loadURL(final String url) {
        Platform.runLater(() -> 
        {
            webEngine.load(url);
        });
    }
	public String retURL(){
		System.out.println(webEngine.getLocation());
		return webEngine.getLocation();
	}

    @Override
    public void run() {
    	if(jfxPanel==null){
        jfxPanel = new JFXPanel();
        
        jfxPanel.setBounds(x,y,width,height);
        frame.add(jfxPanel);
               Platform.runLater(() -> {
            WebView view = new WebView();
            webEngine = view.getEngine();
            
            jfxPanel.setScene((new Scene(view)));
        });

    	}
               loadURL(string);
    }
}