package com.mthree.yash.VendingMachine.dto;

public enum Coin {
    //Enum is a special class that represents a group of constants, like final variables
    POUND(100), FIFTY(50), TWENTY(25), TEN(10), FIVE(5), PENNY(1);
    private final int value;

    Coin (int value) {
        this.value = value;
    }

    private int getValue() {
        return value;
    }

    public String toString(){
        switch (this) {
            case POUND:
                return "100";
            case FIFTY:
                return "50";
            case TWENTY:
                return "20";
            case TEN:
                return "10";
            case FIVE:
                return "5";
            case PENNY:
                return "1";
        }
        return null;
    }

}
