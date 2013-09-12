package fr.epimarket.db;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import fr.epimarket.RuntimeException.ValidationException;
import fr.epimarket.factory.DBFactory;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;


public class DBManager
{
	private String				_driver;
	private String 				_url;
	private String 				_user;
	private String 				_password;
	private Connection 			_connect;
	private Statement 			_state = null;
	private ResultSet			_resultSet;
	private DatabaseMetaData	_dbMetaData;
	private ResultSetMetaData	_resultSetMetaData;
	private PreparedStatement 	_preparedStatement;
	
	public DBManager() {}
	
	public DBManager(String driver, String url, String user, String password)
	{
		this._driver 	= driver;
		this._url 		= url;
		this._user 		= user;
		this._password 	= password;
	}
	
	public void dbConnect()
	{
		try
		{	
			Class.forName(this._driver);
			this._connect 					= DriverManager.getConnection(this._url, this._user, this._password);
		}
		catch (ClassNotFoundException e) 	{System.out.println("Connexion au DBManager : Classe non existente: " + e.toString());}	
		catch (SQLException e) 				{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString());}	
		catch (ValidationException e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString());}	
	}
	
	public void dbDisconnect()
	{
		try {this._state.close();}
		catch (java.sql.SQLException e)		{System.out.println("Deconnexion au DBManager: Erreur interne SQL: " + e.toString());}
		catch (ValidationException e)		{System.out.println("Deconnexion au DBManager: Operation non valide: " + e.toString());}		
			
		try {this._connect.close();}
		catch (java.sql.SQLException e)		{System.out.println("Deconnexion au DBManager: Erreur interne SQL: " + e.toString());}
		catch (ValidationException e)		{System.out.println("Deconnexion au DBManager: Operation non valide: " + e.toString());}
	}
	
	public void init(ArrayList<ArrayList<String>> mappingProperties, ArrayList stringListForeignKeys)
	{
		try
		{
			_state					= _connect.createStatement();
			
			for (int i = 0; i != mappingProperties.size(); i++)
			{
				String queryString = "CREATE TABLE IF NOT EXISTS " + mappingProperties.get(i).get(0) + "(id int NOT NULL AUTO_INCREMENT, ";
				for (int j = 1; j < mappingProperties.get(i).size();)
				{
					if (mappingProperties.get(i).get(j + 4).equalsIgnoreCase("NULL"))
						queryString += mappingProperties.get(i).get(j + 1)  + " " + mappingProperties.get(i).get(j + 3) + ", ";
					else
						queryString += mappingProperties.get(i).get(j + 1)  + " " + mappingProperties.get(i).get(j + 3) + "(" + mappingProperties.get(i).get(j + 4) + "), ";
					j += 5;
				}
				queryString += "PRIMARY KEY (id)";
				for (int countFK = 0; countFK < stringListForeignKeys.size();)
				{
					if (stringListForeignKeys.get(countFK).equals(mappingProperties.get(i).get(0)))
					{
						queryString += ", FOREIGN KEY (" + stringListForeignKeys.get(countFK + 1) + ") REFERENCES " + stringListForeignKeys.get(countFK + 2) + "(id)";
					}
					countFK += 4;
				}
				queryString += ");";
				_preparedStatement = _connect.prepareStatement(queryString);
				//System.out.println(queryString);
				_preparedStatement.executeUpdate(queryString);	
			}

		}
		catch (SQLException e) 		{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString()); e.printStackTrace();}	
		catch (Exception e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString()); 	e.printStackTrace();}	
	}
	
	public void create(ArrayList<ArrayList<String>> mappingProperties, Object classModel)
	{
		try
		{	
			String		keysInsert = new String();
			Method		getMethod;
			Class		myTableAssociatedClass = Class.forName(classModel.getClass().getName().toString());
			Class 		tabParameterTypes[] = {};
			Object		tabObject[] = {};
			String		valuesInsert = new String();
			
			_state = _connect.createStatement();
			_resultSet = _state.executeQuery("Select * from "+ getTableName(classModel.getClass().toString())+";");
			_resultSetMetaData = _resultSet.getMetaData();
			int countFK = 0;
			for (int i = 0; i != mappingProperties.size(); i++)
			{
				if (mappingProperties.get(i).get(0).equalsIgnoreCase(getTableName(classModel.getClass().toString())))
				{
					for (int j = 1; j != mappingProperties.get(i).size();)
					{
						if (j > 5)
							keysInsert += ", ";
						keysInsert += mappingProperties.get(i).get(j);
						getMethod = myTableAssociatedClass.getMethod("get" + upperFirstChar(mappingProperties.get(i).get(j)), tabParameterTypes);
						Object objectResult = getMethod.invoke(classModel, tabObject);
						if (j > 5)
							valuesInsert += ", ";
						if (objectResult == null)
							valuesInsert += "NULL";
						else if (objectResult.getClass() ==  String.class || objectResult.getClass() == Date.class)
							valuesInsert += '\'' + objectResult.toString() + '\'';
						else
							valuesInsert += objectResult.toString();
						j += 5;
					}
				}
			}
			String queryString = new String("INSERT INTO "+ getTableName(classModel.getClass().toString()) +"("+keysInsert+") VALUES("+valuesInsert+");");
			_preparedStatement = _connect.prepareStatement(queryString);
			System.out.println(queryString);
			_preparedStatement.executeUpdate(queryString);
		}
		catch (SQLException e) 		{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString()); e.printStackTrace();}	
		catch (Exception e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString()); 	e.printStackTrace();}	
	}
	
	public void delete(String modelName, Integer primaryKeyValue)
	{
		try
		{		
			_state = _connect.createStatement();
			String queryString = new String("DELETE FROM "+ modelName + " WHERE id = " + primaryKeyValue + ";");
			_preparedStatement = _connect.prepareStatement(queryString);
			System.out.println(queryString);
			_preparedStatement.executeUpdate(queryString);
		}
		catch (SQLException e) 		{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString()); e.printStackTrace();}	
		catch (Exception e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString()); 	e.printStackTrace();}	
	}

	public void update(ArrayList<ArrayList<String>> mappingProperties, Object classModel, Integer primaryKeyValue)
	{
		try
		{	
			String		keysInsert = new String();
			Method		getMethod;
			Class		myTableAssociatedClass = Class.forName(classModel.getClass().getName().toString());
			Class 		tabParameterTypes[] = {};
			Object		tabObject[] = {};
			
			_state = _connect.createStatement();
			_resultSet = _state.executeQuery("Select * from "+ getTableName(classModel.getClass().toString())+";");
			_resultSetMetaData = _resultSet.getMetaData();
			int countFK = 0;
			for (int i = 0; i != mappingProperties.size(); i++)
			{
				if (mappingProperties.get(i).get(0).equalsIgnoreCase(getTableName(classModel.getClass().toString())))
				{
					for (int j = 1; j != mappingProperties.get(i).size();)
					{
						if (j > 5)
							keysInsert += ", ";
						keysInsert += mappingProperties.get(i).get(j) + "=";
						getMethod = myTableAssociatedClass.getMethod("get" + upperFirstChar(mappingProperties.get(i).get(j)), tabParameterTypes);
						Object objectResult = getMethod.invoke(classModel, tabObject);
						if (objectResult == null)
							keysInsert += "NULL";
						else if (objectResult.getClass() ==  String.class || objectResult.getClass() == Date.class)
							keysInsert += '\'' + objectResult.toString() + '\'';
						else
							keysInsert += objectResult.toString();
						j += 5;
					}
				}
			}
			String queryString = new String("UPDATE "+ getTableName(classModel.getClass().toString()) + " SET " + keysInsert + " WHERE id = " + primaryKeyValue + ";");
			_preparedStatement = _connect.prepareStatement(queryString);
			System.out.println(queryString);
			_preparedStatement.executeUpdate(queryString);
		}
		catch (SQLException e) 		{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString()); e.printStackTrace();}	
		catch (Exception e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString()); 	e.printStackTrace();}	
	}
	
	public List find(ArrayList<ArrayList<String>> mappingProperties, ArrayList stringListForeignKeys, String modelName, ISQLCriteria criteria)
	{
		try
		{
			Class	myTableAssociatedClass	 	= Class.forName("fr.epimarket.model." + modelName);
			ArrayList<Object> objectsList 		= new ArrayList<Object>();
			this._state							= this._connect.createStatement();
			String queryString					= new String("SELECT * FROM " + modelName.toLowerCase() + criteria.toSqlString());
			System.out.println(queryString);
			this._resultSet						= this._state.executeQuery(queryString);
			ArrayList<ArrayList<String>> fk		= new ArrayList<ArrayList<String>>();
			
			for (int countFK = 0; countFK < stringListForeignKeys.size();)
			{
				if (modelName.equalsIgnoreCase((String) stringListForeignKeys.get(countFK)))
				{
					ArrayList<String> tmp = new ArrayList();
					tmp.add((String) stringListForeignKeys.get(countFK + 1));
					tmp.add((String) stringListForeignKeys.get(countFK + 2));
					fk.add(tmp);
				}
				countFK += 4;
			}
			Integer count = 0;
			ArrayList<Integer> valueList = new ArrayList<Integer>();
			while (this._resultSet.next())
			{
				Object target 		= null;
				for (int i = 0; i != mappingProperties.size(); i++)
				{
					if (mappingProperties.get(i).get(0).equalsIgnoreCase(modelName))
					{
						target = DBFactory.getInstance(myTableAssociatedClass);
						BeanUtils.setProperty(target, "id", this._resultSet.getInt("id"));
						for (int j = 1; j < mappingProperties.get(i).size();)
						{
							for (int k = 0; k != fk.size(); k++)
								if (fk.get(k).get(0).equals(mappingProperties.get(i).get(j + 1)))
									valueList.add(this._resultSet.getInt(mappingProperties.get(i).get(j + 1)));
							if (mappingProperties.get(i).get(j + 2).equals("Integer"))
								BeanUtils.setProperty(target, mappingProperties.get(i).get(j), this._resultSet.getInt(mappingProperties.get(i).get(j + 1)));
							else if (mappingProperties.get(i).get(j + 2).equals("String"))
								BeanUtils.setProperty(target, mappingProperties.get(i).get(j), this._resultSet.getString(mappingProperties.get(i).get(j + 1)));
							j += 5;
						}
						for (int k = 0; k != fk.size(); k++)
						{
							ISQLCriteria criteriaRec = new SQLCriteria();
							criteriaRec.add("id", String.valueOf(valueList.get(count)));//fk.get(k).get(2));
							Object newObject = heavyObjetsFind(mappingProperties, stringListForeignKeys, fk.get(k).get(1), criteriaRec);
							BeanUtils.setProperty(target, fk.get(k).get(1), newObject);
							count++;
						}
						objectsList.add(target);
					}
				}
			}
			return (objectsList);
		}
		catch (SQLException e) 		{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString()); e.printStackTrace();}	
		catch (Exception e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString()); 	e.printStackTrace();}
		return (null);
	}
	
	public Object heavyObjetsFind(ArrayList<ArrayList<String>> mappingProperties, ArrayList stringListForeignKeys, String modelName, ISQLCriteria criteria)
	{
		try
		{
			Class	myTableAssociatedClass	 	= Class.forName("fr.epimarket.model." + upperFirstChar(modelName));
			ArrayList<Object> objectsList 		= new ArrayList<Object>();
			Statement localState				= this._connect.createStatement();
			String queryString					= new String("SELECT * FROM " + modelName.toLowerCase() + criteria.toSqlString());
			System.out.println(queryString);
			ResultSet localresultSet			= localState.executeQuery(queryString);
			ArrayList<ArrayList<String>> fk		= new ArrayList<ArrayList<String>>();
			Object newObject					= null;
			Object target 						= null;
			
			for (int countFK = 0; countFK < stringListForeignKeys.size();)
			{
				if (modelName.equalsIgnoreCase((String) stringListForeignKeys.get(countFK)))
				{
					ArrayList<String> tmp = new ArrayList();
					tmp.add((String) stringListForeignKeys.get(countFK + 1));
					tmp.add((String) stringListForeignKeys.get(countFK + 2));
					fk.add(tmp);
				}
				countFK += 4;
			}
			Integer count = 0;
			ArrayList<Integer> valueList = new ArrayList<Integer>();
			while (localresultSet.next())
			{
				for (int i = 0; i != mappingProperties.size(); i++)
				{
					if (mappingProperties.get(i).get(0).equalsIgnoreCase(modelName))
					{
						target = DBFactory.getInstance(myTableAssociatedClass);
						BeanUtils.setProperty(target, "id", localresultSet.getInt("id"));
						for (int j = 1; j < mappingProperties.get(i).size();)
						{
							for (int k = 0; k != fk.size(); k++)
								if (fk.get(k).get(0).equals(mappingProperties.get(i).get(j + 1)))
									valueList.add(localresultSet.getInt(mappingProperties.get(i).get(j + 1)));
							if (mappingProperties.get(i).get(j + 2).equals("Integer"))
								BeanUtils.setProperty(target, mappingProperties.get(i).get(j), localresultSet.getInt(mappingProperties.get(i).get(j + 1)));
							else if (mappingProperties.get(i).get(j + 2).equals("String"))
								BeanUtils.setProperty(target, mappingProperties.get(i).get(j), localresultSet.getString(mappingProperties.get(i).get(j + 1)));
							j += 5;
						}
						for (int k = 0; k != fk.size(); k++)
						{
							ISQLCriteria criteriaRec = new SQLCriteria();
							criteriaRec.add("id", String.valueOf(valueList.get(count)));
							newObject = heavyObjetsFind(mappingProperties, stringListForeignKeys, fk.get(k).get(1), criteriaRec);
							BeanUtils.setProperty(target, fk.get(k).get(1), newObject);
						}
					}
				}
			}
			return (target);
		}
		catch (SQLException e) 		{System.out.println("Connexion au DBManager : Erreur interne SQL: " + e.toString()); e.printStackTrace();}	
		catch (Exception e) 		{System.out.println("Connexion au DBManager : Operation non valide: " + e.toString()); 	e.printStackTrace();}
		return (null);
	}
	
	private static String getTableName(String className)
	{
		StringBuilder result = new StringBuilder(className.substring(className.lastIndexOf(".") + 1, className.length()));
		result.replace(0, 1, result.substring(0, 1).toLowerCase());
		return result.toString();
	}
	
	private static String upperFirstChar(String value)
	{
		if (value == null)
			return null;
		if (value.length() == 0)
			return value;
		StringBuilder result = new StringBuilder(value);
		result.replace(0, 1, result.substring(0, 1).toUpperCase());
		return result.toString();
	}
}