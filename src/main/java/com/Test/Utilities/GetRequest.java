package com.Test.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequest {

	// Request URL
	private String requestUrlForGET;

	public void setRequestUrlForGET(String requestUrlForGET) {
		this.requestUrlForGET = requestUrlForGET;
	}

	public String getData() throws IOException {

		// Setting up connection
		URL url = new URL(requestUrlForGET);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// Setting up request headers
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		connection.setRequestProperty("charset", "utf-8");
		connection.setDoOutput(true);

		// Reading response
		String finalResponse = null;
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			br.close();
			finalResponse = response.toString();
		}

		connection.disconnect();
		return finalResponse;

	}
}
