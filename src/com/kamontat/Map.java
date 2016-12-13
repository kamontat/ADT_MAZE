package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:37 PM
 */
class Map {
	private MapKey[] map;
	int start, stop;
	int row, column;
	
	Map(MapKey[] map, int row, int column, int start, int stop) {
		this.map = map;
		this.start = start;
		this.stop = stop;
		this.row = row;
		this.column = column;
	}
	
	void move(int currentPosition, MapKey dir) {
		map[currentPosition] = dir;
	}
	
	boolean isStart(int cp) {
		return cp == start;
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
