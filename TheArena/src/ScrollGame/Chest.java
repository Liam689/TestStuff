package ScrollGame;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Chest {
	Rectangle collision;
	SpriteSheet chestSpriteSheet;
	Image closedChest, openChest;
	Animation chestOpening;
	Renderable chestImage;
	Item item;
	
	Block block;
	private float x, y;
	private boolean  startAnimation;
	static final int NORTH = 0;
	static final int EAST = 1;
	static final int SOUTH = 2;
	static final int WEST = 3;
	private int direction = 0;
	
	// Constructor, initializes everything

	public Chest(Block b) throws SlickException { //onstructer for chest, needs to be placed onto a block
		
		x = y = 0;
		block = b;
		Image chestImageScaledCopy = new Image("res/chest.png").getScaledCopy(2); // get a scaled coppy of the image for the chest
		chestSpriteSheet = new SpriteSheet(chestImageScaledCopy, 64, 64); // sprite sheet chests
		for (int i = 0; i < 4; i++) // set the chests direction so it is facing the way where there isn't a wall
			if (!b.isWallPreasent(i)) {
				direction = i;
				break;
			}
		closedChest = chestSpriteSheet.getSprite(0, direction); // gets the chest image relative to the direction
		openChest = chestSpriteSheet.getSprite(3, direction); // gets the image for an open chest using the direction variable
		int timings[] = { 100, 100, 100, 100 }; // timings for the animation
		Image[] images = { chestSpriteSheet.getSprite(0, direction),
				chestSpriteSheet.getSprite(1, direction),
				chestSpriteSheet.getSprite(2, direction),
				chestSpriteSheet.getSprite(3, direction) };
		chestOpening = new Animation(images, timings, true);
		chestOpening.setLooping(false);
		chestImage = closedChest;

		collision = new Rectangle(x, y, 64, 45); // make a collision box the user can click to open the chest

		initRectangles(direction);
		setRandomItem(); // give the chest a random item
	}

	// sets the item in the chest to a random item.
	public void setRandomItem() {
		while(true){
		item = Play.getItems().get(new Random().nextInt(Play.items.size()));
		if(!Gem.class.isInstance(item))
			break;
		}
	}

	public void draw(Graphics g) { // draws the chest onto the screen
		Color tc = g.getColor();
		if (startAnimation) // if clicked, start the open animation
			g.drawAnimation(chestOpening, x, y);
		else if (!startAnimation) // if not, draw the closed chest image
			g.drawImage(closedChest, x, y);
		if (chestOpening.isStopped()) { // if the animation has stopped, draw the item and draw a string telling the user what item they have just picked up
				g.setColor(Color.white);
				item.draw(g);
				g.drawString("Your have picked up: \n" + item.toString(), x-50, y);
				g.setColor(tc);
		}
	}

	public void update() {  // update the variables of this chest

		
		x = block.getX() + Play.shiftX + 68;
		y = block.getY() + Play.shiftY + 68;
		
		initRectangles(direction);


		if ((Play.character.getCollision().intersects(
				block.getNextBlockByID(direction).floor) || Play.character 			// Check if the character is either on the block the character is, or the other available block
				.getCollision().intersects(block.floor))
				&& collision.contains(Mouse.getX(), Game.HEIGHT - Mouse.getY())		// Check if the Mouse is over the chest
				&& Mouse.isButtonDown(0)											// check if the Mouse is clicked
				&& Play.character.getDirection() == (direction + 2) % 4){
			startAnimation = true;
			item.setCharacter(Play.character);
			item.setX(Play.character.getX());
			item.setY(Play.character.getY());
		}
			
			if(item != null){
				if(item.getCharacter() == null){
				item.setX(x);
				item.setY(y);
				}
			}
	}

	public void initRectangles(int direction) { // Initialise the rectangles so that they are made relative to the direction the chest is facing
		switch (direction) {
		case 0:
			collision = new Rectangle(x, y, 64, 45);
			break;
		case 1:
			collision = new Rectangle(x + 20, y, 45, 64);
			break;
		case 2:
			collision = new Rectangle(x, y + 20, 64, 45);
			break;
		case 3:
			collision = new Rectangle(x + 5, y, 45, 64);
			break;
		}
	}
}
