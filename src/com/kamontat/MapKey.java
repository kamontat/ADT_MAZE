package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:31 PM
 */
public enum MapKey {
	START(8, "O"), WALL(5, "#"), EMPTY(0, "."), STOP(9, "X"), MOVE_UP(1, "^"), MOVE_DOWN(2, "v"), MOVE_LEFT(3, ">"), MOVE_RIGHT(4, "<"), NULL(-1, "");
	
	int code;
	String key;
	
	private MapKey(int c, String k) {
		code = c;
		key = k;
	}
	
	static int getCode(String key) {
		for (MapKey mk : MapKey.values()) {
			if (mk.key.equals(key)) return mk.code;
		}
		return NULL.code;
	}
	
	static String getKey(int code) {
		for (MapKey mk : MapKey.values()) {
			if (mk.code == code) return mk.key;
		}
		return NULL.key;
	}
}
