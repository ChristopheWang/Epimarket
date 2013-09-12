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

import fr.epimarket.bo.CategoryBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.KeyRepository;


public class CategoryBean
{
	//Injected references
	private CategoryBO 				categoryBO = new CategoryBO();
	private CategoryEditBean 		categoryEditBean;
	private KeyRepository 			keyRepository;
	
	//Page variables
	private HtmlDataTable 			dataTable;
	private String 					sortColumn 		= "id";
    private int                		rowCount       	= 10;
	private boolean 				sortAscending;
	
	//temp
	private Date					date;
	
	
	public CategoryBean() {}
	
	public ArrayList<Category> getAllCategory()
	{
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Category> list = (ArrayList<Category>) categoryBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumn)));
		else
			Collections.sort(list, new BeanComparator(sortColumn));
		return list;
	}
	
	public String createCategory()
	{
		categoryBO.create(new Category(this.categoryEditBean.getCurrentCategory().getName()));
		this.categoryEditBean.getCurrentCategory().setName("");
		return "#";
	}
	
	public String delete()
	{
		Category c = (Category) (dataTable.getRowData());
		categoryBO.delete(c.getId());
		return "#";
	}
	
	public String edit()
	{
		ISQLCriteria criteria = new SQLCriteria();
		Category c = (Category) (dataTable.getRowData());
		criteria.add("id", c.getId().toString());
		ArrayList<Category> list = (ArrayList<Category>) categoryBO.find(criteria);
		categoryEditBean.setCurrentCategory(list.get(0)); 
		categoryEditBean.setStateDisplayValueOnly(false);
		return "categoryEdit";
	}

	public CategoryBO getCategoryBO()									{return categoryBO;}
	public HtmlDataTable getDataTable()									{return dataTable;}
	public String getSortColumn()										{return sortColumn;}
	public boolean isSortAscending()									{return sortAscending;}
	public CategoryEditBean getCategoryEditBean()						{return categoryEditBean;}
	public int getRowCount()											{return rowCount;}
	public Date getDate()												{return date;}
	public KeyRepository getKeyRepository()								{return keyRepository;}

	public void setCategoryBO(CategoryBO categoryBO)					{this.categoryBO	 	= categoryBO;}
	public void setDataTable(HtmlDataTable dataTable)					{this.dataTable 		= dataTable;}
	public void setSortColumn(String sortColumn)						{this.sortColumn 		= sortColumn;}
	public void setSortAscending(boolean sortAscending)					{this.sortAscending 	= sortAscending;}
	public void setCategoryEditBean(CategoryEditBean categoryEditBean)	{this.categoryEditBean 	= categoryEditBean;}
	public void setRowCount(int rowCount)								{this.rowCount 			= rowCount;}
	public void setDate(Date date)										{this.date 				= date;}
	public void setKeyRepository(KeyRepository keyRepository)			{this.keyRepository 	= keyRepository;}
	 
}
