package voice;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Browser {

	static JFrame frame;
	public void start(){
		initializeView();
		
	}

	private void initializeView() {
		
		Toolkit toolkit=Toolkit.getDefaultToolkit();
		Dimension dimension=toolkit.getScreenSize();
		double height=dimension.height*0.92;
		double width=dimension.width*0.97;
		
		 frame=new JFrame("AVS-Browser");
		frame.setBounds(0,0,(int)width,(int)height);

		BrowserWindow window1=new BrowserWindow(height,width);
		BrowserPaneWindow window2=new BrowserPaneWindow(height,width);
		
		frame.add(window1);
		frame.add(window2);
		
		window1.setVisible(true);
		window2.setVisible(false);
		
		frame.setLayout(null);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
	}
	public static void closeBrowser(){
		frame.dispose();
	}
}
