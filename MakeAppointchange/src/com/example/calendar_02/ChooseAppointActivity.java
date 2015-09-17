package com.example.calendar_02;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.domain.AppointList;
import com.example.makeappoint.ChoTimeDetailActivity;
import com.example.makeappoint.R;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.view.Display;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AbsListView.LayoutParams;

public class ChooseAppointActivity extends Activity implements OnGestureListener, OnClickListener {
	private ViewFlipper flipper1 = null;
	// private ViewFlipper flipper2 = null;
	private static String TAG = "ZzL";
	private GridView gridView = null;
	private GestureDetector gestureDetector = null;
	private int year_c = 0;
	private int month_c = 0;
	private int day_c = 0;
	private int week_c = 0;
	private int week_num = 0;
	private String currentDate = "";
	private static int jumpWeek = 0;
	private static int jumpMonth = 0;
	private static int jumpYear = 0;
	private DateAdapter dateAdapter;
	private int daysOfMonth = 0; // 某月的天数
	private int dayOfWeek = 0; // 具体某一天是星期几
	private int weeksOfMonth = 0;
	private SpecialCalendar sc = null;
	private boolean isLeapyear = false; // 是否为闰年
	private int selectPostion = 0;
	private String dayNumbers[] = new String[7];
	private TextView tvDate;
	private int currentYear;
	private int currentMonth;
	private int currentWeek;
	private int currentDay;
	private int currentNum;
	private boolean isStart;// 是否是交接的月初
	private ListView mLvchoseva;
    private List<String> list =new ArrayList<String>();
	private AreaAdapter adapter;
	private RelativeLayout mRlre1;

	public ChooseAppointActivity() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		currentDate = sdf.format(date);
		year_c = Integer.parseInt(currentDate.split("-")[0]);
		month_c = Integer.parseInt(currentDate.split("-")[1]);
		day_c = Integer.parseInt(currentDate.split("-")[2]);
		currentYear = year_c;
		currentMonth = month_c;
		currentDay = day_c;
		sc = new SpecialCalendar();
		getCalendar(year_c, month_c);
		week_num = getWeeksOfMonth();
		currentNum = week_num;
		if (dayOfWeek == 7) {
			week_c = day_c / 7 + 1;
		} else {
			if (day_c <= (7 - dayOfWeek)) {
				week_c = 1;
			} else {
				if ((day_c - (7 - dayOfWeek)) % 7 == 0) {
					week_c = (day_c - (7 - dayOfWeek)) / 7 + 1;
				} else {
					week_c = (day_c - (7 - dayOfWeek)) / 7 + 2;
				}
			}
		}
		currentWeek = week_c;
		getCurrent();

	}

	/**
	 * 判断某年某月所有的星期数
	 * 
	 * @param year
	 * @param month
	 */
	public int getWeeksOfMonth(int year, int month) {
		// 先判断某月的第一天为星期几
		int preMonthRelax = 0;
		int dayFirst = getWhichDayOfWeek(year, month);
		int days = sc.getDaysOfMonth(sc.isLeapYear(year), month);
		if (dayFirst != 7) {
			preMonthRelax = dayFirst;
		}
		if ((days + preMonthRelax) % 7 == 0) {
			weeksOfMonth = (days + preMonthRelax) / 7;
		} else {
			weeksOfMonth = (days + preMonthRelax) / 7 + 1;
		}
		return weeksOfMonth;

	}

	/**
	 * 判断某年某月的第一天为星期几
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getWhichDayOfWeek(int year, int month) {
		return sc.getWeekdayOfMonth(year, month);

	}

	/**
	 * 
	 * @param year
	 * @param month
	 */
	public int getLastDayOfWeek(int year, int month) {
		return sc.getWeekDayOfLastMonth(year, month,
				sc.getDaysOfMonth(isLeapyear, month));
	}

	public void getCalendar(int year, int month) {
		isLeapyear = sc.isLeapYear(year); // 是否为闰年
		daysOfMonth = sc.getDaysOfMonth(isLeapyear, month); // 某月的总天数
		dayOfWeek = sc.getWeekdayOfMonth(year, month); // 某月第一天为星期几
	}

	public int getWeeksOfMonth() {
		// getCalendar(year, month);
		int preMonthRelax = 0;
		if (dayOfWeek != 7) {
			preMonthRelax = dayOfWeek;
		}
		if ((daysOfMonth + preMonthRelax) % 7 == 0) {
			weeksOfMonth = (daysOfMonth + preMonthRelax) / 7;
		} else {
			weeksOfMonth = (daysOfMonth + preMonthRelax) / 7 + 1;
		}
		return weeksOfMonth;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.chooseappoint);
		tvDate = (TextView) findViewById(R.id.tv_date);
		tvDate.setText(year_c + "年" + month_c + "月" + day_c + "日");
		gestureDetector = new GestureDetector(this);
		flipper1 = (ViewFlipper) findViewById(R.id.flipper1);
		dateAdapter = new DateAdapter(this, getResources(), currentYear,
				currentMonth, currentWeek, currentNum, selectPostion,
				currentWeek == 1 ? true : false);
		addGridView();
		dayNumbers = dateAdapter.getDayNumbers();
		gridView.setAdapter(dateAdapter);
		selectPostion = dateAdapter.getTodayPosition();
		gridView.setSelection(selectPostion);
		flipper1.addView(gridView, 0);
		mLvchoseva =(ListView)this.findViewById(R.id.mLvchosevadetail1);
		mLvchoseva.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(getApplicationContext(), ChoTimeDetailActivity.class));
			}
		});
		mRlre1 =(RelativeLayout)this.findViewById(R.id.mRlre1);
		mRlre1.setOnClickListener(this);
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
						.inflate(R.layout.itemchoseappoint, null);
				
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
		list.add("9:00-9:30");
		list.add("10:00-10:30");
		list.add("10:30-11:00");
		list.add("14:00-14:30");
		list.add("15:00-15:30");
		
	}

	private void addGridView() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		gridView = new GridView(this);
		gridView.setNumColumns(7);
		gridView.setGravity(Gravity.CENTER_VERTICAL);
		gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gridView.setVerticalSpacing(1);
		gridView.setHorizontalSpacing(1);
		gridView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return ChooseAppointActivity.this.gestureDetector.onTouchEvent(event);
			}
		});

		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.i(TAG, "day:" + dayNumbers[position]);
				selectPostion = position;
				dateAdapter.setSeclection(position);
				dateAdapter.notifyDataSetChanged();
				tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
						+ dateAdapter.getCurrentMonth(selectPostion) + "月"
						+ dayNumbers[position] + "日");
			}
		});
		gridView.setLayoutParams(params);
	}

	@Override
	protected void onPause() {
		super.onPause();
		jumpWeek = 0;
	}

	@Override
	public boolean onDown(MotionEvent e) {

		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	/**
	 * 重新计算当前的年月
	 */
	public void getCurrent() {
		if (currentWeek > currentNum) {
			if (currentMonth + 1 <= 12) {
				currentMonth++;
			} else {
				currentMonth = 1;
				currentYear++;
			}
			currentWeek = 1;
			currentNum = getWeeksOfMonth(currentYear, currentMonth);
		} else if (currentWeek == currentNum) {
			if (getLastDayOfWeek(currentYear, currentMonth) == 6) {
			} else {
				if (currentMonth + 1 <= 12) {
					currentMonth++;
				} else {
					currentMonth = 1;
					currentYear++;
				}
				currentWeek = 1;
				currentNum = getWeeksOfMonth(currentYear, currentMonth);
			}

		} else if (currentWeek < 1) {
			if (currentMonth - 1 >= 1) {
				currentMonth--;
			} else {
				currentMonth = 12;
				currentYear--;
			}
			currentNum = getWeeksOfMonth(currentYear, currentMonth);
			currentWeek = currentNum - 1;
		}
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		int gvFlag = 0;
		if (e1.getX() - e2.getX() > 80) {
			// 向左滑
			addGridView();
			currentWeek++;
			getCurrent();
			dateAdapter = new DateAdapter(this, getResources(), currentYear,
					currentMonth, currentWeek, currentNum, selectPostion,
					currentWeek == 1 ? true : false);
			dayNumbers = dateAdapter.getDayNumbers();
			gridView.setAdapter(dateAdapter);
			tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
					+ dateAdapter.getCurrentMonth(selectPostion) + "月"
					+ dayNumbers[selectPostion] + "日");
			gvFlag++;
			flipper1.addView(gridView, gvFlag);
			dateAdapter.setSeclection(selectPostion);
			this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_in));
			this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_left_out));
			this.flipper1.showNext();
			flipper1.removeViewAt(0);
			return true;

		} else if (e1.getX() - e2.getX() < -80) {
			addGridView();
			currentWeek--;
			getCurrent();
			dateAdapter = new DateAdapter(this, getResources(), currentYear,
					currentMonth, currentWeek, currentNum, selectPostion,
					currentWeek == 1 ? true : false);
			dayNumbers = dateAdapter.getDayNumbers();
			gridView.setAdapter(dateAdapter);
			tvDate.setText(dateAdapter.getCurrentYear(selectPostion) + "年"
					+ dateAdapter.getCurrentMonth(selectPostion) + "月"
					+ dayNumbers[selectPostion] + "日");
			gvFlag++;
			flipper1.addView(gridView, gvFlag);
			dateAdapter.setSeclection(selectPostion);
			this.flipper1.setInAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_in));
			this.flipper1.setOutAnimation(AnimationUtils.loadAnimation(this,
					R.anim.push_right_out));
			this.flipper1.showPrevious();
			flipper1.removeViewAt(0);
			return true;
			// }
		}
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return this.gestureDetector.onTouchEvent(event);
	}

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

}
