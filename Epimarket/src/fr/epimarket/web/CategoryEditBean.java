package fr.epimarket.web;

import javax.faces.application.FacesMessage;

import fr.epimarket.bo.CategoryBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.web.util.KeyRepository;
import fr.epimarket.web.util.WebHelper;


public class CategoryEditBean
{
	//Injected references
	private CategoryBO 				categoryBO;
	private KeyRepository 			keyRepository;
	
	//Session Associated references
	private Category				currentCategory;
	
	private boolean 				stateDisplayValueOnly = true;
	
	
	public CategoryEditBean()	{}
	
	public void editCategory()
	{
		stateDisplayValueOnly = false;
	}
	
	public void saveCategory()
	{
		stateDisplayValueOnly = true;
		
		Category c = new Category(this.currentCategory.getName());
		categoryBO.update(c, this.currentCategory.getId());
		WebHelper.addMessageFromBundle(keyRepository.getResourceBundle(), "message.save.ok", "Enregistrement OK", FacesMessage.SEVERITY_INFO);
	}
	
	public CategoryBO getCategoryBO()										{return categoryBO;}
	public Category getCurrentCategory()									{return currentCategory;}
	public boolean isStateDisplayValueOnly()								{return stateDisplayValueOnly;}
	public KeyRepository getKeyRepository()									{return keyRepository;}

	public void setCategoryBO(CategoryBO categoryBO)						{this.categoryBO 				= categoryBO;}
	public void setCurrentCategory(Category currentCategory)				{this.currentCategory 			= currentCategory;}
	public void setStateDisplayValueOnly(boolean stateDisplayValueOnly)		{this.stateDisplayValueOnly 	= stateDisplayValueOnly;}
	public void setKeyRepository(KeyRepository keyRepository)				{this.keyRepository 			= keyRepository;}

}
