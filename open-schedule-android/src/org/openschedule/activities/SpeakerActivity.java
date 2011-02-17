/**
 * 
 */
package org.openschedule.activities;

import org.openschedule.R;
import org.openschedule.controllers.NavigationManager;
import org.openschedule.domain.Event;
import org.openschedule.domain.Speaker;
import org.openschedule.util.SharedDataManager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author dmfrey
 *
 */
public class SpeakerActivity extends Activity {

	private static final String TAG = "SpeakerActivity";
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
	    Log.d( TAG, "onCreate : enter" );

	    super.onCreate( savedInstanceState );
		
		setContentView( R.layout.speaker );

		final ListView listView = (ListView) findViewById( R.id.speaker_details_menu );
		
		String[] menu_items = getResources().getStringArray( R.array.speaker_options_array );
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, R.layout.menu_list_item, menu_items );
		listView.setAdapter( arrayAdapter );
		
		listView.setOnItemClickListener( new OnItemClickListener() {
		    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
		    	
				Speaker speaker = SharedDataManager.getCurrentSpeaker();
				switch( position ) {
		    		case 0:
		    			Log.d( TAG, "display biography" );

		    			if( null != speaker.getBio() && !"".equals( speaker.getBio() ) ) {
		    				NavigationManager.startActivity( view.getContext(), SpeakerBiographyActivity.class );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The speaker has not provided a biography.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}
		    			
		    			break;
			      	case 1:
			      		Log.d( TAG, "display web site in browser" );

		    			if( null != speaker.getWebSite() && !"".equals( speaker.getWebSite() ) ) {
		    				Uri uri = Uri.parse( speaker.getWebSite() );
		    				Intent intent = new Intent( Intent.ACTION_VIEW, uri );

		    				Log.v( TAG, "starting display web site intent" );
		    				NavigationManager.startActivity( view.getContext(), intent );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The speaker has not provided a web site.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
			      	case 2:
			      		Log.d( TAG, "send email" );

		    			if( null != speaker.getEmail() && !"".equals( speaker.getEmail() ) ) {
		    				Event event = SharedDataManager.getCurrentEvent();
		    				
		    				Intent intent = new Intent( Intent.ACTION_SEND );
		    				intent.putExtra( Intent.EXTRA_EMAIL, new String[] { speaker.getEmail() } );
		    				intent.putExtra( Intent.EXTRA_SUBJECT, event.getName() );
		    				intent.setType( "plain/text" );
		    				
		    				Log.v( TAG, "starting send email intent" );
		    				NavigationManager.startActivity( view.getContext(), intent );
		    			} else {
		    				Toast toast = Toast.makeText( view.getContext(), "The speaker has not provided an email.", Toast.LENGTH_LONG );
		    				toast.setGravity( Gravity.CENTER, 0, 0 );
		    				toast.show();
		    			}

		    			break;
			      	default:
			      		Log.d( TAG, "default option" );
			      		break;
		    	}
		    }
		});

		Log.d( TAG, "onCreate : exit" );
	}

	@Override
	protected void onStart() {
	    Log.d( TAG, "onStart : enter" );

	    super.onStart();
		
		refreshSpeaker();

		Log.d( TAG, "onStart : exit" );
	}

	@Override
	protected void onResume() {
	    Log.d( TAG, "onResume : enter" );

	    super.onResume();
		
		refreshSpeaker();

		Log.d( TAG, "onResume : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshSpeaker() {
		Log.d( TAG, "Refreshing Session : enter" );

		Speaker speaker = SharedDataManager.getCurrentSpeaker();
		if( null == speaker ) {
			Log.d( TAG, "Refreshing Speaker : exit, no speaker" );

			return;
		}
		
		final TextView speakerNameTextView = (TextView) findViewById( R.id.speaker_name_text_view );
		final TextView speakerEmailTextView = (TextView) findViewById( R.id.speaker_email_text_view );
		final TextView speakerWebSiteTextView = (TextView) findViewById( R.id.speaker_web_site_text_view );

		speakerNameTextView.setText( speaker.getName() );
		
		if( null != speaker.getEmail() && !"".equals( speaker.getEmail() ) ) {
			speakerEmailTextView.setText( speaker.getEmail() );
		}
		
		if( null != speaker.getWebSite() && !"".equals( speaker.getWebSite() ) ) {
			speakerWebSiteTextView.setText( speaker.getWebSite() );
		}
		
		Log.d( TAG, "Refreshing Session : exit" );
	}
	
}