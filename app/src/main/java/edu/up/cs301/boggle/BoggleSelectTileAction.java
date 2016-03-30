package edu.up.cs301.boggle;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 @author Charles Rayner
  * @author Michael Waitt
 * @author Jacob Kirby
 * Action class for selecting a tile
 */
public class BoggleSelectTileAction extends GameAction {
    /**
     * constructor for BoggleSelectTileAction
     *
     * @param player the player who created the action
     */
    public BoggleSelectTileAction(GamePlayer player) {
        super(player);
    }
}
