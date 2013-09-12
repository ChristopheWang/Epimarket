package fr.epimarket.bo;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.epimarket.RuntimeException.ValidationException;
import fr.epimarket.dao.CategoryDAO;
import fr.epimarket.dao.ClientDAO;
import fr.epimarket.dao.OrderlineDAO;
import fr.epimarket.dao.ProductDAO;
import fr.epimarket.dao.definition.ICategoryDAO;
import fr.epimarket.dao.definition.IClientDAO;
import fr.epimarket.dao.definition.IProductDAO;
import fr.epimarket.facade.FacadeORM;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.model.Orderline;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.service.reporting.ProductReportWriter;
import fr.epimarket.xml.IXMLViewConstants;

public class OrderlineBO implements IXMLViewConstants
{
	private OrderlineDAO orderlineDAO;
	
	public OrderlineBO()
	{
		this.orderlineDAO = new OrderlineDAO(XMLCONFCOMPLETE);
	}
	
	public InputStream getPDF(Comparator beanComparator, Client c, String totalPrice) throws Exception 
	{
		try
		{
			ISQLCriteria criteria = new SQLCriteria();
			criteria.add("clientId", c.getId().toString());
			criteria.add("status", "basket");
			ArrayList<Orderline> list = (ArrayList<Orderline>) find(criteria);
			Collections.sort(list, beanComparator);
			return new ProductReportWriter().generate(list, c, totalPrice);
		}
		catch (ValidationException e) 		{System.out.println("Vous ne pouvez pas exporter en PDF : " + e.toString());}
		return null;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Services access
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void create(Orderline obj) 							{orderlineDAO.create(obj);}
	public void delete(Integer primaryKeyValue) 				{orderlineDAO.delete(primaryKeyValue);}
	public void update(Orderline obj, Integer primaryKeyValue) 	{orderlineDAO.update(obj, primaryKeyValue);}
	public List<Orderline> find(ISQLCriteria criteria)			{return orderlineDAO.find(criteria);}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & setters
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public OrderlineDAO getOrderlineDAO()						{return orderlineDAO;}

	public void setOrderlineDAO(OrderlineDAO orderlineDAO)		{this.orderlineDAO = orderlineDAO;}

}
