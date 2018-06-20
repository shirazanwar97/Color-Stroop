package com.example.colorstrooop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;
import java.util.*;

public class Game extends Activity implements OnClickListener{
	String isHighScore="false";
	
	TextView stage,leftrod,rightrod,tvColorname;
	ImageView right,wrong;
	Animation drop;
	private int durationtime=5500;
	public int level=1;
	int feed,feedcolor;
	public static String fileName = "MySharedString";
	SharedPreferences someData,sharedHighscore;
	Intent i;
	Thread t;
	MediaPlayer gamemusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game);

		setallid();
	 	setPage();
	}
	
	public void setallid(){
		tvColorname=(TextView)findViewById(R.id.tvColorname);
		stage=(TextView)findViewById(R.id.tvstage);
		leftrod=(TextView)findViewById(R.id.tvrodleft);
		rightrod=(TextView)findViewById(R.id.tvrodright);
		right=(ImageView)findViewById(R.id.ivright);
		wrong=(ImageView)findViewById(R.id.ivwrong);
		
		right.setOnClickListener(this);
		wrong.setOnClickListener(this);
		
		gamemusic=MediaPlayer.create(Game.this,R.raw.gamemusicgame);
		gamemusic.start();
		gamemusic.setLooping(true);
		
		someData = getSharedPreferences(fileName,0);

		drop=AnimationUtils.loadAnimation(Game.this,R.anim.blockdrop);
	}
	
	public void setPage(){
		
		String tvStage = "Stage" + Integer.toString(level);
		stage.setText(tvStage);
		pick();
		animatebox();
		t = new Thread(){
			public void run(){
				
				try {
					sleep(durationtime);
					i  = new Intent(Game.this,ShowResult.class);
				//Setting up the Score high		
					if(checkHighscore()){
						isHighScore="true";
						String Data = Integer.toString(level);
					 	SharedPreferences.Editor editor = someData.edit();
						editor.putString("HighScore",Data);
						editor.commit();
					}
					
				//sending Score		
						String score = Integer.toString(level);
						 Bundle basket = new Bundle();
						 basket.putString("score",score);
						 basket.putString("isHighScore",isHighScore);
						 i.putExtras(basket);
						startActivity(i);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t.start();
		
	}
	
	public void animatebox()
	{	
		drop.setDuration(durationtime);
		leftrod.startAnimation(drop);
		rightrod.startAnimation(drop);
		
	}
	
	public void setValues(){
		level++;
		if(durationtime>2000){
			durationtime = durationtime - 80;
		}else if(durationtime>400 && durationtime<=2000){
			durationtime = durationtime - 50;
		}
	}
	
	 public void pick()
	{ 
		 //{"RED","BLUE","YELLOW","GREEN","ORANGE","PINK"};
		 Random num=new Random();
		 feed=num.nextInt(9);
		 feedcolor=num.nextInt(6);
		
		switch(feed)
		{
		case 0:
			tvColorname.setText("RED");
			break;
		case 1:
			tvColorname.setText("BLUE");
		    break;
		case 2:
			tvColorname.setText("YELLOW");
			break;
		case 3:
			tvColorname.setText("GREEN");
			break;
		case 4:
			tvColorname.setText("ORANGE");
			break;
		case 5:
			tvColorname.setText("PINK");
			break;
		case 6:
			feed = feedcolor = 0;
			tvColorname.setText("RED");
			break;
		case 7:
			feed = feedcolor = 1;
			tvColorname.setText("BLUE");
			break;
		case 8:
			feed = feedcolor = 2;
			tvColorname.setText("YELLOW");
			break;
		
		}
		
		
		switch(feedcolor)
		{
		case 0:
			tvColorname.setTextColor(Color.parseColor("#FF0000"));
			leftrod.setBackgroundColor(Color.parseColor("#FF0000"));
			rightrod.setBackgroundColor(Color.parseColor("#FF0000"));
			break;
			
		case 1:
			tvColorname.setTextColor(Color.parseColor("#0000FF"));
			leftrod.setBackgroundColor(Color.parseColor("#0000FF"));
			rightrod.setBackgroundColor(Color.parseColor("#0000FF"));
		    break;
		    
		case 2:
			tvColorname.setTextColor(Color.parseColor("#FFFF00"));
			leftrod.setBackgroundColor(Color.parseColor("#FFFF00"));
			rightrod.setBackgroundColor(Color.parseColor("#FFFF00"));
			break;
			
		case 3:
			tvColorname.setTextColor(Color.parseColor("#06FF00"));
			leftrod.setBackgroundColor(Color.parseColor("#06FF00"));
			rightrod.setBackgroundColor(Color.parseColor("#06FF00"));
			break;
			
		case 4:
			tvColorname.setTextColor(Color.parseColor("#FF9E21"));
			leftrod.setBackgroundColor(Color.parseColor("#FF9E21"));
			rightrod.setBackgroundColor(Color.parseColor("#FF9E21"));
			break;
			
		case 5:
			tvColorname.setTextColor(Color.parseColor("#FF0072"));
			leftrod.setBackgroundColor(Color.parseColor("#FF0072"));
			rightrod.setBackgroundColor(Color.parseColor("#FF0072"));
			break;
			
		}
			
		
		
	}
	
	 public boolean right(){
		 if(feed==feedcolor){
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	 public boolean wrong(){
		 if(feed!=feedcolor){
			 return true;
		 }else{
			 return false;
		 }
	 }
	 
	 public boolean checkHighscore(){
		 sharedHighscore = getSharedPreferences(fileName,0);
		String HighScore = someData.getString("HighScore", "0");
		int HighINT = Integer.parseInt(HighScore);
		 if(HighINT<level){
			 return true;
		 }else{
			 return false;
		 }
	 }
	
	 @Override
	public void onClick(View v) {
		 i  = new Intent(Game.this,ShowResult.class);
		switch(v.getId())
		{
		case R.id.ivright:
			t.interrupt();
			
			if(right()){
				
				setValues();
				setPage();
			}else{
		//Setting up the Score high		
				if(checkHighscore()){
					isHighScore="true";
					String Data = Integer.toString(level);
				 	SharedPreferences.Editor editor = someData.edit();
					editor.putString("HighScore",Data);
					editor.commit();
				}
				
		//sending Score		
				String score = Integer.toString(level);
				 Bundle basket = new Bundle();
				 basket.putString("score",score);
				 basket.putString("isHighScore",isHighScore);
				 i.putExtras(basket);
				startActivity(i);
			}
			
			break;

		case R.id.ivwrong:
			t.interrupt();
			if(wrong()){
				setValues();
				setPage();
			}else{
		//Setting up the Scopre high
				if(checkHighscore()){
					isHighScore="true";
					String Data = Integer.toString((level));
				 	SharedPreferences.Editor editor = someData.edit();
					editor.putString("HighScore",Data);
					editor.commit();
				}
		//sending Score		
				String score = Integer.toString(level);
				 Bundle basket = new Bundle();
				 basket.putString("isHighScore",isHighScore);
				 basket.putString("score",score);
				 i.putExtras(basket);
				startActivity(i);
			}
			
			break;
		}
	}

	
	 @Override
	protected void onPause() {
		super.onPause();
		gamemusic.stop();
		finish();
	}
	
	
}
		
	
		
	
