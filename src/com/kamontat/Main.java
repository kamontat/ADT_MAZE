package com.kamontat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	static Graph graph;
	
	public static void main(String[] args) {
		readInput();
		
		State temp = new State(State.map.startPoint());
		
		while (true) {
			List<State> nss = temp.getNextStates();
			if (nss.size() == 0) break;
			
			Pointer currentPosition = nss.get(0).currentPosition;
			
			int dir = nss.get(0).direction;
			
			temp.currentPosition = currentPosition;
			
			State.map.walk(currentPosition, dir);
		}
		
		State.map.print();
		System.out.println();
	}
	
	private void breadthFirstSearch(int id) {
		
	}
	
	private void buildGraph() {
		
	}
	
	private static void readInput() {
		try {
			File file = new File("src/com/testcase/testcase1.txt");
			Scanner input = new Scanner(file);
			
			String[] number = input.nextLine().split(" ");
			int row = Integer.parseInt(number[0]);
			int column = Integer.parseInt(number[1]);
			
			int[][] temp = new int[row][column];
			
			for (int i = 0; i < row; i++) {
				int j = 0;
				for (char chr : input.nextLine().toCharArray()) {
					temp[i][j++] = MapKey.getCode(String.valueOf(chr));
				}
			}
			State.map = new Map(temp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
