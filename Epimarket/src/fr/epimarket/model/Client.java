package fr.epimarket.model;

import java.util.ArrayList;

import fr.epimarket.db.DBManager;

public class Client
{
	private Integer				id = 0;
	private String				login;
	private	String				password;
	private String				email;
	private String				phone;
	private String				firstName;
	private String				lastName;
	private String				sexe;
	private String 				streetNumber;
	private String 				streetName;
	private String 				city;
	private String 				zipCode;
	private String 				country;
	
	public Client()	{}
	
	public Client(String login, String password, String lastName, String firstName, String sexe, String email, String phone, String streetNumber, String streetName, String city, String zipCode, String country)
	{
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sexe = sexe;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.city = city;
		this.zipCode = zipCode;
		this.country = country;
	}
	
	public String toString()
	{
		String res = new String();
		res = "\tIdentifiant = " + login + "\n";
		res = "\tMot de passe = " + password + "\n";
		res = "\tNom = " + lastName + "\n";
		res = "\tPrénom = " + firstName + "\n";
		res = "\tSexe = " + sexe + "\n";
		res = "\tEmail = " + email + "\n";
		res = "\tTéléphone = " + phone + "\n";
		res = "\tNuméro de rue = " + streetNumber + "\n";
		res = "\tAdresse = " + streetName + "\n";
		res = "\tCode postal = " + zipCode + "\n";
		res = "\tVille = " + city + "\n";
		res = "\tPays = " + country;
		return res;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//Getters & Setters
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public Integer getId()										{return id;}
	public String getFirstName()								{return firstName;}
	public String getLastName()									{return lastName;}
	public String getSexe()										{return sexe;}
	public String getEmail()									{return email;}
	public String getPhone()									{return phone;}
	public String getLogin() 									{return login;}
	public String getPassword() 								{return password;}
	public String getStreetNumber() 							{return streetNumber;}
	public String getStreetName() 								{return streetName;}
	public String getCity() 									{return city;}
	public String getZipCode() 									{return zipCode;}
	public String getCountry() 									{return country;}

	public void setId(Integer id)								{this.id				= id;}
	public void setFirstName(String firstName)					{this.firstName 		= firstName;}
	public void setLastName(String lastName)					{this.lastName 			= lastName;}
	public void setSexe(String sexe)							{this.sexe 				= sexe;}
	public void setEmail(String email)							{this.email 			= email;}
	public void setPhone(String phone)							{this.phone 			= phone;}
	public void setLogin(String login) 							{this.login 			= login;}
	public void setPassword(String password) 					{this.password 			= password;}
	public void setStreetNumber(String streetNumber) 			{this.streetNumber 		= streetNumber;}
	public void setStreetName(String streetName) 				{this.streetName 		= streetName;}
	public void setCity(String city) 							{this.city 				= city;}
	public void setZipCode(String zipCode) 						{this.zipCode 			= zipCode;}
	public void setCountry(String country) 						{this.country 			= country;}
}
