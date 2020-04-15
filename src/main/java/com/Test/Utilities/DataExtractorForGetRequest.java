package com.Test.Utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.Test.model.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataExtractorForGetRequest {

	private JSONObject jsonObject;
	private JSONArray jsonArray;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Employee employee;
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

		jsonArray = jsonObject.getJSONArray("data");

		for (int i = 0; i < jsonArray.length(); i++) {

			employee = objectMapper.readValue(jsonArray.get(i).toString(), Employee.class);
			extractedData += employee.toString();
		}

		writeData(extractedData);
	}

	public void writeData(String data) throws IOException {

		BufferedWriter br = new BufferedWriter(new FileWriter(outputLocation + outputFileName, true));
		br.append(data);
		br.close();
	}

}
