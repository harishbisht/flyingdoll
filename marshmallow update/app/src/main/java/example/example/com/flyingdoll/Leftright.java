package example.example.com.flyingdoll;

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
import android.util.Log;
import android.view.KeyEvent;
//import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Amandeep Kaur on 1/27/2017.
 */
public class Leftright extends Activity implements SensorEventListener {
    SensorManager sm;
    int soundvalue;
    FlyingNew fly;
    Dialog dialog;
    static MediaPlayer clicksound;
    static Context c;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        clicksound = MediaPlayer.create(getBaseContext(), R.raw.click2);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        c=this;
        fly = new FlyingNew(this);
        setContentView(fly);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO Auto-generated method stub
        if (event.values[0] == 0) {
        } else {
            if (event.values[0] > 0)
                FlyingNew.imagex = FlyingNew.imagex - (int) event.values[0]  * 3;
            Log.e("eventvalue=",(int)event.values[0]+", imagex="+FlyingNew.imagex);
            if (FlyingNew.imagex < 0)
                FlyingNew.imagex = 0;
            else
                FlyingNew.imagex = FlyingNew.imagex - (int) event.values[0] * 3;
            if (FlyingNew.imagex > FlyingNew.canvaswidth)
                FlyingNew.imagex = FlyingNew.canvaswidth;
        }
    }

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
        FlyingNew.gamepaused=true;
        FlyingNew.backgroundmusic.pause();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

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
                FlyingNew.backgroundmusic.start();
                FlyingNew.gamepaused=false;
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

                fly = new FlyingNew(Leftright.this);
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

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplication());
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("score", String.valueOf(FlyingNew.maxscore));
        editor.apply();
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // v.vibrate(500);
        dialog.show();

    }



    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        sm.unregisterListener(this);
        soundvalue = FlyingNew.backgroundmusic.getCurrentPosition();
        FlyingNew.backgroundmusic.stop();

    }

    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
        }

        FlyingNew.backgroundmusic.seekTo(soundvalue);
        FlyingNew.backgroundmusic.start();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
        }
        FlyingNew.backgroundmusic.seekTo(soundvalue);
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
        dialog.setContentView(R.layout.newcontinue);
        dialog.show();
        TextView distance,highscore;
        Button continue1;
        distance =(TextView)dialog.findViewById(R.id.continuedistance);
        highscore =(TextView)dialog.findViewById(R.id.continuenewhighscore);
        continue1 =(Button)dialog.findViewById(R.id.continueyes);


        distance.setText("DISTANCE : " + FlyingNew.score+"m");

        continue1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if(Menu.soundb==1) clicksound.start();
                Intent menu = new Intent(c,MenuNew.class);
                c.startActivity(menu);
            }
        });

    Vibrator v = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);



        if(FlyingNew.score>=FlyingNew.maxscore){
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(c);
            SharedPreferences.Editor editor = prefs.edit();
            highscore.setVisibility(1);
            editor.putString("score", String.valueOf(FlyingNew.score));
            editor.commit();

        }
    }
}
