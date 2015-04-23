package ScrollGame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class StatisticUI {
	private final int HEALTH = 0;
	private final int ENERGY = 1;
	private final int ARMOUR = 2;
	private final int GEM_COUNT = 3;
	Image[] statisticBar;
	Character character;
	public StatisticUI(Character c) throws SlickException{ //constructor, create the UI around the character pased through
		character = c;
		Image bar = new Image("res/statistic-bar.png");
		statisticBar = new Image[4];
		for(int i = 0; i<statisticBar.length; i++)
			statisticBar[i] = bar.getScaledCopy(500, 25);
		
		statisticBar[ARMOUR] = bar.getScaledCopy(150,20);
		statisticBar[GEM_COUNT] = bar.getScaledCopy(100, 20);

		
	}
	public void draw(Graphics g){ // draw evrything
		//health bar
		g.setColor(Color.red);
		g.drawString("HEALTH |--"+ character.getHealth()+"--|", (Game.WIDTH/2 )- statisticBar[HEALTH].getWidth()/2, 375);
		g.fillRect((Game.WIDTH/2 )- statisticBar[HEALTH].getWidth()/2, 400, ((float)character.getHealth()/(float)character.getMaxHealth())*500, 25); // draw a rectangle which will grow/shrink depending on the health (relative to its max health)
		g.drawImage(statisticBar[HEALTH], (Game.WIDTH/2) - statisticBar[HEALTH].getWidth()/2, 400);
		//energy bar
		g.setColor(Color.cyan);
		g.drawString("ENERGY |--"+ character.getEnergy()+"--|", (Game.WIDTH/2 )- statisticBar[HEALTH].getWidth()/2, 425);
		g.fillRect((Game.WIDTH/2 )- statisticBar[HEALTH].getWidth()/2, 450, ((float)character.getEnergy()/(float)character.getMaxEnergy())*500, 25); // draw a rectangle which will grow/shrink depending on the energy (relative to its max energy)
		g.drawImage(statisticBar[ENERGY], (Game.WIDTH/2) - statisticBar[ENERGY].getWidth()/2, 450);
		//armour bar
		g.setColor(Color.yellow);
		g.drawString("ARMOUR |--"+ character.getArmour()+"--|", 10, 375);
		g.fillRect(10, 400, ((float)character.getArmour()/(float)character.getMaxArmour())*150, 20); // draw a rectangle which will grow/shrink depending on the armour (relative to its max armour)
		g.drawImage(statisticBar[ARMOUR], 10, 400);
		// gem Bar
		g.setColor(Color.white);
		g.drawString("Gem count |--"+ Gem.gemsCollected+"/"+LevelContainer.gemsRequired+"--|", 690, 375);
		g.fillRect(690, 400, ((float)Gem.gemsCollected/(float)LevelContainer.gemsRequired)*100, 20); // draw a rectangle which will grow/shrink depending on the gem count (relative to the max gem count )
		g.drawImage(statisticBar[GEM_COUNT], 690, 400);
	}
}
