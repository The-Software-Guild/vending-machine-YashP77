package com.mthree.yash.VendingMachine.ui;

import java.math.BigDecimal;
import java.util.Map;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView (UserIO io){
        this.io = io;
    }

    public BigDecimal getMoney() {
        return io.readBigDecimal("Please input the amount money in pounds before making selection");
    }

    public void displayMenuBanner() {
        io.print("=== Menu ===");
    }

    public void displayMenu(Map<String, BigDecimal> itemsInStockWithCosts){
        itemsInStockWithCosts.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + ": £" +entry.getValue());
        });
    }

    public String getItemSelection(){
        return io.readString("Please select an item from the menu above or 'exit' to quit");
    }

    public void displayEnjoyBanner(String name) {
        io.print("Here is your change.");
        io.print("Enjoy your " + name + "!");
    }

    public void displayInsufficientFundsMsg(BigDecimal money){
        io.print("Insufficient funds, you only have input £");
    }

    public void displayItemOutOfStockMsg(String name){
        io.print("Error, " + name + " is out of stock.");
    }

    public void displayChangeDuePerCoin(Map<BigDecimal, BigDecimal> changeDuePerCoin) {
        changeDuePerCoin.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() + "p : " +entry.getValue());
        });
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage (String errorMsg) {
        io.print("=== Error ===");
        io.print(errorMsg);
    }

    public void displayPleaseTryAgainMsg() {
        io.print("Please select something else.");
    }

}
