package com.example.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.testswipedemo.R;

public class ListViewAdapter extends BaseSwipeAdapter{
	
	private Context mContext;
	private Handler handler;
	private ArrayList<String> arrList=new ArrayList<String>();
	public ListViewAdapter(Context mContext,Handler handler) {
	        this.mContext = mContext;
	        this.handler=handler;
	        for(int i=0;i<50;i++){
	        	arrList.add("第"+i+"行评论");
	        }
	}
	 
	@Override
	public int getCount() {
		return 50;
	}

	@Override
	public Object getItem(int position) {
		return arrList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getSwipeLayoutResourceId(int position) {
		return R.id.swipe;
	}
	//绘制,只绑定一次
	@Override
	public View generateView(int position, ViewGroup parent) {
		final int nowpp=position;

        final View v = LayoutInflater.from(mContext).inflate(R.layout.listview_item, null);
        final SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
//                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
            @Override
            public void onClose(SwipeLayout layout) {
            	super.onClose(layout);
            	notifyDatasetChanged();
            }
        });
        
        final Honder honder=new Honder();
        honder.bt=(Button) v.findViewById(R.id.item_button);
        honder.et=(EditText) v.findViewById(R.id.item_EditText);		
        honder.tv=(TextView) v.findViewById(R.id.item_textview);
        honder.tvPosition=(TextView) v.findViewById(R.id.item_position);
        honder.swipeLayout=swipeLayout;
        v.setTag(honder);
        
        
        return v;
	}
	
	//填内容，绑事件
	@Override
	public void fillValues(final int position, View convertView) {
		final Honder honder= (Honder) convertView.getTag();
		
        honder.tvPosition.setText(position+",");
		String str=arrList.get(position);
		if(str!=null){
			honder.tv.setText(str);honder.et.setText(str);
		}
        honder.bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				arrList.set(position, honder.et.getText().toString());
				honder.swipeLayout.close();
				notifyDatasetChanged();
			}
		});
        
        
	}
	
	private void showInputMethod(View view){
		InputMethodManager imm=(InputMethodManager) mContext.getSystemService(mContext.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
	}
	
	class Honder{
		TextView tv;TextView tvPosition;
		EditText et;
		Button bt;
		SwipeLayout swipeLayout;
	}
}
