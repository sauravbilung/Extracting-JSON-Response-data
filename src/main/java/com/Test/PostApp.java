package com.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.Test.Utilities.DataExtractorForPostRequest;
import com.Test.Utilities.PostRequest;

public class PostApp {

	public static void main(String[] args) throws IOException {

		DataExtractorForPostRequest extractor = new DataExtractorForPostRequest();

		// Loading property file
		InputStream input = new FileInputStream(args[0]);
		Properties prop = new Properties();
		prop.load(input);

		// Post Request
		PostRequest postRequest = new PostRequest();
		postRequest.setRequestUrlForPOST(prop.getProperty("postRequest"));
		String postString = "{\"name\":\"test\",\"salary\":\"123\",\"age\":\"23\"}";
		postRequest.setRequestBodyValue(postString);
		String result = postRequest.getData();
		System.out.println("result");

		// Extracting Data
		extractor.setJsonObject(result);
		extractor.setOutputLocation(prop.getProperty("outputLocation"));
		extractor.setOutputFileName(prop.getProperty("outputFileName"));
		System.out.println("Output location:" + prop.getProperty("outputLocation") + prop.getProperty("outputFileName"));
		extractor.extractData();

		System.out.println("Done !!!");
		
	}

}
