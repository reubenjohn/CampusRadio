package com.campusradio.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.campusradio.android.R;
import com.campusradio.android.acquaint.Welcome;

public class Splash extends ActionBarActivity {

	FullScreenManager fullScreenManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fullScreenManager = new FullScreenManager(Splash.this);
		fullScreenManager.allowContentBehindStatusBar();
		fullScreenManager.activateFullScreen();
		setContentView(R.layout.splash);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		SharedPreferences prefs = getSharedPreferences("Splash",
				Context.MODE_PRIVATE);
		boolean firstRun = prefs.getBoolean(CampusRadio.keys.firstRun, true);
		final Intent intent;
		if (firstRun) {
			intent = new Intent(Splash.this, Welcome.class);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean(CampusRadio.keys.firstRun, CampusRadio.debugMode);
			editor.commit();
		} else
			intent = new Intent(Splash.this, Home.class);
		Runnable continueToNextActivity = new Runnable() {

			@Override
			public void run() {
				startActivity(intent);
				overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
			}
		};
		(new Handler()).postDelayed(continueToNextActivity,
				CampusRadio.properties.splashDuration);

	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_splash,
					container, false);
			return rootView;
		}
	}

}
