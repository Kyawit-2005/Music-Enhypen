import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MuseDash extends JFrame implements KeyListener {
	public ImageIcon GameIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\KagamineRinLen Icon.png"),
											TrackIcon;
	public JPanel GameMenuUI, GameplayUI;
	public JLabel BG1 = new JLabel(), BG2 = new JLabel();
	public int ChosenTrack = 0, MusicBPM = 0;
	public int Delay_ms = 5, PixelsTravelled = 5, PixelsMoved = 0;
	public short LastUpperCount = 15, LastLowerCount = 15;
	public JLabel[] UpperCharts, LowerCharts;
	public JLabel[] RenderedUpperCharts = new JLabel[15], RenderedLowerCharts = new JLabel[15];
	public boolean isAuto = false, isHoldingA = false, isHoldingL = false, isChartMoving = false;
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
		JLabel TrackImageIcon = new JLabel();
		TrackImageIcon.setBounds(1060-250, 5, 250, 250);
		GameMenuUI.add(TrackImageIcon);
		
		
		
		//Remind me, myself, and I, that I need to simplify the shit out of this mess, to simplify with a "for" loop
		JButton ButtonMusicTrack1 = new JButton("Music 1: テレキャスタービーボーイ (feat. Kagamine Len)");
		ButtonMusicTrack1.setBounds(357, 200, 350, 35);
		ButtonMusicTrack1.setFocusable(false);
		ButtonMusicTrack1.setBorder(null);
		ButtonMusicTrack1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent mouse) {
				TrackIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\Telecaster b boy.png");
				TrackIcon = new ImageIcon(TrackIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
				TrackImageIcon.setIcon(TrackIcon);
			}
			
			public void mouseExited(MouseEvent mouse) {
				TrackImageIcon.setIcon(null);
			}
		});
		ButtonMusicTrack1.addActionListener(e -> {
			//Telecaster b boy [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Telecaster B Boy.wav"  ||  182 BPM
			this.setTitle("Music Enhypen: Now playing \"テレキャスタービーボーイ [Feat. Kagamine Len]\"");
			ChosenTrack = 1;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack1);
		
		
		
		JButton ButtonMusicTrack2 = new JButton("Music 2: Bring it on (feat. Kagamine Len/Rin)");
		ButtonMusicTrack2.setBounds(357, 245, 350, 35);
		ButtonMusicTrack2.setFocusable(false);
		ButtonMusicTrack2.setBorder(null);
		ButtonMusicTrack2.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent mouse) {
				TrackIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\Bring it on.png");
				TrackIcon = new ImageIcon(TrackIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
				TrackImageIcon.setIcon(TrackIcon);
			}
			
			public void mouseExited(MouseEvent mouse) {
				TrackImageIcon.setIcon(null);
			}
		});
		ButtonMusicTrack2.addActionListener(e -> {
			//Bring it on [Featuring: Kagamine Len and Kagamine Rin]. Target File: "Game Assets/Audio Files/Muse Dash/[Rin Len] Bring it on.wav"  ||  160 BPM
			this.setTitle("Music Enhypen: Now playing \"Bring it on [Feat. Kagamine Len/Rin]\"");
			ChosenTrack = 2;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack2);
		
		
		
		JButton ButtonMusicTrack3 = new JButton("Music 3: The Servant of Evil (feat. Kagamine Len)");
		ButtonMusicTrack3.setBounds(357, 290, 350, 35);
		ButtonMusicTrack3.setFocusable(false);
		ButtonMusicTrack3.setBorder(null);
		ButtonMusicTrack3.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent mouse) {
				TrackIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\The Servant of Evil.png");
				TrackIcon = new ImageIcon(TrackIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
				TrackImageIcon.setIcon(TrackIcon);
			}
			
			public void mouseExited(MouseEvent mouse) {
				TrackImageIcon.setIcon(null);
			}
		});
		ButtonMusicTrack3.addActionListener(e -> {
			//The Servant of Evil [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Servant of Evil.wav"  ||  120 BPM
			this.setTitle("Music Enhypen: Now playing \"The Servant of Evil [feat. Kagamine Len]\"");
			ChosenTrack = 3;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack3);
		
		
		
		JButton ButtonMusicTrack4 = new JButton("Music 4: Paradichlorobenzene (feat. Kagamine Len)");
		ButtonMusicTrack4.setBounds(357, 335, 350, 35);
		ButtonMusicTrack4.setFocusable(false);
		ButtonMusicTrack4.setBorder(null);
		ButtonMusicTrack4.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent mouse) {
				TrackIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\Paradichlorobenzene.png");
				TrackIcon = new ImageIcon(TrackIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
				TrackImageIcon.setIcon(TrackIcon);
			}
			
			public void mouseExited(MouseEvent mouse) {
				TrackImageIcon.setIcon(null);
			}
		});
		ButtonMusicTrack4.addActionListener(e -> {
			//Paradichlorobenzene [Featuring: Kagamine Len]. Target File: "Game Assets/Audio Files/Muse Dash/[Len] Paradichlorobenzene.wav"  ||  250 BPM
			this.setTitle("Music Enhypen: Now playing \"Paradichlorobenzene [feat. Kagamine Len]\"");
			ChosenTrack = 4;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack4);
		
		
		
		JButton ButtonMusicTrack5 = new JButton("Music 5: Chipflake Intro 8-Bit (BLUEY)");
		ButtonMusicTrack5.setBounds(357, 380, 350, 35);
		ButtonMusicTrack5.setFocusable(false);
		ButtonMusicTrack5.setBorder(null);
		/*ButtonMusicTrack5.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent mouse) {
				TrackIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\Paradichlorobenzene.png");
				TrackIcon = new ImageIcon(TrackIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
				TrackImageIcon.setIcon(TrackIcon);
			}
			
			public void mouseExited(MouseEvent mouse) {
				TrackImageIcon.setIcon(null);
			}
		}); */
		ButtonMusicTrack5.addActionListener(e -> {
			//Chipflake Intro 8-Bit [From BLUEY]. Target File: "Game Assets/Audio Files/Muse Dash/Chipflake Intro 8-Bit.wav"  ||  170 BPM
			this.setTitle("Music Enhypen: Now playing \"Chipflake Intro 8-Bit [Bluey]\"");
			ChosenTrack = 5;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack5);
		
		
		
		JButton ButtonMusicTrack6 = new JButton("Music 6: Chattermax (BLUEY)");
		ButtonMusicTrack6.setBounds(357, 425, 350, 35);
		ButtonMusicTrack6.setFocusable(false);
		ButtonMusicTrack6.setBorder(null);
		/* ButtonMusicTrack6.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent mouse) {
				TrackIcon = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\Paradichlorobenzene.png");
				TrackIcon = new ImageIcon(TrackIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH));
				TrackImageIcon.setIcon(TrackIcon);
			}
			
			public void mouseExited(MouseEvent mouse) {
				TrackImageIcon.setIcon(null);
			}
		}); */
		ButtonMusicTrack6.addActionListener(e -> {
			//Chattermax [From BLUEY]. Target File: "Game Assets/Audio Files/Muse Dash/Chattermax.wav"  ||  180 BPM
			this.setTitle("Music Enhypen: Now playing \"Chattermax [Bluey]\"");
			ChosenTrack = 6;
			createInitialChart();
			GameMenuUI.setVisible(false);
			GameplayUI.setVisible(true);
		}); GameMenuUI.add(ButtonMusicTrack6);
		
		
		
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
		this.setResizable(false);
	}
	
	
	
	
//Charting System
//==============================================================================
//==============================================================================
	public void createInitialChart() { //Initial Charting. Still loads slow, AF
		int InitialBeatsCount = 15;
	
		switch(ChosenTrack) {
			case 1: MusicBPM = 182; break;			//Telecaster B Boy
			case 2: MusicBPM = 160; break;			//Bring it on!
			case 3: MusicBPM = 120*2; break;	//The Servant of Evil
			case 4: MusicBPM = 250; break;		//Paradichlorobenzene
			case 5: MusicBPM = 170; break;			//Chipflake Intro 8-Bit
			case 6: MusicBPM = 180; break;			//Chattermax
			default: //Nothing
		}
		
		UpperCharts = MuseDash_LevelCharts.getUpperChart(ChosenTrack);
		LowerCharts = MuseDash_LevelCharts.getLowerChart(ChosenTrack);
		
		//Initially render only "InitialBeatsCount" Beats at a time
		for(int beat = 0; beat < InitialBeatsCount; beat++) {
			try{
				RenderedUpperCharts[beat] = UpperCharts[beat];
				GameplayUI.add(RenderedUpperCharts[beat]);
			} catch(IndexOutOfBoundsException err) {
				break;
			}
		}
		
		for(int beat = 0; beat < InitialBeatsCount; beat++) {
			try{
				RenderedLowerCharts[beat] = LowerCharts[beat];
				GameplayUI.add(RenderedLowerCharts[beat]);
			} catch(IndexOutOfBoundsException err) {
				break;
			}
		}
		
		//Main.chartMusic[ChosenTrack-1].play();
		isChartMoving = true;
		Main.Runnable1_Run();
		//Main.Runnable3_Run();
	}
	
	public void updateChart(String Pos) { //Updates the charts so that when the leftmost enemy is dead/miss, then adds another one to the rightmost
	JLabel[] prev;
	int n = 0, m=0;
		switch(Pos) {
			case "Upper":
				//try{System.out.println("[DEBUG: Air Enemy Hit! ["+(RenderedUpperCharts[0].getX()-200)+"px]");} catch(Exception e) {};
				prev = RenderedUpperCharts;
				RenderedUpperCharts[0].setVisible(false);
				for(n = 0; n < 14; n++) {
					try{RenderedUpperCharts[n] = prev[n+1]; m++;}
					catch(NullPointerException err) {RenderedUpperCharts[n] = null; m = n; break;}
				} if(m >= 14) {
					try{
						RenderedUpperCharts[14] = UpperCharts[LastUpperCount]; LastUpperCount++;
						RenderedUpperCharts[14].setLocation(RenderedUpperCharts[14].getX()-PixelsMoved, RenderedUpperCharts[14].getY());
						RenderedUpperCharts[14].setVisible(true);
						GameplayUI.add(RenderedUpperCharts[14]);
					} catch(Exception e) {RenderedUpperCharts[14] = null;}
					
				} break;
				
			case "Lower":
				//try{System.out.println("[DEBUG: Ground Enemy Hit! ["+(RenderedLowerCharts[0].getX()-200)+"px]");} catch(Exception e) {};
				prev = RenderedLowerCharts;
				RenderedLowerCharts[0].setVisible(false);
				for(n = 0; n < 14; n++) {
					try{RenderedLowerCharts[n] = prev[n+1]; m++;}
					catch(NullPointerException err) {RenderedLowerCharts[n] = null; m = n; break;}
				} if(m >= 14) {
					try{
						RenderedLowerCharts[14] = LowerCharts[LastLowerCount]; LastLowerCount++;
						RenderedLowerCharts[14].setLocation(RenderedLowerCharts[14].getX()-PixelsMoved, RenderedLowerCharts[14].getY());
						RenderedLowerCharts[14].setVisible(true);
						GameplayUI.add(RenderedLowerCharts[14]);
					} catch(Exception e) {RenderedLowerCharts[14] = null;}
					
				} break;
				
			default: //NONE
		}
	}
	
	public void clearChart() {
		
		for(int n = 0; n < 15; n++) {
			try{GameplayUI.remove(RenderedUpperCharts[n]);}
			catch(NullPointerException err) {break;}
		}
		for(int n = 0; n < 15; n++) {
			try{GameplayUI.remove(RenderedLowerCharts[n]);}
			catch(NullPointerException err) {break;}
		}
	}
	
	
	
	
//Keyboard Controls
//==============================================================================
//==============================================================================
	public void keyTyped(KeyEvent key) {
	
	}
	
	public void keyPressed(KeyEvent key) {
		int MarginOfError = 60;
		//System.out.print("[Pressed key: "+key.getKeyChar()+"] ");
		
		try{
			switch(key.getKeyCode()) {
				case 65: //case 83:
					if(!isHoldingA) {
						isHoldingA = true;
						//if condition: Let 'x' be the time window in pixels. 140px<x<260px
						//else consition: Fails, then counts total misses
						//AIR ENEMIES
						//updateChart("Upper");
						Main.Runnable2_Run("Upper");
					}
					break;
					
				case 76: //case 75:
					if(!isHoldingL) {
						isHoldingL = true;
						//if condition: Let 'x' be the time window in pixels. 140px<x<260px
						//else consition: Fails, then counts total misses
						//GROUND ENEMIES
						//updateChart("Lower");
						Main.Runnable2_Run("Lower");
					}
					break;
					
				case 27: //Presses "Esc", closes the "MuseDash" window
					this.setVisible(false);
					break;
				
				default: System.out.println("Unregistered");
			}
		} catch(NullPointerException err) {
			System.out.println("[NullPointerException] THE GAME HASN'T STARTED YET!!!");
		}
	}
	
	public void keyReleased(KeyEvent key) {
		switch(key.getKeyCode()) {
			case 65: //case 83:
				isHoldingA = false; break;
			case 76: //case 75:
				isHoldingL = false; break;
			default: //nothing
		}
	}
	
//Threads Getter
//==============================================================================
//==============================================================================
	
	public MuseDash_UpTime getThread1() {
		MuseDash_UpTime here = new MuseDash_UpTime();
		return here;
	}
	
	public ChartMove getThread2() {
		ChartMove here = new ChartMove();
		return here;
	}
	
	public ChartChecker getThread3() {
		ChartChecker here = new ChartChecker();
		return here;
	}
}















//Thread name: "[MuseDash.java] UpTime Checker"
//Checks if the MuseDash window is open. Blocks the MapGUI window from moving [KeyListener] and exiting.
//Priority Level: 1 [Lowest]
class MuseDash_UpTime extends Thread { 
	int progress = 0;
	boolean progressBool = false;
	
	public void run() {
		while(true) {
			try{Thread.sleep(100);} catch(Exception e) {} //on CPU Saving mode: only checks 1 time per second when the MuseDash window is NOT VISIBLE
			if(Main.Processes[4].isVisible()) {
				System.out.println("==========================");
				System.out.println("Opened \"Music Enhypen\"");
				while(true) {
					try{Thread.sleep(100);} catch(Exception e) {}
					Main.Processes[3].setDefaultCloseOperation(0);
					Main.MapGUI.inputEnable(false);
					if(!Main.Processes[4].isVisible()) {
						System.out.println("Closed \"Music Enhypen\"");
						System.out.println("==========================");
						Main.Processes[3].setDefaultCloseOperation(2);
						Main.MD.clearChart();
						Main.MD.setTitle("Music Enhypen");
						
						Main.MD.GameplayUI.setVisible(false);
						Main.MD.GameMenuUI.setVisible(true);
						Main.MD.isChartMoving = false;
						Main.MD.isHoldingA = false; Main.MD.isHoldingL = false;
						for(MusicManager music : Main.chartMusic) {music.stop();};
						
						if(Main.Processes[3].isVisible()) {
							Main.MapGUI.inputEnable(true);
							if(progress == 0 && progressBool) {
								Main.MapGUI.setMap("Maze");
								MapData.MapGrid = MapGridList.getMap(3);
								Main.MapGUI.LimitedVision.setVisible(true);
								progress++;
								progressBool = false;
								
								Main.MapGUI.DialogueCounter++;
								Main.MapGUI.DialogueOverlay.setVisible(true);
								Main.MapGUI.inputEnable(false);
								Main.MapGUI.DialogueList();
								
							} else if(progress == 1 && progressBool) {
								Main.MapGUI.setMap("");
								MapData.MapGrid = MapGridList.getMap(2);
								Main.MapGUI.LimitedVision.setVisible(false);
								MapData.MapGrid[16][18] = 0;
								MapData.MapGrid[16][28] = 13;
								progress++;
								progressBool = false;
								
								Main.MapGUI.DialogueCounter++;
								Main.MapGUI.DialogueOverlay.setVisible(true);
								Main.MapGUI.inputEnable(false);
								Main.MapGUI.DialogueList();
								
							}
						}; Main.MapGUI.MapItemRender();
						break;
}	}	}	}	}	}




//Runnable Thread: Made "Runnable" to repeatedly call "start" after the thread died
//When the game actually started, moves the enemies by a stable pixels/sec
//Priority Level: 5 [Default]
class ChartMove implements Runnable { //Fast processing, af
	JLabel[] BG = {Main.MD.BG1, Main.MD.BG2};
	long current_MS, previous_MS, current_MS_Music, previous_MS_Music;
	long timeDif, timeDifMusicStart;
	
	public void run() {
		boolean playedMusic = false;
		JLabel[] RenderedUpperCharts = Main.MD.RenderedUpperCharts;
		JLabel[] RenderedLowerCharts = Main.MD.RenderedLowerCharts;
		double StartDelayPixels = MuseDash_LevelCharts.Pix_per_Beat*5;
		double StartDelayTime = MuseDash_LevelCharts.sec_perBPM*5;
		Main.MD.PixelsMoved -= StartDelayPixels;
		
		//Shifts the charts by certain Beats
		for(JLabel enemy : RenderedUpperCharts) {
			try{enemy.setLocation(enemy.getX()+(int) StartDelayPixels, enemy.getY());}
			catch(NullPointerException err) {break;}
		}
		for(JLabel enemy : RenderedLowerCharts) {
			try{enemy.setLocation(enemy.getX()+(int) StartDelayPixels, enemy.getY());}
			catch(NullPointerException err) {break;}
		}
		
		previous_MS = System.nanoTime(); //Check previous time, in Nanoseconds
		previous_MS_Music = System.nanoTime(); //Check previous time, in Nanoseconds
		while(Main.MD.isChartMoving) {
			//try{Thread.sleep(0);} catch(Exception e) {}; //Fail Safe
			current_MS = System.nanoTime(); //Check current time, in Nanoseconds
			current_MS_Music = System.nanoTime(); //Check current time, in Nanoseconds
			timeDif = (current_MS - previous_MS);
			timeDifMusicStart = current_MS_Music - previous_MS_Music;
			
			if(timeDif >= Main.MD.Delay_ms*1000000) {
				previous_MS = current_MS;
				timeDif -= Main.MD.Delay_ms;
				
				//System.out.println("timeDif = "+timeDif);
				//System.out.println("timeDifMusicStart = "+timeDifMusicStart+" > "+(StartDelayTime*1000d*1000000d));
				for(JLabel enemy : RenderedUpperCharts) {
					try{enemy.setLocation(enemy.getX()-Main.MD.PixelsTravelled, enemy.getY());}
					catch(NullPointerException err) {break;}
				}
				for(JLabel enemy : RenderedLowerCharts) {
					try{enemy.setLocation(enemy.getX()-Main.MD.PixelsTravelled, enemy.getY());}
					catch(NullPointerException err) {break;}
				}
				Main.MD.PixelsMoved += Main.MD.PixelsTravelled;
			}
			
			try{
				if(RenderedUpperCharts[0].getX() < 115) {
					System.out.println("[Upper Chart, Auto] MISS!!!");
					
					Main.MD.updateChart("Upper");
				}
			} catch(Exception e) {}
			
			try{
				if(RenderedLowerCharts[0].getX() < 115) {
					System.out.println("[Lower Chart, Auto] MISS!!!");
					
					Main.MD.updateChart("Lower");
				}
			} catch(Exception e) {}
			
			if(!playedMusic && (timeDifMusicStart > StartDelayTime*1000d*1000000d)) {Main.chartMusic[Main.MD.ChosenTrack-1].play(); playedMusic = true;}
			
			if(Main.MD.RenderedUpperCharts[0] == null && Main.MD.RenderedLowerCharts[0] == null) {
				try{Thread.sleep(1000);} catch(Exception e) {};
				for(MusicManager music : Main.chartMusic) {music.stop();};
				JOptionPane.showMessageDialog(null, "Chart Completed", "Chart Checker", -1);
				
				if((Main.MD_UpTime.progress == 0 || Main.MD_UpTime.progress == 1) && Main.Processes[3].isVisible()) {
					Main.MD_UpTime.progressBool = true;
				}
				Main.MD.setVisible(false);
				break;
			}
		}
		
		Main.MD.LastUpperCount = 15;
		Main.MD.LastLowerCount = 15;
		Main.MD.PixelsMoved = 0;
	}
	
}

//Thread name:
//Checks if the enemies went past the target area [or the so-called "Miss Judgement"]
//Priority Level: 5 [Default]
class ChartChecker implements Runnable {
	JLabel NextUpperEnemy = new JLabel(), NextLowerEnemy = new JLabel();
	String loc = "";
	
	public void run() {
		NextUpperEnemy = Main.MD.RenderedUpperCharts[0];
		NextLowerEnemy = Main.MD.RenderedLowerCharts[0];
		
		switch(loc) {
			case "Upper":
				/* if(Main.MD.RenderedUpperCharts[0] != null) {
					System.out.println("RenderedUpperCharts[0] == has contents");
				} else System.out.println("RenderedUpperCharts[0] == NULL"); */
				try{
					if(NextUpperEnemy.getX() >= 115 && NextUpperEnemy.getX() <= 275) {
						if(NextUpperEnemy.getX() >= 175 && NextUpperEnemy.getX() <= 225) {System.out.println("[Upper] Perfect!! ["+(NextUpperEnemy.getX()-200)+"px]");}
						else System.out.println("[Upper] Great! ["+(NextUpperEnemy.getX()-200)+"px]");
						Main.SFX[0].playSFX();
						Main.MD.updateChart("Upper");
					} else {
						System.out.println("[Upper Chart] MISS!!! ["+(NextUpperEnemy.getX()-200)+"px]");
					}
				} catch(NullPointerException err) {}
				break;
			
			case "Lower":
				/* if(Main.MD.RenderedLowerCharts[0] != null) {
					System.out.println("RenderedLowerCharts[0] == has contents");
				} else System.out.println("RenderedLowerCharts[0] == NULL"); */
				try{
					if(NextLowerEnemy.getX() >= 115 && NextLowerEnemy.getX() <= 275) {
						if(NextLowerEnemy.getX() >= 175 && NextLowerEnemy.getX() <= 225) {System.out.println("[Lower] Perfect!! ["+(NextLowerEnemy.getX()-200)+"px]");}
						else System.out.println("[Lower] Great! ["+(NextLowerEnemy.getX()-200)+"px]");
						Main.SFX[0].playSFX();
						Main.MD.updateChart("Lower");
					} else {
						System.out.println("[Lower Chart] MISS!!! ["+(NextLowerEnemy.getX()-200)+"px]");
					}
				} catch(NullPointerException err) {}
				break;
			
			default: 
		}	
	}
}

































