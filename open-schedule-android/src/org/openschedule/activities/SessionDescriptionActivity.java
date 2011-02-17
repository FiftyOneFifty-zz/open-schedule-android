/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;
import org.openschedule.domain.Block;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author dmfrey
 *
 */
public class SessionDescriptionActivity extends Activity {

	private static final String TAG = "SessionDescriptionActivity";
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		setContentView( R.layout.session_description );

		Log.d( TAG, "onCreate : exit" );
	}

	@Override
	protected void onStart() {
	    Log.d( TAG, "onStart : enter" );

	    super.onStart();
		
		refreshSession();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	protected void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		
		refreshSession();

		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSession() {
		Log.d( TAG, "Refreshing Session : enter" );

		Block block = SharedDataManager.getCurrentBlock();
		if( null == block ) {
			Log.d( TAG, "Refreshing Session : exit, no block" );

			return;
		}
		
		final TextView sessionDescriptionTextView = (TextView) findViewById( R.id.session_description_textview );

		sessionDescriptionTextView.setText( block.getSession().getDescription() );
		
		Log.d( TAG, "Refreshing Session : exit" );
	}
	
}
