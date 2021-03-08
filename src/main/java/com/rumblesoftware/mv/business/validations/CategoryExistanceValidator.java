package com.rumblesoftware.mv.business.validations;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.rumblesoftware.mv.exception.CategoryNotFoundException;
import com.rumblesoftware.mv.exception.InternalValidationErrorException;
import com.rumblesoftware.mv.exception.ValidationException;
import com.rumblesoftware.mv.io.CandidateToValidationData;


@Component
@PropertySource(value = "classpath:application.properties")
public class CategoryExistanceValidator extends BaseValidator<CandidateToValidationData> {

		
	private Logger log = LogManager.getLogger(CategoryExistanceValidator.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value(value = "${ms.categories.service.url}")
	public String url;
	
	@Override
	public void validate(CandidateToValidationData input) throws InternalValidationErrorException, ValidationException{
			
		log.debug("[Validation Layer] - Starting Category Validation...");
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put("catid", input.getCategoryId().toString());	
	    params.put("custid", input.getCustomerId().toString());	
	    	    
	    try {
	    	restTemplate.getForEntity(url,String.class,params);
	    } catch(HttpClientErrorException error) {
	    	if(error.getStatusCode() == HttpStatus.NOT_FOUND) {
	    		log.error("[validator layer] category does not exists");
	    		throw new CategoryNotFoundException();
	    	} else {
	    		log.error("[validator layer] An error occurred during category validation...");
	    		log.error(error.getMessage());
	    		error.printStackTrace();
	    		throw new InternalValidationErrorException();
	    	}    		
	    }
	    
		if(nextValidator != null)
			nextValidator.validate(input);
	}

}
