package com.klyp.androidmovietest;

import com.klyp.androidmovietest.model.MovieData;
import com.klyp.androidmovietest.listadapter.MovieListAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class MainActivityController extends Activity {
	MovieData  moviedata;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		moviedata = new MovieData();
		

	    final ListView listview = (ListView) findViewById(R.id.list_movies);
	    
		new Thread() {
			public void run() {
				
				while(moviedata.getTitles()==null);
					Log.d("APP",""+moviedata.getTitles().length);
				runOnUiThread(new Runnable(){

					@Override
					public void run() {
						final MovieListAdapter adapter = new MovieListAdapter(MainActivityController.this, moviedata.getTitles(), moviedata.getDates(), moviedata.getRuntimes());
						listview.setAdapter(adapter);
					}
					
				});
				
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_controller, menu);
		return true;
	}

}
