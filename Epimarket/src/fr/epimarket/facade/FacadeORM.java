package fr.epimarket.facade;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.List;

import fr.epimarket.db.DBManager;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.xml.MySaxParser;

public class FacadeORM
{
	private MySaxParser saxParser;
	private DBManager connection;
	
	public FacadeORM() {}
	
	public FacadeORM(String XMLConfig, Object classDao)
	{
		this.saxParser = new MySaxParser(XMLConfig, classDao);
		List<String> list = this.saxParser.getStringListDbConnect();
		this.connection = new DBManager(list.get(0), list.get(1), list.get(2), list.get(3));
		this.init(XMLConfig);
	}
	
	/**
	 * Permet de cr�er les tables si elles n'existent pas encore
	 */
	public void init(String XMLConfig)
	{
		this.connection.dbConnect();
		this.connection.init(this.saxParser.getMappingProperties(), this.saxParser.getStringListForeignKeys());
		this.connection.dbDisconnect();
	}
	
	/**
	 * Permet de cr�er une entr�e dans la base de donn�es
	 * par rapport � un objet
	 */
	public void create(String XMLConfig, Object classModel)
	{
		this.connection.dbConnect();
		this.connection.create(this.saxParser.getMappingProperties(), classModel);
		this.connection.dbDisconnect();
	}
	
	/**
	 * Permet de supprimer une entr�e dans la base de donn�es
	 * par rapport � une classe et un crit�re id
	 */
	public void delete(String modelName, Integer primaryKeyValue)
	{
		this.connection.dbConnect();
		this.connection.delete(modelName, primaryKeyValue);
		this.connection.dbDisconnect();
	}
	
	/**
	 * Permet de mettre � jour une entr�e dans la base de donn�es
	 * par rapport � une classe et un crit�re id
	 */
	public void update(Object classModel, Integer primaryKeyValue)
	{
		this.connection.dbConnect();
		this.connection.update(this.saxParser.getMappingProperties(), classModel, primaryKeyValue);
		this.connection.dbDisconnect();
	}
	
	/**
	 * Permet de r�cup�rer une liste d'objets via une s�rie de crit�res
	 */
	public List find(String modelName, ISQLCriteria criteria)
	{
		this.connection.dbConnect();
		List list = this.connection.find(this.saxParser.getMappingProperties(), this.saxParser.getStringListForeignKeys(), modelName, criteria);
		this.connection.dbDisconnect();
		return (list);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public MySaxParser getSaxParser()						{return saxParser;}
	public DBManager getConnection()						{return connection;}
	
	public void setSaxParser(MySaxParser saxParser)			{this.saxParser = saxParser;}
	public void setConnection(DBManager connection)			{this.connection = connection;}
}
