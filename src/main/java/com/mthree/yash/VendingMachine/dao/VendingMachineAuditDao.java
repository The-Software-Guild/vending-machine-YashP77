package com.mthree.yash.VendingMachine.dao;

public interface VendingMachineAuditDao {

    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;

}
