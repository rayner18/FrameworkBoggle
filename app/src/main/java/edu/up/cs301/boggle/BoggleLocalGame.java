package edu.up.cs301.boggle;

import android.util.Log;

import java.io.IOException;

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
		return null;
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


		if(action instanceof BoggleSelectTileAction){

			BoggleSelectTileAction BSTA = (BoggleSelectTileAction)action;

			int curRow = BSTA.curLetterRow;
			int curCol = BSTA.curLetterCol;

			String currentWord = state.getCurrentWord();
			int[][] selectedLetters = state.getSelectedLetters();
			String[][] gameBoard = state.getGameBoard();


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
			if(state.wordLength(word)&& state.inDictionary(word)){
				int score = state.updateScore(word);
				System.out.println("THE SCORE IS:" + score);
				state.setPlayer1Score(state.getPlayer1Score() + score);
				state.addToWordBank(word);
				state.setCurrentWord("");
				return true;

			}
			state.setCurrentWord("");
			return true;
		}
		else if (action instanceof BoggleRotateAction) {
			state.rotateBoard(state.getGameBoard());
			return true;
		}

		return false;
	}

	/**
	 * Invoked whenever the game's timer has ticked. It is expected
	 * that this will be overridden in many games.
	 */
	protected void timerTicked() {
		int seconds = state.getSecondsLeft();
		if (seconds > 0) {
			seconds--;
		}
		state.setSecondsLeft(seconds);
		sendAllUpdatedState();
	}
}// class BoggleLocalGame
