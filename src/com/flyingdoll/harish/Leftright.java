package com.flyingdoll.harish;

import java.security.acl.LastOwnerException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.TextView;

public class Leftright extends Activity implements SensorEventListener{
	SensorManager sm;
	int soundvalue;
	Flying fly;
	 Dialog dialog;
	 static MediaPlayer clicksound;
	 static Context c;
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode== 4)
		exitByBackKey();
		return super.onKeyDown(keyCode, event);
	}
	
	@SuppressLint("NewApi")
	public void exitByBackKey(){
		// TODO Auto-generated method stub
		//super.onBackPressed();
         Flying.gamepaused=true;
        Flying.backgroundmusic.pause(); 
        dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// //////////////////
		Window window = dialog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		dialog.setCancelable(false);
		sm.unregisterListener(this);
		dialog.setContentView(R.layout.pause);
			
		Button resume,restart,exit;
		resume=(Button)dialog.findViewById(R.id.bresume);
		restart=(Button)dialog.findViewById(R.id.brestart);
		exit=(Button)dialog.findViewById(R.id.bexit);
			resume.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Flying.backgroundmusic.start();
					Flying.gamepaused=false;
					registeraccelerometer();
					dialog.dismiss();
					fly.invalidate();
				    if(Menu.soundb==1) 	clicksound.start();
					
				}
			});
			/////////////////////////////////////////////
			restart.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					fly.destroyDrawingCache();
					fly=null;
					
					fly = new Flying(Leftright.this);
					setContentView(fly);
					registeraccelerometer();
					 if(Menu.soundb==1) clicksound.start();
					dialog.dismiss();
					
					//fly.invalidate();
					
					
				}
			});
			exit.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//System.exit(0);
					 if(Menu.soundb==1) clicksound.start();
					Intent menu = new Intent(Leftright.this,Menu.class);
					startActivity(menu);
					
				}
			});
			
		//	SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
			//SharedPreferences.Editor editor= getPreferences(MODE_PRIVATE).edit();
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplication());
			SharedPreferences.Editor editor = prefs.edit();
			//editor.putInt("text" ,123);
			editor.putString("score", String.valueOf(Flying.maxscore));
			editor.apply();
			//editor.commit();
			 Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			// v.vibrate(500);
			 dialog.show();
			 
	}
	

	public void onresumebuttonclick(View view) {
		
		//Flying.backgroundmusic.start();
		//Flying.gamepaused=false;
		//dialog.hide();
		
		
	} 
	
	
	
	
	

	
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		// Flying.imagex=-(int) event.values[0]*30;
		
		if (event.values[0] == 0) {
		} else {
			if (event.values[0] > 0)
				// if(Flying.imagex>0) //we do - ///event.values[0] return +
				// when phone slided to <---
				Flying.imagex = Flying.imagex - (int) event.values[0]  * 3;// -(int)event.values[2]
																			// ;
			if (Flying.imagex < 0)
				Flying.imagex = 0;
			else
				// if(Flying.imagex>1200)
				Flying.imagex = Flying.imagex - (int) event.values[0] * 3;// -(int)event.values[2]
																			// ;
			if (Flying.imagex > Flying.canvaswidth)
				Flying.imagex = Flying.canvaswidth;
		}
		// Flying.imagex=-(int) event.values[0]*10;
	}

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		clicksound = MediaPlayer.create(getBaseContext(), R.raw.click);
		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			
			
			// sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
			//sm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
			
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
		}

		 requestWindowFeature(Window.FEATURE_NO_TITLE);
		 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// MediaPlayer backgroundmusic;
		// backgroundmusic = MediaPlayer.create(this, R.raw.a);
		c=this;
		fly = new Flying(this);
		setContentView(fly);
	
	/*	if(Flying.gameover==true)
		{
			setContentView(R.layout.newcontinue);
			
		}
		  
		*/
	


	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		sm.unregisterListener(this);
		soundvalue = Flying.backgroundmusic.getCurrentPosition();
		Flying.backgroundmusic.stop();
		
		
		// Menu.music.seekTo(00);
		// Menu.music.start();
		// finish();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			// sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
			//Sensor s = sm.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION).get(0);
			//sm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
			
		}
		
		Flying.backgroundmusic.seekTo(soundvalue);
		Flying.backgroundmusic.start();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			// sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
		//	sm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
		}
		Flying.backgroundmusic.seekTo(soundvalue);
		
		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}
  
	public void registeraccelerometer()
	{
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			// sm.registerListener(this, s,SensorManager.SENSOR_DELAY_NORMAL);
			//sm.registerListener(this, s, SensorManager.SENSOR_DELAY_FASTEST);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
		}
	}

public static  void exitformcavas()
{  
	Dialog dialog = new Dialog(c);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// //////////////////
		Window window = dialog.getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
				WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
		dialog.setCancelable(false);
		//sm.unregisterListener(c); 
		dialog.setContentView(R.layout.newcontinue);
		dialog.show();
		TextView distance,highscore;
		Button continue1;
		distance =(TextView)dialog.findViewById(R.id.continuedistance);
		highscore =(TextView)dialog.findViewById(R.id.continuenewhighscore);
		continue1 =(Button)dialog.findViewById(R.id.continueyes);
		
		
		distance.setText("DISTANCE : " + Flying.score+"m");
		
		continue1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			//	clicksound.start();
				if(Menu.soundb==1) clicksound.start();
                Intent menu = new Intent(c,Menu.class);
				//startActivity(menu);
				
			    c.startActivity(menu);
			}
		});
		
		
		 // long[] pattern = { 0,100,200,100,100,100,100,100,200,100,500,100,225,100 };
	         Vibrator v = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
			 // Vibrate for 500 milliseconds
		 v.vibrate(100);
		
		
		
		if(Flying.score>=Flying.maxscore){
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
		SharedPreferences.Editor editor = prefs.edit();
		//editor.putInt("text" ,123);
		highscore.setVisibility(1);
		editor.putString("score", String.valueOf(Flying.score));
		//editor.apply();
		editor.commit();	
		
		}
}
	

	
	
}
