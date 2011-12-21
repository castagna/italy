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

package com.kasabi.labs.datasets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openjena.atlas.iterator.Iter;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.vocabulary.RDFS;
import com.kasabi.labs.datasets.italy.Run;
import com.kasabi.labs.vocabularies.ITALY;

public class TestItalyDataset {

	private static Model model = null;

	@BeforeClass
	public static void load() {
		model = FileManager.get().loadModel(Constants.VOCABULARIES_PATH + Run.FILENAME);
	}

	@Test
	public void countRegions() {
		ResIterator iter = model.listSubjectsWithProperty(RDF.type, ITALY.Region);
		assertEquals ("There are 20 regions in Italy.", 20L, Iter.count(iter));
	}

	@Test
	public void countProvinces() {
		ResIterator iter = model.listSubjectsWithProperty(RDF.type, ITALY.Province);
		assertEquals ("There are 110 provinces in Italy.", 110L, Iter.count(iter));
	}

	@Test
	public void testRegionSlugs() {
		ResIterator iter = model.listSubjectsWithProperty(RDF.type, ITALY.Region);
		while ( iter.hasNext() ) {
			Resource region = iter.nextResource();
			
			// check localname is derived from rdfs:label via slugging
			StmtIterator iter2 = region.listProperties(RDFS.label);
			while ( iter2.hasNext() ) {
				Literal lit = iter2.nextStatement().getObject().asLiteral();
				if ( lit.getLanguage().equals("it") ) {
					assertEquals ( region.getLocalName(), Utils.toSlug(lit.getLexicalForm()) );
				}
			}
			
			StmtIterator iter3 = region.listProperties(ITALY.contains);
			while ( iter3.hasNext() ) {
				Resource province = iter3.nextStatement().getResource() ;
				assertTrue ( model.contains(province, RDF.type, ITALY.Province));
			}
		}
	}
	
}
