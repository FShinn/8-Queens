/*
* Sam Shinn
* October 2017
* Board
* 
* Board stores locations of queens
*/

public class Board {
	private int[][] board;
	
	public Board(int len) {
		board = new int[len][len];
	}
	
	//////// PUBLIC METHODS
	public void clear() {
		for (int r=0; r<board.length; r++)
			for (int c=0; c<board.length; c++)
				board[r][c] = 0;
	}
	
	public void placeQueen(int r, int c) {
		board[r][c] = 1;
	}
	
	public int countCollisions() {
		int collisions = 0;
		for (int r=0; r<board.length; r++)
			for (int c=0; c<board.length; c++)
				if (board[r][c] == 1)
					collisions += this.countCollisions(r,c);
		return collisions;
	}
	
	public void print() {
		System.out.printf("  ");
		for (int c=0; c<board.length; c++)
			System.out.printf("%2d", c);
		System.out.printf("\n  ");
		for (int c=0; c<board.length; c++)
			System.out.printf("--", c);
		System.out.printf("-\n");
		
		for (int r=0; r<board.length; r++) {
			System.out.printf("%-2d|", r);
			for (int c=0; c<board.length; c++) {
				System.out.printf("%c|", board[r][c] == 1 ? 'x' : ' ');
			}
			System.out.printf("%2d\n", r);
		}
		
		System.out.printf("  ");
		for (int c=0; c<board.length; c++)
			System.out.printf("%2d", c);
		System.out.printf("\n   ");
		for (int c=0; c<board.length; c++)
			System.out.printf("--", c);
		System.out.printf("-\n");
	}
	
	//////// PRIVATE METHODS
	private int countCollisions(int R, int C) {
		int collisions = 0;
		int r, c;
		
		r = R; c = C;
		while (++c < board.length)
			collisions += board[r][c];
		r = R; c = C;
		while (++r < board.length && ++c < board.length)
			collisions += board[r][c];
		r = R; c = C;
		while (++r < board.length)
			collisions += board[r][c];
		r = R; c = C;
		while (++r < board.length && --c >= 0)
			collisions += board[r][c];
		
		return collisions;
	}
}
