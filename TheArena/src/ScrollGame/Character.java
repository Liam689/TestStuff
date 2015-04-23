package ScrollGame;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Character {
	
	private int health, armour,visionRadius,energy, soundRadius, damage;
	int maxHealth = 100;
	float lastPosition;
	private int maxEnergy = 100;
	private int maxArmour = 50;
	private int direction;
	private float maxSpeed = 0.7f;
	private float speed;
	protected Shape collision;
	private Circle vision, sound;
	private SpriteSheet character;
	private Animation directionalAnimations[];
	private Image directionalImages[], deadImage;
	protected boolean isRunning, hasDied, movingUp,movingRight,movingDown,movingLeft;
	private Block currentBlock;
	private float x,y;
	protected boolean isMoving;
	private Gun gun;
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int DEAD = 4;
	
	public Character(float x, float y, Block b) throws SlickException{ // set up the character so he spawns into a block
		if(b != null)
		setCurrentBlock(b);
		damage = 25;
		hasDied = false;
		directionalAnimations = new Animation[4];
		directionalImages = new Image[4];
		health = 100;
		setCharacter(new SpriteSheet("res/Sprites/character-sprites.png",32,32));
		speed = 0.2f;
		armour = 50;
		visionRadius = 125;
		soundRadius = 450;
		energy = 100;
		this.x = x-32;
		this.y = y-32;
		vision = new Circle(x,y,visionRadius,15);
		sound = new Circle(x,y,soundRadius,15);
		collision = new Rectangle(x,y,64,64);
		directionalImages[Character.UP] = getCharacterSprites().getSprite(1, 3);
		directionalImages[Character.RIGHT] = getCharacterSprites().getSprite(0, 2);
		directionalImages[Character.DOWN] = getCharacterSprites().getSprite(1, 0);
		directionalImages[Character.LEFT] = getCharacterSprites().getSprite(0, 1);
		deadImage = new Image("res/Sprites/dead-character.png").getScaledCopy(2);
		
		Play.characters.add(this); // add character to the characters list
	}
	public void draw(Graphics g){}// draw method, to be overided by sub-classes
	public int getDamage() { // getter for damage
		return damage;
	}
	public void setDamage(int damage) { // setter for damage
		this.damage = damage;
	}
	public float getMaxSpeed() { // getter for maxSpeed
		return maxSpeed;
	}
	public void getNextBlock(){ // getter for NextBlock
		for(int i = 0; i<Play.levels.WIDTH;i++)
			for(int j = 0; j<Play.levels.HEIGHT; j++)
				if(Play.levels.getLevel().block[i][j].contains(this.collision)){
					setCurrentBlock(Play.levels.getLevel().block[i][j]);
				}
	}
	public void setMaxSpeed(float maxSpeed) {// setter for max speed
		this.maxSpeed = maxSpeed;
	}
	public int getMaxHealth() { // getter for max health
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {// setter for max health
		this.maxHealth = maxHealth;
	}
	public int getMaxEnergy() {// getter for max energy
		return maxEnergy;
	}
	public void setMaxEnergy(int maxEnergy) {// setter for max energy
		this.maxEnergy = maxEnergy;
	}
	public int getMaxArmour() {// getter for max armour
		return maxArmour;
	}
	public void setMaxArmour(int maxArmour) {// setter for max armour
		this.maxArmour = maxArmour;
	}
	public boolean isMovingUp() {//return if the chareacter is moving upwards
		return movingUp;
	}
	public void setMovingUp(boolean movingUp) { // set if the character is moving upwards
		this.movingUp = movingUp;
	}
	public boolean isMovingRight() { // return true if the character if moving right
		return movingRight;
	}
	public void setMovingRight(boolean movingRight) { // set if the chasracter if moving right
		this.movingRight = movingRight;
	}
	public boolean isMovingDown() { // return true if the character is moving down
		return movingDown;
	}
	public void setMovingDown(boolean movingDown) { // set to true if the character is moving down
		this.movingDown = movingDown;
	}
	public boolean isMovingLeft() { // return true if the character is moving left
		return movingLeft;
	}
	public void setMovingLeft(boolean movingLeft) { // set to true if the character is moving left
		this.movingLeft = movingLeft;
	}
	public int getEnergy() {// getter for energy
		return energy;
	}
	public void setEnergy(int energy) { // setter for energy
		this.energy = energy;
		if(energy>maxEnergy)
			this.energy = maxEnergy; // if stateents limits the enrgy to be capped from 0 to max energy
		if(energy<0)
			this.energy = 0;
		
	}
	
	public int getHealth() { // getter for health
		return health;
	}
	public Gun getGun() { // getter for gun object
		return gun;
	}
	public void setGun(Gun gun) { // setter for gun object
		this.gun = gun;
	}
	public void setHealth(int health) { // setter for health
		this.health = health;
		if(this.health<=0){
			this.health = 0; // sets bounderies for the characters health to not go below 0, or above max health... If it is 0, this will also kill the player
			hasDied = true;
		}
		if(this.health>maxHealth){
			this.health = maxHealth;
		}
	}
	public float getSpeed() {// getter for speed
		return speed;
	}
	public void setSpeed(float speed) { // setter for speed
		this.speed = speed;
		if(this.speed<0)
			speed = 0.1f;		//caps the speed so it cannot go over max speed or below 0
		if(this.speed>maxSpeed)
			speed = maxSpeed;
	}
	public Image getDirectionalImages(int ID) {//gets the images relative to the Identifier
		return directionalImages[ID];
	}
	public void setDirectionalImages(Image image, int ID) { // sets the directional images relative to the identifier
		this.directionalImages[ID] = image;
	}
	public int getArmour() {// getter for armour
		return armour;
	}
	public void setArmour(int armour) { // setter for armour
		this.armour = armour;
		if(this.armour<0)
			this.armour = 0;
		if(this.armour>maxArmour)
			this.armour = maxArmour;
	}
	public int getVisionRadius() { // getter for visionRadius
		return visionRadius;
	}
	public void setVisionRadius(int visionRadius) { // setter for visionradius
		this.visionRadius = visionRadius;
		vision = new Circle(x,y,visionRadius,15);
	}
	public Circle getVision() { // getter for vision Circle object
		return vision;
	}
	public void setVision(Circle vision) { // setter for circle vision object
		this.vision = vision;
	}
	public SpriteSheet getCharacterSprites() { // getter for character sprites
		return getCharacter();
	}
	public void setCharacterSprites(SpriteSheet character) { // setter for character sprites
		this.setCharacter(character);
		
	}
	public float getX() { // getter for x
		return x;
	}
	public void setX(float x) { // setter for x
		this.x = x;
		collision.setX(x+8);
	}
	public float getY() {//getter for y
		return y;
	}
	public void setY(float y) {//setter for y
			this.y = y;
			collision.setY(y+20);
	}
	public Shape getCollision() {//getter for collision shape Object
			
		return collision;
	}
	public void setCollision(Shape collision) { // setter for collision shape object
		this.collision = collision;
	}
	public Animation getDirectionalAnimations(int ID) { // get direcitonal animation relative to the identifier
		return directionalAnimations[ID];
	}
	public void setDirectionalAnimations(int ID, Animation a) { // set the directional animation relative to the identifier
		directionalAnimations[ID] = a;
	}
	public Circle getSound() { // getter for sound
		return sound;
	}
	public void setSound(Circle sound) { // setter for sound
		this.sound = sound;
	}
	public int getSoundRadius() { // getter for soundRadius
		return soundRadius;
	}
	public void setSoundRadius(int soundRadius) { // setter for soundRadius
		this.soundRadius = soundRadius;
	}
	public Image getDeadImage() { // getter for deadImage Image object
		return deadImage;
	}
	public void setDeadImage(Image deadImage) { // setter for deadImage Image object
		this.deadImage = deadImage;
	}
	public void takeHeath(int amt){ // take this amount of health from the character
		setHealth(getHealth()-amt);
	}
	public void takeHealth(Character c){
		setHealth(getHealth()-c.getDamage());
	}
	public void update(){ // to be overriden by sub-classes, updates stats
		
		
	}
	public Block getCurrentBlock() { // getter for currentBlock
		return currentBlock;
	}
	public void setCurrentBlock(Block currentBlock) { // setter for currentBlock
		this.currentBlock = currentBlock;
		
	}
	public int getDirection() { // getter for direction
		return direction;
	}
	public void setDirection(int direction) { // setter for direction
		this.direction = direction;
	}
	public SpriteSheet getCharacter() { // getter for character's spritesheet
		return character;
	}
	public void setCharacter(SpriteSheet character) { // setter for character's spritesheet
		this.character = character;
	}
	

}
