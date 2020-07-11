package cl.matiasaraya.ms.loanconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import cl.matiasaraya.ms.loanconsumer.elements.Loan;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class LoanConsumerController {

	@Autowired
    private RestTemplate restTemplate;

    
    /** GET **/	
	@GetMapping(value = "/getLoan/", produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Retrieve the loan by the given id", response = Loan.class, responseContainer = "", notes = "Returns true for success and false for error operation")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success return the Loan Object", response = Boolean.class),
			@ApiResponse(code = 204, message = "No content, without content for the query"),
			@ApiResponse(code = 500, message = "Internal Server Error, Exception message") })
	public ResponseEntity<Loan> getLoan(@RequestParam int id) {		
							
		HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
 
        ResponseEntity<Loan> responseEntity = restTemplate.exchange(
          "http://localhost:8080/loans/getLoan/?id=" + id,
          HttpMethod.GET,
          new HttpEntity<>(httpHeaders),
          Loan.class);
        
        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
	}

}
