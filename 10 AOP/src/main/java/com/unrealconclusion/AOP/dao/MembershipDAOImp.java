package com.unrealconclusion.AOP.dao;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImp implements MembershipDAO {
    @Override
    public boolean addAccount() {
        System.out.println(getClass() + ": Adding a membership account");
        return true;
    }

    @Override
    public void doNothing() {
        System.out.println(getClass() + ": Doing Nothing");
    }
}
