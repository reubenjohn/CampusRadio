package com.campusradio.upload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
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
import android.widget.EditText;

import com.campusradio.android.CampusRadio;
import com.campusradio.android.R;
import com.campusradio.upload.suspicious.SuspiciousUpload;
import com.reubenjohn.util.HttpPostHandler;
import com.reubenjohn.util.StringFeildCondition;

public class UploadFragment extends Fragment {

	ActionMode uploadActionMode;
	HttpPostHandler uploadHandler;
	EditText song, artist, email, university, mood, genre, username;
	String feilds[] = { CampusRadio.keys.uploadSongName,
			CampusRadio.keys.uploadArtistName, CampusRadio.keys.uploadEmail,
			CampusRadio.keys.uploadUniversity, CampusRadio.keys.uploadMood,
			CampusRadio.keys.uploadGenre, CampusRadio.keys.uploadUsername };
	/*
	 * String defaults[] = { CampusRadio.defaults.uploadSongName,
	 * CampusRadio.defaults.uploadArtistName, CampusRadio.defaults.uploadEmail,
	 * CampusRadio.defaults.uploadUniversity, CampusRadio.defaults.uploadMood,
	 * CampusRadio.defaults.uploadGenre, CampusRadio.defaults.uploadUserName };
	 */

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
			switch (menu.getItemId()) {
			case R.id.action_upload:
				upload();

				break;

			default:
				Log.e(UploadFragment.class.toString(),
						"Unknown action item selected");
				if (Build.VERSION.SDK_INT < 11) {
					upload();
				}
				break;
			}

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

	@Override
	public void onResume() {
		autoFillFeilds();
		super.onResume();
	}

	private void autoFillFeilds() {
		SharedPreferences prefs = getActivity().getSharedPreferences(
				CampusRadio.fileNames.uploadInfo, Context.MODE_PRIVATE);
		artist.setText(prefs.getString(CampusRadio.keys.uploadArtistName, ""));
		email.setText(prefs.getString(CampusRadio.keys.uploadEmail, ""));
		university.setText(prefs.getString(CampusRadio.keys.uploadUniversity,
				""));
		username.setText(prefs.getString(CampusRadio.keys.uploadUsername, ""));
	}

	protected boolean validateUploadInfo(Bundle uploadInfo) {
		// TODO go through the various fields in the bundle and check if they
		// all match the requirements
		StringFeildCondition emptyCondition = new StringFeildCondition() {

			@Override
			public boolean test(String s) {
				return !s.isEmpty();
			}
		};
		boolean emptyTest = true, result;
		for (String key : feilds) {
			emptyTest &= emptyCondition.test(uploadInfo.getString(key));
		}
		result = emptyTest;// TODO combine conditions here
		return result;
	}

	private void performUpload(Bundle uploadInfo) {

		uploadHandler.execute(CampusRadio.properties.uri.upload, getResources()
				.getString(R.string.uploading_message),
				CampusRadio.keys.uploadSongName, uploadInfo
						.getString(CampusRadio.keys.uploadSongName),
				CampusRadio.keys.uploadArtistName, uploadInfo
						.getString(CampusRadio.keys.uploadArtistName),
				CampusRadio.keys.uploadEmail, uploadInfo
						.getString(CampusRadio.keys.uploadEmail),
				CampusRadio.keys.uploadUniversity, uploadInfo
						.getString(CampusRadio.keys.uploadUniversity),
				CampusRadio.keys.uploadMood, uploadInfo
						.getString(CampusRadio.keys.uploadMood),
				CampusRadio.keys.uploadGenre, uploadInfo
						.getString(CampusRadio.keys.uploadGenre),
				CampusRadio.keys.uploadUsername, uploadInfo
						.getString(CampusRadio.keys.uploadUsername));
	}

	protected Bundle bundleUploadInfo() {
		Bundle uploadInfo = new Bundle();
		uploadInfo.putString(CampusRadio.keys.uploadSongName, song.getText()
				.toString());
		uploadInfo.putString(CampusRadio.keys.uploadArtistName, artist
				.getText().toString());
		uploadInfo.putString(CampusRadio.keys.uploadEmail, email.getText()
				.toString());
		uploadInfo.putString(CampusRadio.keys.uploadUniversity, university
				.getText().toString());
		uploadInfo.putString(CampusRadio.keys.uploadMood, mood.getText()
				.toString());
		uploadInfo.putString(CampusRadio.keys.uploadGenre, genre.getText()
				.toString());
		uploadInfo.putString(CampusRadio.keys.uploadUsername, username
				.getText().toString());
		return uploadInfo;
	}

	private void upload() {

		Bundle uploadInfo = bundleUploadInfo();

		if (validateUploadInfo(uploadInfo)) {
			saveState(uploadInfo);
			performUpload(uploadInfo);
		}
	}

	private void saveState(Bundle uploadInfo) {
		SharedPreferences prefs = getActivity().getSharedPreferences(
				CampusRadio.fileNames.uploadInfo, Context.MODE_PRIVATE);
		Editor editor = prefs.edit();
		editor.putString(CampusRadio.keys.uploadSongName,
				uploadInfo.getString(CampusRadio.keys.uploadSongName));
		editor.putString(CampusRadio.keys.uploadArtistName,
				uploadInfo.getString(CampusRadio.keys.uploadArtistName));
		editor.putString(CampusRadio.keys.uploadEmail,
				uploadInfo.getString(CampusRadio.keys.uploadEmail));
		editor.putString(CampusRadio.keys.uploadUniversity,
				uploadInfo.getString(CampusRadio.keys.uploadUniversity));
		editor.putString(CampusRadio.keys.uploadMood,
				uploadInfo.getString(CampusRadio.keys.uploadMood));
		editor.putString(CampusRadio.keys.uploadGenre,
				uploadInfo.getString(CampusRadio.keys.uploadGenre));
		editor.putString(CampusRadio.keys.uploadUsername,
				uploadInfo.getString(CampusRadio.keys.uploadUsername));
		editor.commit();
	}

	private void bridgeXML(View v) {
		song = (EditText) v.findViewById(R.id.et_upload_song_name);
		artist = (EditText) v.findViewById(R.id.et_upload_artist_name);
		email = (EditText) v.findViewById(R.id.et_upload_email);
		university = (EditText) v.findViewById(R.id.et_upload_university);
		mood = (EditText) v.findViewById(R.id.et_upload_mood);
		genre = (EditText) v.findViewById(R.id.et_upload_genre);
		username = (EditText) v.findViewById(R.id.et_upload_username);
	}

	private void initializeFeilds() {
		if (uploadActionMode == null) {
			uploadActionMode = ((ActionBarActivity) getActivity())
					.startSupportActionMode(sessionCreateActionModeCallBack);
		}
		uploadHandler = new HttpPostHandler();

		uploadHandler
				.setOnPostCompleteListener(new OnHttpPostCompleteListener() {
					@Override
					public void onHttpPostComplete(String response) {
						Intent intent;
						if (validateResponse(response)) {
						} else {
							intent = new Intent(getActivity(),
									SuspiciousUpload.class);
							intent.putExtra(CampusRadio.keys.uploadResponse,
									response);
							getActivity().startActivity(intent);
						}
					}
				});
	}

	protected boolean validateResponse(String response) {
		// TODO Evaluate and return true if the response represents a success
		// and false otherwise
		return false;
	}

}
