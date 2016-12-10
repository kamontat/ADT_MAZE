package com.kamontat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	private static Graph graph;
	
	private static int[] parents;
	
	public static void main(String[] args) {
		progress();
		
		//		State temp = new State(State.map.startPoint());
		//
		//		while (true) {
		//			List<State> nss = temp.getNextStates();
		//			if (nss.size() == 0) break;
		//
		//			Pointer currentPosition = nss.get(0).currentPosition;
		//
		//			Direction dir = nss.get(0).direction;
		//
		//			State.map.walk(temp.currentPosition, dir);
		//
		//			temp.currentPosition = currentPosition;
		//		}
		//
		//		State.map.print();
		//		System.out.println();
	}
	
	private static void progress() {
		readInput();
		buildGraph();
		State startState = new State(State.map.startPoint());
		State finalState = new State(State.map.stopPoint());
		
		System.out.println("Start: " + startState.getId());
		System.out.println("End: " + finalState.getId());
		System.out.println();
		
		breadthFirstSearch(finalState.getId());
		
		int v = finalState.getId();
		
		Scanner input = new Scanner(System.in);
		
		int a = input.nextInt();
		
		while (a != -1) {
			State s = State.createFromId(v);
			System.out.println(s);
			System.out.println(v);
			State.map.walk(s.currentPosition, s.direction);
			
			v = parents[v];
			if (v == -1) {
				break;
			}
			
			State.map.print();
			a = input.nextInt();
		}
	}
	
	private static void breadthFirstSearch(int s) {
		List<Integer> nextLevel = new ArrayList<>();
		parents = new int[graph.getVertex()];
		
		for (int u = 0; u < graph.getVertex(); u++) {
			parents[u] = -1;
		}
		
		nextLevel.add(s);
		
		parents[s] = State.createFromId(s).getNextStates().get(0).getId();
		
		while (!nextLevel.isEmpty()) {
			List<Integer> currentLevel = nextLevel;
			nextLevel = new ArrayList<Integer>();
			
			for (int u : currentLevel) {
				for (int possibleWay : graph.getAdjList(u)) {
					if (parents[possibleWay] == -1) {
						parents[possibleWay] = parents[u];
						parents[u] = possibleWay;
						//						System.out.println("index: " + u + ", value: " + possibleWay);
						nextLevel.add(possibleWay);
					}
				}
			}
		}
	}
	
	private static void buildGraph() {
		int size = 40405;
		graph = new Graph(size);
		for (int i = 0; i < size; i++) {
			State s = State.createFromId(i);
			if (s.isValidState() && s.isOk()) {
				List<State> nextStates = s.getNextStates();
				for (State ns : nextStates) {
					System.out.println("index: " + i + ", value: " + ns.getId());
					// i -> current position
					// j -> next position
					graph.addArc(i, ns.getId()/* j */);
				}
			}
		}
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
