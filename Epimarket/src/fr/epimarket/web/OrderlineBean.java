package fr.epimarket.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.collections.functors.AllPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import fr.epimarket.bo.OrderlineBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Orderline;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.KeyRepository;


public class OrderlineBean
{
	//Injected references
	private OrderlineBO 			orderlineBO = new OrderlineBO();
	private OrderlineEditBean 		orderlineEditBean;
	private KeyRepository 			keyRepository;
	
	//Page variables
	private HtmlDataTable 			dataTable;
	private String 					sortColumn 		= "id";
    private int                		rowCount       	= 10;
	private boolean 				sortAscending;
	
	//temp
	private Date					date;
	
	
	public OrderlineBean() {}
	
	public ArrayList<Orderline> getAllOrderline()
	{
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumn)));
		else
			Collections.sort(list, new BeanComparator(sortColumn));
		return list;
	}
	
	public String delete()
	{
		Orderline c = (Orderline) (dataTable.getRowData());
		orderlineBO.delete(c.getId());
		return "#";
	}
	
	public String edit()
	{
		ISQLCriteria criteria = new SQLCriteria();
		Orderline c = (Orderline) (dataTable.getRowData());
		criteria.add("id", c.getId().toString());
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		orderlineEditBean.setCurrentOrderline(list.get(0)); 
		orderlineEditBean.setStateDisplayValueOnly(false);
		return "orderlineEdit";
	}
	
	public String getStatusOrderline()
	{
		if (((Orderline) dataTable.getRowData()).getStatus().equalsIgnoreCase("basket"))
			return "panier";
		return "acheté";
	}

	public OrderlineBO getOrderlineBO()										{return orderlineBO;}
	public HtmlDataTable getDataTable()										{return dataTable;}
	public String getSortColumn()											{return sortColumn;}
	public boolean isSortAscending()										{return sortAscending;}
	public OrderlineEditBean getOrderlineEditBean()							{return orderlineEditBean;}
	public int getRowCount()												{return rowCount;}
	public Date getDate()													{return date;}
	public KeyRepository getKeyRepository()									{return keyRepository;}

	public void setOrderlineBO(OrderlineBO orderlineBO)						{this.orderlineBO	 	= orderlineBO;}
	public void setDataTable(HtmlDataTable dataTable)						{this.dataTable 		= dataTable;}
	public void setSortColumn(String sortColumn)							{this.sortColumn 		= sortColumn;}
	public void setSortAscending(boolean sortAscending)						{this.sortAscending 	= sortAscending;}
	public void setOrderlineEditBean(OrderlineEditBean orderlineEditBean)	{this.orderlineEditBean = orderlineEditBean;}
	public void setRowCount(int rowCount)									{this.rowCount 			= rowCount;}
	public void setDate(Date date)											{this.date 				= date;}
	public void setKeyRepository(KeyRepository keyRepository)				{this.keyRepository 	= keyRepository;}
	 
}
