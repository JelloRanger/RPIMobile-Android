/**
 * Filename: DiningHallMenuListAdapter.java
 * Author: Jacob Abramson
 * Date: 10/20/2014
 * Description: DiningHallMenuListAdapter class draws the ListView
 *              from the DiningHallMenuFragment class.
 */

package edu.rpi.rpimobile;

import java.util.ArrayList;

import edu.rpi.rpimobile.model.DiningHall;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
				
			}
			
		});
		return itemView;
	}
}