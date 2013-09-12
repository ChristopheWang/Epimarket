package fr.epimarket.model;

import java.util.ArrayList;

import fr.epimarket.db.DBManager;

public class Category
{
	private Integer				id = 0;
	private String 				name = null;
	
	public Category() {}
	public Category(String name)
	{
		this.setName(name);
	};
	
	public String toString()
	{
		String res = new String();
		res += "\t\tNom = " + name;
		return res;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public String 	getName()									{return name;}
	public Integer 	getId()										{return id;}
	
	public void setName(String name)							{this.name = name;}
	public void setId(Integer id)								{this.id = id;}
}
