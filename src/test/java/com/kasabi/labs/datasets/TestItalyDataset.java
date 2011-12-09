package com.kasabi.labs.datasets;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openjena.atlas.iterator.Iter;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.RDF;
import com.kasabi.labs.datasets.italy.Run;

public class TestItalyDataset {

	private static Model model = null;

	@BeforeClass
	public static void load() {
		model = FileManager.get().loadModel(Constants.VOCABULARIES_PATH + Run.FILENAME);
	}

	@Test
	public void countRegions() {
		ResIterator iter = model.listSubjectsWithProperty(RDF.type, ResourceFactory.createResource(Run.ITALY_NAMESPACE + "Region"));
		assertEquals ("There are 20 regions in Italy.", 20L, Iter.count(iter));
	}

	@Test
	public void countProvinces() {
		ResIterator iter = model.listSubjectsWithProperty(RDF.type, ResourceFactory.createResource(Run.ITALY_NAMESPACE + "Province"));
		assertEquals ("There are 110 provinces in Italy.", 110L, Iter.count(iter));
	}

}
