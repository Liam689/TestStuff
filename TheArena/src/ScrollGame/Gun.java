package ScrollGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public class Gun extends WearableItem{
	private long time;
	private int reloadTime;
	private int ammo;
	Bullet b;
	private Animation movingUpAnimation,movingRightAnimation,movingDownAnimation,movingLeftAnimation;
	public Gun(float x, float y) throws SlickException { // constructor, places the gun at the x and y position
		super("res/Sprites/gun-sprites.png", x, y);
		reloadTime = 500;
		time = System.currentTimeMillis();
		this.setItemImage(getItemImage().getSubImage(32, 0, 32, 32));
		Image images[] = new Image[4];
		int[] timing = {200,200,200,200};
		SpriteSheet ss = new SpriteSheet("res/Sprites/gun-sprites.png", 32, 32);
		for(int i = 0; i<3; i++)
			images[i] = ss.getSprite(i, 0).getScaledCopy(2);			// sets up the animation from moving down
		images[3] = images[1];
		this.setDirectionalImages(2, images[1]);
		movingDownAnimation = new Animation(images,timing);
		
		for(int i = 0; i<3; i++)
			images[i] = ss.getSprite(i, 1).getScaledCopy(2);			// sets up the animation for moving left
		images[3] = images[1];
		this.setDirectionalImages(3, images[1]);
		movingLeftAnimation = new Animation(images,timing);
		
		for(int i = 0; i<3; i++)
			images[i] = ss.getSprite(i, 2).getScaledCopy(2);			// sets the animation for moving right
		images[3] = images[1];
		this.setDirectionalImages(1, images[1]);
		movingRightAnimation = new Animation(images,timing);
		
		
		for(int i = 0; i<3; i++)
			images[i] = ss.getSprite(i, 3).getScaledCopy(2);			// set the animation for moving up
		images[3] = images[1];
		this.setDirectionalImages(0, images[1]);
		movingUpAnimation = new Animation(images,timing);
		
		this.movingDownAnimation.setLooping(true);
		this.movingLeftAnimation.setLooping(true);
		this.movingRightAnimation.setLooping(true);
		this.movingUpAnimation.setLooping(true);
		
		
	}
	
	public void shoot(int direction){// shoot the bullet int he direction identifier
		if(System.currentTimeMillis() > time){
			new Bullet(direction);
			time = (System.currentTimeMillis()+reloadTime);
		}

	}
	public void applyEffect() { // apply the effect to the character whn the item is picked up, abstract class from Item
		shoot(getCharacter().getDirection());
		
	}
	@Override
	public void setCharacter(Character c){ // set the character 
		this.character = c;
		c.setGun(this);
	}
	

	public void pickUpItem() { // what happen when the item is picked up
		if( getCollision() != null && character != null&& getCollision().intersects(character.getCollision())){
		movingUpAnimation.setSpeed((character.getSpeed()/30));
		movingLeftAnimation.setSpeed((character.getSpeed()/30));
		movingRightAnimation.setSpeed((character.getSpeed()/30));
		movingDownAnimation.setSpeed((character.getSpeed()/30));
		setCollision(null);
		}
			
	}
	public void draw(Graphics g){ // draw the graphics
		if(this.character != null && !character.isMoving){
				g.drawImage(this.getDirectionalImages(character.getDirection()),x,y);
		}else
		if(character != null)
		switch(character.getDirection()){
		case 0:
			g.drawAnimation(movingUpAnimation,x,y);
		  break;
		case 1:
			g.drawAnimation(movingRightAnimation,x,y);
			break;
		case 2:
			g.drawAnimation(movingDownAnimation, x, y);
			break;
		case 3:
			g.drawAnimation(movingLeftAnimation, x, y);
			break;
		}
	}
	public void update(){// update everything
		
		if(getCollision() != null)
		setCollision(new Rectangle(x,y,32,32));
		pickUpItem();
		
	}
	public String toString(){ // the string value of thsi class
		return "Gun.";
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}
}
