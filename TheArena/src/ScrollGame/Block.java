package ScrollGame;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Block {
	protected boolean potentialItem;
	protected Rectangle northWall,eastWall,southWall,westWall,floor, turningPoint;
	protected Block northBlock,eastBlock,southBlock,westBlock;
	private float x,y;
	private SpikeTrap trap;
	private Chest chest;
	private EnemyCharacter enemyCharacter;
	public static final int NORTH = 0;
	public static final int EAST = 1;
	public static final int SOUTH = 2;
	public static final int WEST = 3;
	private boolean visited, hasNorthWall,hasWestWall,hasEastWall,hasSouthWall;
	private final int SPIKE_TRAP = 0;
	private final int ENEMY = 1;
	


	public Block(int x, int y) throws SlickException{
		
		visited = false;
		this.x = x*200;
		this.y = y*200;
		visited = false;
		floor = new Rectangle(x,y,200,200);
		northWall = new Rectangle(x,y,200,20);
		southWall =new Rectangle(x,y+180,200,20);
		eastWall =new Rectangle(x+180,y,20,200);
		westWall =  new Rectangle(x,y,20,200);
		turningPoint = new Rectangle(x+95,y+95,10,10);
		hasNorthWall = true;
		hasEastWall = true;
		hasSouthWall = true;
		hasWestWall = true;
		if (new Random().nextInt(20) == 0
				&& !(floor.intersects(Play.character.getCollision()))) // 1 in																	// is in
			trap = new SpikeTrap(getX()+ 20,getY()+ 20);
		if(new Random().nextInt(10) == 0 && !(floor.intersects(Play.character.getCollision())))	// 1in 10 chance of an enemy spawning;
		enemyCharacter = new EnemyCharacter(getX(), getY(),this);
		
		
	}
	public void draw(Graphics g){ // draws graphics onto the screen


		if(new Circle(Play.character.getX(),Play.character.getY(),275).intersects(floor)){ // if the Circle made intersects with the floor, draw the floor, else, dont...
			g.setColor(new Color(150,64,1,30)); // set the colour to a greyish colour
			g.fill(floor); // fill the floor with this colour
			g.setColor(Color.black); // set the colour to black
			
		if(hasNorthWall){
			g.fill(northWall); // draw and fill the wall to the colour set
		}
		if(hasEastWall){ 
			g.fill(eastWall);// draw and fill the wall to the colour set

		}
		if(hasWestWall){
			g.fill(westWall);// draw and fill the wall to the colour set

		}
		if(hasSouthWall){
			g.fill(southWall);// draw and fill the wall to the colour set

		}
		

		}
	
		
		
		if(Play.character.getVision().intersects(floor)){ // if the floor intersects with the characters vision 
			g.setColor(new Color(150,64,1)); // set the colour to a brownish colour
			g.fill(floor); // fill the floor
			g.setColor(Color.lightGray);// set the colour to a light grey
			
		if(hasNorthWall){
		
			g.fill(northWall);	// draw and fill the wall colour to the colour set above
		}
		if(hasEastWall){
		
			g.fill(eastWall);// draw and fill the wall colour to the colour set above

		}
		if(hasWestWall){
		
			g.fill(westWall);// draw and fill the wall colour to the colour set above

		}
		if(hasSouthWall){
		
			g.fill(southWall);// draw and fill the wall colour to the colour set above

		}
		if(trap != null)
			trap.draw(g,x+ Play.shiftX+ 66,y+ Play.shiftY+ 66); // if there is a trap, draw it onto the screen
		
		if(chest != null){
			chest.draw(g); // if there is a chest on this block, draw it onto the screen
		}
	}
		
		
		
	}
	public float getX() { // getter for x
		return x;
	}
	
	public void setX(float x) { // setter for x
		this.x = x;
	}
	public float getY() { // getter for y
		return y;
	}
	public void setY(float y) { // setter for y
		this.y = y;
		
	}
	public void setgridX(int x){ // sets the x position to the grid
		this.x = x*200;
	}
	public void setgridY(int y){ // sets the y position to the grid
		this.y = y*200;
	}
	public Block getNextBlockByID(int ID){ // gets the next block by the Identifier, (North, East, South, West)
		switch(ID){
		case NORTH:
			return northBlock;
		case EAST:
			return eastBlock;
		case SOUTH:
			return southBlock;
		case WEST:
			return westBlock;
		default:
			return null;
		}
	}
	public boolean isBlockPreasent(int ID){ // check if the block is preasent in the direction
		switch(ID){
		case Block.NORTH:
			return (northBlock != null);
		case Block.EAST:
			return (eastBlock != null);
		case Block.WEST:
			return (westBlock != null);
		case Block.SOUTH:
			return (southBlock != null);
		default:
			return false;
		}
	}
	public boolean isVisited() { // check of the current block is visited
		return visited;
	}
	public void setVisited(boolean visited) { // set if the current block ahs been visited
		this.visited = visited;
	}
	public void carve(Block nextBlock, int ID){ // remove a wall wall by ID, place the next block in that direciton and remove that walls in the ipposite direction.
		switch(ID){
		case NORTH:
			if(northBlock == null)
				northBlock = nextBlock;
			hasNorthWall = false;
			if(nextBlock.hasSouthWall)
				nextBlock.carve(this, SOUTH);
			break;
		case EAST:
			if(eastBlock == null)
				eastBlock = nextBlock;
			hasEastWall = false;
			if(nextBlock.hasWestWall)
				nextBlock.carve(this, WEST);
			break;
		case SOUTH:
			if(southBlock == null)
				southBlock = nextBlock;
			hasSouthWall = false;
			if(nextBlock.hasNorthWall)
				nextBlock.carve(this, NORTH);
			break;
		case WEST:
			if(westBlock == null)
				westBlock = nextBlock;
			hasWestWall = false;
			if(nextBlock.hasEastWall)
				nextBlock.carve(this, EAST);
			break;
		}
		
	}
	public boolean contains(float x,float y){ // check if the x and y position is inside this block
		if(southWall !=null)
		if(southWall.contains(x,y))
			return true;
		if(northWall !=null)
		if(northWall.contains(x,y))
			return true;
		if(eastWall !=null)
		if(eastWall.contains(x,y))
			return true;
		if(westWall !=null)
		if(westWall.contains(x,y))
			return true;
		if(floor.contains(x,y))
			return true;
		
		return false;
	}
	public boolean wallContains(float x,float y){ // check if the x and y are tutching or inside the walls of the block
		if(southWall !=null)
		if(southWall.contains(x,y))
			return true;
		if(northWall !=null)
		if(northWall.contains(x,y))
			return true;
		if(eastWall !=null)
		if(eastWall.contains(x,y))
			return true;
		if(westWall !=null)
		if(westWall.contains(x,y))
			return true;
		
		return false;
	}
	public boolean contains(Shape s){ // check if the shape is inside tis current block
		if(southWall != null)
		return(southWall.contains(s));
			
		else if(northWall!=null)
		return(northWall.contains(s));
			
		if(eastWall !=null)
		return (eastWall.contains(s));
			
		if(westWall !=null)
		return(westWall.contains(s));
		if(floor !=null)
		return (floor.contains(s));
		
			return false;
	}
	public boolean intersects(Shape s){ // check if the shape intersects with thsi block.
		if(southWall != null)
		return(southWall.intersects(s));
			
		if(northWall!=null)
		return(northWall.intersects(s));
			
		if(eastWall !=null)
		return (eastWall.intersects(s));
			
		if(westWall !=null)
		return(westWall.intersects(s));
		if(floor !=null)
		return (floor.intersects(s));
			
		
		return false;
	}

	public boolean isWallPreasent(int ID){ // check of the wall is present, identified by ID
		switch(ID){
		
		case NORTH:
			return hasNorthWall;
		case EAST:
			return hasEastWall;
		case SOUTH:
			return hasSouthWall;
		case WEST:
			return hasWestWall;
		default:
			return true;
		}
	}
	public void update() throws SlickException{ // updates the statistics of this block
		if(potentialItem){ // check if there si a potential for a chest to spawn
			if(trap == null && new Random().nextInt(4) == 0){ // initialise chest if there isnt a trap on this block
					chest = new Chest(this);
			}
			potentialItem = false;
		}
		if( chest != null ) // update chest
			chest.update(); 
		else
			chest = null;
		
		floor.setX(x + Play.shiftX); // update x
		floor.setY(y + Play.shiftY); // update y
		
		turningPoint.setX(x+ Play.shiftX + 95); // update the turning point's x
		turningPoint.setY(y + Play.shiftY+ 95);// update the turning point's y
		if(Play.character.getVision().intersects(floor)){//if the characters vision intersects with this block's floor
		if(Play.character.movingUp && northWall.intersects(Play.character.getCollision()) ) //move the character back if it collides with the walls
			Play.shiftY-=Play.character.getSpeed()* Play.delta;
		if(Play.character.movingRight && eastWall.intersects(Play.character.getCollision()) )// ''
			Play.shiftX+=Play.character.getSpeed()* Play.delta;
		if(Play.character.movingDown && southWall.intersects(Play.character.getCollision()) )// ''
			Play.shiftY+=Play.character.getSpeed()* Play.delta;
		if(Play.character.movingLeft && westWall.intersects(Play.character.getCollision()) )//''
			Play.shiftX-=Play.character.getSpeed()* Play.delta;
		
		}
		if(hasNorthWall){
			northWall.setX( x+ Play.shiftX);
			northWall.setY(y + Play.shiftY);
		}
		if(hasEastWall){
			eastWall.setX(x + 180 +Play.shiftX);
			eastWall.setY( y+ Play.shiftY);
		}
		if(hasWestWall){
			westWall.setX(x + Play.shiftX);
			westWall.setY(y + Play.shiftY);
		}
		if(hasSouthWall){
			southWall.setX(x +Play.shiftX);
			southWall.setY( y+180+ Play.shiftY);
		}
		if(trap !=null)
			trap.update();
		
		if(this.enemyCharacter!= null){
			enemyCharacter.setX(x +Play.shiftX + enemyCharacter.getEnemyShiftX());
			enemyCharacter.setY(y +Play.shiftY + enemyCharacter.getEnemyShiftY());

	}
	}

}
