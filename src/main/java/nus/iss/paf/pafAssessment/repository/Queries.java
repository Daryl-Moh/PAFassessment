package nus.iss.paf.pafAssessment.repository;

public class Queries {

    public static final String SQL_UPDATE_STATEMENT = """
        UPDATE accounts
        SET balance = ?
        WHERE acount_id = ?;
        """;

    public static final String SQL_SELECT_STATEMENT = """
        SELECT *
        FROM accounts
        """;   

    public static final String SQL_SELECT_NAME_ID_STATEMENT = """
        SELECT CONCAT (name, " (", account_id, ")") as Accounts
        from accounts;
        """;   

    public static final String SQL_COUNT_BY_ID_STATEMENT = """
        SELECT count(*)
        FROM accounts
        WHERE account_id = ?;
        """;     

    public static final String SQL_GET_BALANCE_BY_ID_STATEMENT = """
        SELECT count(*)
        FROM accounts
        WHERE account_id = ?;
        """;       

}
