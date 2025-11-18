package com.unrealconclusion.AOP.dao;

import org.springframework.stereotype.Repository;

import com.unrealconclusion.AOP.Account;

@Repository
public class AccountDAOImp implements AccountDAO {

    @Override
    public void addAccount(Account account, Boolean isVip) {
        System.out.println(getClass() + ": Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": Doing work");
        return false;
    }

}
