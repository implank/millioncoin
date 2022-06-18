package com.example.m3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.m3.adapter.VPAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
	private TabLayout tabLayout;
	private ViewPager2 viewPager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabLayout = findViewById(R.id.tabLayout);
		viewPager = findViewById(R.id.viewPager2);
		VPAdapter vpAdapter=new VPAdapter(getSupportFragmentManager(),getLifecycle());
//		vpAdapter.addFragment(new MainView(), "Fragment1");
//		vpAdapter.addFragment(new MainView(), "Fragment2");
//		vpAdapter.addFragment(new MainView(), "Fragment3");
		viewPager.setAdapter(vpAdapter);
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
			}
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
			}
		});
	}
}
