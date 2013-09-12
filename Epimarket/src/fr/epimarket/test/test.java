package fr.epimarket.test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import fr.epimarket.dao.AbstractDAO;
import fr.epimarket.dao.CategoryDAO;
import fr.epimarket.dao.ClientDAO;
import fr.epimarket.dao.OrderlineDAO;
import fr.epimarket.dao.ProductDAO;
import fr.epimarket.dao.definition.IDAO;
import fr.epimarket.factory.DBFactory;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.model.Orderline;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.ClientBean;
import fr.epimarket.xml.IXMLViewConstants;


public class test implements IXMLViewConstants
{

	public static void main(String argv[])
	{
		buildEntities();
	}
	
	public static void buildEntities()
	{
		IDAO categoryDao = new CategoryDAO(XMLCONF);
		System.out.println("--------------CREATION DES CATEGORIES-----------------");
		categoryDao.create(new Category("Sacs"));
		categoryDao.create(new Category("Bijoux"));
		categoryDao.create(new Category("Parfums"));
		categoryDao.create(new Category("Lingerie"));
		System.out.println("\n");
		
		
		IDAO clientDao = new ClientDAO(XMLCONF);
		System.out.println("--------------CREATION DES CLIENTS-----------------");
		clientDao.create(new Client("phol_r", "azerty", "Phol", "Richard", "homme", "richard.phol@epitech.eu", "06", "5", "rue Kennedy", "Le Kremlin-Bicetre", "94270", "FR"));
		clientDao.create(new Client("wang_c", "wxcvbn", "Wang", "Christophe", "homme", "christophe.wang@epitech.eu", "06", "5", "place des Fetes", "Paris", "75019", "FR"));
		System.out.println("\n");
		
		
		IDAO productDao = new ProductDAO(XMLCONF);
		System.out.println("--------------CREATION DES PRODUITS-----------------");
		productDao.create(new Product(1, "Sac Louis Vuitton", 900, "Le sac en toile de damier"));
		productDao.create(new Product(1, "Sac Hermès", 1500, "Un sac en peau de crocodile"));
		productDao.create(new Product(2, "Bague Boucheron", 450, "Certi de diamands"));
		productDao.create(new Product(2, "Bague H&M", 35, "Designée par Versace"));
		productDao.create(new Product(3, "Terre Hermès", 75, "Senteur boisée"));
		productDao.create(new Product(3, "Polo de Lacoste", 65, "Un parfum sport"));
		productDao.create(new Product(4, "Slip D&G", 45, "Taille XS"));
		productDao.create(new Product(4, "Soutien-gorge Armani", 75, "Taille M"));
		System.out.println("\n");
		
		
		IDAO orderlineDao = new OrderlineDAO(XMLCONF);
		System.out.println("--------------CREATION DES LIGNES DE COMMANDES-----------------");
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String localDate = format.format(new Date());
		orderlineDao.create(new Orderline(2, 2, 2, "basket", localDate));
		System.out.println("--------------RECUPERATION DES LIGNES DE COMMANDES-----------------");
		ISQLCriteria criteria = new SQLCriteria();
		//criteria.add("clientId", "1");
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineDao.find(criteria);
		assert (list == null) : "Impossible de loader la liste des lignes de commandes : null";
		for (Orderline o : list)
		{
			System.out.println(o.toString());
		}
		System.out.println("--------------EDITION DES LIGNES DE COMMANDES-----------------");
		orderlineDao.update(new Orderline(1, 3, 1, "basket", "01/11/2011"), 1);
		System.out.println("--------------SUPPRESSION DES LIGNES DE COMMANDES-----------------");
		orderlineDao.delete(1);
	}

	
}
