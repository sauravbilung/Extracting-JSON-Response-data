package com.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.Test.Utilities.DataExtractorForGetRequest;
import com.Test.Utilities.GetRequest;

public class GetApp {

	public static void main(String[] args) throws IOException {

		DataExtractorForGetRequest extractor = new DataExtractorForGetRequest();

		// Loading property file
		InputStream input = new FileInputStream(args[0]);
		Properties prop = new Properties();
		prop.load(input);

		// Get Request
		GetRequest getRequest = new GetRequest();
		getRequest.setRequestUrlForGET(prop.getProperty("getRequest"));
		String result = getRequest.getData();
		System.out.println(result);

		// Extracting Data
		extractor.setJsonObject(result);
		extractor.setOutputLocation(prop.getProperty("outputLocation"));
		extractor.setOutputFileName(prop.getProperty("outputFileName"));
		System.out.println("Output location:" + prop.getProperty("outputLocation") + prop.getProperty("outputFileName"));
		extractor.extractData();

		System.out.println("Done !!!");
	}
}
