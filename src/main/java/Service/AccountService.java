package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

public Account addAccount(Account account){
Account accountfromDB = this.accountDAO.verifyAccount(account);
if(accountfromDB!=null||account.username.length()==0||account.password.length()<4){
    return null;
}else{
    return accountDAO.insertAccount(account);
}

    
}

public Account loginAccount(Account account){
    return accountDAO.verifyAccount(account);
}

}
