package com.campusradio.upload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.campusradio.android.CampusRadio;
import com.campusradio.android.R;

public class UploadFragment extends Fragment {

	ActionMode uploadActionMode;
	UploadHandler uploadHandler;
	TextView response;

	private ActionMode.Callback sessionCreateActionModeCallBack = new ActionMode.Callback() {

		@Override
		public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
			return false;
		}

		@Override
		public void onDestroyActionMode(ActionMode mode) {
			Intent result = new Intent();
			getActivity().setResult(Activity.RESULT_CANCELED, result);
			getActivity().finish();
		}

		@Override
		public boolean onCreateActionMode(ActionMode mode, Menu menu) {
			MenuInflater inflater = mode.getMenuInflater();
			inflater.inflate(R.menu.upload, menu);
			return true;
		}

		@Override
		public boolean onActionItemClicked(ActionMode mode, MenuItem menu) {
			Log.d("UploadFragment", "Action item clicked");
			Bundle uploadInfo = new Bundle();
			uploadInfo.putString(CampusRadio.keys.uploadSongName,
					CampusRadio.defaults.uploadSongName);
			uploadInfo.putString(CampusRadio.keys.uploadArtistName,
					CampusRadio.defaults.uploadArtistName);
			uploadInfo.putString(CampusRadio.keys.uploadEmail,
					CampusRadio.defaults.uploadSongName);
			uploadInfo.putString(CampusRadio.keys.uploadUniversity,
					CampusRadio.defaults.uploadUniversity);
			uploadInfo.putString(CampusRadio.keys.uploadSongName,
					CampusRadio.defaults.uploadMood);
			uploadInfo.putString(CampusRadio.keys.uploadSongName,
					CampusRadio.defaults.uploadSongName);
			uploadInfo.putString(CampusRadio.keys.uploadSongName,
					CampusRadio.defaults.uploadSongName);

			uploadHandler = new UploadHandler();
			uploadHandler.execute("blah");

			Intent result = new Intent();
			Log.d("StudyTimerSessionSetup",
					"Bundled uploadInfo->" + uploadInfo.toString());
			result.putExtras(uploadInfo);
			getActivity().setResult(Activity.RESULT_OK, result);
			getActivity().finish();
			return false;
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_upload, container, false);
		bridgeXML(v);
		initializeFeilds();
		return v;
	}

	private void bridgeXML(View v) {
		response = (TextView) v.findViewById(R.id.tv_song);
	}

	private void initializeFeilds() {
		if (uploadActionMode == null) {
			uploadActionMode = ((ActionBarActivity) getActivity())
					.startSupportActionMode(sessionCreateActionModeCallBack);
		}
	}

	public void checkResponse() {
		response.setText(uploadHandler.result);
	}

}
