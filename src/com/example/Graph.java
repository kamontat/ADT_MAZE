package com.example;

import java.util.*;

/**
 * @author kamontat
 * @version 1.0
 * @since 12/8/2016 AD - 10:34 AM
 */
public class Graph {
	private int n;
	private int m;
	private ArrayList<Integer>[] adjList;
	
	@SuppressWarnings("unchecked")
	public Graph(int n) {
		this.n = n;
		m = 0;
		
		adjList = (ArrayList<Integer>[]) new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList<Integer>();
		}
	}
	
	/**
	 * vertex
	 * @return
	 */
	public int getN() {
		return n;
	}
	
	public int getM() {
		return m;
	}
	
	public void addArc(int u, int v) {
		adjList[u].add(v);
		m++;
	}
	
	public void addEdge(int u, int v) {
		adjList[u].add(v);
		adjList[v].add(u);
		m += 2;
	}
	
	public int degree(int u) {
		return adjList[u].size();
	}
	
	public ListIterator<Integer> iterator(int u) {
		return adjList[u].listIterator();
	}
	
	public List<Integer> getAdjList(int u) {
		return adjList[u];
	}
}