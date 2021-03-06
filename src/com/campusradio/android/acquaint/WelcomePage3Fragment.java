package com.campusradio.android.acquaint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.campusradio.android.Home;
import com.campusradio.android.R;

public class WelcomePage3Fragment extends Fragment {

	Button b_finish;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null)
			return null;
		View v = inflater.inflate(R.layout.fragment_welcome_page_3, container,
				false);
		bridgeXML(v);
		initializeFeilds();
		return v;
	}

	private void initializeFeilds() {

		OnClickListener finishButtonListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Play welcome sound
				Intent startHome=new Intent(getActivity(), Home.class);
				getActivity().startActivity(startHome);
				getActivity().finish();
			}
		};
		b_finish.setOnClickListener(finishButtonListener);
	}

	private void bridgeXML(View v) {
		b_finish = ((Button) v.findViewById(R.id.b_welcome_finish));
	}
}