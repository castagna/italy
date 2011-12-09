package com.kasabi.labs.datasets.italy;

import java.io.IOException;

public class Run {

	public static final String ITALY_NAMESPACE = "http://data.kasabi.com/dataset/italy/" ;
	public static final String VERSION = "1.2" ;
	public static final String FILENAME_PREFIX = "kasabi-italy" ;
	public static final String FILENAME = FILENAME_PREFIX + "-" + VERSION + ".ttl" ;
	
	public static void main(String[] args) throws IOException {
		EurostatItaly.extract_italy_nuts_codes() ; // generates eurostat-nuts2008-italy.ttl
		GeonamesItaly.generate_italy_vocabulary(); // generates FILENAME_PREFIX-VERSION.ttl
		IsoItaly.main(new String[]{}); // add ISO codes to FILENAME_PREFIX-VERSION.ttl
	}

}
