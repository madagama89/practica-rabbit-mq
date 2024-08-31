package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OrdersTacos {

    int numberOrder;
    int numberTable;
    String nameCustomer;
    ArrayList<Menu> optionMenu;

    public OrdersTacos() {}

    public OrdersTacos(int numberOrder, int numberTable, String nameCustomer, ArrayList<Menu> optionMenu) {
        this.numberOrder = numberOrder;
        this.numberTable = numberTable;
        this.nameCustomer = nameCustomer;
        this.optionMenu = optionMenu;
    }

    public int getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(int numberOrder) {
        this.numberOrder = numberOrder;
    }

    public int getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(int numberTable) {
        this.numberTable = numberTable;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public ArrayList<Menu> getOptionMenu() {
        return optionMenu;
    }

    public void setOptionMenu(ArrayList<Menu> optionMenu) {
        this.optionMenu = optionMenu;
    }
}

