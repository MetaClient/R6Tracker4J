package de.bugisoft.r6tracker.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class R6Tracker {
	
	private static R6Tracker api;
	
	private static final String PLAYER_URL = "https://r6.tracker.network/api/v0/overwolf/player?name=%s";
	
	public JsonObject getData(String name) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(String.format(PLAYER_URL, name)).openConnection();
			connection.addRequestProperty("Accept", "application/json");
			connection.setRequestMethod("GET");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Baguette) Chrome/86.0.4240.198 Safari/537.36 OPR/72.0.3815.459");
			InputStream inputStream = connection.getInputStream();
			
			String response = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines().collect(Collectors.joining());
			
			return JsonParser.parseString(response).getAsJsonObject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static R6Tracker getInstance() {
		return api;
	}
	
	static {
		api = new R6Tracker();
	}

}
