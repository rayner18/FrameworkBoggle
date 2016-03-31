package edu.up.cs301.boggle;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by Jacob on 3/26/2016.
 */
public class BoggleSelectTileAction extends GameAction {

    public int curLetterRow;
    public int curLetterCol;


    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public BoggleSelectTileAction(GamePlayer player, int curLetterRow, int curLetterCol) {
        super(player);

        this.curLetterRow = curLetterRow;
        this.curLetterCol = curLetterCol;


    }

    public int curLetterRow(){return curLetterRow;};
    public int curLetterCol(){return curLetterCol;};
}
