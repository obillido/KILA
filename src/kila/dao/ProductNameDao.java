package kila.dao;

public class ProductNameDao {
	private static ProductNameDao instance=new ProductNameDao();
	private ProductNameDao() {}
	public static ProductNameDao getInstance() {
		return instance;
	}
}
