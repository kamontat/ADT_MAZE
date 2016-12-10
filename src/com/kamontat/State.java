package com.kamontat;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/10/2016 AD - 1:28 PM
 */
public class State {
	static final int UP = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int RIGHT = 4;
	
	static Map map;
	
	int direction = 0;
	Pointer currentPosition; // {row, column}
	
	State(Pointer cp) {
		currentPosition = cp;
	}
	
	private static State walk(Pointer cp, int i) {
		State state = new State(cp.clone());
		switch (i) {
			case UP:
				state.currentPosition.row--;
				break;
			case DOWN:
				state.currentPosition.row++;
				break;
			case LEFT:
				state.currentPosition.column--;
				break;
			case RIGHT:
				state.currentPosition.column++;
				break;
		}
		state.direction = i;
		
		return state;
	}
	
	private boolean validState() {
		return currentPosition.row > 0 && currentPosition.row < map.row() && currentPosition.column > 0 && currentPosition.column < map.column();
	}
	
	private boolean isOk() {
		return validState() && map.canWalk(currentPosition);
	}
	
	public int getId() {
		return currentPosition.encode();
	}
	
	public static State createFromId(int id) {
		return new State(Pointer.decode(id));
	}
	
	List<State> getNextStates() {
		LinkedList<State> allStates = new LinkedList<>();
		int a = (int) Math.ceil(Math.random() * 4);
		allStates.add(State.walk(currentPosition, UP));
		allStates.add(State.walk(currentPosition, DOWN));
		allStates.add(State.walk(currentPosition, LEFT));
		allStates.add(State.walk(currentPosition, RIGHT));
		
		LinkedList<State> states = new LinkedList<>();
		for (State s : allStates) {
			if (s.isOk()) {
				states.add(s);
			}
		}
		return states;
	}
	
	@Override
	public String toString() {
		return "State{" + "currentPosition=" + currentPosition + '}';
	}
}
