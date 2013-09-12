package fr.epimarket.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.epimarket.dao.AbstractDAO;
import fr.epimarket.dao.CategoryDAO;
import fr.epimarket.dao.ClientDAO;
import fr.epimarket.dao.ProductDAO;
import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IClientDAO;
import fr.epimarket.dao.definition.IDAO;
import fr.epimarket.dao.definition.IProductDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.xml.IXMLViewConstants;

public class ClientBO implements IXMLViewConstants
{
	private IDAO clientDAO;
	
	public ClientBO()
	{
		this.clientDAO = new ClientDAO(XMLCONFCOMPLETE);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Services access
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void create(Client obj) 								{clientDAO.create(obj);}
	public void delete(Integer primaryKeyValue) 				{clientDAO.delete(primaryKeyValue);}
	public void update(Client obj, Integer primaryKeyValue) 	{clientDAO.update(obj, primaryKeyValue);}
	public List<Client> find(ISQLCriteria criteria)				{return clientDAO.find(criteria);}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & setters
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public IDAO getClientDAO()									{return clientDAO;}

	public void setClientDAO(ClientDAO clientDAO)				{this.clientDAO = clientDAO;}

}
