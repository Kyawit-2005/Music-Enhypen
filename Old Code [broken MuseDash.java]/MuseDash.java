import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MuseDash extends JFrame implements KeyListener {
	public ImageIcon GameIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\KagamineRinLen Icon.png");
	public JPanel GameMenuUI, GameplayUI;
	public int ChosenTrack = 0, MusicBPM = 0;
	public JLabel[] UpperCharts, LowerCharts;
	public boolean isChartCharted = false, isAuto = true, isHoldingA = false, isHoldingL = false;
	public int Misses = 0;
	
	public MuseDash() {
		this.setTitle("Music Enhypen"); //lmao, Muse Dash
		this.setIconImage(GameIcon.getImage());
		this.setSize(1080, 720);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		this.addKeyListener(this);
		
		
		GameMenuUI = new JPanel();
		GameMenuUI.setBounds(0, 0, 1065, 680); //Exact Window Size
		GameMenuUI.setLayout(null);
		GameMenuUI.setOpaque(false);
		//==============================================================
		JButton ButtonMusicTrack1 = new JButton("Music 1: テレキャスタービーボーイ (feat. Kagamine Len)");
		ButtonMusicTrack1.setBounds(357, 200, 350, 35);
		ButtonMusicTrack1.setFocusable(false);
		ButtonMusicTrack1.setBorder(null);
		ButtonMusicTrack1.addActionListener(e -> {
			//Telecaster b boy [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Telecaster B Boy.wav"  ||  182 BPM
			//Conclusion: Delays after around sometime, making it off-rhythm [Update 2: it syncs, but now deteriorates on the further part of the song]
			//Conclusion 2: LMAO, Rounding Issues is da problem, LMAAAAOOOOOOOOOOOO. Now it syncs
			this.setTitle("Music Enhypen: Now playing \"テレキャスタービーボーイ [Feat. Kagamine Len]\"");
			ChosenTrack = 1; MusicBPM = 182;
			createChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack1);
		
		JButton ButtonMusicTrack2 = new JButton("Music 2: Bring it on (feat. Kagamine Len/Rin)");
		ButtonMusicTrack2.setBounds(357, 245, 350, 35);
		ButtonMusicTrack2.setFocusable(false);
		ButtonMusicTrack2.setBorder(null);
		ButtonMusicTrack2.addActionListener(e -> {
			//Bring it on [Featuring: Kagamine Len and Kagamine Rin]. Target File: "Game Assets/Audio Files/Muse Dash/[Rin Len] Bring it on.wav"  ||  160 BPM
			//Conclusion: The entire song is stable 160BPM
			this.setTitle("Music Enhypen: Now playing \"Bring it on [Feat. Kagamine Len/Rin]\"");
			ChosenTrack = 2; MusicBPM = 160;
			createChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack2);
		
		JButton ButtonMusicTrack3 = new JButton("Music 3: The Servant of Evil (feat. Kagamine Len)");
		ButtonMusicTrack3.setBounds(357, 290, 350, 35);
		ButtonMusicTrack3.setFocusable(false);
		ButtonMusicTrack3.setBorder(null);
		ButtonMusicTrack3.addActionListener(e -> {
			//The Servant of Evil [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Servant of Evil.wav"  ||  120 BPM
			this.setTitle("Music Enhypen: Now playing \"The Servant of Evil [feat. Kagamine Len]\"");
			ChosenTrack = 3; MusicBPM = 120*2;
			createChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack3);
		
		JButton GameInfo = new JButton("Info");
		GameInfo.setBounds(5, 5, 30, 30);
		GameInfo.setFocusable(false);
		GameInfo.setBorder(null);
		GameInfo.addActionListener(e -> {
			Main.GameInfo.showGameInfo("Muse Dash");
		}); GameMenuUI.add(GameInfo);
		//==============================================================
		this.add(GameMenuUI);
		
		
		GameplayUI = new JPanel();
		GameplayUI.setBounds(0, 0, 1065, 680); //Exact Window Size
		GameplayUI.setLayout(null);
		GameplayUI.setOpaque(false);
		GameplayUI.setVisible(false); //Hidden by default
		//==============================================================
		ImageIcon HitMark = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\HitPoint.png");
		HitMark = new ImageIcon(HitMark.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH));
		
		JLabel UpperHitMark = new JLabel(HitMark);
		UpperHitMark.setBounds(200, 177, 75, 75);
		GameplayUI.add(UpperHitMark);
		
		JLabel LowerHitMark = new JLabel(HitMark);
		LowerHitMark.setBounds(200, 680-177-75, 75, 75);
		GameplayUI.add(LowerHitMark);
		
		
		//==============================================================
		this.add(GameplayUI);
		
		
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
	}
	
//Keyboard Controls
	public void keyTyped(KeyEvent key) {
	
	}
	
	public void keyPressed(KeyEvent key) {
		int MarginOfError = 60;
		//System.out.print("[Pressed key: "+key.getKeyChar()+"] ");
		
		try{
			switch(key.getKeyChar()) {
				case 'A' :
				case 'a' :
					if(!isHoldingA) {
						isHoldingA = true;
						if(Main.MD_process.CurrentUpperEnemy.getX() > 200-MarginOfError && Main.MD_process.CurrentUpperEnemy.getX() < 200+MarginOfError) {
							System.out.println("Air Enemy killed!");
							Main.MD_process.CurrentUpperEnemy.setVisible(false);
							Main.MD_process.CurrentUpperTarget++;
							Main.SFX[0].playSFX();
						} else {
							System.out.println("Air MISS!!!");
							//Misses++;
						}
					}
					break;
					
				case 'L' :
				case 'l' :
					if(!isHoldingL) {
						isHoldingL = true;
						if(Main.MD_process.CurrentLowerEnemy.getX() > 200-MarginOfError && Main.MD_process.CurrentLowerEnemy.getX() < 200+MarginOfError) {
							System.out.println("Ground Enemy killed!");
							Main.MD_process.CurrentLowerEnemy.setVisible(false);
							Main.MD_process.CurrentLowerTarget++;
							Main.SFX[0].playSFX();
						} else {
							System.out.println("Ground MISS!!!");
							//Misses++;
						}
					}
					break;
					
				default: System.out.println("Unregistered");
			}
		} catch(NullPointerException err) {
			System.out.println("[NullPointerException] THE GAME HASN'T STARTED YET!!!");
			Main.timer.getTime();
		}
	}
	
	public void keyReleased(KeyEvent key) {
		switch(key.getKeyChar()) {
			case 'A' :
			case 'a' :
				isHoldingA = false; break;
			case 'L' :
			case 'l' :
				isHoldingL = false; break;
			default: //nothing
		}
	}
	
//Misc
	public void createChart() {
		UpperCharts = MuseDash_LevelCharts.getUpperChart(ChosenTrack);
		for(int n = 0; n < UpperCharts.length; n++) {
			GameplayUI.add(UpperCharts[n]);
		}
		LowerCharts = MuseDash_LevelCharts.getLowerChart(ChosenTrack);
		for(int n = 0; n < LowerCharts.length; n++) {
			GameplayUI.add(LowerCharts[n]);
		};
		
		isChartCharted = true;
		Main.chartMusic[ChosenTrack-1].play();
	}
	
	public void clearChart() {
		try{
			for(int n = 0; n < UpperCharts.length; n++) {
				GameplayUI.remove(UpperCharts[n]);
			}
			for(int n = 0; n < LowerCharts.length; n++) {
				GameplayUI.remove(LowerCharts[n]);
			}
		} catch(NullPointerException err) {
			System.out.println("[NullPointerException] The game tried to clear chart, but \"UpperCharts\" or \"LowerChart\" is NULL");
		}
	}

	public MD_Thread getThread() {
		MD_Thread here = new MD_Thread();
		return here;
	}
	
	public Stopwatch getThread_SW() {
		Stopwatch here = new Stopwatch();
		return here;
	}
	
	public MoveChart getThread_MC() {
		MoveChart here = new MoveChart();
		return here;
	}
}







//Thread name: "[MuseDash.java] MuseDash BackGround Process"
class MD_Thread extends Thread {
	JFrame MD = Main.Processes[4]; //"this" JFrame
	JLabel[] UpperChart = {new JLabel()}, LowerChart = {new JLabel()};
	JLabel CurrentUpperEnemy, CurrentLowerEnemy;
	int CurrentUpperTarget = 0, CurrentLowerTarget = 0;
	boolean doMove = false;
	
	//Each second = 1000ms, so if it triggers every 'a' milliseconds, then it triggers 1000/a Times to reach 1 second.			[In this case: 1000/10 = 100 times]
	//Every trigger moves enemies by 'b' Pixels left. For 1 Second, (1000/a)*b Pixels will be travelled every second					[In this case: (1000/10)*10 = 1000 pixels/sec]
	//For the BPM Calculation, if there are 'n' Beats per minute, then a single beat happens every 60/n seconds						[In this case: 60/182 = 0.3296... sec/beat]
	//As for each pixels must it travel every beat, that would be... (60/n)*(1000/a)*b Pixels per beat...											[In this case: (60/182)*(1000/10)*10 = 329.67032... px/beat]
	int Delay_ms = 5, PixelsTravelled = 5;
	
	public void run() {
		System.out.println("Process MuseDash started");

		while(true) {
			try{Thread.sleep(10);} catch(Exception e) {}; //Keeps this thread running
			
			if(MD.isVisible()) {
				System.out.println("You opened MuseDash");
				Main.MD.GameMenuUI.setVisible(true);
				Main.MD.GameplayUI.setVisible(false);
				Main.MD.Misses = 0;
				
				Main.MapGUI.inputEnable(false);
				Main.Processes[3].setDefaultCloseOperation(0);
				
				while(MD.isVisible()) { //Traps the process here while MuseDash JFrame is visible
					try{Thread.sleep(0);} catch(Exception e) {}; //Temporary
					//System.out.println("[DEBUG] is \"Muse Dash\" open? "+MD.isVisible());
					
					if(doMove) {
						Main.moveChart.setCharts();
						
						if(Main.MD.isChartCharted) {
							UpperChart = Main.MD.UpperCharts;
							LowerChart = Main.MD.LowerCharts;
							
							if(Main.MD.isAuto) {
								if(UpperChart[CurrentUpperTarget].getX() < 200) {
									UpperChart[CurrentUpperTarget].setVisible(false);
									CurrentUpperTarget++;
									System.out.println("[Auto] Air Enemy Killed");
									
									Main.SFX[0].playSFX();
								}
							} else if(UpperChart[CurrentUpperTarget].getX() < 200-75) {
								//UpperChart[CurrentUpperTarget].setVisible(false);
								CurrentUpperTarget++;
								System.out.println("MISS!!!");
								//Main.MD.Misses++;
							}; CurrentUpperEnemy = UpperChart[CurrentUpperTarget];
						
						
							if(Main.MD.isAuto) {
								if(LowerChart[CurrentLowerTarget].getX() < 200) {
									LowerChart[CurrentLowerTarget].setVisible(false);
									CurrentLowerTarget++;
									System.out.println("[Auto] Ground Enemy Killed");
									
									Main.SFX[0].playSFX();
								}
							} else if(LowerChart[CurrentLowerTarget].getX() < 200-75) {
								//LowerChart[CurrentLowerTarget].setVisible(false);
								CurrentLowerTarget++;
								System.out.println("MISS!!!");
								//Main.MD.Misses++;
							}; CurrentLowerEnemy = LowerChart[CurrentLowerTarget];
							
						}
					}
					
					if(Main.MD.Misses >= 3) {
						for(MusicManager stopper : Main.chartMusic) {
							stopper.stop();
						}
						JOptionPane.showMessageDialog(null, "You missed 3 Times!\nGame Over!", "Test Dialog", 0);
						Main.MD.setVisible(false);
						Main.MD.Misses = 0;
						break;
					}
				}
				System.out.println("You exited the MuseDash");
				CurrentUpperTarget = 0; CurrentLowerTarget = 0; //reset
				Main.MD.isChartCharted = false;
				Main.MD.clearChart();
				Main.MD.setTitle("Music Enhypen");
				for(MusicManager stopper : Main.chartMusic) {
					stopper.stop();
				}
				Main.MapGUI.inputEnable(true);
				Main.Processes[3].setDefaultCloseOperation(2);
			}
		}
	}
}

class Stopwatch extends Thread {
	long current_MS = System.currentTimeMillis(), new_MS, timeDif = 0;
	int addTimer = 0, addTimer2 = 0;
	public void run() {
		while(true) {
			try{Thread.sleep(1);} catch(Exception e) {};
			new_MS = System.currentTimeMillis();
			
			getTime();
			addTimer += timeDif;
			if(addTimer >= Main.MD_process.Delay_ms) {
				//System.out.println("Triggered Timer: "+addTimer);
				Main.MD_process.doMove = true;
				addTimer -= Main.MD_process.Delay_ms;
			}
		}
	}
	
	public void getTime() {
		timeDif = new_MS-current_MS;
		//System.out.println("[Stopwatch] Timer: "+timeDif+" ms.");
		current_MS = new_MS;
	}
}

class MoveChart extends Thread {
	JLabel[] UpperChart;
	JLabel[] LowerChart;
	public void run() {
		while(true) {
			try{Thread.sleep(0);} catch(Exception e) {};
			if(Main.MD_process.doMove && Main.MD.isChartCharted) {
				//System.out.println("");
				for(JLabel enemy : UpperChart) {
					enemy.setLocation(enemy.getX()-Main.MD_process.PixelsTravelled, enemy.getY());
				}
				for(JLabel enemy : LowerChart) {
					enemy.setLocation(enemy.getX()-Main.MD_process.PixelsTravelled, enemy.getY());
				}
				Main.MD_process.doMove = false;
			}
		}
	}
	
	public void setCharts() {
		UpperChart = Main.MD_process.UpperChart;
		LowerChart = Main.MD_process.LowerChart;
	}
}