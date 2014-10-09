package com.campusradio.upload.suspicious;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SuspiciousUploadPageAdapter extends FragmentPagerAdapter {

	private List<Fragment> fragments;

	public SuspiciousUploadPageAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int id) {
		return this.fragments.get(id);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

}
