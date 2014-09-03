package com.example.striker.androidmovietest;

import com.example.striker.androidmovietest.modal.MovieData;
import com.example.striker.androidmovietest.adapers.MovieListAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.ListView;


public class MainActivity extends Activity {

    MovieData  moviedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadMovieList();
    }

    public void loadMovieList()
    {
        moviedata = new MovieData();


        final ListView listview = (ListView) findViewById(R.id.list_movies);

        new Thread() {
            public void run() {

                while(moviedata.getTitles()==null);
                Log.d("APP",""+moviedata.getTitles().length);
                runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                        final MovieListAdapter adapter = new MovieListAdapter(MainActivity.this, moviedata.getTitles(), moviedata.getDates(), moviedata.getRuntimes());
                        listview.setAdapter(adapter);
                    }

                });

            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
