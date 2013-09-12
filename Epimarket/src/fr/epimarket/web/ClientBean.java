package fr.epimarket.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Comparator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.collections.functors.AllPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import fr.epimarket.bo.CategoryBO;
import fr.epimarket.bo.ClientBO;
import fr.epimarket.bo.OrderlineBO;
import fr.epimarket.bo.ProductBO;
import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.model.Orderline;
import fr.epimarket.model.Product;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.FileDownloadHelper;
import fr.epimarket.web.util.KeyRepository;
import fr.epimarket.web.util.WebHelper;


public class ClientBean
{
	//Injected references
	private ClientBO 				clientBO = new ClientBO();
	private ProductBO				productBO = new ProductBO();
	private CategoryBO				categoryBO = new CategoryBO();
	private OrderlineBO				orderlineBO = new OrderlineBO();
	private ClientEditBean 			clientEditBean;
	private KeyRepository 			keyRepository;
	private Category				category;
	private Orderline				orderline;
	
	//Page variables
	private HtmlDataTable 			dataTable;
	private HtmlDataTable 			dataTableProduct;
	private HtmlDataTable 			dataTableBasket;
	private String 					sortColumn 			= "id";
	private String 					sortColumnBasket	= "id";
	private String 					sortColumnProduct	= "id";
    private int                		rowCount       	= 10;
	private boolean 				sortAscending;
	
	//temp
	private Date					date;
	
	
	public ClientBean() {}
	
	public ArrayList<Client> getAllClient()
	{
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Client> list = (ArrayList<Client>) clientBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumn)));
		else
			Collections.sort(list, new BeanComparator(sortColumn));
		return list;
	}
	
	public String validationForm()
	{
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("login", this.clientEditBean.getCurrentClient().getLogin());
		ArrayList<Client> list = (ArrayList<Client>) clientBO.find(criteria);
		if (list.size() > 0)
			return "registerFailed";
		return "registerConfirm";
	}
	
	public String createAccount()
	{
		clientBO.create(new Client(this.clientEditBean.getCurrentClient().getLogin(), this.clientEditBean.getCurrentClient().getPassword(), this.clientEditBean.getCurrentClient().getFirstName(), this.clientEditBean.getCurrentClient().getLastName(), this.clientEditBean.getCurrentClient().getSexe(), this.clientEditBean.getCurrentClient().getEmail(), this.clientEditBean.getCurrentClient().getPhone(), this.clientEditBean.getCurrentClient().getStreetNumber(), this.clientEditBean.getCurrentClient().getStreetName(), this.clientEditBean.getCurrentClient().getCity(), this.clientEditBean.getCurrentClient().getZipCode(), this.clientEditBean.getCurrentClient().getCountry()));
		return "registerDone";
	}
	
	public String logUser()
	{
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("login", this.clientEditBean.getCurrentClient().getLogin());
		criteria.add("password", this.clientEditBean.getCurrentClient().getPassword());
		ArrayList<Client> list = (ArrayList<Client>) clientBO.find(criteria);
		if (list.size() > 0)
		{
			Client c = list.get(0);
			this.clientEditBean.getCurrentClient().setId(c.getId());
			this.clientEditBean.getCurrentClient().setLastName(c.getLastName());
			this.clientEditBean.getCurrentClient().setFirstName(c.getFirstName());
			this.clientEditBean.getCurrentClient().setSexe(c.getSexe());
			this.clientEditBean.getCurrentClient().setEmail(c.getEmail());
			this.clientEditBean.getCurrentClient().setPhone(c.getPhone());
			this.clientEditBean.getCurrentClient().setStreetNumber(c.getStreetNumber());
			this.clientEditBean.getCurrentClient().setStreetName(c.getStreetName());
			this.clientEditBean.getCurrentClient().setCity(c.getCity());
			this.clientEditBean.getCurrentClient().setZipCode(c.getZipCode());
			this.clientEditBean.getCurrentClient().setCountry(c.getCountry());
			return "main";
		}
		return "loginFailed";
	}
	
	public String logAdmin()
	{
		if (this.clientEditBean.getCurrentClient().getLogin().equals("admin") && this.clientEditBean.getCurrentClient().getPassword().equals("root"))
			return "adminSuccess";
		return "loginFailed";
	}

	public String delete()
	{
		Client c = (Client) (dataTable.getRowData());
		clientBO.delete(c.getId());
		return "#";
	}
	
	public String edit()
	{
		ISQLCriteria criteria = new SQLCriteria();
		Client c = (Client) (dataTable.getRowData());
		criteria.add("id", c.getId().toString());
		ArrayList<Client> list = (ArrayList<Client>) clientBO.find(criteria);
		clientEditBean.setCurrentClient(list.get(0)); 
		clientEditBean.setStateDisplayValueOnly(false);
		return "clientEdit";
	}
	
	public String editProfile()
	{
		if (this.clientEditBean.getCurrentClient().getId() == 0)
			return "index";
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("login", this.clientEditBean.getCurrentClient().getLogin());
		criteria.add("password", this.clientEditBean.getCurrentClient().getPassword());
		ArrayList<Client> list = (ArrayList<Client>) clientBO.find(criteria);
		Client c = list.get(0);
		this.clientEditBean.getCurrentClient().setId(c.getId());
		this.clientEditBean.getCurrentClient().setLastName(c.getLastName());
		this.clientEditBean.getCurrentClient().setFirstName(c.getFirstName());
		this.clientEditBean.getCurrentClient().setSexe(c.getSexe());
		this.clientEditBean.getCurrentClient().setEmail(c.getEmail());
		this.clientEditBean.getCurrentClient().setPhone(c.getPhone());
		this.clientEditBean.getCurrentClient().setStreetNumber(c.getStreetNumber());
		this.clientEditBean.getCurrentClient().setStreetName(c.getStreetName());
		this.clientEditBean.getCurrentClient().setCity(c.getCity());
		this.clientEditBean.getCurrentClient().setZipCode(c.getZipCode());
		this.clientEditBean.getCurrentClient().setCountry(c.getCountry());
		return "editProfile";
	}
	
	public String selectCategory()
	{
		if (this.clientEditBean.getCurrentClient().getId() == 0)
			return "index";
		ISQLCriteria criteria = new SQLCriteria();
		Category c = (Category) (dataTable.getRowData());
		this.category.setId(c.getId());
		this.category.setName(c.getName());
		return "#";
	}
	
	public ArrayList<Product> getAllProduct()
	{
		ISQLCriteria criteria = new SQLCriteria();
		if (this.category.getId() != 0)
			criteria.add("categoryId", this.category.getId().toString());
		else
		{
			ArrayList<Category> listCategory = (ArrayList<Category>) categoryBO.find(criteria);
			if (listCategory.size() > 0)
				criteria.add("categoryId", listCategory.get(0).getId().toString());
		}
		ArrayList<Product> list = (ArrayList<Product>) productBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumn)));
		else
			Collections.sort(list, new BeanComparator(sortColumn));
		return list;
	}
	
	public ArrayList<Category> getAllCategory()
	{
		ISQLCriteria criteria = new SQLCriteria();
		ArrayList<Category> list = (ArrayList<Category>) categoryBO.find(criteria);
		return list;
	}
	
	public String addBasket()
	{
		if (this.clientEditBean.getCurrentClient().getId() == 0)
			return "index";
		Product p = (Product) (dataTableProduct.getRowData());
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("clientId", this.clientEditBean.getCurrentClient().getId().toString());
		criteria.add("productId", p.getId().toString());
		criteria.add("status", "basket");
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String localDate = format.format(new Date());
		if (list.size() > 0)
		{
			Orderline o = list.get(0);
			o.setQuantity(o.getQuantity() + 1);
			o.setStatus("basket");
			o.setDate(localDate);
			orderlineBO.update(o, list.get(0).getId());
		}
		else
		{
			ISQLCriteria criteriaBasket = new SQLCriteria();
			criteriaBasket.add("designation", p.getDesignation());
			ArrayList<Product> listBasket = (ArrayList<Product>) productBO.find(criteriaBasket);
			Orderline o = new Orderline(this.clientEditBean.getCurrentClient().getId(), listBasket.get(0).getId(), 1, "basket", localDate);
			orderlineBO.create(o);
		}
		return "#";
	}
	
	public String getTotalBasket()
	{
		Integer res = 0;
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("clientId", this.clientEditBean.getCurrentClient().getId().toString());
		criteria.add("status", "basket");
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		for (Orderline o : list)
		{
			ISQLCriteria criteriaPrice = new SQLCriteria();
			criteriaPrice.add("id", o.getProductId().toString());
			ArrayList<Product> listProductPrice = (ArrayList<Product>) productBO.find(criteriaPrice);
			res += o.getQuantity() * listProductPrice.get(0).getPrice();
		}
		return res.toString();
	}
	
	public String deleteItemFromBasket()
	{
		if (this.clientEditBean.getCurrentClient().getId() == 0)
			return "index";
		orderlineBO.delete(((Orderline) dataTableBasket.getRowData()).getId());
		return "#";
	}
	
	public ArrayList<Orderline> getAllOrderline()
	{
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("clientId", this.clientEditBean.getCurrentClient().getId().toString());
		criteria.add("status", "basket");
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumnBasket)));
		else
			Collections.sort(list, new BeanComparator(sortColumnBasket));
		return (list);
	}
	
	public ArrayList<Orderline> getAllRecord()
	{
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("clientId", this.clientEditBean.getCurrentClient().getId().toString());
		criteria.add("status", "bought");
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		if (sortAscending)
			Collections.sort(list, new ReverseComparator(new BeanComparator(sortColumnBasket)));
		else
			Collections.sort(list, new BeanComparator(sortColumnBasket));
		return (list);
	}
	
	public String checkout()
	{
		if (this.clientEditBean.getCurrentClient().getId() == 0)
			return "index";
		if (Integer.valueOf(getTotalBasket()) == 0)
			return "#";
		return "order";
	}
	
	public String validateOrder()
	{
		if (this.clientEditBean.getCurrentClient().getId() == 0)
			return "index";
		ISQLCriteria criteria = new SQLCriteria();
		criteria.add("clientId", this.clientEditBean.getCurrentClient().getId().toString());
		criteria.add("status", "basket");
		ArrayList<Orderline> list = (ArrayList<Orderline>) orderlineBO.find(criteria);
		for (Orderline o : list)
		{
			o.setStatus("bought");
			orderlineBO.update(o, o.getId());
		}
		return "orderDone";
	}
	
	public String getPdf()
	{
        try
        {
    		if (this.clientEditBean.getCurrentClient().getId() == 0)
    			return "index";
            FileDownloadHelper.returnFile(orderlineBO.getPDF((sortAscending)? new BeanComparator(sortColumn) : new ReverseComparator(new BeanComparator(sortColumn)), this.clientEditBean.getCurrentClient(), getTotalBasket()), 
            								"application/pdf", "Export.pdf");
        } 
        catch (Throwable t) {t.printStackTrace(); WebHelper.addMessage("generation NOk !", "Probleme dans la generation du document !", FacesMessage.SEVERITY_ERROR );}
        return "#";
    }
	
	public ClientBO getCurrentClientBO()										{return clientBO;}
	public ProductBO getProductBO()										{return productBO;}
	public CategoryBO getCategoryBO()									{return categoryBO;}
	public OrderlineBO getOrderlineBO()									{return orderlineBO;}
	public Category getCategory()										{return category;}
	public Orderline getOrderline()										{return orderline;}
	public HtmlDataTable getDataTable()									{return dataTable;}
	public HtmlDataTable getDataTableProduct()							{return dataTableProduct;}
	public HtmlDataTable getDataTableBasket()							{return dataTableBasket;}
	public String getSortColumn()										{return sortColumn;}
	public String getSortColumnBasket()									{return sortColumnBasket;}
	public String getSortColumnProduct()								{return sortColumnProduct;}
	public boolean isSortAscending()									{return sortAscending;}
	public ClientEditBean getClientEditBean()							{return clientEditBean;}
	public int getRowCount()											{return rowCount;}
	public Date getDate()												{return date;}
	public KeyRepository getKeyRepository()								{return keyRepository;}

	public void setClientBO(ClientBO clientBO)							{this.clientBO	 		= clientBO;}
	public void setProductBO(ProductBO productBO)						{this.productBO	 		= productBO;}
	public void setCategoryBO(CategoryBO categoryBO)					{this.categoryBO 		= categoryBO;}
	public void setOrderlineBO(OrderlineBO orderlineBO)					{this.orderlineBO 		= orderlineBO;}
	public void setCategory(Category category)							{this.category			= category;}
	public void setOrderline(Orderline orderline)						{this.orderline			= orderline;}
	public void setDataTable(HtmlDataTable dataTable)					{this.dataTable 		= dataTable;}
	public void setDataTableProduct(HtmlDataTable dataTableProduct)		{this.dataTableProduct	= dataTableProduct;}
	public void setDataTableBasket(HtmlDataTable dataTableBasket)		{this.dataTableBasket	= dataTableBasket;}
	public void setSortColumn(String sortColumn)						{this.sortColumn 		= sortColumn;}
	public void setSortColumnBasket(String sortColumnBasket)			{this.sortColumnBasket 	= sortColumnBasket;}
	public void setSortColumnProduct(String sortColumnProduct)			{this.sortColumnBasket 	= sortColumnProduct;}
	public void setSortAscending(boolean sortAscending)					{this.sortAscending 	= sortAscending;}
	public void setClientEditBean(ClientEditBean clientEditBean)		{this.clientEditBean 	= clientEditBean;}
	public void setRowCount(int rowCount)								{this.rowCount 			= rowCount;}
	public void setDate(Date date)										{this.date 				= date;}
	public void setKeyRepository(KeyRepository keyRepository)			{this.keyRepository 	= keyRepository;}
	 
}
