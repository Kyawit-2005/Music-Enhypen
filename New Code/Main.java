import javax.swing.*;
import java.awt.*;

public class Main {
	//JFrames
	static JFrame Loading = new JFrame(), MainFrame = new JFrame();
	static JFrame[] Processes;
	static Story1 story = new Story1();
	static Gameplay1 MapGUI;
	static GameHowToPlay GameInfo;
	static MuseDash MD = new MuseDash();
	
	//Threads
	static Thread[] threads;
	static KillAll die;
	static MuseDash_UpTime MD_UpTime;
	
	//MusicManager
	static MusicManager[] chartMusic;
	static MusicManager[] SFX;
	
	//Runnables
	static ChartMove MD_Move = MD.getThread2();
	static ChartChecker chartCheck = MD.getThread3();
	
	public static void main(String[] args) {
		Loading.setSize(200, 200);
		Loading.setResizable(false);
		Loading.setLayout(null);
		Loading.setDefaultCloseOperation(0);
		Loading.setLocationRelativeTo(null);
		
		System.out.println("Loading Application. Please wait...");
		
		//ARRAYS OF SOUND ASSETS
		chartMusic = new MusicManager[]{new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Len] Telecaster B Boy.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Rin Len] Bring it on.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Len] Servant of Evil.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Len] Paradichlorobenzene.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\Chipflake Intro 8-Bit.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\Chattermax.wav")};
		SFX = new MusicManager[]{new MusicManager("Game Assets\\Audio Files\\Muse Dash\\HIT.wav"),
																		new MusicManager("Game Assets\\Audio Files\\Talk.wav")};
		
		//AN ARRAY OF JFRAMES
		MapGUI = new Gameplay1();
		GameInfo = new GameHowToPlay();
		Processes = new JFrame[]{Loading, MainFrame, story, MapGUI, MD, GameInfo};
		
		//AN ARRAY OF THREADS
		die = new KillAll();
		MD_UpTime = MD.getThread1();
		threads = new Thread[]{die, MD_UpTime};
		
		JOptionPane.showMessageDialog(null, "DISCLAIMER:\n"+
																									"This game is HEAVILY UNFINISHED due to false deadline alarm and changes.\n\n"+
																									"Here's all the feature that are SUPPOSED TO BE ADDED, but not implemented, anyways:\n"+
																									" - Saving System [everything java.io]\n"+
																									" - \"Chattermax\" music chart for the 2nd Rhythm Minigame\n"+
																									" - \"Chipflake Intro 8-Bit\" Easy and Normal music chart for the 1st Rhythm Minigame\n"+
																									" - Tutorial Phase for the Rhythm Minigame [Just open the \"info\" on \"Music Enhypen: Free-Play\"\n"+
																									" - Dialogues after the 1st Rhythm Minigame, and the 2nd Rhythm Minigame\n"+
																									" - Proper UI for the Title Screen, and the \"Music Enhypen\" Screen\n"+
																									" - Moving BG for the \"Music Enhypen\" [There was an attempt, but couldn't optimize]\n"+
																									" - Cutscenes, and the map near the entrance of the map\n"+
																									" - Others that I failed to mention\n\n"+
																									"Also note that the Rhythm Minigame is buggy sometimes.\n"+
																									"I am terribly sorry for this. I hope this project is still acceptable, huhu"
																									, "Your game will load/launch upon closing this dialog [I'm so sorry...]", 0);
//===========================
		MainScreen();
		//Story1.StoryFrame();
		//Processes[3].setVisible(true);
		//Processes[4].setVisible(true);
		
//===========================
		threads[0].setName("[Main.java] BackGround Processes Checker");
		threads[0].setPriority(1);
		threads[0].start();
		
		threads[1].setName("[MuseDash.java] UpTime Checker");
		threads[1].setPriority(1);
		threads[1].start();
	}
	
	public static void Runnable1_Run() {
		Thread here = new Thread(MD_Move);
		//here.setPriority(10);
		here.start();
	}
	
	public static void Runnable2_Run(String Location) {
		chartCheck.loc = Location;
		Thread here = new Thread(chartCheck);
		//here.setPriority(1);
		here.start();
	}
	
	
	
	
	
	
	
	public static void MainScreen() {
		MainFrame.setTitle("Music Game");
		MainFrame.setSize(1080, 720); //Window Actual res: 1040px - 680px
		MainFrame.setLayout(null);
		MainFrame.setResizable(false);
		MainFrame.getContentPane().setBackground(Color.black);
		
		JLabel GameLogo = new JLabel(new ImageIcon("Game Assets\\Image Files\\GameLogo.png"));
		GameLogo.setBounds(80, 40, 900, 150);
		MainFrame.add(GameLogo);
		
		JButton PlayGame = new JButton("PLAY!");
		PlayGame.setBounds(430, 300, 200, 50);
		PlayGame.setFocusable(false);
		PlayGame.addActionListener(e -> {
			story.User_Name = MapGUI.User_Name = JOptionPane.showInputDialog(null, "Please enter your name:", "I am a Failure", 3);
			if(story.User_Name != null) {
				if(story.User_Name.equals("")) {story.User_Name = "The Nameless";}
				
				story.setVisible(true);
				MainFrame.setVisible(false);
			}
		});
		MainFrame.add(PlayGame);
		
		JButton FreePlay = new JButton("Music Enhypen: Free-Play");
		FreePlay.setBounds(430, 360, 200, 50);
		FreePlay.setFocusable(false);
		FreePlay.addActionListener(e -> {
			MD.setVisible(true);
		});
		MainFrame.add(FreePlay);
		
		JButton Credits = new JButton("Credits");
		Credits.setBounds(430, 420, 200, 50);
		Credits.setFocusable(false);
		Credits.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "<html><font color='red'>ALMOST ALL ASSETS USED IN THIS PROGRAM ARE STOLEN FROM OTHER GAMES.</font><br><br>"+
																									"Credits to the following sources:<br>"+
																									" - Standing Images: <font color='purple'>\"Normal Me and Abnormal Friends\"</font>, a Mobile Game<br>"+
																									" - MAP layouts and the items: <font color='purple'>Manually made in \"Geometry Dash\"</font><br>"+
																									" - Whole \"Music Enhypen\" idea: <font color='purple'>\"Muse Dash\"</font><br><br>"+
																									"Music used are:<br>"+
																									" - Music 1: <font color='orange'>\"Telecaster B Boy\" [feat. Lagamine Len]</font><br>"+
																									" - Audio: <font color='blue'>https://www.youtube.com/watch?v=i-DZukWFR64</font> <br>"+
																									" - Music Pic: <font color='blue'>https://musedash.peropero.net/#/art/covers</font> under 【Rin·Len's Mirrorland】<br><br>"+
																									" - Music 2: <font color='orange'>\"Bring it on!\" [feat. Kagamine Len & Kagamine Rin]</font><br>"+
																									" - Audio: <font color='blue'>https://www.youtube.com/watch?v=oEkGC2HV7rc</font> <br>"+
																									" - Music Pic: <font color='blue'>https://musedash.peropero.net/#/art/covers</font> under 【Rin·Len's Mirrorland】<br><br>"+
																									" - Music 3: <font color='orange'>\"The Servant of Evil\" [feat. Kagamine Len, Project Sekai version]</font><br>"+
																									" - Audio: <font color='blue'>https://projectsekai.fandom.com/wiki/Aku_no_Meshitsukai</font> <br>"+
																									" - Music Pic: <font color='blue'>https://projectsekai.fandom.com/wiki/Aku_no_Meshitsukai</font> <br><br>"+
																									" - Music 4: <font color='orange'>\"Paradichlorobenzene\" [feat. Kagamine Len (and Rin, I guess), Project Sekai version]</font><br>"+
																									" - Audio: <font color='blue'>https://projectsekai.fandom.com/wiki/Paradichlorobenzene</font> <br>"+
																									" - Music Pic: <font color='blue'>https://projectsekai.fandom.com/wiki/Paradichlorobenzene</font> <br><br>"+
																									" - Music 5: <font color='green'>\"Chipflake Intro 8-Bit\" [from \"Bluey\"]</font><br>"+
																									" - Audio: <font color='blue'>https://www.youtube.com/watch?v=6H256sYmwUc</font> <br><br>"+
																									" - Music 6: <font color='green'>\"Chattermax\" [from \"Bluey Album\"]</font><br>"+
																									" - Audio: <font color='blue'>https://www.youtube.com/watch?v=JBIa5ZTV7Eo</font> <br><br><br>"+
																									"You can see these and pick the links at \"Main.java\".", "Credits where it is due to all 'Stolen' assets", 2);
		});
		MainFrame.add(Credits);
		
		MainFrame.setDefaultCloseOperation(2);
		MainFrame.setLocationRelativeTo(null);
		MainFrame.setResizable(false);
		MainFrame.setVisible(true);
	}
	
	public static void isLoading(boolean isVisible) {
		if(isVisible) {
			Loading.setVisible(true);
		} else Loading.dispose();
	}
}

class KillAll extends Thread {
	JFrame[] Processes = Main.Processes;
	public void run() {
		while(true) {
			boolean isFramesAllKilled = false;
			for(JFrame bool : Processes) {isFramesAllKilled = isFramesAllKilled || bool.isVisible();}
			//System.out.println("All JFrame isVisible? "+isFramesAllKilled);
			if(!isFramesAllKilled) {
				System.out.println("Program Terminated.");
				System.exit(0);
			}; try{Thread.sleep(100);} catch(Exception e) {};
		}
	}
}