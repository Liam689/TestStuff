package ScrollGame;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class Menu extends BasicGameState implements ButtonResponder{
	

	Image background;
	Image controls;
	Button play, control,back;
	boolean clickedPlayButton,clickedControlButton,clickedOnBackButton;
	
	public Menu(int state){
		
		
	}


	public void init(GameContainer gc, StateBasedGame sbg) // Initialise all variables and objects
			throws SlickException {
		background = new Image("res/menu-background.png");
		controls = new Image("res/controlls.png");
		play = new Button("Play","play", 375,100);
		control = new Button("Controls", "controls",375,200);
		back = new Button("Back",750,0);
		back.hide();
	}

	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) // draw eveyrhtign onto the screen
			throws SlickException {
		background.draw();
		play.draw(g);
		control.draw(g);
		if(clickedControlButton)
			g.drawImage(controls,0,0);
			back.draw(g);
			}

	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) // update everything
			throws SlickException {
		play.update();
		control.update();
		if(clickedControlButton){
			back.update();
			back.unHide();
		}
			if(clickedPlayButton)
				sbg.enterState(Game.play);
			

	}

	
	public int getID() {
		
		return 0;
	}


	// butten pressed if from the buttonResponder
	public void buttonPressed(Button b) {
		if(b.text.equals("Play")){
			clickedPlayButton = true;
		}
		if(b.text.equals("Controls")){
			clickedControlButton = true;
		}
		if(b.text.equals("Back"))
			clickedControlButton = false;
		
	}
	
	

}
