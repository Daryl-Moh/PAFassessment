package nus.iss.paf.pafAssessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.iss.paf.pafAssessment.models.Customer;

import static nus.iss.paf.pafAssessment.repository.Queries.*;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountsRepository {
    
    @Autowired
    private JdbcTemplate template;

    public int updateBalance(Float newBalance, String id) {
        return template.update(SQL_UPDATE_STATEMENT, newBalance, id);
    }

    public List<Customer> retrieveAllCustomer() {
        List<Customer> custList = new ArrayList<Customer>();
        custList = template.query(SQL_SELECT_STATEMENT, BeanPropertyRowMapper.newInstance(Customer.class));
        return custList;
    }

    public List<String> populateDropList() {
        List<String> accounts = template.queryForList(SQL_SELECT_NAME_ID_STATEMENT, String.class);
        return accounts;
    }

    public int checkExists() {
        Integer result = template.queryForObject(SQL_SELECT_NAME_ID_STATEMENT, Integer.class);
        return result;
    }

    public Float getBalance(String account_id) {
        Float balance = template.queryForObject(SQL_GET_BALANCE_BY_ID_STATEMENT, Float.class);
        return balance;
    }

}
