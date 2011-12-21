/* CVS $Id: $ */
package com.kasabi.labs.vocabularies; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from data/vocabularies/eurostat-vocabulary-0.6.ttl 
 * @author Auto-generated by schemagen on 21 Dec 2011 18:26 
 */
public class EUROSTAT {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final Property code = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#code" );
    
    public static final Property edition = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#edition" );
    
    public static final Property hasParentRegion = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#hasParentRegion" );
    
    public static final Property hasSubRegion = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#hasSubRegion" );
    
    public static final Property inScheme = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#inScheme" );
    
    public static final Property level = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#level" );
    
    public static final Property name = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#name" );
    
    public static final Property protocolOrder = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#protocolOrder" );
    
    public static final Property regionCode = m_model.createProperty( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#regionCode" );
    
    public static final Resource Country = m_model.createResource( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#Country" );
    
    public static final Resource LAURegion = m_model.createResource( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#LAURegion" );
    
    public static final Resource NUTSRegion = m_model.createResource( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#NUTSRegion" );
    
    public static final Resource Region = m_model.createResource( "http://ec.europa.eu/eurostat/ramon/ontologies/geographic.rdf#Region" );
    
}
