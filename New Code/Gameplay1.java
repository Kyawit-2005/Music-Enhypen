import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Gameplay1 extends JFrame implements KeyListener {
	JLabel PersonLeft, PersonRight, Dialogue;
	JLabel PersonNameLeftPic, PersonNameRightPic, PersonNameLeft, PersonNameRight;
	JPanel DialogueOverlay = new JPanel(), MapItems;
	JLabel[] WholeMap = new JLabel[9];
	JLabel Player, LimitedVision;
	String User_Name ="Kagamine Len"; //Temporary Name when started immediately here
	int DialogueCounter = 0;
	
	public Gameplay1() {
		Player = new JLabel(new ImageIcon("Game Assets\\Image Files\\Map Resources\\Player-UP.png"));
		Player.setBounds(-66+9*63, -7+5*63, 63, 63);
		this.add(Player);
		
		DialogueOverlay.setBounds(0, 0, 1065, 680); //Exact Window Size
		DialogueOverlay.setLayout(null);
		DialogueOverlay.setOpaque(false);
		//==============================================================
		//JPanel
		Dialogue = new JLabel(TextSize(6, "You, "+User_Name+", arrived at a strange place-"));
		Dialogue.setBounds(175, 455, 690, 190);
		Dialogue.setVerticalAlignment(JLabel.TOP);
		Dialogue.setHorizontalAlignment(JLabel.CENTER);
		Dialogue.setForeground(Color.white);
		DialogueOverlay.add(Dialogue);
		
		//JPanel
		PersonNameLeft = new JLabel();
		PersonNameLeft.setBounds(180, 428, 145, 25);
		PersonNameLeft.setForeground(Color.white);
		PersonNameLeft.setHorizontalAlignment(JLabel.LEFT);
		DialogueOverlay.add(PersonNameLeft);
		
		//JPanel
		PersonNameRight = new JLabel();
		PersonNameRight.setBounds(715, 428, 145, 25);
		PersonNameRight.setForeground(Color.white);
		PersonNameRight.setHorizontalAlignment(JLabel.RIGHT);
		DialogueOverlay.add(PersonNameRight);
		
		//JPanel
		PersonNameLeftPic = new JLabel(new ImageIcon("Game Assets\\Image Files\\PersonNameLeft.png"));
		PersonNameLeftPic.setBounds(175, 428, 150, 25);
		PersonNameLeftPic.setVisible(false);
		DialogueOverlay.add(PersonNameLeftPic);
		
		//JPanel
		PersonNameRightPic = new JLabel(new ImageIcon("Game Assets\\Image Files\\PersonNameRight.png"));
		PersonNameRightPic.setBounds(715, 428, 150, 25);
		PersonNameRightPic.setVisible(false);
		DialogueOverlay.add(PersonNameRightPic);
		
		//JPanel
		JLabel DialogueBox = new JLabel(new ImageIcon("Game Assets\\Image Files\\DialogueBox.png"));
		DialogueBox.setBounds(170, 440, 700, 200); //Res: 700px - 200px
		DialogueOverlay.add(DialogueBox);
		
		//JPanel
		PersonLeft = new JLabel();
		PersonLeft.setBounds(-100, 80, 427, 640);
		PersonLeft.setHorizontalAlignment(JLabel.CENTER);
		DialogueOverlay.add(PersonLeft);
		
		//JPanel
		PersonRight = new JLabel();
		PersonRight.setBounds(723, 80, 427, 640);
		PersonRight.setHorizontalAlignment(JLabel.CENTER);
		DialogueOverlay.add(PersonRight);
		
		JButton NextDialogue = new JButton("Next");
		NextDialogue.setBounds(170, 650, 100, 25);
		NextDialogue.setFocusable(false);
		NextDialogue.setBorder(null);
		NextDialogue.addActionListener(e -> {
			DialogueCounter++;
			DialogueList();
		}); DialogueOverlay.add(NextDialogue);
		//==============================================================
		this.add(DialogueOverlay);
		
		ImageIcon Mask = new ImageIcon("Game Assets\\Image Files\\Map Layout\\Map_Mask.png");
		Mask = new ImageIcon(Mask.getImage().getScaledInstance(1192, 690, Image.SCALE_SMOOTH));
		LimitedVision = new JLabel(Mask);
		LimitedVision.setBounds(-64, -7, 1192, 690);
		LimitedVision.setVisible(false);
		this.add(LimitedVision);
		
		MapItems = new JPanel();
		MapItems.setBounds(-1256, -697, 3576, 2070);
		MapItems.setOpaque(false);
		MapItems.setLayout(null);
		this.add(MapItems);
		
		MapBuilder();
		
		this.setTitle("The MAP!!!");
		this.setSize(1080, 720); //Window Actual res: 1040px - 680px
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		
		
		
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//Moving Player's initial Position
		for(int n=0; n<27; n++) {
			MovePlayer("LEFT");
		} MovePlayer("RIGHT");
		MapData.MapGrid = MapGridList.getMap(2);
		
		MapItemRender();
	}
	
	public void MoveMap(int X, int Y) {
		MapItems.setLocation(MapItems.getX()+X, MapItems.getY()+Y);
		for(int n = 0; n < 9; n++) {
			WholeMap[n].setLocation(WholeMap[n].getX()+X, WholeMap[n].getY()+Y);
		}
	}
	
	public void MapBuilder() {
		//this.add(Story1.DialogueOverlay); //Gotta work on this later
		ImageIcon MapLoc, MapPic;
		int point = 0;
		
		int[][] LabelPos = {{-1256, -697}, 	{-64, -697}, {1128, -697},
													{-1256, -7}, 		{-64, -7}, 		{1128, -7},
													{-1256, 683}, 	{-64, 683}, 	{1128, 683}};
		String[] Loc = {"UL", "U", "UR", "L", "C", "R", "BL", "B", "BR"};
		for(String loc : Loc) {
			MapLoc = new ImageIcon("Game Assets\\Image Files\\Map Layout\\Map_"+loc+".png"); //Size: 1920 - 1080
			MapPic = new ImageIcon(MapLoc.getImage().getScaledInstance(1192, 690, Image.SCALE_SMOOTH));
			
			WholeMap[point] = new JLabel(MapPic);
			WholeMap[point].setBounds(LabelPos[point][0], LabelPos[point][1], 1192, 690);
			WholeMap[point].setHorizontalAlignment(JLabel.CENTER);
			WholeMap[point].setVerticalAlignment(JLabel.CENTER);
			this.add(WholeMap[point]);
			
			point++;
		}
	}
	
	public void MapItemRender() {
		for(Component item: MapItems.getComponents()) {
			MapItems.remove(item);
		}
		ImageIcon ItemIMG;
		double BlockSize = 62d+(8d/11d);
		int[][] Map = MapData.MapGrid;
		
		for(int Y = 0; Y < Map.length; Y++) {
			for(int X = 0; X < Map[Y].length; X++) {
				switch(Map[Y][X]) {
					case 3: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Red Key.png"); break;
					case 4: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Red Lock.png"); break;
					case 5: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Green Key.png"); break;
					case 6: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Green Lock.png"); break;
					case 11: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Music Disk.png"); break;
					case 12: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Music Disk.png"); break;
					case 13: ItemIMG = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Music Disk.png"); break;
					default: ItemIMG = null;
				}
				
				if(ItemIMG != null) {
					ItemIMG = new ImageIcon(ItemIMG.getImage().getScaledInstance(63, 63, Image.SCALE_SMOOTH));
						
					JLabel Item = new JLabel(ItemIMG); 
					Item.setBounds(0, 0, 63, 63);
					Item.setLocation(Item.getX()+(int) (X*BlockSize), Item.getY()+(int) (Y*BlockSize));
					MapItems.add(Item);
					//System.out.println("X = "+X+", Y = "+Y+", BlockSize = "+BlockSize);
					//System.out.println("X Pos = "+((X+1)*BlockSize)+", Y = "+((Y+1)*BlockSize));
				}
			}
		}
	}
	
	
	//Reminding myself to fix the hell out of the English Grammar
	void DialogueList() { //Syntax: "Character Actual Name", "Position", "Display Name", "TextBox Message"
		switch(DialogueCounter) { //																																				|| Text Limit
		//																																																				|| Text Limit
			case 1: Say("Hajime", "Left", User_Name,
									"What is this place?"); break;
			
			case 2:  Say("Mari", "Right", "Ema Skye",
									"Well... As you can see, in this area, there's no other way to go around."); break; //Broken English
			
			case 3: Say("Hajime", "Left", User_Name,
									"...and who is this lady with us?"); break;
			
			case 4: Say("Shino", "Right", "Random Girl",
									"Yeah, you did forget... *sigh~"); break;
			
			case 5: Say("Hajime", "Left", User_Name,
									"...uhhh, what?"); break;
			
			case 6: Say("Shino", "Right", "Random Girl",
									"You know what? Just call me \"ID\""); break;
			
			case 7: Say("Hajime", "Left", User_Name,
									"Okay!?"); break;
			
			case 8: Say("Hajime", "Left", User_Name,
									"So... What exactly are we finding in this area?"); break;
			
			case 9: Say("Mari", "Right", "Ema Skye",
									"Music."); break;
			
			case 10: Say("Hajime", "Left", User_Name,
									"Music?"); break;
			
			case 11: Say("Mari", "Right", "Ema Skye",
									"Yes. We were acually finding music-"); break;
			
			case 12: Say("Hajime", "Left", User_Name,
									"...but why?"); break;
			
			case 13: Say("Mari", "Right", "Ema Skye",
									"So that we restore the original music, and also put them back on the soundware."); break;
			
			
			case 15: Say("Mari", "Right", "Ema Skye",
									"Here... Look! There's one over there!"); break;
			
			case 16: Say("Hajime", "Left", User_Name,
									"Whoaaa, that's the music you're talking about?"); break;
			
			case 17: Say("Mari", "Right", "Ema Skye",
									"Yes. That's it. Well, are you ready to fix it, though?"); break;
			
			case 18: Say("Hajime", "Left", User_Name,
									"...huh? Fix it? No! I don't know how to fix this..."); break;
			
			case 19: Say("Shino", "Right", "ID",
									"...yeah, you have to fix them, because they're coming-"); break; //WTF IS THIS BROKEN ENGLISH!?!?!?
			
			case 20: Say("Hajime", "Left", User_Name,
									"Coming... what?"); break;
			
			case 21: Say("Shino", "Right", "ID",
									"Enough whining, just do it. I know you can do it!"); break;
			
			
			case 23: Say("Hajime", "Left", User_Name,
									"...what is going on? [Insert missing dialogues here]"); break;
			
			
			case 25:  Say("Hajime", "Left", User_Name,
									"Huh? What is happening again? [Insert missing dialogues here"); break;
				
			default:
				inputEnable(true);
				DialogueOverlay.setVisible(false);
				
		}
	}
	
	
	
	
	
	
	
	public void keyTyped(KeyEvent key) {
		
	}
	
	public void keyPressed(KeyEvent key) {
		System.out.print("[Pressed Key: "+key.getKeyChar()+" ("+key.getKeyCode()+")]\r");
		switch(key.getKeyCode()) {
			case 38: case 87: MovePlayer("UP"); break;
			case 40: case 83: MovePlayer("DOWN"); break;
			case 37: case 65: MovePlayer("LEFT"); break;
			case 39: case 68: MovePlayer("RIGHT"); break;
			default: System.out.println("[DEBUG] Unregistered: You typed "+key.getKeyChar()+" [KeyCode "+key.getKeyCode()+"]");
		}
		MapItemRender();
		
	}
	
	public void keyReleased(KeyEvent key) {
	
	}
	
	
	
	
	
	
//==============================================================================
//==============================================================================
	public void MovePlayer(String dir) { //Exact Block Movement: 62.72727272... [62 8/11]
		ImageIcon PlayerPic = new ImageIcon("Game Assets\\Image Files\\Map Resources\\Player-"+dir+".png");
		Player.setIcon(PlayerPic);
		MapData.MovePlayer(dir);
		
		switch(dir) {
			case "UP":
				if(MapData.canMove) {MoveMap(0, 63); MapData.setCurrentTile(0);};
				break;
			case "DOWN":
				if(MapData.canMove) {MoveMap(0, -63); MapData.setCurrentTile(0);};
				break;
			case "LEFT":
				if(MapData.canMove) {MoveMap(63, 0); MapData.setCurrentTile(0);};
				break;
			case "RIGHT":
				if(MapData.canMove) {MoveMap(-63, 0); MapData.setCurrentTile(0);};
				break;
			default:
		}
		
		//System.out.println("[DEBUG] MapData.canMove = "+MapData.canMove);
	}
	
	public void inputEnable(boolean bool) {
		if(!bool) {
			this.removeKeyListener(this);
		} else this.addKeyListener(this);
	}
	
	void Say(String Person, String Direction, String PersonName, String Text) {
		switch(Direction) {
			case "Left":
				PersonLeft.setIcon(getPerson(Person, "Left"));
				PersonNameLeftPic.setVisible(true);
				PersonNameRightPic.setVisible(false);
				PersonNameLeft.setText(TextSize(4, PersonName));
				PersonNameRight.setText("");
				break;
			case "Right":
				PersonRight.setIcon(getPerson(Person, "Right"));
				PersonNameRightPic.setVisible(true);
				PersonNameLeftPic.setVisible(false);
				PersonNameRight.setText(TextSize(4, PersonName));
				PersonNameLeft.setText("");
				break;
			case "Both":
				ImageIcon nothing = new ImageIcon("Game Assets\\Image Files\\Standing Characters\\Null.png");
				//PersonLeft.setIcon(nothing);
				//PersonRight.setIcon(nothing);
				PersonNameLeftPic.setVisible(false);
				PersonNameRightPic.setVisible(false);
				PersonNameLeft.setText("");
				PersonNameRight.setText("");
				break;
			default: System.out.println("Something is wrong...");
		}; Dialogue.setText(TextSize(5, Text));
		Main.SFX[1].playSFX();
	}
	
	ImageIcon getPerson(String CharName, String Direction) { //Standing Image res: 640px - 960px, Resized: 426.66px - 640px
		ImageIcon pic = new ImageIcon("Game Assets\\Image Files\\Standing Characters\\"+CharName+"_"+Direction+".png");
		ImageIcon resized_pic = new ImageIcon(pic.getImage().getScaledInstance(pic.getIconWidth()*2/3, pic.getIconHeight()*2/3, Image.SCALE_SMOOTH));
		return resized_pic;
	}
	
	String TextSize(int size, String text) {
		return "<html><font size = '"+size+"'>"+text+"</font></html>";
	}
	
	void setMap(String append) {
		ImageIcon MapLoc, MapPic;
		int point = 0;
		String[] Loc = {"UL", "U", "UR", "L", "C", "R", "BL", "B", "BR"};
		for(String loc : Loc) {
			MapLoc = new ImageIcon("Game Assets\\Image Files\\Map Layout\\Map"+append+"_"+loc+".png"); //Size: 1920 - 1080
			MapPic = new ImageIcon(MapLoc.getImage().getScaledInstance(1192, 690, Image.SCALE_SMOOTH));
			
			WholeMap[point].setIcon(MapPic);
			point++;
		}
	}
}
