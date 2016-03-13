package edu.up.cs301.boggle;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;

/**
 * A computer-version of a boggle-player.
 * 
 * @author Steven R. Vegdahl
 * @author Andrew M. Nuxoll
 * @version September 2013
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
