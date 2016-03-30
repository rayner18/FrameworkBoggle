package edu.up.cs301.boggle;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * This is the action class for submitting the score
 */
public class BoggleSubmitScoreAction extends GameAction {
    /**
     * constructor for BoggleSubmitScoreAction
     *
     * @param player the player who created the action
     */
    public BoggleSubmitScoreAction(GamePlayer player) {
        super(player);
    }
}
