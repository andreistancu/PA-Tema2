
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a
 */

public class Graph {
	private int N;
	private int M;
	private int Q;
	public int parent[];
	public int rank[];
	public boolean visited[];
	private long cost;
	private long maxEdgeCost;
	private Edge maxEdge;
	private long costAdded;

	private ArrayList <Edge> edges;
	private ArrayList <Integer> reserves;
	private ArrayList <Edge> ama;
	private ArrayList <ArrayList <Pair>> adjList;
	private int depth[];
	private long costcost[];
	
	static class Pair {
		int toNode;
		int cost;
		
		public Pair(int toNode, int cost) {
			this.toNode = toNode;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "" + toNode;
		}
	}
	
	public Graph() {
		cost = 0;
		edges = new ArrayList <> ();
		reserves = new ArrayList <> ();
		ama = new ArrayList <> ();
		adjList = new ArrayList <> ();
	}

	public void makeSet() {
		for(int x = 0; x < N; x++) {
			parent[x] = x;
			rank[x] = 0;
		}
	}

	public int find(int x) {
		//while(x != parent[x])
		//	x = parent[x];
		//return x;
		if(x != parent[x])
			x = find(parent[x]);
		return x;
	}

	public void getDepth(int x) {
		if(x == parent[x])
			return;
		getDepth(parent[x]);
		depth[x] = depth[parent[x]] + 1;
	}
	
	public void union(int i, int j) {
		int x = find(i);
		int y = find(j);

		//if(rank[x] > rank[y])
		//	parent[y] = x;
		//else
		//	parent[x] = y;
		//if(rank[x] == rank[y])
		//	rank[y]++;
		
		parent[j] = i;
	}

	public long kruskal() {
		
		ArrayList <Edge> copie = (ArrayList <Edge>) edges.clone();
		makeSet();
		Collections.sort(copie);
		for(Edge e : copie) {
			int i = e.getNod1();
			int j = e.getNod2();
			int x = find(i);
			int y = find(j);
			if(x != y) {
				cost += e.getCost();
				ama.add(e);
				//union(i, j);
				parent[x] = y;
				costcost[x] = e.getCost();
			}
		}
		for(int i = 0; i < N; i++)
			for(int j = i; j != parent[j]; j = parent[j])
				depth[i]++;
		return cost;
	}
	
	public long query(int x, int y) {
		long maxim = 0;
		while(x != y) {
			if(depth[x] < depth[y]) {
				int aux = x;
				x = y;
				y = aux;
				maxim = Math.max(costcost[x], maxim);
				x = parent[x];
			}
		}
		return maxim;
	}
	
	public ArrayList <Long> vkruskal() {
		long cost = 0;
		ArrayList <Edge> ama = new ArrayList <> ();
		ArrayList <Edge> copie = new ArrayList <> ();
		ArrayList <Long> costuri = new ArrayList <> ();
		
		for(int k = 0; k < reserves.size(); k++) {
			//copie = (ArrayList <Edge>) edges.clone();
			ama.clear();
			Edge edge = edges.get(reserves.get(k));
			costAdded = edge.getCost();
			if(this.ama.contains(edge)) {
				costuri.add(this.cost);
			}
			else {
				visited = new boolean[N];
				this.ama.add(edge);
				//dfs(edge.getNod1(), -1);
				
				this.ama.remove(edge);
				
				/*cost = edge.getCost();
				copie.remove(edge);
				Collections.sort(copie);
				makeSet();
				union(edge.getNod1(), edge.getNod2());
				for(Edge e : copie) {
					int i = e.getNod1();
					int j = e.getNod2();
					if(find(i) != find(j)) {
						cost += e.getCost();
						union(i, j);
						ama.add(e);
					}
				}
				costuri.add(cost);
				*/
			}
		}
		return costuri;
	}
	
	/*public void dfs(int x, int parent) {
		visited[x] = true;
		for(Pair i : adjList.get(x)) {
			if(!visited[i.toNode]) {
				if(maxEdgeCost < adjList.get(x).get(i.toNode).cost) {
					maxEdgeCost = adjList.get(x).get(i.toNode).cost;
					maxEdge = new Edge(x, i.toNode - 1, (int) maxEdgeCost);
				}
				dfs(i.toNode, x);
			}
			else {
				if(i.toNode != parent) {
					long result = cost - maxEdgeCost + costAdded;
					System.out.println("new Ama cost " + result);
				}
			}
		}
	}*/
	
	/*public void dfs(int x, int parent) {
		visited[x] = true;
		for(int i = 0; i < adjList[x].size(); i++) {
			Pair p = adjList[x].get(i);
			if(!visited[p.toNode]) {
				if(maxEdgeCost < getAdjList(x).get(p.toNode).cost) {
					maxEdgeCost = getAdjList(x).get(p.toNode).cost;
					maxEdge = new Edge(x, p.toNode, maxEdgeCost);
				}
				dfs(p.toNode, x);
			}
			else {
				if(p.toNode != parent) {
					long result = cost - maxEdgeCost + costAdded;
					System.out.println("new AMA cost " + result);
				}
			}
		}
	}*/
	
	public void read(String filename) {
		MyScanner scan = new MyScanner(filename);
		N = scan.nextInt();
		parent = new int[N];
		rank = new int[N];
		depth = new int[N];
		M = scan.nextInt();
		costcost = new long[M];
		Q = scan.nextInt();
		for(int i = 0; i < N; i++)
			adjList.add(new ArrayList <> ());
		for(int i = 0; i < M; i++) {
			int nod1 = scan.nextInt() - 1;
			int nod2 = scan.nextInt() - 1;
			int cost = scan.nextInt();
			Edge edge;
			edge = new Edge(nod1, nod2, cost);
			edge.setIndex(i);
			edges.add(edge);
			adjList.get(nod1).add(new Pair(nod2, cost));
			adjList.get(nod2).add(new Pair(nod1, cost));
		}
		for(int i = 0; i < Q; i++) {
			reserves.add(scan.nextInt() - 1);
		}
	}

	public int getNoNodes() {
		return N;
	}

	public ArrayList <Edge> getEdges() {
		return this.edges;
	}

	public ArrayList <Integer> getRezerves() {
		return this.reserves;
	}
	
	public ArrayList <Edge> getAma() {
		return this.ama;
	}
	
	public ArrayList <Pair> getAdjList(int i) {
		return this.adjList.get(i);
	}
}