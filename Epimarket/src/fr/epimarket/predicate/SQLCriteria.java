package fr.epimarket.predicate;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.AllPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

public class SQLCriteria implements ISQLCriteria
{
	/*ArrayList<Predicate> 			listPredicate  	= new ArrayList();
	List 							listMatching 	= new ArrayList();
	Predicate						pAll;*/
	ArrayList<ArrayList<String>>	listCriteriaAnd = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>>	listCriteriaOr = new ArrayList<ArrayList<String>>();
	
	public SQLCriteria() {}
	
	public void add(String property, String value)
	{
		//this.listPredicate.add(new BeanPredicate(property, new EqualPredicate(value)));
		ArrayList tmp = new ArrayList<String>();
		tmp.add(property);
		tmp.add(value);
		this.listCriteriaAnd.add(tmp);
	}
	
	public String toSqlString()
	{
		if (this.listCriteriaAnd.isEmpty())
			return "";
		String result = new String(" WHERE ");
		for (int i = 0; i != this.listCriteriaAnd.size(); i++)
		{
			if (i > 0)
				result += " AND ";
			result += this.listCriteriaAnd.get(i).get(0) + " = \'" + this.listCriteriaAnd.get(i).get(1) + "\'";
		}
		for (int i = 0; i != this.listCriteriaOr.size(); i++)
		{
			if (i > 0)
				result += " OR ";
			result += this.listCriteriaOr.get(i).get(0) + " = \'" + this.listCriteriaOr.get(i).get(1) + "\'";
		}
		return result;
	}
}
