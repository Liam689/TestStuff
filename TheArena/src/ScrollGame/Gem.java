package ScrollGame;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Gem extends Item{
	static int gemsCollected;
	Block block;
	public Gem( float x, float y) throws SlickException { // Constructor, placing the Gem at the x and y position 
		super("res/gem.png", x, y);
		setCollision(new Rectangle(x,y,64,64));
	}
	public Gem( Block b) throws SlickException {// Constructor, placing the Gem at the block passed in the peramiters
		super("res/gem.png", b.getX()+78,b.getY()+78 );
		block = b;
		setCollision(new Rectangle(x,y,64,64));
	}

	public void draw(Graphics g){ // draw the Gem
		if(getCollision() != null)
		g.drawImage(getItemImage(),x,y);
	}
	public void applyEffect() { // Inherited from Item, this is the effect that happens when the item is picked up
		Gem.gemsCollected = Gem.gemsCollected+1;
		if(gemsCollected == LevelContainer.gemsRequired){ // if the gems collected equals the required gems, then try to go to the next level
			try {
				Play.levels.setToNextLevel();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}else{ // if not, place the gem where there isn't a chest 
			while(true){
				if(block != null)
			block =Play.levels.getLevel().block[new Random().nextInt(LevelContainer.WIDTH)][new Random().nextInt(LevelContainer.HEIGHT)];
			if(!block.potentialItem)
				break;
			}
				
			getCollision().setX(block.getX());
			getCollision().setY(block.getY());
			setX(block.getX());
			setY(block.getY());
		}

	}
	
	public void update(){ // update the statistics and effects of the Gem
		if(getCollision() != null && getCollision().intersects(Play.character.collision)){
			applyEffect();
		}
		setX(block.getX()+ Play.shiftX + 50);
		setY(block.getY()+ Play.shiftY+ 50);
		if(getCollision() != null){
		getCollision().setX(x);
		getCollision().setY(y);
		}
	}
	
	public String toString(){ // returnt eh string value of this class.
		return "Gem stone.";
	}
}
