package de.bugisoft.r6tracker.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import de.bugisoft.r6tracker.api.R6Tracker;

public class TrackerTest {
	
	public static void main(String[] args) {
		R6Tracker tracker = R6Tracker.getInstance();
		
		JsonObject data = tracker.getData("OP-KillerYT");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(data));
		
		System.out.println("Name: " + data.get("name").getAsString());
		System.out.println("Premium: " + data.get("isPremium").getAsBoolean());
		System.out.println("Lifetime KD: " + data.get("lifetimeStats").getAsJsonObject().get("kd").getAsDouble());
		System.out.println("Rank: " + data.get("currentSeasonBestRegion").getAsJsonObject().get("rankName").getAsString());
	}

}
