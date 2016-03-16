package edu.up.cs301.boggle;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * A computer-version of a boggle-player.
 * 
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleComputerPlayer1 extends GameComputerPlayer implements BogglePlayer, Tickable {


	/**
	 * constructor
	 *
	 * @param name the player's name (e.g., "John")
	 */
	public BoggleComputerPlayer1(String name) {
		super(name);
	}

	@Override
	protected void receiveInfo(GameInfo info) {

	}

}
