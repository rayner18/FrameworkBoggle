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
    private ArrayList<String> currentWord; //the current word the player is making
    private ArrayList<String> wordBank; //the current words in the word bank
    private boolean timer; //true if the timer is running, false if timer has stopped

    public BoggleState() {
        playerTurn = 0;
        player1Score = 0;
        player2Score = 0;
        currentWord = null;
        wordBank = null;
        timer = true;

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

    public ArrayList<String> getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(ArrayList<String> currentWord) {
        this.currentWord = currentWord;
    }

    public ArrayList<String> getWordBank() {
        return wordBank;
    }

    public void setWordBank(ArrayList<String> wordBank) {
        this.wordBank = wordBank;
    }


    public boolean wordLength(ArrayList<String> word) {
        return word.size() >= 3;
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
        boolean file = findFile(file1,"words.txt");
        return file;
        //HashMap map = inDictionary(file);



    }



}







