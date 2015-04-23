package ScrollGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class Item {
	private Image itemImage;
	private Shape collision;
	float x,y;
	Character character;
	
	
	public Item(String fileLocation,float x, float y) throws SlickException{ //constructor sets up the image and places the itema t the x and y position
		itemImage = new Image(fileLocation).getScaledCopy(2);
		this.x = x;
		this.y = y;
		Play.items.add(this);
	}
	public Image getItemImage() { // getter for the itemImage
		return itemImage;
	}


	public void setItemImage(Image itemImage) { // setter for the item image
		this.itemImage = itemImage;
	}


	public float getX() { //getter for x
		return x;
	}


	public void setX(float x) { // setter for x
		this.x = x;
	}


	public float getY() { //getter for y
		return y;
	}


	public void setY(float y) { //setter for y
		this.y = y;
	}



	public Character getCharacter() { //getter for character
		return character;
	}

	public Shape getCollision() { //getter for collision
		return collision;
	}
	
	public void setCollision(Shape collision) { // seter for collision
		this.collision = collision;
	}


	public void draw(Graphics g){ //draw this item
		
	}
	public void setCharacter(Character character) { // setter for character
		this.character = character;
	}

	public abstract void applyEffect();	 //aabstract method to be inherrited by sub-classes to implement what the ffect of the item is
	
	public void update(){ // update
		if(collision != null && collision.intersects(Play.character.collision)){
				applyEffect();
				collision = null;
		}
		setX(x);
		setY(y);
		
	}
}
