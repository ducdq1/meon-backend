package com.viettel.etc.repositories.impl;

import com.viettel.etc.xlibrary.core.repositories.CommonDataBaseRepository;

import java.util.ArrayList;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Autogen class Repository Impl: Lop thong tin log
 * 
 * @author ToolGen
 * @date Thu Sep 23 09:15:40 ICT 2021
 */
@Repository
public class ActionLogRepositoryImpl extends CommonDataBaseRepository {

   /* *//**
     * 
     * 
     * @param itemParamsEntity: params client truyen len
     * @return 
     *//*
    @Override
    public List<ActionLogDTO> getActionLog(ActionLogDTO itemParamsEntity){
         StringBuilder sql = new StringBuilder();
         sql.append("select * from action_log");
         List<Object> arrParams = new ArrayList<>();
         //==========TODO: DEV Thuc hien bo sung params va edit query o day=====
         //Example: String sql = select * from table where column1=?,column2=?
         //         arrParams.add("value1");
         //         arrParams.add("value2");
         //==========END TODO ==================================================
         Integer start = null;
         if(itemParamsEntity!=null && itemParamsEntity.getStartrecord()!=null){
             start = itemParamsEntity.getStartrecord();
         }
         Integer pageSize = null;
         if(itemParamsEntity!=null && itemParamsEntity.getPagesize()!=null){
             pageSize = itemParamsEntity.getPagesize();
         }
         List<ActionLogDTO>  listData = (List<ActionLogDTO>) getListData(sql, arrParams, start, pageSize,ActionLogDTO.class);
         return listData;
    }*/
}