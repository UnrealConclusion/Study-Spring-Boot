package com.unrealconclusion.AOP.dao;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Account> findAccounts() {
        
        // calls the other find account without throwing an acception
        return this.findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        // throws an exception for demostration purposes
        if (tripWire) {
            throw new RuntimeException("Boom!"); // trip over the wire
        }

        List<Account> myAccounts = new ArrayList<>();
        myAccounts.add(new Account("Patrick", "Gold"));
        myAccounts.add(new Account("Sandy", "Gold"));
        myAccounts.add(new Account("Squidward", "Bronze"));
        return myAccounts;
    }
}
