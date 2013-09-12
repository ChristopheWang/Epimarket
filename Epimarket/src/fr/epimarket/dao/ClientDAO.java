package fr.epimarket.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IClientDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;

public class ClientDAO extends AbstractDAO implements IClientDAO
{	
	public ClientDAO() {}
	
	public ClientDAO(String filename)
	{
		super.XMLConfig = filename;
		super.facade = new FacadeORM(super.XMLConfig, this);
	}

	public void create(Client obj)
	{
		super.create(obj);
	}
	
	public void delete(Integer primaryKeyValue)
	{
		super.delete(primaryKeyValue);
	}
	
	public void update(Client obj, Integer primaryKeyValue)
	{
		super.update(obj, primaryKeyValue);
	}
	
	public ArrayList<Client> find(ISQLCriteria criteria)
	{
		return (ArrayList<Client>) (super.find(criteria));
	}
}
