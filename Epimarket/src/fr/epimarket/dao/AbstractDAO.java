package fr.epimarket.dao;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.Predicate;
import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.functors.AllPredicate;
import org.apache.commons.collections.functors.AnyPredicate;
import org.apache.commons.collections.functors.EqualPredicate;
import fr.epimarket.RuntimeException.ValidationException;
import fr.epimarket.dao.definition.IDAO;
import fr.epimarket.db.DBManager;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.predicate.ISQLCriteria;

public abstract class AbstractDAO implements IDAO
{	
	protected List 		list	= new ArrayList();
	protected FacadeORM facade;
	protected String	XMLConfig;
	
	public AbstractDAO() {}
	
	public void create(Object obj)
	{
		this.facade.create(this.XMLConfig, obj);
	}
	
	public void delete(Integer primaryKeyValue)
	{
		this.facade.delete(this.daoToModel(), primaryKeyValue);
	}
	
	public void update(Object obj, Integer primaryKeyValue)
	{
		this.facade.update(obj, primaryKeyValue);
	}
	
	public List find(ISQLCriteria criteria)
	{
		return (this.facade.find(this.daoToModel(), criteria));
	}
	
	private String daoToModel()
	{
		String className		= this.toString();
		StringBuilder result 	= new StringBuilder(className.substring(className.lastIndexOf(".") + 1, className.lastIndexOf("DAO")));
		return(result.toString());
	}
	
	public FacadeORM getFacade()				{return this.facade;}
	public void setFacade(FacadeORM facade)		{this.facade = facade;}
}
