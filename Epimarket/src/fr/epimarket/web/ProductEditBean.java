package fr.epimarket.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import fr.epimarket.bo.CategoryBO;
import fr.epimarket.bo.ProductBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Orderline;
import fr.epimarket.model.Product;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.KeyRepository;
import fr.epimarket.web.util.WebHelper;
import javax.faces.model.SelectItem;

public class ProductEditBean
{
	//Injected references
	private ProductBO 				productBO;
	private CategoryBO				categoryBO;
	private KeyRepository 			keyRepository;
	
	//Session Associated references
	private Product					currentProduct;
	
	private boolean 				stateDisplayValueOnly = true;
	
	
	public ProductEditBean()	{}
	
	public void editProduct()
	{
		stateDisplayValueOnly = false;
	}
	
	public void saveProduct()
	{
		stateDisplayValueOnly = true;
		
		
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("name", this.currentProduct.getCategory().getName());
		ArrayList<Category> list = (ArrayList<Category>) categoryBO.find(criteria);
		Product c = new Product(list.get(0).getId(), this.currentProduct.getDesignation(), this.currentProduct.getPrice(), this.currentProduct.getDescription());
		productBO.update(c, this.currentProduct.getId());
		WebHelper.addMessageFromBundle(keyRepository.getResourceBundle(), "message.save.ok", "Enregistrement OK", FacesMessage.SEVERITY_INFO);
	}
	
	public List getAllCategory()
	{
		List options = new ArrayList();
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Category> list = (ArrayList<Category>) categoryBO.find(criteria);
		for (Category c : list)
		{
			SelectItem option = new SelectItem(c.getName(), c.getName());
			options.add(option);
		}
		return options;
	}
	
	public ProductBO getProductBO()											{return productBO;}
	public CategoryBO getCategoryBO()										{return categoryBO;}
	public Product getCurrentProduct()										{return currentProduct;}
	public boolean isStateDisplayValueOnly()								{return stateDisplayValueOnly;}
	public KeyRepository getKeyRepository()									{return keyRepository;}

	public void setProductBO(ProductBO productBO)							{this.productBO 				= productBO;}
	public void setCategoryBO(CategoryBO categoryBO)						{this.categoryBO 				= categoryBO;}
	public void setCurrentProduct(Product currentProduct)					{this.currentProduct 			= currentProduct;}
	public void setStateDisplayValueOnly(boolean stateDisplayValueOnly)		{this.stateDisplayValueOnly 	= stateDisplayValueOnly;}
	public void setKeyRepository(KeyRepository keyRepository)				{this.keyRepository 			= keyRepository;}

}
