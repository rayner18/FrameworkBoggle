package edu.up.cs301.boggle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

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
	protected TextView yourScoreNumberTextView;
	protected TextView letterDisplayTextView;
	protected Button submitScoreButton;
	public static String buttonLetter;

	public static String tile1ButtonLetter, tile2ButtonLetter, tile3ButtonLetter, tile4ButtonLetter,
			tile5ButtonLetter, tile6ButtonLetter, tile7ButtonLetter, tile8ButtonLetter,
			tile9ButtonLetter, tile10ButtonLetter, tile11ButtonLetter, tile12ButtonLetter,
			tile13ButtonLetter, tile14ButtonLetter, tile15ButtonLetter, tile16ButtonLetter;


	// the most recent game state, as given to us by the CounterLocalGame
	private BoggleState state;

	public static Boolean tile1ButtonPushed, tile2ButtonPushed, tile3ButtonPushed, tile4ButtonPushed,
			tile5ButtonPushed, tile6ButtonPushed, tile7ButtonPushed, tile8ButtonPushed,
			tile9ButtonPushed, tile10ButtonPushed, tile11ButtonPushed, tile12ButtonPushed,
			tile13ButtonPushed, tile14ButtonPushed, tile15ButtonPushed, tile16ButtonPushed ;

	protected Button tile1Button, tile2Button, tile3Button, tile4Button, tile5Button, tile6Button,
			tile7Button, tile8Button, tile9Button, tile10Button, tile11Button, tile12Button, tile13Button,
			tile14Button, tile15Button, tile16Button;

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
		if(info instanceof BoggleState){
			state = (BoggleState)info;
			yourScoreNumberTextView.setText("" + state.getPlayer1Score());
			letterDisplayTextView.setText("" + state.getCurrentWord());
		}
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
		tile1ButtonPushed = false;
		tile2ButtonPushed = false;
		tile3ButtonPushed = false;
		tile4ButtonPushed = false;
		tile5ButtonPushed = false;
		tile6ButtonPushed = false;
		tile7ButtonPushed = false;
		tile8ButtonPushed = false;
		tile9ButtonPushed = false;
		tile10ButtonPushed = false;
		tile11ButtonPushed = false;
		tile12ButtonPushed = false;
		tile13ButtonPushed = false;
		tile14ButtonPushed = false;
		tile15ButtonPushed = false;
		tile16ButtonPushed = false;

		tile1Button = (Button)activity.findViewById(R.id.tile1Button);
		tile1Button.setOnClickListener(this);

		tile2Button = (Button)activity.findViewById(R.id.tile2Button);
		tile2Button.setOnClickListener(this);

		tile3Button = (Button)activity.findViewById(R.id.tile3Button);
		tile3Button.setOnClickListener(this);

		tile4Button = (Button)activity.findViewById(R.id.tile4Button);
		tile4Button.setOnClickListener(this);

		tile5Button = (Button)activity.findViewById(R.id.tile5Button);
		tile5Button.setOnClickListener(this);

		tile6Button = (Button)activity.findViewById(R.id.tile6Button);
		tile6Button.setOnClickListener(this);

		tile7Button = (Button)activity.findViewById(R.id.tile7Button);
		tile7Button.setOnClickListener(this);

		tile8Button = (Button)activity.findViewById(R.id.tile8Button);
		tile8Button.setOnClickListener(this);

		tile9Button = (Button)activity.findViewById(R.id.tile9Button);
		tile9Button.setOnClickListener(this);

		tile10Button = (Button)activity.findViewById(R.id.tile10Button);
		tile10Button.setOnClickListener(this);

		tile11Button = (Button)activity.findViewById(R.id.tile11Button);
		tile11Button.setOnClickListener(this);

		tile12Button = (Button)activity.findViewById(R.id.tile12Button);
		tile12Button.setOnClickListener(this);

		tile13Button = (Button)activity.findViewById(R.id.tile13Button);
		tile13Button.setOnClickListener(this);

		tile14Button = (Button)activity.findViewById(R.id.tile14Button);
		tile14Button.setOnClickListener(this);

		tile15Button = (Button)activity.findViewById(R.id.tile15Button);
		tile15Button.setOnClickListener(this);

		tile16Button = (Button)activity.findViewById(R.id.tile16Button);
		tile16Button.setOnClickListener(this);

		submitScoreButton = (Button)activity.findViewById(R.id.submitScoreButton);
		submitScoreButton.setOnClickListener(this);

		yourScoreNumberTextView = (TextView)activity.findViewById(R.id.yourScoreNumberTextView);
		letterDisplayTextView = (TextView)activity.findViewById(R.id.letterDisplayTextView);
	}
	public void onClick(View v) {
		BoggleSelectTileAction select;
		BoggleDeSelectTileAction deSelect;

			if (v == tile1Button && !tile1ButtonPushed) {
				//Sets background of button to black when pushed

				tile1Button.setBackgroundColor(0x86090404);

				buttonLetter = tile1ButtonLetter = tile1Button.getText().toString();
				tile1ButtonPushed = true; //button is pushed
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);
				tile1ButtonPushed = true; //button is pushed
				//yourScoreNumberTextView.setText("" + state.getPlayer1Score());
			} else if (v == tile1Button && tile1ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile1Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile1ButtonLetter = tile1Button.getText().toString();
				tile1ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile2Button && !tile2ButtonPushed) {
				//Sets background of button to black when pushed

				tile2Button.setBackgroundColor(0x86090404);
				tile2ButtonPushed = true; //button is pushed
				buttonLetter = tile2ButtonLetter = tile2Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

				//yourScoreNumberTextView.setText("" + state.getPlayer1Score());
			} else if (v == tile2Button && tile2ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile2Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile2ButtonLetter = tile2Button.getText().toString();
				tile2ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile3Button && !tile3ButtonPushed) {
				//Sets background of button to black when pushed

				tile3Button.setBackgroundColor(0x86090404);
				tile3ButtonPushed = true; //button is pushed
				buttonLetter = tile3ButtonLetter = tile3Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile3Button && tile3ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile3Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile3ButtonLetter = tile3Button.getText().toString();
				tile3ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile4Button && !tile4ButtonPushed) {
				//Sets background of button to black when pushed

				tile4Button.setBackgroundColor(0x86090404);
				tile4ButtonPushed = true; //button is pushed
				buttonLetter = tile4ButtonLetter = tile4Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile4Button && tile4ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile4Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile4ButtonLetter = tile4Button.getText().toString();
				tile4ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile5Button && !tile5ButtonPushed) {
				//Sets background of button to black when pushed

				tile5Button.setBackgroundColor(0x86090404);
				tile5ButtonPushed = true; //button is pushed
				buttonLetter = tile5ButtonLetter = tile5Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile5Button && tile5ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile5Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile5ButtonLetter = tile5Button.getText().toString();
				tile5ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile6Button && !tile6ButtonPushed) {
				//Sets background of button to black when pushed

				tile6Button.setBackgroundColor(0x86090404);
				tile6ButtonPushed = true; //button is pushed
				buttonLetter = tile6ButtonLetter = tile6Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile6Button && tile6ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile6Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile6ButtonLetter = tile6Button.getText().toString();
				tile6ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

		if (v == tile7Button && !tile7ButtonPushed) {
			//Sets background of button to black when pushed

			tile7Button.setBackgroundColor(0x86090404);
			tile7ButtonPushed = true; //button is pushed
			buttonLetter = tile7ButtonLetter = tile7Button.getText().toString();
			select = new BoggleSelectTileAction(this);
			game.sendAction(select);

		} else if (v == tile7Button && tile7ButtonPushed) {
			//if Button pressed again, changes background back to original background
			tile7Button.setBackgroundResource(R.mipmap.wood1);
			buttonLetter = tile7ButtonLetter = tile7Button.getText().toString();
			tile7ButtonPushed = false; //the button is no longer pushed
			deSelect = new BoggleDeSelectTileAction(this);
			game.sendAction(deSelect);
		}


		if (v == tile8Button && !tile8ButtonPushed) {
				//Sets background of button to black when pushed

				tile8Button.setBackgroundColor(0x86090404);
				tile8ButtonPushed = true; //button is pushed
			    buttonLetter = tile8ButtonLetter = tile8Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile8Button && tile8ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile8Button.setBackgroundResource(R.mipmap.wood1);
			    buttonLetter = tile8ButtonLetter = tile8Button.getText().toString();
				tile8ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile9Button && !tile9ButtonPushed) {
				//Sets background of button to black when pushed

				tile9Button.setBackgroundColor(0x86090404);
				tile9ButtonPushed = true; //button is pushed
				buttonLetter = tile9ButtonLetter = tile9Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile9Button && tile9ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile9Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile9ButtonLetter = tile9Button.getText().toString();
				tile9ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile10Button && !tile10ButtonPushed) {
				//Sets background of button to black when pushed

				tile10Button.setBackgroundColor(0x86090404);
				tile10ButtonPushed = true; //button is pushed
				buttonLetter = tile10ButtonLetter = tile10Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile10Button && tile10ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile10Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile10ButtonLetter = tile10Button.getText().toString();
				tile10ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile11Button && !tile11ButtonPushed) {
				//Sets background of button to black when pushed

				tile11Button.setBackgroundColor(0x86090404);
				tile11ButtonPushed = true; //button is pushed
				buttonLetter = tile11ButtonLetter = tile11Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile11Button && tile11ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile11Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile11ButtonLetter = tile11Button.getText().toString();
				tile11ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile12Button && !tile12ButtonPushed) {
				//Sets background of button to black when pushed

				tile12Button.setBackgroundColor(0x86090404);
				tile12ButtonPushed = true; //button is pushed
				buttonLetter = tile12ButtonLetter = tile12Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile12Button && tile12ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile12Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile12ButtonLetter = tile12Button.getText().toString();
				tile12ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile13Button && !tile13ButtonPushed) {
				//Sets background of button to black when pushed

				tile13Button.setBackgroundColor(0x86090404);
				tile13ButtonPushed = true; //button is pushed
				buttonLetter = tile13ButtonLetter = tile13Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile13Button && tile13ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile13Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile13ButtonLetter = tile13Button.getText().toString();
				tile13ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile14Button && !tile14ButtonPushed) {
				//Sets background of button to black when pushed

				tile14Button.setBackgroundColor(0x86090404);
				tile14ButtonPushed = true; //button is pushed
				buttonLetter = tile14ButtonLetter = tile14Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile14Button && tile14ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile14Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile14ButtonLetter = tile14Button.getText().toString();
				tile14ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile15Button && !tile15ButtonPushed) {
				//Sets background of button to black when pushed

				tile15Button.setBackgroundColor(0x86090404);
				tile15ButtonPushed = true; //button is pushed
				buttonLetter = tile15ButtonLetter = tile15Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile15Button && tile15ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile15Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile15ButtonLetter = tile15Button.getText().toString();
				tile15ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}

			if (v == tile16Button && !tile16ButtonPushed) {
				//Sets background of button to black when pushed

				tile16Button.setBackgroundColor(0x86090404);
				tile16ButtonPushed = true; //button is pushed
				buttonLetter = tile16ButtonLetter = tile16Button.getText().toString();
				select = new BoggleSelectTileAction(this);
				game.sendAction(select);

			} else if (v == tile16Button && tile16ButtonPushed) {
				//if Button pressed again, changes background back to original background
				tile16Button.setBackgroundResource(R.mipmap.wood1);
				buttonLetter = tile16ButtonLetter = tile16Button.getText().toString();
				tile16ButtonPushed = false; //the button is no longer pushed
				deSelect = new BoggleDeSelectTileAction(this);
				game.sendAction(deSelect);
			}
	}
	}
// class BoggleHumanPlayer

