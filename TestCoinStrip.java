import java.util.Random;
import java.util.Scanner;

public class TestCoinStrip{

    int MIN_NUM_COINS = 3;

    int MAX_GAP_SIZE = 4;

    int[] position;

    private Random r = new Random();

    public TestCoinStrip() {
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
        public TestCoinStrip(int n, int g) {
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
    

    public void generateStrip(int coins, int gap){

        position = new int[coins];

        int size = 0;

        for(int i = 0; i < coins; i++){
            position[i] = size+r.nextInt(gap+1);
            size = position[i] + 1;
        }

    }

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

    public void moveCoin(int coin, int move) {
        position[coin] = position[coin]-move;
    }

    public boolean isGameOver() {
        int numCoins = position.length;
        return position[numCoins-1] == numCoins - 1;
    }


    public String toString(){
        int maxPos = position[position.length-1];
        int i =0;
        int currentCoin = 0;
        String output = "|";

        for (i = 0; i<= maxPos;i++){
            if (position[currentCoin] == i){
                output+= " "+ currentCoin +" |";
            }
            else{
                output+="  |";
            }
        }

        output+= "\n";

        return output;
    }

    public static void main (String args[]) {
        // local variables
        Scanner scan = new Scanner(System.in);
        TestCoinStrip strip;
        int coin = 0;
        int move = 0;
        int player = 0;
    
        // Prompt user for game parameters
        System.out.println("Welcome to CoinStrip!\n");
        System.out.print("Would you like to randomly generate the number of coins? (y/n): ");
        String response = scan.next();
        if (response.equals("y") || response.equals("Y")) {
            strip = new TestCoinStrip();
        }
        else {
            int coins, gap;
            System.out.print("\nPlease enter the number of coins (at least 3): ");
            coins = scan.nextInt();
    
            System.out.print("\nPlease enter size of maximum allowable gap between coins (at least 1): ");
            gap = scan.nextInt();
            strip = new TestCoinStrip(coins, gap);
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