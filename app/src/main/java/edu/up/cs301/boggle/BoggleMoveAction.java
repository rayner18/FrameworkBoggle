package edu.up.cs301.boggle;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 *
 *
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleMoveAction extends GameAction {
	
	// to satisfy the serializable interface
	private static final long serialVersionUID = 28062013L;

	//whether this move is a plus (true) or minus (false)
	private boolean isPlus;
	
	/**
	 * Constructor for the BoggleMoveAction class.
	 * 
	 * @param player
	 *            the player making the move
	 * @param isPlus
	 *            value to initialize this.isPlus
	 */
	public BoggleMoveAction(GamePlayer player, boolean isPlus) {
		super(player);
		this.isPlus = isPlus;
	}
	
	/**
	 * getter method, to tell whether the move is a "plus"
	 * 
	 * @return
	 * 		a boolean that tells whether this move is a "plus"
	 */
	public boolean isPlus() {
		return isPlus;
		
	}
}//class BoggleMoveAction
