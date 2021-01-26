package GOL;

public class CellDataEdith {
		    private String indexData;
			    public CellDataEdith(String inputData) {
						        indexData = inputData;
								    }
				    public int GetCellX() {
							        return Integer.parseInt(indexData.substring(0, indexData.indexOf('|')));
									    }
					    public int GetCellY() {
								        return Integer.parseInt(indexData.substring(indexData.indexOf('|')+1, indexData.length()).substring(0, indexData.substring(indexData.indexOf('|')+1, indexData.length()).indexOf('|')));
										    }
						    public int GetCellColor() {
									        return Integer.parseInt(indexData.substring(indexData.indexOf('|')+1, indexData.length()).substring(indexData.substring(indexData.indexOf('|')+1, indexData.length()).indexOf('|')+1));
											    }
}

