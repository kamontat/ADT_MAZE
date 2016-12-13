package com.kamontat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
	private static Graph graph = new Graph(4041);
	private static Map map;
	
	private static int[] parents;
	private static int[] levels;
	
	public static void main(String[] args) {
		progress();
	}
	
	private static void progress() {
		readInput();
		
		breadthFirstSearch(map.start);
		System.out.println(levels[map.stop]);
		
		map.move(parents);
		System.out.print(map.toString());
	}
	
	private static void breadthFirstSearch(int s) {
		List<Integer> nextLevel = new ArrayList<Integer>();
		levels = new int[graph.getVertex()];
		parents = new int[graph.getVertex()];
		
		for (int u = 0; u < graph.getVertex(); u++) {
			levels[u] = -1;
			parents[u] = -1;
		}
		
		levels[s] = 0;
		nextLevel.add(s);
		
		while (!nextLevel.isEmpty()) {
			List<Integer> currentLevel = nextLevel;
			nextLevel = new ArrayList<Integer>();
			
			for (int u : currentLevel) {
				for (int v : graph.getAdjList(u)) {
					if (levels[v] == -1) {
						levels[v] = levels[u] + 1;
						parents[v] = u;
						nextLevel.add(v);
					}
				}
			}
		}
	}
	
	private static void readInput() {
		Scanner input = new Scanner(System.in);
		try {
			File file = new File("src/com/testcase/testcase" + input.nextInt() + ".txt");
			input = new Scanner(file);
			
			String[] number = input.nextLine().split(" ");
			int row = Integer.parseInt(number[0]);
			int column = Integer.parseInt(number[1]);
			
			MapKey[] temp = new MapKey[row * column];
			int id = 0;
			int start = 0, stop = 0;
			
			for (int i = 0; i < row; i++) {
				for (char chr : input.nextLine().toCharArray()) {
					temp[id] = MapKey.by(chr) != MapKey.NULL ? MapKey.by(chr): MapKey.WALL;
					
					if (temp[id] != MapKey.WALL && temp[id + 1] != MapKey.WALL) {
						graph.addEdge(id, id + 1);
					}
					if (temp[id] != MapKey.WALL && temp[id + column] != MapKey.WALL) {
						graph.addEdge(id, id + column);
					}
					if (temp[id] == MapKey.START) {
						start = id;
					} else if (temp[id] == MapKey.STOP) {
						stop = id;
					}
					id++;
				}
			}
			map = new Map(temp, row, column, start, stop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
