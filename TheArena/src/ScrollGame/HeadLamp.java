package ScrollGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class HeadLamp extends WearableItem{
	SpriteSheet imageSprites;
	public HeadLamp( float x, float y) // constructor for headlamp, placing the object at the x and y position
			throws SlickException {
		super("res/head-light.png", x, y);
		imageSprites = new SpriteSheet(new Image("res/head-light.png").getScaledCopy(2),64,64);
		for(int i = 0; i<4; i++)
		this.setDirectionalImages(i, imageSprites.getSprite(i, 0));
		setItemImage(getDirectionalImages(0).getScaledCopy(0.5f));
		
	}

	public void setCharacterImages(Character c){ // set the character images to the new images
		try {
			c.setCharacterSprites(new SpriteSheet(new Image("res/Sprites/playable-character-headlamp-sprites.png"),32,32));
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g){ // draw the item
		if(this.getItemImage()!= null && character != null)
		g.drawImage(this.getDirectionalImages(character.getDirection()), x, y);
	}
	public void applyEffect() {//apply the effect of the object
		character.setVisionRadius(character.getVisionRadius()+75);
		
	}
	
	public void pickUpItem() {// what happens when the item is picked up
		if(character != null && getCollision() != null)
		if(getCollision().intersects(character.getCollision())){
		setCollision(null);
		applyEffect();
		}
	}
	public String toString(){ // string value of this class
		return "Head Lamp.";
		
	}

}
