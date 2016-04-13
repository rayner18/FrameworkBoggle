package edu.up.cs301.boggle;

import java.util.ArrayList;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Jacob on 4/12/2016.
 */
public class BoggleComputerSubmitScoreAction extends GameAction{
    public ArrayList<String> found;
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BoggleComputerSubmitScoreAction(GamePlayer player, ArrayList<String> found) {
        super(player);
        this.found = found;

    }
    public ArrayList<String> curArray(){return found;}

}
