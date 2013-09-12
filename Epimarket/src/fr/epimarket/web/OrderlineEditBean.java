package fr.epimarket.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

import fr.epimarket.bo.ClientBO;
import fr.epimarket.bo.ClientBO;
import fr.epimarket.bo.OrderlineBO;
import fr.epimarket.bo.ProductBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Orderline;
import fr.epimarket.model.Client;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.KeyRepository;
import fr.epimarket.web.util.WebHelper;
import javax.faces.model.SelectItem;

public class OrderlineEditBean
{
	//Injected references
	private OrderlineBO 			orderlineBO;
	private ClientBO				clientBO;
	private ProductBO				productBO;
	private KeyRepository 			keyRepository;
	
	//Session Associated references
	private Orderline				currentOrderline;
	
	private boolean 				stateDisplayValueOnly = true;
	
	
	public OrderlineEditBean()	{}
	
	public void editOrderline()
	{
		stateDisplayValueOnly = false;
	}
	
	public void saveOrderline()
	{
		stateDisplayValueOnly = true;
		
		ISQLCriteria criteriaClient = new SQLCriteria();
		ISQLCriteria criteriaProduct = new SQLCriteria();
		criteriaClient.add("login", this.currentOrderline.getClient().getLogin());
		criteriaProduct.add("designation", this.currentOrderline.getProduct().getDesignation());
		ArrayList<Client> listClient = (ArrayList<Client>) clientBO.find(criteriaClient);
		ArrayList<Product> listProduct = (ArrayList<Product>) productBO.find(criteriaProduct);	
		Orderline c = new Orderline(listClient.get(0).getId(), listProduct.get(0).getId(), this.currentOrderline.getQuantity(), this.currentOrderline.getStatus(), this.currentOrderline.getDate());
		orderlineBO.update(c, this.currentOrderline.getId());
		WebHelper.addMessageFromBundle(keyRepository.getResourceBundle(), "message.save.ok", "Enregistrement OK", FacesMessage.SEVERITY_INFO);
	}
	
	public List getAllClient()
	{
		List options = new ArrayList();
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Client> list = (ArrayList<Client>) clientBO.find(criteria);
		for (Client c : list)
		{
			SelectItem option = new SelectItem(c.getLogin(), c.getLogin());
			options.add(option);
		}
		return options;
	}
	
	public List getAllProduct()
	{
		List options = new ArrayList();
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Product> list = (ArrayList<Product>) productBO.find(criteria);
		for (Product p : list)
		{
			SelectItem option = new SelectItem(p.getDesignation(), p.getDesignation());
			options.add(option);
		}
		return options;
	}
	
	public OrderlineBO getOrderlineBO()										{return orderlineBO;}
	public ClientBO getClientBO()											{return clientBO;}
	public ProductBO getProductBO()											{return productBO;}
	public Orderline getCurrentOrderline()									{return currentOrderline;}
	public boolean isStateDisplayValueOnly()								{return stateDisplayValueOnly;}
	public KeyRepository getKeyRepository()									{return keyRepository;}

	public void setOrderlineBO(OrderlineBO orderlineBO)						{this.orderlineBO 				= orderlineBO;}
	public void setClientBO(ClientBO clientBO)								{this.clientBO	 				= clientBO;}
	public void setProductBO(ProductBO productBO)							{this.productBO	 				= productBO;}
	public void setCurrentOrderline(Orderline currentOrderline)				{this.currentOrderline 			= currentOrderline;}
	public void setStateDisplayValueOnly(boolean stateDisplayValueOnly)		{this.stateDisplayValueOnly 	= stateDisplayValueOnly;}
	public void setKeyRepository(KeyRepository keyRepository)				{this.keyRepository 			= keyRepository;}

}
