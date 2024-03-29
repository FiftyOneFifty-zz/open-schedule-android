/**
 *  This file is part of OpenSchedule for Android
 * 
 *  OpenSchedule for Android is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenSchedule for Android is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenSchedule for Android.  If not, see <http://www.gnu.org/licenses/>.
 *   
 * @author Daniel Frey <dmfrey at gmail dot com>
 * 
 * This software can be found at <http://code.google.com/p/open-schedule-android/>
 *
 */
package org.openschedule.activities.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openschedule.R;
import org.openschedule.activities.AbstractOpenScheduleListActivity;
import org.openschedule.activities.session.SessionActivity;
import org.openschedule.api.Block;
import org.openschedule.api.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author Daniel Frey
 *
 */
public class ScheduleActivity extends AbstractOpenScheduleListActivity {

	private static final String TAG = ScheduleActivity.class.getSimpleName();
	
	//***************************************
    // Activity methods
    //***************************************
	@Override
	public void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		refreshBlocks();
	}

	//***************************************
    // ListActivity methods
    //***************************************
	@Override
	protected void onListItemClick( ListView l, View v, int position, long id ) {
		Log.d( TAG, "onListItemClick : enter" );

		super.onListItemClick( l, v, position, id );
		
		Log.d( TAG, "onListItemClick : selecting position=" + position );
		
		Block block = getApplicationContext().getSelectedSchedule().getBlocks().get( position );
		getApplicationContext().setSelectedBlock( block );
		
		if( null != block.getLabel() && null != block.getSession() ) {
			Intent intent = new Intent();
			intent.setClass( v.getContext(), SessionActivity.class );
			startActivity( intent );
		}
		
		Log.d( TAG, "onListItemClick : exit" );
	}

	//***************************************
    // Private methods
    //***************************************
	private void refreshBlocks() {
		Log.d( TAG, "Refreshing Blocks : enter" );
		
		Schedule schedule = getApplicationContext().getSelectedSchedule();
		
		if( null == schedule ) {
			Log.d( TAG, "Refreshing Blocks : exit, no day" );
			return;
		}
		
		List<Map<String,String>> blocks = new ArrayList<Map<String,String>>();
		
		// TODO: Is there w way to populate the table from an Event instead of a Map?
		for( Block block : schedule.getBlocks() ) {
			if( null != block.getLabel() && null != block.getSession() ) {
				Map<String, String> map = new HashMap<String, String>();
				map.put( "block_label", block.getLabel().getName() );
				map.put( "block_name", block.getSession().getName() );
				blocks.add( map );
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put( "block_label", "" );
				map.put( "block_name", "Empty" );
				blocks.add( map );
			}
		}		
		
		SimpleAdapter adapter = new SimpleAdapter(
				this,
				blocks,
				R.layout.block_list_item,
				new String[] { "block_label", "block_name" },
				new int[] { R.id.block_label, R.id.block_name } );
		
		setListAdapter( adapter );

		Log.d( TAG, "Refreshing Blocks : exit" );
	}	

}
