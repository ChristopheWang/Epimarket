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

import fr.epimarket.bo.ProductBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Product;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.KeyRepository;


public class ProductBean
{
	//Injected references
	private ProductBO 				productBO = new ProductBO();
	private ProductEditBean 		productEditBean;
	private KeyRepository 			keyRepository;
	
	//Page variables
	private HtmlDataTable 			dataTable;
	private String 					sortColumn 		= "id";
    private int                		rowCount       	= 10;
	private boolean 				sortAscending;
	
	//temp
	private Date					date;
	
	
	public ProductBean() {}
	
	public ArrayList<Product> getAllProduct()
	{
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Product> list = (ArrayList<Product>) productBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumn)));
		else
			Collections.sort(list, new BeanComparator(sortColumn));
		return list;
	}
	
	public void createProduct()
	{
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("name", this.productEditBean.getCurrentProduct().getCategory().getName());
		ArrayList<Category> list = (ArrayList<Category>) this.productEditBean.getCategoryBO().find(criteria);
		
		productBO.create(new Product(list.get(0).getId(), this.productEditBean.getCurrentProduct().getDesignation(), this.productEditBean.getCurrentProduct().getPrice(), this.productEditBean.getCurrentProduct().getDescription()));
		this.productEditBean.getCurrentProduct().setDesignation("");
		this.productEditBean.getCurrentProduct().setPrice(0);
		this.productEditBean.getCurrentProduct().setDescription("");

	}
	
	public String delete()
	{
		Product c = (Product) (dataTable.getRowData());
		productBO.delete(c.getId());
		return "#";
	}
	
	public String edit()
	{
		ISQLCriteria criteria = new SQLCriteria();
		Product c = (Product) (dataTable.getRowData());
		criteria.add("id", c.getId().toString());
		ArrayList<Product> list = (ArrayList<Product>) productBO.find(criteria);
		productEditBean.setCurrentProduct(list.get(0)); 
		productEditBean.setStateDisplayValueOnly(false);
		return "productEdit";
	}

	public ProductBO getProductBO()										{return productBO;}
	public HtmlDataTable getDataTable()									{return dataTable;}
	public String getSortColumn()										{return sortColumn;}
	public boolean isSortAscending()									{return sortAscending;}
	public ProductEditBean getProductEditBean()							{return productEditBean;}
	public int getRowCount()											{return rowCount;}
	public Date getDate()												{return date;}
	public KeyRepository getKeyRepository()								{return keyRepository;}

	public void setProductBO(ProductBO productBO)						{this.productBO	 	= productBO;}
	public void setDataTable(HtmlDataTable dataTable)					{this.dataTable 		= dataTable;}
	public void setSortColumn(String sortColumn)						{this.sortColumn 		= sortColumn;}
	public void setSortAscending(boolean sortAscending)					{this.sortAscending 	= sortAscending;}
	public void setProductEditBean(ProductEditBean productEditBean)		{this.productEditBean 	= productEditBean;}
	public void setRowCount(int rowCount)								{this.rowCount 			= rowCount;}
	public void setDate(Date date)										{this.date 				= date;}
	public void setKeyRepository(KeyRepository keyRepository)			{this.keyRepository 	= keyRepository;}
	 
}
