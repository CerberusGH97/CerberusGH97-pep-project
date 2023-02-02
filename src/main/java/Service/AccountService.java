package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

public Account addAccount(Account account){
    return accountDAO.insertAccount(account);
}

public Account loginAccount(Account account){
    return accountDAO.verifyAccount(account);
}

}
