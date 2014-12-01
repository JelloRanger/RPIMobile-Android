package edu.rpi.rpimobile;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
/*import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;*/

import edu.rpi.rpimobile.model.FoodItem;

// parse a specific dining halls menu items according to provided attributes
public class GrabDiningHall {

	// attributes used to select food items
	private String diningHallURL; 	// link to rpi sodexo menu page
	private String dayOfWeek; 	  	// day of week meal is being served
	private String mealTime; 		// meal time meal is being served (breakfast, lunch, dinner)
	private String station; 		// station meal is being served at
	private String attr; 			// vegetarian/vegan/mindful item
	
	// list of food items that meet the above attributes' criteria
	private ArrayList<FoodItem> foodItems;
	
	
	
	// tester main function
	/*public static void main(String args[]) throws IOException {
		GrabDiningHall gdh = new GrabDiningHall(null, "thursday", "brk", null, "vegetarian");
		
		
		gdh.determineDiningHall("sage");
		ArrayList<FoodItem> listthing = gdh.parsePage();
		
		for (FoodItem fi : listthing) {
			System.out.println(fi.getName());
		}
	}*/
	
	// determine dining hall url
	public boolean determineDiningHall(String dinHallName) throws IOException {
		
		// initialize document for parsing
		Document doc;
		
		// grab correct dining hall index page
		switch (dinHallName.toLowerCase()) {
			case "sage":
				doc = Jsoup.connect("https://rpi.sodexomyway.com/dining-choices/res/sage.html").userAgent("Mozilla/5.0").timeout(30000).get();
				break;
			case "commons":
				doc = Jsoup.connect("https://rpi.sodexomyway.com/dining-choices/res/index.html").userAgent("Mozilla/5.0").timeout(30000).get();
				break;
			case "blitman":
				doc = Jsoup.connect("https://rpi.sodexomyway.com/dining-choices/res/bitman.html").userAgent("Mozilla/5.0").timeout(30000).get();
				break;
			case "barh":
				doc = Jsoup.connect("https://rpi.sodexomyway.com/dining-choices/res/BARH.html").userAgent("Mozilla/5.0").timeout(30000).get();
				break;
			default:
				return false;
		}
		
		// parse URL to the specific dining hall's menu page
		Element link = doc.select("div[id^=accordion] a[href][target]").first();

		if (link == null) {
			return false;
		}
		
		this.diningHallURL = "https://rpi.sodexomyway.com" + link.attr("href");
		
		return true;
	}
	
	// constructor given all attributes (dining hall, day of week, meal, station, vegan/vegetarian/mindful item)
	public GrabDiningHall(String diningHallURL, String dayOfWeek, String mealTime, String station, String attr) { 
		this.diningHallURL = diningHallURL;
		this.dayOfWeek = dayOfWeek;
		this.station = station;
		this.attr = attr;
		
		if (mealTime != null) {
			if (mealTime.equalsIgnoreCase("breakfast")) this.mealTime = "brk";
			else if (mealTime.equalsIgnoreCase("lunch")) this.mealTime = "lun";
			else if (mealTime.equalsIgnoreCase("dinner")) this.mealTime = "din";
			else this.mealTime = mealTime;
		}
		else this.mealTime = mealTime;
		
		foodItems = new ArrayList<FoodItem>();
		
		// allow this to run on main thread
				ThreadPolicy tp = ThreadPolicy.LAX;
				StrictMode.setThreadPolicy(tp);
	}
	
	// parse the provided URL given the provided attributes
	public ArrayList<FoodItem> parsePage() throws IOException {
		
		// clear foodItems before parsing
		foodItems.clear();
		
		// connect to the dining hall menu webpage
		Document doc = Jsoup.connect(diningHallURL).userAgent("Mozilla/5.0").timeout(30000).get();
		
		Elements meals = doc.select("*");
		
		// select based on dayOfWeek
		if (dayOfWeek != null) {
			meals = meals.select("td[id=" + dayOfWeek + "]");
		}
		
		// select based on meal time
		if (mealTime != null) {
			meals = meals.select("tr[class=" + mealTime + "]");
		}
		
		
		// select based on attr
		/*if (attr != null) {
			meals = meals.select("tr img[alt=" + attr + "]");
		}*/
		
		
		// grab meal names
		meals = meals.select("div[class=menuitem]");
		
		// select based on station
		//todo
		
		
		for (Element meal : meals) {
			FoodItem fi = new FoodItem();
			fi.setName(meal.text());
			foodItems.add(fi);
		}
		
		return foodItems;
	}
	
}
