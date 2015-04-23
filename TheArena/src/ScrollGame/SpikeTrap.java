package ScrollGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

public class SpikeTrap extends Trap {;
	long time = System.currentTimeMillis();
	public SpikeTrap(float x, float y) throws SlickException { // place the spike trap at the x and y position
	
		setX(x);
		setY(y);
		setVision((Rectangle)new Rectangle(x,y,76,76));
		this.active = (Image) new Image("res/spike-trap-armed.png").getScaledCopy(1.5f);
		this.unactive = (Image) new Image("res/spike-trap-unarmed.png").getScaledCopy(1.5f);
		
	}
	

	public void draw(Graphics g) { // draw the trap
		this.draw(g,getX(),getY());

	}
	public void draw(Graphics g,float x,float y) { // draw the trap at the x and y position given
		getVision().setX(x);
		getVision().setY(y);
		
		g.drawImage((Image) ((getActiveState()) ? (Image)active : (Image)unactive), x,y);
		if(!getActiveState()){
		g.setColor(new Color(150,64,1,150));
		g.fill((Rectangle)getVision());
		}

	}
	private void damagePlayer(){// damage the player
		if(Play.character != null)
		Play.character.setHealth(Play.character.getHealth()-10);

	}

	
	
	public void action() { // abstract method, called in superclass and run when the character collides with he object
			damagePlayer();
	}
	
		
	

}
