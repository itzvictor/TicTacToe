package com.example.tictactoe;

import java.util.Observable;
import java.util.Observer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class GameOption extends Activity{
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_option);
		Button button = (Button) findViewById(R.id.game_option_button);
		Button button2 = (Button) findViewById(R.id.tmode);
		final EditText et = (EditText) this.findViewById(R.id.editText1);
		final EditText et2 = (EditText) this.findViewById(R.id.editText2);
		final RadioButton rb = (RadioButton) this.findViewById(R.id.radio0);
		final RadioButton rb2 = (RadioButton) this.findViewById(R.id.radio1);
		// create a controller for the increment counter button
		button.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// create intent to request the view2 activity to be shown
				Intent intent = new Intent(context, BoardActivity.class);
				if(rb.isChecked()){
					MyApplication.model.setP1m("X");
					MyApplication.model.setP2m("O");
				}else if(rb2.isChecked()){
					MyApplication.model.setP2m("X");
					MyApplication.model.setP1m("O");
				}
				MyApplication.model.setTMode(false);
				MyApplication.model.newGame();
				// start the activity
                startActivity(intent);   
                // we're done with this activity ...
                //finish();
			}
		});
		
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// create intent to request the view2 activity to be shown
				Intent intent = new Intent(context, BoardActivity.class);
				if(rb.isChecked()){
					MyApplication.model.setP1m("X");
					MyApplication.model.setP2m("O");
				}else if(rb2.isChecked()){
					MyApplication.model.setP2m("X");
					MyApplication.model.setP1m("O");
				}
				MyApplication.model.setTMode(true);
				MyApplication.model.newGame();
				// start the activity
                startActivity(intent);   
                // we're done with this activity ...
                //finish();
			}
		});
		et.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				Log.d("1", et.getText().toString());
				MyApplication.model.setP1(et.getText().toString());
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});
		et2.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable arg0) {
				MyApplication.model.setP2(et2.getText().toString());
				
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
		});

		
		// add this activity to the model's list of observers
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_option, menu);
		return true;
	}

}
