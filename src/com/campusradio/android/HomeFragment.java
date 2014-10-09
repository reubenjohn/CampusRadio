package com.campusradio.android;

import com.campusradio.upload.Upload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements OnClickListener {

	private Button bUpload;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_home, container, false);
		bridgeXML(v);
		initialzieFeilds();

		return v;
	}

	private void bridgeXML(View v) {
		bUpload = (Button) v.findViewById(R.id.b_home_upload);
	}

	private void initialzieFeilds() {
		bUpload.setOnClickListener(this);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b_home_upload:
			getActivity().startActivityForResult(
					new Intent(getActivity(), Upload.class),
					CampusRadio.requestCodes.uploadSong);
			break;

		default:
			Log.d("HomeFragment", "Unknown button id");
			break;
		}
	}
}
