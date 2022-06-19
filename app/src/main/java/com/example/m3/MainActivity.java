package com.example.m3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
	Button b1,b2,b3,b4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b1=findViewById(R.id.b1);
		b2=findViewById(R.id.b2);
		b3=findViewById(R.id.b3);
		b4=findViewById(R.id.b4);
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
	}
}
