package fr.epimarket.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.epimarket.dao.CategoryDAO;
import fr.epimarket.dao.ClientDAO;
import fr.epimarket.dao.ProductDAO;
import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IClientDAO;
import fr.epimarket.dao.definition.IDAO;
import fr.epimarket.dao.definition.IProductDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.xml.IXMLViewConstants;

public class CategoryBO implements IXMLViewConstants
{
	private IDAO categoryDAO;
	
	public CategoryBO()
	{
		this.categoryDAO = new CategoryDAO(XMLCONFCOMPLETE);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Services access
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void create(Category obj) 							{categoryDAO.create(obj);}
	public void delete(Integer primaryKeyValue) 				{categoryDAO.delete(primaryKeyValue);}
	public void update(Category obj, Integer primaryKeyValue) 	{categoryDAO.update(obj, primaryKeyValue);}
	public List<Category> find(ISQLCriteria criteria)			{return categoryDAO.find(criteria);}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & setters
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public IDAO getCategoryDAO()								{return categoryDAO;}

	public void setCategoryDAO(CategoryDAO categoryDAO)			{this.categoryDAO = categoryDAO;}

}
