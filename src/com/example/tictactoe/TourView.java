package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TourView extends LinearLayout implements Observer{
	
	TextView textView;
	TextView textView2;
	TextView textView3;
	
	public TourView(Context context) {
		super(context);
		MyApplication.model.addObserver(this);
		View.inflate(context, R.layout.tournament_view, this);
		textView = (TextView) this.findViewById(R.id.tournamentInfo);
		textView2 = (TextView) this.findViewById(R.id.tournamentInfo2);
		textView3 = (TextView) this.findViewById(R.id.tournamentInfo3);
		MyApplication.model.initObservers();
	}
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		if(MyApplication.model.getTMode()){
			textView.setText(MyApplication.model.getTxMessage());
			textView2.setText( MyApplication.model.getToMessage());
			textView3.setText( MyApplication.model.getTieMessage());
		}
	}

}