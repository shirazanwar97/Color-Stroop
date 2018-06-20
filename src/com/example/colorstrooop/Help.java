package com.example.colorstrooop;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Help extends Activity  {
	
	ImageView ivhome;
	MediaPlayer opensong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		
		opensong=MediaPlayer.create(Help.this,R.raw.gamemusic);
		opensong.start();
		
		ivhome=(ImageView)findViewById(R.id.ivhome);
		ivhome.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				Intent i=new Intent(Help.this,OpenActivity.class);
				startActivity(i);
				 				
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		opensong.stop();
		finish();
	}
	
	
	

}
