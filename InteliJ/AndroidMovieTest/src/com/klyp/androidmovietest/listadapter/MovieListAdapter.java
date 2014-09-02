package com.klyp.androidmovietest.listadapter;

import com.klyp.androidmovietest.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MovieListAdapter extends ArrayAdapter<String> {
	private final Context context;
	private String[] names;
	private String[] dates;
	private String[] runtimes;
	
	
	public MovieListAdapter(Context context, String[] movieNames, String[] movieDates, String[] movieRuntimes) {
			super(context, R.layout.item_movie, movieNames);
		this.context = context;
		this.names=movieNames;
		this.dates=movieDates;
		this.runtimes=movieRuntimes;		
	}
	
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  //get an inflator
		  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  
		  //select a checkable or non checkable list item
		  View rowView ;
		  rowView = inflater.inflate(R.layout.item_movie, parent, false);
		  
		  //set values for name and image view
		  TextView titleTextView = (TextView) rowView.findViewById(R.id.text_movie_title);
		  TextView dateTextView = (TextView) rowView.findViewById(R.id.text_movie_date);
		  TextView runtimeTextView = (TextView) rowView.findViewById(R.id.text_movie_runtime);

		  titleTextView.setText(names[position]);
		  dateTextView.setText(dates[position]);
		  runtimeTextView.setText(runtimes[position]);

		  return rowView;
	  }
}
