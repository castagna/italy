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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDFS;

public class EurostatItaly {

	public static final Property eurostat_label = ResourceFactory.createProperty("http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#", "name") ;
	
	public static void main(String[] args) throws IOException {
		extract_italy_nuts_codes() ;
	}
	
	public static void extract_italy_nuts_codes() throws IOException {
		Model eurostat = FileManager.get().loadModel( DATA_EUROSTAT_PATH + "eurostat-nuts2008-europe.ttl" ) ;
		StmtIterator iter = eurostat.listStatements() ;

		Model eurostat_italy = ModelFactory.createDefaultModel() ;
		eurostat_italy.setNsPrefix("eurostat", "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#") ;
		eurostat_italy.setNsPrefix("nuts", "http://ec.europa.eu/eurostat/ramon/rdfdata/nuts2008/") ;
		eurostat_italy.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#") ;
		eurostat_italy.setNsPrefix("italy", "http://data.kasabi.com/dataset/italy/") ;
		
		while ( iter.hasNext() ) {
			Statement stmt = iter.next() ;
			Resource subject = stmt.getSubject() ;
			if ( subject.getURI().contains("nuts2008/IT") ) {
				eurostat_italy.add(stmt) ;
				
				if ( stmt.getPredicate().equals(eurostat_label) ) {
					Resource object = ResourceFactory.createResource("http://data.kasabi.com/dataset/italy/" + GeonamesItaly.normalise(stmt.getLiteral().getLexicalForm()) ) ;
					eurostat_italy.add(subject, RDFS.seeAlso, object) ;
				}
			}
		}
		
		FileOutputStream out = new FileOutputStream(new File(DATA_EUROSTAT_PATH, "eurostat-nuts2008-italy.ttl")) ;
		eurostat_italy.write(out, "TURTLE") ;
		out.close() ;
	}

}
