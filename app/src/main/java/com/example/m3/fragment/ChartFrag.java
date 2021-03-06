package com.example.m3.fragment;

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

import com.example.m3.R;
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
		View view = inflater.inflate(R.layout.frag_chart, container, false);
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
//        ??????Fragment?????????
		incomChartFragment = new IncomChartFragment();
		outcomChartFragment = new OutcomChartFragment();
//        ???????????????Fragment??????
		Bundle bundle = new Bundle();
		bundle.putInt("year",year);
		bundle.putInt("month",month);
		incomChartFragment.setArguments(bundle);
		outcomChartFragment.setArguments(bundle);
//        ???Fragment????????????????????????
		chartFragList.add(outcomChartFragment);
		chartFragList.add(incomChartFragment);
//        ???????????????
		chartVPAdapter = new ChartVPAdapter(getChildFragmentManager(), chartFragList);
		chartVp.setAdapter(chartVPAdapter);
//        ???Fragment?????????Acitivy??????
	}
	
	/* ???????????????????????????????????????*/
	private void initStatistics(int year, int month) {
		float inMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);  //???????????????
		float outMoneyOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0); //???????????????
		int incountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 1);  //???????????????
		int outcountItemOneMonth = DBManager.getCountItemOneMonth(year, month, 0); //???????????????
		dateTv.setText(year+"???"+month+"?????????");
		inTv.setText("???"+incountItemOneMonth+"?????????, ??? "+inMoneyOneMonth);
		outTv.setText("???"+outcountItemOneMonth+"?????????, ??? "+outMoneyOneMonth);
		
	}
	
	/** ????????????????????????*/
	private void initTime() {
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH)+1;
	}
	
	/* ?????????????????????*/
	private void showCalendarDialog() {
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
	
	/* ???????????????????????????  ??????-0  ??????-1*/
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
