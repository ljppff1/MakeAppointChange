package com.example.makeappoint;


import java.util.ArrayList;
import java.util.List;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.example.domain.AppointList;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
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
import android.widget.Toast;
/**
 * —°‘Ò’ÔÀ˘
 * @author liujun
 *
 */
public class ChoservaActivity extends Activity {

	private RelativeLayout mRlre1;
	private SwipeMenuListView mLvchoseva;
    private List<AppointList> list =new ArrayList<AppointList>();
	private AreaAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chosereserva);
		initView();
		initData();
		

	}

	private void initData() {
		for(int i=0;i<45;i++){
			AppointList al =new AppointList();
			list.add(al);
		}
	}

	private void initView() {
		mRlre1 =(RelativeLayout)this.findViewById(R.id.mRlre1);
		mRlre1.setOnClickListener(listener);
		mLvchoseva =(SwipeMenuListView)this.findViewById(R.id.mLvchoseva);
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem deleteItem1 = new SwipeMenuItem(
						getApplicationContext());
				// set item background
				deleteItem1.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				deleteItem1.setBackground(R.drawable.d2);
				// set item width
				deleteItem1.setWidth(dp2px(90));
				// set a icon
				deleteItem1.setIcon(R.drawable.b16);
				// add to menu
				menu.addMenuItem(deleteItem1);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(
						getApplicationContext());
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
						0x3F, 0x25)));
				deleteItem.setBackground(R.drawable.d2);

				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.b15);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		mLvchoseva.setMenuCreator(creator);

		// step 2. listener item click event
		mLvchoseva.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
			
				switch (index) {
				case 0:
					Toast.makeText(getApplicationContext(), "aa", 0).show();
					break;
				case 1:
					Toast.makeText(getApplicationContext(), "bb", 0).show();
					break;
				}
			}
		});
		
		
		adapter =new AreaAdapter();
		mLvchoseva.setAdapter(adapter);
		mLvchoseva.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(getApplicationContext(), ChoservaN3Activity.class));
			}
		});
		
	}
	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

	class Holder{
		TextView mTvItext1,mTvi1;
		LinearLayout mLLic1;
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
						.inflate(R.layout.itemchoseva1, null);
				holder = new Holder();
				holder.mTvi1 =(TextView)convertView.findViewById(R.id.mTvi1);
				
				convertView.setTag(holder);
	
			}else{
				holder =(Holder)convertView.getTag();
			}
			if(position==0){
				holder.mTvi1.setText(R.string.i121a);
			}		
			if(position==4){
				holder.mTvi1.setText(R.string.i121d);
			}
			if(position==7){
				holder.mTvi1.setText(R.string.i121c);
			}
			if(position==14){
				holder.mTvi1.setText(R.string.i121b);
			}
			
            if(position!=0&&position!=4&&position!=7&&position!=14){
				holder.mTvi1.setText("");
			}
			return convertView;
		}
		
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
