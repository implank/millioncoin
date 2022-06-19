package com.example.m3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.m3.fragment.AboutFrag;
import com.example.m3.fragment.ChartFrag;
import com.example.m3.fragment.HistoryFrag;
import com.example.m3.fragment.HomeFrag;

public class MainActivity extends AppCompatActivity {
	Button b1,b2,b3,b4,b5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=findViewById(R.id.b1);
		b2=findViewById(R.id.b2);
		b3=findViewById(R.id.b3);
		b4=findViewById(R.id.b4);
		b5=findViewById(R.id.b5);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm=getSupportFragmentManager();
				fm.beginTransaction()
					.replace(R.id.fragmentContainerView,new HomeFrag())
						.setReorderingAllowed(true)
						.commit();
			}
		});
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm=getSupportFragmentManager();
				fm.beginTransaction()
					.replace(R.id.fragmentContainerView,new ChartFrag())
						.setReorderingAllowed(true)
						.commit();
			}
		});
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm=getSupportFragmentManager();
				fm.beginTransaction()
					.replace(R.id.fragmentContainerView,new HistoryFrag())
						.setReorderingAllowed(true)
						.commit();
			}
		});
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentManager fm=getSupportFragmentManager();
				fm.beginTransaction()
					.replace(R.id.fragmentContainerView,new AboutFrag())
						.setReorderingAllowed(true)
						.commit();
			}
		});
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d("MainActivity","onClick");
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), RecordActivity.class);
				startActivity(intent);
			}
		});
	}
}
