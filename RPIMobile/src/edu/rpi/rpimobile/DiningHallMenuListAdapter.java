/**
 * Filename: DiningHallMenuListAdapter.java
 * Author: Jacob Abramson
 * Date: 10/20/2014
 * Description: DiningHallMenuListAdapter class draws the ListView
 *              from the DiningHallMenuFragment class.
 */

/*package edu.rpi.rpimobile;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.rpi.rpimobile.model.DiningHall;

public class DiningHallMenuListAdapter extends BaseAdapter
{
	private Context context;
	private ArrayList<DiningHall> diningHalls;
	private LayoutInflater inflater;
	
	public DiningHallMenuListAdapter(Context context_, ArrayList<DiningHall> diningHalls_)
	{
		this.context = context_;
		this.diningHalls = diningHalls_;
	}

	@Override
	public int getCount()
	{
		return diningHalls.size();
	}

	@Override
	public Object getItem(int index)
	{
		return diningHalls.get(index);
	}

	@Override
	public long getItemId(int index)
	{
		return index;
	}

	@Override
	public View getView(final int index, View convertView, ViewGroup parent)
	{
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.dininghallmenu_list_item, parent, false);
		
		TextView dhDinHallName = (TextView) itemView.findViewById(R.id.name);
		
		//TextView tvNetworkName = (TextView) itemView.findViewById(R.id.networkName);
		//TextView tvChannelNum = (TextView) itemView.findViewById(R.id.channelNumber);
		//tvNetworkName.setText(diningHalls.get(index).getNetworkName());
		//tvChannelNum.setText(diningHalls.get(index).getNumber());
		
		itemView.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				//context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(diningHalls.get(index).getNetworkURL())));
				// Grab ListView item clicked
				//String message = v.getItemAtPosition(pos).toString();
				
				// Parse the name of the dining hall
				String dinHallName = diningHalls.get(index).getName();//grabName(message);
				
				//Toast.makeText(getSherlockActivity(), "rofl", Toast.LENGTH_LONG).show();
				Log.d("wow:", "rofl");
				
				// bundle the dining hall name along with fragment transition
				Bundle args = new Bundle();
				args.putString("dinHall", dinHallName);
				DiningHallItemsFragment fragment2 = new DiningHallItemsFragment();
				FragmentManager fragmentManager = .getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				Fragment fragment = Fragment.instantiate(getActivity(), DiningHallMenuFragment.class.getName(), args);
				ft.replace(R.layout.dininghallmenu_fragment, fragment);
				//ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
			
		});
		return itemView;
	}
}*/