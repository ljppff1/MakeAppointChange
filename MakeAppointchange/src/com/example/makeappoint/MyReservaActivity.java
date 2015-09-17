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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * »∑»œ‘§‘º
 * @author liujun
 *
 */
public class MyReservaActivity extends Activity {

	private RelativeLayout mRlre1;
	private Button mBtNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myreserva);
		initView();
	

	}

	
	private void initView() {
		mRlre1 =(RelativeLayout)this.findViewById(R.id.mRlre1);
		mRlre1.setOnClickListener(listener);
		mBtNext=(Button)this.findViewById(R.id.mBtNext);
		mBtNext.setOnClickListener(listener);
	}
	
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.mRlre1:
				finish();
				break;
			case R.id.mBtNext:
			startActivity(new Intent(getApplicationContext(), MyRevActivity.class));
				break;
			default:
				break;
			}
		}
	};

}
