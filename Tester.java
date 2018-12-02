/*
* Sam Shinn
* October 2017
* Tester
* 
* Tester for 8-Queens solution algorithms
*/

public class Tester {
	public static void main(String[] args) {
		int size = 8;
		try { size = Integer.parseInt(args[0]); }
		catch (Exception e) {}
		int pop = 100;
		try { pop = Integer.parseInt(args[1]); }
		catch (Exception e) {}
		
		GA algorithm = new GA(pop);
		algorithm.initialPopulation(size);
		algorithm.printBest();
		int generationCount = 0;
		System.out.printf("Generation: %d\n", generationCount++);
		
		while (!algorithm.solved()) {
			algorithm.nextGeneration();
			if (++generationCount % 1000 == 0) {
				algorithm.printBest();
				System.out.printf("Generation: %d\n", generationCount);
			}
		}
		
		algorithm.printBest();
		System.out.printf("Generation: %d\n", generationCount++);
	}
}
