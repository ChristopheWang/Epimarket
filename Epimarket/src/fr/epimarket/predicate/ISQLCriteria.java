package fr.epimarket.predicate;

public interface ISQLCriteria
{
	public void add(String property, String value);
	public String toSqlString();
}
