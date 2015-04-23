package ScrollGame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Button {
	Rectangle on,off;
	Color isOn,isOff;
	Image buttonImage[];
	String text;
	float x,y;
	private boolean rollover,displayText, vision;
	public Button(String text,int x,int y){ // constructor, sets up the button using rectangles and not an image, rollover
		this.x = x;
		this.y = y;
		on = off = new Rectangle(x,y,100,50);
		isOff = new Color(150,64,1);
		isOn = new Color(140,44,1);
		this.text = text;
		displayText = true;
		vision = true;
	}
	public Button(String text, String imageLoc,int x,int y){ // constructor, sets up the button using an image, rollover
		this.x = x;
		this.y = y;
		on = off = new Rectangle(x,y,100,50);
		isOff = new Color(150,64,1);
		isOn = new Color(140,44,1);
		this.text = text;
		buttonImage = new Image[2];
		for(int i = 0; i<2; i++)
			try {
				buttonImage[i] = new Image("res/Buttons/"+ imageLoc+((i==0)? "-off":"-on")+".png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		vision = true;
	}
	public void hide(){ // hide the button
		vision = false;
		displayText = false;
	}
	public void unHide(){ // unhide the button
		vision = true;
		displayText = true;
	}
	public void draw(Graphics g){//Draw the button
		if(vision)
		if(rollover){ // if the button doesnt have a mouse over
			if(buttonImage != null)
				g.drawImage(buttonImage[1], x, y);
			else{ // else rollover
				g.setColor(isOn);
				g.fill(on);
			}
		}else{
			if(buttonImage != null)
				g.drawImage(buttonImage[0], x, y);
			else{
				g.setColor(isOff);
				g.fill(off);
			}
		}
		g.setColor(Color.white);
		if(displayText)
		g.drawString(text, x, y);
		
		
	}
	public void displayText(boolean allowTextDisplay){//set the text display
		displayText = allowTextDisplay;
	}
	public void update(){ // update the Button
		if(on.contains(Mouse.getX(),Game.HEIGHT-Mouse.getY())){
			if(Mouse.isButtonDown(0))
			Game.menuState.buttonPressed(this);
			rollover = true;
		}else
			rollover = false;
			
		
	}
}
