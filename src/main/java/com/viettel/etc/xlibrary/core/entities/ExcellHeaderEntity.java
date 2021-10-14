/*    */ package com.viettel.etc.xlibrary.core.entities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExcellHeaderEntity
/*    */ {
/*    */   String headerName;
/*    */   Integer width;
/*    */   Short colorHeader;
/*    */   
/*    */   public ExcellHeaderEntity(String headerName, Integer width, Short colorHeader) {
/* 17 */     this.headerName = headerName;
/* 18 */     this.width = width;
/* 19 */     this.colorHeader = colorHeader;
/*    */   }
/*    */   
/*    */   public ExcellHeaderEntity(String headerName, Integer width) {
/* 23 */     this.headerName = headerName;
/* 24 */     this.width = width;
/*    */   }
/*    */   
/*    */   public ExcellHeaderEntity(String headerName) {
/* 28 */     this.headerName = headerName;
/*    */   }
/*    */ 
/*    */   
/*    */   public ExcellHeaderEntity() {}
/*    */   
/*    */   public String getHeaderName() {
/* 35 */     return this.headerName;
/*    */   }
/*    */   
/*    */   public void setHeaderName(String headerName) {
/* 39 */     this.headerName = headerName;
/*    */   }
/*    */   
/*    */   public Integer getWidth() {
/* 43 */     return this.width;
/*    */   }
/*    */   
/*    */   public void setWidth(Integer width) {
/* 47 */     this.width = width;
/*    */   }
/*    */   
/*    */   public Short getColorHeader() {
/* 51 */     return this.colorHeader;
/*    */   }
/*    */   
/*    */   public void setColorHeader(Short colorHeader) {
/* 55 */     this.colorHeader = colorHeader;
/*    */   }
/*    */ }


/* Location:              D:\DU_AN\MeOn\meon-backend\libs\com\viettel\telecare\libs\telecarelibs\1.0-RELEASE\telecarelibs-1.0-RELEASE.jar!\com\viettel\etc\xlibrary\core\entities\ExcellHeaderEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */