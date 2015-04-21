package com.thumbtack.githubexplorer;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class EventListAdapter extends ArrayAdapter<Event> {
	List<Event> data;
	
	public EventListAdapter(Context context, List<Event> events) {
		super(context, IGNORE_ITEM_VIEW_TYPE);
		data = events;
	}
	
	
}
