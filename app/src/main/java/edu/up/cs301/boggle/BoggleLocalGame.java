package edu.up.cs301.boggle;

import android.util.Log;

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

	public BoggleLocalGame(){
		state = new BoggleState();
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

	@Override
	protected boolean makeMove(GameAction action) {


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

		return false;
	}
}// class BoggleLocalGame
