package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:37 PM
 */
class Map {
	private MapKey[] map;
	int start, stop;
	private int row, column;
	
	Map(MapKey[] map, int row, int column, int start, int stop) {
		this.map = map;
		this.start = start;
		this.stop = stop;
		this.row = row;
		this.column = column;
		
		String error = null;
		if (start == 0 && stop == 0) {
			error = "Start Point and End Point";
		} else if (start == 0) {
			error = "Start Point";
		} else if (stop == 0) {
			error = "End Point";
		}
		if (error != null) System.err.println("This map don't have " + error + ", please read other map");
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	void move(int[] parents) throws Exception {
		int current = parents[stop];
		int bc = stop; // before current
		while (!isStart(current)) {
			if (current == bc + column) move(current, MapKey.MOVE_UP);
			else if (current == bc - column) move(current, MapKey.MOVE_DOWN);
			else if (current == bc + 1) move(current, MapKey.MOVE_LEFT);
			else if (current == bc - 1) move(current, MapKey.MOVE_RIGHT);
			bc = current; // update before
			current = parents[current];
		}
	}
	
	private void move(int currentPosition, MapKey dir) {
		map[currentPosition] = dir;
	}
	
	private boolean isStart(int cp) {
		return cp == start;
	}
	
	boolean isEnable() {
		return start != 0 && stop != 0;
	}
	
	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < row * column; i++) {
			if (i != 0 && i % column == 0) {
				output += "\n";
			}
			output += map[i].key;
		}
		
		return output;
	}
}
