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
     *
     * @param word
     * @return
     *
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
     * @param dir
     * @param target
     * @return
     *
     * This method finds a specific file. We use it to find our dictionary file.
     * We might not end up using this file but we made it just in case
     */
    public File findFile(File dir, String target) {
        File[] fileList = dir.listFiles(); //array of files in a directory
        //loops through files and determines if they are a directory or a file
        for (File file : fileList) {
            if (file.isDirectory()) {
                File result = findFile(file, target); //recursive method
                if (result != null) {
                    return result;
                }
            } else {
                if (file.isFile()) {
                    if (file.getName().equals(target)) {
                        return file;
                    }

                }
            }
        }
        return null;
    }

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

    public Boolean wordAvailable() throws IOException {
        File file1 = new File(".");
        File file = findFile(file1, "words.txt");

        if(file.getName().equals("words.txt")){
            return true;
        }
        else{
            return false;
        }
 }

    /**
     * Updates the score based on the length of the word entered
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







