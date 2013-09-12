package fr.epimarket.xml;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


public class MySaxParser extends DefaultHandler
{
	private ArrayList<ArrayList<String>>	mappingProperties = new ArrayList<ArrayList<String>>();
	private ArrayList						tmp = new ArrayList<String>();
	private ArrayList						stringListDbConnect = new ArrayList();
	private ArrayList						stringListForeignKeys = new ArrayList();
	private String							fileName;
	private String							className;
	private boolean							inCategory, inConfig, inMapping;
	
	public MySaxParser() {}
	
	public MySaxParser(String fileName, Object classDao)
	{
		daoToModel(classDao);
		
		createStructures(fileName, this.className);
		
        createSaxParser();
	}
	
	public void daoToModel(Object classDao)
	{
		String className		= classDao.toString();
		StringBuilder result 	= new StringBuilder(className.substring(className.lastIndexOf(".") + 1, className.lastIndexOf("DAO")));
		
		this.className = result.toString();
	}
	
	public void createStructures(String fileName, String className)
	{
    	this.fileName			= fileName;  			
		this.className	 		= className;
        //System.out.println("MySaxParser starts parsing " + fileName);	
	}
	
	/**
	 * Instanciation du parser Sax affecte.
	 */ 	
	public void createSaxParser()
	{		
		SAXParserFactory factory= SAXParserFactory.newInstance();
	
        try 
        {
        	SAXParser saxParser	= factory.newSAXParser();
	
        	saxParser.getXMLReader().setErrorHandler(this);
            saxParser.getXMLReader().setEntityResolver(this);
            saxParser.getXMLReader().setDTDHandler(this);

            saxParser.parse(new File(fileName), this); 
        }
        catch (ParserConfigurationException pce)	{System.out.println("Erreur de configuration du parseur lors de l'appel à newSAXParser()");}
        catch (SAXException se)						{System.out.println("Erreur de parsing lors de l'appel à parse()");}
        catch (IOException ioe)						{System.out.println("Erreur d'entrée/sortie lors de l'appel à parse()" + ioe.toString());}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//SAX parsing methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void startElement(String namespaceURI, String sName, String qName, Attributes attrs) throws SAXException
	{
		{
			
			if (qName.equalsIgnoreCase("orm-configuration"))
				this.inConfig = true;
			if (this.inConfig == true && qName.equalsIgnoreCase("property"))
				this.stringListDbConnect.add(attrs.getValue("value"));
			if (qName.equalsIgnoreCase("orm-mapping"))
				this.inMapping = true;
			if (this.inMapping == true && qName.equalsIgnoreCase("class"))
				tmp.add(attrs.getValue("table"));
			if (this.inMapping == true && qName.equalsIgnoreCase("property"))
			{
				tmp.add(attrs.getValue("name"));
				tmp.add(attrs.getValue("column"));
				tmp.add(attrs.getValue("typename"));
				tmp.add(attrs.getValue("typecolumn"));
				tmp.add(attrs.getValue("length"));
			}
			if (this.inMapping == true && qName.equalsIgnoreCase("many-to-one"))
			{
				this.stringListForeignKeys.add(attrs.getValue("parent"));
				this.stringListForeignKeys.add(attrs.getValue("column"));
				this.stringListForeignKeys.add(attrs.getValue("name"));
				this.stringListForeignKeys.add(attrs.getValue("class"));
			}
		}
	}

	public void endElement(String namespaceURI, String sName, String qName) throws SAXException
	{
		if (qName.equalsIgnoreCase("orm-configuration"))
			this.inConfig = false;
		if (qName.equalsIgnoreCase("orm-mapping"))
			this.inMapping = false;
		if (qName.equalsIgnoreCase("class"))
		{
			this.mappingProperties.add(tmp);
			tmp = new ArrayList<String>();
		}
	}	
	
	public void characters(char buf[], int offset, int len) throws SAXException						{}
	public void startDocument() throws SAXException													{}	
	public void endDocument() throws SAXException													{}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Error handler methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void error(SAXParseException exception)					{System.out.println("error: " 		+ exception.toString());}
	public void fatalError(SAXParseException exception)				{System.out.println("fatalError: " 	+ exception.toString());}
	public void warning(SAXParseException exception)				{System.out.println("warning: " 	+ exception.toString());}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Entity handler methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public InputSource resolveEntity(String publicId, String systemId) throws SAXException		{return null;}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//DTD handler methods
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void notationDecl(String name, String publicId, String systemId)	
	{System.out.println("notationDecl: name: " + name + ", publicId: " + publicId + ", systemId: " + systemId);}
	
	public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException
	{System.out.println("unparsedEntityDecl: name: " + name + ", publicId: " + publicId + ", systemId: " + systemId + ", notationName: " + notationName);}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public String getClassName()													{return className;}
	public Boolean getinMapping()													{return inMapping;}
	public Boolean getinCategory()													{return inCategory;}
	public Boolean getinConfig()													{return inConfig;}
	public String getFileName()														{return fileName;}
	public ArrayList getTmp()														{return tmp;}
	public ArrayList getStringListDbConnect()										{return stringListDbConnect;}
	public ArrayList getStringListForeignKeys()										{return stringListForeignKeys;}
	public ArrayList<ArrayList<String>> getMappingProperties()						{return mappingProperties;}
	
	public void setClassName(String classModel)										{this.className = className;}
	public void setFileName(String fileName)										{this.fileName = fileName;}
	public void setInMapping(Boolean inMapping)										{this.inMapping = inMapping;}
	public void setInCategory(Boolean inCategory)									{this.inCategory = inCategory;}
	public void setInConfig(Boolean inConfig)										{this.inConfig = inConfig;}
	public void setStringListDbConnect(ArrayList stringListDbConnect)				{this.stringListDbConnect = stringListDbConnect;}
	public void setTmp(ArrayList tmp)												{this.tmp = tmp;}
	public void setMappingProperties(ArrayList<ArrayList<String>>mappingProperties) {this.mappingProperties = mappingProperties;}
	public void setStringListForeignKeys(ArrayList stringListForeignKeys)			{this.stringListForeignKeys = stringListForeignKeys;}

}
