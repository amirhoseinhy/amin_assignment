package com.niksan.amin.model;

public class Item {
    private int itemId;
    private int itemSelection;

    public int getItemStatus() {
        return itemSelection;
    }

    public void setItemStatus(int itemSelection) {
        this.itemSelection = itemSelection;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
