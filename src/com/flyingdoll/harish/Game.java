package com.flyingdoll.harish;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;



public class Game extends Activity{

	Flying fly;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//Intent ourIntent = new Intent("android.intent.action.LEFTRIGHT");
		//startActivity(ourIntent);
		//startService(ourIntent);
	//////	Intent ourIntent = new Intent(this,Leftright.class);
		//startService(ourIntent);
//////		startActivity(ourIntent);
	//	fly = new Flying(this);
		//setContentView(fly);
		
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
		
	}
	
	
	
	
}