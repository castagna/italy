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

import static com.kasabi.labs.datasets.Constants.DATA_ISO_3166_2_PATH;
import static com.kasabi.labs.datasets.Constants.QUERIES_PATH;
import static com.kasabi.labs.datasets.Constants.VOCABULARIES_PATH;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.util.Timer;
import com.kasabi.labs.datasets.Utils;

public class IsoItaly {

	private static final Logger log = LoggerFactory.getLogger(IsoItaly.class) ;
	
	// input data
	public static final File[] data = {
		new File(DATA_ISO_3166_2_PATH + "iso-3166-2.ttl"),
	};
	
	public static final File[] merge = {
		new File(VOCABULARIES_PATH, Run.FILENAME),
	};
	
	public static void main(String[] args) throws IOException {
		File query = new File(QUERIES_PATH, "iso_codes.sparql") ;
		Model result = Utils.construct(data, merge, query) ;

		Timer timer = new Timer();
		timer.startTimer();
		File output = new File(VOCABULARIES_PATH, Run.FILENAME); 
		FileOutputStream out = new FileOutputStream (output);
		result.write(out, "TURTLE") ;
		out.flush();
		out.close();
		log.info("Generated {} in {} ms", output.getAbsolutePath(), timer.endTimer());
	}

}
