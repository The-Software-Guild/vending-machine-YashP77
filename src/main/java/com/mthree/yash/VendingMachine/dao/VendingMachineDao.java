package com.mthree.yash.VendingMachine.dao;

import com.mthree.yash.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface VendingMachineDao {

    void removeOneItemFromInventory(String name) throws VendingMachinePersistenceException;

    List<Item> getAllItems() throws VendingMachinePersistenceException ;

    int getItemInventory(String name) throws VendingMachinePersistenceException;
    Item getItem(String name)throws VendingMachinePersistenceException;

    Map<String, BigDecimal> getMapOfItemNamesInStockWithCosts()throws VendingMachinePersistenceException;

}
