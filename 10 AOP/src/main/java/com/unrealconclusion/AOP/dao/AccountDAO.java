package com.unrealconclusion.AOP.dao;

import java.util.List;

import com.unrealconclusion.AOP.Account;

public interface AccountDAO {
    void addAccount(Account account, Boolean isVip);
    boolean doWork();
    void setName(String name);
    String getName();
    void setServiceCode(String serviceCode);
    String getServiceCode();
    List<Account> findAccounts();
}
