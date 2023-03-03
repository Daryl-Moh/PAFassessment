package nus.iss.paf.pafAssessment.models;


import java.security.Timestamp;
import java.util.Date;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Transfer {

    private String transaction_id;
    
    @NotNull
    //@Size(min=10, message="Minimum amount for transfer is $10.00")
    // I found out that @Size does not work on float but I cannot change it as it will affect other codes
    // should have used BigDecimal and @DecimalMin for validation
    private Float amount;

    @NotNull
    private String fromAccount;

    @NotNull
    private String toAccount;   

    private String comments;

    private Long timestamp;

    public String getTransaction_id() {
        return transaction_id;
    }
    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public String getFromAccount() {
        return fromAccount;
    }
    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }
    public String getToAccount() {
        return toAccount;
    }
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Customer {transaction_id = %s, transferAmount = %.2f,  fromAccount = %s, toAccount = %s, comments = %s}"
            .formatted(transaction_id, amount, fromAccount, toAccount, comments);
    }
    
    public JsonObject toJson(Transfer trf) {

        return Json.createObjectBuilder()
            .add("transactionId", trf.getTransaction_id())
            // not sure why my date can't be put in
            //.add("date", trf.setTimestamp(System.currentTimeMillis())) 
            .add("from_account", trf.getFromAccount())
            .add("to_account", trf.getToAccount())
            .add("amount", trf.getAmount())
            .build();
    }
  

    
}
