package com.unrealconclusion.AOP.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImp implements AccountDAO {

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Adding an account");
    }

}
