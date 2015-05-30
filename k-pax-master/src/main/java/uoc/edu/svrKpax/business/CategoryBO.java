package uoc.edu.svrKpax.business;

import java.util.List;

import uoc.edu.svrKpax.vo.Category;

public interface CategoryBO {

	public List<Category> listCategories(String campusSession);
	public Category getCategory(String campusSession, int idCategory);

}
