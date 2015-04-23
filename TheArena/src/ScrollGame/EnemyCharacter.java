package ScrollGame;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class EnemyCharacter extends PlayableCharacter{
	private int directionID;
	boolean checkBlock;
	private long timeCap;
	private final int STATIONARY = 5;
	private float enemyShiftX,enemyShiftY;
	boolean hasChangedBlock = true;
	Maze maze;
	Block nextBlock;
	public EnemyCharacter(float x, float y,Block b) throws SlickException { // constructor, need to place onto a block.
		super(x, y);// passed to the PlayableCharacter class
		setEnemyShiftX(78); // set  enemies shiftX 
		setEnemyShiftY(78); // set enemies shiftY
		this.setCurrentBlock(b); // set the current block to the one passed through
		setCharacterSprites(new SpriteSheet("res/Sprites/goblin-spritesheet.png", 32, 32));//set up the characters sprites
		init(x,y);
		setSpeed(0.15f);
		setMaxHealth(50);
		setHealth(40);
		setVisionRadius(50);
		setSoundRadius(750);
		setDamage(10);
		directionID = Character.DOWN; // set the first direciton to down
		timeCap = (System.currentTimeMillis())+500;
		nextBlock = getCurrentBlock().getNextBlockByID(directionID);
	}
	
	public void draw(Graphics g){ // draw the character
		if(collision.intersects(Play.character.getVision())){
		this.drawCharacter(directionID, g);
		g.setColor(Color.white);
		g.drawString("Health: "+ getHealth(), getX(), getY());
		}
	}
	public void move(int delta, int ID){ // move the character  relative to the identifier, delta is for updating, making sure the character doesnt move faster/slower depending on computer speed

		isMoving = false;
		switch(ID){
		case Character.UP:
			moveUp(delta);
			break;
		case Character.RIGHT:
			moveRight(delta);
			break;
		case Character.DOWN:
			moveDown(delta);
			break;
		case Character.LEFT:
			moveLeft(delta);
			break;
		case STATIONARY:
			isMoving = false;
			directionID = new Random().nextInt(4);
			break;
		}

	}
	public void move(int delta){ // move the character only passing in through delta
		move(delta, directionID);
	}
	public void moveUp(int delta){
		enemyShiftY -=getSpeed()*delta; // shifts upwards working with character movement
		directionID = Character.UP;
		this.isMoving = true;

	}
	public void moveLeft(int delta){
		enemyShiftX -= getSpeed()*delta; // shifts left working with character movement
		directionID = Character.LEFT;
		this.isMoving = true;

	}	
	public void moveDown(int delta){
		enemyShiftY +=getSpeed()*delta;// shifts downwards working with character movement
		directionID = Character.DOWN;
		this.isMoving = true;

	}	
	public void moveRight(int delta){
		directionID = Character.RIGHT;
		this.isMoving = true;
		enemyShiftX +=getSpeed()*delta; // shifts right working with character movement
	}
	public void update(){ // update the chatacters collision
		if(System.currentTimeMillis()>timeCap){
			timeCap = (System.currentTimeMillis())+500;
			checkCharacterCollision();
		}
		nextBlock = getCurrentBlock().getNextBlockByID(directionID);
		int availableSlots[];
		int amountOfAvailableSlots = 0;
		int currentSlot = 0;

		if(getCurrentBlock().floor.intersects(collision)){ // check if the character is on the current block
		for(int i = 0; i<4;i++)
			if(!getCurrentBlock().isWallPreasent(i))
				amountOfAvailableSlots ++;
		availableSlots = new int[amountOfAvailableSlots];
		for(int i = 0; i<4;i++)
			if(!getCurrentBlock().isWallPreasent(i)){
				availableSlots[currentSlot] = i;
				currentSlot++;
		}
		if(hasChangedBlock)
		directionID = availableSlots[new Random().nextInt(amountOfAvailableSlots)];
		hasChangedBlock = false;
		
		}else{

			if(collision != null && nextBlock != null && collision.intersects(nextBlock.turningPoint))
			updateCurrentBlock(); // if the character isn't on the current block, update the block to its new block.
			hasChangedBlock = true;
		}
			
		move(Play.delta);
		if(hasDied){
			Play.characters.remove(this);
		}
		
	}

	public float getEnemyShiftY() { // get the shiftY variable
		return enemyShiftY;
	}

	public void setEnemyShiftY(float enemyShiftY) { //setter for the enemies shiftY
		this.enemyShiftY = enemyShiftY;
	}

	public float getEnemyShiftX() { // get the shiftX variable
		return enemyShiftX;
	}

	public void setEnemyShiftX(float enemyShiftX) {//setter for the enemies shiftX
		this.enemyShiftX = enemyShiftX;
	}
	public void checkCharacterCollision(){ // check if the character collides with the player
		if(getCollision().intersects(Play.character.getCollision()))
			Play.character.takeHeath(7);
	}
	public void updateCurrentBlock(){ // update the current block the enemy is standing on
				if(getCurrentBlock().isBlockPreasent(directionID) && !getCurrentBlock().floor.contains(getCollision())){//
					setCurrentBlock(getCurrentBlock().getNextBlockByID(directionID));
				}
	}
	 

}
