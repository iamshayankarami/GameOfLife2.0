package GameOfLife2_0;

public class CellDataEdit {
	private String indexData;

	public CellDataEdit(String inputData) {
		indexData = inputData;
	}

	public int GetCellX() {
		return Integer.parseInt(indexData.substring(0, indexData.indexOf('|')));
	}

	public int GetCellY() {
		return Integer.parseInt(indexData.substring(indexData.indexOf('|')+1, indexData.length()).substring(0, indexData.substring(indexData.indexOf('|')+1, indexData.length()).indexOf('|')));
	}

	public int GetCellColor() {
		String procces = indexData.substring(indexData.indexOf('|')+1, indexData.length());
		procces = procces.substring(procces.indexOf('|')+1, procces.length());
		return Integer.parseInt(procces.substring(0, procces.indexOf('|')));
	}
	public char GetCellShape() {
		return indexData.charAt(indexData.length()-1);

	}
}
