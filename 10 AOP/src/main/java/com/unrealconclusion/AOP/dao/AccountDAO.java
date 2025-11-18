package com.unrealconclusion.AOP.dao;

import com.unrealconclusion.AOP.Account;

public interface AccountDAO {
    void addAccount(Account account, Boolean isVip);
    boolean doWork();
}
