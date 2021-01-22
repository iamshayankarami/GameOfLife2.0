package src;

public class World {
	public int W = 10;
	public int H = 10;
	private char[][] worldData = new char[H][W];

	public void World() {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				worldData[i][j] = '*';
			}
		}
	}

	public void getWorldData() {
		for (int i=0; i<H; i++) {
			for (int j=0; j<W; j++) {
				if (i<=3) {
					if (j<=3) {
						System.out.print("\u001B[31m"+"*" + "\u001B[0m");
					} else {
						if (j>=6) {
							System.out.print("\u001B[32m"+"*" + "\u001B[0m");
						} else {
							System.out.print("\u001B[37m"+"0"+"\u001B[0m");
						}
					}
				} else {
					if (i>=6) {
						if (j<=3) {
							System.out.print("\u001B[33m"+"*"+"\u001B[0m");
						} else {
							if (j>=6) {
								System.out.print("\u001B[35m"+"*"+"\u001B[0m");
							} else {
								System.out.print("\u001B[37m"+"0"+"\u001B[0m");
							}
						}
					} else {
						System.out.print("\u001B[37m"+"0"+"\u001B[0m");
					}
				}
			}
			System.out.print("\n");
		}
	}
	public char[][] getWorldDataByte() {
		return worldData;
	}
	public void changeWorldData(int positionH, int positionW, char value) {
		worldData[positionH][positionW] = value;
	}
}
