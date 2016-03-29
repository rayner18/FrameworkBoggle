package edu.up.cs301.boggle;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Jacob on 3/26/2016.
 */
public class BoggleSubmitScoreAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BoggleSubmitScoreAction(GamePlayer player) {
        super(player);
    }
}
