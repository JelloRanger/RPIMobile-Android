/**
 * Filename: DiningHallItemsListAdapter.java
 * Author: Jacob Abramson
 * Date: 11/22/2014
 * Description: DiningHallItemsListAdapter class draws the ListView
 *              from the DiningHallItemsFragment class.
 */

package edu.rpi.rpimobile;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.rpi.rpimobile.model.FoodItem;

public class DiningHallItemsListAdapter extends BaseAdapter {
	
	private Context context;
	private ArrayList<FoodItem> foodItems;
	private LayoutInflater inflater;
	
	public DiningHallItemsListAdapter(Context context_, ArrayList<FoodItem> foodItems_) {
		this.context = context_;
		this.foodItems = foodItems_;
	}

	@Override
	public int getCount() {
		return foodItems.size();
	}

	@Override
	public Object getItem(int position) {
		return foodItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.dininghallitems_list_item, parent, false);
		
		// grab the textviews
		TextView tvName = (TextView) itemView.findViewById(R.id.name);
		/*TextView tvDayOfWeek = (TextView) itemView.findViewById(R.id.dayOfWeek);
		TextView tvMeal = (TextView) itemView.findViewById(R.id.meal);
		TextView tvStation = (TextView) itemView.findViewById(R.id.station);*/
		
		// set the text properly for each textview
		tvName.setText(foodItems.get(position).getName());
		/*tvDayOfWeek.setText(foodItems.get(position).getDayOfWeek());
		tvMeal.setText(foodItems.get(position).getMealTime());
		tvStation.setText(foodItems.get(position).getStation());*/
		
		return itemView;
	}
	
}
