package ScrollGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class GameOverInterface {
	Rectangle[] squares;
	int score;
	public GameOverInterface(){ // sets up the variables
		score = (13000+ Play.character.getHealth()*10) -(int)((System.currentTimeMillis() - Play.startingTime)/1000);
		squares = new Rectangle[2];
		squares[0] = new Rectangle(50,100,750,300);
		squares[1] = squares[0];
	}
	public void draw(Graphics g){ // draws the game over graphic
		g.setColor(Color.white);
		g.fill(squares[1]);
		g.setColor(Color.cyan);
		g.draw(squares[0]);
		g.setColor(Color.red);
		g.drawString("YOU WIN",425-("YOU WIN".length()*5), 250);
		g.drawString("SCORE: "+ score,425-("YOU WIN".length()*5), 275);

	}
}
