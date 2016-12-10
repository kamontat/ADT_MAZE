package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/11/2016 AD - 12:14 AM
 */
public enum Direction {
	MOVE_UP(1), MOVE_DOWN(2), MOVE_LEFT(3), MOVE_RIGHT(4), STAND(6);
	
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
