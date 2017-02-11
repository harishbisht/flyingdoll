package example.example.com.flyingdoll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.firebase.crash.FirebaseCrash;

import java.io.FileOutputStream;

/**
 * Created by Amandeep Kaur on 1/27/2017.
 */
public class Menu extends Activity {
    static FileOutputStream fOut;
    static int soundb,musicb;

    Button play, about, exit, setting;
    MediaPlayer clicksound,music;
    int soundvalue;
    Dialog dialog;
    static int menumaxscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //	play = (Button) findViewById(R.id.button1);
        clicksound = MediaPlayer.create(getBaseContext(), R.raw.click2);
        music = MediaPlayer.create(getBaseContext(), R.raw.b);
        music.setVolume(1.0f, 1.0f);
        music.setLooping(true);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        musicb=Integer.parseInt((mSharedPreference.getString("music", "1")));
        if(musicb==1)music.start();
        soundb=Integer.parseInt((mSharedPreference.getString("sound", "1")));
        menumaxscore=Integer.parseInt((mSharedPreference.getString("score", "0")));
        //	about = (Button) findViewById(R.id.button3);
        //	setting = (Button) findViewById(R.id.button2);
        //	exit = (Button) findViewById(R.id.button4);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
   //     FirebaseCrash.report(new Exception("My first Android non-fatal error"));

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        //music.release();

        soundvalue=music.getCurrentPosition();
        music.pause();
    }

    @Override
    protected void onRestart() {
// TODO Auto-generated method stub
        super.onRestart();

        music.seekTo(0);
        if(musicb==1)	music.start();
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode== 4) {
            dialog = new Dialog(Menu.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.exit);
            exitByBackKey();

            dialog.show();}
        //


        return super.onKeyDown(keyCode, event);
    }
/*	public void exityespressed(View view)
	{
		clicksound.start();

		System.exit(0);


	}
  public void exitnopressed(View view)
  {clicksound.start();
	 dialog.dismiss();

	  //dialog
  }   */

	/*public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK ){
	        exitByBackKey();
		  return true;
		}

		return super.onKeyUp(keyCode, event);
	}
	*/
    public void exitByBackKey(){
        // TODO Auto-generated method stub
        //super.onBackPressed();

        //	final Dialog dialog = new Dialog(this);
        //	dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // //////////////////
        //	Window window = dialog.getWindow();
        //window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
        ///		WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        //dialog.setContentView(R.layout.pause);
        //	dialog.show();

        Button exityes,exitno;
        exityes=(Button)dialog.findViewById(R.id.exityes);
        exitno=(Button)dialog.findViewById(R.id.exitno);
        exityes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (soundb == 1) clicksound.start();
                clicksound.start();
                System.exit(0);
            }
        });
        exitno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (soundb == 1)
                    clicksound.start();

                dialog.dismiss();
            }
        });
    }
    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        music.seekTo(soundvalue);
        if(musicb==1)
            music.start();
        //setprefencesdata();

        //	getpreferencesdata();
    }

    public void openplay(View view) {
        Log.e("Menu","inside open play");
        if(soundb==1) clicksound.start();
        Intent i1 = new Intent(Menu.this,Leftright.class );   //("android.intent.action.Leftright");
        Menu.this.startActivity(i1);
    }
    public void opensetting(View view) {
        if(soundb==1)   clicksound.start();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // //////////////////
        //	Window window = dialog.getWindow();
        //window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
        ///		WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        dialog.setContentView(R.layout.setting);

        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        soundb=Integer.parseInt((mSharedPreference.getString("sound", "1")));
        musicb=Integer.parseInt((mSharedPreference.getString("music", "1")));

        final ToggleButton s;
        final ToggleButton m;
        final Button reset,close ;
        reset=(Button)dialog.findViewById(R.id.clearhighscore);
        s=(ToggleButton)dialog.findViewById(R.id.soundtoggle);
        m=(ToggleButton)dialog.findViewById(R.id.musictoggle);
        close=(Button)dialog.findViewById(R.id.closebutton);
        if (soundb==1)
            s.setChecked(true);
        else
            s.setChecked(false);

        if(musicb==1)
            m.setChecked(true);
        else
            m.setChecked(false);

        if(Integer.parseInt((mSharedPreference.getString("score", "0"))) >=1)
            reset.setEnabled(true);
        else
            reset.setEnabled(false);
        ///////////////////////////////////****************************///////////////////////////////
        s.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(s.isChecked())
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Menu.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("sound", String.valueOf("1"));
                    editor.commit();
                    soundb=1;clicksound.start();
                }
                else
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Menu.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("sound", String.valueOf("0"));
                    editor.commit();soundb=0;
                    //	if(soundb==1)

                }


            }
        });

        m.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(m.isChecked())
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Menu.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("music", String.valueOf("1"));
                    editor.commit();
                    musicb=1;
                    music.start();
                }
                else
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Menu.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("music", String.valueOf("0"));
                    editor.commit();musicb=0;
                    music.pause();
                }
                if(soundb==1)
                    clicksound.start();

            }
        });


        reset.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplication());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("score", String.valueOf(0));
                editor.apply();
                editor.commit();
                Flying.maxscore=0;
                Flying.score=0;
                menumaxscore=0;

                if(soundb==1&&reset.isEnabled())
                    clicksound.start();
                reset.setEnabled(false);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(soundb==1) clicksound.start();dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void openabout(View view) {
        if(soundb==1)	clicksound.start();
        final Dialog dialog = new Dialog(this);

        //tell the Dialog to use the dialog.xml as it's layout description

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // //////////////////
        //Window window = dialog.getWindow();
        //	window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
        //		WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        dialog.setContentView(R.layout.about);
        dialog.show();
    }

    public void openexit(View view) {
        if(soundb==1)	clicksound.start();
        ///System.exit(0);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.exit);
        exitByBackKey();
        dialog.show();

    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy(); finish();
    }




    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();finish();
    }
    @SuppressLint("NewApi")
    public void	setprefencesdata()
    {
        //SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        //editor.putInt("text" ,123);
        editor.putString("score", "123456");
        editor.apply();
    }

    public void getpreferencesdata()
    {

        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        menumaxscore=Integer.parseInt((mSharedPreference.getString("score", "0")));
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        //v.vibrate(500);//}
    }

}
