package edu.up.cs301.boggle;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 *
 * This is our Game State class. This is where all the methods are for Boggle to run.
 * The methods that we implemented and are testing are wordLength, Remove Letter, findFile,
 * inDictionary, wordAvailable, update Score, rotate board, add to wordbank
 */
public class BoggleState extends GameState {

    private int playerTurn; //tells which players turn it is
    private int player1Score;
    private int player2Score;
    private String currentWord; //the current word the player is making
    private ArrayList<String> wordBank; //the current words in the word bank
    private boolean timer; //true if the timer is running, false if timer has stopped
    private String[][] gameBoard = new String[4][4];


    public BoggleState() {
        playerTurn = 0;
        player1Score = 0;
        player2Score = 0;
        currentWord = null;
        timer = true;
        wordBank = new ArrayList<String>();
    }

    public BoggleState(BoggleState state) {
        playerTurn = state.playerTurn;
        player1Score = state.player1Score;
        player2Score = state.player2Score;
        currentWord = state.currentWord;
        wordBank = state.wordBank;
        timer = state.timer;
    }

    public boolean isTimer() {
        return timer;
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
    }

    public String[][] getGameBoard() {return gameBoard;}

    public void setGameBoard(String[][] gameBoard) {this.gameBoard = gameBoard;}

    public int getPlayerTurn() {
        return playerTurn;
    }
    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int getPlayer1Score() {
        return player1Score;
    }

    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> getWordBank() {
        return wordBank;
    }

    public void setWordBank(ArrayList<String> wordBank) {
        this.wordBank = wordBank;
    }

    /**
     * @param word
     * @return
     * determines if the word is more then 3 letters, which means its playable
     */
    public boolean wordLength(String word) {

        if (word.length() < 3) {
            return false;
        } else {
            return true;
        }
    }
    public void removeLetter(String word) {

    }

    /**
     *
     * @param word
     * @return
     * @throws IOException
     *
     * This method finds if a word is in the dictionary. It uses the file "words.txt"
     * which is a file with all the words in the dictionary
     */
    public Boolean inDictionary(String word) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/res/raw/words.txt")));
        String line;
        while ((line = reader.readLine())!= null){
            if(word.equals(line))
                return true;
        }
        return false;
    }

    /**
     * Updates the score based on the length of the word entered
     * Words of size 3 and 4 = 1 point
     *                     5 = 2 points
     *                     6 = 3 points
     *                     7 = 5 points
     *             8 or more = 11 points
     *
     * @param word the word the user has submitted
     */
    public void updateScore(String word) {
        int score = 0;

        //Check to see how long the word is
        if (word.length() <= 4 && word.length() >= 3) {
            score = 1;
        }
        else if (word.length() == 5) {
            score = 2;
        }
        else if (word.length() == 6) {
            score = 3;
        }
        else if (word.length() == 7) {
            score = 5;
        }
        else if (word.length() >= 8) {
            score = 11;
        }
        //Update the player's score
        player1Score = score;
    }

    /**
     * Rotates the board
     *
     * @param board two dimensional array representing the board
     */
    public void rotateBoard(String[][] board) {
        String[][] tmp = new String[4][4];

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                tmp[i][j] = board[4 - j - 1][i]; //Rotates the board
            }
        }
        /**
         * External Citation
         *
         * Date: 15 March 2016
         * Problem: Didn't know how to rotate a two dimensional array.
         * Resource: http://stackoverflow.com/questions/42519/how-do-you-rotate-
         * a-two-dimensional-array
         * Solution: Used the sample code from this post.
         */

        //Copies the rotated board to the existing board
        gameBoard = tmp;
    }

    /**
     *
     * @param word
     * Adds a sumbitted correct word to the word bank
     */
    public void addToWordBank(String word) {
        //Adds it to the arrayList
         wordBank.add(word);
    }
}







