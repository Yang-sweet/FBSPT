package pers.chhuai.storm.beans;

import java.util.List;

/*
分页
 */
public class Page<T>{
    private int totalCount;
    private int totalPage;
    private List<T> list;//每页的数据
    private int currentPage;
    private int rows;//每页显示记录数

    public Page() {

    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Page(int totalCount, int totalPage, List<T> list, int currentPage, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
