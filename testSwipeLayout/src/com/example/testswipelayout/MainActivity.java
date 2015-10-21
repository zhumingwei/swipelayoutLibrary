package com.example.testswipelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.SwipeLayout.ShowMode;

public class MainActivity extends Activity {
	private EditText et;
	private TextView tv;
	private SwipeLayout sl;
	private Button but;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		but=(Button) findViewById(R.id.button);
		et=(EditText) findViewById(R.id.et);
		tv=(TextView) findViewById(R.id.tv);
		sl=(SwipeLayout) findViewById(R.id.test_swipe_swipe);
		sl.setShowMode(ShowMode.PullOut);
//		sl.setShowMode(ShowMode.LayDown);
		sl.addSwipeListener(new SimpleSwipeListener(){
			@Override
			public void onOpen(SwipeLayout layout) {
				super.onOpen(layout);
			}
			@Override
			public void onClose(SwipeLayout layout) {
				super.onClose(layout);
				tv.setText(et.getText());
			}
		});
		but.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sl.close();
			}
		});
		
	}


}
