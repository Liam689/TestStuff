package ScrollGame;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class Bullet {
	private int direction = 0;
	Image bullet;
	float x;
	float y;
	public Bullet(int direction){ // constructor
		this.direction = direction;
		x = Play.character.getX()+32;
		y = Play.character.getY()+32;
		try {		// rotate the image so its facing the right direction
			bullet = new Image("res/bullet.png");
			switch(direction){
			case 0:
				bullet.rotate(270);
				break;
			case 1:
				bullet.rotate(0);
				break;
			case 2:
				bullet.rotate(90);
				break;
			case 3:
				bullet.rotate(180);
				
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		Play.bullets.add(this); // add to the Arraylist in Play
	}
	public void draw(Graphics g){
		g.drawImage(bullet, x, y); // draw the bullet
	}
	public void checkCollision(Character c){ // check if the bullet has hit a character, has to be an enemy character
		if((c instanceof EnemyCharacter) &&c.getCollision().contains(x,y)){
			Play.removeBullet(this);
			c.takeHeath(15);
		}
	}
	public void update(){ // update the bullet statistics
		for(Character c: Play.characters) // check the collision of the bullet with all the characters on the map
			checkCollision(c);
		
		for(int i = 0; i<Play.levels.getLevel().getWidth();i++)
			for(int j = 0; j<Play.levels.getLevel().getHeight();j++)
				if(Play.levels.getLevel().block[i][j].wallContains(x, y)) // remove the bullet if it hits the block
					Play.bullets.remove(this);
		
		switch(direction){
		case 0:
			y-=(1*Play.delta); // shoot the bullet North
			break;
		case 1:
			x+=(1*Play.delta);// shoot the bullet east
			break;
		case 2:
			y+=(1*Play.delta); // shoot the bullet South
			break;
		case 3:
			x-=(1*Play.delta);// shoot the bullet west
			break;
		}
	}
	
}
