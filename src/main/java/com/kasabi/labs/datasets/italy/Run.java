package com.kasabi.labs.datasets.italy;

import java.io.IOException;

public class Run {

	public static void main(String[] args) throws IOException {
		EurostatItaly.extract_italy_nuts_codes() ; // generates eurostat-nuts2008-italy.ttl
		GeonamesItaly.generate_italy_vocabulary(); // generates kasabi-italy-1.1.ttl
		IsoItaly.main(new String[]{}); // add ISO codes to kasabi-italy-1.1.ttl
	}

}
