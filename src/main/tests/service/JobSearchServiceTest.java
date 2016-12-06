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

import core.util.Constants;
import core.vo.Filter;
import core.vo.FilterRequest;
import web.dto.JobRequestDTO;
import web.dto.JobResultDTO;
import web.service.JobSearchService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JobSearchServiceTest {

	private final Logger log = Logger.getLogger(JobSearchServiceTest.class);

	@Autowired
	JobSearchService jobSearchService;
	
	@Test
	public void testCreateFilter() {
		List<String> filterVals = createMockFilterValues();
		Filter filter = jobSearchService.createFilter(filterVals, Constants.FILTER_LEVEL);
		log.info(filter.toString());
	}
	
	@Test
	public void testCreateFilterRequest() {
		JobRequestDTO jobRequestDTO = createMockJobRequest();
		FilterRequest filterRequest = jobSearchService.createFilterRequest(jobRequestDTO);
		assertNotNull(filterRequest);
	}
	
	@Test
	public void testGetJobSearchResults() {
		JobRequestDTO jobRequestDTO = createMockJobRequest();
		JobResultDTO jobResultDTO = jobSearchService.getJobSearchResults(jobRequestDTO);
		assertNotNull(jobResultDTO);
	}
	
	public JobRequestDTO createMockJobRequest() {
		JobRequestDTO jobRequestDTO = new JobRequestDTO();
		
		List<String> filterVals = createMockFilterValues();
		jobRequestDTO.setCategoryFilter(filterVals);
		jobRequestDTO.setCompanyFilter(filterVals);
		jobRequestDTO.setLevelFilter(filterVals);
		jobRequestDTO.setLocationFilter(filterVals);
		jobRequestDTO.setDescending(true);
		jobRequestDTO.setPage(0);
		
		return jobRequestDTO;
	}
	
	public List<String> createMockFilterValues() {
		List<String> filterVals = new ArrayList<String>();
		filterVals.add("Filter1");
		filterVals.add("Filter2");
		filterVals.add("Filter3");
		return filterVals;
	}
}
