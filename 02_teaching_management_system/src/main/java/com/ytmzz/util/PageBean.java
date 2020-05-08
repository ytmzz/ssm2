package com.ytmzz.util;

public class PageBean {
    private Integer currentPage = 1;
    private Integer count;
    private Integer showCount = 5;
    private Integer pages;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public Integer getPages() {
        if(count != null && showCount != null && showCount != 0) {
            pages = count % showCount == 0 ? count / showCount : count / showCount + 1;
        } else {
            pages = 0;
        }
        return pages;
    }

    private void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", count=" + count +
                ", showCount=" + showCount +
                ", pages=" + pages +
                '}';
    }
}
