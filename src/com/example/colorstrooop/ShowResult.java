package com.example.colorstrooop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class ShowResult extends Activity implements OnClickListener {

	TextView tvcompliment,tvhighscore,tvscore;
	ImageView ivreplay,ivhome;
	Intent i;
	public static String fileName = "MySharedString";
	SharedPreferences someData;
	String HighScore;
	LinearLayout background;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showresult);
		
		setAllId();
		setScoreandPage();
		sethighscore();
		
	}
	
	public void setAllId(){
		tvcompliment = (TextView)findViewById(R.id.tvcompliment);
		tvhighscore = (TextView)findViewById(R.id.tvhighscore);
		tvscore = (TextView)findViewById(R.id.tvscore);
		ivreplay = (ImageView)findViewById(R.id.ivreplay);
		ivhome = (ImageView)findViewById(R.id.ivhome);
		background=(LinearLayout)findViewById(R.id.background);
		//background.setBackgroundResource(R.drawable.congo);
		
		ivreplay.setOnClickListener(this);
		ivhome.setOnClickListener(this);
	}

	public void sethighscore(){
		someData = getSharedPreferences(fileName,0);
		HighScore = someData.getString("HighScore", "0");
		String h = "HIGHSCORE : "+ HighScore;
		tvhighscore.setText(h);
	}
	
	public void setScoreandPage(){
		Bundle person = getIntent().getExtras();
		String score = person.getString("score");
		String isHighScore=person.getString("isHighScore");
		if(isHighScore.equals("true")){
			background.setBackgroundResource(R.drawable.congo);
			tvcompliment.setText("CONGRATULATIONS");
			tvcompliment.setTextColor(Color.parseColor("#000000"));
			tvscore.setTextColor(Color.parseColor("#000000"));
			tvhighscore.setTextColor(Color.parseColor("#000000"));
		}
		tvscore.setText(score);
	}
	
	
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.ivhome:
				i=new Intent(ShowResult.this,OpenActivity.class);
				startActivity(i);
				break;
				
			case R.id.ivreplay:
				i=new Intent(ShowResult.this,Game.class);
				startActivity(i);
				break;	
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	

}
