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
		this.setSize(720, 720); //Window Actual res: 695px - 680px
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
				
				components[3].setText("<html>This game is directly inspired from the game mentioned above. However, due to my coding capacities, I can NOT"+
																			"<br>implement all the featues the original game has. Just think of this as a bootleg version of the original game..."+
																	"</html>");
				components[3].setBounds(5, 450, 685, 250);
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