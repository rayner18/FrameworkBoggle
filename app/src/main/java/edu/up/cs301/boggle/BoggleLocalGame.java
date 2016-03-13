package edu.up.cs301.boggle;

import android.util.Log;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * A class that represents the sate of the game
 *
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleLocalGame extends LocalGame implements BoggleGame {


	@Override
	protected void sendUpdatedStateTo(GamePlayer p) {

	}

	@Override
	protected boolean canMove(int playerIdx) {
		return false;
	}

	@Override
	protected String checkIfGameOver() {
		return null;
	}

	@Override
	protected boolean makeMove(GameAction action) {
		return false;
	}
}// class BoggleLocalGame
