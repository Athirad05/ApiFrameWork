package org.resources;

import java.util.ArrayList;
import java.util.List;

import org.pojo.Location;
import org.pojo.Place;

public class TestData {
	
	public Place payload(String name,String language,String address) {
		
		Place p = new Place();
		p.setAccuracy(80);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number("9876545678");
		p.setWebsite("www.google.com");
		List<String>mylist = new ArrayList<String>();
		mylist.add("mango");
		mylist.add("apple");
		mylist.add("orange");
		p.setTypes(mylist);
		Location l = new Location();
		l.setLat(34.1234);
		l.setLng(54.876);
		p.setLocation(l);
		
		return p;
		

	}
	
	public String deletPayload(String place_id) {

		return "{\"place_id\":\""+place_id+"\"}";
		
		

	}

}
