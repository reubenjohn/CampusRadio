package com.campusradio.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

public class FullScreenManager {

	Activity activity;

	public FullScreenManager(Activity activity) {
		this.activity = activity;
	}

	public void activateFullScreen() {
		if (Build.VERSION.SDK_INT < 16) {
			activateFullScreen_16lower();
		} else {
			activateFullScreen_v16();
		}
	}

	private void activateFullScreen_16lower() {
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	private void activateFullScreen_v16() {

		View decorView = activity.getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
		decorView.setSystemUiVisibility(uiOptions);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public void allowContentBehindStatusBar() {
		View decorView = activity.getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
	}
}
