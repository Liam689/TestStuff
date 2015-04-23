package ScrollGame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class LevelContainer {
	private Maze level;
	int ID;
	static int WIDTH, HEIGHT;
	static int gemsRequired;
	static GameOverInterface gof;
	Gem gems;
	public LevelContainer(int levelID) throws SlickException{ // constructor for class
		ID = levelID;
		init();
	}
	
	public void init() throws SlickException{ // initialize everything, depending on the ID
		switch(ID){
		case 1:
			gemsRequired = 3;
			level = new HuntAndKillMazeGenerator(10,10);
			((HuntAndKillMazeGenerator)level).generateMaze();
			WIDTH = level.getWidth();
			HEIGHT = level.getHeight();
			
			gems =new Gem(level.block[4][5]);
			
			break;

		case 2:

			gemsRequired = 5;
			level = new HuntAndKillMazeGenerator(12,12);
			((HuntAndKillMazeGenerator)level).generateMaze();
			WIDTH = level.getWidth();
			HEIGHT = level.getHeight();
			gems =new Gem(level.block[6][5]);
			break;
		case 3:
			gof = new GameOverInterface();
			break;
			}
	}
	
	public Maze getLevel() { // getLevel
		return level;
	}

	public void setLevel(Maze level) { // setter for level
		this.level = level;
	}
	public void setToNextLevel() throws SlickException{ //sets the game to next level
		level = null;
//		Play.items.removeAll(Play.items);
		Play.characters.removeAll(Play.characters);
		Play.bullets.removeAll(Play.bullets);
		Gem.gemsCollected = 0;
		
		ID++;
		init();
	}
}
