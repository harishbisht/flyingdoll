package example.example.com.flyingdoll;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

/**
 * Created by Amandeep Kaur on 2/1/2017.
 */
public class MenuNew extends Activity implements Button.OnClickListener{

    Button btnPlay,btnSetting,btnAbout,btnExit;
    static int soundb,musicb;
    MediaPlayer clicksound,music;
    int soundvalue;
    Dialog dialog;
    static int menumaxscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Screen Settings
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.menu);
        initControls();

    }
    private void initControls(){
        btnPlay=(Button)findViewById(R.id.button1);
        btnSetting=(Button)findViewById(R.id.button2);
        btnAbout=(Button)findViewById(R.id.button3);
        btnExit=(Button)findViewById(R.id.button4);
        btnPlay.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        //Sound and Music settings
        clicksound = MediaPlayer.create(getBaseContext(), R.raw.click);
        music = MediaPlayer.create(getBaseContext(), R.raw.b);
        music.setVolume(1.0f, 1.0f);
        music.setLooping(true);
        //Save default values of sound,music and maxscore in sharedprefrences
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        musicb=Integer.parseInt((mSharedPreference.getString("music", "1")));
        if(musicb==1)music.start();
        soundb=Integer.parseInt((mSharedPreference.getString("sound", "1")));
        menumaxscore=Integer.parseInt((mSharedPreference.getString("score", "0")));


    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button1){
            openplay(v);
        }else if(v.getId()==R.id.button2){
            opensetting(v);
        }else if(v.getId()==R.id.button3){
            openabout(v);
        }else if(v.getId()==R.id.button4){
            openexit(v);
        }
    }

    public void openplay(View view) {
        if(soundb==1) clicksound.start();
        Intent i = new Intent(this,Leftright.class );
        startActivity(i);
    }
    public void opensetting(View view) {
        if(soundb==1)   clicksound.start();
        if(musicb==1) music.start();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
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
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MenuNew.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("sound", String.valueOf("1"));
                    editor.commit();
                    soundb=1;clicksound.start();
                }
                else
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MenuNew.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("sound", String.valueOf("0"));
                    editor.commit();soundb=0;
                }
            }
        });

        m.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(m.isChecked())
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MenuNew.this);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("music", String.valueOf("1"));
                    editor.commit();
                    musicb=1;
                    music.start();
                }
                else
                {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MenuNew.this);
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
        dialog.setContentView(R.layout.about);
        dialog.show();
    }
    public void openexit(View view) {
        if(soundb==1)	clicksound.start();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.exit);
        exitByBackKey();
        dialog.show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        soundvalue=music.getCurrentPosition();
        music.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(musicb==1)
            music.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        music.seekTo(0);
        if(musicb==1)	music.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode== 4) {
            dialog = new Dialog(MenuNew.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.exit);
            exitByBackKey();

            dialog.show();}
        //


        return super.onKeyDown(keyCode, event);
    }
    public void exitByBackKey(){
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

}
