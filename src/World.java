package GameOfLife2_0;
import java.util.*;

public class World {
	public int W = 20;
	public int H = 20;
	private char[][] worldData = new char[H][W];
	private int[][] worldCellsColor = new int[H][W];
	private String[] cells = new String[W * H];
	private int numberOfCells = 0;

	public World() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (i <= W / 2 - 2) {
					if (j <= W / 2 - 2) {
						worldData[i][j] = ' ';
						worldCellsColor[i][j] = 31;
					} else {
						if (j >= W / 2 + 1) {
							worldData[i][j] = ' ';
							worldCellsColor[i][j] = 32;
						} else {
							worldData[i][j] = '0';
							worldCellsColor[i][j] = 37;
						}
					}
				} else {
					if (i >= W / 2 + 1) {
						if (j <= W / 2 - 2) {
							worldData[i][j] = ' ';
							worldCellsColor[i][j] = 33;
						} else {
							if (j >= W / 2 + 1) {
								worldData[i][j] = ' ';
								worldCellsColor[i][j] = 35;
							} else {
								worldData[i][j] = '0';
								worldCellsColor[i][j] = 37;
							}
						}
					} else {
						worldData[i][j] = '0';
						worldCellsColor[i][j] = 37;
					}
				}
			}
		}
	}


	public void getWorldData() {
		char[][] localWorldData = worldData;
		int[][] localWorldDataColors = worldCellsColor;
		if (numberOfCells > 0) {
			for (int element = 0; element < numberOfCells; element++) {
				CellDataEdit edit = new CellDataEdit(cells[element]);
				localWorldData[edit.GetCellX()][edit.GetCellY()] = edit.GetCellShape();
				localWorldDataColors[edit.GetCellX()][edit.GetCellY()] = edit.GetCellColor();
			}
		}

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print("\u001B[" + localWorldDataColors[i][j] + "m" + localWorldData[i][j] + "\u001B[0m");
			}
			System.out.print("\n");
		}
	}


	private void addNewCell(int pX, int pY, int color, char shape) {
		if (pX > W/2+2 || pY<W/2-2) {
			if (pY > H/2+2 || pY < H/2-2) {
				if (numberOfCells + 1 < 250) {
					cells[numberOfCells] = pX + "|" + pY + "|" + color + "|" + shape;
					numberOfCells++;
				}
			}
		}
	}

	private int getColorByPosition(int X, int Y) {
		return worldCellsColor[X][Y];
	}

	private void randomCellPoistion() {
		Random random = new Random();
		int postionX, postionY;
		postionX = random.nextInt(W);
		postionY = random.nextInt(H);
		addNewCell(postionX, postionY, getColorByPosition(postionX, postionY), '*');
	}

	public void firstStartingCells(int numberOfNewCells) {
		int ROUND = 1;
		while (ROUND <= numberOfNewCells) {
			randomCellPoistion();
			ROUND = ROUND + 1;
		}
	}


	public void move() {
		for (int element = 0; element < numberOfCells; element++) {
			Random random = new Random();
			int nextX = random.nextInt(3) - 1;
			int nextY = random.nextInt(3) - 1;
			CellDataEdit edit = new CellDataEdit(cells[element]);
			if (edit.GetCellShape() == '*' || edit.GetCellShape() == '$') {
				if (edit.GetCellX() + nextX >= 0 && edit.GetCellY() + nextY >= 0) {
					if (edit.GetCellX() + nextX < W - 1 && edit.GetCellY() + nextY < H - 1) {
						if (worldData[edit.GetCellX() + nextX][edit.GetCellY() + nextY] == '0') {
							worldData[edit.GetCellX()][edit.GetCellY()] = ' ';
							cells[element] = changeTheValue(edit.GetCellX() + nextX, edit.GetCellY() + nextY, edit.GetCellColor(), '0');
						} else {
							worldData[edit.GetCellX()][edit.GetCellY()] = ' ';
							cells[element] = changeTheValue(edit.GetCellX() + nextX, edit.GetCellY() + nextY, edit.GetCellColor(), edit.GetCellShape());
						}
					}
				}
			}
		}
	}

	private String changeTheValue(int x, int y, int color, char Shape) {
		return x + "|" + y + "|" + color + "|" + Shape;
	}


	public void evolution() {
		for (int element = 0; element < numberOfCells; element++) {
			CellDataEdit edit = new CellDataEdit(cells[element]);
			for (int check = 0; check < numberOfCells; check++) {
				CellDataEdit editCheck = new CellDataEdit(cells[check]);
				if (editCheck.GetCellX() != edit.GetCellX() && editCheck.GetCellY() != edit.GetCellY()) {
					if (edit.GetCellX() + 1 == editCheck.GetCellX() || edit.GetCellX() - 1 == editCheck.GetCellX() || edit.GetCellY() + 1 == editCheck.GetCellY() || edit.GetCellY() - 1 == editCheck.GetCellY()) {
						if (edit.GetCellShape() == '$' || editCheck.GetCellShape() == '$') {
							if (edit.GetCellShape() == '$') {
								if (editCheck.GetCellShape() == '*') {
									int color = ((edit.GetCellColor() - 30) + (editCheck.GetCellColor() - 30)) + 30;
									addNewCell(edit.GetCellX(), edit.GetCellY(), color, '*');
								} else {
									if (editCheck.GetCellShape() == '0') {
										int color = 30 + (edit.GetCellColor() + editCheck.GetCellColor())%10;
										addNewCell(edit.GetCellX(), edit.GetCellY(), color, '*');
										killOneCell(check);
									} else {
										if (editCheck.GetCellShape() == '$') {
											int color = 30 + (edit.GetCellColor() + editCheck.GetCellColor())%10;
											addNewCell(edit.GetCellX(), edit.GetCellY(), color, '$');
										}
									}
								}
							} else {
								if (editCheck.GetCellShape() == '$') {
									if (edit.GetCellShape() == '*') {
										int color = 30 + (edit.GetCellColor() + editCheck.GetCellColor())%10;
										addNewCell(editCheck.GetCellX(), editCheck.GetCellY(), color, '$');
									} else {
										if (edit.GetCellShape() == '0') {
											int color = 30 + (edit.GetCellColor() + editCheck.GetCellColor())%10;
											addNewCell(editCheck.GetCellX(), editCheck.GetCellY(), color, '$');
											killOneCell(element);
										} else {
											if (edit.GetCellShape() == '$') {
												int color = 30 + (edit.GetCellColor() + editCheck.GetCellColor())%10;
												addNewCell(editCheck.GetCellX(), editCheck.GetCellY(), color, '$');
											}
										}
									}
								}
							}
						} else {
							if (edit.GetCellShape() == '*') {
								if (editCheck.GetCellShape() == '0') {
									if (edit.GetCellColor() == editCheck.GetCellColor()) {
										addNewCell(edit.GetCellX(), edit.GetCellY(), edit.GetCellColor(), '$');
									}
								} else {
									if (editCheck.GetCellColor() == '*') {
										if (edit.GetCellColor() == editCheck.GetCellColor()) {
											addNewCell(edit.GetCellX(), edit.GetCellY(), edit.GetCellColor(), '*');
										}
									}
								}
							} else {
								if (editCheck.GetCellShape() == '*') {
									if (edit.GetCellShape() == '0') {
										if (edit.GetCellColor() == editCheck.GetCellColor()) {
											addNewCell(editCheck.GetCellX(), editCheck.GetCellY(), editCheck.GetCellColor(), '$');
										}
									} else {
										if (edit.GetCellShape() == '*') {
											if (edit.GetCellColor() == editCheck.GetCellColor()) {
												addNewCell(editCheck.GetCellX(), editCheck.GetCellY(), editCheck.GetCellColor(), '*');
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public int getNumberOfCells() {
		return numberOfCells;
	}
	public String[] getCells() {
		return cells;
	}

	public void killCells() {
		String[] localCells = new String[H*W];
		int localNumberOfCells=0;
		for (int element=0; element<numberOfCells; element++) {
			CellDataEdit cellInfo = new CellDataEdit(cells[element]);
			if (cellInfo.GetCellY() % 2 != 0) {
				localCells[localNumberOfCells] = cells[element];
				localNumberOfCells++;
			}
		}
		cells = localCells;
		numberOfCells = localNumberOfCells;
	}
	public void killOneCell(int cellNumber) {
		String[] localCells = new String[H*W];
		int localNumberOfCells=0;
		for (int element=0; element<numberOfCells; element++) {
			if (element != cellNumber) {
				localCells[localNumberOfCells] = cells[element];
				localNumberOfCells++;
			}
		}
		cells = localCells;
		numberOfCells = localNumberOfCells;
	}

	public boolean checkLessPopulation() {
		int mainCellsPopulation=0;
		for (int i=0; i<numberOfCells; i++) {
			CellDataEdit cellInfo = new CellDataEdit(cells[i]);
			if (cellInfo.GetCellShape() == '*' || cellInfo.GetCellShape() == '$') {
				mainCellsPopulation++;
			}
		}
		if (mainCellsPopulation < 10) {
			return true;
		} else {
			return false;
		}
	}
}
