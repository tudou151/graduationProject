package com.lut.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Page<T> {
    /**
     * 当前页数
     */
    private Integer currentPage=1;
    /**
     * 总页数
     */
    private Integer allPageNum=0;
    /**
     * 总记录数
     */
    private Integer totalCount;
    /**
     * 每页显示的最多条数
     */
    private static final Integer perPageSize=10;
    /**
     * 所有页数据
     */
    private Map<Integer,List<T>> datas = new HashMap<>();
    /**
     * 当前页显示的数据
     */
    private List<T> lists = new ArrayList<>();

    public Page(){}
    public Page(List<T> data){
        this.totalCount = data.size();
        if (data.size()!=0){
            int x = 0;
            List<T> es = new ArrayList<>();
            for (T t : data){
                ++x;
                if (x==perPageSize){
                    es.add(t);
                    allPageNum++;
                    x=0;
                    datas.put(allPageNum,es);
                    es = new ArrayList<>();
                }else{
                    es.add(t);
                }
            }
            if (data.size()%perPageSize!=0){
                allPageNum++;
                datas.put(allPageNum,es);
            }
        }
        setPage(1);
    }

    public void setPage(Integer pageNum){
        if (allPageNum>= pageNum && pageNum>0){
            currentPage = pageNum;
            lists = datas.get(currentPage);
        }
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getAllPageNum() {
        return allPageNum;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public static Integer getPerPageSize() {
        return perPageSize;
    }

    public Map<Integer, List<T>> getDatas() {
        return datas;
    }

    public List<T> getLists() {
        return lists;
    }
}
