package com.nchomework.netcrackerhw.model;

public final class Page {

    private int offset = 0;
    private int limit = -1;


    public Page() {
    }

    public Page(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
