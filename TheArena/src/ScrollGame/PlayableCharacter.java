package ScrollGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class PlayableCharacter extends Character {
	Thread updateStats;

	private float movingSpeed, runningSpeed;
	public PlayableCharacter(float x, float y) throws SlickException {
		super(x, y,null);
		setCharacterSprites(new SpriteSheet(
				"res/Sprites/playable-character-sprites.png", 32, 32));
		init(x,y);

	}
	public void init(float x, float y) throws SlickException{ // Initialises everything
		movingSpeed = getSpeed();
		runningSpeed = getSpeed()*2;
		collision = new Rectangle((x-32)+5,(y-32)+20,50,44);
		updateStats = new Thread(new UpdateStatsThread());
		updateStats.start();
		int[] duration = { (int) (30/getSpeed()), (int) (30/getSpeed()), (int) (30/getSpeed()), (int) (30/getSpeed()) };
		Image imagesRight[] = new Image[4];
		Image imagesDown[] = new Image[4];
		Image imagesUp[] = new Image[4];
		Image imagesLeft[] = new Image[4];
		
		 // change the sprite sheet to the playable characters spreadsheet

		// Initialize images
		setDirectionalImages( getCharacterSprites().getSprite(1, 3).getScaledCopy(2), UP);
		setDirectionalImages( getCharacterSprites().getSprite(1, 2).getScaledCopy(2), RIGHT);
		setDirectionalImages( getCharacterSprites().getSprite(1, 0).getScaledCopy(2), DOWN);
		setDirectionalImages( getCharacterSprites().getSprite(1, 1).getScaledCopy(2), LEFT);
		
		for (int i = 0; i < imagesUp.length-1; i++)
			imagesUp[i] = getCharacterSprites().getSprite(i, 3)
					.getScaledCopy(2);
		for (int i = 0; i < imagesRight.length-1; i++)
			imagesRight[i] = getCharacterSprites().getSprite(i, 2)
					.getScaledCopy(2);
		for (int i = 0; i < imagesDown.length-1; i++)
			imagesDown[i] = getCharacterSprites().getSprite(i, 0)
					.getScaledCopy(2);
		for (int i = 0; i < imagesLeft.length-1; i++)
			imagesLeft[i] = getCharacterSprites().getSprite(i, 1)
					.getScaledCopy(2);
		imagesUp[3] = imagesUp[1];
		imagesRight[3] = imagesRight[1];
		imagesDown[3] = imagesDown[1];
		imagesLeft[3] = imagesLeft[1];
		// set animations for 
		this.setDirectionalAnimations(Character.UP, new Animation(imagesUp,
				duration));
		this.setDirectionalAnimations(Character.RIGHT, new Animation(
				imagesRight, duration));
		this.setDirectionalAnimations(Character.DOWN, new Animation(imagesDown,
				duration));
		this.setDirectionalAnimations(Character.LEFT, new Animation(imagesLeft,
				duration));
		
	}

	public void drawCharacter(int ID, Graphics g) {	// draw the character onto the screen
		switch (ID) {
		case UP:
			if(isRunning && getEnergy()>0){
				setSpeed(runningSpeed);
				getDirectionalAnimations(UP).setSpeed(2);
			}else{
				setSpeed(movingSpeed);
				getDirectionalAnimations(UP).setSpeed(1);
			}
			if (getDirectionalAnimations(UP) != null)
				if(isMoving)
				g.drawAnimation(getDirectionalAnimations(UP), getX(), getY());
				else
					g.drawImage(getDirectionalImages(UP), getX(), getY());
				
			break;

		case RIGHT:
			if(isRunning && getEnergy()>0){
				setSpeed(runningSpeed);
				getDirectionalAnimations(RIGHT).setSpeed(2);
			}else{
				setSpeed(movingSpeed);
				getDirectionalAnimations(RIGHT).setSpeed(1);
			}
			if (getDirectionalAnimations(RIGHT) != null)
				if(isMoving)
				g.drawAnimation(getDirectionalAnimations(RIGHT), getX(), getY());
				else
					g.drawImage(getDirectionalImages(RIGHT), getX(), getY());
			break;
		case DOWN:
			if(isRunning && getEnergy()>0){
				setSpeed(runningSpeed);
				getDirectionalAnimations(DOWN).setSpeed(2);
			}else{
				setSpeed(movingSpeed);
				getDirectionalAnimations(DOWN).setSpeed(1);
			}
			if (getDirectionalAnimations(DOWN) != null)
				if(isMoving)
				g.drawAnimation(getDirectionalAnimations(DOWN), getX(), getY());
			else
				g.drawImage(getDirectionalImages(DOWN), getX(), getY());
			break;
		case LEFT:
			if(isRunning && getEnergy() > 0){
				setSpeed(runningSpeed);
				getDirectionalAnimations(LEFT).setSpeed(2);
			}else{
				setSpeed(movingSpeed);
				getDirectionalAnimations(LEFT).setSpeed(1);
			}
			if (getDirectionalAnimations(LEFT) != null)
				if(isMoving)
				g.drawAnimation(getDirectionalAnimations(LEFT), getX(), getY());
			else
				g.drawImage(getDirectionalImages(LEFT), getX(), getY());
			break;
		case DEAD:
			g.drawImage(this.getDeadImage(),getX(),getY());
			break;
		}
//		g.fill(getCollision());
	}

	public void setCharacterSprites(SpriteSheet character) { //set the characyer sprites
		this.setCharacter(character);
		
		int[] duration = { (int) (30/getSpeed()), (int) (30/getSpeed()), (int) (30/getSpeed()), (int) (30/getSpeed()) };
		Image imagesRight[] = new Image[4];
		Image imagesDown[] = new Image[4];
		Image imagesUp[] = new Image[4];
		Image imagesLeft[] = new Image[4];
		 // change the sprite sheet to the playable characters spreadsheet

		// Initialize images
		setDirectionalImages( getCharacterSprites().getSprite(1, 3).getScaledCopy(2), UP);
		setDirectionalImages( getCharacterSprites().getSprite(1, 2).getScaledCopy(2), RIGHT);
		setDirectionalImages( getCharacterSprites().getSprite(1, 0).getScaledCopy(2), DOWN);
		setDirectionalImages( getCharacterSprites().getSprite(1, 1).getScaledCopy(2), LEFT);
		
		for (int i = 0; i < imagesUp.length-1; i++)
			imagesUp[i] = getCharacterSprites().getSprite(i, 3)
					.getScaledCopy(2);
		for (int i = 0; i < imagesRight.length-1; i++)
			imagesRight[i] = getCharacterSprites().getSprite(i, 2)
					.getScaledCopy(2);
		for (int i = 0; i < imagesDown.length-1; i++)
			imagesDown[i] = getCharacterSprites().getSprite(i, 0)
					.getScaledCopy(2);
		for (int i = 0; i < imagesLeft.length-1; i++)
			imagesLeft[i] = getCharacterSprites().getSprite(i, 1)
					.getScaledCopy(2);
		imagesUp[3] = imagesUp[1];
		imagesRight[3] = imagesRight[1];
		imagesDown[3] = imagesDown[1];
		imagesLeft[3] = imagesLeft[1];
		// set animations for 
		this.setDirectionalAnimations(Character.UP, new Animation(imagesUp,
				duration));
		this.setDirectionalAnimations(Character.RIGHT, new Animation(
				imagesRight, duration));
		this.setDirectionalAnimations(Character.DOWN, new Animation(imagesDown,
				duration));
		this.setDirectionalAnimations(Character.LEFT, new Animation(imagesLeft,
				duration));
		
	}
	private class UpdateStatsThread implements Runnable{ 

		int regenCap = 0;
		public void run() {
			while(true){ // Infinite loop. saves processing as it stops this method from being run in the Update class, therefore runs less and uses less processing
				regenCap++; // Increments the regencap 
				try {
					if(isRunning && isMoving) // is the character has shiftDown and is moving, take 1 off energy
					setEnergy(getEnergy()-1);
					
					if(regenCap%10 == 0){ // makes the regeneration of energy 10 times slower then it is beign lost
						setEnergy(getEnergy()+1);
					}
					if(regenCap%20==0)
						if(getHealth()<maxHealth && !hasDied)
						setHealth(getHealth()+1);
					
					regenCap = regenCap%100; //stops regenCap from going over the limit of an integer
					Thread.sleep(100); 	// run this loop every 1/10th of a second
				} catch (InterruptedException e){
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
}
