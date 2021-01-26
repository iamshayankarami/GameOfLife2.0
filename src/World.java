package GOL;
import java.util.*;

public class World {
    public int W = 20;
    public int H = 20;
    private char[][] worldData = new char[H][W];
    private String[][] worldCellsData = new String[H][W];
    private String[] cellsInfo;

    public World() {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                if (i<=W/2-2) {
                    if (j<=W/2-2) {
                        worldData[i][j] = '#';
                        worldCellsData[i][j] = "\u001B[31m";
                    } else {
                        if (j>=W/2+1) {
                            worldData[i][j] = '#';
                            worldCellsData[i][j] = "\u001B[32m";
                        } else {
                            worldData[i][j] = '0';
                            worldCellsData[i][j] = "\u001B[37m";
                        }
                    }
                } else {
                    if (i>=W/2+1) {
                        if (j<=W/2-2) {
                            worldData[i][j] = '#';
                            worldCellsData[i][j] = "\u001B[33m";
                        } else {
                            if (j>=W/2+1) {
                                worldData[i][j] = '#';
                                worldCellsData[i][j] = "\u001B[35m";
                            } else {
                                worldData[i][j] = '0';
                                worldCellsData[i][j] = "\u001B[37m";
                            }
                        }
                    } else {
                        worldData[i][j] = '0';
                        worldCellsData[i][j] = "\u001B[37m";
                    }
                }
            }
        }
    }


    public void getWorldData() {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                System.out.print(worldCellsData[i][j] + worldData[i][j] + "\u001B[0m");
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

    private void randomCellPoistion() {
        Random random = new Random();
        int postionX, postionY;
        postionX = random.nextInt(W - 1) + 1;
        postionY = random.nextInt(H - 1) + 1;
        if (postionX <= W / 2 - 2) {
            if (postionY >= H / 2 + 1) {
                changeWorldData(postionX, postionY, '*');
            } else {
                if (postionY <= H / 2 - 2) {
                    changeWorldData(postionX, postionY, '*');
                }
            }
        }
        if (postionX >= W / 2 + 1) {
            if (postionY >= H / 2 + 1) {
                changeWorldData(postionX, postionY, '*');
            } else {
                if (postionY <= H / 2 - 2) {
                    changeWorldData(postionX, postionY, '*');
                }
            }
        }
    }

    public void firstTenCell() {
        int ROUND = 1;
        while (ROUND <= 10) {
            randomCellPoistion();
            ROUND = ROUND + 1;
        }
    }

    public int[] moves(int cellX, int cellY) {
        //this method is for moves for all the cells in the map
    }

    public void evoulotion(String cellOneInfo, String cellTwoInfo) {
        //this method is for evoulutions in the map
    }

    public void gamePlay() {
        //this method is the gameplay method and it is for all of the moves for all of the cells and proccess all evoulutions.
    }
}
