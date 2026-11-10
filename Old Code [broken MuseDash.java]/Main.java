import javax.swing.*;
import java.awt.*;

public class Main {
	static JFrame Loading = new JFrame(), MainFrame = new JFrame();
	static JFrame[] Processes;
	static Gameplay1 MapGUI;
	static GameHowToPlay GameInfo;
	static MuseDash MD = new MuseDash();
	
	static Thread[] threads;
	static KillAll die;
	static MD_Thread MD_process;
	static Stopwatch timer;
	static MoveChart moveChart;
	
	static MusicManager[] chartMusic;
	static MusicManager[] SFX;
	
	public static void main(String[] args) {
		Loading.setSize(200, 200);
		Loading.setResizable(false);
		Loading.setLayout(null);
		Loading.setDefaultCloseOperation(0);
		Loading.setLocationRelativeTo(null);
		
		System.out.println("Loading Application. Please wait...");
		MapGUI = new Gameplay1();
		GameInfo = new GameHowToPlay();
		Processes = new JFrame[]{Loading, MainFrame, Story1.MainFrame, MapGUI, MD, GameInfo};
		
		die = new KillAll();
		MD_process = MD.getThread();
		timer = MD.getThread_SW();
		moveChart = MD.getThread_MC();
		threads = new Thread[]{die, MD_process, timer, moveChart};
		
		chartMusic = new MusicManager[]{new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Len] Telecaster B Boy.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Rin Len] Bring it on.wav"),
																							new MusicManager("Game Assets\\Audio Files\\Muse Dash\\[Len] Servant of Evil.wav")};
		SFX = new MusicManager[]{new MusicManager("Game Assets\\Audio Files\\Muse Dash\\HIT.wav"),
																		new MusicManager("Game Assets\\Audio Files\\Talk.wav")};
//===========================
		//MainScreen();
		//Story1.StoryFrame();
		Processes[3].setVisible(true);
		//Processes[4].setVisible(true);
		
//===========================
		threads[0].setName("[Main.java] BackGround Processes Checker");
		threads[0].setPriority(1);
		threads[0].start();
		
		threads[1].setName("[MuseDash.java] MuseDash BackGround Process");
		threads[1].setPriority(10);
		threads[1].start();
		
		threads[2].setName("[MuseDash.java] Stopwatch Process");
		threads[2].setPriority(10);
		threads[2].start();
		
		threads[3].setName("[MuseDash.java] MoveChart Process");
		threads[3].setPriority(10);
		threads[3].start();
	}
	
	public static void MainScreen() {
		MainFrame.setTitle("Welcome to my Game! by yours truly, \"Kyle Jayuma\"");
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
			Story1.StoryFrame();
			MainFrame.dispose();
		});
		MainFrame.add(PlayGame);
		
		MainFrame.setDefaultCloseOperation(2);
		MainFrame.setLocationRelativeTo(null);
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