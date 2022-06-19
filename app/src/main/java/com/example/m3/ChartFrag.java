package com.example.m3;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.m3.adapter.ChartVPAdapter;
import com.example.m3.db.DBManager;
import com.example.m3.frag_chart.IncomChartFragment;
import com.example.m3.frag_chart.OutcomChartFragment;
import com.example.m3.utils.CalendarDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChartFrag extends Fragment {
	Button inBtn,outBtn;
	TextView dateTv,inTv,outTv;
	ViewPager chartVp;
	ImageView calendarIv;
	int year;
	int month;
	int selectPos = -1,selectMonth =-1;
	
	List<Fragment> chartFragList;
	private IncomChartFragment incomChartFragment;
	private OutcomChartFragment outcomChartFragment;
	private ChartVPAdapter chartVPAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view = inflater.inflate(R.layout.activity_month_chart, container, false);
		inBtn = view.findViewById(R.id.chart_btn_in);
		outBtn = view.findViewById(R.id.chart_btn_out);
		dateTv = view.findViewById(R.id.chart_tv_date);
		inTv = view.findViewById(R.id.chart_tv_in);
		outTv = view.findViewById(R.id.chart_tv_out);
		chartVp = view.findViewById(R.id.chart_vp);
		calendarIv = view.findViewById(R.id.chart_iv_rili);
		calendarIv.setOnClickListener(new View.OnClickListener() {
			                              @Override
			                              public void onClick(View v) {
				                              showCalendarDialog();
			                              }
		                              });
		inBtn.setOnClickListener(new View.OnClickListener() {
			                           @Override
			                           public void onClick(View v) {
				                           setButtonStyle(1);
				                           chartVp.setCurrentItem(1);
			                           }
		                           });
		outBtn.setOnClickListener(new View.OnClickListener() {
			                            @Override
			                            public void onClick(View v) {
				                            setButtonStyle(0);
				                            chartVp.setCurrentItem(0);
			                            }});
		initTime();
		initStatistics(year,month);
		initFrag();
		setVPSelectListener();
		return view;
	}
	private void setVPSelectListener() {
		chartVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				setButtonStyle(position);
			}
		});
	}
	private void initFrag() {
		chartFragList = new ArrayList<>();
//        添加Fragment的对象
		incomChartFragment = new IncomChartFragment();
		outcomChartFragment = new OutcomChartFragment();
//        添加数据到Fragment当中
		Bundle bundle = new Bundle();
		bundle.putInt("year",year);
		bundle.putInt("month",month);
		incomChartFragment.setArguments(bundle);
		outcomChartFragment.setArguments(bundle);
//        将Fragment添加到数据源当中
		chartFragList.add(outcomChartFragment);
		chartFragList.add(incomChartFragment);
//        使用适配器
		chartVPAdapter = new ChartVPAdapter(getChildFragmentManager(), chartFragList);
		chartVp.setAdapter(chartVPAdapter);
//        将Fragment加载到Acitivy当中
	}
	
	/* 统计某年某月的收支情况数据*/
	private void initStatistics(int year, int month) {
		float inMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);  //收入总钱数
		float outMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0); //支出总钱数
		int incountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 1);  //收入多少笔
		int outcountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 0); //支出多少笔
		dateTv.setText(year+"年"+month+"月账单");
		inTv.setText("共"+incountItemOneMonth+"笔收入, ￥ "+inMoneyOneMonth);
		outTv.setText("共"+outcountItemOneMonth+"笔支出, ￥ "+outMoneyOneMonth);
		
	}
	
	/** 初始化时间的方法*/
	private void initTime() {
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH)+1;
	}
	
	/* 显示日历对话框*/
	private void showCalendarDialog() {
		Log.d("ChartFrag", "showCalendarDialog: ");
		CalendarDialog dialog = new CalendarDialog(getContext(), selectPos, selectMonth);
		dialog.show();
		dialog.setDialogSize();
		dialog.setOnRefreshListener(new CalendarDialog.OnRefreshListener() {
			@Override
			public void onRefresh(int selPos, int year, int month) {
				ChartFrag.this.selectPos = selPos;
				ChartFrag.this.selectMonth = month;
				initStatistics(year,month);
				incomChartFragment.setDate(year,month);
				outcomChartFragment.setDate(year,month);
			}
		});
	}
	
	/* 设置按钮样式的改变  支出-0  收入-1*/
	private void setButtonStyle(int kind){
		if (kind == 0) {
			outBtn.setBackgroundResource(R.drawable.main_recordbtn_bg);
			outBtn.setTextColor(Color.WHITE);
			inBtn.setBackgroundResource(R.drawable.dialog_btn_bg);
			inBtn.setTextColor(Color.BLACK);
		}else if (kind == 1){
			inBtn.setBackgroundResource(R.drawable.main_recordbtn_bg);
			inBtn.setTextColor(Color.WHITE);
			outBtn.setBackgroundResource(R.drawable.dialog_btn_bg);
			outBtn.setTextColor(Color.BLACK);
		}
	}
}
