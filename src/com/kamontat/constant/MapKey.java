package com.kamontat.constant;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:31 PM
 */
public enum MapKey {
	START('O'), WALL('#'), EMPTY('.'), STOP('X'), MOVE_UP('^'), MOVE_DOWN('v'), MOVE_LEFT('<'), MOVE_RIGHT('>'), NULL(-1);
	
	int code;
	public String key;
	
	private MapKey(int c) {
		code = c;
		key = String.valueOf((char) c);
	}
	
	public static MapKey by(char c) {
		for (MapKey k : MapKey.values()) {
			if (k.key.equals(String.valueOf(c))) {
				return k;
			}
		}
		return NULL;
	}
}
