/* CVS $Id: $ */
package com.kasabi.labs.vocabularies; 
import com.hp.hpl.jena.rdf.model.*;
 
/**
 * Vocabulary definitions from data/italy/italy-vocabulary.ttl 
 * @author Auto-generated by schemagen on 21 Dec 2011 18:26 
 */
public class ITALY {
    /** <p>The RDF model that holds the vocabulary terms</p> */
    private static Model m_model = ModelFactory.createDefaultModel();
    
    /** <p>The namespace of the vocabulary as a string</p> */
    public static final String NS = "http://data.kasabi.com/dataset/italy/schema/";
    
    /** <p>The namespace of the vocabulary as a string</p>
     *  @see #NS */
    public static String getURI() {return NS;}
    
    /** <p>The namespace of the vocabulary as a resource</p> */
    public static final Resource NAMESPACE = m_model.createResource( NS );
    
    public static final Property contains = m_model.createProperty( "http://data.kasabi.com/dataset/italy/schema/contains" );
    
    public static final Property iso3166_2 = m_model.createProperty( "http://data.kasabi.com/dataset/italy/schema/iso3166-2" );
    
    public static final Resource Comune = m_model.createResource( "http://data.kasabi.com/dataset/italy/schema/Comune" );
    
    public static final Resource Province = m_model.createResource( "http://data.kasabi.com/dataset/italy/schema/Province" );
    
    public static final Resource Region = m_model.createResource( "http://data.kasabi.com/dataset/italy/schema/Region" );
    
    public static final Resource PaoloCastagna = m_model.createResource( "http://data.kasabi.com/dataset/italy/schema/PaoloCastagna" );
    
}
