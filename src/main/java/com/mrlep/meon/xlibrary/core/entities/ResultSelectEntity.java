package com.mrlep.meon.xlibrary.core.entities;

import java.util.List;


public class ResultSelectEntity {
    List<? extends Object> listData;
    Object count;

    public List<? extends Object> getListData() {
         return this.listData;
    }

    public void setListData(List<? extends Object> listData) {
      this.listData = listData;
    }

    public Object getCount() {
         return this.count;
    }

    public void setCount(Object count) {
         this.count = count;
    }
}