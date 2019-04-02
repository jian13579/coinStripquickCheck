// CoinStrip game. 
// Jeannie Albrecht; edited by Bill Lenhart

// FOR TA USE ONLY: NOT TO BE COPIED, PRINTED, OR SHARED!

// Note: This represents one possible solution to the problem.  It is provided
// to give you a sense of the relevant techniques and structures as well as
// reasonable documentation/organization.

import java.util.Random;
import java.util.Scanner;

/**
 * An implementation of a data structure for supporting the Silver Dollar
 * Game.  In this game, some coins are placed in individual squares near the 
 * left end of an infinite strip of paper.  The object is to move the n coins
 * into the n left-most squares.
 * <p>
 * Players take turns, moving the coins according to the following rules:
 * <ol>
 *  <li> Coins may only move to the left.
 *  <li> No coin may be moved past another.
 *  <li> No square may hold more than one coin.
 * </ol>
 * <p>
 * You lose if you cannot move a coin on your turn
 * <p>
   @author Jeannie Albrecht; edited by Bill Lenhart
*/

public class CoinStrip {

    /**
     * Default number of coins
     */
    private final int MIN_NUM_COINS = 3;
    
    /**
     * Default maximum gap size between consecutive coins
     */
    private final int MAX_GAP_SIZE = 4;
    
    /** 
     * The position of the coins.
     */
    private int[] position;

    /** 
     * A random number generator.
     */
    private Random r = new Random();

    /**
     * A constructor to generate a random number of coins with
     * random positions on a random-size coinstrip.
     * Note: There are at least 3 coins.
     */
    public CoinStrip() {
	int numCoins = MIN_NUM_COINS;
	// randomly increment the number of coins
	while (r.nextInt(2) == 1) {
	    numCoins++;
	}

	// build a default strip
	generateStrip(numCoins, MAX_GAP_SIZE);
    }


    /**
     * A constructor to generate n coins with random positions on a random-size coinstrip.
     * Pre-conditions:  n &ge; 3, g &ge; 1.
     *
     * @param n The number of coins
     * @param g The maximum gap size between consecutive coins
     */
    public CoinStrip(int n, int g) {
	if (n < 3) {
	    System.err.print("At least 3 coins must be used.");
	    System.exit(0);
	}
	
	if (g < 1) {
	    System.err.print("Maximum gap must be at least 1.");
	    System.exit(0);
	}
	
	generateStrip(n, g);
    }

    /**
     * A helper method to produce positions for each coin. 
     */
    public void generateStrip(int numCoins, int maxGap) {

	//First create array of positions
	position = new int[numCoins];

	//Now generate positions randomly (no more than maxGap spaces between coins)
	int size = 0;
	for (int i=0; i<numCoins; i++) {
	    position[i]=size+r.nextInt(maxGap+1);
	    size = position[i]+1;
	}
    }

    /**
     * Test if moves are legal.
     * @param coin Which coin to move: 0 &le; coin &lt; numCoins
     * @param move How many spaces to move coin
     *
     * @return True iff move was legal.
     */
    public boolean isLegalMove(int coin, int move) {
	// check for out of range values
	if (move <= 0 || coin < 0 || coin >= position.length ) {
	    return false;
	}
	// check for moving too far
	else if (position[coin]-move < 0) {
	    return false;
	}
	// check for leap-frogging another coin
	else if ((coin > 0) && (position[coin]-move <=  position[coin-1])) {
	    return false;
	}
	// the move is good
	else {
	    return true;
	}
    }

    /**
     * Move coin coin to the left move spaces.
     * @param coin Which coin to move: 0 &le; coin &lt; numCoins
     * @param move How many spaces to move coin
     */
    public void moveCoin(int coin, int move) {
	position[coin] = position[coin]-move;
    }

    /**
     * Test to see if game is over.
     * @return True iff game is over.
     */
    public boolean isGameOver() {
	int numCoins = position.length;
	return position[numCoins-1] == numCoins - 1;
    }

    /**
     * Generate string representing game.
     * @return String representation of coinstrip.
     */
    public String toString() {
	int maxPos = position[position.length-1];
	int i = 0;
	int currentCoin = 0;
	String output = "+";

	for (i=0; i<=maxPos; i++) {
	    output +="---+";
	}
	
	output+="\n|";

	for (i=0; i<=maxPos; i++) {
	    if(position[currentCoin] == i) {
		output+=" "+currentCoin+" |";
		currentCoin++;
	    }
	    else {
		output+="   |";
	    }
	}
	
	output+="\n+";

	for (i=0; i<=maxPos; i++) {
	    output +="---+";
	}
	
	return output;
    }

    
    /**
     * Let user play game
     * @param args: command-line arguments: ignored
     */
    public static void main (String args[]) {
	// local variables
	Scanner scan = new Scanner(System.in);
	CoinStrip strip;
	int coin = 0;
	int move = 0;
	int player = 0;

	// Prompt user for game parameters
	System.out.println("Welcome to CoinStrip!\n");
	System.out.print("Would you like to randomly generate the number of coins? (y/n): ");
	String response = scan.next();
	if (response.equals("y") || response.equals("Y")) {
	    strip = new CoinStrip();
	}
	else {
	    int coins, gap;
	    System.out.print("\nPlease enter the number of coins (at least 3): ");
	    coins = scan.nextInt();

	    System.out.print("\nPlease enter size of maximum allowable gap between coins (at least 1): ");
	    gap = scan.nextInt();
	    strip = new CoinStrip(coins, gap);
	}

	// Play the game
	player=player%2+1; // Player 1 goes first
	while (!strip.isGameOver()) {
	    System.out.println();
	    System.out.println(strip.toString());
	    System.out.print("Player "+player+":   Please enter your move. Coin number? ");
	    coin = scan.nextInt();
	    System.out.print("How many spaces do you want to move Coin "+coin+" left? ");
	    move = scan.nextInt();
	    if (strip.isLegalMove(coin, move)) {
		strip.moveCoin(coin, move);
		player=player%2+1; // next player's move
	    }
	    else
		System.out.println("Illegal move! Go again Player "+player+"!");
	}

	player=player%2+1; // After final move, undo change of player
	System.out.println("Game over! Player "+player+" wins!");
    }

}
