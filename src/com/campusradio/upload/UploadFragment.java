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
import com.campusradio.upload.suspicious.SuspiciousUpload;
import com.reubenjohn.util.HttpPostHandler;

public class UploadFragment extends Fragment {

	ActionMode uploadActionMode;
	HttpPostHandler uploadHandler;
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
			Bundle uploadInfo;
			switch (menu.getItemId()) {
			case R.id.action_upload:
				uploadInfo = bundleUploadInfo();

				upload(uploadInfo);

				break;

			default:
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

	protected void upload(Bundle uploadInfo) {
		uploadHandler.execute(
				"http://172.16.16.16/24online/webpages/client.jsp",
				getResources().getString(R.string.uploading_message),
				CampusRadio.keys.uploadSongName,
				uploadInfo.getString(CampusRadio.keys.uploadSongName),
				CampusRadio.keys.uploadArtistName,
				uploadInfo.getString(CampusRadio.keys.uploadArtistName),
				CampusRadio.keys.uploadEmail,
				uploadInfo.getString(CampusRadio.keys.uploadEmail),
				CampusRadio.keys.uploadUniversity,
				uploadInfo.getString(CampusRadio.keys.uploadUniversity),
				CampusRadio.keys.uploadMood,
				uploadInfo.getString(CampusRadio.keys.uploadMood),
				CampusRadio.keys.uploadMood,
				uploadInfo.getString(CampusRadio.keys.uploadMood));
	}

	protected Bundle bundleUploadInfo() {
		Bundle uploadInfo = new Bundle();
		uploadInfo.putString(CampusRadio.keys.uploadSongName,
				CampusRadio.defaults.uploadSongName);
		uploadInfo.putString(CampusRadio.keys.uploadArtistName,
				CampusRadio.defaults.uploadArtistName);
		uploadInfo.putString(CampusRadio.keys.uploadEmail,
				CampusRadio.defaults.uploadEmail);
		uploadInfo.putString(CampusRadio.keys.uploadUniversity,
				CampusRadio.defaults.uploadUniversity);
		uploadInfo.putString(CampusRadio.keys.uploadSongName,
				CampusRadio.defaults.uploadMood);
		uploadInfo.putString(CampusRadio.keys.uploadSongName,
				CampusRadio.defaults.uploadSongName);
		uploadInfo.putString(CampusRadio.keys.uploadSongName,
				CampusRadio.defaults.uploadSongName);
		return uploadInfo;
	}

	private void bridgeXML(View v) {
		response = (TextView) v.findViewById(R.id.tv_response);
	}

	private void initializeFeilds() {
		if (uploadActionMode == null) {
			uploadActionMode = ((ActionBarActivity) getActivity())
					.startSupportActionMode(sessionCreateActionModeCallBack);
		}
		uploadHandler = new HttpPostHandler();

		// uploadHandler.execute("http://posttestserver.com/post.php",
		// getResources().getString(R.string.uploading_message), "REUBEN",
		// "REUBEN", "JOHN", "JOHN");

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

	public void checkResponse() {
		response.setText(uploadHandler.result);
	}

}
