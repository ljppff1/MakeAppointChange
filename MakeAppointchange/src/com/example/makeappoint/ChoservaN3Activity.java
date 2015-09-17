package com.example.makeappoint;


import java.util.ArrayList;
import java.util.List;

import com.example.domain.AppointList;
import com.example.fragment.FragmentFJ1a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * —°‘Ò’ÔÀ˘
 * @author liujun
 *
 */
public class ChoservaN3Activity extends FragmentActivity {

	private RelativeLayout mRlre1;
	private ListView mLvchoseva;
    private List<AppointList> list1 =new ArrayList<AppointList>();
	private ViewPager vp;
	private ArrayList<Fragment> list;
	private FragmentFJ1a fa;
	private FragmentFJ1a fb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chosereservan3);
		initView();
		initData();
		

	}

	private void initData() {
		for(int i=0;i<45;i++){
			AppointList al =new AppointList();
			list1.add(al);
		}
	}
class ZxzcAdapter extends FragmentStatePagerAdapter {
		   
		
		List<Fragment> list;			
		public ZxzcAdapter(FragmentManager fm,List<Fragment> list) {
			super(fm);
			this.list=list;			
		}
		@SuppressLint("ResourceAsColor")
		@Override
		public Fragment getItem(int arg0) {		
			return list.get(arg0);
		}
		@Override
		public int getCount() {
		
			return list.size();
		}

	}

	private void initView() {
		vp=(ViewPager)this.findViewById(R.id.vp1);
		list = new ArrayList<Fragment>();
		 fa=new FragmentFJ1a();
		 fb=new FragmentFJ1a();
		list.add(fa);
		list.add(fb);
       
		ZxzcAdapter zxzc = new ZxzcAdapter(getSupportFragmentManager(), list);
		vp.setAdapter(zxzc);
		zxzc.notifyDataSetChanged();

		mRlre1 =(RelativeLayout)this.findViewById(R.id.mRlre1);
		mRlre1.setOnClickListener(listener);
		
	}
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mRlre1:
				finish();
				break;

			default:
				break;
			}
		}
	};

}
