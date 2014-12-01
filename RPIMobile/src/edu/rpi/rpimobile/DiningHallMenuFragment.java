/**
 * Filename: DiningHallMenuFragment.java
 * Author: Jacob Abramson
 * Date: 10/20/2014
 * Description: DiningHallMenuFragment class creates the ListView
 *              from which the user selects from all of
 *              the RPI Dining Halls to be
 *              taken to their corresponding food menu.
 */

package edu.rpi.rpimobile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;

import edu.rpi.rpimobile.model.DiningHall;
import edu.rpi.rpimobile.model.FoodItem;


public class DiningHallMenuFragment extends SherlockListFragment
/** Class used to implement the TV Guide feature */
{
	public static ArrayList<DiningHall> diningHalls;
	public static ArrayList<DiningHall> diningHallObjects;
	private ArrayAdapter<DiningHall> adapter;
	
	// URL to get sodexo menu JSON
	private static String url = "http://m.uploadedit.com/b042/1416705987325.txt"; // FOR TEST PURPOSES
	
	// JSON tag names
	private static final String TAG_MENU = "menu";
	private static final String TAG_STATION = "station";
	private static final String TAG_DAYOFWEEK = "dayOfWeek";
	private static final String TAG_MEAL = "meal";
	private static final String TAG_ATTRS = "attributes";
	private static final String TAG_NAME = "name";
	private static final String TAG_DININGHALL = "diningHall";
	
	public DiningHallMenuFragment()
	{
		diningHallObjects = new ArrayList<DiningHall>();
		diningHalls = new ArrayList<DiningHall>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	/** Upon being selected by the user, this method is called to create the ListView */
	{
		
		View rootView = inflater.inflate(R.layout.dininghallmenu_fragment, container, false);
		setHasOptionsMenu(true); // Options Menu is the "three-dots" button
		
		if (diningHalls.size() == 0) {
		
			// parse json for relevant data
			//grabData();
			
			DiningHall dh1 = new DiningHall("Commons");
			DiningHall dh2 = new DiningHall("Sage");
			DiningHall dh3 = new DiningHall("BARH");
			DiningHall dh4 = new DiningHall("Blitman");
			diningHalls.add(dh1);diningHalls.add(dh2);diningHalls.add(dh3);diningHalls.add(dh4);
		
		}
		
		// sort dining halls alphabetically
		Collections.sort(diningHalls, new Comparator<DiningHall>() {
			@Override
			public int compare(DiningHall d1, DiningHall d2) {
				return d1.getName().compareTo(d2.getName());
			}
		});
		
		
		adapter = new ArrayAdapter<DiningHall>(getSherlockActivity(), R.layout.dininghallmenu_list_item, diningHalls);
		setListAdapter(adapter);
		
		return rootView;
	}
	
	private void grabData() {
		// Creating service hander class instance
		ServiceHandler sh = new ServiceHandler();
		
		// allow this to run on main thread
		ThreadPolicy tp = ThreadPolicy.LAX;
		StrictMode.setThreadPolicy(tp);
		
		String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
		
		if (jsonStr != null) {
			try {
				//grab jsonarray
				JSONArray arr = new JSONArray(jsonStr);
				
				// loop thru all dining halls
				for (int i = 0; i < arr.length(); i++) {
					
					// grab individual dining hall
					JSONObject c = arr.getJSONObject(i);
					String diningHall = c.getString(TAG_DININGHALL);
					
					// construct dining hall object
					DiningHall dh = new DiningHall(diningHall);
					
					diningHalls.add(dh);
					
					// Menu node is JSON Object
					JSONArray menu = c.getJSONArray(TAG_MENU);
					for (int x = 0; x < menu.length(); x++) {
						JSONObject d = menu.getJSONObject(x);
						
						// create FoodItem
						FoodItem fi = new FoodItem();
						
						// grab all attributes of FoodItem
						JSONArray attrs = d.getJSONArray(TAG_ATTRS);
						for (int j = 0; j < attrs.length(); j++) {
							fi.addAttribute(attrs.getString(j));
						}
						
						// grab other properties of food item
						fi.setDayOfWeek(d.getString(TAG_DAYOFWEEK));
						fi.setMealTime(d.getString(TAG_MEAL));
						fi.setName(d.getString(TAG_NAME));
						fi.setStation(d.getString(TAG_STATION));
						
						// add FoodItem to dining hall
						dh.addFoodItem(fi);
						
					}
					
					// add dining hall to dining hall list
					diningHallObjects.add(dh);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any data from the url");
		}
	}
	
	@Override
	public void onListItemClick(ListView lv, View v, int pos, long id) {
		
		// Grab ListView item clicked
		String message = lv.getItemAtPosition(pos).toString();
		
		// Parse the name of the dining hall
		String dinHallName = grabName(message);
		
		DiningHallItemsFragment dhi = new DiningHallItemsFragment();
		dhi.setDiningHallParameters(dinHallName);
		FragmentTransaction ft = getSherlockActivity().getSupportFragmentManager().beginTransaction();
		ft.addToBackStack(null);
		ft.replace(R.id.content_frame, dhi);
		ft.commit();
		
	}
	
	// Grabbing the item from onListItemClick returns ex. "{id=0, diningHall="BARH"}"
	// We only want to pass the diningHall name value as an attr to the new fragment
	// Thus, we parse it
	private static String grabName(String str) {
		String[] tokens = str.split(",|\\=|\\}|\\{"); // split using delimeters: , and = and { and }
		return tokens[tokens.length - 1];
	}
}