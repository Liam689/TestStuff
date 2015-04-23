package ScrollGame;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

public abstract class WearableItem extends Item{

	private Image directionalImages[] = new Image[4];
	
	public WearableItem(String fileLocation, float x, float y)
			throws SlickException {
		super(fileLocation, x, y);
		setCollision(new Rectangle(x,y,32,32));
	}


	public abstract void applyEffect();
	public abstract void pickUpItem(); // abstract method for sub-classes 

	public Image[] getDirectionalImages() { // get the directional image array
		return directionalImages;
	}
	public Image getDirectionalImages(int id) { // gett he directional image respectively to the identivier
		return directionalImages[id];
	}
	


	public void setDirectionalImages(Image directionalImages[]) { // set the dimentional image array
		this.directionalImages = directionalImages;
	} 
	public void setDirectionalImages(int location, Image i) { // set the direciton image respectively to the identifier
		this.directionalImages[location] = i;
	} 

	

	public void update(){// updates everything
		if(getCollision() != null)
		setCollision(new Rectangle(x,y,32,32));
		pickUpItem();
		if(getDirectionalImages(Play.character.getDirection())!= null)
			setItemImage(getDirectionalImages(Play.character.getDirection()));
	}

}
