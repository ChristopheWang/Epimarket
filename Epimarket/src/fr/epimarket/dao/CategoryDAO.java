package fr.epimarket.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.predicate.ISQLCriteria;

public class CategoryDAO extends AbstractDAO implements ICategoryDAO
{
	public CategoryDAO() {}
	
	public CategoryDAO(String filename)
	{
		super.XMLConfig = filename;
		super.facade = new FacadeORM(super.XMLConfig, this);
	}

	public void create(Category obj)
	{
		super.create(obj);
	}
	
	public void delete(Integer primaryKeyValue)
	{
		super.delete(primaryKeyValue);
	}
	
	public void update(Category obj, Integer primaryKeyValue)
	{
		super.update(obj, primaryKeyValue);
	}
	
	public ArrayList<Category> find(ISQLCriteria criteria)
	{
		return (ArrayList<Category>) (super.find(criteria));
	}
}
