package com.kasabi.labs.datasets.italy;

import com.kasabi.labs.datasets.Constants;

public class ItalySchemagen {

	public static void main(String[] args) {
		jena.schemagen.main(new String[]{
				"-i", Constants.DATA_ITALY_PATH + "italy-vocabulary.ttl", 
				"-n", "ITALY", 
				"--package", "com.kasabi.labs.vocabularies", 
				"-o", "src/main/java/",
				"--nocomments",			
				"--rdfs", 
			}) ;
		jena.schemagen.main(new String[]{
				"-i", Constants.VOCABULARIES_PATH + "geonames-ontology-2.2.1.ttl", 
				"-n", "GEONAMES", 
				"--package", "com.kasabi.labs.vocabularies", 
				"-o", "src/main/java/",
				"--nocomments",			
				"--owl", 
			}) ;
		jena.schemagen.main(new String[]{
				"-i", Constants.VOCABULARIES_PATH + "eurostat-vocabulary-0.6.ttl", 
				"-n", "EUROSTAT", 
				"--package", "com.kasabi.labs.vocabularies", 
				"-o", "src/main/java/",
				"--nocomments",			
				"--rdfs", 
			}) ;
	}

}
