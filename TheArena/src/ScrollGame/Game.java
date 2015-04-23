package ScrollGame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


/*
 * This class renders the states onto the screen and also sets up the defautl screen size and other variables.
 */

public class Game extends StateBasedGame {
	public static final String gameName = "The Arena |D21|";
	public static final int menu = 0;
	public static final int play = 1;
	public static final int WIDTH = 850;
	public static final int HEIGHT = 500;
	static Menu menuState = new Menu(menu);
	static Play playState = new Play(play);
	
	public Game(String gameName){
		super(gameName);
		
		this.addState(menuState);
		this.addState(playState);
	}
	
	public void initStatesList(GameContainer gc) throws SlickException { // Inherited, initialises the states and pickes a state to begin on
		
		this.getState(menu).init(gc, this);
		this.getState(play).init(gc, this);
		
		this.enterState(menu);
		
	}

	
	
public static void main(String args[]) throws SlickException{
	
	AppGameContainer agc;
	try{
		agc = new AppGameContainer(new Game(gameName)); 	 //try to set up the screen and the appGameContainer
		agc.setDisplayMode(WIDTH,HEIGHT,false);
		agc.start();
	}catch(SlickException e){
		e.printStackTrace();
	}
	
}




	
	
}
