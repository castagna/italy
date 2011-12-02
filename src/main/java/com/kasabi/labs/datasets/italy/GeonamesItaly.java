/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kasabi.labs.datasets.italy;

import static com.kasabi.labs.datasets.Constants.DATA_EUROSTAT_PATH;
import static com.kasabi.labs.datasets.Constants.DATA_GEONAMES_PATH;
import static com.kasabi.labs.datasets.Constants.DATA_ISO_3166_2_PATH;
import static com.kasabi.labs.datasets.Constants.QUERIES_PATH;
import static com.kasabi.labs.datasets.Constants.VOCABULARIES_PATH;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.sparql.util.Timer;
import com.kasabi.labs.datasets.Utils;
import com.kasabi.labs.vocabularies.GEONAMES;

public class GeonamesItaly {

	private static String filename_geonames = DATA_GEONAMES_PATH + "geonames-italy.rdf.txt";
	private static String filename_regions = DATA_GEONAMES_PATH + "geonames-italy-regions.ttl";
	private static String filename_provinces = DATA_GEONAMES_PATH + "geonames-italy-provinces.ttl";
	private static String filename_municipalities = DATA_GEONAMES_PATH + "geonames-italy-municipalities.ttl";
	
	private static final Logger log = LoggerFactory.getLogger(GeonamesItaly.class) ;
	
	// input data
	public static final File[] data = {
		new File(filename_regions),
		new File(filename_provinces),
		new File(filename_municipalities),
		new File(DATA_EUROSTAT_PATH + "eurostat-nuts2008-italy.ttl"),
		new File(DATA_ISO_3166_2_PATH + "iso-3166-2.ttl"),
	};
	
	// additional data to merge|add
	public static final File[] merge = {
		new File(DATA_GEONAMES_PATH + "geonames-italy-merge.ttl"),
	};

	// stuff to be removed from the generated dataset
	public static final File[] remove = {
		new File(DATA_GEONAMES_PATH + "geonames-italy-remove.ttl"),
	};
	
	public static void main(String[] args) throws IOException {
		// split() ;
		generate_italy_vocabulary() ;
	}

	public static void generate_italy_vocabulary() throws IOException {
		File query = new File(QUERIES_PATH + "geonames_italy_construct.sparql") ;
		Model result = Utils.construct(data, merge, remove, query) ;

		Timer timer = new Timer();
		timer.startTimer();
		File output = new File(VOCABULARIES_PATH, "kasabi-italy-v1.0.ttl"); 
		FileOutputStream out = new FileOutputStream (output);
		result.write(out, "TURTLE") ;
		out.flush();
		out.close();
		log.info("Generated {} in {} ms", output.getAbsolutePath(), timer.endTimer());
	}
	
	public static void count() {
		File query = new File(QUERIES_PATH + "geonames_italy_count.sparql") ;
		ResultSet results = Utils.select(data, query) ;
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			System.out.println(soln);
		}
	}
	
	public static void query() {
		File query = new File(QUERIES_PATH + "geonames_italy.sparql") ;
		ResultSet results = Utils.select(data, query) ;
		while (results.hasNext()) {
			QuerySolution soln = results.nextSolution();
			RDFNode region = soln.get("region") ;
			RDFNode province = soln.get("province") ;
			RDFNode comune = soln.get("comune") ;
			System.out.println(region + " » " + province + " » " + comune);
		}
	}
	
	public static String normalise(String name) {
		String result = name.toLowerCase() ;
		result = result.replaceAll("provincia autonoma della ", "") ;
		result = result.replaceAll("provincia di ", "") ;
		result = result.replaceAll("provincia ", "") ;
		result = result.replaceAll("province of ", "") ;
		result = result.replaceAll("regione ", "") ;
		result = result.replaceAll(" - ", "-") ;
		return result.replaceAll(" ", "_") ;
	}
	
	public static void split() throws IOException {
		FileOutputStream regions = new FileOutputStream(filename_regions);
		FileOutputStream provinces = new FileOutputStream(filename_provinces);
		FileOutputStream municipalities = new FileOutputStream(filename_municipalities);
		
		Model model_regions = ModelFactory.createDefaultModel();
		Model model_provinces = ModelFactory.createDefaultModel();
		Model model_municipalities = ModelFactory.createDefaultModel();
		
		BufferedReader in = new BufferedReader(new FileReader(filename_geonames));
		String str;
		int count = 0;
		while ((str = in.readLine()) != null) {
			count++;
			try {
				StringReader sr = new StringReader(str); 
				Model model = ModelFactory.createDefaultModel();
				model.read(sr, null);

				StmtIterator iter = model.listStatements(null, GEONAMES.featureCode, GEONAMES.A_ADM1) ;
				if ( iter.hasNext() ) {
					model_regions.setNsPrefixes(model.getNsPrefixMap());
					model_regions.add(model);
				} else {
					iter = model.listStatements(null, GEONAMES.featureCode, GEONAMES.A_ADM2) ;
					if ( iter.hasNext() ) {
						model_provinces.setNsPrefixes(model.getNsPrefixMap());
						model_provinces.add(model);
					} else {
						iter = model.listStatements(null, GEONAMES.featureCode, GEONAMES.A_ADM3) ;
						if ( iter.hasNext() ) {
							model_municipalities.setNsPrefixes(model.getNsPrefixMap());
							model_municipalities.add(model);
						}					
					}
				}				
			} catch ( JenaException e ) {
				System.err.println("Error: line " + count);
				System.err.println(str);
			}
		}
		in.close();

		model_regions.write(regions, "TURTLE");
		model_provinces.write(provinces, "TURTLE");
		model_municipalities.write(municipalities, "TURTLE");

		regions.close();
		provinces.close();
		municipalities.close();
	}

}
