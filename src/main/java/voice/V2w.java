package voice;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class V2w {

	private static StreamSpeechRecognizer recognizer;
	private static LiveSpeechRecognizer liveRecognizer;
	
	public static void initialize(){
		
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		try{
		recognizer = new StreamSpeechRecognizer(configuration);
		liveRecognizer= new LiveSpeechRecognizer(configuration);
		}
		catch(Exception exception){
			System.out.println("bv"+exception);
		}
		/*
		 * System.out.println(".1."+result.getHypothesis());
		for (WordResult r : result.getWords()) {
            System.out.println(r);
        }
        System.out.println("Best 3 hypothesis:");
        for (String s : result.getNbest(3))
            System.out.println(s);
        */
	}
   public static ArrayList<String> startLiveRecognition(){
		
		ArrayList<String> words=new ArrayList<String>();
		liveRecognizer.startRecognition(true);
		SpeechResult result = liveRecognizer.getResult();
		String result1= result.getHypothesis() ;
		String[] resultWords=result1.split(" ");
		for(int i=0;i<resultWords.length;i++)
            words.add(resultWords[i].toLowerCase());   
           
		//stopRecognizer();
		liveRecognizer.stopRecognition();
		return words;
	}
	public static ArrayList<String> startRecognition(String filePath){
		
		ArrayList<String> words=new ArrayList<String>();
		
		InputStream stream = V2w.class
                .getResourceAsStream("/data/"+filePath+".wav");
		playVoice(filePath);
		recognizer.startRecognition(stream);
		System.out.println("file: "+filePath);
		SpeechResult result = recognizer.getResult();
		String result1= result.getHypothesis() ;
		String[] resultWords=result1.split(" ");
		
		for(int i=0;i<resultWords.length;i++){
            words.add(resultWords[i].toLowerCase());   
		System.out.print(resultWords[i]+" ");}
		stopRecognizer();
		return words;
	}
	
	private static void playVoice(String filePath) {
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(new File(V2w.class.getResource("/data/"+filePath+".wav").getPath())));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
		
	}
	public static void stopRecognizer(){
		recognizer.stopRecognition();
	}
}
