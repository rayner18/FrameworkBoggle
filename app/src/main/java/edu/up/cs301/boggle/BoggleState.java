package edu.up.cs301.boggle;


import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;


/**
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleState extends GameState {

        private int playerTurn;
        private int player1Score;
        private int player2Score;
        private ArrayList<String> currentWord;
        private ArrayList<String> wordBank;
        private boolean timer;



    public BoggleState(BoggleState state){
            playerTurn = state.playerTurn;
            player1Score = state.player1Score;
            player2Score = state.player2Score;
            currentWord = state.currentWord;
            wordBank = state.wordBank;
            timer = state.timer;

        }

        public boolean isTimer() {return timer;}
        public void setTimer(boolean timer) {this.timer = timer;}
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
            this.currentWord = currentWord;}
        public ArrayList<String> getWordBank() {
            return wordBank;
        }
        public void setWordBank(ArrayList<String> wordBank) {
            this.wordBank = wordBank;
        }
}



