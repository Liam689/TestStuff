package ScrollGame;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Play extends BasicGameState {
	static List<Character> characters;
	static List<Item> items;
	static List<Bullet> bullets;
	public static PlayableCharacter character;
	static float shiftX, shiftY;
	static int delta;
	static LevelContainer levels;
	StatisticUI statisticUI ;
	int characterID;
	static long startingTime;


	public Play(int state) {

	}

	public void init(GameContainer gc, StateBasedGame sbg) // Initialise everything
			throws SlickException {
		startingTime = System.currentTimeMillis();
		characters = new ArrayList<Character>();
		items = new ArrayList<Item>();
		bullets = new ArrayList<Bullet>();
		characterID = 2;
		character = (PlayableCharacter)new PlayableCharacter(Game.WIDTH/2,Game.HEIGHT/2);
		statisticUI = new StatisticUI(character);
		levels = new LevelContainer(1);
		character.setCurrentBlock(levels.getLevel().block[((int)levels.WIDTH/2)][(int)levels.HEIGHT/2]);
		shiftX = -(((Maze.WIDTH/2))+(((Maze.WIDTH/2%200 == 0))? 100:0))+ Game.WIDTH/2; // set the character in the middle of the maze
		shiftY = -(((Maze.HEIGHT/2))+(((Maze.HEIGHT/2%200 == 0))? 100:0))+ Game.HEIGHT/2; // set the character in the middle of the maze
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) //draws eveyrhtign onto the screen
			throws SlickException {

		if(levels.getLevel() != null)
		levels.getLevel().draw(g, shiftX, shiftY);
		
		character.drawCharacter(characterID, g);
			
		g.setColor(Color.cyan);
		statisticUI.draw(g);
		
		for(Bullet b: bullets)
			if(b != null)
				b.draw(g);
		for(Item i: items)
			if(i != null)
				i.draw(g);
		for(Character c: characters)
			if(c != null)
			c.draw(g);
		if(levels.gof != null)
		levels.gof.draw(g);
	}
	

	public void update(GameContainer gc, StateBasedGame sbg, int delta) // updates everything
			throws SlickException {
		this.delta = delta;
		Input input = gc.getInput();
		character.isMoving = false;
		if (input.isKeyDown(Input.KEY_W)&& !character.hasDied) { // Moving up
			character.movingUp = true;
			character.movingDown = false;
			character.movingLeft = false;
			character.movingRight = false;
			shiftY += character.getSpeed() * delta;
			character.setDirection(Character.UP);
			characterID = Character.UP;
			character.isMoving = true;
		}else
		if (input.isKeyDown(Input.KEY_A) && !character.hasDied) { // Moving left
			character.movingUp = false;
			character.movingDown = false;
			character.movingLeft = true;
			character.movingRight = false;
			shiftX += character.getSpeed() * delta;
			character.setDirection(Character.LEFT);
			characterID = Character.LEFT;
			character.isMoving = true;
		}else
		if (input.isKeyDown(Input.KEY_S)&& !character.hasDied ) { // Moving down
			character.movingUp = false;
			character.movingDown = true;
			character.movingLeft = false;
			character.movingRight = false;
			shiftY -= character.getSpeed() * delta;
			character.setDirection(Character.DOWN);
			characterID = Character.DOWN;
			character.isMoving = true;
		}else
		if (input.isKeyDown(Input.KEY_D)&& !character.hasDied ) { // Moving right
			character.movingUp = false;
			character.movingDown = false;
			character.movingLeft = false;
			character.movingRight = true;
			shiftX -= character.getSpeed() * delta;
			character.setDirection(Character.RIGHT);
			characterID = Character.RIGHT;
			character.isMoving = true;
		}
		if(input.isKeyDown(Input.KEY_SPACE)&& character.getGun() != null){
			character.getGun().shoot(character.getDirection());
		}
		if(character.hasDied){
		
			characterID = Character.DEAD;
		}
		if(input.isKeyDown(Input.KEY_LSHIFT))
			character.isRunning = true;
		else
			character.isRunning = false;
		if(levels.getLevel() != null)
		levels.getLevel().update();
		
		for(int i = 0; i<getItems().size();i++)
			if(getItems().get(i) != null)
				getItems().get(i).update();
		for(int i = 0; i<getCharacters().size();i++)
			if(getCharacters().get(i) != null)
				getCharacters().get(i).update();
		for(int i = 0; i<getBullets().size();i++)
				if(getBullets().get(i) != null)
					getBullets().get(i).update();

	}
	public static synchronized List<Item> getItems() { // getter for items
		return items;
	}

	public static void setItems(List<Item> items) { // setter for item
		Play.items = items;
	}
	public static synchronized List<Character> getCharacters() { // getter for characters
		return characters;
	}

	public static void setCharacters(List<Character> characters) { // setter for characters
		Play.characters = characters;
	}
	public static void removeBullet(Bullet b){ // remove the buller fromt he buller list
		Bullet bulletToRemove = null;
		if(bullets != null)
			for(Bullet blt: getBullets())
					if(blt == b){
						bulletToRemove = b;
						break;
					}
		getBullets().remove(bulletToRemove);
	}
	public static synchronized List<Bullet> getBullets() { // getter for bullets
		return bullets;
	}

	public static synchronized  void setBullets(List<Bullet> bullets) { //setter for bullets
		Play.bullets = bullets;
	}

	public int getID() {
		return 1;
	}

}
