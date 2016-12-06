package service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import core.service.JobService;
import core.util.Constants;
import core.util.PropertyUtil;
import core.vo.Filter;
import core.vo.FilterRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JobServiceTest {
	private final Logger log = Logger.getLogger(JobServiceTest.class);
	
	@Autowired
	JobService jobService;
	
	@Test
	public void testSearchJobsbyFilters() {
		assertNotNull(jobService.searchJobsbyFilters(createMockFilterRequest()));
	}
	
	@Test
	public void testGenerateUriFromFilters() {
		String uri = "";
		try {
			uri = PropertyUtil.getProperty("jobs");
			assertNotNull(uri); 
		} catch (Exception e) {
			log.error("cannot load properties");
		}
		assertNotNull(jobService.generateUriFromFilters(uri, createMockFilterRequest()));
	}
	
	public FilterRequest createMockFilterRequest() {
		FilterRequest filterRequest = new FilterRequest();
		filterRequest.setDescending(true);
		filterRequest.setPageNo(0);
		Filter filter = new Filter();
		filter.setFilterType(Constants.FILTER_LEVEL);
		filter.setFilterValues(createMockFilterValues());
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(filter);
		filterRequest.setFilterList(filters);
		return filterRequest;
	}
	
	public List<String> createMockFilterValues() {
		List<String> filterVals = new ArrayList<String>();
		filterVals.add("Filter1");
		filterVals.add("Filter2");
		filterVals.add("Filter3");
		return filterVals;
	}
}
