package cl.matiasaraya.ms.loanconsumer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.matiasaraya.ms.loanconsumer.elements.Loan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = "cl.matiasaraya:ms.loans:+:stubs:8080")
public class LoanConsumerContractTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private Loan loan;
    
    @Before
    public void setup() {
    	loan = new Loan();
    	loan.setId(1);
    	loan.setType("hipotecario");
    	loan.setDebtAmount(1000);    	    	
    }

    @Test
    public void given_WhenPassIdOne_ThenReturnLoanObject() throws Exception {
    	ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/getLoan/?id=1")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
        
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Loan response = objectMapper.readValue(contentAsString, Loan.class);       
        
        Assert.assertEquals(loan, response);
    }
}
