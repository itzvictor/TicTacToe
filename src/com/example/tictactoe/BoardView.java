package com.example.tictactoe;


import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View implements Observer{
	Paint paint = new Paint();
	Point m = new Point();
	double boxX = 100;
	double boxY = 100;
	int curSqX = - 999;
	int curSqY = - 999;
	double height;
	double width;
	double xSpace;
	double ySpace;
	boolean actionDown = false;
	int move = 1;
	public void theBoard(){
		MyApplication.model.addObserver(this);
		this.addOnLayoutChangeListener(new OnLayoutChangeListener() {

			@Override
			public void onLayoutChange(View v, int left, int top, int right,
					int bottom, int oldLeft, int oldTop, int oldRight,
					int oldBottom) {
				height = bottom - top;
				width = right - left;
				xSpace = (width - 3*boxX)/2;
				ySpace = (height - 3*boxY)/2;
			}

		});
		this.setLongClickable(true);
		MyApplication.model.startGame();
		MyApplication.model.updateMove(1);
		this.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == arg1.ACTION_DOWN){
					actionDown = true;
				}else if(arg1.getAction() == arg1.ACTION_UP){
					actionDown = false;
					if (!MyApplication.model.isGameWin()) {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								if (m.x > i * (int) boxX + (int) xSpace && m.x < (i + 1) * (int) boxX + (int) xSpace && m.y > j * (int) boxY + (int) ySpace && m.y < (j + 1) * (int) boxY + (int) ySpace) {
									curSqX = i;
									curSqY = j;
									if (MyApplication.model.checkMove(curSqX, curSqY) && !MyApplication.model.checkBoard() && MyApplication.model.isStart()) {
										MyApplication.model.setMoveMade(true);
										MyApplication.model.incrementCount();
										MyApplication.model.updateBox(curSqX, curSqY, true);
									}
								}
							}
						}
					}
						
					if (!MyApplication.model.isGameWin()) {
						MyApplication.model.checkBoard();
					}

				}

				m.x = (int)arg1.getX();
				m.y = (int)arg1.getY();

				postInvalidate();
				return false;
			}

		});
	}

	@Override
	public void onDraw(Canvas canvas) {
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(5.0f);
		paint.setStyle(Paint.Style.STROKE);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				canvas.drawRect(i * (int)boxX + (int)xSpace, j * (int)boxY+(int)ySpace, i*(int)boxX+(int)xSpace+(int)boxX, j*(int)boxY+(int)ySpace+(int)boxY,paint);
			}
		}
		if(actionDown){
			paint.setColor(Color.YELLOW);
			paint.setStyle(Paint.Style.FILL);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if(actionDown){
						if (m.x > i * (int) boxX + (int) xSpace && m.x < (i + 1) * (int) boxX + (int) xSpace && m.y > j * (int) boxY + (int) ySpace && m.y < (j + 1) * (int) boxY + (int) ySpace){					
							canvas.drawRect(i * (int)boxX + (int)xSpace+2, j * (int)boxY+(int)ySpace+2, i*(int)boxX+(int)xSpace+(int)boxX-2, j*(int)boxY+(int)ySpace+(int)boxY-2,paint);
						}
					}
				}
			}
		}
		paint.setColor(Color.BLACK);
		paint.setStyle(Paint.Style.STROKE);
		for (int i = 0; i < MyApplication.model.boxInfo.size(); i++) {
			if (MyApplication.model.boxInfo.get(i).move == -1) {
				RectF r = new RectF(MyApplication.model.boxInfo.get(i).squareX * (int) boxX + (int) xSpace,MyApplication.model.boxInfo.get(i).squareY* (int) boxY + (int) ySpace,(MyApplication.model.boxInfo.get(i).squareX +1)* (int) boxX + (int) xSpace,(MyApplication.model.boxInfo.get(i).squareY +1)* (int) boxY + (int) ySpace);
				canvas.drawOval(r, paint);               
			} else {
				canvas.drawLine(MyApplication.model.boxInfo.get(i).squareX * (int) boxX + (int) xSpace, MyApplication.model.boxInfo.get(i).squareY * (int) boxY + (int) ySpace,
						MyApplication.model.boxInfo.get(i).squareX * (int) boxX + (int) xSpace  + (int) boxX, MyApplication.model.boxInfo.get(i).squareY * (int) boxY + (int) ySpace + (int) boxY,paint);
				canvas.drawLine(MyApplication.model.boxInfo.get(i).squareX * (int) boxX + (int) xSpace + (int) boxX, MyApplication.model.boxInfo.get(i).squareY * (int) boxY + (int) ySpace,
						MyApplication.model.boxInfo.get(i).squareX * (int) boxX + (int) xSpace, MyApplication.model.boxInfo.get(i).squareY * (int) boxY + (int) ySpace + (int) boxY,paint);
			}        
		}

		if (MyApplication.model.isGameWin()) {
			paint.setColor(Color.GREEN);            
			canvas.drawRect(MyApplication.model.getWinX() * (int) boxX + (int) xSpace, MyApplication.model.getWinY() * (int) boxY + (int) ySpace, (MyApplication.model.getWinX()+1) * (int) boxX + (int) xSpace, (MyApplication.model.getWinY()+1) * (int) boxY + (int) ySpace,paint);
			canvas.drawRect(MyApplication.model.getWinX2() * (int) boxX + (int) xSpace, MyApplication.model.getWinY2() * (int) boxY + (int) ySpace, (MyApplication.model.getWinX2()+1) * (int) boxX + (int) xSpace, (MyApplication.model.getWinY2()+1) * (int) boxY + (int) ySpace,paint);
			canvas.drawRect(MyApplication.model.getWinX3() * (int) boxX + (int) xSpace, MyApplication.model.getWinY3() * (int) boxY + (int) ySpace, (MyApplication.model.getWinX3()+1) * (int) boxX + (int) xSpace, (MyApplication.model.getWinY3()+1) * (int) boxY + (int) ySpace,paint);
		}
	}
	public BoardView(Context context) {
		super(context);	
		theBoard();
	}	
	public BoardView (Context context, AttributeSet attrs){
		super(context,attrs);
		theBoard();
	}

	public BoardView (Context context, AttributeSet attrs, int defSty){
		super(context, attrs,defSty);
		theBoard();
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}
}
