package org.example;

public class Menu
{
    int id;
    String nameItem;
    String description;

    public Menu()
    {}

    public Menu(int id, String nameItem, String description) {
        this.id = id;
        this.nameItem = nameItem;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
