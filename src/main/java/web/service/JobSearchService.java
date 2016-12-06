package web.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import core.service.JobService;
import core.util.Constants;
import core.vo.Filter;
import core.vo.FilterRequest;
import core.vo.Generic;
import core.vo.Job;
import core.vo.JobSearchResult;
import web.dto.JobDTO;
import web.dto.JobRequestDTO;
import web.dto.JobResultDTO;

@Service
public class JobSearchService {
	private static Logger logger = Logger.getLogger(JobSearchService.class);
	
	@Autowired
	JobService jobService;
	
	public JobResultDTO getJobSearchResults(JobRequestDTO jobRequestDTO) {
		return processSearchRequest(jobRequestDTO);
	}
	
	private JobResultDTO processSearchRequest(JobRequestDTO jobRequestDTO) {
		try {
			FilterRequest filterRequest = createFilterRequest(jobRequestDTO);
			JobSearchResult jobSearchResult = jobService.searchJobsbyFilters(filterRequest);
			JobResultDTO jobResultDTO = convertJobResults(jobSearchResult);
			return jobResultDTO;
		} catch (Exception e) {
			logger.error("JobSearchService: processSearchRequest: " + e.getMessage());
		}
		return null;
	}
	
	private JobResultDTO convertJobResults(JobSearchResult jobSearchResult) {
		try {
			JobResultDTO jobResultDTO = new JobResultDTO();
			
			BeanUtils.copyProperties(jobSearchResult, jobResultDTO);
			List<JobDTO> jobDtos = new ArrayList<JobDTO>();
			StringBuilder locSb = new StringBuilder();
			for (Job j : jobSearchResult.getJobList()) {
				JobDTO jobDTO = new JobDTO();
				BeanUtils.copyProperties(j, jobDTO);
				for (Generic g : j.getLocations()) {
					if (g.getName() != null && !g.getName().isEmpty()) {
						locSb.append(g.getName()).append(", ");
					}
				}
				jobDTO.setLocations(locSb.toString().substring(0, locSb.length()-1));
				jobDTO.setCompanyLongName(j.getCompany().getName());
				jobDtos.add(jobDTO);
			}
			jobResultDTO.setJobs(jobDtos);
			
			return jobResultDTO;
		} catch (Exception e) {
			logger.error("JobSearchService: convertJobResults: " + e.getMessage());
		}
		return null;
	}
	
	public FilterRequest createFilterRequest(JobRequestDTO jobRequestDTO) {
		try {
			List<Filter> filterList = new ArrayList<Filter>();
			Filter companyFilter = createFilter(jobRequestDTO.getCompanyFilter(), Constants.FILTER_COMPANY);
			if (companyFilter != null) {
				filterList.add(companyFilter);
			}
			Filter categoryFilter = createFilter(jobRequestDTO.getCategoryFilter(), Constants.FILTER_CATEGORY);
			if (categoryFilter != null) {
				filterList.add(categoryFilter);
			}
			Filter levelFilter = createFilter(jobRequestDTO.getLevelFilter(), Constants.FILTER_LEVEL);
			if (levelFilter != null) {
				filterList.add(levelFilter);
			}
			Filter locationFilter = createFilter(jobRequestDTO.getLocationFilter(), Constants.FILTER_LOCATION);
			if (locationFilter != null) {
				filterList.add(locationFilter);
			}
			
			FilterRequest filterRequest = new FilterRequest();
			BeanUtils.copyProperties(jobRequestDTO, filterRequest);
			filterRequest.setFilterList(filterList);
			return filterRequest;
		} catch (Exception e) {
			logger.error("JobSearchService: createFilterRequest: " + e.getMessage());
		}
		return null;
	}
	
	public Filter createFilter(List<String> filterValues, String filterType) {
		try {
			if (filterType == null || filterValues.isEmpty()) {
				return null;
			}
			Filter filter = new Filter();
			filter.setFilterType(filterType);
			
			List<String> encodedValues = new ArrayList<String>();
			for (String s : filterValues) {
				try {
					encodedValues.add(URLEncoder.encode(s, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			filter.setFilterValues(encodedValues);
			
			return filter;
		} catch (Exception e) {
			logger.error("JobSearchService: createFilter: " + e.getMessage());
		}
		return null;
	}
	
	
}
