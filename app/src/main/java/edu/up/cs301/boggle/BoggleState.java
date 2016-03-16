package edu.up.cs301.boggle;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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


    public boolean wordLength(String word) {
        return word.length() >= 3;
    }

    public void removeLetter(ArrayList<String> word) {
        word.remove(word.size() - 1);

    }

    public boolean findFile(File dir, String target) {
        File[] fileList = dir.listFiles();
        for (File file : fileList) {
            if (file.isDirectory()) {
                boolean result = findFile(file, target);
                if (result) {
                    return true;
                }
            } else {
                if (file.isFile()) {
                    if (file.getName().contains(target)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    //public void inDictionary(File file, Resources resources){
    public HashMap inDictionary(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader(file.getName()));

        HashMap<String, String> map = new HashMap<String, String>();

        while (scanner.hasNextLine()) {
            String[] columns = scanner.nextLine().split(" ");
            map.put(columns[0], columns[1]);
        }

        // System.out.println(map);
        return map;
    }

    public boolean wordAvailable() throws FileNotFoundException {
        File file1 = new File(".");
        boolean file = findFile(file1, "words.txt");
        return file;
        //HashMap map = inDictionary(file);


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

    public void addToWordBank(String word) {
        //Adds it to the arrayList
         wordBank.add(word);
    }


}







