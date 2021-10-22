package com.mthree.yash.VendingMachine.dao;

import com.mthree.yash.VendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineDaoImpl implements VendingMachineDao {

    private final Map<String, Item> items = new HashMap<>();
    public static final String DELIMITER = "::";
    private final String VENDING_MACHINE_FILE;

    public VendingMachineDaoImpl() {
        VENDING_MACHINE_FILE = "VendingMachine.txt";
    }

    public VendingMachineDaoImpl(String testFile) {
        VENDING_MACHINE_FILE = testFile;
    }

    @Override
    public int getItemInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name).getInventory();
    }

    @Override
    public void removeOneItemFromInventory(String name) throws VendingMachinePersistenceException {
        loadMachine();
        int prevInventory = items.get(name).getInventory();
        items.get(name).setInventory(prevInventory - 1);
        writeMachine();
    }


    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        loadMachine();
        return items.get(name);
    }


    @Override
    public Map<String, BigDecimal> getMapOfItemNamesInStockWithCosts() throws VendingMachinePersistenceException {
        loadMachine();

        Map<String, BigDecimal> itemsInStockWithCosts = items.entrySet()
                .stream()
                .filter(map -> map.getValue().getInventory() > 0)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue().getCost()));

        return itemsInStockWithCosts;

    }

    private String marshallItem(Item anItem) {
        String itemAsText = anItem.getName() + DELIMITER;
        itemAsText += anItem.getCost() + DELIMITER;
        itemAsText += anItem.getInventory();
        return itemAsText;
    }


    private Item unmarshallItem(String itemAsText) {
        String[] itemTokens = itemAsText.split("::");
        String name = itemTokens[0];
        Item itemFromFile = new Item(name);
        BigDecimal bigDecimal = new BigDecimal(itemTokens[1]);
        itemFromFile.setCost(bigDecimal);
        itemFromFile.setInventory(Integer.parseInt(itemTokens[2]));
        return itemFromFile;
    }


    private void loadMachine() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDING_MACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load item data into memory.", e);
        }
        String currentLine;
        Item currentItem;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentItem = unmarshallItem(currentLine);
            items.put(currentItem.getName(), currentItem);
        }
        scanner.close();
    }


    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        loadMachine();
        return new ArrayList(items.values());
    }


    private void writeMachine() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save student data.", e);
        }
        String itemAsText;
        List<Item> itemList = this.getAllItems();
        for (Item currentItem : itemList) {
            itemAsText = marshallItem(currentItem);
            out.println(itemAsText);
            out.flush();
        }
        out.close();
    }


}