package edu.up.cs301.boggle;


import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
    private String wordBank; //the current words in the word bank
    private boolean timer; //true if the timer is running, false if timer has stopped

    private String[][] board = new String[4][4];

    public BoggleState() {
        playerTurn = 0;
        player1Score = 0;
        player2Score = 0;
        currentWord = null;
        wordBank = null;
        timer = true;

        board[0][0] = ("a");
        board[0][1] = ("a");
        board[0][2] = ("a");
        board[0][3] = ("a");
        board[1][0] = ("a");
        board[1][1] = ("a");
        board[1][2] = ("a");
        board[1][3] = ("a");
        board[2][0] = ("a");
        board[2][1] = ("a");
        board[2][2] = ("a");
        board[2][3] = ("a");
        board[3][0] = ("a");
        board[3][1] = ("a");
        board[3][2] = ("a");
        board[3][3] = ("a");

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

    public String getWordBank() {
        return wordBank;
    }

    public void setWordBank(String wordBank) {
        this.wordBank = wordBank;
    }


    public boolean wordLength(String word) {
        return true;
    }

    public String removeLetter(String word) {


        if ((word != null) && (word.length() > 0)) {

            word = word.substring(0, word.length()-1);

        }

        return word;

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
        boolean file = findFile(file1,"words.txt");
        return file;
        //HashMap map = inDictionary(file);



    }











    public int isCurrentAdjacentToLast(int lastLetterRow,
                                       int lastLetterCol, int curLetterRow, int curLetterCol) {


        int trueOrFalse = 0; //false is 0, true is 1
                             //didn't use boolean because it wasn't working

        if (lastLetterRow == 0) {
            if (lastLetterCol == 0) {
                if (curLetterRow == 0 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 0) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 1) {
                if (curLetterRow == 0 && curLetterCol == 0) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 0 && curLetterCol == 2) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 0) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 2) {
                    trueOrFalse = 1;
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 2) {
                if (curLetterRow == 0 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 0 && curLetterCol == 3) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 2) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 3) {
                    trueOrFalse = 1;
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 3) {
                if (curLetterRow == 0 && curLetterCol == 2) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 2) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 3) {
                    trueOrFalse = 1;
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else
            {
                trueOrFalse = 0;
            }
        }
        else if (lastLetterRow == 1) {
            if (lastLetterCol == 0) {
                if (curLetterRow == 0 && curLetterCol == 0) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 0 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 1 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 2 && curLetterCol == 0) {
                    trueOrFalse = 1;
                }
                else if (curLetterRow == 2 && curLetterCol == 1) {
                    trueOrFalse = 1;
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 1) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 2) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 3) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else
            {
                trueOrFalse = 0;
            }
        }
        else if (lastLetterRow == 2) {
            if (lastLetterCol == 0) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
            }
            else if (lastLetterCol == 1) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 2) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 3) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow + 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
        }
        else if (lastLetterRow == 3) {
            if (lastLetterCol == 0) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }

            }
            else if (lastLetterCol == 1) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 2) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol + 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else if (lastLetterCol == 3) {
                if (curLetterRow == lastLetterRow - 1) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else if (curLetterCol == lastLetterCol) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else if (curLetterRow == lastLetterRow) {
                    if (curLetterCol == lastLetterCol - 1) {
                        trueOrFalse = 1;
                    }
                    else
                    {
                        trueOrFalse = 0;
                    }
                }
                else
                {
                    trueOrFalse = 0;
                }
            }
            else
            {
                trueOrFalse = 0;
            }
        }
        else {
            trueOrFalse = 0;
        }


        return trueOrFalse;
    }




}







