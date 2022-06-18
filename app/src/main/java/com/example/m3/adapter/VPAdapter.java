package com.example.m3.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class VPAdapter extends FragmentStateAdapter {
	private final ArrayList<Fragment> fragments = new ArrayList<>();
	private final ArrayList<String> titles= new ArrayList<>();
	public VPAdapter(FragmentManager fm, Lifecycle lifecycle) {
		super(fm, lifecycle);
	}
	public void addFragment(Fragment fragment, String title) {
		fragments.add(fragment);
		titles.add(title);
	}
	/**
	 * Provide a new Fragment associated with the specified position.
	 * <p>
	 * The adapter will be responsible for the Fragment lifecycle:
	 * <ul>
	 *     <li>The Fragment will be used to display an item.</li>
	 *     <li>The Fragment will be destroyed when it gets too far from the viewport, and its state
	 *     will be saved. When the item is close to the viewport again, a new Fragment will be
	 *     requested, and a previously saved state will be used to initialize it.
	 * </ul>
	 *
	 * @param position
	 * @see ViewPager2#setOffscreenPageLimit
	 */
	@NonNull
	@Override
	public Fragment createFragment(int position) {
		return fragments.get(position);
	}
	
	/**
	 * Returns the total number of items in the data set held by the adapter.
	 *
	 * @return The total number of items in this adapter.
	 */
	@Override
	public int getItemCount() {
		return fragments.size();
	}
}
