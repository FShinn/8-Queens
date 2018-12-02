/*
* Sam Shinn
* October 2017
* Chromosome
* 
* Chromosome object for GA
*/

import java.util.Random;

public class Chromo {
	private int[] genes;
	private int cost;
	private static Random rand = new Random();
	
	
	//////// CONSTRUCTORS
	public Chromo(int len) {
		genes = new int[len];
		for (int i=0; i<genes.length; i++)
			genes[i] = rand(genes.length);
		calculateCost();
	}
	
	private Chromo(Chromo p1, Chromo p2) {
		genes = new int[p1.genes.length];
		for (int g=0; g<genes.length; g++)
			if (rand(2) == 1)
				genes[g] = p1.genes[g];
			else
				genes[g] = p2.genes[g];
		calculateCost();
	}
	
	//////// PUBLIC METHODS
	public int cost() {
		return cost;
	}
	
	public static void rankFitness(Chromo[] chrs) {
		int b;
		Chromo aux;
		for (int c=0; c<chrs.length; c++) {
			b = c;
			while (b > 0 && chrs[b].cost() < chrs[b-1].cost()) {
				aux = chrs[b-1];
				chrs[b-1] = chrs[b];
				chrs[b] = aux;
			}
		}
	}
	
	public static Chromo[] mate(Chromo[] parents) {
		rankFitness(parents);
		Chromo[] children = new Chromo[parents.length];
		int p1,p2;
		for (int c=0; c<children.length; c++) {
			p1 = rand(parents.length);
			p2 = rand(parents.length);
			p1 = p1*p1/parents.length;
			p2 = p1*p1/parents.length;
			children[c] = new Chromo(parents[p1],parents[p2]);
		}
		return children;
	}
	
	public void mutate() {
		genes[rand(genes.length)] = rand(genes.length);
		calculateCost();
	}
	
	public void print() {
		fillBoard().print();
		/*for (int g=0; g<genes.length; g++)
			System.out.printf("|%2d", genes[g]);
		System.out.println("|");*/
	}
	
	//////// PRIVATE METHODS
	private void calculateCost() {
		cost = fillBoard().countCollisions();
	}
	
	private Board fillBoard() {
		Board board = new Board(genes.length);
		//int insertLocation = 0;
		for (int g=0; g<genes.length; g++) {
			//insertLocation = (insertLocation + genes[g])%genes.length;
			//board.placeQueen(insertLocation,g);
			board.placeQueen(genes[g],g);
		}
		return board;
	}
	
	private static int rand(int max) {
		return rand.nextInt(max);
	}
}
