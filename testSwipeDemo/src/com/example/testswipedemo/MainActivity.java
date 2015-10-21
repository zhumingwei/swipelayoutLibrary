package com.example.testswipedemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.adapter.ListViewAdapter;

public class MainActivity extends Activity {
	ListView listview;
	ListViewAdapter adapter;
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				View v=(View) msg.obj;
				System.out.println("v=="+v);
				
				showInputMethod( v);
				break;
			default:
				break;
			}
		}
	};
	private void showInputMethod(View view){
		InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView) findViewById(R.id.listview);
		adapter=new ListViewAdapter(MainActivity.this,handler);
		listview.setAdapter(adapter);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	            @Override
	            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//	                ((SwipeLayout)(listview.getChildAt(position - listview.getFirstVisiblePosition()))).open(true);
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
