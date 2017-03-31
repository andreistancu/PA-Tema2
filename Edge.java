/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a
 */

public class Edge implements Comparable <Edge> {

	private int nod1;
	private int nod2;
	private long cost;
	private int index;

	public Edge(int nod1, int nod2, long cost) {
		this.nod1 = nod1;
		this.nod2 = nod2;
		this.cost = cost;
	}

	public int getNod1() {
		return this.nod1;
	}

	public int getNod2() {
		return this.nod2;
	}

	public long getCost() {
		return this.cost;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	@Override
	public String toString() {
		return nod1 + " " + nod2 + " " + cost;
	}

	@Override
	public int compareTo(Edge e) {
		return (int) (this.cost - e.cost);
	}
}
