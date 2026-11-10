import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GameHowToPlay extends JFrame {
	public ImageIcon GameIcon_MD = new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\KagamineRinLen Icon.png");
	public ImageIcon Comp1_MD = new ImageIcon(new ImageIcon("Game Assets\\Image Files\\MuseDash Resources\\HowToPlay_MD.jpg").getImage().getScaledInstance(675, 380, Image.SCALE_SMOOTH));
	public JLabel[] components = {new JLabel(), new JLabel(), new JLabel(), new JLabel()};
	public String showInfo = "";
	
	public GameHowToPlay() {
		this.setTitle("Game Information [HOW TO PLAY]");
		this.setSize(720, 750); //Window Actual res: 695px - 710px
		this.setLayout(null);
		this.getContentPane().setBackground(Color.black);
		
		this.setDefaultCloseOperation(2);
		this.setLocationRelativeTo(null);
	}
	
	public void showGameInfo(String showInfo) {
		switch(showInfo) {
			case "Muse Dash" :
				components[0].setText("<html><font size='5'>Additional info on how to play \"Music Enhypen\"</font></html>");
				components[0].setBounds(5, 5, 685, 25);
				components[0].setHorizontalAlignment(JLabel.CENTER);
				components[0].setForeground(Color.white);
				
				components[1].setIcon(Comp1_MD);
				components[1].setBounds(15, 40, 675, 380);
				
				components[2].setText("<html><font size='2'>A screenshot of \"Muse Dash\", the game where it directly inspired/recreated from...</font></html>");
				components[2].setBounds(5, 425, 685, 10);
				components[2].setHorizontalAlignment(JLabel.CENTER);
				components[2].setForeground(Color.white);
				
				//																	||																																																																							||
				components[3].setText("<html>This game is directly inspired from the game mentioned above. However, due to how the code is structured, I can NOT"+
																			"<br>implement Hold Notes <font color='gray'>[I mean... I could, but... Deadline, man...]</font>"+
																			"<br>"+
																			"<br>Here's how this works: In this recreation, there's 2 Circles on the left side of your screen. That's WHEN you hit the enemies."+
																			"<br>The enemies in this recreation are just <font color='yellow'>YELLOW STARS</font>."+
																			"<br>When an enemy is within the circles, press \"A\" to hit the Upper Enemies, and press \"L\" to hit the Lower Enemies."+
																			"<br>Since this is a Rhythm Game, you hit enemies with the music. [I apologize in advance if my Charting is bad or difficult.]"+
																			"<br>"+
																			"<br><font color='red'>Also, due to how dirty and unoptimized my code is, sometimes it will have LAG SPIKES. The LAG will definitely throw you"+
																			"<br>off, making the visuals off-sync to the Music. I sincerely apologize, as I am no expert at JAVA Programming."+
																			"<br>Basic workaround for this [when it happens] is to quit to the Rhythm game, and launch it again. But it is not 100% working"+
																			"<br>all the time.</font>"+
																			"<br>"+
																			"<br>Device specifications used to code this: Acer Aspire E15 [E5-575-52DA]"+
																			"<br> > [CPU: Intel Core i5-7200U] [RAM: 8GB] [GPUs: Intel HD Graphics 620/Nvidia GeForce 940MX]"+
																	"</html>");
				components[3].setBounds(5, 450, 695, 250);
				components[3].setVerticalAlignment(JLabel.TOP);
				components[3].setForeground(Color.white);
				
				for(JLabel comp : components) {this.add(comp);}
				this.setIconImage(GameIcon_MD.getImage());
				this.setVisible(true);
				break;
			case "A Dance of Fire and Ice" :
				
				break;
			case "Osu!" :
				
				break;
			default : //uhhmmmm...
		}
	}
}