package com.thumbtack.githubexplorer;

import java.util.List;

import org.json.JSONArray;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.content.Context;

public class VolleyClient {
	private static String EVENT_URL = "https://api.github.com/users/ttandroidtest/events";
	private final Context context;
	private final RequestQueue requestQueue;
	
	public VolleyClient(Context context) {
		this.context = context;
		requestQueue = Volley.newRequestQueue(context);
	}
	
	public void getEvents(Listener<JSONArray> listener, ErrorListener errorListener) {
		JsonArrayRequest request = new JsonArrayRequest(EVENT_URL, listener, errorListener);
		requestQueue.add(request);
	}

}
