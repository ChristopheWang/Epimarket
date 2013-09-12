package fr.epimarket.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.epimarket.db.DBManager;

public class Orderline
{

	private Product 	product = null;
	private Client		client = null;
	private Integer		id = 0;
	private Integer		quantity, productId, clientId;
	private String		status, date;
	
	
	public Orderline()	{}

	public Orderline(Integer clientId, Integer productId, Integer quantity, String status, String date)
	{
		this.clientId = clientId;
		this.productId = productId;
		this.quantity = quantity;
		this.status = status;
		this.date = date;
	}
	
	public String toString()
	{
		String res = new String();
		res = "Client associé :\n" + ((client != null)? client.toString() : "null") + "\n";
		res += "Produit associé :\n" + ((product != null)? product.toString() : "null") + "\n";
		res += "Quantité = " + quantity + "\n";
		res += "Status = " + status + "\n";
		res += "Date = " + date;
		return res;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	public Integer getId()						{return id;}
	public Product getProduct()					{return product;}
	public Client getClient()					{return client;}
	public Integer getQuantity()				{return quantity;}
	public Integer getProductId()				{return productId;}
	public Integer getClientId()				{return clientId;}
	public String getStatus()					{return status;}
	public String getDate()						{return date;}
	
	public void setId(Integer id)				{this.id		= id;}
	public void setProduct(Product product)		{this.product 	= product;}
	public void setQuantity(Integer quantity)	{this.quantity 	= quantity;}
	public void setClient(Client client)		{this.client 	= client;}
	public void setProductId(Integer productId)	{this.productId = productId;}
	public void setClientId(Integer clientId)	{this.clientId 	= clientId;}
	public void setStatus(String status)		{this.status	= status;}
	public void setDate(String date)			{this.date		= date;}
}
