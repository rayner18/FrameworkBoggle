package edu.up.cs301.boggle;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;

import static edu.up.cs301.boggle.BoggleHumanPlayer.*;

/**
 * This is our Game State class. This is where all the methods are for Boggle to run.
 *
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleState extends GameState {

    private int playerTurn; //tells which players turn it is
    private int player1Score; //tracks the score of player1
    private int player2Score; //tracks the score of player2
    private ArrayList<String> wordBank; //the current words in the word bank
    private String currentWord; //the current word the player is making
    private boolean timer; //true if the timer is running, false if timer has stopped
    private String[][] gameBoard = new String[4][4]; //a 2D array of all letters on board
    private int[][] selectedLetters = new int[16][2]; //tells where the selected letters are located
    private String[] buttonLetter = new String[16];  //array of the letters available
    private boolean[] buttonPushed = new boolean[16]; //array that tells which button have been pushed


    /**
     * The BoggleState constructor. The heart and soul of Boggle. Constructs the gameState of Boggle.
     */
    public BoggleState() {
        playerTurn = 0;
        player1Score = 0;
        player2Score = 0;
        currentWord = "";
        timer = true;

        int n;
        int z;
        for(z = 0; z < 16; z++){
            buttonLetter[z] = "";
        }
        for(n = 0; n < 16; n++){
            buttonPushed[n] = false;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = ("a");
            }
        }


        wordBank = new ArrayList<String>();
    }

    /**
     * The BoggleState copy constructor. Constructs a deep copy of a passed in gameState.
     *
     * @param state the old boggleState to be copied
     */
    public BoggleState(BoggleState state) {
        playerTurn = state.playerTurn;
        player1Score = state.player1Score;
        player2Score = state.player2Score;
        currentWord = state.currentWord;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j] = state.gameBoard[i][j];
            }
        }
        for (int k = 0; k < 16; k++) {
            selectedLetters[k][0] = state.selectedLetters[k][0];
            selectedLetters[k][1] = state.selectedLetters[k][1];
        }

        int n;
        int z;
        for(z = 0; z < 16; z++){
            buttonLetter[z] = state.buttonLetter[z];
        }
        for(n = 0; n < 16; n++){
            buttonPushed[n] = state.buttonPushed[n];
        }
        wordBank = state.wordBank;
        timer = state.timer;

    }

    public boolean isTimer() {
        return timer;
    }

    public void setTimer(boolean timer) {
        this.timer = timer;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(String[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

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

    public int[][] getSelectedLetters() {
        return selectedLetters;
    }

    private void setSelectedLetters(int[][] selectedLetters){
        int i;
        int j;
        for(i = 0; i<16;i++){
            for(j=0;j<2;j++){
                this.selectedLetters[i][j]= selectedLetters[i][j];
            }
        }
    }

    public boolean[] getButtonPushed(){
        return buttonPushed;
    }
    public void setButtonPushed(boolean[] buttonPushed){
        this.buttonPushed = buttonPushed;
    }
    public String[] getButtonLetter(){
        return buttonLetter;
    }
    public void setButtonLetter(String[] buttonLetter){
        this.buttonLetter = buttonLetter;
    }

    /**
     * Determines if the word is more then 3 letters, which means its playable
     *
     * @param word the word being checked
     */
    public boolean wordLength(String word) {
        if (word.length() < 3) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * removes temporary letter
     * @param word
     * @return
     */
    public String removeLetter(String word){
        if ((word != null) && (word.length() > 0)) {
            word = word.substring(0, word.length() - 1);
            return word;
        }
        return null;
    }

    /**
     * Checks if tile can be removed based on the grounds that it is the last checked tile.
     *
     * @param word            word to be edited
     * @param selectedLetters 2d array for the coordinates of each letter tiles
     *                        the array is 16 ints long to represent each tile. 1 -16. each tile is has a 2
     *                        dimension array to correspond to its row and column. When a tile's row or column is 4,
     *                        it means that it is null.
     */
    public String removeLetter(String word, int[][] selectedLetters) {
        if ((word != null) && (word.length() > 0)) {
            word = word.substring(0, word.length() - 1);
            //4 serves as "null"
            int i = 1;
            for (i = 1; i < 16; i++) {
                if (selectedLetters[i][0] != 4) {
                    continue;
                } else {
                    selectedLetters[i - 1][0] = 4;
                    selectedLetters[i - 1][1] = 4;
                }
            }
            return word;
        }
        return null;
    }

    /**
     * Checks if a word is in the dictionary
     *
     * @param word the word being checked
     * @return a boolean dictating if it is a legal word
     * @throws IOException
     */
    public Boolean inDictionary(String word) throws IOException {
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("./src/main/res/raw/words.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            if (word.equals(line))
                return true;
        }
        return false;

        /**
         * External Citation
         *
         * Date: 15 March 2016
         * Problem: Didn't know how to check for spelling errors.
         * Resource: http://www.java-gaming.org/index.php?topic=28057.0
         * Solution: Used the code from this post.
         */
    }

    /**
     * checks if tile can be removed based on the grounds that it is the last picked tile
     *
     * @param curLetterRow  the row that the new letter is in
     * @param curLetterCol  the col that the new letter is in
     * @param lastLetterRow the row that the last selected letter was in
     * @param lastLetterCol the col that the last selected letter was in
     */
    public Boolean canRemove(int curLetterRow, int curLetterCol,
                             int lastLetterRow, int lastLetterCol) {

        //System.out.println(curLetterRow);
        //System.out.println(curLetterCol);
        //System.out.println(lastLetterRow);
        //System.out.println(lastLetterCol);
        if (curLetterRow == lastLetterRow && curLetterCol == lastLetterCol) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * finds current letter Column
     * @param word
     * @return
     */
    public int getCurLetterCol(String word) {

        if((word.substring(word.length()-1).equals(tile1ButtonLetter)) ||
                (word.substring(word.length()-1).equals(tile2ButtonLetter))||
                (word.substring(word.length()-1).equals(tile3ButtonLetter))||
                (word.substring(word.length()-1).equals(tile4ButtonLetter))){

                    return 0;
                 }
        if(word.substring(word.length()-1).equals(tile5ButtonLetter) ||
                word.substring(word.length()-1).equals(tile6ButtonLetter)||
                word.substring(word.length()-1).equals(tile7ButtonLetter) ||
                word.substring(word.length()-1).equals(tile8ButtonLetter)) {
                    return 1;
        }if((word.substring(word.length()-1).equals(tile9ButtonLetter)) ||
                (word.substring(word.length()-1).equals(tile10ButtonLetter))||
                (word.substring(word.length()-1).equals(tile11ButtonLetter))||
                (word.substring(word.length()-1).equals(tile12ButtonLetter))) {
                    return 2;
        }if((word.substring(word.length()-1).equals(tile13ButtonLetter)) ||
                (word.substring(word.length()-1).equals(tile14ButtonLetter))||
                (word.substring(word.length()-1).equals(tile15ButtonLetter))||
                (word.substring(word.length()-1).equals(tile16ButtonLetter))) {
                     return 3;
        }
        return 4;
    }

    /**
     * finds current letter Row
     * @param word
     * @return
     */
    public int getCurLetterRow(String word) {

        if((word.substring(word.length()-1).equals(tile1ButtonLetter)) ||
                (word.substring(word.length()-1).equals(tile5ButtonLetter))||
                (word.substring(word.length()-1).equals(tile9ButtonLetter))||
                (word.substring(word.length()-1).equals(tile13ButtonLetter))){

            return 0;
        }
        if(word.substring(word.length()-1).equals(tile2ButtonLetter) ||
                word.substring(word.length()-1).equals(tile6ButtonLetter)||
                word.substring(word.length()-1).equals(tile10ButtonLetter) ||
                word.substring(word.length()-1).equals(tile14ButtonLetter)) {
            return 1;
        }if((word.substring(word.length()-1).equals(tile3ButtonLetter)) ||
                (word.substring(word.length()-1).equals(tile7ButtonLetter))||
                (word.substring(word.length()-1).equals(tile11ButtonLetter))||
                (word.substring(word.length()-1).equals(tile15ButtonLetter))) {
            return 2;
        }if((word.substring(word.length()-1).equals(tile4ButtonLetter)) ||
                (word.substring(word.length()-1).equals(tile8ButtonLetter))||
                (word.substring(word.length()-1).equals(tile12ButtonLetter))||
                (word.substring(word.length()-1).equals(tile16ButtonLetter))) {
            return 3;
        }
        return 4;
    }

    /**
     * finds last letter Col
     * @param word
     * @return
     */
    public int getLastCurLetterCol(String word) {
            System.out.println("--------" + word.charAt(word.length()-2));
            String letter = ""+word.charAt(word.length() - 2);

        if((letter.equals(tile1ButtonLetter)) ||
                (letter.equals(tile2ButtonLetter))||
                (letter.equals(tile3ButtonLetter))||
                (letter.equals(tile4ButtonLetter))){

            return 0;
        }
        if(letter.equals(tile5ButtonLetter) ||
                letter.equals(tile6ButtonLetter)||
                letter.equals(tile7ButtonLetter) ||
                letter.equals(tile8ButtonLetter)) {
            return 1;
        }if(letter.equals(tile9ButtonLetter)||
                letter.equals(tile10ButtonLetter)||
                letter.equals(tile11ButtonLetter)||
                letter.equals(tile12ButtonLetter)) {
            return 2;
        }if(letter.equals(tile13ButtonLetter) ||
                letter.equals(tile14ButtonLetter)||
                letter.equals(tile15ButtonLetter)||
                letter.equals(tile16ButtonLetter)) {
            return 3;
        }
        return 4;
    }

    /**
     * finds last letter Row
     * @param word
     * @return
     */
    public int getLastCurLetterRow(String word) {
        String letter = ""+word.charAt(word.length() - 2);

        if(letter.equals(tile1ButtonLetter) ||
                (letter.equals(tile5ButtonLetter))||
                (letter.equals(tile9ButtonLetter))||
                (letter.equals(tile13ButtonLetter))){

            return 0;
        }
        if(letter.equals(tile2ButtonLetter) ||
                letter.equals(tile6ButtonLetter)||
                letter.equals(tile10ButtonLetter) ||
                letter.equals(tile14ButtonLetter)) {
            return 1;
        }if(letter.equals(tile3ButtonLetter) ||
                letter.equals(tile7ButtonLetter)||
                letter.equals(tile11ButtonLetter)||
                letter.equals(tile15ButtonLetter)) {
            return 2;
        }if(letter.equals(tile4ButtonLetter) ||
                letter.equals(tile8ButtonLetter)||
                letter.equals(tile12ButtonLetter)||
                letter.equals(tile16ButtonLetter)) {
            return 3;
        }
        return 4;
    }



    /**
     * adds letter to word
     *
     * @param word            the word to edit
     * @param selectedLetters 2d array for the coordinates of each letter tiles
     * @param curLetterRow    the row that the new letter is in
     * @param curLetterCol    the col that the new col is in
     * @param letter          letter to add to end of word
     */
    public String addLetter(String word, int[][] selectedLetters, int curLetterRow, int curLetterCol, String letter) {

        for(int i = 1; i < 16; i++) {
            for (int j = 1; j < 2; j++) {
                    if (selectedLetters[i][j] == 4) {

                        continue;
                    } else {
                        if (word.length() == 0) {
                            word = letter;
                            selectedLetters[0][0] = curLetterRow;
                            selectedLetters[0][1] = curLetterCol;
                            return word;
                        } else if (word.length() > 0) {

                            word = word.concat(letter);
                            selectedLetters[i][0] = curLetterRow;
                            selectedLetters[i][1] = curLetterCol;
                            return word;
                        }
                    }
                }
            }
        return word;
        }

    /**
     * adds temporary letter
     * @param word
     * @param letter
     * @return
     */
    public String addLetter(String word, String letter){
        if (word.length() == 0) {
            word = letter;
            //selectedLetters[0][0] = curLetterRow;
            //selectedLetters[0][1] = curLetterCol;
            return word;
        } else if (word.length() > 0) {
            word = word.concat(letter);
            //selectedLetters[i][0] = curLetterRow;
            //selectedLetters[i][1] = curLetterCol;
            return word;
        }
        return null;
    }





    /**
     * checks if tile can be added based on the grounds that it has not already been added and
     * it is adjacent to the last tile picked
     *
     * @param selectedLetters 2d array for the coordinates of each letter tiles
     * @param curLetterRow    the row that the new letter is in
     * @param curLetterCol    the col that the new letter is in
     * @param lastLetterRow   the row that the last selected letter was in
     * @param lastLetterCol   the col that the last selected letter was in
     */
    public Boolean canAdd(int[][] selectedLetters, int curLetterRow, int curLetterCol,
                          int lastLetterRow, int lastLetterCol) {

        boolean isSelected = false;
        setSelectedLetters(selectedLetters);
        for (int i = 0; i < 16; i++) {

            if (selectedLetters[i][0] == curLetterRow && selectedLetters[i][1] == curLetterCol) {
                isSelected = true;
            }
        }


        if (isSelected) {
            return false;
        } else if (isCurrentAdjacentToLast
                (lastLetterRow, lastLetterCol, curLetterRow, curLetterCol) == 1) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * Updates the score based on the length of the word entered
     * Words of size 3 and 4 = 1 point
     * 5 = 2 points
     * 6 = 3 points
     * 7 = 5 points
     * 8 or more = 11 points
     * <p/>
     * CAVEAT: Does not take into account who made the move. This is a feature that will be added
     * in later versions of this class.
     *
     * @param word the word the user has submitted
     */
    public int updateScore(String word) {
        int score = 0;

        //Check to see how long the word is
        if (word.length() <= 4 && word.length() >= 3) {
            score = 1;
        } else if (word.length() == 5) {
            score = 2;
        } else if (word.length() == 6) {
            score = 3;
        } else if (word.length() == 7) {
            score = 5;
        } else if (word.length() >= 8) {
            score = 11;
        }
        //Update the player's score
        //player1Score = score;

        //resets the values of the selectedLetters array
        for (int i = 0; i < 16; i++) {
            selectedLetters[i][0] = 4;
            selectedLetters[i][1] = 4;
        }
        return score;
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
     * checks if tile to select is adjacent to last tile picked.
     *
     * @param lastLetterRow the row that the last selected letter was in
     * @param lastLetterCol the col that the last selected letter was in
     * @param curLetterRow  the row that the new letter is in
     * @param curLetterCol  the col that the new letter is in
     */
    public int isCurrentAdjacentToLast(int lastLetterRow,
                                       int lastLetterCol, int curLetterRow, int curLetterCol) {


        int trueOrFalse = 0; //false is 0, true is 1
        //didn't use boolean because it wasn't working


        if (curLetterRow == lastLetterRow - 1) {
            if (curLetterCol == lastLetterCol - 1 || curLetterCol == lastLetterCol || curLetterCol == lastLetterCol + 1) {
                trueOrFalse = 1;
            }
        }

        if (curLetterRow == lastLetterRow) {
            if (curLetterCol == lastLetterCol - 1 || curLetterCol == lastLetterCol + 1) {
                trueOrFalse = 1;
            }
        }

        if (curLetterRow == lastLetterRow + 1) {
            if (curLetterCol == lastLetterCol - 1 || curLetterCol == lastLetterCol || curLetterCol == lastLetterCol + 1) {
                trueOrFalse = 1;
            }
        }


        return trueOrFalse;
    }

    /**
     * @param word Adds a sumbitted correct word to the word bank
     */
    public void addToWordBank(String word) {
        //Adds it to the arrayList
        wordBank.add(word);
    }


}







