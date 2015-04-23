package ScrollGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Maze {
	Block block[][];
	private int height, width;
	public static int WIDTH,HEIGHT;


	public Maze(int width, int height) throws SlickException {
		block = new Block[width][height];
		new HeadLamp(0,0);
		new Gun(0,0);				
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				block[x][y] = new Block(x, y); // initialize the block in an
										    	// array depending on width &
												// height
		this.height = height;
		this.width = width;
		WIDTH = width*200;
		HEIGHT = height*200;
	}

	public int getHeight() { // getter for height
		return height;
	}

	public void setHeight(int height) { // setter for height
		this.height = height;
	}

	public int getWidth() { // getter for width
		return width;
	}

	public void setWidth(int width) { // setter for width
		this.width = width;
	}
	public void draw(Graphics g, float shiftX, float shiftY) { // draw the maze
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++){
				block[x][y].draw(g);
				
			}
	}
	public void update() throws SlickException{ //update the maze
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++){
				block[x][y].update();
					
				
		}
	}

}
