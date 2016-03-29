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
	private int curRow;
	private int curCol;
	private int lastRow;
	private int lastCol;

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

			String word = state.getCurrentWord();
			word = state.addLetter(word, BoggleHumanPlayer.buttonLetter);
			System.out.println(word);
			curRow = state.getCurLetterRow(word);
			curCol = state.getCurLetterCol(word);

			if (word.length() > 1) {
				lastRow = state.getLastCurLetterRow(word);
				lastCol = state.getLastCurLetterCol(word);
			}
			else{
				state.setCurrentWord(word);
				return true;
			}

			if(state.canAdd(state.getSelectedLetters(),curRow,curCol,lastRow,lastCol)){
				word = state.removeLetter(word);
				String finalWord = state.addLetter(word, state.getSelectedLetters(),curRow,curCol,
						BoggleHumanPlayer.buttonLetter);
				state.setCurrentWord(finalWord);
				return true;

			}
			return false;
		}
		else if(action instanceof BoggleDeSelectTileAction){
			String word = state.getCurrentWord();
			System.out.println(BoggleHumanPlayer.buttonLetter);
			System.out.println("remove 0: " + word);
			word = state.addLetter(word, BoggleHumanPlayer.buttonLetter);
			System.out.println(word+ " << THIS IS THE WORD ");
			curRow = state.getCurLetterRow(word);
			curCol = state.getCurLetterCol(word);
			lastRow = state.getLastCurLetterRow(word);
			lastCol = state.getLastCurLetterCol(word);
			if(state.canRemove(curRow,curCol,lastRow,lastCol)){
				System.out.println("you are here!");


				word = state.removeLetter(word,state.getSelectedLetters());
				System.out.println("remove 1: " + word);
				word = state.removeLetter(word,state.getSelectedLetters());
				System.out.println("remove 2: " + word);


				state.setCurrentWord(word);

				return true;
			}

			return false;
		}
		return false;
	}
}// class BoggleLocalGame
