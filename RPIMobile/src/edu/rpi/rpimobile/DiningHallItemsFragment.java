/**
 * Filename: DiningHallItemsFragment.java
 * Author: Jacob Abramson
 * Date: 10/21/2014
 * Description: DiningHallItemsFragment class creates the ListView
 *              from which the user views all the
 *              food options corresponding to the
 *           	dining hall selected in the DiningHallMenuFragment class
 */
/*
package edu.rpi.rpimobile;


import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;


public class DiningHallItemsFragment extends SherlockFragment

{
	// JSON tag names for food item attributes
	private static final String TAG_STATION = "station";
	private static final String TAG_DAYOFWEEK = "dayOfWeek";
	private static final String TAG_MEAL = "meal";
	private static final String TAG_ATTRS = "attributes";
	private static final String TAG_NAME = "name";
	
	// Hashmap for ListView
	public ArrayList<HashMap<String, String>> menuItem;
	
	public DiningHallItemsFragment()
	{
		menuItem = new ArrayList<HashMap<String, String>>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	Upon being selected by the user, this method is called to create the ListView
	{
		View rootView = inflater.inflate(R.layout.dininghallitems_fragment, container, false);
		setHasOptionsMenu(true); // Options Menu is the "three-dots" button
		
		new BackGround().execute(getArguments.getString("dinHall"));
		
		generateMenuItems(efawfew);
		
		return rootView;
	}
	
}*/