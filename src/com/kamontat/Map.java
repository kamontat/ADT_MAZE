package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:37 PM
 */
class Map {
	private int[][] map;
	
	Map(int[][] map) {
		this.map = map;
	}
	
	Pointer startPoint() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == MapKey.START.code) return new Pointer(i, j);
			}
		}
		return new Pointer(-1, -1);
	}
	
	Pointer stopPoint() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == MapKey.STOP.code) return new Pointer(i, j);
			}
		}
		
		return new Pointer(-1, -1);
	}
	
	boolean isStop(Pointer cp) {
		return map[cp.row][cp.column] == MapKey.STOP.code;
	}
	
	boolean canWalk(Pointer cp) {
		return map[cp.row][cp.column] == MapKey.EMPTY.code || map[cp.row][cp.column] == MapKey.STOP.code || map[cp.row][cp.column] == MapKey.START.code;
	}
	
	int row() {
		return map.length;
	}
	
	int column() {
		return map[0].length;
	}
	
	void walk(Pointer cp, Direction dir) {
		if (map[cp.row][cp.column] != MapKey.STOP.code && map[cp.row][cp.column] != MapKey.START.code) {
			System.out.println("MOVE");
			map[cp.row][cp.column] = dir.code;
		}
	}
	
	void print() {
		for (int[] m : map) {
			for (int n : m) {
				System.out.print(MapKey.getKey(n));
			}
			System.out.println();
		}
	}
}
