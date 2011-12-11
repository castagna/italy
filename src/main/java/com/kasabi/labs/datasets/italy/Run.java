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

import java.io.IOException;

public class Run {

	public static final String ITALY_DATA_NAMESPACE = "http://data.kasabi.com/dataset/italy/" ;
	public static final String ITALY_SCHEMA_NAMESPACE = "http://data.kasabi.com/dataset/italy/schema/" ;
	public static final String VERSION = "1.4" ;
	public static final String FILENAME_PREFIX = "kasabi-italy" ;
	public static final String FILENAME = FILENAME_PREFIX + "-" + VERSION + ".ttl" ;
	
	public static void main(String[] args) throws IOException {
		EurostatItaly.extract_italy_nuts_codes() ; // generates eurostat-nuts2008-italy.ttl
		GeonamesItaly.generate_italy_vocabulary(); // generates FILENAME_PREFIX-VERSION.ttl
		IsoItaly.main(new String[]{}); // add ISO codes to FILENAME_PREFIX-VERSION.ttl
	}

}
