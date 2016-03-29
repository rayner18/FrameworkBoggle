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

			String word = state.getCurrentWord(); //gets the current word
			//adds a temporary letter so we can get curRow and CurCol
			word = state.addLetter(word, BoggleHumanPlayer.buttonLetter);
			//System.out.println(word);
			curRow = state.getCurLetterRow(word);
			curCol = state.getCurLetterCol(word);
			//checks to see if we are adding the first letter to the word, or if letters already
			//exsist
			if (word.length() > 1) {
				//if there is already letters in the word, it is safe to check for lastRow and LastCol
				lastRow = state.getLastCurLetterRow(word);
				lastCol = state.getLastCurLetterCol(word);
			}
			else{
				//if no letters in word yet, the pushed leter will become the first letter
				state.setCurrentWord(word);
				return true;
			}
			//checks if the curLetter is adjacent to the lastLetter
			if(state.canAdd(state.getSelectedLetters(),curRow,curCol,lastRow,lastCol)){
				//removes the temporary letter
				word = state.removeLetter(word);
				//now adds the letter again through another method that will keep track of its
				//location and put it into SelectedLetters()
				String finalWord = state.addLetter(word, state.getSelectedLetters(),curRow,curCol,
						BoggleHumanPlayer.buttonLetter);
				state.setCurrentWord(finalWord);
				return true;

			}
			return false;
		}
		else if(action instanceof BoggleDeSelectTileAction){
			String word = state.getCurrentWord(); //gets current word
			//System.out.println(BoggleHumanPlayer.buttonLetter);
			//System.out.println("remove 0: " + word);
			//temporarily adds the letter to the word, so we can get the curRow and CurCol
			word = state.addLetter(word, BoggleHumanPlayer.buttonLetter);
			//System.out.println(word+ " << THIS IS THE WORD ");
			curRow = state.getCurLetterRow(word);
			curCol = state.getCurLetterCol(word);
			lastRow = state.getLastCurLetterRow(word);
			lastCol = state.getLastCurLetterCol(word);
			//checks to see if the letter is adjacent to previous letter
			if(state.canRemove(curRow,curCol,lastRow,lastCol)){
				//System.out.println("you are here!");
				//removes the letter we temporarily added
				word = state.removeLetter(word,state.getSelectedLetters());
				//System.out.println("remove 1: " + word);
				//removes the actual letter we want to remove
				word = state.removeLetter(word,state.getSelectedLetters());
				//System.out.println("remove 2: " + word);


				state.setCurrentWord(word);

				return true;
			}

			return false;
		}
		return false;
	}
}// class BoggleLocalGame
