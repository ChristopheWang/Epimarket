package fr.epimarket.dao;

import java.io.File;
import java.util.ArrayList;

import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IClientDAO;
import fr.epimarket.dao.definition.IProductDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;

public class ProductDAO extends AbstractDAO implements IProductDAO
{	
	public ProductDAO() {}
	
	public ProductDAO(String filename)
	{
		super.XMLConfig = filename;
		super.facade = new FacadeORM(super.XMLConfig, this);
	}

	public void create(Product obj)
	{
		super.create(obj);
	}
	
	public void delete(Integer primaryKeyValue)
	{
		super.delete(primaryKeyValue);
	}
	
	public void update(Product obj, Integer primaryKeyValue)
	{
		super.update(obj, primaryKeyValue);
	}
	
	public ArrayList<Product> find(ISQLCriteria criteria)
	{
		return (ArrayList<Product>) (super.find(criteria));
	}

}
