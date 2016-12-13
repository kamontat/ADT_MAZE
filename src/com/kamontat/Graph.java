package com.kamontat;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/8/2016 AD - 10:34 AM
 */
public class Graph {
	private int vertex;
	private int edge;
	private ArrayList<Integer>[] adjList;
	
	@SuppressWarnings("unchecked")
	public Graph(int v) {
		this.vertex = v;
		edge = 0;
		
		adjList = (ArrayList<Integer>[]) new ArrayList[v];
		for (int i = 0; i < v; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
	}
	
	public int getVertex() {
		return vertex;
	}
	
	public int getEdge() {
		return edge;
	}
	
	public void addArc(int u, int v) {
		validateVertex(u);
		validateVertex(v);
		adjList[u].add(v);
		edge++;
	}
	
	public void addEdge(int u, int v) {
		validateVertex(u);
		validateVertex(v);
		adjList[u].add(v);
		adjList[v].add(u);
		edge += 2;
	}
	
	public int degree(int u) {
		validateVertex(u);
		return adjList[u].size();
	}
	
	public ListIterator<Integer> iterator(int u) {
		validateVertex(u);
		return adjList[u].listIterator();
	}
	
	public List<Integer> getAdjList(int u) {
		validateVertex(u);
		return adjList[u];
	}
	
	private void validateVertex(int v) {
		if (v < 0 || v >= vertex)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertex - 1));
	}
	
	public void print() {
		for (int i = 0; i < getVertex(); i++) {
			ListIterator<Integer> li = iterator(i);
			if (li.hasNext()) {
				System.out.print("I: " + i + " -> ");
				while (li.hasNext()) System.out.print(li.next() + ", ");
				System.out.println();
			}
		}
	}
}