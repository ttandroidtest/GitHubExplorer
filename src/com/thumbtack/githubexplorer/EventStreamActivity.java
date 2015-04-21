package com.thumbtack.githubexplorer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.thumbtack.githubexplorer.Event.Builder;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class EventStreamActivity extends Activity implements Listener<JSONArray>, ErrorListener {
	private static String TAG = EventStreamActivity.class.getSimpleName();
    VolleyClient volleyClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event_stream);
		volleyClient = new VolleyClient(this);		
		volleyClient.getEvents(this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.event_stream, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return false;
	}

	@Override
	public void onErrorResponse(VolleyError error) {
		Log.e(TAG, error.getMessage());		
	}

	@Override
	public void onResponse(JSONArray response) {
		List<Event> events = new ArrayList();
		
		for (int i = 0; i < response.length(); i++) {
			try {
				JSONObject item = (JSONObject) response.get(i);
				String type = item.getString("type");
				String repoName = item.getJSONObject("repo").getString("name");
				Event event =  new Event(type, repoName);
				events.add(event);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		showListFragment(events);
	}

	private void showListFragment(List<Event> events) {
		EventListFragment listFragment = new EventListFragment();
		listFragment.setListAdapter(new EventListAdapter(events));
		
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.add(listFragment, EventListFragment.TAG);
		transaction.addToBackStack(null);
		transaction.commit();
	}
}
