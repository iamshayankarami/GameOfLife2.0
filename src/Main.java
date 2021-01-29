package GameOfLife2_0;
import java.util.*;


public class Main {
	public static void main(String[] args) {
		World world = new World();
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		char printingMethod;
		int terms;

		System.out.println("\u001B[32mworld is created ✅ \u001B[0m");
		world.firstStartingCells(100);
		System.out.println("\u001B[32mfirst generated cells created ✅ \u001B[0m");
		world.getWorldData();

		System.out.print("Do you want to show the map for each term? (Y, N): ");
		printingMethod = scanner.next().charAt(0);

		System.out.print("enter the terms number(grater then 100): ");
		terms = scanner.nextInt();

		for (int term=1; term<=terms; term++) {
			if (world.getNumberOfCells() > 300) {
				world.killCells();
				System.out.println("\u001B[31m"+"☠️ killing some cells because of overcrowding ☠️"+"\u001B[0m");
			}
			world.evolution();
			if (world.getNumberOfCells() > 300) {
				world.killCells();
				System.out.println("\u001B[31m"+"☠️ killing some cells because of overcrowding ☠️"+"\u001B[0m");
			}
			world.move();
			if (world.checkLessPopulation()) {
				world.firstStartingCells(10);
				System.out.println("\u001B[33m"+"\uD83D\uDD14Generate new cells.\uD83D\uDD14"+"\u001B[0m");
			}
			if (printingMethod == 'Y') {
				world.getWorldData();
			}
			System.out.println("\u001B[32m"+ "term: " + term + "\u001B[0m");
		}
		if (printingMethod == 'N') {
			world.getWorldData();
		}
		System.out.println("\u001B[31m" + "after " + terms + " terms, the worlds end;" + "\u001B[0m");
	}
}
