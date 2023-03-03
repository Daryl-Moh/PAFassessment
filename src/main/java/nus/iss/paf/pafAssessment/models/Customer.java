package nus.iss.paf.pafAssessment.models;


public class Customer {

    private String account_id;
    private String name;
    private Float balance;
    
    public String getAccount_id() {
        return account_id;
    }
    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getBalance() {
        return balance;
    }
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Customer {account_id = %s, name = %s, balance = %.2f}"
            .formatted(account_id, name, balance);
    }

}
