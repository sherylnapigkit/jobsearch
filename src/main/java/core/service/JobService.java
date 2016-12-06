package core.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import core.util.Constants;
import core.util.PropertyUtil;
import core.vo.Filter;
import core.vo.FilterRequest;
import core.vo.JobSearchResult;

@Service
public class JobService {
	private static Logger logger = Logger.getLogger(JobService.class);
	
	@Autowired
	RestTemplate restTemplate;
	
	public JobSearchResult searchJobsbyFilters(FilterRequest filterRequest) {
		
		String uri = processFilters(filterRequest);
		return retrieveJobSearchResult(uri);		
	}
	
	private JobSearchResult retrieveJobSearchResult(String uri) {
		try {
			JobSearchResult jobSearchResult = new JobSearchResult();
			
			jobSearchResult = restTemplate.getForObject(uri, JobSearchResult.class);
			return jobSearchResult;
		} catch (Exception e) {
			logger.error("JobService: retrieveJobSearchResult: " + e.getMessage());
		}
		return null;
	}
	
	private String processFilters(FilterRequest filterRequest) {
		try {
			String uri = PropertyUtil.getProperty("jobs");
			 if (uri == null || uri.trim().isEmpty()) {
				 return null;
			 }
			 return generateUriFromFilters(uri, filterRequest);
		} catch (Exception e) {
			logger.error("JobService: processFilters: " + e.getMessage());
		}
		return null;
	}
	
	public String generateUriFromFilters(String baseUri, FilterRequest filterRequest) {
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append(baseUri);
			sb.append("page=").append(filterRequest.getPageNo());
			if (filterRequest.getDescending() != null && filterRequest.getDescending()) {
				sb.append("&").append("descending").append("=").append(filterRequest.getDescending());				
			}
			for (Filter filter : filterRequest.getFilterList()) {
				if (filter != null && isValidFilterType(filter.getFilterType()) 
						&& !filter.getFilterValues().isEmpty()) {
					sb.append(filter.toString());					
				}
			}
			return sb.toString();
		} catch (Exception e) {
			logger.error("JobService: generateUriFromFilters: " + e.getMessage());
		}
		return null;
	}
	
	private boolean isValidFilterType(String filterType) {
		return filterType.equals(Constants.FILTER_COMPANY) ||
				filterType.equals(Constants.FILTER_CATEGORY) ||
				filterType.equals(Constants.FILTER_LEVEL) ||
				filterType.equals(Constants.FILTER_LOCATION);
	}
	 

}
