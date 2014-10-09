package com.campusradio.upload.suspicious;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.campusradio.android.CampusRadio;
import com.campusradio.android.R;
import com.campusradio.android.debug.NullPointerAsserter;

public class SuspiciousUploadHtmlFragment extends Fragment {

	WebView webView;
	NullPointerAsserter nullAsserter = new NullPointerAsserter(
			SuspiciousUploadHtmlFragment.class.toString());

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null)
			return null;
		View v = (LinearLayout) inflater.inflate(
				R.layout.fragment_suspicious_upload_html, container, false);
		bridgeXML(v);
		initializeFeilds();
		return v;
	}

	private void bridgeXML(View v) {
		webView = (WebView) v.findViewById(R.id.wv_suspicious_upload);

		nullAsserter.assertPointer(webView);
	}

	private void initializeFeilds() {
		webView.loadData(
				getActivity().getIntent().getExtras()
						.getString(CampusRadio.keys.uploadResponse),
				"text/html", null);
	}
}
