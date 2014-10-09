package com.campusradio.upload.suspicious;

import java.util.List;
import java.util.Vector;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campusradio.android.R;
import com.campusradio.android.util.WelcomePageTransformer;

public class SuspiciousUploadFragment extends Fragment {

	private SuspiciousUploadPageAdapter suspiciousUploadPageAdapter;
	ViewPager welcomePager;
	ViewPager viewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_suspicious_upload, container, false);
		bridgeXML(v);
		initializeFeilds();
		return v;
	}

	private void bridgeXML(View v) {
		viewPager = (ViewPager) v.findViewById(R.id.vp_suspicious_upload);
	}

	private void initializeFeilds() {
		initializePaging();
	}

	private void initializePaging() {
		if(Build.VERSION.SDK_INT>=11)
			viewPager.setPageTransformer(true, new WelcomePageTransformer());
		else{
			// TODO Create alternate page transformers for lower APIs
		}
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(getActivity(),
				SuspiciousUploadRawFragment.class.getName()));
		fragments.add(Fragment.instantiate(getActivity(),
				SuspiciousUploadHtmlFragment.class.getName()));
		suspiciousUploadPageAdapter = new SuspiciousUploadPageAdapter(getActivity()
				.getSupportFragmentManager(), fragments);

		viewPager.setAdapter(suspiciousUploadPageAdapter);
	}
}
