package fr.epimarket.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IOrderlineDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.model.Orderline;
import fr.epimarket.predicate.ISQLCriteria;

public class OrderlineDAO extends AbstractDAO implements IOrderlineDAO
{
	public OrderlineDAO() {}
	
	public OrderlineDAO(String filename)
	{
		super.XMLConfig = filename;
		super.facade = new FacadeORM(super.XMLConfig, this);
	}

	public void create(Orderline obj)
	{
		super.create(obj);
	}
	
	public void delete(Integer primaryKeyValue)
	{
		super.delete(primaryKeyValue);
	}
	
	public void update(Orderline obj, Integer primaryKeyValue)
	{
		super.update(obj, primaryKeyValue);
	}
	
	public ArrayList<Orderline> find(ISQLCriteria criteria)
	{
		return (ArrayList<Orderline>) (super.find(criteria));
	}

}
