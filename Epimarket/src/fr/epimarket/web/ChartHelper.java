package fr.epimarket.web;

import java.util.Collection;
import java.util.Map;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import fr.epimarket.bo.ClientBO;
import fr.epimarket.bo.ProductBO;
import fr.epimarket.predicate.ISQLCriteria;
import fr.epimarket.predicate.SQLCriteria;
import fr.epimarket.web.util.ReflectHelper;

public class ChartHelper 
{
	//Injected references
	private ProductBO 				productBO;
	private ClientBO 				clientBO;
	
	
	
	public ChartHelper()
	{
		
	}

	public DefaultPieDataset getLocationClient() 
	{
		DefaultPieDataset pieDataSet = new DefaultPieDataset();
		ISQLCriteria criteria = new SQLCriteria();
		Map<Object, Collection> map = ReflectHelper.reflectMap(clientBO.find(criteria), "zipCode");
		
		for (Object key : map.keySet())
		{
			pieDataSet.setValue(key.toString(), map.get(key).size());
		}
		
		return pieDataSet;
	}
	
	public DefaultPieDataset getPriceProduct() 
	{
		DefaultPieDataset pieDataSet = new DefaultPieDataset();
		ISQLCriteria criteria = new SQLCriteria();
		Map<Object, Collection> map = ReflectHelper.reflectMap(productBO.find(criteria), "price");
		
		for (Object key : map.keySet())
			pieDataSet.setValue(key.toString(), map.get(key).size());
		
		return pieDataSet;
	}

	public DefaultCategoryDataset getCategoryDataset() 
	{
		DefaultCategoryDataset categoryDataSet;

		// row keys...
		String series1 = "First";
		String series2 = "Second";
		String series3 = "Third";
		// column keys...
		String category1 = "A";
		String category2 = "B";
		String category3 = "C";
		String category4 = "D";
		String category5 = "E";

		// create the dataset...
		categoryDataSet = new DefaultCategoryDataset();
		categoryDataSet.addValue(1.0, series1, category1);
		categoryDataSet.addValue(4.0, series1, category2);
		categoryDataSet.addValue(3.0, series1, category3);
		categoryDataSet.addValue(5.0, series1, category4);
		categoryDataSet.addValue(5.0, series1, category5);
		categoryDataSet.addValue(5.0, series2, category1);
		categoryDataSet.addValue(7.0, series2, category2);
		categoryDataSet.addValue(6.0, series2, category3);
		categoryDataSet.addValue(8.0, series2, category4);
		categoryDataSet.addValue(4.0, series2, category5);
		categoryDataSet.addValue(4.0, series3, category1);
		categoryDataSet.addValue(3.0, series3, category2);
		categoryDataSet.addValue(2.0, series3, category3);
		categoryDataSet.addValue(3.0, series3, category4);
		categoryDataSet.addValue(6.0, series3, category5);
		return categoryDataSet;

	}


	//Returns an implementation of an xy dataset
	public XYDataset getXYDataset() {
			TimeSeriesCollection timeSeriesDataSet = new TimeSeriesCollection();
			TimeSeries s1 = new TimeSeries("1", Month.class);
			s1.add(new Month(2, 2001), 181.8);
			s1.add(new Month(3, 2001), 167.3);
			s1.add(new Month(4, 2001), 153.8);
			s1.add(new Month(5, 2001), 167.6);
			s1.add(new Month(6, 2001), 158.8);
			s1.add(new Month(7, 2001), 148.3);
			s1.add(new Month(8, 2001), 153.9);
			s1.add(new Month(9, 2001), 142.7);
			s1.add(new Month(10, 2001), 123.2);
			s1.add(new Month(11, 2001), 131.8);
			s1.add(new Month(12, 2001), 139.6);
			s1.add(new Month(1, 2002), 142.9);
			s1.add(new Month(2, 2002), 138.7);
			s1.add(new Month(3, 2002), 137.3);
			s1.add(new Month(4, 2002), 143.9);
			s1.add(new Month(5, 2002), 139.8);
			s1.add(new Month(6, 2002), 137.0);
			s1.add(new Month(7, 2002), 132.8);
			
			TimeSeries s2 = new TimeSeries("2", Month.class);
			s2.add(new Month(2, 2001), 129.6);
			s2.add(new Month(3, 2001), 123.2);
			s2.add(new Month(4, 2001), 117.2);
			s2.add(new Month(5, 2001), 124.1);
			s2.add(new Month(6, 2001), 122.6);
			s2.add(new Month(7, 2001), 119.2);
			s2.add(new Month(8, 2001), 116.5);
			s2.add(new Month(9, 2001), 112.7);
			s2.add(new Month(10, 2001), 101.5);
			s2.add(new Month(11, 2001), 106.1);
			s2.add(new Month(12, 2001), 110.3);
			s2.add(new Month(1, 2002), 111.7);
			s2.add(new Month(2, 2002), 111.0);
			s2.add(new Month(3, 2002), 109.6);
			s2.add(new Month(4, 2002), 113.2);
			s2.add(new Month(5, 2002), 111.6);
			s2.add(new Month(6, 2002), 108.8);
			s2.add(new Month(7, 2002), 101.6);
			timeSeriesDataSet.addSeries(s1);
			timeSeriesDataSet.addSeries(s2);
		return timeSeriesDataSet;
	}

	public XYDataset getFirstXYDataset() {
		XYSeries series = new XYSeries("Price");
		series.setDescription("Price");
		series.add(1, 8);
		series.add(2, 7);
		series.add(3, 6);
		series.add(4, 5);
		series.add(5, 4);
		series.add(6, 3);
		series.add(7, 7);
		series.add(8, 8);
		
		return new XYSeriesCollection(series);
	}

	public XYDataset getSecondXYDataset() {
		XYSeries series = new XYSeries("Sentiment");
		series.setDescription("Sentiment");
		series.add(1, 10);
		series.add(2, 20);
		series.add(3, 30);
		series.add(4, 40);
		series.add(5, 50);
		series.add(6, 60);
		series.add(7, 70);
		series.add(8, 80);	
		
		return new XYSeriesCollection(series);
	}

	public ProductBO getProductBO()						{return productBO;}
	public ClientBO getClientBO()						{return clientBO;}

	public void setProductBO(ProductBO productBO)		{this.productBO = productBO;}	
	public void setClientBO(ClientBO clientBO)			{this.clientBO = clientBO;}
}
