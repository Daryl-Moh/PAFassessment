package nus.iss.paf.pafAssessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import nus.iss.paf.pafAssessment.exception.TransferException;
import nus.iss.paf.pafAssessment.models.Transfer;
import nus.iss.paf.pafAssessment.repository.AccountsRepository;
import nus.iss.paf.pafAssessment.service.FundsTransferService;
import nus.iss.paf.pafAssessment.service.LogAuditService;

@Controller
public class FundsTransferController {
    
    @Autowired
    private FundsTransferService fundsTransferSvc;

    @Autowired
    private AccountsRepository accountsRepo;

    @Autowired
    private LogAuditService logAuditSvc;

    @PostMapping(path="/transfer")
    public String processTransfer(@RequestBody MultiValueMap<String, String> form, Model model, @Valid Transfer t, 
        BindingResult bindings) throws TransferException {

        if (bindings.hasErrors())
            return "redirect:/";

        Boolean result = true;

        // check for account_id == 10
        if(t.getFromAccount().substring(6,16).length()!=10)
            result = false;

        // check if account exist
        if (accountsRepo.checkExists() == 0)
            result = false;

        // check fromAccount != toAccount
        if (t.getFromAccount().equals(t.getToAccount()))
            result = false;

        // check balance > amount
        String accountno = t.getFromAccount().substring(6,16);
        Float fromBalance = accountsRepo.getBalance(accountno);
        if (t.getAmount()>fromBalance)
            result = false;

        model.addAttribute("amount", t.getAmount());
        model.addAttribute("fromAccount", t.getFromAccount());
        model.addAttribute("toAccount", t.getToAccount());
        model.addAttribute("transaction_id", t.getTransaction_id());

        if (result == false) {
            return "redirect:/";

        } else {
            fundsTransferSvc.createTransaction(t);
            logAuditSvc.saveToRedis(t);
            return "confirmation";
        } 
    }

    @GetMapping(path={"/", "/index.html"})
    public String getIndex(Model model) {
        List<String> accounts = accountsRepo.populateDropList();
        model.addAttribute("accounts", accounts);
        System.out.println("Customers in Index >>>" + accounts.toString());
        return "index";
    }

    

}
