package model.gameMap.additional;

import javafx.beans.property.IntegerProperty;

public class NewMovable {
	private int cellId;
	private int key;
	private IntegerProperty imageValue;
	
	public NewMovable(int key,int cellId,IntegerProperty imageValue) {
		this.key = key;
		this.cellId = cellId;
		this.imageValue = imageValue;
	}
	
	public int getCellId() {
		return this.cellId;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public IntegerProperty getImageValue() {
		return this.imageValue;
	}
}
