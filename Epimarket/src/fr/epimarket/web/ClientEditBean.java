package fr.epimarket.web;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;

import fr.epimarket.bo.ClientBO;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.KeyRepository;
import fr.epimarket.web.util.WebHelper;


public class ClientEditBean
{
	//Injected references
	private ClientBO 				clientBO;
	private KeyRepository 			keyRepository;
	
	//Session Associated references
	private Client					currentClient;
	
	private boolean 				stateDisplayValueOnly = true;
	
	
	public ClientEditBean()	{}
	
	public void editClient()
	{
		stateDisplayValueOnly = false;
	}
	
	public void saveClient()
	{
		stateDisplayValueOnly = true;
		
		Client c = new Client(this.currentClient.getLogin(), this.currentClient.getPassword(), this.currentClient.getLastName(), this.currentClient.getFirstName(), this.currentClient.getSexe(), this.currentClient.getEmail(), this.currentClient.getPhone(), this.currentClient.getStreetNumber(), this.currentClient.getStreetName(), this.currentClient.getCity(), this.currentClient.getZipCode(), this.currentClient.getCountry());
		clientBO.update(c, this.currentClient.getId());
		WebHelper.addMessageFromBundle(keyRepository.getResourceBundle(), "message.save.ok", "Enregistrement OK", FacesMessage.SEVERITY_INFO);
	}
	
	public String confirmEditProfile()
	{
		stateDisplayValueOnly = true;
		Client c = new Client(this.currentClient.getLogin(), this.currentClient.getPassword(), this.currentClient.getLastName(), this.currentClient.getFirstName(), this.currentClient.getSexe(), this.currentClient.getEmail(), this.currentClient.getPhone(), this.currentClient.getStreetNumber(), this.currentClient.getStreetName(), this.currentClient.getCity(), this.currentClient.getZipCode(), this.currentClient.getCountry());
		clientBO.update(c, this.currentClient.getId());
		return "profile";
	}
	
	public ClientBO getClientBO()											{return clientBO;}
	public Client getCurrentClient()										{return currentClient;}
	public boolean isStateDisplayValueOnly()								{return stateDisplayValueOnly;}
	public KeyRepository getKeyRepository()									{return keyRepository;}

	public void setClientBO(ClientBO clientBO)								{this.clientBO 					= clientBO;}
	public void setCurrentClient(Client client)								{this.currentClient 			= client;}
	public void setStateDisplayValueOnly(boolean stateDisplayValueOnly)		{this.stateDisplayValueOnly 	= stateDisplayValueOnly;}
	public void setKeyRepository(KeyRepository keyRepository)				{this.keyRepository 			= keyRepository;}

}
