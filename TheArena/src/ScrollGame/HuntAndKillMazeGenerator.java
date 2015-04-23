package ScrollGame;

import java.util.Random;

import org.newdawn.slick.SlickException;

public class HuntAndKillMazeGenerator extends Maze {
	int startX, startY;
	Random rand = new Random();

	public HuntAndKillMazeGenerator(int height, int width)
			throws SlickException {
		super(height, width);
		startX = rand.nextInt(width);
		startY = rand.nextInt(height);
	}

	public void generateMaze() { //generate the maze

		System.out.println("Began running");
		int x = startX;
		int y = startY;
		int[] neighbours = new int[4];

		mazeLoop: do {

			// mark the current block as visited
			block[x][y].setVisited(true);

			// check neighbours, count free ones

			int neighbourCount = 0;

			for (int i = 0; i < 4; i++) {
				switch (i) {
				case Block.NORTH:
					if (y > 0 && !block[x][y - 1].isVisited())
						neighbours[neighbourCount++] = i;
					break;
				case Block.EAST:
					if (x < getWidth() - 1 && !block[x + 1][y].isVisited())
						neighbours[neighbourCount++] = i;
					break;
				case Block.SOUTH:
					if (y < getHeight() - 1 && !block[x][y + 1].isVisited())
						neighbours[neighbourCount++] = i;
					break;
				case Block.WEST:
					if (x > 0 && !block[x - 1][y].isVisited())
						neighbours[neighbourCount++] = i;
					break;
				}
			}
			if (neighbourCount > 0) {
				switch (neighbours[rand.nextInt(neighbourCount)]) {
				case Block.NORTH:
//					System.out.println("N");
					block[x][y].carve(block[x][y - 1], Block.NORTH);
					y--;
					break;
				case Block.EAST:
//					System.out.println("E");
					block[x][y].carve(block[x + 1][y], Block.EAST);
					x++;
					break;
				case Block.SOUTH:
//					System.out.println("S");
					block[x][y].carve(block[x][y + 1], Block.SOUTH);
					y++;
					break;
				case Block.WEST:
//					System.out.println("W");
					block[x][y].carve(block[x - 1][y], Block.WEST);
					x--;
					break;
				}
			} else { // enter 'hunt mode'
//				System.out.println("Hunting");
				block[x][y].potentialItem = true;
				for (int i = 0; i < getWidth(); i++) {
					for (int j = 0; j < getHeight(); j++) {
						if (!block[i][j].isVisited()) { // Check every block for
														// a free space
							x = i;
							y = j;

							for (int k = 0; k < 4; k++) {
								switch (k) {
								case Block.NORTH:
									if (y > 0)
										for (int l = 0; l < 4; l++)
											if (!block[x][y - 1]
													.isWallPreasent(l)) {
												block[x][y].carve(
														block[x][y - 1],
														Block.NORTH);
												continue mazeLoop;
											}
									break;
								case Block.EAST:
									if (x < getWidth() - 1)
										for (int l = 0; l < 4; l++)
											if (!block[x + 1][y]
													.isWallPreasent(l)) {
												block[x][y].carve(
														block[x + 1][y],
														Block.EAST);
												continue mazeLoop;
											}
									break;
								case Block.SOUTH:
									if (y < getHeight() - 1)
										for (int l = 0; l < 4; l++)
											if (!block[x][y + 1]
													.isWallPreasent(l)) {
												block[x][y].carve(
														block[x][y + 1],
														Block.SOUTH);
												continue mazeLoop;
											}
									break;
								case Block.WEST:
									if (x > 0)
										for (int l = 0; l < 4; l++)
											if (!block[x - 1][y]
													.isWallPreasent(l)) {
												block[x][y].carve(
														block[x - 1][y],
														Block.WEST);
												continue mazeLoop;
											}
									break;
								}
							}
						}

					}// for loop, test the curent unvisited cell

				} // for loop, test the curent unvisited cell
				System.out.println("FINISHED!");
				break;
			}// end no free neighbours

		} while (true);
	}

}
