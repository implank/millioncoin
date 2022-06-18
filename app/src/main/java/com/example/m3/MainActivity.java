package com.example.m3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

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
	}
}
