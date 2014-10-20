/**
 * Filename: DiningHall.java
 * Author: Jacob Abramson
 * Date: 10/20/2014
 * Description: DiningHall is a simple class that
 *              holds necessary data for each element
 *              in the ListView created by DiningHallMenuListFragment.
 */

package edu.rpi.rpimobile.model;

public final class DiningHall
{
	private final String diningHall_name;
	//private final String network_number;
	//private final String network_url;
	
	public DiningHall(String diningHall/*, String number, String URL*/)
	{
		this.diningHall_name = diningHall;
		//this.network_number = number;
		//this.network_url = URL;
	}
	
	public String getDiningHallName() { return new String(this.diningHall_name); }
	//public String getNumber() { return new String(this.network_number); }
	//public String getNetworkURL() { return new String(this.network_url); }

}
