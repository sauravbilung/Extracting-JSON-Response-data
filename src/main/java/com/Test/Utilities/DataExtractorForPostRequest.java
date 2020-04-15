package com.Test.Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.Test.model.PostResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataExtractorForPostRequest {

	private JSONObject jsonObject;
	private ObjectMapper objectMapper = new ObjectMapper();
	private PostResponse postResponse;
	private String extractedData=""; 
	private String outputLocation;
	private String outputFileName;

	public void setJsonObject(String source) {
		this.jsonObject = new JSONObject(source);
	}

	public void setOutputLocation(String outputLocation) {
		this.outputLocation = outputLocation;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public void extractData() throws JsonParseException, JsonMappingException, JSONException, IOException {

		JSONObject data=jsonObject.getJSONObject("data");
		postResponse = objectMapper.readValue(data.toString(),PostResponse.class);
		extractedData += postResponse.toString();		

		writeData(extractedData);
	}

	public void writeData(String data) throws IOException {

		BufferedWriter br = new BufferedWriter(new FileWriter(outputLocation + outputFileName, true));
		br.append(data);
		br.close();
	}

}
