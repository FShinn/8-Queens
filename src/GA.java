/*
* Sam Shinn
* Febuary 2017
* Genetic Algorithm
* 
* GA solution for 8-Queens puzzle
*/

import java.util.Random;

public class GA {
	Chromo[] chroms;
	double mutationRate;
	private static Random rand = new Random();
	
	//////// CONSTRUCTOR
	public GA(int population) {
		chroms = new Chromo[population];
	}
	
	//////// PUBLIC METHODS
	public void initialPopulation(int len) {
		mutationRate = len*0.005;
		for (int i=0; i<chroms.length; i++)
			chroms[i] = new Chromo(len);
	}
	
	public boolean solved() {
		return best().cost() == 0;
	}
	
	public void nextGeneration() {
		chroms = Chromo.mate(chroms);
		
		int mutations = (int)(mutationRate*chroms.length);
		for (int m=0; m<mutations; m++)
			chroms[rand(chroms.length-1)].mutate();
	}
	
	public void printBest() {
		Chromo best = best();
		best.print();
		System.out.println("Cost: " + best.cost());
	}
	
	//////// PRIVATE METHODS
	private Chromo best() {
		Chromo.rankFitness(chroms);
		return chroms[0];
	}
	
	private static int rand(int max) {
		return rand.nextInt(max);
	}
}
