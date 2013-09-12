package fr.epimarket.model;

import java.util.ArrayList;

import fr.epimarket.db.DBManager;

public class Product
{
	private Integer					id = 0;
	private Category				category = null;
		
	private String 					designation, type, description; 
	
	private Integer					price, categoryId;
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Constructors
	////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Product() {}
	
	public Product(Integer categoryId, String designation, Integer price, String description)
	{
		this.designation = designation;
		this.categoryId = categoryId;
		this.price = price;
		this.description = description;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getter and setter
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Integer getId()												{return id;}
	public Category getCategory()										{return category;}
	public String getDesignation()										{return designation;}
	public Integer getPrice()											{return price;}
	public String getDescription()										{return description;}
	public Integer getCategoryId()										{return categoryId;}
	
	public void setId(Integer id)										{this.id				= id;}
	public void setCategory(Category category)							{this.category			= category;}
	public void setDesignation(String designation)						{this.designation		= designation;}
	public void setPrice(Integer price)									{this.price				= price;}
	public void setDescription(String description)						{this.description		= description;}
	public void setCategoryId(Integer categoryId)						{this.categoryId		= categoryId;}


	public String toString()								
	{
		String res = new String();
		res = "\tCategorie associée :\n" + ((category != null)? category.toString() : "null") + "\n";
		res += "\tDésignation = " + designation + "\n";
		res += "\tPrix = " + price + "\n";
		res += "\tDescription = " + description;
		return res;
	}
		
}