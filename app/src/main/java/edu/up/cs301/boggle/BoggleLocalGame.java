package edu.up.cs301.boggle;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * A class that represents the sate of the game
 *
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleLocalGame extends LocalGame implements BoggleGame {
	private BoggleState state;

	/**
	 * Constuctor for Local Class
	 */
	public BoggleLocalGame(){
		state = new BoggleState();
		this.getTimer().setInterval(1000);
		this.getTimer().start();
	}
	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {
		GameState copy = new BoggleState(state);
		p.sendInfo(copy);
	}

	@Override
	protected boolean canMove(int playerIdx) {
		return true;
	}

	@Override
	protected String checkIfGameOver() {


		int p1Score = state.getPlayer1Score();
		int p2Score = state.getPlayer2Score();

		ArrayList<String> p1Words = state.getWordBank();
		ArrayList<String> p2Words = state.getCompUsedWords();


		ArrayList<String> sameWords = new ArrayList<String>();

		for (int i = 0; i < p1Words.size(); i++) {
			if (p2Words.contains(p1Words.get(i))) {
				sameWords.add(p1Words.get(i));
			}
		}


		String wordToModify;

		for (int i = 0; i < sameWords.size(); i++) {
			wordToModify = sameWords.get(i);

			if (wordToModify.length() == 3 || wordToModify.length() == 4) {
				p1Score = p1Score - 1;
				p2Score = p2Score - 1;
			}
			else if (wordToModify.length() == 5) {
				p1Score = p1Score - 2;
				p2Score = p2Score - 2;
			}
			else if (wordToModify.length() == 6) {
				p1Score = p1Score - 3;
				p2Score = p2Score - 3;
			}
			else if (wordToModify.length() == 7) {
				p1Score = p1Score - 4;
				p2Score = p2Score - 4;
			}
			else if (wordToModify.length() >= 8) {
				p1Score = p1Score - 5;
				p2Score = p2Score - 5;
			}


		}



		System.out.println("CHECKING IF GAME OVER" + state.getGameOver());
		if(state.getGameOver() == 1){
			int winner = state.getWinner();
			String winTxt = "";

			if (winner == 1) {
				winTxt = "Game over! "+playerNames[0]+" wins with a score of "+p1Score+"!";
			}

			else if (winner == 2) {
				winTxt = "Game over! "+playerNames[1]+" wins with a score of "+p2Score+"!";
			}

			else if (winner == 3) {
				winTxt = "It's a draw! "+playerNames[0]+" and "+playerNames[1]+" tie with a score of "+p1Score+" each!";
			}
			return winTxt;
		}
		else{
			return null;
		}

	}

	/**
	 * Where all the actions are configured
	 * @param action
	 * 			The move that the player has sent to the game
	 *
	 * @return
	 */
	@Override
	protected boolean makeMove(GameAction action) throws IOException {
        //action used for when the computer needs to score
        if(action instanceof BoggleComputerSubmitScoreAction){
            BoggleComputerSubmitScoreAction BCSA = (BoggleComputerSubmitScoreAction)action;
            String word = BCSA.curWord(); //gets list of all possible words comp can use
            //state.setCompUsedWords(word); //puts the words used by the computer in array
            System.out.println("----"+ word+ "------");
            int score = state.updateScore(word); //calculates the score for the word
            state.setPlayer2Score(score + state.getPlayer2Score()); //sets the comps score
            return true;
		}
		else if(action instanceof BoggleSelectTileAction){
			BoggleSelectTileAction BSTA = (BoggleSelectTileAction)action;
			int curRow = BSTA.curLetterRow;
			int curCol = BSTA.curLetterCol;
			String currentWord = state.getCurrentWord(); //pre exsisting word
            //array of all words choosen already and their positions
			int[][] selectedLetters = state.getSelectedLetters();
			String[][] gameBoard = state.getGameBoard();
            //adds letter from tile to exsisitng word
			currentWord = state.addToWord(currentWord, gameBoard[curRow][curCol]);
			state.setCurrentWord(currentWord);
			state.setSelectedLetters(selectedLetters);
			return true;
		}
		else if(action instanceof BoggleDeSelectTileAction) {
			String currentWord = state.getCurrentWord();
			int[][] selectedLetters = state.getSelectedLetters();
			currentWord = state.removeFromWord(currentWord);
			state.setCurrentWord(currentWord);
			state.setSelectedLetters(selectedLetters);
        	return true;

		}
		else if(action instanceof BoggleSubmitScoreAction){
			BoggleSubmitScoreAction BSSA = (BoggleSubmitScoreAction)action;
			String word = BSSA.currentWord;
            //if word is more then 3 letters and in dictionary
			if(state.wordLength(word)&&state.inDictionary(word)){
				if (state.getWordBank().contains(word)) {return false;}
				else {
					int score = state.updateScore(word);
                    state.setPlayer1Score(state.getPlayer1Score() + score);
					state.addToWordBank(word);
					state.setCurrentWord("");
					return true;
				}
			}
			state.setCurrentWord("");
			return true;
		}
		else if (action instanceof BoggleRotateAction) {
			state.rotateBoard(state.getGameBoard());
			return true;
		}
        else if(action instanceof BoggleTimerOutAction){
            checkIfGameOver();
        }
		return false;
	}
	/**
	 * Invoked whenever the game's timer has ticked. It is expected
	 * that this will be overridden in many games.
	 */
	protected void timerTicked() {
        int seconds = state.getSecondsLeft();

        if (seconds == 1){
            state.setGameOver(1);
        }
		if (seconds > 0) {
			seconds--;
		}
		checkIfGameOver();
		state.setSecondsLeft(seconds);
		sendAllUpdatedState();
	}
}// class BoggleLocalGame
