/**
 * Filename: DiningHall.java
 * Author: Jacob Abramson
 * Date: 10/20/2014
 * Description: DiningHall is a simple class that
 *              holds necessary data for each element
 *              in the ListView created by DiningHallMenuListFragment.
 */

package edu.rpi.rpimobile.model;

import java.util.ArrayList;

public final class DiningHall
{
	/**
	 * name: name of dining hall
	 * meals: list of meals served in given week
	 * week: current week of menu (format is MMDDYY, so April 3 2014 would be 040314)
	 */
	private String name;
	private ArrayList<FoodItem> meals;
	private String week;
	
	// constructor
	public DiningHall(String n) {
		name = n;
		meals = new ArrayList<FoodItem>();
	}
	
	// add foodItem
	public void addFoodItem(FoodItem fi) {
		meals.add(fi);
	}
	
	// GETTERS
	public String getName() {
		return name;
	}
	
	public ArrayList<FoodItem> getMeals() {
		return meals;
	}
	
	public String getWeek() {
		return week;
	}
	
	// SETTERS
	public void setName(String n) {
		name = n;
	}
	
	public void setMeals(ArrayList<FoodItem> m) {
		meals = m;
	}
	
	public void setWeek(String w) {
		week = w;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
