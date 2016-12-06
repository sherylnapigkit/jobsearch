package web.dto;

import java.util.ArrayList;
import java.util.List;

public class JobRequestDTO {

	private Integer page;
	private Boolean descending;
	private List<String> companyFilter = new ArrayList<String>();
	private List<String> categoryFilter = new ArrayList<String>();
	private List<String> levelFilter = new ArrayList<String>();
	private List<String> locationFilter = new ArrayList<String>();
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Boolean getDescending() {
		return descending;
	}
	public void setDescending(Boolean descending) {
		this.descending = descending;
	}
	public List<String> getCompanyFilter() {
		return companyFilter;
	}
	public void setCompanyFilter(List<String> companyFilter) {
		this.companyFilter = companyFilter;
	}
	public List<String> getCategoryFilter() {
		return categoryFilter;
	}
	public void setCategoryFilter(List<String> categoryFilter) {
		this.categoryFilter = categoryFilter;
	}
	public List<String> getLevelFilter() {
		return levelFilter;
	}
	public void setLevelFilter(List<String> levelFilter) {
		this.levelFilter = levelFilter;
	}
	public List<String> getLocationFilter() {
		return locationFilter;
	}
	public void setLocationFilter(List<String> locationFilter) {
		this.locationFilter = locationFilter;
	}
	
	
}
