package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameView extends LinearLayout implements Observer{
	public GameView(Context context) {
		super(context);
	}
	public GameView (Context context, AttributeSet attrs){
		super(context,attrs);
	}

	public GameView (Context context, AttributeSet attrs, int defSty){
		super(context, attrs,defSty);
	}
	
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}
