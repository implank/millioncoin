package com.example.m3.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.m3.R;
import com.example.m3.RecordActivity;
import com.example.m3.SearchActivity;
import com.example.m3.adapter.AccountAdapter;
import com.example.m3.db.AccountBean;
import com.example.m3.db.DBManager;
import com.example.m3.utils.CalendarDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HistoryFrag extends Fragment {
	ListView historyLv;
	TextView timeTv;
	ImageView calendarIv;
	ImageView searchIv;
	List<AccountBean> mDatas;
	AccountAdapter adapter;
	int year,month;
	int dialogSelPos = -1;
	int dialogSelMonth = -1;
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view=inflater.inflate(R.layout.frag_history,container,false);
		
		historyLv = view.findViewById(R.id.history_lv);
		timeTv = view.findViewById(R.id.history_tv_time);
		calendarIv = view.findViewById(R.id.history_iv_rili);
		searchIv = view.findViewById(R.id.history_iv_search);
		calendarIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CalendarDialog dialog = new CalendarDialog(getContext(), dialogSelPos, dialogSelMonth);
				dialog.show();
				dialog.setDialogSize();
				dialog.setOnRefreshListener(new CalendarDialog.OnRefreshListener() {
					@Override
					public void onRefresh(int selPos, int year, int month) {
						timeTv.setText(year + "年" + month + "月");
						loadData(year, month);
						dialogSelPos = selPos;
						dialogSelMonth = month;
					}
				});
			}
		});
		searchIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getContext(), SearchActivity.class);
				startActivity(intent);
			}
		});
		mDatas = new ArrayList<>();
		// 设置适配器
		adapter = new AccountAdapter(getContext(),mDatas);
		historyLv.setAdapter(adapter);
		initTime();
		timeTv.setText(year+"年"+month+"月");
		loadData(year,month);
		setLVClickListener();
		return view;
	}
	private void setLVClickListener() {
		historyLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				AccountBean accountBean = mDatas.get(position);
				deleteItem(accountBean);
				return false;
			}
		});
	}
	
	private void deleteItem(final AccountBean accountBean) {
		final int delId = accountBean.getId();
		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
		builder.setTitle("提示信息").setMessage("您确定要删除这条记录么？")
				.setNegativeButton("取消",null)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						DBManager.deleteItemFromAccounttbById(delId);
						mDatas.remove(accountBean);   //实时刷新，从数据源删除
						adapter.notifyDataSetChanged();
					}
				});
		builder.create().show();
	}
	
	/* 获取指定年份月份收支情况的列表*/
	private void loadData(int year,int month) {
		List<AccountBean> list = DBManager.getAccountListOneMonthFromAccounttb(year, month);
		mDatas.clear();
		mDatas.addAll(list);
		adapter.notifyDataSetChanged();
	}
	
	private void initTime() {
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH)+1;
	}
}
