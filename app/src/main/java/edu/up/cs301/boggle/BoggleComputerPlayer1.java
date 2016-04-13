package edu.up.cs301.boggle;

import java.io.IOException;
import java.util.ArrayList;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;
import java.util.Random;

/**
 * The Dumb AI
 * 
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleComputerPlayer1 extends GameComputerPlayer implements BogglePlayer, Tickable {
    BoggleState state;
    BoggleComputerSubmitScoreAction submitScore;
    boolean[][] visited = new boolean[4][4];  //array to see if the tile has been locked at yet
    String[][] board = new String[4][4];//array of all the letters on the board
    ArrayList<String> found = new ArrayList<String>(); // list of all the words found by the computer
    int tested;

    /**
     * constructor
     *
     * @param name the player's name
     */
    public BoggleComputerPlayer1(String name) {
        super(name);
        this.tested = 0;

    }

    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof BoggleState) {
            state = (BoggleState) info;
            visited = state.getVisited(); //array to see if the tile has been locked at yet
            board = state.getGameBoard();
            found = state.getFound();


         //   if(state.getDictionary() == null) {

                System.out.println(" You are inside");

                try {
                    boolean l = state.inDictionary("hi");
                } catch (IOException e) {
                }
                for (int row = 0; row < 4; row++)// goes through all the rows
                {
                    for (int col = 0; col < 4; col++)  // goes through all the columns
                    {
                        //resets all the visited to false
                        for (int i = 0; i < 4; i++) {
                            for (int j = 0; j < 4; j++) {
                                visited[i][j] = false;
                            }
                        }
                        //makes the current location true
                        visited[row][col] = true;
                        //calls the recursive method
                        state.findWords(state.getDictionary(), board, row, col, board[row][col], visited, found);

                   }
                }
             //return;
            //}else {

                //Submits a word every 1 in 5 chance
                Random rand = new Random();
                int random = rand.nextInt(5);
                System.out.println("Random number" + random);
                if (random == 1) {
                    submitScore = new BoggleComputerSubmitScoreAction(this, found);
                    game.sendAction(submitScore);
                } else {
                    return;
                }
            }
        //}
        return;
    }
}

