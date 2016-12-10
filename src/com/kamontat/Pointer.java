package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 2:38 PM
 */
public class Pointer {
	int row;
	int column;
	
	public Pointer(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	int encode() {
		return Integer.parseInt(String.format("%02d%02d", row, column));
	}
	
	static Pointer decode(int code) {
		int r = 0, c = 0;
		if (String.valueOf(code).length() == 4) {
			r = Integer.parseInt(String.valueOf(code).substring(0, 2));
			c = Integer.parseInt(String.valueOf(code).substring(2, 4));
		} else if (String.valueOf(code).length() == 3) {
			r = Integer.parseInt(String.valueOf(code).substring(0, 1));
			c = Integer.parseInt(String.valueOf(code).substring(1, 3));
		} else {
			return new Pointer(0, 0);
		}
		
		return new Pointer(r, c);
	}
	
	int move(Pointer next) {
		if (next.row < row && next.column == column) return State.UP;
		if (next.row > row && next.column == column) return State.DOWN;
		if (next.row == row && next.column < column) return State.LEFT;
		if (next.row == row && next.column > column) return State.RIGHT;
		return 0;
	}
	
	@Override
	public String toString() {
		return "Pointer{" + "row=" + row + ", column=" + column + '}';
	}
	
	protected Pointer clone() {
		return new Pointer(row, column);
	}
}
