package com.kamontat;

import java.util.*;

import static com.kamontat.Direction.*;

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
	
	Direction direction;
	Pointer currentPosition; // {row, column}
	
	State(Pointer cp) {
		currentPosition = cp;
	}
	
	private static State walk(Pointer cp, Direction dir) {
		State state = new State(cp.clone());
		switch (dir) {
			case MOVE_UP:
				state.currentPosition.row--;
				break;
			case MOVE_DOWN:
				state.currentPosition.row++;
				break;
			case MOVE_LEFT:
				state.currentPosition.column--;
				break;
			case MOVE_RIGHT:
				state.currentPosition.column++;
				break;
		}
		
		state.direction = dir;
		
		return state;
	}
	
	boolean isValidState() {
		return currentPosition.row > 0 && currentPosition.row < map.row() && currentPosition.column > 0 && currentPosition.column < map.column();
	}
	
	boolean isOk() {
		return isValidState() && map.canWalk(currentPosition);
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
		allStates.add(State.walk(currentPosition, MOVE_UP));
		allStates.add(State.walk(currentPosition, MOVE_DOWN));
		allStates.add(State.walk(currentPosition, MOVE_LEFT));
		allStates.add(State.walk(currentPosition, MOVE_RIGHT));
		
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
		return "State{" + "direction=" + direction + ", currentPosition=" + currentPosition + '}';
	}
}
