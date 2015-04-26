package com.flyingdoll.harish;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
import android.view.View;




public class Flying extends View  {

	
	
	/*@Override
	protected int[] onCreateDrawableState(int extraSpace) {

		
		
		return super.onCreateDrawableState(extraSpace);
	}*/

	//SensorManager sm;
static	boolean gamepaused;
	SoundPool sp;
static	MediaPlayer backgroundmusic;
public static int score,maxscore;
	int temp;
	Random ran = new Random();
	int newplatformheight=0;
	int width, height;
	Canvas c;
	int howmanycircles;
	Paint mypaint;
	Bitmap image1,image2,image3,image4;
int dollflag=0;
boolean isjumping;
boolean isfalling,colloid;
static boolean gameover;
int colloidvalue;
int jumpspeed,fallspeed;
int jumpspeedvalue;
public static int imagex;
public static int canvaswidth;
int platformmovingspeed;
int imagey;
int noofplatforms;
int totalplatforms;
int platformdistance;
int platformthickness;
int onetimeexecutingcodeinsidecanvas=1;
Rect r = new Rect();



Paint myPaint = new Paint();
Paint rectgreen = new Paint();
Paint scorePaint = new Paint();
Paint scoretext = new Paint();
//	Platform[] plat= new Platform[7];
int[][] platform= new int[9][7];

int platform_temp_i=0,platform_temp_j=0;
Paint p = new Paint();

int collisionmusic;
int[][] snow= new int[30][5];
int maxnoofsnow=30;
int maxcounterofsnow;
//Random r = new Random();
int snowcounter =0;
	public Flying(Context context) {
		super(context);
		maxscore=Menu.menumaxscore;
		
	//	maxscore=Menu.settingvalues[0];
		  
		
	/*	// TODO Auto-generated constructor stub
		width=20;
		height=20;
		mypaint =new Paint();
		mypaint.setColor(Color.WHITE);
		mypaint.setStrokeWidth(10);
		mypaint.setTextSize(20);
		howmanycircles=10;
		mypaint= new Paint();
	//	mypaint.setARGB(255, 0,0, 0);
        mypaint.setColor(Color.WHITE);
*/
		
		//Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
		
		////////////////snow/////////////
		maxcounterofsnow=maxnoofsnow;
		p.setAntiAlias(true);
		p.setColor(Color.WHITE);
		p.setStyle(Paint.Style.FILL); 
		p.setStrokeWidth(4.5f);
		////////////endofsnow///////////////////////
	/////////////////musixc////////////////////////////////
		backgroundmusic = MediaPlayer.create(getContext(), R.raw.a);
		backgroundmusic.setLooping(true);
		gamepaused=false;
		//backgroundmusic.
		//backgroundmusic.seekTo(45000);
	
		backgroundmusic.setVolume(200, 200);
		if(Menu.musicb==1)
	    backgroundmusic.start();
		sp=new SoundPool(1,AudioManager.STREAM_MUSIC, 0);
		
		collisionmusic=sp.load(getContext(), R.raw.jump6, 1);
		
		
		
		image1 = BitmapFactory.decodeResource(getResources(), R.drawable.angel);
		//image2 = BitmapFactory.decodeResource(getResources(), R.drawable.angel1);
	//	jumpspeedvalue=23;
	//	jumpspeed=jumpspeedvalue;
		fallspeed=0;
		isjumping= true;
		isfalling=false;
		colloid=true;
		gameover=false;
		score=0;
		imagex=250;imagey=600;
		score=0;
		platformmovingspeed=5;
		totalplatforms=noofplatforms;
		
		
		//  
		
		
		
		}


	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
	
		super.onDraw(canvas);
		c=canvas;
		//newplatformheight=canvas.getHeight()/7;
		/**canvas.drawColor(Color.RED);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		canvas.drawColor(Color.BLUE);
		**/
	
		if(onetimeexecutingcodeinsidecanvas==1)
		{
			
			noofplatforms=totalplatforms=canvas.getHeight()/115;
			
			jumpspeedvalue=canvas.getHeight()/30;	
			jumpspeed=jumpspeedvalue;
			platformdistance=canvas.getHeight()/noofplatforms;
		////	platformthickness=canvas.getHeight()/23;
			imagey=canvas.getHeight()-100;
			onetimeexecutingcodeinsidecanvas=0;
		}
		
	/*	if(gamepaused)
		{
		 return;	 
		}
		*/ 
		canvas.restore();
		canvas.drawRGB(208, 231, 249);
		//canvas.drawRGB(0, 64,128);
		canvas.drawColor(Color.TRANSPARENT);
		/*canvas.drawText("harish", width, height, mypaint);
		//canvas.drawRGB(208, 230, 120);
		//R: 208 G: 230 B: 120
		width++; height++;  */
	//	canvas.drawRect(0, 0, canvas.getWidth(), canvas.getDensity(), null);
		
		//draw circles
		
		//for(int i=0;i<howmanycircles;i++)
		//{
		//	canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2,40 , mypaint);
	//	}
		
		//////////////////////////circle ends//////////////////////
		
	///drawing doll on screen//////////
		
   /*   if (dollflag==1){
		image2 = Bitmap.createBitmap(image1, 0, 0, image1.getWidth(), image1.getHeight()/2);
		
		dollflag=0;
      }else
      {
		image2 = Bitmap.createBitmap(image1, 0,image1.getHeight()/2 , image1.getWidth(), image1.getHeight()/2);
		
        dollflag=1;
      }
      canvas.drawBitmap(image2,  canvas.getWidth()/2-image2.getWidth()/2, canvas.getHeight()/2-image2.getHeight()/2, null);
    */ 
      //////jumping code
      
    /*  if(!isjumping && !isfalling)
      {
    	  fallspeed=0;
    	  isjumping=true;
    	  jumpspeed=17;
    	  
      }*/

      
      if(isjumping)
    	{  
    	  
    	     if (dollflag==1)
    	     {
    			image2 = Bitmap.createBitmap(image1, 0, 0, image1.getWidth(), image1.getHeight()/2);
    			canvaswidth=canvas.getWidth()-image2.getWidth();
    			dollflag=0;
    	      }else
    	      {
    			image2 = Bitmap.createBitmap(image1, 0,image1.getHeight()/2 , image1.getWidth(), image1.getHeight()/2);
    			canvaswidth=canvas.getWidth()-image2.getWidth();
    	        dollflag=1;
    	      }
    	     
    	    //   
    	  /* if(imagey<canvas.getHeight()*0.5){
    		   imagey= (int) ((int)canvas.getHeight()*0.5);
    	   }
           if(jumpspeed>10&&imagey>canvas.getHeight()*0.4)
           {
        	   for(int i=0;i<7;i++){
           	     platform[i][1]=platform[i][1]+jumpspeed; //for move downward
           	     platform[i][3]=platform[i][3]+jumpspeed; // for move downward
           	       }
           }
    	    */
    	     /*  if(imagey<canvas.getHeight()*0.4)
    	       {   imagey=(int) (canvas.getHeight()*0.4);
    	    	   for(int i=0;i<7;i++){
    	           	     platform[i][1]=platform[i][1];//+jumpspeed; //for move downward
    	           	     platform[i][3]=platform[i][3];//+jumpspeed; // for move downward
    	           	       }
    	       }
    	       canvas.drawBitmap(image2,  imagex, imagey-jumpspeed, null);
     	      imagey=imagey-jumpspeed;
     	       jumpspeed--; 
          */
    	     if(imagey>canvas.getHeight()*0.4){
    	    	 canvas.drawBitmap(image2,  imagex, imagey-jumpspeed, null);
    	    	 imagey=imagey-jumpspeed;
    	     }
    	     else for(int i=0;i<totalplatforms;i++){
           	     platform[i][1]=platform[i][1]+jumpspeed; //for move downward
           	     platform[i][3]=platform[i][3]+jumpspeed; // for move downward
           	     
           	  if(platform[i][1]>=canvas.getHeight()){
          		temp= platform[i][0]= temp=ran.nextInt(canvas.getWidth()-100);
          		int temp2=platform[i][1];
          		// platform[i][1]= canvas.getHeight()-platform[i][1];
          		platform[i][1]= platform[i][1]-canvas.getHeight();
          		 platform[i][2]=temp+100;
          		// platform[i][3]=canvas.getHeight()-temp2+30;
          		platform[i][3]=platform[i][1] +30;
          		platform[i][4]=0;
          		 //canvas.drawRect(platform[i][0]+platform[i][4], platform[i][1], platform[i][2]+platform[i][4], platform[i][3], myPaint);
          	   if(score>1000)
          		platform[i][5]=ran.nextInt(10);
          	   
          	   if(score>50000)
          	   {int tempi=ran.nextInt(2);
          	      if(score>50000&&score<53000)
          	       if(tempi==1)
          		   platform[i][4]=-(ran.nextInt(4)+1);
	               else 
	            		platform[i][4]=ran.nextInt(4)+1;
          	       
          	    if(score>53000&&score<57000)
           	       if(tempi==1)
           		   platform[i][4]=-(ran.nextInt(6)+1);//7
 	               else 
 	            		platform[i][4]=ran.nextInt(6)+1;
          	    
          	  if(score>57000)
          	       if(tempi==1)
          		   platform[i][4]=-(ran.nextInt(9)+1);   //10
	               else 
	            		platform[i][4]=ran.nextInt(9)+1;   
         
          	      
          	   }
          	   else if(score>5000)
          	   {
          		   int tempi=ran.nextInt(20);
          		   if(tempi<8)
          		   {   	    tempi=ran.nextInt(score/200);  //ran.nextInt(30);
          		            if(tempi< score/600)
          		            {	tempi=ran.nextInt(2);
          		            	if(tempi==1)
          			            platform[i][4]=-(int)ran.nextInt(score/1000);
          		            	else
          		                platform[i][4]=(int)ran.nextInt(score/1000); }
          		            else
          		            	 platform[i][4]=0;//(int)ran.nextInt(15);
          		   }
          	   }
          	 }  
           	 score=score+1;    
           	     
    	     }
    	     canvas.drawBitmap(image2,  imagex, imagey, null);
    	  //   imagey=imagey-jumpspeed;
   	       jumpspeed--; 
   	       
   	       
   	       
    	    if(jumpspeed<=0)
    	    {
    	    	isjumping=false;
    	    	isfalling=true;
    	    	
    	    			
    	    	fallspeed=1;
    	    			
    	    }
    	}
        
      
      
      
      if(isfalling)
        {
    	  if(imagey<canvas.getHeight()-image2.getHeight())
    	  { 		  
    		     		  
    	     if (dollflag==1)
    	     {
  			  image2 = Bitmap.createBitmap(image1, 0, 0, image1.getWidth(), image1.getHeight()/2);
  		     	dollflag=0;
  	         }else
  	          {
  			  image2 = Bitmap.createBitmap(image1, 0,image1.getHeight()/2 , image1.getWidth(), image1.getHeight()/2);
  	           dollflag=1;
  	          }
    	     
    	     canvas.drawBitmap(image2,  imagex, imagey+fallspeed, null);
    	       imagey=imagey+fallspeed;
    	       fallspeed++;
    	     
    	  }
        ///////////for game over this else if  
    	  else if(imagey>canvas.getHeight()-image2.getHeight()&& score>60)
    	  {
    		  gameover=true;
    	  }
    	  
    	   else
    	   {
    		   
    		   isjumping=true;
    		   isfalling=false;
    		   
    		   fallspeed=0;
    		   jumpspeed=jumpspeedvalue;	   
    	   } 	 
        }
              
     if(imagex<=0)
      {
    	 imagex=0;
    	 
    	  //canvas.drawBitmap(image2, imagex, imagey+fallspeed, null);
      }
      if(imagex>canvas.getWidth()-image1.getWidth())
      {  
    	  imagex=canvas.getWidth()-image1.getWidth();
      }
      
      
      //platform and collision code
      
      
    // plat[0].setdata(200,200, 250, 250);
   //   plat[1].setdata(200, 200, 250, 250);
    
      //r.set(10, , 400, 400);
      
   //  myPaint.setColor(Color.BLUE);
       myPaint.setARGB(255 ,	238 ,	92 ,	66 	);
      myPaint.setStrokeWidth(10);
    //  rectgreen.setColor(Color.GREEN);
    
      rectgreen.setARGB(255,46,139,87);
      rectgreen.setStrokeWidth(10);
     // r.set(10, 10, 20, 20);
      //canvas.drawRect(r, null);
    // canvas.drawRect(plat[1].getx(), plat[1].gety() , plat[1].getx()+ plat[1].getwidth(),plat[1].gety()+plat[1].getheight(),myPaint); 
    // canvas.drawRect(200, 200 ,250,250,myPaint); 
      
      
     // myPaint.setColor(Color.BLACK);

      //myPaint.setStrokeWidth(3);
     //canvas.drawText(null, canvas.getHeight(), 500, 500, colloidvalue, null);
      myPaint.setTextSize(20);
     // myPaint.setColor(Color.RED);
    //  int as=canvas.getHeight();
      
     // canvas.drawText(String.valueOf(as) , 300, 300, myPaint);
      while(noofplatforms>0){
    	 
     temp=platform[platform_temp_i][platform_temp_j]= temp=ran.nextInt(canvas.getWidth()-100);
     platform_temp_j++;
     platform[platform_temp_i][platform_temp_j]= newplatformheight;
     platform_temp_j++;
     platform[platform_temp_i][platform_temp_j]=(int) (temp+canvas.getWidth()/4.8);
     platform_temp_j++;
     platform[platform_temp_i][platform_temp_j]=newplatformheight+30;
     platform[platform_temp_i][4]=0;
     platform[platform_temp_i][5]=0;
     platform[platform_temp_i][6]=0;
     //canvas.drawRect(temp, newplatformheight, temp+100, newplatformheight+40, myPaint);
     //newplatformheight=newplatformheight+100;
     newplatformheight=newplatformheight+platformdistance;;
     noofplatforms--;
     platform_temp_j=0;
     platform_temp_i++;
      }
     
     
     for(int i=0;i<totalplatforms;i++)
     { 
    	  if(platform[i][5]==1)
    	  canvas.drawRect(platform[i][0]+platform[i][4], platform[i][1], platform[i][2]+platform[i][4], platform[i][3], rectgreen);
    	  else
    	 canvas.drawRect(platform[i][0]+platform[i][4], platform[i][1], platform[i][2]+platform[i][4], platform[i][3], myPaint);
       platform[i][0]=platform[i][0]+platform[i][4];platform[i][2]=platform[i][2]+platform[i][4];
     
      // canvas.drawText(String.valueOf(platform[i][1]) , platform[i][2], platform[i][3]-40, myPaint);
    //	 canvas.drawText(String.valueOf(score) , 50, 75, myPaint);
  /*   } ////////////////////////////
     
   for(int i=0;i<6;i++)///////////////////////////////////////
   {  *////////////////////////////////////
    	//platform[i][1]=platform[i][1]+5; //for move downward
    	// platform[i][3]=platform[i][3]+5; // for move downward
    	 /// when platform reach the end then it genrates the new platform
    	/* if(platform[i][1]>=canvas.getHeight()){
    		temp= platform[i][0]= temp=ran.nextInt(canvas.getWidth()-100);
    		 platform[i][1]= 0;
    		 platform[i][2]=temp+100;
    		 platform[i][3]=0+30;
    		 //canvas.drawRect(platform[i][0]+platform[i][4], platform[i][1], platform[i][2]+platform[i][4], platform[i][3], myPaint);
    	
    	 }*/
	
    	
    	 ////****** for movving platform left and right
    	if(platform[i][0]>canvas.getWidth()-100)
    	 platform[i][4]=-platform[i][4];	 
    	 
    	 if(platform[i][0]<0 )
    	   platform[i][4]=-platform[i][4];
    	 
    	
    	 
    	 if((isfalling)&& (imagex < platform[i][2]) && 
   			  (imagex + image2.getWidth()> platform[i][0])    && 
   			  (imagey+ image2.getHeight()> platform[i][1]) && 
   			   (imagey + image2.getHeight()<platform[i][3] ))
   	 {
   		 isfalling=false;
   		 fallspeed=0;
   		 isjumping=true;
   		 if(platform[i][5]==1)
   			 jumpspeed=60;
   		 else
   		 jumpspeed=jumpspeedvalue;
   		 if(Menu.soundb==1)
   		 sp.play(collisionmusic, 1, 1, 0, 0, 2);
   		 
   	 
   	    	 
   	    }
    	   	 	
     }
///////////////****************************************************////////////////      
////////////////////////////////drawing snow //////////////////////////////////////
///////////////****************************************************////////////////
   while(maxcounterofsnow>0){
		int x = ran.nextInt(canvas.getWidth());
		int y = ran.nextInt(canvas.getHeight());
		int radius = ran.nextInt(8);
		canvas.drawCircle(x, y, radius, p);
		snow[snowcounter][0]=x;
		snow[snowcounter][1]=y;
		snow[snowcounter][2]=radius;
		snow[snowcounter][3]=ran.nextInt(5)+1;
		if(ran.nextInt(5)>3)
		snow[snowcounter][4]=ran.nextInt(3);
		else
			snow[snowcounter][4]=-ran.nextInt(3);
			snowcounter++;					
	maxcounterofsnow--;
    }
	
	for(int i=0;i<maxnoofsnow;i++){
		
		if (imagey>canvas.getHeight()*0.4)
		{	canvas.drawCircle(snow[i][0]+snow[i][4], snow[i][1]+snow[i][3], snow[i][2], p);
		snow[i][1]=snow[i][1]+snow[i][3];
		}
		else
		{	
		canvas.drawCircle(snow[i][0]+snow[i][4], snow[i][1]+snow[i][3]+jumpspeed, snow[i][2], p);
		snow[i][1]=snow[i][1]+snow[i][3]+jumpspeed;}
		
		snow[i][0]=snow[i][0]+snow[i][4];
	
		
		if(snow[i][1]>canvas.getHeight()|| snow[i][0]<0 || snow[i][0]>canvas.getWidth()) {
			snow[i][0]=ran.nextInt(canvas.getWidth());
			snow[i][1]=0;
	     	snow[i][2]=ran.nextInt(8);
	     	snow[i][3]=ran.nextInt(5)+1;
	     	if(ran.nextInt(5)>3) snow[i][4]=ran.nextInt(5);
	     	else
				snow[i][4]=-ran.nextInt(3);
	     	}
	}
	
 //  canvas.drawText(String.valueOf(backgroundmusic.getCurrentPosition()), 200, 200, myPaint);
//	canvas.drawText(String.valueOf(canvas.getWidth()), 200, 200, myPaint);
   
  // if(backgroundmusic.getCurrentPosition()>43500) //43500
	//   backgroundmusic.seekTo(900);
   if(backgroundmusic.getCurrentPosition()>54300) //43500
	  backgroundmusic.seekTo(11550);
   
 //  backgroundmusic.start();
   
   /////////////////score board////////////////////
 
	 scorePaint.setARGB(220 ,0 ,159 ,60);
	    scorePaint.setStrokeWidth(10);
	   scoretext.setARGB(255, 255, 255, 255);
	   scoretext.setStrokeWidth(10);
	scoretext.setTextSize(25);
	//scoretext.sett
   canvas.drawRect(0, 0, canvas.getWidth(),60, scorePaint);
   if(score>maxscore)	   
   canvas.drawText("DISTANCE : " + String.valueOf(score) + "m / "+score+"m", 20, 40, scoretext);
   else
	canvas.drawText("DISTANCE : " + String.valueOf(score) + "m / "+maxscore+"m", 20, 40, scoretext);
   
  // image3 = BitmapFactory.decodeResource(getResources(), R.drawable.pause1);
  // canvas.drawBitmap(image3,  canvas.getWidth()-60, 5, null);
   
   
   
  
   
   //////////////////////end of score board//////////////////////
     
		
      if(gameover)
      {   myPaint.setARGB(255, 0, 204, 102);
      myPaint.setTextSize(30);
          	 //canvas.drawColor(Color.WHITE);
    	 collisionmusic=sp.load(getContext(), R.raw.jump3, 1);
 		
          if(Menu.soundb==1)
  	      sp.play(collisionmusic, 1, 1, 0, 2, 1);
  	
    	 backgroundmusic.setLooping(false);
         backgroundmusic.seekTo(62000);  /////for a
       
      //  maxscore=score;
       
          Leftright.exitformcavas();
         
          
    	 canvas.drawText("GAME OVER ", canvas.getWidth()/2-image2.getWidth()/2, canvas.getHeight()/2-image2.getHeight()/2, myPaint);
    	canvas=null;
    	 return;
      }
      else  if(!gamepaused)
    	  invalidate();
      
      //if(!gamepaused)
      //invalidate();
		
		
      try {
  		Thread.sleep(20);
  	} catch (InterruptedException e) {
  		// TODO Auto-generated catch block
  		e.printStackTrace();
  	}
		
		
		
		  
	}
/*
public class Platform
{ 
	 int x;
	 int y;
	 int width;
	 int height;
	 
	// public platform(int a, int b, int c ,int d)
	 //{
	//	 x=a;y=b;width=c;height=d;
	 //}

	 
	 void setdata(int a, int b, int c ,int d)
	 {
		 x=a;y=b;width=c;height=d;
	 };
	 public int getx()
	 {return x;}
	 public int gety()
	 {return y;}
	 public int getwidth()
	 {
		return width;
		 }
	 public int getheight()
	 {
		 return height;
	 }
}
	*/


	
	
	
	
	
	
	
}


