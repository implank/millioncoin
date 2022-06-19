package com.example.m3.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.m3.R;
import com.example.m3.adapter.AccountAdapter;
import com.example.m3.db.AccountBean;
import com.example.m3.db.DBManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFrag extends Fragment {
	ListView todayLv;
	List<AccountBean> mDatas;
	AccountAdapter adapter;
	int year, month, day;
	//头布局相关控件
	View headerView;
	TextView topOutTv, topInTv, topbudgetTv, topConTv;
	SharedPreferences preferences;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view =  inflater.inflate(R.layout.frag_main, container, false);
		initTime();
		todayLv = view.findViewById(R.id.main_lv);
		setLVLongClickListener();
//        preferences = getSharedPreferences("budget", Context.MODE_PRIVATE);
		//添加ListView的头布局
		addLVHeaderView();
		mDatas = new ArrayList<>();
		//设置适配器：加载每一行数据到列表当中
		adapter = new AccountAdapter(getContext(), mDatas);
		todayLv.setAdapter(adapter);
		setTopTvShow();
		loadDBData();
		return view;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		setTopTvShow();
		loadDBData();
	}
	
	void initTime() {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
	}
	private void setLVLongClickListener() {
		todayLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {  //点击了头布局
					return false;
				}
				int pos = position - 1;
				AccountBean clickBean = mDatas.get(pos);  //获取正在被点击的这条信息
				
				//弹出提示用户是否删除的对话框
				return false;
			}
		});
	}
	private void addLVHeaderView() {
		//将布局转换成View对象
		headerView = getLayoutInflater().inflate(R.layout.item_mainlv_top, null);
		todayLv.addHeaderView(headerView);
		//查找头布局可用控件
		topOutTv = headerView.findViewById(R.id.item_mainlv_top_tv_out);
		topInTv = headerView.findViewById(R.id.item_mainlv_top_tv_in);
		topbudgetTv = headerView.findViewById(R.id.item_mainlv_top_tv_budget);
		topConTv = headerView.findViewById(R.id.item_mainlv_top_tv_day);
	}
	private void setTopTvShow() {
        //获取今日支出和收入总金额，显示在view当中
        float incomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 1);
        float outcomeOneDay = DBManager.getSumMoneyOneDay(year, month, day, 0);
        String infoOneDay = "今日支出 ￥"+outcomeOneDay+"  收入 ￥"+incomeOneDay;
        topConTv.setText(infoOneDay);
//        获取本月收入和支出总金额
        float incomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 1);
        float outcomeOneMonth = DBManager.getSumMoneyOneMonth(year, month, 0);
        topInTv.setText("￥"+incomeOneMonth);
        topOutTv.setText("￥"+outcomeOneMonth);

//    设置显示运算剩余
//        float bmoney = preferences.getFloat("bmoney", 0);//预算
//        if (bmoney == 0) {
//            topbudgetTv.setText("￥ 0");
//        }else{
//            float syMoney = bmoney-outcomeOneMonth;
//            topbudgetTv.setText("￥"+syMoney);
//        }
    }
	private void loadDBData() {
    List<AccountBean> list = DBManager.getAccountListOneDayFromAccounttb(year, month, day);
    mDatas.clear();
    mDatas.addAll(list);
    adapter.notifyDataSetChanged();
  }
}
