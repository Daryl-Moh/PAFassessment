package nus.iss.paf.pafAssessment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import nus.iss.paf.pafAssessment.models.Customer;
import nus.iss.paf.pafAssessment.repository.AccountsRepository;
import nus.iss.paf.pafAssessment.service.FundsTransferService;

@SpringBootApplication
public class PafAssessmentApplication implements CommandLineRunner{

	@Autowired
	private AccountsRepository acctRepo;

	@Autowired
	private FundsTransferService fundTrfSvc;
	public static void main(String[] args) {
		SpringApplication.run(PafAssessmentApplication.class, args);
	}

	@Override
	public void run(String... args){
		
		List<Customer> results = acctRepo.retrieveAllCustomer();
	
		for (Customer c: results)
			System.out.printf(">>> %s\n", c.toString());
		
	}

}
