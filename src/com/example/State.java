package com.example;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/8/2016 AD - 10:35 AM
 */
public class State {
	static final int LEFT = 1;
	static final int RIGHT = 2;
	
	public int wolf;
	public int goat;
	public int cabbage;
	public int boat;
	public int stick;
	public int fire;
	
	public State(int w, int g, int c, int b, int s, int f) {
		wolf = w;
		goat = g;
		cabbage = c;
		boat = b;
		stick = s;
		fire = f;
	}
	
	public int opposite(int bank) {
		if (bank == LEFT) {
			return RIGHT;
		} else {
			return LEFT;
		}
	}
	
	public boolean isVariableValid(int v) {
		return (v == LEFT) || (v == RIGHT);
	}
	
	public boolean isValidState() {
		return isVariableValid(wolf) && isVariableValid(goat) && isVariableValid(cabbage) && isVariableValid(boat) && isVariableValid(stick) && isVariableValid(fire);
	}
	
	public boolean isOk() {
		if (!isValidState()) {
			return false;
		}
		
		if ((wolf == goat) && (boat != goat)) return false;
		
		if ((goat == cabbage) && (boat != cabbage)) return false;
		
		if ((stick == wolf) && (boat != wolf)) return false;
		
		if ((fire == stick) && (boat != fire)) return false;
		
		return true;
	}
	
	public int getId() {
		return 100000 * wolf + 10000 * goat + 1000 * cabbage + 100 * boat + 10 * stick + fire;
	}
	
	public static State createFromId(int id) {
		int w = id / 100000;
		int g = (id % 100000) / 10000;
		int c = (id % 10000) / 1000;
		int b = (id % 1000) / 100;
		int s = (id % 100) / 10;
		int f = (id % 10);
		
		return new State(w, g, c, b, s, f);
	}
	
	List<State> getNextStates() {
		LinkedList<State> allStates = new LinkedList<State>();
		
		allStates.add(new State(wolf, goat, cabbage, opposite(boat), stick, fire));
		
		if (wolf == boat) {
			allStates.add(new State(opposite(wolf), goat, cabbage, opposite(boat), stick, fire));
			if (goat == boat) {
				allStates.add(new State(opposite(wolf), opposite(goat), cabbage, opposite(boat), stick, fire));
			}
			if (cabbage == boat) {
				allStates.add(new State(opposite(wolf), goat, opposite(cabbage), opposite(boat), stick, fire));
			}
			if (stick == boat) {
				allStates.add(new State(opposite(wolf), goat, cabbage, opposite(boat), opposite(stick), fire));
			}
			if (fire == boat) {
				allStates.add(new State(opposite(wolf), goat, cabbage, opposite(boat), stick, opposite(fire)));
			}
		}
		if (goat == boat) {
			allStates.add(new State(wolf, opposite(goat), cabbage, opposite(boat), stick, fire));
			if (cabbage == boat) {
				allStates.add(new State(wolf, opposite(goat), opposite(cabbage), opposite(boat), stick, fire));
			}
			if (stick == boat) {
				allStates.add(new State(wolf, opposite(goat), cabbage, opposite(boat), opposite(stick), fire));
			}
			if (fire == boat) {
				allStates.add(new State(wolf, opposite(goat), cabbage, opposite(boat), stick, opposite(fire)));
			}
		}
		if (cabbage == boat) {
			allStates.add(new State(wolf, goat, opposite(cabbage), opposite(boat), stick, fire));
			if (stick == boat) {
				allStates.add(new State(wolf, goat, opposite(cabbage), opposite(boat), opposite(stick), fire));
			}
			if (fire == boat) {
				allStates.add(new State(wolf, goat, opposite(cabbage), opposite(boat), stick, opposite(fire)));
			}
		}
		if (stick == boat) {
			allStates.add(new State(wolf, goat, cabbage, opposite(boat), opposite(stick), fire));
			if (fire == boat) {
				allStates.add(new State(wolf, goat, cabbage, opposite(boat), opposite(stick), opposite(fire)));
			}
		}
		if (fire == boat) {
			allStates.add(new State(wolf, goat, cabbage, opposite(boat), stick, opposite(fire)));
		}
		
		LinkedList<State> states = new LinkedList<State>();
		for (State s : allStates) {
			if (s.isOk()) {
				states.add(s);
			}
		}
		
		return states;
	}
	
	public String toString() {
		String bl = "";
		String br = "";
		
		if (wolf == LEFT) {
			bl += "W ";
			br += "  ";
		} else {
			bl += "  ";
			br += "W ";
		}
		if (goat == LEFT) {
			bl += "G ";
			br += "  ";
		} else {
			bl += "  ";
			br += "G ";
		}
		if (cabbage == LEFT) {
			bl += "C ";
			br += "  ";
		} else {
			bl += "  ";
			br += "C ";
		}
		if (stick == LEFT) {
			bl += "S ";
			br += "  ";
		} else {
			bl += "  ";
			br += "S ";
		}
		if (fire == LEFT) {
			bl += "F ";
			br += "  ";
		} else {
			bl += "  ";
			br += "F ";
		}
		
		if (boat == LEFT) {
			return bl + "| B         | " + br;
		} else {
			return bl + "|         B | " + br;
		}
	}
}