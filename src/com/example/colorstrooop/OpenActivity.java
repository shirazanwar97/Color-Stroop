package com.example.colorstrooop;





import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;


public class OpenActivity extends Activity implements OnClickListener {
TextView start;
ImageView hlp,abt,ivColorStroop;
Animation rotateCircle;
TextView tvhighscore;
public static String fileName = "MySharedString";
SharedPreferences someData;
MediaPlayer opensong;
Intent i;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.openactivity);
		setAllId();
		sethighscore();
		
	}
	public void setAllId()
	{
		abt=(ImageView)findViewById(R.id.baboutus);
		hlp=(ImageView)findViewById(R.id.bhelp);
		tvhighscore=(TextView)findViewById(R.id.tvhighscore);
		start=(TextView)findViewById(R.id.tvStart);
		ivColorStroop = (ImageView)findViewById(R.id.ivColorStroop);
		
		opensong=MediaPlayer.create(OpenActivity.this,R.raw.gamemusic);
		opensong.start();
		
		rotateCircle = AnimationUtils.loadAnimation(OpenActivity.this, R.anim.rotate_circle);
		ivColorStroop.startAnimation(rotateCircle);
		
		abt.setOnClickListener(this);
		hlp.setOnClickListener(this);
		start.setOnClickListener(this);

		
	}
	
	public void sethighscore(){
		someData = getSharedPreferences(fileName,0);
		String DataReturn = someData.getString("HighScore", "0");
		tvhighscore.setText(DataReturn);
	}
	@Override
	public void onClick(View v) {
		
		switch(v.getId()){
		case R.id.tvStart:
			i=new Intent(OpenActivity.this,Game.class);
			startActivity(i);	
			break;
			
		case R.id.bhelp:
			i=new Intent(OpenActivity.this,Help.class);
			startActivity(i);	
			break;
			
		case R.id.baboutus:
			i=new Intent(OpenActivity.this,Aboutus.class);
			startActivity(i);	
			break;
			
		}
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		opensong.stop();
		}
	
	
	
}
		
		
		
	