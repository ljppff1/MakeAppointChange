package com.example.makeappoint;


import java.util.ArrayList;
import java.util.List;

import com.example.domain.AppointList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * —°‘Ò’ÔÀ˘
 * @author liujun
 *
 */
public class MyRevDetailActivity extends Activity {

	private Button mRlre1;
	private ListView mLvchoseva;
    private List<String> list =new ArrayList<String>();
	private RelativeLayout mRlre11;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myrevdetail);
		
       
		initView();
		

	}


	private void initView() {
		mRlre1 =(Button)this.findViewById(R.id.mBtNext);
		mRlre1.setOnClickListener(listener);
		mRlre11 =(RelativeLayout)this.findViewById(R.id.mRlre1);
		mRlre11.setOnClickListener(listener);
		
	}
	
	
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mBtNext:
				finish();
				break;
			case R.id.mRlre1:
				finish();
				break;

			default:
				break;
			}
		}
	};

}
