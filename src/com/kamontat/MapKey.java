package com.kamontat;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:31 PM
 */
public enum MapKey {
	START(1, "O"),
	WALL(2, "#"),
	EMPTY(3, "."),
	STOP(4, "X"),
	MOVE_UP(11, "^"),
	MOVE_DOWN(12, "v"),
	MOVE_LEFT(13, "<"),
	MOVE_RIGHT(14, ">"),
	NULL(-1, "");
	
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
