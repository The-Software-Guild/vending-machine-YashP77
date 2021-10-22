package com.mthree.yash.VendingMachine.service;

import com.mthree.yash.VendingMachine.dao.VendingMachineAuditDao;
import com.mthree.yash.VendingMachine.dao.VendingMachineDao;
import com.mthree.yash.VendingMachine.dao.VendingMachinePersistenceException;
import com.mthree.yash.VendingMachine.dto.Change;
import com.mthree.yash.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{

    private VendingMachineAuditDao auditDao;
    private VendingMachineDao dao;

    public VendingMachineServiceLayerImpl(VendingMachineAuditDao auditDao, VendingMachineDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    @Override
    public void checkIfEnoughMoney(Item item, BigDecimal inputMoney) throws InsufficientFundsException {

        if (item.getCost().compareTo(inputMoney)==1) {
            throw new InsufficientFundsException (
                    "ERROR: insufficient funds, you have only input "+ inputMoney);
        }
    }

    @Override
    public Map<String, BigDecimal> getItemsInStockWithCosts () throws VendingMachinePersistenceException {

        Map<String, BigDecimal> itemsInStockWithCosts = dao.getMapOfItemNamesInStockWithCosts();
        return itemsInStockWithCosts;
    }

    @Override
    public Map<BigDecimal, BigDecimal> getChangePerCoin(Item item, BigDecimal money) {
        BigDecimal itemCost = item.getCost();
        Map<BigDecimal, BigDecimal> changeDuePerCoin = Change.changeDuePerCoin(itemCost, money);
        return changeDuePerCoin;
    }

    @Override
    public Item getItem(String name, BigDecimal inputMoney) throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException {
        Item wantedItem = dao.getItem(name);

        if (wantedItem == null) {
            throw new NoItemInventoryException (
                    "ERROR: there are no " + name + "'s in the vending machine.");
        }

        checkIfEnoughMoney(wantedItem,inputMoney);
        removeOneItemFromInventory(name);
        return wantedItem;

    }


    public void removeOneItemFromInventory (String name) throws NoItemInventoryException, VendingMachinePersistenceException {

        if (dao.getItemInventory(name)>0) {
            dao.removeOneItemFromInventory(name);
            auditDao.writeAuditEntry(" One " + name + " removed");
        } else {
            throw new NoItemInventoryException (
                    "ERROR: " + name + " is out of stock.");
        }
    }

}
