package edu.up.cs301.boggle;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
    @author Charles Rayner
    @author Michael Waitt
    @author Jacob Kirby
    Action class for rotating the board

 */
public class BoggleQuitAction extends GameAction {
    /**
     * constructor for BoggleRotateAction
     *
     * @param player the player who created the action
     */
    public BoggleQuitAction(GamePlayer player) {
        super(player);
    }
}



