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
		return row * 10 + column;
	}
	
	static Pointer decode(int code) {
		return new Pointer(code / 10, code % 10);
	}
	
	@Override
	public String toString() {
		return "Pointer{" + "row=" + row + ", column=" + column + '}';
	}
	
	protected Pointer clone() {
		return new Pointer(row, column);
	}
}
