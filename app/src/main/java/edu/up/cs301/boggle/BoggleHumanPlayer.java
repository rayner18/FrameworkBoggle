package edu.up.cs301.boggle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * A GUI of a boggle player
 *
 * @author Charles Rayner
 * @author Michael Waitt
 * @author Jacob Kirby
 * @version March 2016
 */
public class BoggleHumanPlayer extends GameHumanPlayer implements BogglePlayer, OnClickListener {
	// The TextView the displays the current counter value
	private TextView counterValueTextView;

	// the most recent game state, as given to us by the CounterLocalGame
	private BoggleState state;

	// the android activity that we are running
	private GameMainActivity myActivity;

	/**
	 * constructor
	 *
	 * @param name
     *     the name of a player
	 */
	public BoggleHumanPlayer(String name) {
		super(name);
	}


	public View getTopView() {
		return myActivity.findViewById(R.id.AppLayout);
	}

	@Override
	public void receiveInfo(GameInfo info) {

	}


	public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.boggle_human_player);

        // if we have a game state, "simulate" that we have just received
        // the state from the game so that the GUI values are updated
        if (state != null) {
            receiveInfo(state);
        }
	}

	public void onClick(View v) {


	}
}// class BoggleHumanPlayer

