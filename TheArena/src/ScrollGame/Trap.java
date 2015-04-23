package ScrollGame;

import java.util.List;



import java.util.concurrent.TimeUnit;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Shape;

public abstract class Trap {
	private boolean isActive;
	private float x, y;
	 Renderable active, unactive;
	private Shape vision;
	private long timeWhenActive;


	public Trap() {
		timeWhenActive = System.currentTimeMillis();
		

		isActive = false;
		
		x = y = 0;
	}

	

	public Shape getVision() { // vision for the trap
		return vision;
	}

	public void setVision(Shape vision) { // setter for vision
		this.vision = vision;
	}

	public float getX() { //getter for x
		return x;
	}

	public void setX(float x) { // setter for x
		this.x = x;
	}

	public boolean getActiveState() {// get the active state
		return isActive;
	}

	public float getY() { // getter for y
		return y;
	}

	public void setY(float y) { // setter for y
		this.y = y;
	}

	public void activate() { // activates the trap and runs the action caused
							// by the activation
		if((timeWhenActive)+ 1000 < System.currentTimeMillis()){
			
		isActive = true;
		action();
		timeWhenActive = System.currentTimeMillis();
		}
	}

	public void disactivate() { // disactivates the trap
		if((timeWhenActive)+ 3000 < System.currentTimeMillis()) // 3 second delay for trap to disactivate;
		isActive = false;
	}

	public void changeState() {// change the current state
		isActive = !isActive;
	}

	public void draw(Graphics g) { // renders the graphics onto the screen
		draw(g,x,y);

	}
	public void draw(Graphics g,float x, float y) { // renders the graphics onto the screen taking an x and y position
		g.drawImage((Image) ((isActive) ? active : unactive), x, y);
		g.draw(getVision());
	}

	public void update(){ // update the trap
		if(vision.intersects(Play.character.getCollision()))
			activate();
		else{
			disactivate();
		}
		
	}
	public abstract void action();	 // the action that happens when the trap is
									// activated
	
	
	
	
	
}
