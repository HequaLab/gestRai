package com.hequalab.rai.api.dtos;

import java.util.List;

public class PageEvent<T> {

    private final int itemsPerPage;
    private final long totalItems;
    private final List<T> items;

    public PageEvent(int itemsPerPage, long totalItems, List<T> items) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.totalItems = totalItems;
    }
	
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public List<T> getItems() {
        return items;
    }

}