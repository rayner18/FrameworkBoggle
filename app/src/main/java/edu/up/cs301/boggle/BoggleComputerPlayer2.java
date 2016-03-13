package edu.up.cs301.boggle;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
* A computer-version of a boggle-player.  Since this is such a simple game,
* it just sends "+" and "-" commands with equal probability, at an average
* rate of one per second. This computer player does, however, have an option to
* display the game as it is progressing, so if there is no human player on the
* device, this player will display a GUI that shows the value of the counter
* as the game is being played.
* 
* @author Steven R. Vegdahl
* @author Andrew M. Nuxoll
* @version September 2013
*/
public class BoggleComputerPlayer2 extends BoggleComputerPlayer1 {


	/**
	 * constructor
	 *
	 * @param name the player's name (e.g., "John")
	 */
	public BoggleComputerPlayer2(String name) {
		super(name);
	}
}
