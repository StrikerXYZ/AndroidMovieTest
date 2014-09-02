package com.klyp.androidmovietest.model;

import android.util.Log;

import com.klyp.androidmovietest.global.GlobalValues;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieData {
	int numMovies=0;
	String[] movieName;
	String[] movieDate;
	String[] movieRuntime;
	
	public MovieData()
	{
		new Thread() {
			public void run() {
				String data=retrieveData();
				parseJSON(data);
			}
		}.start();
		
	}
	
	
	public String retrieveData()
	{
		InputStream inputStream = null;
		String result = null;
		
		try {
		    
			HttpClient httpclient = new DefaultHttpClient();

            HttpGet request = new HttpGet();
            URI website = new URI("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="+GlobalValues.APIKey_RottenTomatoes+"&q="+GlobalValues.MovieSearchQuery);
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    
		    /////////////////////////////////////////////////////////////SKIP EMPTY LINES
		    for(int i=0;i<100;i++)
		    {
		    	line = reader.readLine();
		    	if(line!=null)
		    		break;
		    		
		    }
		    /////////////////////////////////////////////////////////////////////////////////
		    
		    while ((line = reader.readLine()) != null)
		    {
		        sb.append(line + "\n");
		    }
		    result = sb.toString();
		    
		} catch (Exception e) { 
		    // Oops
		}
		finally {
		    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
		}

		return result;
	}

	private void parseJSON(String data) {
		try {
			
			JSONObject jObject = new JSONObject(data);
			numMovies =  jObject.getInt("total");
			
			//initialize movie data
			movieName=new String[numMovies];
			movieDate=new String[numMovies];
			movieRuntime=new String[numMovies];
			
			
			JSONArray jMoviesArray = jObject.getJSONArray("movies");
			
			for (int i=0; i < numMovies; i++)
			{
			    try {
			        JSONObject jMovieItem = jMoviesArray.getJSONObject(i);
			        // Pulling items from the array
			        movieName[i] = jMovieItem.getString("title");
			        movieDate[i] = jMovieItem.getString("year");
			        movieRuntime[i] = jMovieItem.getString("runtime");
			    } catch (JSONException e) {
			        // Oops
			    }
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String[] getTitles()
	{
		if(numMovies==0)//null check
			return null;
		
		return movieName;
	}

	public String[] getDates()
	{
		if(numMovies==0)//null check
			return null;
		
		return movieDate;
	}

	public String[] getRuntimes()
	{
		if(numMovies==0)//null check
			return null;
		
		return movieRuntime;
	}
}