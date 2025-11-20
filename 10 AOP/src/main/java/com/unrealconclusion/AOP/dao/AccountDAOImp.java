package com.unrealconclusion.AOP.dao;

import org.springframework.stereotype.Repository;

import com.unrealconclusion.AOP.Account;

@Repository
public class AccountDAOImp implements AccountDAO {
    private String name;
    private String serviceCode; 

    @Override
    public void addAccount(Account account, Boolean isVip) {
        System.out.println(getClass() + ": Adding an account");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": Doing work");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": Getting name");
        return this.name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": Setting name");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": Getting service code");
        return this.serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": Setting service code");
        this.serviceCode = serviceCode;
    }

}
