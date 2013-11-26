package com.game.mastermind.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.game.mastermind.R;
import com.game.mastermind.maingame.ActivityMainGame;
import com.game.mastermind.util.CommonValue;

public class ActivityHomePage extends Activity {

	public static String TAG = "ActivityHomePage";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_hompage);
		
		//
		//	Initialize()
		//
		Initailize();
		
		//
		//	Get controls
		//
		final RelativeLayout button_easy = (RelativeLayout) findViewById(R.id.relativelayout_button_easy);
		final RelativeLayout button_medium = (RelativeLayout) findViewById(R.id.relativelayout_button_medium);
		final RelativeLayout button_hard = (RelativeLayout) findViewById(R.id.relativelayout_button_hard);
		
		
		//	Button easy
		button_easy.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(ActivityHomePage.this, ActivityMainGame.class);
				intent.putExtra(ActivityMainGame.TAG, CommonValue.NUMBER_SLOT_EASY);
				startActivity(intent);
				
			}
		});
		
		//	Button medium
		button_medium.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(ActivityHomePage.this, ActivityMainGame.class);
				intent.putExtra(ActivityMainGame.TAG, CommonValue.NUMBER_SLOT_MEDIUM);
				startActivity(intent);
				
			}
		});
		
		//	Button hard
		button_hard.setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(ActivityHomePage.this, ActivityMainGame.class);
				intent.putExtra(ActivityMainGame.TAG, CommonValue.NUMBER_SLOT_HARD);
				startActivity(intent);
				
			}
		});
		
	}

	private void Initailize() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get Intent ActivityClubs
	 * 
	 * @param context
	 * @return
	 */
	public static void StartActivity(Context context)
	{
		try {			
			Intent intent = new Intent(context,
					ActivityHomePage.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 	    			
			((Activity)context).startActivityForResult(intent, 0); 
			
		} catch (Exception ex) {
			Log.println(Log.DEBUG, TAG, ex.getMessage());
		}
	}

}
