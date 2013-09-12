package fr.epimarket.dao.definition;

import java.util.ArrayList;
import java.util.List;

import fr.epimarket.model.Category;
import fr.epimarket.model.Client;
import fr.epimarket.predicate.ISQLCriteria;

public interface ICategoryDAO extends IDAO
{
	public void 				create(Object obj);
	public void 				delete(Integer primaryKeyValue);
	public void 				update(Object obj, Integer primaryKeyValue);
	public ArrayList<Category>	find(ISQLCriteria criteria);
}
