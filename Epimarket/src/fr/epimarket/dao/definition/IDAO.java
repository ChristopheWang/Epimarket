package fr.epimarket.dao.definition;

import java.util.List;

import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;

public interface IDAO
{
	public void 		create(Object obj);
	public void 		delete(Integer primaryKeyValue);
	public void 		update(Object obj, Integer primaryKeyValue);
	public List			find(ISQLCriteria criteria);
}
