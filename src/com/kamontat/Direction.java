package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/11/2016 AD - 12:14 AM
 */
public enum Direction {
	MOVE_UP(11), MOVE_DOWN(12), MOVE_LEFT(13), MOVE_RIGHT(14);
	
	int code;
	
	private Direction(int c) {
		code = c;
	}
	
	public static Direction by(int code) {
		for (Direction d : Direction.values()) {
			if (d.code == code) return d;
		}
		return null;
	}
}
