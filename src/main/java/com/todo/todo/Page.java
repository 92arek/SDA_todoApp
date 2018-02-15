package com.todo.todo;

public class Page {

    private int page;
    private int size;

    public Page(int limit, int size) {
        this.page = limit;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return (page - 1) * getSize();
    }
}
