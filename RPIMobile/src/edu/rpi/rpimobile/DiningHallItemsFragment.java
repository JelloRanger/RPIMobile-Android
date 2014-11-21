/**
 * Filename: DiningHallItemsFragment.java
 * Author: Jacob Abramson
 * Date: 10/21/2014
 * Description: DiningHallItemsFragment class creates the ListView
 *              from which the user views all the
 *              food options corresponding to the
 *           	dining hall selected in the DiningHallMenuFragment class
 */

package edu.rpi.rpimobile;


import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockListFragment;

import edu.rpi.rpimobile.model.DiningHall;
import edu.rpi.rpimobile.model.FoodItem;


public class DiningHallItemsFragment extends SherlockListFragment {
	
	private SherlockFragmentActivity mActivity;
	
	private ArrayList<String> foodItems;
	private ArrayAdapter<String> adapter;
	
	//private SherlockFragmentActivity mActivity;
	private String name; // dining hall name
	
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
		foodItems = new ArrayList<String>();
	}
	
	public void setDiningHallParameters(String n) {
		name = n;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	//Upon being selected by the user, this method is called to create the ListView
	{
		//super.onCreateView(inflater, container, savedInstanceState);
		//View rootView = inflater.inflate(R.layout.dininghallitems_fragment, container, false);
		View rootView = inflater.inflate(R.layout.dininghallitems_second, container, false);
		
		Log.d("awef","FIOAEFJIOFJAIOEWJFIOAWFJWEOI");
		
		setHasOptionsMenu(true); // Options Menu is the "three-dots" button
		
		//new BackGround().execute(getArguments.getString("dinHall"));
		
		generateMenuItems(name);
		
		adapter = new ArrayAdapter<String>(getSherlockActivity(), R.layout.dininghallitems_fragment, R.id.name, foodItems);
		setListAdapter(adapter);
		
		return rootView;
	}
	
	private void generateMenuItems(String dinHall) {
		
		// grab list of dining hall objects
		//ArrayList<DiningHall> dh = DiningHallMenuFragment.diningHallList;
		ArrayList<DiningHall> dh = DiningHallMenuFragment.diningHallObjects;
		
		Log.d("awef","elbaro");
		// grab correct dining hall
		DiningHall hall = null;
		for (int i = 0; i < dh.size(); i++) {
			if (dh.get(i).getName().equals(dinHall)) {
				hall = dh.get(i);
				break;
			}
		}
		
		// loop through meals of dining hall
		for (int i = 0; i < hall.getMeals().size(); i++) {
			
			FoodItem fi = hall.getMeals().get(i);
			HashMap<String, String> menu = new HashMap<String, String>();
			menu.put("id", String.valueOf(0));
			menu.put(TAG_NAME, fi.getName());
			menu.put(TAG_DAYOFWEEK, fi.getDayOfWeek());
			menu.put(TAG_MEAL, fi.getMealTime());
			menu.put(TAG_STATION, fi.getStation());
			menuItem.add(menu);
			
			//********************************************************************************************
			foodItems.add(fi.getName());
			Log.d("awef","jacobsdefijfe");
			//********************************************************************************************
		}
		
		// display dining halls in ListView
		/*ListView listView = (ListView) getActivity().findViewById(R.id.dininghallfoodlist);
		ListAdapter adapter = new SimpleAdapter(getActivity(), menuItem, R.layout.dininghallmenu_list_item, 
				new String[] { TAG_NAME, TAG_DAYOFWEEK, TAG_MEAL, TAG_STATION }, 
				new int[] { R.id.name, R.id.dayOfWeek, R.id.meal, R.id.station});
		listView.setAdapter(adapter);*/
	}
	
}