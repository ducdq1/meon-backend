/*    */ package com.viettel.etc.xlibrary.core.entities;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExcellSheet
/*    */ {
/*    */   String strSheetName;
/*    */   List<ExcellHeaderEntity> listHeader;
/*    */   ExcellDataEntity excellDataEntity;
/*    */   
/*    */   public ExcellSheet(String strSheetName, List<ExcellHeaderEntity> listHeader, ExcellDataEntity excellDataEntity) {
/* 19 */     this.strSheetName = strSheetName;
/* 20 */     this.listHeader = listHeader;
/* 21 */     this.excellDataEntity = excellDataEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public ExcellSheet() {}
/*    */   
/*    */   public String getStrSheetName() {
/* 28 */     return this.strSheetName;
/*    */   }
/*    */   
/*    */   public void setStrSheetName(String strSheetName) {
/* 32 */     this.strSheetName = strSheetName;
/*    */   }
/*    */   
/*    */   public List<ExcellHeaderEntity> getListHeader() {
/* 36 */     return this.listHeader;
/*    */   }
/*    */   
/*    */   public void setListHeader(List<ExcellHeaderEntity> listHeader) {
/* 40 */     this.listHeader = listHeader;
/*    */   }
/*    */   
/*    */   public ExcellDataEntity getExcellDataEntity() {
/* 44 */     return this.excellDataEntity;
/*    */   }
/*    */   
/*    */   public void setExcellDataEntity(ExcellDataEntity excellDataEntity) {
/* 48 */     this.excellDataEntity = excellDataEntity;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\entities\ExcellSheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */