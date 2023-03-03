package nus.iss.paf.pafAssessment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import nus.iss.paf.pafAssessment.exception.TransferException;
import nus.iss.paf.pafAssessment.models.Customer;
import nus.iss.paf.pafAssessment.models.Transfer;
import nus.iss.paf.pafAssessment.repository.AccountsRepository;

@Service
public class FundsTransferService {
    
    @Autowired
    private AccountsRepository accRepo;
    
    @Transactional(rollbackFor = TransferException.class) 
    public Transfer createTransaction(Transfer transfer) throws TransferException {

        String transactionId= UUID.randomUUID().toString().substring(0, 8);
        transfer.setTransaction_id(transactionId);

        String s = transfer.getFromAccount();
        String id = s.substring(6, 16);
        Float currBalance = accRepo.getBalance(id);
        Float newBalance = currBalance - transfer.getAmount();
        Integer result = accRepo.updateBalance(newBalance, id);

        if(result == 0){
            throw new TransferException("Transaction Unsucessful");
        }

        return transfer;

    }
    
}
