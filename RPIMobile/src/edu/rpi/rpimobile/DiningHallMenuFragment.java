/**
 * Filename: DiningHallMenu.java
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
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.actionbarsherlock.app.SherlockFragment;

import edu.rpi.rpimobile.model.DiningHall;
import edu.rpi.rpimobile.model.FoodItem;


public class DiningHallMenuFragment extends SherlockFragment
/** Class used to implement the TV Guide feature */
{
	private ArrayList<DiningHall> diningHalls;
	private DiningHallMenuListAdapter listadapter;
	
	public static ArrayList<DiningHall> diningHallList;
	// URL to get sodexo menu JSON
	private static String url = "http://m.uploadedit.com/b041/1413670784424.txt"; // FOR TEST PURPOSES
	
	// used to show progress of loading menu data
	private ProgressDialog pDialog;
	
	// JSON tag names
	private static final String TAG_MENU = "menu";
	private static final String TAG_STATION = "station";
	private static final String TAG_DAYOFWEEK = "dayOfWeek";
	private static final String TAG_MEAL = "meal";
	private static final String TAG_ATTRS = "attributes";
	private static final String TAG_NAME = "name";
	private static final String TAG_DININGHALL = "diningHall";
	
	private int numberthing = 0; // necessary for id in hashmap...will look at removing it in future
	
	// Hashmap for ListView
	ArrayList<HashMap<String, String>> dinHallItem;
	
	public DiningHallMenuFragment()
	{
		diningHalls = new ArrayList<DiningHall>();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	/** Upon being selected by the user, this method is called to create the ListView */
	{
		View rootView = inflater.inflate(R.layout.dininghallmenu_fragment, container, false);
		setHasOptionsMenu(true); // Options Menu is the "three-dots" button
		
		dinHallItem = new ArrayList<HashMap<String, String>>();
		
		// Retrieve list of dining halls
		if (diningHalls.size() == 0) {
			new GetHalls().execute(); // call async task to get json
		}
		
		ListView channelsList = (ListView) rootView.findViewById(R.id.dininghalllist);
		listadapter = new DiningHallMenuListAdapter(this.getSherlockActivity(), diningHalls);
		channelsList.setAdapter(listadapter);
		
		return rootView;
	}
	
	protected void onListItemClick(ListView lv, View v, int pos, long id) {
		
		/*// Grab ListView item clicked
		String message = lv.getItemAtPosition(pos).toString();
		
		// Parse the name of the dining hall
		String dinHallName = grabName(message);
		
		//DiningHallItemsFragment diningHallItemsFragment = new DiningHallItemsFragment();
		Fragment detail = DiningHallItemsFragment.newInstance(dinHallName);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		//fragmentTransaction.replace(R.layout.dininghallmenu_fragment, diningHallItemsFragment, dinHallName);
		fragmentTransaction.add(R.layout.dininghallmenu_fragment, detail).addToBackStack("back");
		fragmentTransaction.commit();*/
		
	}
	
	// Grabbing the item from onListItemClick returns ex. "{id=0, diningHall="BARH"}"
	// We only want to pass the diningHall name value as an attr to the new fragment
	// Thus, we parse it
	private static String grabName(String str) {
		String[] tokens = str.split(",|\\=|\\}|\\{"); // split using delimeters: , and = and { and }
		return tokens[tokens.length - 1];
	}
	
	/*
	 * Async task class to grab JSON by making HTTP call
	 */
	private class GetHalls extends AsyncTask<Void, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			// Creating service hander class instance
			ServiceHandler sh = new ServiceHandler();
			
			String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);
			
			diningHallList = new ArrayList<DiningHall>();
			
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
						diningHallList.add(dh);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				//generate list of dining halls
				generateDiningHallListView(diningHallList);
			} else {
				Log.e("ServiceHandler", "Couldn't get any data from the url");
			}
			
			return null;
		}
		
		// add dining halls into dining hall list view
		protected void generateDiningHallListView(ArrayList<DiningHall> diningHallList) {
			
			// sort list of dining halls by name
			Collections.sort(diningHallList, new Comparator<DiningHall>() {
				@Override
				public int compare(final DiningHall obj1, final DiningHall obj2) {
					return obj1.getName().compareTo(obj2.getName());
				}
			});
			
			// loop through all dining halls
			for (int i = 0; i < diningHallList.size(); i++) {
				
				// generate tmp hashmap for listview of dining halls
				HashMap<String, String> hall = new HashMap<String, String>();
				hall.put("id", String.valueOf(numberthing));
				numberthing++;
				hall.put(TAG_DININGHALL, diningHallList.get(i).getName());
				dinHallItem.add(hall);
			}
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			// Dismiss the progress dialog
			if (pDialog.isShowing()) {
				pDialog.dismiss();
			}
			
			/*
			 * Updating parsed JSON data into ListView
			 */
			
			// display dining halls in ListView
			ListView listView = (ListView) getActivity().findViewById(R.id.dininghalllist);
			ListAdapter adapter = new SimpleAdapter(getActivity(), dinHallItem, R.layout.dininghallmenu_list_item, 
					new String[] { TAG_DININGHALL }, new int[] { R.id.name });
			listView.setAdapter(adapter);
		}
	}
	
	/*public static class DiningHallItemsFragment extends SherlockFragment {
		public static DiningHallItemsFragment newInstance(String dinHallName) {
			
			DiningHallItemsFragment diningHallItemsFragment = new DiningHallItemsFragment();
			Bundle bundle = new Bundle();
			bundle.putString("dinHall", dinHallName);
			diningHallItemsFragment.setArguments(bundle);
			return diningHallItemsFragment;
		}
	}*/
	
}