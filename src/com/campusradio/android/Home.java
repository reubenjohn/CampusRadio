package com.campusradio.android;

import com.campusradio.upload.Upload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class Home extends ActionBarActivity {

	HomeFragment homeF;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);

		bridgeXML();
		initializeFeilds();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.home_container, homeF).commit();
		}
	}

	private void bridgeXML() {
	}

	private void initializeFeilds() {
		homeF = new HomeFragment();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.action_settings:
			break;
		case R.id.action_home_upload:
			startActivityForResult(new Intent(Home.this, Upload.class),
					CampusRadio.requestCodes.uploadSong);
			break;

		default:
			Log.e(Home.class.toString(), "Unknown options item selected");
			break;

		}
		return super.onOptionsItemSelected(item);
	}

}
