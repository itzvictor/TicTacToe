package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToolBarView extends LinearLayout implements Observer{
	
	TextView textView;
	TextView playerView;
	TextView playerView2;
	TextView moveView;
	
	public ToolBarView(Context context) {
		super(context);
		MyApplication.model.addObserver(this);
		View.inflate(context, R.layout.toolbar_view, this);
		textView = (TextView) this.findViewById(R.id.toolBarText);
		playerView = (TextView) this.findViewById(R.id.playerText);
		playerView2 = (TextView) this.findViewById(R.id.playerText2);
		moveView = (TextView) this.findViewById(R.id.moveText);
		MyApplication.model.initObservers();
	}
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		playerView.setText(MyApplication.model.getPmessage());
		playerView2.setText(MyApplication.model.getPmessage2());
		textView.setText(MyApplication.model.getMessage());
		moveView.setText(MyApplication.model.getMoveMessage());
	}

}
