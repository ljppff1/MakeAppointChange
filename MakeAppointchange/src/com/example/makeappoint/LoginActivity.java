package com.example.makeappoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

	private Button mLogin1;
	private Button mLogin2;
	private Button mLogin3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		findView();
		
		
		
		
		
		
	}

	private void findView() {
		mLogin1 =(Button)this.findViewById(R.id.mLogin1);
		mLogin2 =(Button)this.findViewById(R.id.mLogin2);
		mLogin3 =(Button)this.findViewById(R.id.mLogin3);
		mLogin1.setOnClickListener(listener);
		mLogin2.setOnClickListener(listener);
		mLogin3.setOnClickListener(listener);
		
	}
	
	OnClickListener listener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			startActivity(new Intent(getApplicationContext(), ReservationActivity.class));
			finish();
		}
	};

}
