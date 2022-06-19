package com.example.m3.fragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m3.AboutActivity;
import com.example.m3.R;
import com.example.m3.RecordActivity;
import com.example.m3.SettingActivity;
import com.example.m3.db.DBManager;

public class AboutFrag extends Fragment {
	TextView aboutTv,setting_tv_clear;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.frag_about, container, false);
		aboutTv = view.findViewById(R.id.about_tv);
		setting_tv_clear = view.findViewById(R.id.setting_tv_clear);
		aboutTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClass(getContext(), AboutActivity.class);
				startActivity(intent);
			}
		});
		setting_tv_clear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				showDeleteDialog();
			}
		});
		return view;
	}
	
	private void showDeleteDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder.setTitle("删除提示")
      .setMessage("您确定要删除所有记录么？\n注意：删除后无法恢复，请慎重选择！")
      .setPositiveButton("取消",null)
      .setNegativeButton("确定", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          DBManager.deleteAllAccount();
          Toast.makeText(getContext(),"删除成功！",Toast.LENGTH_SHORT).show();
        }
      });
    builder.create().show();
  }
}
