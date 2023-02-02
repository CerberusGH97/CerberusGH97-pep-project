package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {
    
    public Account insertAccount (Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT into Account (username, password) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            return account;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public Account verifyAccount (Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Account WHERE username = ?, password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account account1 = new Account
                        (rs.getInt("account_id"),
                        rs.getString("username"),
                        rs.getString("password"));
                return account1;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }
}
