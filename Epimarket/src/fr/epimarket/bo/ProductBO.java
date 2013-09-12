package fr.epimarket.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.epimarket.dao.ClientDAO;
import fr.epimarket.dao.ProductDAO;
import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IClientDAO;
import fr.epimarket.dao.definition.IProductDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.xml.IXMLViewConstants;

public class ProductBO implements IXMLViewConstants
{
	private ProductDAO productDAO;
	
	public ProductBO() 
	{
		this.productDAO = new ProductDAO(XMLCONFCOMPLETE);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Services access
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void create(Product obj) 							{productDAO.create(obj);}
	public void delete(Integer primaryKeyValue) 				{productDAO.delete(primaryKeyValue);}
	public void update(Product obj, Integer primaryKeyValue) 	{productDAO.update(obj, primaryKeyValue);}
	public List<Product> find(ISQLCriteria criteria)			{return productDAO.find(criteria);}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & setters
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public ProductDAO getProductDAO()					{return productDAO;}

	public void setProductDAO(ProductDAO productDAO)	{this.productDAO = productDAO;}

}
