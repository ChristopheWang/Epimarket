 package fr.epimarket.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;


import fr.epimarket.dao.CategoryDAO;
import fr.epimarket.dao.AbstractDAO;
import fr.epimarket.model.Category;

public class DBFactory
{	
	public static Object getInstance(Class className) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		return className.newInstance();
	}
}
