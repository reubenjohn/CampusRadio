package com.campusradio.upload.suspicious;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.campusradio.android.CampusRadio;
import com.campusradio.android.R;

public class SuspiciousUploadRawFragment extends Fragment {

	EditText raw;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null)
			return null;
		View v = (LinearLayout) inflater.inflate(
				R.layout.fragment_suspicious_upload_raw, container, false);

		bridgeXML(v);
		initializeFeilds();
		return v;
	}

	private void bridgeXML(View v) {
		raw = (EditText) v.findViewById(R.id.et_plain_response);
	}

	private void initializeFeilds() {
		raw.setText(getActivity().getIntent().getExtras()
				.getString(CampusRadio.keys.uploadResponse));
	}

}
