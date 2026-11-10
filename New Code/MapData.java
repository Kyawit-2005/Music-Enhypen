//Tiles per Screenshot: 19 Blocks width, 11 Blocks height.     Center Coords: X=10, Y=6
//Total Map Size: 57 Blocks wdth, 33 Blocks height.     Center Coords: X=29, Y=17
//1 Block Size = 63 Pixels

//Image Resolution: 1865px - 1080px
//Image Shift: 27px

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MapData { 
	static int PosX = 28, PosY = 16;
	static int RED_KeyAmount = 0, GREEN_KeyAmount = 0, NextTile = 0;
	static boolean canMove = false;
	static int[][] MapGrid =MapGridList.getMap(1);
	
	//Moving Guidelines:
	//0 = Empty
	//1 = Player Spawn
	//2 = Blocks
	
	//Event Guidelines:
	//3 = Key 1
	//4 = Locked Door 1
	//5 = Open Dialogue, and proceeds to go next dialogue
	//9 = Test Block: Winning
	
	//Enter into another game guidelines:
	//11 = "Muse Dash"
	
	public static void MovePlayer(String Direction) {
		try{
			switch(Direction) {
				case "UP":
					TileTrigger(MapGrid[PosY-1][PosX]);
					if(canMove) {
						PosY--;
					} else System.out.println("The position above you is a Block");
					break;
					
				case "DOWN":
					TileTrigger(MapGrid[PosY+1][PosX]);
					if(canMove) {
						PosY++;
					} else System.out.println("The position below you is a Block");
					break;
					
				case "LEFT":
					TileTrigger(MapGrid[PosY][PosX-1]);
					if(canMove) {
						PosX--;
					} else System.out.println("The position to the left of you is a Block");
					break;
					
				case "RIGHT":
					TileTrigger(MapGrid[PosY][PosX+1]);
					if(canMove) {
						PosX++;
					} else System.out.println("The position to the right of you is a Block");
					break;
					
				default:
			};
		} catch(ArrayIndexOutOfBoundsException err) {
			System.out.println("[ArrayIndexOutOfBoundsException] OUT OF BOUNDS!!!");
			canMove = false;
		}
	}
	
	public static void TileTrigger(int num) {
		switch(num) {
			//Empty Space
			case 0: case 1:
				canMove = true;
				break;
			
			//Blocks
			case 2: 
				canMove = false;
				break;
			
			//Red Key Collectible
			case 3: 
				JOptionPane.showMessageDialog(null, "You picked up a Red Key.\nYou wonder what it is for...", "Obtained Red Key!", 1);
				RED_KeyAmount++;
				canMove = true;
				break;
			
			//Red Lock Unlockable
			case 4: 
				String[] ChoiceRED = {"Open the Door ["+RED_KeyAmount+" key(s)]", "Save the key for now..."};
				int chosenRED = JOptionPane.showOptionDialog(null, "This door is locked. Want to open the door?", "Red door is locked", 0, 1, null, ChoiceRED, ChoiceRED[0]);
				if(chosenRED == 0 && RED_KeyAmount <= 0) {
					JOptionPane.showMessageDialog(null, "You don't have enough red keys.", "Sample Trigger", 0);
					canMove = false;
					break;
				} else if(chosenRED == 0) {
					JOptionPane.showMessageDialog(null, "You successfully opened the door...", "Sample Trigger", -1);
					RED_KeyAmount--;
					canMove = true;
				} else canMove = false;
				break;
			
			//Green Key Collectible
			case 5: 
				JOptionPane.showMessageDialog(null, "You picked up a Green Key.\nYou wonder what it is for...", "Obtained Green Key!", 1);
				GREEN_KeyAmount++;
				canMove = true;
				break;
			
			//Green Lock Unlockable
			case 6: 
				String[] ChoiceGREEN = {"Open the Door ["+GREEN_KeyAmount+" key(s)]", "Save the key for now..."};
				int chosenGREEN = JOptionPane.showOptionDialog(null, "This door is locked. Want to open the door?", "Green door is locked", 0, 1, null, ChoiceGREEN, ChoiceGREEN[0]);
				if(chosenGREEN == 0 && GREEN_KeyAmount <= 0) {
					JOptionPane.showMessageDialog(null, "You don't have enough green keys.", "Sample Trigger", 0);
					canMove = false;
					break;
				} else if(chosenGREEN == 0) {
					JOptionPane.showMessageDialog(null, "You successfully opened the door...", "Sample Trigger", -1);
					GREEN_KeyAmount--;
					canMove = true;
				} else canMove = false;
				break;
			
			//We do a li'l bit of trolling, lmao [Rickroll block]
			//In the end, it's unused [LMAO]
			case 7: 
				JOptionPane.showMessageDialog(null, "Insert Rickroll song here, with the Lyrics here", "Rick Astley - Never gonna give you up", -1);
				break;
			
			//Next Dialogue
			case 8:
				Main.MapGUI.DialogueCounter++;
				Main.MapGUI.DialogueOverlay.setVisible(true);
				Main.MapGUI.inputEnable(false);
				Main.MapGUI.DialogueList();
				break;
			
			//Unused Trigger
			case 9: 
				JOptionPane.showMessageDialog(null, "An unused trigger...", "HELLO WORLD", 1);
				canMove = true; 
				break;
			
			//Music Enhypen: Chipflake Intro 8-Bit
			case 11: 
				JOptionPane.showMessageDialog(null, "You will now proceeding to Playing \"Music Enhypen\"", "Minigame time...!", 1);
				canMove = false; 
				Main.Processes[4].setVisible(true);
				
				//Auto play the chart based on the lines of code below
				Main.MD.setTitle("Music Enhypen: Now playing \"Chipflake Intro 8-Bit [Bluey]\"");
				Main.MD.ChosenTrack = 5;
				Main.MD.createInitialChart();
				Main.MD.GameMenuUI.setVisible(false);
				Main.MD.GameplayUI.setVisible(true);
				break;
			
			//Music Enhypen: Chattermax
			case 12: 
				JOptionPane.showMessageDialog(null, "You will now proceeding to Playing \"Music Enhypen\"", "Sample Trigger", 1);
				canMove = false; 
				Main.Processes[4].setVisible(true);
				
				//Auto play the chart based on the lines of code below
				Main.MD.setTitle("Music Enhypen: Now playing \"Chattermax [Bluey]\"");
				Main.MD.ChosenTrack = 6;
				Main.MD.createInitialChart();
				Main.MD.GameMenuUI.setVisible(false);
				Main.MD.GameplayUI.setVisible(true);
				break;
			
			//End Trigger
			case 13:
				JOptionPane.showMessageDialog(null, "Continuation will be coming soon [hopefully]. Stay tuned to find out!\nThe End...?", "You tried to go 1 Floor below, but something went wrong", 2);
				canMove = false;
			default: //nothing
		}; //DebugTileInfo(num); //For debugging purposes only
	}
	
	public static void DebugTileInfo(int num) {
		if(canMove) {
			System.out.print("[DEBUG: MapData] ");
			switch(num) {
				case 0: System.out.println("Empty Tile"); break;
				case 1: System.out.println("Player Initial Position Tile"); break;
				case 2: System.out.println("Block"); break; //How to trigger this, by the way, lmao
				case 3: System.out.println("Key Tile"); break;
				case 4: System.out.println("Door Tile"); break;
				case 5: System.out.println("Open Chat Tile"); break;
				case 9: System.out.println("Exit Tile"); break;
				case 11: System.out.println("Muse Dash trigger Tile: Chipflake Intro"); break;
				case 12: System.out.println("Muse Dash trigger Tile: Chattermax"); break;
				default: System.out.println("Unimplemented Tile");
			}
		}
	}
	
	public static void setCurrentTile(int num) {
		MapGrid[PosY][PosX] = num;
	}
	
	public static void setMap(int num) {
		MapGrid = MapGridList.getMap(num);
	}
}