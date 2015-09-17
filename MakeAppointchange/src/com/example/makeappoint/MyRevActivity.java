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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
/**
 * —°‘Ò’ÔÀ˘
 * @author liujun
 *
 */
public class MyRevActivity extends Activity {

	private RelativeLayout mRlLeft;
	private Fragment mContent;
	private RelativeLayout mRlre1;
	private ListView mLvchoseva;
    private List<String> list =new ArrayList<String>();
	private AreaAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myrev);
		initData();

		initView();
		

	}


	private void initView() {
		mRlre1 =(RelativeLayout)this.findViewById(R.id.mRlre1);
		mRlre1.setOnClickListener(listener);
		mLvchoseva =(ListView)this.findViewById(R.id.mLvchoseva);
		mLvchoseva.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(getApplicationContext(), MyRevDetailActivity.class));
			}
		});
        initData();
		adapter =new AreaAdapter();
		mLvchoseva.setAdapter(adapter);

	}
	class Holder{
		TextView mTvtime;
	}

	private class AreaAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if(convertView==null){
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.itemmyrev, null);
				
				holder = new Holder();
				holder.mTvtime =(TextView) convertView.findViewById(R.id.mTvtime);
				convertView.setTag(holder);
	
			}else{
				holder =(Holder)convertView.getTag();
			}
			holder.mTvtime.setText(list.get(position));
			
			return convertView;
		}
		
	}

	private void initData() {
		list.clear();
		list.add(getString(R.string.Itext16));
		list.add(getString(R.string.Itext16));
		list.add(getString(R.string.Itext17));
		list.add(getString(R.string.Itext17));
		list.add(getString(R.string.Itext17));
		
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
