/*    */ package com.viettel.etc.xlibrary.exportfile;
/*    */ 
/*    */ import com.viettel.etc.xlibrary.core.constants.FunctionCommon;
/*    */ import com.viettel.etc.xlibrary.core.entities.ExcellDataEntity;
/*    */ import com.viettel.etc.xlibrary.core.entities.ExcellHeaderEntity;
/*    */ import com.viettel.etc.xlibrary.core.entities.ExcellSheet;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExcellExport
/*    */ {
/*    */   public static void main(String[] args) {
/* 22 */     ExcellSheet sheetExprort = new ExcellSheet();
/*    */     
/* 24 */     List<ExcellHeaderEntity> listHeader = new ArrayList<>();
/* 25 */     ExcellHeaderEntity item = new ExcellHeaderEntity();
/* 26 */     item.setHeaderName("test 1");
/* 27 */     item.setWidth(Integer.valueOf(4000));
/* 28 */     listHeader.add(item);
/* 29 */     item = new ExcellHeaderEntity();
/* 30 */     item.setHeaderName("test 2");
/* 31 */     listHeader.add(item);
/* 32 */     item = new ExcellHeaderEntity();
/* 33 */     item.setHeaderName("test 3");
/* 34 */     listHeader.add(item);
/* 35 */     sheetExprort.setListHeader(listHeader);
/*    */ 
/*    */ 
/*    */     
/* 39 */     ExcellDataEntity excellDataEntity = new ExcellDataEntity();
/* 40 */     List<List<Object>> listData = new ArrayList<>();
/*    */     
/* 42 */     List<Object> listOb = new ArrayList();
/* 43 */     listOb.add("ha noi");
/* 44 */     listOb.add(Integer.valueOf(1));
/* 45 */     listOb.add("Hồ gươm");
/* 46 */     listData.add(listOb);
/* 47 */     for (int i = 0; i < 10; i++) {
/* 48 */       listOb = new ArrayList();
/* 49 */       listOb.add("ha noi aaaa" + i);
/* 50 */       listOb.add(Integer.valueOf(2 + i));
/* 51 */       listOb.add("Hồ gươm" + i);
/* 52 */       listData.add(listOb);
/*    */     } 
/*    */     
/* 55 */     excellDataEntity.setListData(listData);
/* 56 */     sheetExprort.setExcellDataEntity(excellDataEntity);
/*    */     
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\exportfile\ExcellExport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */